package com.dariotek.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dariotek.TradingInvestingAppConfig;
import com.dariotek.service.AmeritradeTransactionService;
import com.dariotek.service.HistoricalStockPriceService;

public class TestApp {;

	public static void main(String[] args) {
		
		int processedRecords = 0;
		long startTime = 0;
		long endTime = 0;
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TradingInvestingAppConfig.class);

		/*
		 * Loop through all the transaction files from 2002 to 2018 and load the records in MySQL
		 * trading_investing.ameritrade_transactions
		 */
		
		
		AmeritradeTransactionService ameritradeTransactionService = 
				context.getBean("ameritradeTransactionService", AmeritradeTransactionService.class);
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
		
		HistoricalStockPriceService historicalStockPriceService = 
				context.getBean("historicalStockPriceService", HistoricalStockPriceService.class);

		/*
		// Load historicalStockPriceService.loadHistoricalStockPricesFromYahooFinanceCSV()
		startTime = System.currentTimeMillis();
		System.out.println("Start Time: " + startTime);
		processedRecords = historicalStockPriceService.loadHistoricalStockPricesFromYahooFinanceCSV();
		System.out.println("Records Processed = " + processedRecords);
		endTime = System.currentTimeMillis();
		System.out.println("End Time: " + endTime + "\nTotal Time: " + (endTime - startTime));
		System.out.println("historicalStockPriceService.loadHistoricalStockPricesFromYahooFinanceCSV() : Records Processed = " + processedRecords);
		*/
		
		System.out.println("=====> historicalStockPriceService.populateDateDimensions()");
		startTime = System.currentTimeMillis();
		System.out.println("Start Time: " + startTime);
		processedRecords = historicalStockPriceService.populateDateDimensions();
		endTime = System.currentTimeMillis();
		System.out.println("End Time: " + endTime + "\nTotal Time: " + (endTime - startTime));
		System.out.println("historicalStockPriceService.populateDateDimensions(): Records Processed = " + processedRecords);
		
		System.out.println("=====> historicalStockPriceService.populateUpAndDownFields()");
		startTime = System.currentTimeMillis();
		System.out.println("Start Time: " + startTime);
		processedRecords = historicalStockPriceService.populateUpAndDownFields();
		endTime = System.currentTimeMillis();
		System.out.println("End Time: " + endTime + "\nTotal Time: " + (endTime - startTime));
		System.out.println("historicalStockPriceService.populateUpAndDownFields(): Records Processed = " + processedRecords);
		
		//historicalStockPriceService.test();
		
		context.close();

	}

}
