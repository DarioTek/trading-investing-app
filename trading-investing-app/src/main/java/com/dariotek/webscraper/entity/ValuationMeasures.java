package com.dariotek.webscraper.entity;

import java.math.BigDecimal;

public class ValuationMeasures {

    private BigDecimal marketCap;
    private BigDecimal enterpriseValue;
    private BigDecimal trailingPE;
    private BigDecimal forwardPE;
    private BigDecimal pegRatio5yearExpected;
    private BigDecimal priceToSales;
    private BigDecimal priceToBook;
    private BigDecimal enterpriseValueRevenue;
    private BigDecimal enterpriseValueEBITDA;
    
	public BigDecimal getMarketCap() {
		return marketCap;
	}
	public void setMarketCap(BigDecimal marketCap) {
		this.marketCap = marketCap;
	}
	public BigDecimal getEnterpriseValue() {
		return enterpriseValue;
	}
	public void setEnterpriseValue(BigDecimal enterpriseValue) {
		this.enterpriseValue = enterpriseValue;
	}
	public BigDecimal getTrailingPE() {
		return trailingPE;
	}
	public void setTrailingPE(BigDecimal trailingPE) {
		this.trailingPE = trailingPE;
	}
	public BigDecimal getForwardPE() {
		return forwardPE;
	}
	public void setForwardPE(BigDecimal forwardPE) {
		this.forwardPE = forwardPE;
	}
	public BigDecimal getPegRatio5yearExpected() {
		return pegRatio5yearExpected;
	}
	public void setPegRatio5yearExpected(BigDecimal pegRatio5yearExpected) {
		this.pegRatio5yearExpected = pegRatio5yearExpected;
	}
	public BigDecimal getPriceToSales() {
		return priceToSales;
	}
	public void setPriceToSales(BigDecimal priceToSales) {
		this.priceToSales = priceToSales;
	}
	public BigDecimal getPriceToBook() {
		return priceToBook;
	}
	public void setPriceToBook(BigDecimal priceToBook) {
		this.priceToBook = priceToBook;
	}
	public BigDecimal getEnterpriseValueRevenue() {
		return enterpriseValueRevenue;
	}
	public void setEnterpriseValueRevenue(BigDecimal enterpriseValueRevenue) {
		this.enterpriseValueRevenue = enterpriseValueRevenue;
	}
	public BigDecimal getEnterpriseValueEBITDA() {
		return enterpriseValueEBITDA;
	}
	public void setEnterpriseValueEBITDA(BigDecimal enterpriseValueEBITDA) {
		this.enterpriseValueEBITDA = enterpriseValueEBITDA;
	}
	
	@Override
	public String toString() {
		return "ValuationMeasures [marketCap=" + marketCap + ", enterpriseValue=" + enterpriseValue + ", trailingPE="
				+ trailingPE + ", forwardPE=" + forwardPE + ", pegRatio5yearExpected=" + pegRatio5yearExpected
				+ ", priceToSales=" + priceToSales + ", priceToBook=" + priceToBook + ", enterpriseValueRevenue="
				+ enterpriseValueRevenue + ", enterpriseValueEBITDA=" + enterpriseValueEBITDA + "]";
	}
    
}
