package com.dariotek.webscraper.entity;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by DarioTek 5/23/2018.
 */
public class YahooFinanceStockQuoteStatistics {

    private String tickerSymbol;
    private ValuationMeasures valueationMeasures;
    private FinancialHighlights financialHighlights;
    private TradingInformation tradingInformation;
	
    public YahooFinanceStockQuoteStatistics() {
    	this.valueationMeasures = new ValuationMeasures();
    	this.financialHighlights = new FinancialHighlights();
    	this.tradingInformation = new TradingInformation();
    }
    
    public String getTickerSymbol() {
		return tickerSymbol;
	}
	public void setTickerSymbol(String tickerSymbol) {
		this.tickerSymbol = tickerSymbol;
	}
	public ValuationMeasures getValueationMeasures() {
		return valueationMeasures;
	}
	public void setValueationMeasures(ValuationMeasures valueationMeasures) {
		this.valueationMeasures = valueationMeasures;
	}
	public FinancialHighlights getFinancialHighlight() {
		return financialHighlights;
	}
	public void setFinancialHighlight(FinancialHighlights financialHighlight) {
		this.financialHighlights = financialHighlight;
	}
	public TradingInformation getTradingInformation() {
		return tradingInformation;
	}
	public void setTradingInformation(TradingInformation tradingInformation) {
		this.tradingInformation = tradingInformation;
	}
	@Override
	public String toString() {
		return "YahooFinanceStockQuoteStatistics [tickerSymbol=" + tickerSymbol + ", valueationMeasures="
				+ valueationMeasures + ", financialHighlight=" + financialHighlights + ", tradingInformation="
				+ tradingInformation + "]";
	}
    
    
	
}