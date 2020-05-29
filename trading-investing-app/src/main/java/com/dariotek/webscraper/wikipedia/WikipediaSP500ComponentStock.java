package com.dariotek.webscraper.wikipedia;

public class WikipediaSP500ComponentStock implements Comparable{
	
	public WikipediaSP500ComponentStock() {
		super();
	}
	
	private String symbol;
	private String securityName;
	private String sector;
	private String industry;
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getSecurityName() {
		return securityName;
	}
	public void setSecurityName(String securityName) {
		this.securityName = securityName;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
    /*
     * https://dzone.com/articles/how-to-sort-objects-in-java
     */	
	@Override
	public int compareTo(Object o) {
		return 0;
		//return this.getTickerSymbol().compareTo(((YahooFinanceStockQuoteSummary) o).getTickerSymbol());
	}
	

}
