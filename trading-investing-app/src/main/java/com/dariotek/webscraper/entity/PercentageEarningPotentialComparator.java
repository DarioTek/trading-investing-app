package com.dariotek.webscraper.entity;

import java.util.Comparator;

public class PercentageEarningPotentialComparator implements Comparator<YahooFinanceStockQuoteSummary> {

	public PercentageEarningPotentialComparator() {
		super();
	}
	
    @Override
    public int compare(YahooFinanceStockQuoteSummary o1, YahooFinanceStockQuoteSummary o2) {
    	return o1.calculateEarningPotentialPercentage() <  o2.calculateEarningPotentialPercentage() ? -1 : o1.calculateEarningPotentialPercentage() == o2.calculateEarningPotentialPercentage() ? 0 : 1;
    }

}
