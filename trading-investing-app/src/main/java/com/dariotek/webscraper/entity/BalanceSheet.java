package com.dariotek.webscraper.entity;

import java.math.BigDecimal;

public class BalanceSheet {

	private BigDecimal totalCash;
	private BigDecimal totalCashPerShare;
	private BigDecimal totalDebt;
	private BigDecimal totalDebtToEquity;
	private BigDecimal currentRatio;
	private BigDecimal bookValuePerShare;
	public BigDecimal getTotalCash() {
		return totalCash;
	}
	public void setTotalCash(BigDecimal totalCash) {
		this.totalCash = totalCash;
	}
	public BigDecimal getTotalCashPerShare() {
		return totalCashPerShare;
	}
	public void setTotalCashPerShare(BigDecimal totalCashPerShare) {
		this.totalCashPerShare = totalCashPerShare;
	}
	public BigDecimal getTotalDebt() {
		return totalDebt;
	}
	public void setTotalDebt(BigDecimal totalDebt) {
		this.totalDebt = totalDebt;
	}
	public BigDecimal getTotalDebtToEquity() {
		return totalDebtToEquity;
	}
	public void setTotalDebtToEquity(BigDecimal totalDebtToEquity) {
		this.totalDebtToEquity = totalDebtToEquity;
	}
	public BigDecimal getCurrentRatio() {
		return currentRatio;
	}
	public void setCurrentRatio(BigDecimal currentRatio) {
		this.currentRatio = currentRatio;
	}
	public BigDecimal getBookValuePerShare() {
		return bookValuePerShare;
	}
	public void setBookValuePerShare(BigDecimal bookValuePerShare) {
		this.bookValuePerShare = bookValuePerShare;
	}
	@Override
	public String toString() {
		return "BalanceSheet [totalCash=" + totalCash + ", totalCashPerShare=" + totalCashPerShare + ", totalDebt="
				+ totalDebt + ", totalDebtToEquity=" + totalDebtToEquity + ", currentRatio=" + currentRatio
				+ ", bookValuePerShare=" + bookValuePerShare + "]";
	}
	
}
