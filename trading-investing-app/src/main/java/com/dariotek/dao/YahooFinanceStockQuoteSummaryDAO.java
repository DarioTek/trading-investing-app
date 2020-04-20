package com.dariotek.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dariotek.entity.HistoricalStockPrice;
import com.dariotek.webscraper.entity.YahooFinanceStockQuoteSummary;

@Component
public interface YahooFinanceStockQuoteSummaryDAO {

	// Add / Insert
	public void addYahooFinanceStockQuoteSummary(YahooFinanceStockQuoteSummary yahooFinanceStockQuoteSummary);
	
	// Update
	public void updateYahooFinanceStockQuoteSummary(YahooFinanceStockQuoteSummary yahooFinanceStockQuoteSummary);
	
	// Delete
	public void deleteYahooFinanceStockQuoteSummary(String symbol, Date date);
	
	// Save / Update
	public void saveOrUpdateYahooFinanceStockQuoteSummary(YahooFinanceStockQuoteSummary yahooFinanceStockQuoteSummary);
	
	// Get one Record (symbol and date)
	public YahooFinanceStockQuoteSummary getYahooFinanceStockQuoteSummary(String symbol, Date date);
	
	// Get a list of Records 
	public List<YahooFinanceStockQuoteSummary> getYahooFinanceStockQuoteSummary(String symbol, Date startDate, Date endDate);
	
	// Get all records (symbol)
	public List<YahooFinanceStockQuoteSummary> getYahooFinanceStockQuoteSummary(String symbol);

}
