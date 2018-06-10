package com.dariotek.webscraper.entity;

import java.math.BigDecimal;

public class ShareStatistics {

	private BigDecimal averageVolume3Months;
	private BigDecimal averageVolume10Days;
	private BigDecimal sharesOutstanding;
	private BigDecimal sharesFloat;
	private BigDecimal percentHeldByInsiders;
	private BigDecimal percentHeldByInstitutions;
	private BigDecimal sharesShort;
	private BigDecimal shortRatio;
	private BigDecimal shortPercentOfFloat;
	private BigDecimal sharesShortPriorMonth;
	
	public BigDecimal getAverageVolume3Months() {
		return averageVolume3Months;
	}
	public void setAverageVolume3Months(BigDecimal averageVolume3Months) {
		this.averageVolume3Months = averageVolume3Months;
	}
	public BigDecimal getAverageVolume10Days() {
		return averageVolume10Days;
	}
	public void setAverageVolume10Days(BigDecimal averageVolume10Days) {
		this.averageVolume10Days = averageVolume10Days;
	}
	public BigDecimal getSharesOutstanding() {
		return sharesOutstanding;
	}
	public void setSharesOutstanding(BigDecimal sharesOutstanding) {
		this.sharesOutstanding = sharesOutstanding;
	}
	public BigDecimal getSharesFloat() {
		return sharesFloat;
	}
	public void setSharesFloat(BigDecimal sharesFloat) {
		this.sharesFloat = sharesFloat;
	}
	public BigDecimal getPercentHeldByInsiders() {
		return percentHeldByInsiders;
	}
	public void setPercentHeldByInsiders(BigDecimal percentHeldByInsiders) {
		this.percentHeldByInsiders = percentHeldByInsiders;
	}
	public BigDecimal getPercentHeldByInstitutions() {
		return percentHeldByInstitutions;
	}
	public void setPercentHeldByInstitutions(BigDecimal percentHeldByInstitutions) {
		this.percentHeldByInstitutions = percentHeldByInstitutions;
	}
	public BigDecimal getSharesShort() {
		return sharesShort;
	}
	public void setSharesShort(BigDecimal sharesShort) {
		this.sharesShort = sharesShort;
	}
	public BigDecimal getShortRatio() {
		return shortRatio;
	}
	public void setShortRatio(BigDecimal shortRatio) {
		this.shortRatio = shortRatio;
	}
	public BigDecimal getShortPercentOfFloat() {
		return shortPercentOfFloat;
	}
	public void setShortPercentOfFloat(BigDecimal shortPercentOfFloat) {
		this.shortPercentOfFloat = shortPercentOfFloat;
	}
	public BigDecimal getSharesShortPriorMonth() {
		return sharesShortPriorMonth;
	}
	public void setSharesShortPriorMonth(BigDecimal sharesShortPriorMonth) {
		this.sharesShortPriorMonth = sharesShortPriorMonth;
	}
	
	@Override
	public String toString() {
		return "ShareStatistics [averageVolume3Months=" + averageVolume3Months + ", averageVolume10Days="
				+ averageVolume10Days + ", sharesOutstanding=" + sharesOutstanding + ", sharesFloat=" + sharesFloat
				+ ", percentHeldByInsiders=" + percentHeldByInsiders + ", percentHeldByInstitutions="
				+ percentHeldByInstitutions + ", sharesShort=" + sharesShort + ", shortRatio=" + shortRatio
				+ ", shortPercentOfFloat=" + shortPercentOfFloat + ", sharesShortPriorMonth=" + sharesShortPriorMonth
				+ "]";
	}
	
}
