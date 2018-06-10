package com.dariotek.webscraper.entity;

public class TradingInformation {

	private StockPriceHistory stockPriceHistory;
	private ShareStatistics shareStatistics;
	private DividendsAndSplits dividendsAndSplits;
	
	public TradingInformation() {
		this.stockPriceHistory = new StockPriceHistory();
		this.shareStatistics = new ShareStatistics();
		this.dividendsAndSplits = new DividendsAndSplits();
	}
	
	public StockPriceHistory getStockPriceHistory() {
		return stockPriceHistory;
	}
	public void setStockPriceHistory(StockPriceHistory stockPriceHistory) {
		this.stockPriceHistory = stockPriceHistory;
	}
	public ShareStatistics getShareStatistics() {
		return shareStatistics;
	}
	public void setShareStatistics(ShareStatistics shareStatistics) {
		this.shareStatistics = shareStatistics;
	}
	public DividendsAndSplits getDividendsAndSplits() {
		return dividendsAndSplits;
	}
	public void setDividendsAndSplits(DividendsAndSplits dividendsAndSplits) {
		this.dividendsAndSplits = dividendsAndSplits;
	}
	
	@Override
	public String toString() {
		return "TradingInformation [stockPriceHistory=" + stockPriceHistory + ", shareStatistics=" + shareStatistics
				+ ", dividendsAndSplits=" + dividendsAndSplits + "]";
	}
	
}
