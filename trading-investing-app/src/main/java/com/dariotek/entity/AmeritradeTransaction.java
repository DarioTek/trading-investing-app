package com.dariotek.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="trading_investing.ameritrade_transactions")
public class AmeritradeTransaction {

	public AmeritradeTransaction() {		
	}
	
	@Column(name="date")
	private Date date;
	
	@Id
	@Column(name="transaction_id")
	private String transactionID;
	
	@Column(name="description")
	private String description;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="symbol")
	private String symbol;
	
	@Column(name="price")
	private double price;
	
	@Column(name="commission")
	private double commission;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="net_cash_balance")
	private double netCashBalance;
	
	@Column(name="reg_fee")
	private double regFee;
	
	@Column(name="short_term_rdm_fee")
	private double shortTermRdmFee;
	
	@Column(name="fund_redemption_fee")
	private double fundRedemptionFee;
	
	@Column(name="deferred_sales_charge")
	private double deferredSalesCharge;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getNetCashBalance() {
		return netCashBalance;
	}

	public void setNetCashBalance(double netCashBalance) {
		this.netCashBalance = netCashBalance;
	}

	public double getRegFee() {
		return regFee;
	}

	public void setRegFee(double regFee) {
		this.regFee = regFee;
	}

	public double getShortTermRdmFee() {
		return shortTermRdmFee;
	}

	public void setShortTermRdmFee(double shortTermRdmFee) {
		this.shortTermRdmFee = shortTermRdmFee;
	}

	public double getFundRedemptionFee() {
		return fundRedemptionFee;
	}

	public void setFundRedemptionFee(double fundRedemptionFee) {
		this.fundRedemptionFee = fundRedemptionFee;
	}

	public double getDeferredSalesCharge() {
		return deferredSalesCharge;
	}

	public void setDeferredSalesCharge(double deferredSalesCharge) {
		this.deferredSalesCharge = deferredSalesCharge;
	}

	@Override
	public String toString() {
		return "AmeritradeTransaction [date=" + date + ", transactionID=" + transactionID + ", description="
				+ description + ", quantity=" + quantity + ", symbol=" + symbol + ", price=" + price + ", commission="
				+ commission + ", amount=" + amount + ", netCashBalance=" + netCashBalance + ", regFee=" + regFee
				+ ", shortTermRdmFee=" + shortTermRdmFee + ", fundRedemptionFee=" + fundRedemptionFee
				+ ", deferredSalesCharge=" + deferredSalesCharge + "]";
	}
	
}
