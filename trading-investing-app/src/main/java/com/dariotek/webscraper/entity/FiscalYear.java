package com.dariotek.webscraper.entity;

import org.joda.time.DateTime;

public class FiscalYear {
	
	private DateTime fiscalYearEnd;
	private DateTime mostRecentQuarter;

	public FiscalYear() {		
	}
	
	public DateTime getFiscalYearEnd() {
		return fiscalYearEnd;
	}
	public void setFiscalYearEnd(DateTime fiscalYearEnd) {
		this.fiscalYearEnd = fiscalYearEnd;
	}
	public DateTime getMostRecentQuarter() {
		return mostRecentQuarter;
	}
	public void setMostRecentQuarter(DateTime mostRecentQuarter) {
		this.mostRecentQuarter = mostRecentQuarter;
	}

	@Override
	public String toString() {
		return "FiscalYear [fiscalYearEnd=" + fiscalYearEnd + ", mostRecentQuarter=" + mostRecentQuarter + "]";
	}
	
	
}
