package com.dariotek.webscraper.wikipedia;

import org.joda.time.DateTime;
//import com.yahoofinance.webscrape.beans.QuoteSummary;
//import com.yahoofinance.webscrape.utils.WebScrapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dariotek.dao.YahooFinanceStockQuoteSummaryDAO;
import com.dariotek.entity.HistoricalStockPrice;
import com.dariotek.webscraper.entity.YahooFinanceStockQuoteSummary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This maps to URL = https://finance.yahoo.com/quote/[symbol]
 * This is the contents of the Summary tab
 */
@Component
public class WikipediaSP500CompanyListScraper {

    private Logger logger = LoggerFactory.getLogger(WikipediaSP500CompanyListScraper.class);
    
    //CSS Selector patterns to parse Yahoo Finance website 
    private String cssCurrentPrice = "[class*=\"Trsdu(0.3s) Fw(\"]";
    private String cssPreviousClosePrice = "td[data-test=\"PREV_CLOSE-value\"]";
    private String cssOpenPrice = "td[data-test=\"PREV_CLOSE-value\"]";
    private String cssExDividendDate = "td[data-test=\"EX_DIVIDEND_DATE-value\"]";
    private String cssEarningsDate = "td[data-test=\"EARNINGS_DATE-value\"]";
    private String cssFiftyTwoWeekPriceRange = "td[data-test=\"FIFTY_TWO_WK_RANGE-value\"]";
    private String cssQuery = "td[class=\"Ta(end) Fw(600) Lh(14px)\"]"; //Generic Tag Selector on Yahoo Finance Page
 
                                                
    public WikipediaSP500CompanyListScraper() {
    	super();
    }
    
    public List<WikipediaSP500ComponentStock> getWikipediaSP500CompanyList() {

    	List<WikipediaSP500ComponentStock> sp500List = new ArrayList();
        try {
        	String url = "https://en.wikipedia.org/wiki/List_of_S%26P_500_companies";
        	logger.info("url = " + url);
            Document doc = Jsoup.connect(url).get();
         
            Elements trs = doc.select("table.wikitable[id=\"constituents\"] tr");
            
            for (int index = 0; index < trs.size(); index++) {
            	//System.out.println(trs.get(index).select("td").text());
            	Elements tds = trs.get(index).select("td");
            	WikipediaSP500ComponentStock componentStock = new WikipediaSP500ComponentStock();
            	for (int i = 0; i < tds.size(); i++) {
            		switch(i) {
            			case 0: componentStock.setSymbol(tds.get(i).text());
            					break;
            			case 1: componentStock.setSecurityName(tds.get(i).text());
            					break;
            			case 3: componentStock.setSector(tds.get(i).text());
            					break;
            			case 4: componentStock.setIndustry(tds.get(i).text());
            					break;
            		}
            		//System.out.println(i + " - " + tds.get(i).text());
            	}
            	/*
            	componentStock.setSymbol(tds.get(0).text());
            	componentStock.setSecurityName(tds.get(1).text());
            	componentStock.setSector(tds.get(3).text());
            	componentStock.setIndustry(tds.get(4).text());
            	*/
            	sp500List.add(componentStock);
            	/*
            	for (int i = 0; i < tds.size(); i++) {
            		System.out.println(i + " - " + tds.get(i).text());
            	}
            	*/
            }
           
        } catch (IOException e) {
            logger.error("An error occurred while trying to retrieve the information from Yahoo Finance: \n" + e);
        }   
        
        return sp500List;
    }
}