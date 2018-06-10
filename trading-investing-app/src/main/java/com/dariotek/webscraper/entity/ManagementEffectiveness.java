package com.dariotek.webscraper.entity;

import java.math.BigDecimal;

public class ManagementEffectiveness {

	private BigDecimal returnOnAssets;
	private BigDecimal returnOnEquity;
	
	public BigDecimal getReturnOnAssets() {
		return returnOnAssets;
	}
	public void setReturnOnAssets(BigDecimal returnOnAssets) {
		this.returnOnAssets = returnOnAssets;
	}
	public BigDecimal getReturnOnEquity() {
		return returnOnEquity;
	}
	public void setReturnOnEquity(BigDecimal returnOnEquity) {
		this.returnOnEquity = returnOnEquity;
	}
	
	@Override
	public String toString() {
		return "ManagementEffectiveness [returnOnAssets=" + returnOnAssets + ", returnOnEquity=" + returnOnEquity + "]";
	}
	
}
