package com.dariotek.webscraper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="trading_investing.stock_quote_summary")
public class YahooFinanceStockQuoteSummary implements Comparable{

	@Column(name="ticker_symbol")
	private String tickerSymbol;
	
	@Column(name="live_price")	
    private Double livePrice;
	
	@Column(name="previous_closing_price")
    private Double previousClosingPrice;
	
	@Column(name="opening_price")
    private Double openingPrice;
	
	@Column(name="bid_offer")
    private Double bidOffer;
	
	@Column(name="bid_quantity")
    private Integer bidQuantity;
	
	@Column(name="asking_price")
    private Double askingPrice;
	
	@Column(name="asking_quantity")
    private Integer askingQuantity;
	
	@Column(name="days_range_low_price")
	private Double daysRangeLowPrice;
	
	@Column(name="days_range_high_price")
    private Double daysRangeHighPrice;
	
	@Column(name="fifty_two_range_low_price")
    private Double fiftyTwoWeekRangeLowPrice;
	
	@Column(name="fifty_two_range_high_price")
    private Double fiftyTwoWeekRangeHighPrice;
	
	@Column(name="volume")
    private Integer volume;
	
	@Column(name="average_volume")
    private Integer avgVolume;
	
	@Column(name="market_cap")
    private BigDecimal marketCap;
	
	// Beta (5Y Monthly)
	@Column(name="beta")
    private Double beta;
	
	// PE Ration (TTM)
	@Column(name="pe_ratio_ttm")
    private Double peRatioTtm;
	
	// EPS (TTM)
	@Column(name="eps_ttm")
    private Double epsTtm;
	
	@Column(name="earnings_date")
    private Date earningsDate;
	
	@Column(name="earnings_date_end")
    private Date earningsDateEnd;
	
	@Column(name="dividend")
    private Double dividend;
	
	@Column(name="dividend_yield")
    private Double dividendYield;
	
	@Column(name="ex_dividend_date")
    private Date exDividendDate;
	
	@Column(name="one_year_targe_estimate")
    private Double oneYearTargetEstimate;
    
	@Column(name="potential_earning")
    private Double potentialEarning;

	@Column(name="potential_earning_percent")
    private Double potentialEarningPercent;
	
    public YahooFinanceStockQuoteSummary() {
        super();
    }
    
	@Embeddable
	public static class Key implements Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Column(name="symbol")
	    private String tickerSymbol;
		
		@Column(name="scrape_date")
	    private Date dateTimeScraped;    

	    public String getTickerSymbol() {
			return tickerSymbol;
		}


		public void setTickerSymbol(String tickerSymbol) {
			this.tickerSymbol = tickerSymbol;
		}

		public Date getDateTimeScraped() {
			return dateTimeScraped;
		}

		public void setDateTimeScraped(Date dateTimeScraped) {
			this.dateTimeScraped = dateTimeScraped;
		}
		
