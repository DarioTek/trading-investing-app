package com.dariotek.webscraper.yahoofinance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dariotek.webscraper.entity.DividendYieldComparator;
import com.dariotek.webscraper.entity.PercentageEarningPotentialComparator;
import com.dariotek.webscraper.entity.PriceComparator;
import com.dariotek.webscraper.entity.YahooFinanceStockQuoteSummary;
import com.dariotek.webscraper.wikipedia.WikipediaSP500CompanyListScraper;
import com.dariotek.webscraper.wikipedia.WikipediaSP500ComponentStock;

public class YahooFinanceWebScraperApp {

    private static Logger logger = LoggerFactory.getLogger(YahooFinanceWebScraperApp.class);

    //Main methods
    public static void main(String[] args) {

    	WikipediaSP500CompanyListScraper wikipedia = new WikipediaSP500CompanyListScraper();
    	List<WikipediaSP500ComponentStock> sp500List = wikipedia.getWikipediaSP500CompanyList();
    	    	
    	String[] stockList = new String[sp500List.size()];
    	for(int i = 0; i < sp500List.size(); i++) {
    		stockList[i] = sp500List.get(i).getSymbol();
    	}    	
    	    	
    	//String[] stockList = {"DOW","XOM","IBM","VZ","CVX","PFE","MMM","WBA","CSCO","KO","FB","AAPL","NFLX","GOOGL","GS","JPM","C","FB","MSFT","DIS","MA","V","TSLA","HD","WDAY"};
    	//String[] indexList= {"QQQ","IWM","DIA","SPY"}; //TODO: Code currently does not scrape properly for indexes 
    	
    	
    	List<YahooFinanceStockQuoteSummary> scrappedObjects = new ArrayList<>();


    	//long startTimeToScrapeYahooFinance = System.nanoTime();
    	long startTimeToScrapeYahooFinance = System.currentTimeMillis();

		Connection conn = null;
        try { 
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //String dbURL = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVER = DEDICATED)(SERVICE_NAME=XEPDB1)))";
            String dbURL = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=localhost)(PORT=1521))(CONNECT_DATA=(SERVER = DEDICATED)(SID=xe)))";
            System.out.println("jdbcurl=" + dbURL);
            String strUserID = "trading_investing";
            String strPassword = "oracle";
            conn=DriverManager.getConnection(dbURL,strUserID,strPassword);

            //Date date = Calendar.getInstance().getTime();
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");  
            DateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yyyy");
            String recordDate = dateFormat.format(date);            
            
        	// Yahoo Finance Summary
        	for (String stock: stockList) {    		
        		if (stock != null && !stock.contains(".") && YahooFinanceWebScraperUtils.isYahooUrlValid(stock)) {
        			try {
    	    	        YahooFinanceStockQuoteSummaryScraper getQuoteSummary = new YahooFinanceStockQuoteSummaryScraper();    	        
    	    	        YahooFinanceStockQuoteSummary yahooFinanceStockQuoteSummary = getQuoteSummary.getQuoteSummary(stock);
    	    	        
    	                Statement st = conn.createStatement();
    	                //System.out.println("BEFORE");
    	                String exDividendDate = yahooFinanceStockQuoteSummary.getExDividendDate() != "N/A" ? "TO_DATE('" + yahooFinanceStockQuoteSummary.getExDividendDate() + "','MM/DD/YYYY')" : "null";
    	                //System.out.println("BEFORE EARNINGS DATE END");
    	                
    	                String earningsDate = yahooFinanceStockQuoteSummary.getEarningsDate() != null ? "TO_DATE('" + dateFormat2.format(yahooFinanceStockQuoteSummary.getEarningsDate()) + "','MM/DD/YYYY')" : "null";
    	                
    	                String earningsDateEnd = yahooFinanceStockQuoteSummary.getEarningsDateEnd() != null ? "TO_DATE('" + dateFormat2.format(yahooFinanceStockQuoteSummary.getEarningsDateEnd()) + "','MM/DD/YYYY')" : "null";
    	                //System.out.println("BEFORE INSERT");
    	                String insertStatement = "INSERT INTO YAHOO_FINANCE_QUOTE_SUMMARY VALUES (TO_DATE('"+ recordDate + "','MM/DD/YYYY HH:MI:SS'), "
    	    	                		+ "'" + yahooFinanceStockQuoteSummary.getTickerSymbol() + "', "
    	    	                		+ yahooFinanceStockQuoteSummary.getLivePrice() + ", "
    	    	                		+ yahooFinanceStockQuoteSummary.getPreviousClosingPrice() + ", "
    	    	    	                + yahooFinanceStockQuoteSummary.getOpeningPrice() + ", "    	                		
    	    	    	                + yahooFinanceStockQuoteSummary.getBidOffer() + ", "
    	    	    	                + yahooFinanceStockQuoteSummary.getBidQuantity() + ", "
    	    	    	                + yahooFinanceStockQuoteSummary.getAskingPrice() + ", "
    	    	    	                + yahooFinanceStockQuoteSummary.getAskingQuantity() + ", "
    	    	    	                + yahooFinanceStockQuoteSummary.getDaysRangeLowPrice() + ", "
    	    	    	                + yahooFinanceStockQuoteSummary.getDaysRangeHighPrice() + ", "
    	    	    	                + yahooFinanceStockQuoteSummary.getFiftyTwoWeekRangeLow() + ", "
    	    	    	                + yahooFinanceStockQuoteSummary.getFiftyTwoWeekRangeHigh() + ", "
    	    	    	                + yahooFinanceStockQuoteSummary.getVolume() + ", "
    	    	    	                + yahooFinanceStockQuoteSummary.getAvgVolume() + ", "
    	    	    	                + yahooFinanceStockQuoteSummary.getMarketCap() + ", "
    	    	    	                + yahooFinanceStockQuoteSummary.getBeta() + ", "
    	    	    	                + yahooFinanceStockQuoteSummary.getPeRatioTtm() + ", "
    	    	    	                + yahooFinanceStockQuoteSummary.getEpsTtm() + ", "
    	    	    	                + earningsDate + ", "
    	    	    	                + earningsDateEnd + ", "
    	    	    	                + yahooFinanceStockQuoteSummary.getDividend() + ", "
    	    	    	                + yahooFinanceStockQuoteSummary.getDividendYield() + ", "
    	    	    	                + exDividendDate + ", "
    	    	    	                + yahooFinanceStockQuoteSummary.getOneYearTargetEstimate() + ")";
    	                
    	                //System.out.println("insertStatement = " + insertStatement);
    	                st.executeUpdate(insertStatement);
    	                st.close(); // Closes Statement Connection
    	                		    	    	        
    	    	        scrappedObjects.add(yahooFinanceStockQuoteSummary);
        			}catch(Exception e) {
        				e.printStackTrace();
        			}
        		}
        	}
            
            conn.close(); // Closes Database Connection
        } catch (Exception e) { 
            System.err.println("Got an exception! "); 
            System.err.println(e.getMessage()); 
        } 
        
    	//long endTimeToScrapeYahooFinance = System.nanoTime();
    	//logger.info((endTimeToScrapeYahooFinance - startTimeToScrapeYahooFinance)/1000000 + " milliseconds to scrape " + stockList.length + " stock information from Yahoo Finance.");
    	long endTimeToScrapeYahooFinance = System.currentTimeMillis();
    	
    	/*
    	 * SORT THE LIST BASED ON SPECIFIC COMPARATORS
    	 */
    	long startTimeToSort = System.currentTimeMillis();
    	//Collections.sort(scrappedObjects); // Sort ArrayList using Stock Ticker using the compareTo() of the Comparable interface
    	//Collections.sort(scrappedObjects, new PriceComparator());
    	Collections.sort(scrappedObjects, new PercentageEarningPotentialComparator());
    	//Collections.sort(scrappedObjects, new DividendYieldComparator());
    	long endTimeToSort = System.currentTimeMillis();
    	
    	/*
    	for(int i=0; i <= scrappedObjects.size()-1; i++) {
    		YahooFinanceStockQuoteSummary yahooFinanceStockQuoteSummary = (YahooFinanceStockQuoteSummary)scrappedObjects.get(i);
    		logger.info(yahooFinanceStockQuoteSummary.toString());
    	}
    	*/
    	
    	logger.info((endTimeToScrapeYahooFinance - startTimeToScrapeYahooFinance) + " milliseconds to scrape " + scrappedObjects.size() + " stock information from Yahoo Finance.");
    	logger.info((endTimeToSort - startTimeToSort) + " milliseconds to sort.");
    	
    }
}
