package com.dariotek.dao;

import java.util.Date;
import java.util.List;

import com.dariotek.webscraper.entity.YahooFinanceStockQuoteStatistics;
import com.dariotek.webscraper.entity.YahooFinanceStockQuoteSummary;

public interface YahooFinanceStockQuoteStatisticsDAO {
	
	// Add / Insert
	public void addYahooFInanceStockQuoteSummary(YahooFinanceStockQuoteStatistics yahooFinanceStockQuoteStatistics);
	
	// Update
	public void updateYahooFInanceStockQuoteSummary(YahooFinanceStockQuoteStatistics yahooFinanceStockQuoteStatistics);
	
	// Delete
	public void deleteYahooFInanceStockQuoteSummary(YahooFinanceStockQuoteStatistics yahooFinanceStockQuoteStatistics);
	
	// Save / Update
	public void saveOrupdateYahooFInanceStockQuoteSummary(YahooFinanceStockQuoteStatistics yahooFinanceStockQuoteStatistics);
	
	// Get one Record (symbol and date)
	public List<YahooFinanceStockQuoteStatistics> getHistoricalStockPrices(String symbol, Date startDate, Date endDate);
	
	// Get all records (symbol)
	public List<YahooFinanceStockQuoteStatistics> getHistoricalStockPrices(String symbol);
	
}
