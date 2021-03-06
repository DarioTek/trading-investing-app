package com.dariotek.webscraper.yahoofinance;

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
    
    //CSS Selector patterns to parse Yahoo Finance website 
    private String cssCurrentPrice = "[class*=\"Trsdu(0.3s) Fw(\"]";
    private String cssPreviousClosePrice = "td[data-test=\"PREV_CLOSE-value\"]";
    private String cssOpenPrice = "td[data-test=\"PREV_CLOSE-value\"]";
    private String cssExDividendDate = "td[data-test=\"EX_DIVIDEND_DATE-value\"]";
    private String cssEarningsDate = "td[data-test=\"EARNINGS_DATE-value\"]";
    private String cssFiftyTwoWeekPriceRange = "td[data-test=\"FIFTY_TWO_WK_RANGE-value\"]";
    private String cssQuery = "td[class=\"Ta(end) Fw(600) Lh(14px)\"]"; //Generic Tag Selector on Yahoo Finance Page
 
                                                
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
            quoteSummary.setTickerSymbol(tickerSymbol);
            quoteSummary.setLivePrice(YahooFinanceWebScraperUtils.stringToDouble(doc.select(cssCurrentPrice).get(0).text()));
            //quoteSummary.setLivePriceChange(doc.select(liveCssQuery).get(1).text());            
            
            quoteSummary.setPreviousClosingPrice(YahooFinanceWebScraperUtils.stringToDouble(doc.select(cssPreviousClosePrice).get(0).text()));            
            quoteSummary.setOpeningPrice(YahooFinanceWebScraperUtils.stringToDouble(doc.select(cssOpenPrice).get(0).text()));

            // The bidOffer, and bidQuantity are both in the same field in the HTML table, so they must be parsed in order to be retrieved
            quoteSummary.setBidOffer(YahooFinanceWebScraperUtils.getStockPriceByQuantity(doc.select(cssQuery).get(2).text()));
            quoteSummary.setBidQuantity(YahooFinanceWebScraperUtils.getStockQuantityByPrice(doc.select(cssQuery).get(2).text()));

            // The askingPrice, and askingQuantity are both in the same field in the HTML table, so they must be parsed in order to be retrieved
            quoteSummary.setAskingPrice(YahooFinanceWebScraperUtils.getStockPriceByQuantity(doc.select(cssQuery).get(3).text()));
            quoteSummary.setAskingQuantity(YahooFinanceWebScraperUtils.getStockQuantityByPrice(doc.select(cssQuery).get(3).text()));

            // The daysRangeStart and daysRangeEnd are both in the same field in the HTML table, so they will need to be parsed
            quoteSummary.setDaysRangeLowPrice(YahooFinanceWebScraperUtils.getStockStartingPrice(doc.select(cssQuery).get(4).text()));
            quoteSummary.setDaysRangeHighPrice(YahooFinanceWebScraperUtils.getStockEndingPrice(doc.select(cssQuery).get(4).text()));

            // The same is true for the 52 Week Range
            quoteSummary.setFiftyTwoWeekRangeLow(YahooFinanceWebScraperUtils.getStockStartingPrice(doc.select(cssFiftyTwoWeekPriceRange).get(0).text()));
            quoteSummary.setFiftyTwoWeekRangeHigh(YahooFinanceWebScraperUtils.getStockEndingPrice(doc.select(cssFiftyTwoWeekPriceRange).get(0).text()));

            // remove the commas from both the volume and avgVolume variables
            quoteSummary.setVolume(YahooFinanceWebScraperUtils.removeCommasReturnInteger(doc.select(cssQuery).get(6).text()));
            quoteSummary.setAvgVolume(YahooFinanceWebScraperUtils.removeCommasReturnInteger(doc.select(cssQuery).get(7).text()));

            quoteSummary.setMarketCap(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(8).text()));
            quoteSummary.setBeta(YahooFinanceWebScraperUtils.stringToDouble(doc.select(cssQuery).get(9).text()));
            quoteSummary.setPeRatioTtm(YahooFinanceWebScraperUtils.stringToDouble(doc.select(cssQuery).get(10).text()));            
            quoteSummary.setEpsTtm(YahooFinanceWebScraperUtils.stringToDouble(doc.select(cssQuery).get(11).text()));
            
            quoteSummary.setEarningsDate(YahooFinanceWebScraperUtils.getEarningsStartDate(doc.select(cssEarningsDate).get(0).text()));
            quoteSummary.setEarningsDateEnd(YahooFinanceWebScraperUtils.getEarningsEndDate(doc.select(cssEarningsDate).get(0).text())); // Gets 2nd Earnings Date

            // Need to return an array of the dividend and yield strings for regular expression needed to be used to extract the values
            String[] dividendYieldArray = YahooFinanceWebScraperUtils.getDividendYieldArray(doc.select(cssQuery).get(13).text());
            if (dividendYieldArray != null) {
                quoteSummary.setDividend(YahooFinanceWebScraperUtils.stringToDouble(dividendYieldArray[0]));
                quoteSummary.setDividendYield(YahooFinanceWebScraperUtils.stringToDouble(dividendYieldArray[1]));
            }
            quoteSummary.setExDividendDate(YahooFinanceWebScraperUtils.stringToDateMMMDDYYYY(doc.select(cssExDividendDate).get(0).text()));
            quoteSummary.setOneYearTargetEstimate(YahooFinanceWebScraperUtils.stringToDouble(doc.select(cssQuery).get(15).text()));                        
            
        } catch (IOException e) {
            logger.error("An error occurred while trying to retrieve the information from Yahoo Finance: \n" + e);
        }
        return quoteSummary;
    }
}