package com.dariotek.webscraper.wikipedia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.dariotek.webscraper.entity.YahooFinanceStockQuoteSummary;

public class WikipediaSP500CompanyList{

	List<WikipediaSP500ComponentStock> sp500List = null;
	HashMap sp500HashMap = null;
	
	public WikipediaSP500CompanyList() {
		super();
		this.sp500List = new ArrayList();
		this.sp500HashMap = new HashMap();
	}
	
	
	
	
	@Override
	public String toString() {
		return null;
		/*
		return "YahooFinanceStockQuoteSummary [\n"
				+ "Key = " + key + ",\n"
				+ "Current Price =" + livePrice + ",\n" 
				+ "Previous Closing Price = " + previousClosingPrice + ",\n"
				+ "Opening Price = " + openingPrice + ",\n" 
				+ "Bid Offer Price = " + bidOffer + ",\n"
				+ "Bid Quantity = " + bidQuantity + ",\n"
				+ "Asking Price = " + askingPrice + ",\n"
				+ "Asking Quantity = " + askingQuantity + ",\n" 
				+ "Days Range Low Price = " + daysRangeLowPrice + ",\n" 
				+ "Days Range High Price = " + daysRangeHighPrice + ",\n" 
				+ "52 Week Range Low = " + fiftyTwoWeekRangeLowPrice + ",\n"
				+ "52 Week Range High = " + fiftyTwoWeekRangeHighPrice + ",\n" 
				+ "Volume = " + volume + ",\n"
				+ "Average Volume = " + avgVolume + ",\n" 
				+ "Market Cap = " + marketCap + ",\n"
				+ "Beta (%Y Monthly) = " + getBeta() + ",\n"
				+ "PE Ratio (TTM) = " + getPeRatioTtm() + ",\n"
				+ "EPS (TTM) = " + getEpsTtm() + ",\n"
				+ "Earnings Date = " + earningsDate + ",\n"
				+ "Earnings Date End = " + earningsDateEnd + ",\n"
				+ "Dividend = " + dividend + ",\n"
				+ "Dividend Yield = " + getDividendYield() + ",\n" 
				+ "Ex-Dividend Date = " + exDividendDate + ",\n"
				+ "1 Year Target Estimate = " + oneYearTargetEstimate + ",\n"
				+ "Calculated 1 Year Earnings Value = "+ calculateEarningPotentialValue() + ",\n"
				+ "Calculated 1 Year Earnings Percentage = " + calculateEarningPotentialPercentage() + "\n]";
		*/
	}
	
 
}