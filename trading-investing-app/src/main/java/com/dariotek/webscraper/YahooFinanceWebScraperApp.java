package com.dariotek.webscraper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dariotek.webscraper.entity.YahooFinanceStockQuoteSummary;

public class YahooFinanceWebScraperApp {

    private static Logger logger = LoggerFactory.getLogger(YahooFinanceWebScraperApp.class);

    
    public static void main(String[] args) {

    	String[] stockList = {"FB","AAPL","NFLX","GOOGL","GS","JPM","C","FB","MSFT","DIS","MA","V","TSLA","HD"};
    	String[] indexList= {"QQQ","IWM","DIA","SPY"}; //TODO: Code currently does not scrape properly for indexes 
    	
    	// Yahoo Finance Summary
    	for (String stock: stockList) {
    		
    		if (YahooFinanceWebScraperUtils.isYahooUrlValid(stock)) {
    	        logger.info("Start scraping Yahoo Finance for " + stock);    	       
    	        YahooFinanceStockQuoteSummaryScraper getQuoteSummary = new YahooFinanceStockQuoteSummaryScraper();    	        
    	        YahooFinanceStockQuoteSummary yahooFinanceStockQuoteSummary = getQuoteSummary.getQuoteSummary(stock);
    	        
    	        Double earningsPotentialValue = yahooFinanceStockQuoteSummary.getOneYearTargetEstimate() - yahooFinanceStockQuoteSummary.getLivePrice();
    	        Double earningsPotentialPercentage = (earningsPotentialValue / yahooFinanceStockQuoteSummary.getLivePrice()) * 100;    	            	            	        
    	        
    	        logger.info(yahooFinanceStockQuoteSummary.toString());
    	        logger.info("EarningsPotentialValue = " + earningsPotentialValue);
    	        logger.info("EarningsPotentialPercentage = " + earningsPotentialPercentage);
    	        System.out.println("\n\n");
    	        
    	        //TODO: How close is the current value to 52 week low?
    	        //TODO: How close is the current value to 52 week high?
    		}
    	}
    	
    }
}
