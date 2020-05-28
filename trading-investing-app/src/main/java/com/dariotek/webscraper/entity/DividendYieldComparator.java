package com.dariotek.webscraper.entity;

import java.util.Comparator;

public class DividendYieldComparator implements Comparator<YahooFinanceStockQuoteSummary> {
	
	public DividendYieldComparator() {
		super();
	}
	
    @Override
    public int compare(YahooFinanceStockQuoteSummary o1, YahooFinanceStockQuoteSummary o2) {
    	return o1.getDividendYield() <  o2.getDividendYield() ? -1 : o1.getDividendYield() == o2.getDividendYield() ? 0 : 1;
    }

}
