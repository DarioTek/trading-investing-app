package com.dariotek.webscraper.yahoofinance;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dariotek.webscraper.entity.DividendYieldComparator;
import com.dariotek.webscraper.entity.PercentageEarningPotentialComparator;
import com.dariotek.webscraper.entity.PriceComparator;
import com.dariotek.webscraper.entity.YahooFinanceStockQuoteSummary;
import com.dariotek.webscraper.wikipedia.WikipediaSP500CompanyListScraper;
import com.dariotek.webscraper.wikipedia.WikipediaSP500ComponentStock;

public class YahooFinanceWebScraperApp {

    private static Logger logger = LoggerFactory.getLogger(YahooFinanceWebScraperApp.class);

    
    public static void main(String[] args) {

    	WikipediaSP500CompanyListScraper wikipedia = new WikipediaSP500CompanyListScraper();
    	List<WikipediaSP500ComponentStock> sp500List = wikipedia.getWikipediaSP500CompanyList();
    	    	
    	String[] stockList = new String[sp500List.size()];
    	for(int i = 0; i < sp500List.size(); i++) {
    		stockList[i] = sp500List.get(i).getSymbol();
    	}    	
    	    	
    	//String[] stockList = {"DOW","XOM","IBM","VZ","CVX","PFE","MMM","WBA","CSCO","KO","FB","AAPL","NFLX","GOOGL","GS","JPM","C","FB","MSFT","DIS","MA","V","TSLA","HD","WDAY"};
    	//String[] indexList= {"QQQ","IWM","DIA","SPY"}; //TODO: Code currently does not scrape properly for indexes 
    	
    	
    	List<YahooFinanceStockQuoteSummary> scrappedObjects = new ArrayList<>();
    	
    	//long startTimeToScrapeYahooFinance = System.nanoTime();
    	long startTimeToScrapeYahooFinance = System.currentTimeMillis();
    	// Yahoo Finance Summary
    	for (String stock: stockList) {    		
    		if (stock != null && !stock.contains(".") && YahooFinanceWebScraperUtils.isYahooUrlValid(stock)) {
    			try {
	    	        YahooFinanceStockQuoteSummaryScraper getQuoteSummary = new YahooFinanceStockQuoteSummaryScraper();    	        
	    	        YahooFinanceStockQuoteSummary yahooFinanceStockQuoteSummary = getQuoteSummary.getQuoteSummary(stock);
	    	        scrappedObjects.add(yahooFinanceStockQuoteSummary);
    			}catch(Exception e) {
    				e.printStackTrace();
    			}
    		}
    	}
    	//long endTimeToScrapeYahooFinance = System.nanoTime();
    	//logger.info((endTimeToScrapeYahooFinance - startTimeToScrapeYahooFinance)/1000000 + " milliseconds to scrape " + stockList.length + " stock information from Yahoo Finance.");
    	long endTimeToScrapeYahooFinance = System.currentTimeMillis();
    	
    	/*
    	 * SORT THE LIST BASED ON SPECIFIC COMPARATORS
    	 */
    	long startTimeToSort = System.currentTimeMillis();
    	//Collections.sort(scrappedObjects); // Sort ArrayList using Stock Ticker using the compareTo() of the Comparable interface
    	Collections.sort(scrappedObjects, new PriceComparator());
    	//Collections.sort(scrappedObjects, new PercentageEarningPotentialComparator());
    	//Collections.sort(scrappedObjects, new DividendYieldComparator());
    	long endTimeToSort = System.currentTimeMillis();
    	
    	for(int i=0; i <= scrappedObjects.size()-1; i++) {
    		YahooFinanceStockQuoteSummary yahooFinanceStockQuoteSummary = (YahooFinanceStockQuoteSummary)scrappedObjects.get(i);
    		logger.info(yahooFinanceStockQuoteSummary.toString());
    	}
    	
    	logger.info((endTimeToScrapeYahooFinance - startTimeToScrapeYahooFinance) + " milliseconds to scrape " + scrappedObjects.size() + " stock information from Yahoo Finance.");
    	logger.info((endTimeToSort - startTimeToSort) + " milliseconds to sort.");
    	
    }
}
