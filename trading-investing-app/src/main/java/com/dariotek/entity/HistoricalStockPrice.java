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
		
		@Column(name="txn_date")
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
		
	@Column(name="day_of_week")
	private int dayOfWeek;
	
	@Column(name="day_of_month")
	private int dayOfMonth;
	
	@Column(name="month")
	private int month;
	
	@Column(name="year")
	private int year;

	@Column(name="up_down")
	private double upDown;
	
	@Column(name="up_down_direction")
	private int upDownDirection;

	// TODO: Added on 2/27/2018
	/*
	 * 1. Add 3rd Friday of the month (monthly expiration)
	 * 
	 */
	@Column(name="up_down_45days")
	private double upDown45Days;
	
	@Column(name="up_down_direction_45days")
	private int upDownDirection45Days;
	
	@Column(name="up_down_60days")
	private double upDown60Days;
	
	@Column(name="up_down_direction_60days")
	private int upDownDirection60Days;
	
	
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

	public double getUpDown() {
		return upDown;
	}

	public void setUpDown(double upDdown) {
		this.upDown = upDdown;
	}

	public int getUpDownDirection() {
		return upDownDirection;
	}

	public void setUpDownDirection(int upDownDirection) {
		this.upDownDirection = upDownDirection;
	}

	@Override
	public String toString() {
		return "HistoricalStockPrice [key=" + key + ", open=" + open + ", close=" + close + ", low=" + low + ", high="
				+ high + ", adjClose=" + adjClose + ", volume=" + volume + ", dayOfWeek=" + dayOfWeek + ", dayOfMonth="
				+ dayOfMonth + ", month=" + month + ", year=" + year + ", upDdown=" + upDown + ", upDownDirection="
				+ upDownDirection + "]";
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
