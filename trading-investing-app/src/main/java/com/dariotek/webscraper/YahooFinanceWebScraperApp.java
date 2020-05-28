package com.dariotek.webscraper;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dariotek.webscraper.entity.PriceComparator;
import com.dariotek.webscraper.entity.YahooFinanceStockQuoteSummary;

public class YahooFinanceWebScraperApp {

    private static Logger logger = LoggerFactory.getLogger(YahooFinanceWebScraperApp.class);

    
    public static void main(String[] args) {

    	//String[] stockList = {"GS","DIS","TSLA","WDAY"};
    	String[] stockList = {"DOW","XOM","IBM","VZ","CVX","PFE","MMM","WBA","CSCO","KO","FB","AAPL","NFLX","GOOGL","GS","JPM","C","FB","MSFT","DIS","MA","V","TSLA","HD","WDAY"};
    	//String[] indexList= {"QQQ","IWM","DIA","SPY"}; //TODO: Code currently does not scrape properly for indexes 
    	
    	List<YahooFinanceStockQuoteSummary> scrappedObjects = new ArrayList<>();
    	
    	// Yahoo Finance Summary
    	for (String stock: stockList) {
    		
    		if (YahooFinanceWebScraperUtils.isYahooUrlValid(stock)) {
    	        logger.info("Start scraping Yahoo Finance for " + stock);    	       
    	        YahooFinanceStockQuoteSummaryScraper getQuoteSummary = new YahooFinanceStockQuoteSummaryScraper();    	        
    	        YahooFinanceStockQuoteSummary yahooFinanceStockQuoteSummary = getQuoteSummary.getQuoteSummary(stock);
    	        
    	        /*
    	        Double earningsPotentialValue = yahooFinanceStockQuoteSummary.getOneYearTargetEstimate() - yahooFinanceStockQuoteSummary.getLivePrice();
    	        Double earningsPotentialPercentage = (earningsPotentialValue / yahooFinanceStockQuoteSummary.getLivePrice()) * 100;    	            	            	        
    	            	        
    	        NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
    	        nf.setMaximumFractionDigits(2);
    	        logger.info("Earnings Potential Value = " + nf.format(earningsPotentialValue));
    	        logger.info("Earnings Potential Percentage = " + nf.format(earningsPotentialPercentage));
    	        System.out.println("\n\n");
    	        */

    	        //Add object to list
    	        scrappedObjects.add(yahooFinanceStockQuoteSummary);
    	        //logger.info(yahooFinanceStockQuoteSummary.toString());

    	        //TODO: How close is the current value to 52 week low?
    	        //TODO: How close is the current value to 52 week high?
    	        
    	        /*
    	         * https://dzone.com/articles/how-to-sort-objects-in-java
    	         * https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/
    	         */
    		}
    	}
    	
    	System.out.println("SORTED OBJECTS");
    	//Collections.sort(scrappedObjects); // Sort ArrayList using Stock Ticker using the compareTo() of the Comparable interface
    	Collections.sort(scrappedObjects, new PriceComparator());
    	
    	for(int i=0; i <= scrappedObjects.size()-1; i++) {
    		YahooFinanceStockQuoteSummary yahooFinanceStockQuoteSummary = (YahooFinanceStockQuoteSummary)scrappedObjects.get(i);
    		logger.info(yahooFinanceStockQuoteSummary.toString());
    	}
    	
    }
}
