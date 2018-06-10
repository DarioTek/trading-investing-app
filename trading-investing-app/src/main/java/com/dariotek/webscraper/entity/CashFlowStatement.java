package com.dariotek.webscraper.entity;

import java.math.BigDecimal;

public class CashFlowStatement {

	private BigDecimal operatingCashFlow;
	private BigDecimal leveredFreeCashFlow;
	
	public BigDecimal getOperatingCashFlow() {
		return operatingCashFlow;
	}
	public void setOperatingCashFlow(BigDecimal operatingCashFlow) {
		this.operatingCashFlow = operatingCashFlow;
	}
	public BigDecimal getLeveredFreeCashFlow() {
		return leveredFreeCashFlow;
	}
	public void setLeveredFreeCashFlow(BigDecimal leveredFreeCashFlow) {
		this.leveredFreeCashFlow = leveredFreeCashFlow;
	}
	
	@Override
	public String toString() {
		return "CashFlowStatement [operatingCashFlow=" + operatingCashFlow + ", leveredFreeCashFlow="
				+ leveredFreeCashFlow + "]";
	}
	
	
}