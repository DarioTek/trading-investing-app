package com.dariotek.webscraper;

import org.joda.time.DateTime;
//import com.yahoofinance.webscrape.beans.QuoteSummary;
//import com.yahoofinance.webscrape.utils.WebScrapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dariotek.dao.YahooFinanceStockQuoteSummaryDAO;
import com.dariotek.entity.HistoricalStockPrice;
import com.dariotek.webscraper.entity.YahooFinanceStockQuoteSummary;

import java.io.IOException;
import java.util.Date;

/**
 * This maps to URL = https://finance.yahoo.com/quote/[symbol]
 * This is the contents of the Summary tab
 */
@Component
public class YahooFinanceStockQuoteSummaryScraper {

    private Logger logger = LoggerFactory.getLogger(YahooFinanceStockQuoteSummaryScraper.class);
    
    private String cssQuery = "td[class=\"Ta(end) Fw(600) Lh(14px)\"]";
    private String cssQuery2 = "span[class=\"Trsdu(0.3s) \"]";
    private String cssQuery3 = "span[data-reactid=\"114\"]";
    
    
    // CSS Class for the live price
    private String liveCssQuery = "[class*=\"Trsdu(0.3s) Fw(\"]";
    //<span class="Trsdu(0.3s) Fw(b) Fz(36px) Mb(-4px) D(ib) Bgc($lightRed) trendDown1" data-reactid="52">314.18</span>
    private String liveCssQueryChange = "[class=\"Trsdu(0.3s) Fw(500) Pstart(10px) Fz(24px)*\"]";
                                                
    public YahooFinanceStockQuoteSummaryScraper() {
    	
    }
    
    public YahooFinanceStockQuoteSummary getQuoteSummary(String tickerSymbol) {
        // Instantiating new instance of the Quote Summary Class
        YahooFinanceStockQuoteSummary quoteSummary = new YahooFinanceStockQuoteSummary();
        try {
        	String url = "https://finance.yahoo.com/quote/" + tickerSymbol;
        	logger.info("url = " + url);
            Document doc = Jsoup.connect(url).get();
            
            //System.out.println(doc.toString());
            
            YahooFinanceStockQuoteSummary.Key key = new YahooFinanceStockQuoteSummary.Key();
    		key.setTickerSymbol(tickerSymbol);
    		key.setDateTimeScraped(new Date());

            quoteSummary.setKey(key);

            quoteSummary.setLivePrice(YahooFinanceWebScraperUtils.stringToDouble(doc.select(liveCssQuery).get(0).text()));
            //quoteSummary.setLivePriceChange(doc.select(liveCssQuery).get(1).text());            
            
            quoteSummary.setPreviousClosingPrice(YahooFinanceWebScraperUtils.stringToDouble(doc.select(cssQuery2).get(0).text()));            
            quoteSummary.setOpeningPrice(YahooFinanceWebScraperUtils.stringToDouble(doc.select(cssQuery).get(1).text()));

            // The bidOffer, and bidQuantity are both in the same field in the HTML table, so they must be parsed in order to be retrieved
            quoteSummary.setBidOffer(YahooFinanceWebScraperUtils.getStockPriceByQuantity(doc.select(cssQuery).get(2).text()));
            quoteSummary.setBidQuantity(YahooFinanceWebScraperUtils.getStockQuantityByPrice(doc.select(cssQuery).get(2).text()));

            // The askingPrice, and askingQuantity are both in the same field in the HTML table, so they must be parsed in order to be retrieved
            quoteSummary.setAskingPrice(YahooFinanceWebScraperUtils.getStockPriceByQuantity(doc.select(cssQuery).get(3).text()));
            quoteSummary.setAskingQuantity(YahooFinanceWebScraperUtils.getStockQuantityByPrice(doc.select(cssQuery).get(3).text()));

            // The daysRangeStart and daysRangeEnd are both in the same field in the HTML table, so they will need to be parsed
            quoteSummary.setDaysRangeStart(YahooFinanceWebScraperUtils.getStockStartingPrice(doc.select(cssQuery).get(4).text()));
            quoteSummary.setDaysRangeEnd(YahooFinanceWebScraperUtils.getStockEndingPrice(doc.select(cssQuery).get(4).text()));

            // The same is true for the 52 Week Range
            quoteSummary.setFiftyTwoWeekRangeStart(YahooFinanceWebScraperUtils.getStockStartingPrice(doc.select(cssQuery).get(5).text()));
            quoteSummary.setFiftyTwoWeekRangeEnd(YahooFinanceWebScraperUtils.getStockEndingPrice(doc.select(cssQuery).get(5).text()));

            // remove the commas from both the volume and avgVolume variables
            quoteSummary.setVolume(YahooFinanceWebScraperUtils.removeCommasReturnInteger(doc.select(cssQuery).get(6).text()));
            quoteSummary.setAvgVolume(YahooFinanceWebScraperUtils.removeCommasReturnInteger(doc.select(cssQuery).get(7).text()));

            quoteSummary.setMarketCap(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(8).text()));
            quoteSummary.setBeta(YahooFinanceWebScraperUtils.stringToDouble(doc.select(cssQuery).get(9).text()));
            quoteSummary.setPeRatioTtm(YahooFinanceWebScraperUtils.stringToDouble(doc.select(cssQuery).get(10).text()));
            quoteSummary.setEpsTtm(YahooFinanceWebScraperUtils.stringToDouble(doc.select(cssQuery).get(11).text()));
             
            quoteSummary.setEarningsDateStart(YahooFinanceWebScraperUtils.getEarningsStartDate(doc.select(cssQuery).get(12).text()));
            //System.out.println("size = " + doc.select(cssQuery).get(12).childNodeSize());
            if (doc.select(cssQuery).get(12).childNodeSize() > 1) {
            	quoteSummary.setEarningsDateEnd(YahooFinanceWebScraperUtils.getEarningsEndDate(doc.select(cssQuery).get(12).text()));
            }
            

            // Need to return an array of the dividend and yield strings for regular expression needed to be used to extract the values
            String[] dividendYieldArray = YahooFinanceWebScraperUtils.getDividendYieldArray(doc.select(cssQuery).get(13).text());
            if (dividendYieldArray != null) {
                quoteSummary.setDividend(YahooFinanceWebScraperUtils.stringToDouble(dividendYieldArray[0]));
                quoteSummary.setYield(YahooFinanceWebScraperUtils.stringToDouble(dividendYieldArray[1]));
            }
            //quoteSummary.setExDividendDate(YahooFinanceWebScraperUtils.stringToDateYYYYMMDD(doc.select(cssQuery3).get(0).text()));
            quoteSummary.setOneYearTargetEstimate(YahooFinanceWebScraperUtils.stringToDouble(doc.select(cssQuery).get(15).text()));
            //quoteSummary.setDateEntered(new Date());

            //logger.info("QuoteSummary String: " + quoteSummary.toString());
            //logger.info("QuoteSummary HashCode: " + quoteSummary.hashCode());
                        
            
        } catch (IOException e) {
            logger.error("An error occurred while trying to retrieve the information from Yahoo Finance: \n" + e);
        }
        return quoteSummary;
    }
}