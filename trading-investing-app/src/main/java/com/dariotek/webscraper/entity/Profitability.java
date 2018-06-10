package com.dariotek.webscraper.entity;

import java.math.BigDecimal;

public class Profitability {

	private BigDecimal profitMargin;
	private BigDecimal operatingMargin;
	
	public BigDecimal getProfitMargin() {
		return profitMargin;
	}
	public void setProfitMargin(BigDecimal profitMargin) {
		this.profitMargin = profitMargin;
	}
	public BigDecimal getOperatingMargin() {
		return operatingMargin;
	}
	public void setOperatingMargin(BigDecimal operatingMargin) {
		this.operatingMargin = operatingMargin;
	}
	
	@Override
	public String toString() {
		return "Profitability [profitMargin=" + profitMargin + ", operatingMargin=" + operatingMargin + "]";
	}
	
}