		public String toString() {
			String key = "[" + tickerSymbol + "|" + dateTimeScraped + "]";
			return key;
		}

	}

	@EmbeddedId
	private Key key;
	
	
	
	public Key getKey() {
		return key;
	}


	public void setKey(Key key) {
		this.key = key;
	}


	public Double getLivePrice() {		
		return livePrice != null ? livePrice : new Double(0);
	}


	public void setLivePrice(Double livePrice) {
		this.livePrice = livePrice;
	}

	public Double getPreviousClosingPrice() {
		return previousClosingPrice;
	}


	public void setPreviousClosingPrice(Double previousClosingPrice) {
		this.previousClosingPrice = previousClosingPrice;
	}


	public Double getOpeningPrice() {
		return openingPrice;
	}


	public void setOpeningPrice(Double openingPrice) {
		this.openingPrice = openingPrice;
	}


	public Double getBidOffer() {
		return bidOffer;
	}


	public void setBidOffer(Double bidOffer) {
		this.bidOffer = bidOffer;
	}


	public Integer getBidQuantity() {
		return bidQuantity;
	}


	public void setBidQuantity(Integer bidQuantity) {
		this.bidQuantity = bidQuantity;
	}


	public Double getAskingPrice() {
		return askingPrice;
	}


	public void setAskingPrice(Double askingPrice) {
		this.askingPrice = askingPrice;
	}


	public Integer getAskingQuantity() {
		return askingQuantity;
	}


	public void setAskingQuantity(Integer askingQuantity) {
		this.askingQuantity = askingQuantity;
	}


	public Double getDaysRangeLowPrice() {
		return daysRangeLowPrice;
	}


	public void setDaysRangeLowPrice(Double daysRangeStart) {
		this.daysRangeLowPrice = daysRangeStart;
	}


	public Double getDaysRangeHighPrice() {
		return daysRangeHighPrice;
	}


	public void setDaysRangeHighPrice(Double daysRangeEnd) {
		this.daysRangeHighPrice = daysRangeEnd;
	}


	public Double getFiftyTwoWeekRangeLow() {
		return fiftyTwoWeekRangeLowPrice;
	}


	public void setFiftyTwoWeekRangeLow(Double fiftyWeekRangeStart) {
		this.fiftyTwoWeekRangeLowPrice = fiftyWeekRangeStart;
	}


	public Double getFiftyTwoWeekRangeHigh() {
		return fiftyTwoWeekRangeHighPrice;
	}


	public void setFiftyTwoWeekRangeHigh(Double fiftyWeekRangeEnd) {
		this.fiftyTwoWeekRangeHighPrice = fiftyWeekRangeEnd;
	}


	public Integer getVolume() {
		return volume;
	}


	public void setVolume(Integer volume) {
		this.volume = volume;
	}


	public Integer getAvgVolume() {
		return avgVolume;
	}


	public void setAvgVolume(Integer avgVolume) {
		this.avgVolume = avgVolume;
	}


	public BigDecimal getMarketCap() {
		return marketCap;
	}


	public void setMarketCap(BigDecimal marketCap) {
		this.marketCap = marketCap;
	}


	public Double getPeRatioTtm() {
		return peRatioTtm;
	}


	public void setPeRatioTtm(Double peRatioTtm) {
		this.peRatioTtm = peRatioTtm;
	}


	public Date getEarningsDate() {
		return earningsDate;
	}


	public void setEarningsDate(Date earningsDate) {
		this.earningsDate = earningsDate;
	}


	public Date getEarningsDateEnd() {
		return earningsDateEnd;
	}


	public void setEarningsDateEnd(Date earningsDateEnd) {
		this.earningsDateEnd = earningsDateEnd;
	}


	public Double getDividend() {
		return dividend;
	}


	public void setDividend(Double dividend) {
		this.dividend = dividend;
	}


	public Double getDividendYield() {
		return dividendYield == null ? new Double(0) : dividendYield;
	}


	public void setDividendYield(Double yield) {
		this.dividendYield = yield;
	}


	public String getExDividendDate() {
		if (exDividendDate == null) {
			return "N/A";
		}else{
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			return dateFormat.format(exDividendDate);
		}		
	}


	public void setExDividendDate(Date exDividendDate) {
		this.exDividendDate = exDividendDate;
	}


	public Double getOneYearTargetEstimate() {
		return oneYearTargetEstimate;
	}


	public void setOneYearTargetEstimate(Double oneYearTargetEstimate) {
		this.oneYearTargetEstimate = oneYearTargetEstimate;
	}
	
	public Double getPotentialEarning() {
		return potentialEarning;
	}


	public void setPotentialEarning(Double potentialEarning) {
		this.potentialEarning = potentialEarning;
	}


	public Double getPotentialEarningPercent() {
		return potentialEarningPercent;
	}


	public void setPotentialEarningPercent(Double potentialEarningPercent) {
		this.potentialEarningPercent = potentialEarningPercent;
	}

	public Double getBeta() {
		return beta;
	}


	public void setBeta(Double beta) {
		this.beta = beta;
	}


	public Double getEpsTtm() {
		return epsTtm;
	}


	public void setEpsTtm(Double epsTtm) {
		this.epsTtm = epsTtm;
	}


	public String getTickerSymbol() {
		return tickerSymbol;
	}


	public void setTickerSymbol(String tickerSymbol) {
		this.tickerSymbol = tickerSymbol;
	}


	@Override
	public String toString() {
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
	}
	
    /*
     * https://dzone.com/articles/how-to-sort-objects-in-java
     */	
	@Override
	public int compareTo(Object o) {
		return this.getTickerSymbol().compareTo(((YahooFinanceStockQuoteSummary) o).getTickerSymbol());
	}

	public Double calculateEarningPotentialValue() {		
		return (getOneYearTargetEstimate() != null && getLivePrice() != null) ? getOneYearTargetEstimate() - getLivePrice() : new Double(0);
	}

	public Double calculateEarningPotentialPercentage() {
		return (getOneYearTargetEstimate() != null && getLivePrice() != null) ? ((getOneYearTargetEstimate() - getLivePrice()) / getLivePrice()) * 100 : new Double(0);
	}

	/*
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof YahooFinanceStockQuoteSummary)) return false;

        YahooFinanceStockQuoteSummary that = (YahooFinanceStockQuoteSummary) o;

        if (getTickerSymbol() != null ? !getTickerSymbol().equals(that.getTickerSymbol()) : that.getTickerSymbol() != null)
            return false;
        if (getPreviousClosingPrice() != null ? !getPreviousClosingPrice().equals(that.getPreviousClosingPrice()) : that.getPreviousClosingPrice() != null)
            return false;
        if (getOpeningPrice() != null ? !getOpeningPrice().equals(that.getOpeningPrice()) : that.getOpeningPrice() != null)
            return false;
        if (getBidOffer() != null ? !getBidOffer().equals(that.getBidOffer()) : that.getBidOffer() != null)
            return false;
        if (getBidQuantity() != null ? !getBidQuantity().equals(that.getBidQuantity()) : that.getBidQuantity() != null)
            return false;
        if (getAskingPrice() != null ? !getAskingPrice().equals(that.getAskingPrice()) : that.getAskingPrice() != null)
            return false;
        if (getAskingQuantity() != null ? !getAskingQuantity().equals(that.getAskingQuantity()) : that.getAskingQuantity() != null)
            return false;
        if (getDaysRangeStart() != null ? !getDaysRangeStart().equals(that.getDaysRangeStart()) : that.getDaysRangeStart() != null)
            return false;
        if (getDaysRangeEnd() != null ? !getDaysRangeEnd().equals(that.getDaysRangeEnd()) : that.getDaysRangeEnd() != null)
            return false;
        if (getFiftyTwoWeekRangeStart() != null ? !getFiftyTwoWeekRangeStart().equals(that.getFiftyTwoWeekRangeStart()) : that.getFiftyTwoWeekRangeStart() != null)
            return false;
        if (getFiftyTwoWeekRangeEnd() != null ? !getFiftyTwoWeekRangeEnd().equals(that.getFiftyTwoWeekRangeEnd()) : that.getFiftyTwoWeekRangeEnd() != null)
            return false;
        if (getVolume() != null ? !getVolume().equals(that.getVolume()) : that.getVolume() != null) return false;
        if (getAvgVolume() != null ? !getAvgVolume().equals(that.getAvgVolume()) : that.getAvgVolume() != null)
            return false;
        if (getMarketCap() != null ? !getMarketCap().equals(that.getMarketCap()) : that.getMarketCap() != null)
            return false;
        if (getBeta() != null ? !getBeta().equals(that.getBeta()) : that.getBeta() != null) return false;
        if (getPeRatioTtm() != null ? !getPeRatioTtm().equals(that.getPeRatioTtm()) : that.getPeRatioTtm() != null)
            return false;
        if (getEpsTtm() != null ? !getEpsTtm().equals(that.getEpsTtm()) : that.getEpsTtm() != null) return false;
        if (getEarningsDateStart() != null ? !getEarningsDateStart().equals(that.getEarningsDateStart()) : that.getEarningsDateStart() != null)
            return false;
        if (getEarningsDateEnd() != null ? !getEarningsDateEnd().equals(that.getEarningsDateEnd()) : that.getEarningsDateEnd() != null)
            return false;
        if (getDividend() != null ? !getDividend().equals(that.getDividend()) : that.getDividend() != null)
            return false;
        if (getYield() != null ? !getYield().equals(that.getYield()) : that.getYield() != null) return false;
        if (getExDividendDate() != null ? !getExDividendDate().equals(that.getExDividendDate()) : that.getExDividendDate() != null)
            return false;
        if (getFirstYearEstimate() != null ? !getFirstYearEstimate().equals(that.getFirstYearEstimate()) : that.getFirstYearEstimate() != null)
            return false;
        //return getDateEntered() != null ? getDateEntered().equals(that.getDateEntered()) : that.getDateEntered() == null;
    }
    */

	/*
    @Override
    public int hashCode() {
        int result = getTickerSymbol() != null ? getTickerSymbol().hashCode() : 0;
        result = 31 * result + (getPreviousClosingPrice() != null ? getPreviousClosingPrice().hashCode() : 0);
        result = 31 * result + (getOpeningPrice() != null ? getOpeningPrice().hashCode() : 0);
        result = 31 * result + (getBidOffer() != null ? getBidOffer().hashCode() : 0);
        result = 31 * result + (getBidQuantity() != null ? getBidQuantity().hashCode() : 0);
        result = 31 * result + (getAskingPrice() != null ? getAskingPrice().hashCode() : 0);
        result = 31 * result + (getAskingQuantity() != null ? getAskingQuantity().hashCode() : 0);
        result = 31 * result + (getDaysRangeStart() != null ? getDaysRangeStart().hashCode() : 0);
        result = 31 * result + (getDaysRangeEnd() != null ? getDaysRangeEnd().hashCode() : 0);
        result = 31 * result + (getFiftyTwoWeekRangeStart() != null ? getFiftyTwoWeekRangeStart().hashCode() : 0);
        result = 31 * result + (getFiftyTwoWeekRangeEnd() != null ? getFiftyTwoWeekRangeEnd().hashCode() : 0);
        result = 31 * result + (getVolume() != null ? getVolume().hashCode() : 0);
        result = 31 * result + (getAvgVolume() != null ? getAvgVolume().hashCode() : 0);
        result = 31 * result + (getMarketCap() != null ? getMarketCap().hashCode() : 0);
        result = 31 * result + (getBeta() != null ? getBeta().hashCode() : 0);
        result = 31 * result + (getPeRatioTtm() != null ? getPeRatioTtm().hashCode() : 0);
        result = 31 * result + (getEpsTtm() != null ? getEpsTtm().hashCode() : 0);
        result = 31 * result + (getEarningsDateStart() != null ? getEarningsDateStart().hashCode() : 0);
        result = 31 * result + (getEarningsDateEnd() != null ? getEarningsDateEnd().hashCode() : 0);
        result = 31 * result + (getDividend() != null ? getDividend().hashCode() : 0);
        result = 31 * result + (getYield() != null ? getYield().hashCode() : 0);
        result = 31 * result + (getExDividendDate() != null ? getExDividendDate().hashCode() : 0);
        result = 31 * result + (getFirstYearEstimate() != null ? getFirstYearEstimate().hashCode() : 0);
        result = 31 * result + (getDateEntered() != null ? getDateEntered().hashCode() : 0);
        return result;
    }
    */
}