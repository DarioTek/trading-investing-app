package com.dariotek.webscraper.entity;

import java.util.Date;

import org.joda.time.DateTime;

public class FiscalYear {
	
	private Date fiscalYearEnd;
	private Date mostRecentQuarter;

	public FiscalYear() {		
	}
	
	public Date getFiscalYearEnd() {
		return fiscalYearEnd;
	}
	public void setFiscalYearEnd(Date fiscalYearEnd) {
		this.fiscalYearEnd = fiscalYearEnd;
	}
	public Date getMostRecentQuarter() {
		return mostRecentQuarter;
	}
	public void setMostRecentQuarter(Date mostRecentQuarter) {
		this.mostRecentQuarter = mostRecentQuarter;
	}

	@Override
	public String toString() {
		return "FiscalYear [fiscalYearEnd=" + fiscalYearEnd + ", mostRecentQuarter=" + mostRecentQuarter + "]";
	}
	
	
}
