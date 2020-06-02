package com.dariotek.webscraper.ameritrade;

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
public class AmeritradeScraper {

    private Logger logger = LoggerFactory.getLogger(AmeritradeScraper.class);
    
    //CSS Selector patterns to parse Yahoo Finance website 
    private String cssCurrentPrice = "[class*=\"Trsdu(0.3s) Fw(\"]";
    private String cssPreviousClosePrice = "td[data-test=\"PREV_CLOSE-value\"]";
    private String cssOpenPrice = "td[data-test=\"PREV_CLOSE-value\"]";
    private String cssExDividendDate = "td[data-test=\"EX_DIVIDEND_DATE-value\"]";
    private String cssEarningsDate = "td[data-test=\"EARNINGS_DATE-value\"]";
    private String cssFiftyTwoWeekPriceRange = "td[data-test=\"FIFTY_TWO_WK_RANGE-value\"]";
    private String cssQuery = "td[class=\"Ta(end) Fw(600) Lh(14px)\"]"; //Generic Tag Selector on Yahoo Finance Page
 
                                                
    public AmeritradeScraper() {
    	super();
    }
        
    public void connectToAmeritrade() {
        try {
        	String url = "https://www.tdameritrade.com/home.page";
        	logger.info("url = " + url);
            Document doc = Jsoup.connect(url).get();
            
            System.out.println(doc.toString());
            
           
        } catch (IOException e) {
            logger.error("An error occurred while trying to retrieve the information from Yahoo Finance: \n" + e);
        }
    }
    
    public static void main(String[] args) {
    	AmeritradeScraper as = new AmeritradeScraper();
    	as.connectToAmeritrade();
    }
}