package com.dariotek.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dariotek.TradingInvestingAppConfig;
import com.dariotek.dao.YahooFinanceStockQuoteSummaryDAO;
import com.dariotek.dao.YahooFinanceStockQuoteSummaryDAOImpl;
import com.dariotek.service.AmeritradeTransactionService;
import com.dariotek.service.HistoricalStockPriceService;
import com.dariotek.webscraper.YahooFinanceStockQuoteSummaryScraper;
import com.dariotek.webscraper.YahooFinanceWebScraperApp;
import com.dariotek.webscraper.YahooFinanceWebScraperUtils;
import com.dariotek.webscraper.entity.YahooFinanceStockQuoteSummary;

public class TestYahooFinanceWebScraper {;

	private static Logger logger = LoggerFactory.getLogger(YahooFinanceWebScraperApp.class);
	
	public static void main(String[] args) {
		
		int processedRecords = 0;
		long startTime = 0;
		long endTime = 0;
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TradingInvestingAppConfig.class);

		YahooFinanceStockQuoteSummaryDAO yahooFinanceStockQuoteSummaryDAO = context.getBean(YahooFinanceStockQuoteSummaryDAOImpl.class);
		
    	String[] stockList = {"VZ", "AAPL", "JPM", "C", "FB", "MSFT", "KO", "DIS", "V","WBA", "PG", "MA"};
    	
    	// Yahoo Finance Summary
    	for (String stock: stockList) {
    		String url = "https://finance.yahoo.com/quote/" + stock;
    		
    		if (YahooFinanceWebScraperUtils.isYahooUrlValid(stock)) {
    	        logger.info("Start scraping Yahoo Finance for " + stock);    	       
    	        YahooFinanceStockQuoteSummaryScraper getQuoteSummary = new YahooFinanceStockQuoteSummaryScraper();    	        
    	        YahooFinanceStockQuoteSummary yahooFinanceStockQuoteSummary = getQuoteSummary.getQuoteSummary(stock);
    	        
    	        Double potentialEarning = yahooFinanceStockQuoteSummary.getOneYearTargetEstimate() - yahooFinanceStockQuoteSummary.getLivePrice();
    	        yahooFinanceStockQuoteSummary.setPotentialEarning(potentialEarning);

    	        Double potentialEarningPercent = (potentialEarning/yahooFinanceStockQuoteSummary.getPreviousClosingPrice())*100;
    	        yahooFinanceStockQuoteSummary.setPotentialEarningPercent(potentialEarningPercent);

    	        logger.info(yahooFinanceStockQuoteSummary.toString());
    	        
    	        
    	        //yahooFinanceStockQuoteSummaryDAO.saveOrUpdateYahooFinanceStockQuoteSummary(yahooFinanceStockQuoteSummary);
    	        //yahooFinanceStockQuoteSummaryDAO.updateYahooFinanceStockQuoteSummary(yahooFinanceStockQuoteSummary);
    	        logger.info("added to DB " + stock);
    		}
    	}

		/*
		 * Loop through all the transaction files from 2002 to 2018 and load the records in MySQL
		 * trading_investing.ameritrade_transactions
		 */		
		
		//AmeritradeTransactionService ameritradeTransactionService = context.getBean("ameritradeTransactionService", AmeritradeTransactionService.class);
		
		/*
		// Load ameritradeTransactionService.loadAmeritradeTransactionsFromCSV()
		startTime = System.currentTimeMillis();
		System.out.println("Start Time: " + startTime);
		processedRecords = ameritradeTransactionService.loadAmeritradeTransactionsFromCSV();
		System.out.println("Records Processed = " + processedRecords);
		endTime = System.currentTimeMillis();
		System.out.println("End Time: " + endTime + "\nTotal Time: " + (endTime - startTime));
		System.out.println("ameritradeTransactionService.loadAmeritradeTransactionsFromCSV() : Records Processed = " + processedRecords);
		*/
		
		context.close();

	}

}
