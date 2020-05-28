package com.dariotek.webscraper.entity;

import java.util.Comparator;

public class PriceComparator implements Comparator<YahooFinanceStockQuoteSummary> {

	public PriceComparator() {
		super();
	}
	
    @Override
    public int compare(YahooFinanceStockQuoteSummary o1, YahooFinanceStockQuoteSummary o2) {
    	return o1.getLivePrice() <  o2.getLivePrice() ? -1 : o1.getLivePrice() == o2.getLivePrice() ? 0 : 1;
    }
    
}
