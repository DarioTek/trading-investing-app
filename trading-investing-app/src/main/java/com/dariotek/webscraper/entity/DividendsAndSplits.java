package com.dariotek.webscraper.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.joda.time.DateTime;

public class DividendsAndSplits {
	
	private BigDecimal forwardAnnualDividendRate;
	private BigDecimal forwardAnnualDividendYieldPercent;
	private BigDecimal trailingAnnualDividendRate;
	private BigDecimal trailingAnnualDividendYieldPercent;
	private BigDecimal average5YearDividendYield;
	private BigDecimal payoutRatioPercent;
	private DateTime dividendDate;
	private DateTime exDividendDate;
	private BigDecimal lastSplitFactor;
	private DateTime lastSplitDate;
	
	public BigDecimal getForwardAnnualDividendRate() {
		return forwardAnnualDividendRate;
	}
	public void setForwardAnnualDividendRate(BigDecimal forwardAnnualDividendRate) {
		this.forwardAnnualDividendRate = forwardAnnualDividendRate;
	}
	public BigDecimal getForwardAnnualDividendYieldPercent() {
		return forwardAnnualDividendYieldPercent;
	}
	public void setForwardAnnualDividendYieldPercent(BigDecimal forwardAnnualDividendYieldPercent) {
		this.forwardAnnualDividendYieldPercent = forwardAnnualDividendYieldPercent;
	}
	public BigDecimal getTrailingAnnualDividendRate() {
		return trailingAnnualDividendRate;
	}
	public void setTrailingAnnualDividendRate(BigDecimal trailingAnnualDividendRate) {
		this.trailingAnnualDividendRate = trailingAnnualDividendRate;
	}
	public BigDecimal getTrailingAnnualDividendYieldPercent() {
		return trailingAnnualDividendYieldPercent;
	}
	public void setTrailingAnnualDividendYieldPercent(BigDecimal trailingAnnualDividendYieldPercent) {
		this.trailingAnnualDividendYieldPercent = trailingAnnualDividendYieldPercent;
	}
	public BigDecimal getAverage5YearDividendYield() {
		return average5YearDividendYield;
	}
	public void setAverage5YearDividendYield(BigDecimal average5YearDividendYield) {
		this.average5YearDividendYield = average5YearDividendYield;
	}
	public BigDecimal getPayoutRatioPercent() {
		return payoutRatioPercent;
	}
	public void setPayoutRatioPercent(BigDecimal payoutRatioPercent) {
		this.payoutRatioPercent = payoutRatioPercent;
	}
	public DateTime getDividendDate() {
		return dividendDate;
	}
	public void setDividendDate(DateTime dividendDate) {
		this.dividendDate = dividendDate;
	}
	public DateTime getExDividendDate() {
		return exDividendDate;
	}
	public void setExDividendDate(DateTime exDividendDate) {
		this.exDividendDate = exDividendDate;
	}
	public BigDecimal getLastSplitFactor() {
		return lastSplitFactor;
	}
	public void setLastSplitFactor(BigDecimal lastSplitFactor) {
		this.lastSplitFactor = lastSplitFactor;
	}
	public DateTime getLastSplitDate() {
		return lastSplitDate;
	}
	public void setLastSplitDate(DateTime lastSplitDate) {
		this.lastSplitDate = lastSplitDate;
	}
	
	@Override
	public String toString() {
		return "DividendsAndSplits [forwardAnnualDividendRate=" + forwardAnnualDividendRate
				+ ", forwardAnnualDividendYieldPercent=" + forwardAnnualDividendYieldPercent
				+ ", trailingAnnualDividendRate=" + trailingAnnualDividendRate + ", trailingAnnualDividendYieldPercent="
				+ trailingAnnualDividendYieldPercent + ", average5YearDividendYield=" + average5YearDividendYield
				+ ", payoutRatioPercent=" + payoutRatioPercent + ", dividendDate=" + dividendDate + ", exDividendDate="
				+ exDividendDate + ", lastSplitFactor=" + lastSplitFactor + ", lastSplitDate=" + lastSplitDate + "]";
	}
	
}
