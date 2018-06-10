package com.dariotek.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dariotek.dao.HistoricalStockPricesDAO;
import com.dariotek.entity.HistoricalStockPrice;
import com.dariotek.util.CSVReaderUtility;

@Component
public class HistoricalStockPriceServiceImpl implements HistoricalStockPriceService {

	public HistoricalStockPriceServiceImpl() {		
	}

	public HistoricalStockPriceServiceImpl(String filePath) {
		this.filePath = filePath;
	}

	private String filePath;
	
	@Autowired
	private CSVReaderUtility csvReaderUtility;
	
	@Autowired
	private HistoricalStockPricesDAO historicalStockPricesDAO;

	@Override
	public int loadHistoricalStockPricesFromYahooFinanceCSV() {
		// Initialize processed records counter
		int processedRecords = 0;
		ZoneId defaultZoneId = ZoneId.systemDefault();
		
		// read the data from Yahoo Finance CSV file
		//String[] historicalStockPrices = {"C", "JPM", "V", "MSFT", "VZ"};
		String[] historicalStockPrices = {"VZ"};
		String filePath = this.filePath;
		
		// load the data in the database
		for(String stockSymbol: historicalStockPrices) {
			
			System.out.println("====> BEGIN LOAD for " + stockSymbol);
			
			String fileName = stockSymbol + ".csv";
			
			csvReaderUtility.setFileName(fileName);
			csvReaderUtility.setFilePath(filePath);
			csvReaderUtility.setCsvLineItems(); // loads each line in an List<String>
			
			List<String> lineItems = csvReaderUtility.getCsvLineItems();
			
			for (int i = 0; i < lineItems.size(); i++) {
				//skip the header / first line
				if (i == 0) continue;
				
				// split the string using the "comma" as the delimiter
				String line = (String)lineItems.get(i);
				String[] csvLine = line.split(",", 7);
				
				HistoricalStockPrice historicalStockPrice = new HistoricalStockPrice();
				// Instantiate static inner class (key)
				HistoricalStockPrice.Key key = new HistoricalStockPrice.Key();
				historicalStockPrice.setKey(key);
				
				// Set Stock Symbol
				System.out.println("Symbol = " + stockSymbol);
				historicalStockPrice.getKey().setSymbol(stockSymbol);
				
				// Set Date
				System.out.println("Date = " + csvLine[0]);
				
				Date date = null;
				try {
					String dateString = csvLine[0];
					date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);						
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				historicalStockPrice.getKey().setDate(date);
				
				// Set Open
				historicalStockPrice.setOpen(csvLine[1].length() == 0 ?  0 : Double.parseDouble(csvLine[1]));
				
				// Set High
				historicalStockPrice.setHigh(csvLine[2].length() == 0 ?  0 : Double.parseDouble(csvLine[2]));

				// Set Low
				historicalStockPrice.setLow(csvLine[3].length() == 0 ?  0 : Double.parseDouble(csvLine[3]));
				
				// Set Close
				historicalStockPrice.setClose(csvLine[4].length() == 0 ?  0 : Double.parseDouble(csvLine[4]));
				
				// Set Adjusted Close
				historicalStockPrice.setAdjClose(csvLine[5].length() == 0 ?  0 : Double.parseDouble(csvLine[5]));
				
				// Set Volume
				historicalStockPrice.setVolume(csvLine[6].length() == 0 ?  0 : Double.parseDouble(csvLine[6]));

				// Set Date Dimension
				Date historicalStockPriceDate = historicalStockPrice.getKey().getDate();
				Instant historicalStockPriceInstant = historicalStockPriceDate.toInstant();
				LocalDate historicalStockPriceLocalDate = historicalStockPriceInstant.atZone(defaultZoneId).toLocalDate();
				
				historicalStockPrice.setDayOfWeek(historicalStockPriceLocalDate.getDayOfWeek().getValue());
				historicalStockPrice.setDayOfMonth(historicalStockPriceLocalDate.getDayOfMonth());
				historicalStockPrice.setMonth(historicalStockPriceLocalDate.getMonthValue());
				historicalStockPrice.setYear(historicalStockPriceLocalDate.getYear());
				
				// Setup Up and Down Fields
				historicalStockPrice.setUpDown(historicalStockPrice.getClose() - historicalStockPrice.getOpen());
				historicalStockPrice.setUpDownDirection(historicalStockPrice.getUpDown() <= 0 ? 0 : 1);
				
				try {
					System.out.println("historicalStockPrice = " + historicalStockPrice);
					//historicalStockPricesDAO.addHistoricalStockPrices(historicalStockPrice);
					historicalStockPricesDAO.saveOrUpdateHistoricalStockPrices(historicalStockPrice);
				}catch(org.hibernate.exception.ConstraintViolationException e) {
					System.out.println("DUPLICATE RECORD: " + historicalStockPrice);
					continue;
				}
				processedRecords++;
				
			}
			
		}
		
		// Return number of records loaded
		return processedRecords;
	}

