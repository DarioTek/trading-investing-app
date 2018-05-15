package com.dariotek.service;

import org.springframework.stereotype.Component;

@Component
public interface HistoricalStockPriceService {

	public int loadHistoricalStockPricesFromYahooFinanceCSV();
	
	public int populateDateDimensions();
	
	public int populateUpAndDownFields();
	
	public int createCSVForAnalytics();
	
	public void test();
	
}
