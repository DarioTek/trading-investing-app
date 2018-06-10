package com.dariotek.webscraper.entity;

import java.math.BigDecimal;

public class StockPriceHistory {

	private BigDecimal beta;
	private BigDecimal change52Week;
	private BigDecimal sp500Change52Week;
	private BigDecimal week52High;
	private BigDecimal week52Low;
	private BigDecimal day50MovingAverage;
	private BigDecimal day200MovingAverage;
	
	public BigDecimal getBeta() {
		return beta;
	}
	public void setBeta(BigDecimal beta) {
		this.beta = beta;
	}
	public BigDecimal getChange52Week() {
		return change52Week;
	}
	public void setChange52Week(BigDecimal change52Week) {
		this.change52Week = change52Week;
	}
	public BigDecimal getSp500Change52Week() {
		return sp500Change52Week;
	}
	public void setSp500Change52Week(BigDecimal sp500Change52Week) {
		this.sp500Change52Week = sp500Change52Week;
	}
	public BigDecimal getWeek52High() {
		return week52High;
	}
	public void setWeek52High(BigDecimal week52High) {
		this.week52High = week52High;
	}
	public BigDecimal getWeek52Low() {
		return week52Low;
	}
	public void setWeek52Low(BigDecimal week52Low) {
		this.week52Low = week52Low;
	}
	public BigDecimal getDay50MovingAverage() {
		return day50MovingAverage;
	}
	public void setDay50MovingAverage(BigDecimal day50MovingAverage) {
		this.day50MovingAverage = day50MovingAverage;
	}
	public BigDecimal getDay200MovingAverage() {
		return day200MovingAverage;
	}
	public void setDay200MovingAverage(BigDecimal day200MovingAverage) {
		this.day200MovingAverage = day200MovingAverage;
	}
	
	@Override
	public String toString() {
		return "StockPriceHistory [beta=" + beta + ", change52Week=" + change52Week + ", sp500Change52Week="
				+ sp500Change52Week + ", week52High=" + week52High + ", week52Low=" + week52Low
				+ ", day50MovingAverage=" + day50MovingAverage + ", day200MovingAverage=" + day200MovingAverage + "]";
	}
	
}
