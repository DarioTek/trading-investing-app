package com.dariotek.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="trading_investing.historical_stock_prices")
public class HistoricalStockPrice {

	@Embeddable
	public static class Key implements Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Column(name="symbol")
		private String symbol;
		
		@Column(name="date")
		private Date date;

		public String getSymbol() {
			return symbol;
		}

		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}
		
		public String toString() {
			String key = symbol + date;
			return key;
		}

	}

	@EmbeddedId
	private Key key;
	
	@Column(name="open")
	private double open;
	
	@Column(name="close")
	private double close;
	
	@Column(name="low")
	private double low;
	
	@Column(name="high")
	private double high;
	
	@Column(name="adj_close")
	private double adjClose;
	
	@Column(name="volume")
	private double volume;
		
	@Column(name="d1_up_down")
	private int upDownD1;
	
	@Column(name="d5_up_down")
	private int upDownD5;
	
	@Column(name="d10_up_down")
	private int upDownD10;
	
	@Column(name="d15_up_down")
	private int upDownD15;
	
	@Column(name="d20_up_down")
	private int upDownD20;
	
	@Column(name="d25_up_down")
	private int upDownD25;
	
	@Column(name="d30_up_down")
	private int upDownD30;

	@Column(name="day_of_week")
	private int dayOfWeek;
	
	@Column(name="day_of_month")
	private int dayOfMonth;
	
	@Column(name="month")
	private int month;
	
	@Column(name="year")
	private int year;

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getAdjClose() {
		return adjClose;
	}

	public void setAdjClose(double adjClose) {
		this.adjClose = adjClose;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public int getUpDownD1() {
		return upDownD1;
	}

	public void setUpDownD1(int upDownD1) {
		this.upDownD1 = upDownD1;
	}

	public int getUpDownD5() {
		return upDownD5;
	}

	public void setUpDownD5(int upDownD5) {
		this.upDownD5 = upDownD5;
	}

	public int getUpDownD10() {
		return upDownD10;
	}

	public void setUpDownD10(int upDownD10) {
		this.upDownD10 = upDownD10;
	}

	public int getUpDownD15() {
		return upDownD15;
	}

	public void setUpDownD15(int upDownD15) {
		this.upDownD15 = upDownD15;
	}

	public int getUpDownD20() {
		return upDownD20;
	}

	public void setUpDownD20(int upDownD20) {
		this.upDownD20 = upDownD20;
	}

	public int getUpDownD25() {
		return upDownD25;
	}

	public void setUpDownD25(int upDownD25) {
		this.upDownD25 = upDownD25;
	}

	public int getUpDownD30() {
		return upDownD30;
	}

	public void setUpDownD30(int upDownD30) {
		this.upDownD30 = upDownD30;
	}

	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public int getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "HistoricalStockPrice [key=" + key + ", open=" + open + ", close=" + close + ", low=" + low + ", high="
				+ high + ", adjClose=" + adjClose + ", volume=" + volume + ", upDownD1=" + upDownD1 + ", upDownD5="
				+ upDownD5 + ", upDownD10=" + upDownD10 + ", upDownD15=" + upDownD15 + ", upDownD20=" + upDownD20
				+ ", upDownD25=" + upDownD25 + ", upDownD30=" + upDownD30 + ", dayOfWeek=" + dayOfWeek + ", dayOfMonth="
				+ dayOfMonth + ", month=" + month + ", year=" + year + "]";
	}

	public String toCSV() {
		List<Field> fieldsList = Arrays.asList(this.getClass().getDeclaredFields());
		StringBuffer sb = new StringBuffer();
		for (Field field: fieldsList) {
			System.out.println("field = " + field);
		}
		
		
		return null;
	}

}
