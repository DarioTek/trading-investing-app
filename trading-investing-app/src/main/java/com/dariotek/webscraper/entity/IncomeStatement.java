package com.dariotek.webscraper.entity;

import java.math.BigDecimal;

public class IncomeStatement {

	private BigDecimal revenue;
	private BigDecimal revenuePerShare;
	private BigDecimal quarterlyRevenueGrowthYoy;
	private BigDecimal grossProfit;
	private BigDecimal ebitda;
	private BigDecimal netIncomeAviToCommon;
	private BigDecimal dillutedEPS;
	private BigDecimal quarterlyEarningsGrowthYoy;
	
	public BigDecimal getRevenue() {
		return revenue;
	}
	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
	}
	public BigDecimal getRevenuePerShare() {
		return revenuePerShare;
	}
	public void setRevenuePerShare(BigDecimal revenuePerShare) {
		this.revenuePerShare = revenuePerShare;
	}
	public BigDecimal getQuarterlyRevenueGrowthYoy() {
		return quarterlyRevenueGrowthYoy;
	}
	public void setQuarterlyRevenueGrowthYoy(BigDecimal quarterlyRevenueGrowthYoy) {
		this.quarterlyRevenueGrowthYoy = quarterlyRevenueGrowthYoy;
	}
	public BigDecimal getGrossProfit() {
		return grossProfit;
	}
	public void setGrossProfit(BigDecimal grossProfit) {
		this.grossProfit = grossProfit;
	}
	public BigDecimal getEbitda() {
		return ebitda;
	}
	public void setEbitda(BigDecimal ebitda) {
		this.ebitda = ebitda;
	}
	public BigDecimal getNetIncomeAviToCommon() {
		return netIncomeAviToCommon;
	}
	public void setNetIncomeAviToCommon(BigDecimal netIncomeAviToCommon) {
		this.netIncomeAviToCommon = netIncomeAviToCommon;
	}
	public BigDecimal getDillutedEPS() {
		return dillutedEPS;
	}
	public void setDillutedEPS(BigDecimal dillutedEPS) {
		this.dillutedEPS = dillutedEPS;
	}
	public BigDecimal getQuarterlyEarningsGrowthYoy() {
		return quarterlyEarningsGrowthYoy;
	}
	public void setQuarterlyEarningsGrowthYoy(BigDecimal quarterlyEarningsGrowthYoy) {
		this.quarterlyEarningsGrowthYoy = quarterlyEarningsGrowthYoy;
	}
	
	@Override
	public String toString() {
		return "IncomeStatement [revenue=" + revenue + ", revenuePerShare=" + revenuePerShare
				+ ", quarterlyRevenueGrowthYoy=" + quarterlyRevenueGrowthYoy + ", grossProfit=" + grossProfit
				+ ", ebitda=" + ebitda + ", netIncomeAviToCommon=" + netIncomeAviToCommon + ", dillutedEPS="
				+ dillutedEPS + ", quarterlyEarningsGrowthYoy=" + quarterlyEarningsGrowthYoy + "]";
	}
	
}
