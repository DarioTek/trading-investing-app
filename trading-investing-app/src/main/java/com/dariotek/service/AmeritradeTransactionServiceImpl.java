package com.dariotek.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dariotek.dao.AmeritradeTransactionDAO;
import com.dariotek.entity.AmeritradeTransaction;
import com.dariotek.util.CSVReaderUtility;

@Component
public class AmeritradeTransactionServiceImpl implements AmeritradeTransactionService {
	
	public AmeritradeTransactionServiceImpl() {
	
	}
		
	public AmeritradeTransactionServiceImpl(String filePath) {
		this.filePath = filePath;
	}
		
	@Autowired
	private CSVReaderUtility csvReaderUtility;
	
	@Autowired
	private AmeritradeTransactionDAO ameritradeTransactionDAO;
	
	private String filePath;
	
	/*
	 * (non-Javadoc)
	 * @see com.dariotek.service.AmeritradeTransactionService#loadAmeritradeTransactionsFromCSV()
	 * 
	 * This method will read the CSV file downloaded from Ameritrade and load the data in the DB.
	 */
	@Override
	@Transactional
	public int loadAmeritradeTransactionsFromCSV() {
		System.out.println("====> Starting loadAmeritradeTransactionsFromCSV()");
		
		int processedRecords = 0;
		int errorRecords = 0;
		
		for (int year = 2002; year <= 2018; year++) {

			String fileName = "transactions " + year + ".csv";
			String filePath = this.filePath;
			
			// read the data from the csv file
			csvReaderUtility.setFileName(fileName);
			csvReaderUtility.setFilePath(filePath);
			csvReaderUtility.setCsvLineItems(); // loads each line in an List<String>
			
			// load the data in the database using hibernate
			List<String> lineItems = csvReaderUtility.getCsvLineItems();
			
			
			for (int i=0; i < lineItems.size(); i++) {
				// skip first line and last line
				if ((i == 0)||(i == lineItems.size() - 1)) {
					continue;
				}
				
				// split the string using the "comma" as the delimiter
				String line = (String)lineItems.get(i);
				String[] csvLine = line.split(",", 13);
				
				AmeritradeTransaction ameritradeTransaction = new AmeritradeTransaction();
				
				// Set Date
				System.out.println("Date = " + csvLine[0]);
				
				Date date = null;
				try {
					String dateString = csvLine[0];
					date = new SimpleDateFormat("MM/dd/yyyy").parse(dateString);						
				}catch(Exception e) {
					e.printStackTrace();
				}
				ameritradeTransaction.setDate(date);
				
				// Set Transaction ID
				System.out.println("Transaction ID = " + csvLine[1]);
				ameritradeTransaction.setTransactionID(csvLine[1]);
				
				// Set Description
				System.out.println("Description = " + csvLine[2]);
				ameritradeTransaction.setDescription(csvLine[2]);
							
				// Set Quantity
				System.out.println("Quantity = " + csvLine[3]);			
				ameritradeTransaction.setQuantity(csvLine[3].length() == 0 ? 0 : (int)Double.parseDouble(csvLine[3]));
				
				// Set Symbol
				System.out.println("Symbol = " + csvLine[4]);
				ameritradeTransaction.setSymbol(csvLine[4]);
				
				// Set Price
				System.out.println("Price = " + csvLine[5]);
				ameritradeTransaction.setPrice(csvLine[5].length() == 0 ? 0 : Double.parseDouble(csvLine[5]));
				
				// Set Commission
				System.out.println("Commission = " + csvLine[6]);
				ameritradeTransaction.setCommission(csvLine[6].length() == 0 ? 0 : Double.parseDouble(csvLine[6]));
				
				// Set Amount
				System.out.println("Amount = " + csvLine[7]);
				ameritradeTransaction.setAmount(csvLine[7].length() == 0 ? 0 : Double.parseDouble(csvLine[7]));
				
				// Set Net Cash Balance - hard coded string = "---"
				System.out.println("Net Cash Balance = " + csvLine[8]);				
				ameritradeTransaction.setNetCashBalance(csvLine[8].length() == 0 ? 0 : csvLine[8].startsWith("---") ? 0 : Double.parseDouble(csvLine[8]));
				
				// Set Reg Fee
				System.out.println("Reg Fee = " + csvLine[9]);
				ameritradeTransaction.setRegFee(csvLine[9].length() == 0 ? 0 : Double.parseDouble(csvLine[9]));
				
				// Short Term RDM Fee
				System.out.println("Short Term RDM Fee = " + csvLine[10]);
				ameritradeTransaction.setShortTermRdmFee(csvLine[10].length() == 0 ? 0 : Double.parseDouble(csvLine[10]));
				
				// Set Fund Redemption Fee
				System.out.println("Fund Redemption Fee = " + csvLine[11]);
				ameritradeTransaction.setFundRedemptionFee(csvLine[11].length() == 0 ? 0 : Double.parseDouble(csvLine[11]));
				
				// Set Deferred Sales Charge
				System.out.println("Deferred Sales Charge = " + csvLine[12]);
				ameritradeTransaction.setDeferredSalesCharge(csvLine[12].length() == 0 ? 0 : Double.parseDouble(csvLine[12]));
				
				System.out.println("-------------------------------");
				
				try {
					ameritradeTransactionDAO.addAmeritradeTransaction(ameritradeTransaction);
				}catch(Exception e) {
					errorRecords++;
					System.out.println("====> ERROR #" +  errorRecords);
					e.printStackTrace();					
					System.out.println("====> ERROR");
					continue;
				}
				processedRecords++;
			}
			
		}
		
		// return the number of records processed
		return processedRecords;
	}

}
