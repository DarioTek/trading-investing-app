package com.dariotek.webscraper;


import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dariotek.webscraper.entity.YahooFinanceStockQuoteStatistics;
import com.dariotek.webscraper.entity.YahooFinanceStockQuoteSummary;

public class YahooFinanceWebScraperApp {

    private static Logger logger = LoggerFactory.getLogger(YahooFinanceWebScraperApp.class);

    public static void main(String[] args) {

    	String[] stockList = {"AAPL", "JPM", "C", "FB", "MSFT", "KO", "DIS", "V"};
    	
    	// Yahoo Finance Summary
    	for (String stock: stockList) {
    		String url = "https://finance.yahoo.com/quote/" + stock;
    		
    		if (YahooFinanceWebScraperUtils.isYahooUrlValid(stock)) {
    	        logger.info("Start scraping Yahoo Finance for " + stock);    	       
    	        YahooFinanceStockQuoteSummaryScraper getQuoteSummary = new YahooFinanceStockQuoteSummaryScraper();    	        
    	        YahooFinanceStockQuoteSummary quote = getQuoteSummary.getQuoteSummary(stock);    	        
    	        logger.info(quote.toString());
    		}
    	}
    	
    	/*
    	// Yahoo Finance Statistics
    	for (String stock: stockList) {
    		String url = "https://finance.yahoo.com/quote/" + stock;
    		
    		if (YahooFinanceWebScraperUtils.isYahooStatisticsUrlValid(stock)) {
    	        logger.info("Start scraping Yahoo Finance for " + stock);
    	        YahooFinanceStockQuoteStatisticsScraper getQuoteStatistics = new YahooFinanceStockQuoteStatisticsScraper();
    	        YahooFinanceStockQuoteStatistics quote = getQuoteStatistics.getQuoteStatistics(stock);
    	        logger.info(quote.toString());
    		}
    	}
		*/
		
    	/*
    	System.out.println("=======================================>");
    	BigDecimal num = YahooFinanceWebScraperUtils.stringToBigDecimal("12.567T");
    	System.out.println("Big Decimal num = " + num);

    	System.out.println("=======================================>");
    	Double num2 = YahooFinanceWebScraperUtils.stringToDoubleWithChar("12.567T");
    	System.out.println("Double num = " + num2);
		*/
    }
}
