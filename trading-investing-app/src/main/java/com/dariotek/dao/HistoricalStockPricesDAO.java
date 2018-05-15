package com.dariotek.dao;

import java.util.Date;
import java.util.List;

import com.dariotek.entity.HistoricalStockPrice;

public interface HistoricalStockPricesDAO {
	
	public void addHistoricalStockPrices(HistoricalStockPrice HistoricalStockPrices);
	
	public void updateHistoricalStockPrices(HistoricalStockPrice HistoricalStockPrices);
	
	public void deleteHistoricalStockPricess(String symbol, Date date);
	
	public HistoricalStockPrice getHistoricalStockPrice(String symbol, Date date);
	
	public List<HistoricalStockPrice> getHistoricalStockPrices(String symbol, Date startDate, Date endDate);
	
	public List<HistoricalStockPrice> getAllHistoricalStockPrices(String symbol);

	public List<String> getListOfStockSymbols();
	
}
