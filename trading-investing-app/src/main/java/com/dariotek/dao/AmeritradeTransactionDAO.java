package com.dariotek.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dariotek.entity.AmeritradeTransaction;


public interface AmeritradeTransactionDAO {

	public List<AmeritradeTransaction> getAmeritradeTransactions();
	
	public AmeritradeTransaction getAmeritradeTransaction(String id);
	
	// Add and Update operations can be merged together since Hibernate handles them internally
	public void addAmeritradeTransaction(AmeritradeTransaction ameritradeTransaction);
	
	public void deleteAmeritradeTransaction(String id);
	
}