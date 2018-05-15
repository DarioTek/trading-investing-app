package com.dariotek.service;

import org.springframework.stereotype.Component;

@Component
public interface AmeritradeTransactionService {

	public int loadAmeritradeTransactionsFromCSV();
	
}