	@Override
	public int populateUpAndDownFields() {
		
		int counter = 0;
		System.out.println("=====> start populateUpAndDownFields()");
		try {
			//Date date =new SimpleDateFormat("yyyy/MM/dd").parse("2018/03/01");
			//HistoricalStockPrice historicalStockPrice = historicalStockPricesDAO.getHistoricalStockPrice("C", date);
			
			//System.out.println("historicalStockPrice = " + historicalStockPrice);
			List<String> stockSymbols = historicalStockPricesDAO.getListOfStockSymbols();
			
			for (String symbol: stockSymbols) {
				List<HistoricalStockPrice> historicalStockPrices = historicalStockPricesDAO.getAllHistoricalStockPrices(symbol);
				
				for (int i = 0; i < historicalStockPrices.size(); i++) {
					
					HistoricalStockPrice currentRecord = (HistoricalStockPrice)historicalStockPrices.get(i);
					
					currentRecord.setUpDown(currentRecord.getClose() - currentRecord.getOpen());
					currentRecord.setUpDownDirection(currentRecord.getUpDown() <= 0 ? 0 : 1);
					
					historicalStockPricesDAO.updateHistoricalStockPrices(currentRecord);
					counter++;
				}

			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		// Query per symbol in reverse chronological order
		
		// determine day of week
		System.out.println("=====> finish populateUpAndDownFields()");
		return counter;
	}

	@Override
	public int populateDateDimensions() {
		
		// Note: This functionality is available starting with Java 8
		// Get system default timezone from OS
		ZoneId defaultZoneId = ZoneId.systemDefault();
		System.out.println("System Default TimeZone : " + defaultZoneId);

		// toString() append +8 automatically.
		Date date = new Date();
		System.out.println("date : " + date);

		// 1. Convert Date -> Instant
		Instant instant = date.toInstant();
		System.out.println("instant : " + instant); // Zone : UTC+0

		// 2. Instant + system default time zone + toLocalDate() = LocalDate
		LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
		System.out.println("localDate : " + localDate);

		// 3. Instant + system default time zone + toLocalDateTime() = LocalDateTime
		LocalDateTime localDateTime = instant.atZone(defaultZoneId).toLocalDateTime();
		System.out.println("localDateTime : " + localDateTime);
		System.out.println("localDateTime.getMonthValue() : " + localDateTime.getMonthValue());
		System.out.println("localDateTime.getYear() : " + localDateTime.getYear());

		// 4. Instant + system default time zone = ZonedDateTime
		ZonedDateTime zonedDateTime = instant.atZone(defaultZoneId);
		System.out.println("zonedDateTime : " + zonedDateTime);

		DayOfWeek dayOfWeek = localDate.getDayOfWeek();
		System.out.println("Day Of Week = " + dayOfWeek.getValue());
		
		List<String> stockSymbols = historicalStockPricesDAO.getListOfStockSymbols();
		
		int counter = 0;		
		for (String symbol: stockSymbols) {
			List<HistoricalStockPrice> historicalStockPrices = historicalStockPricesDAO.getAllHistoricalStockPrices(symbol);

			for (HistoricalStockPrice historicalStockPrice: historicalStockPrices) {
				Date historicalStockPriceDate = historicalStockPrice.getKey().getDate();
				Instant historicalStockPriceInstant = historicalStockPriceDate.toInstant();
				LocalDate historicalStockPriceLocalDate = historicalStockPriceInstant.atZone(defaultZoneId).toLocalDate();
				
				historicalStockPrice.setDayOfWeek(historicalStockPriceLocalDate.getDayOfWeek().getValue());
				historicalStockPrice.setDayOfMonth(historicalStockPriceLocalDate.getDayOfMonth());
				historicalStockPrice.setMonth(historicalStockPriceLocalDate.getMonthValue());
				historicalStockPrice.setYear(historicalStockPriceLocalDate.getYear());
				
				// Perhaps look at day of year as well? 365 to 366 day in a year
				// Determin the top day of year that the stocks are usually up
				
				historicalStockPricesDAO.updateHistoricalStockPrices(historicalStockPrice);
				counter++;
			}		
			
		}
		
		return counter;
	}

	public void test() {
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = new SimpleDateFormat("MM/dd/yyyy").parse("03/27/2018");
			endDate = new SimpleDateFormat("MM/dd/yyyy").parse("04/27/2018");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<HistoricalStockPrice> historicalStockPrices = historicalStockPricesDAO.getAllHistoricalStockPrices("C");
		
		for (HistoricalStockPrice historicalStockPrice: historicalStockPrices) {
			System.out.println(historicalStockPrice);
		}
		
		List<String> stockSymbols = historicalStockPricesDAO.getListOfStockSymbols();
		for (String symbol: stockSymbols) {
			System.out.println("Symbol = " + symbol);
		}
		
	}

	@Override
	public int createCSVForAnalytics() {
		List<String> stockSymbols = historicalStockPricesDAO.getListOfStockSymbols();
		for (String symbol: stockSymbols) {
			List<HistoricalStockPrice> historicalStockPrices = historicalStockPricesDAO.getAllHistoricalStockPrices(symbol);
			for(HistoricalStockPrice historicalStockPrice:historicalStockPrices) {
				//historicalStockPrice.get
			}
		}
		
		return 0;
	}
	
}
