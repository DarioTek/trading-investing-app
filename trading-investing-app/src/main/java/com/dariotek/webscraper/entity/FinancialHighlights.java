package com.dariotek.webscraper.entity;

public class FinancialHighlights {

	private FiscalYear fiscalYear;
	private Profitability profitability;
	private ManagementEffectiveness managementEffectiveness;
	private IncomeStatement incomeStatement;
	private BalanceSheet balanceSheet;
	private CashFlowStatement cashFlowStatement;
	
	public FinancialHighlights() {
		this.fiscalYear = new FiscalYear();
		this.profitability = new Profitability();
		this.managementEffectiveness = new ManagementEffectiveness();
		this.incomeStatement = new IncomeStatement();
		this.balanceSheet = new BalanceSheet();
		this.cashFlowStatement = new CashFlowStatement();
	}
	
	public FiscalYear getFiscalYear() {
		return fiscalYear;
	}
	public void setFiscalYear(FiscalYear fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
	public Profitability getProfitability() {
		return profitability;
	}
	public void setProfitability(Profitability profitability) {
		this.profitability = profitability;
	}
	public ManagementEffectiveness getManagementEffectiveness() {
		return managementEffectiveness;
	}
	public void setManagementEffectiveness(ManagementEffectiveness managementEffectiveness) {
		this.managementEffectiveness = managementEffectiveness;
	}
	public IncomeStatement getIncomeStatement() {
		return incomeStatement;
	}
	public void setIncomeStatement(IncomeStatement incomeStatement) {
		this.incomeStatement = incomeStatement;
	}
	public BalanceSheet getBalanceSheet() {
		return balanceSheet;
	}
	public void setBalanceSheet(BalanceSheet balanceSheet) {
		this.balanceSheet = balanceSheet;
	}
	public CashFlowStatement getCashFlowStatement() {
		return cashFlowStatement;
	}
	public void setCashFlowStatement(CashFlowStatement cashFlowStatement) {
		this.cashFlowStatement = cashFlowStatement;
	}
	@Override
	public String toString() {
		return "FinancialHighlights [fiscalYear=" + fiscalYear + ", profitability=" + profitability
				+ ", managementEffectiveness=" + managementEffectiveness + ", incomeStatement=" + incomeStatement
				+ ", balanceSheet=" + balanceSheet + ", cashFlowStatement=" + cashFlowStatement + "]";
	}
	
	
}
