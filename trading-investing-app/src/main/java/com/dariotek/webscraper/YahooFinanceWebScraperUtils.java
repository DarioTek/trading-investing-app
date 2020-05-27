package com.dariotek.webscraper;

import org.apache.commons.validator.routines.UrlValidator;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Seth on 4/1/2017.
 * Enhanced by DarioTek
 */

public class YahooFinanceWebScraperUtils {

    private static final String[] schemes = {"http", "https"};
    private static final String dividendYieldRegex = "([+-]?\\d*\\.\\d+)(?![-+0-9\\.]).*?([+-]?\\d*\\.\\d+)(?![-+0-9\\.])";

    private static Logger logger = LoggerFactory.getLogger(YahooFinanceWebScraperUtils.class);

    public static Boolean isYahooUrlValid(String tickerSymbol) {
        UrlValidator urlValidator = new UrlValidator(schemes);
        return urlValidator.isValid("https://finance.yahoo.com/quote/" + tickerSymbol);
    }

    public static Boolean isYahooStatisticsUrlValid(String tickerSymbol) {
        UrlValidator urlValidator = new UrlValidator(schemes);
        return urlValidator.isValid("https://finance.yahoo.com/quote/" + tickerSymbol + "/key-statistics");
    }

    public static Double stringToDouble(String doubleStr) {
        //logger.info("Double Str: " + doubleStr);
        if (isStringNonApplicable(doubleStr)) {
            return null;
        }
        return Double.parseDouble(doubleStr.replace(",", "")); //remove comma on string values before converting String to Double
    }

    public static Double getStockPriceByQuantity(String stockByQuantityStr) {
        if (!isStockPriceAndQuantityNull(stockByQuantityStr)) {
            String[] stockByQuantityArray = stockByQuantityStr.split(" x ");
            //logger.info("Stock Price By Quantity: " + stockByQuantityArray[0]);
            return Double.parseDouble(stockByQuantityArray[0].replace(",", "")); //remove comma on string values before converting String to Double
        }
        return null;
    }

    public static Integer getStockQuantityByPrice(String stockByPriceStr) {
        if (!isStockPriceAndQuantityNull(stockByPriceStr)) {
            String[] stockByQuantityArray = stockByPriceStr.split(" x ");
            //logger.info("Stock Quantity By Price: " + stockByQuantityArray[1]);
            return Integer.parseInt(stockByQuantityArray[1]);
        }
        return null;
    }

    public static Double getStockStartingPrice(String stockRangeStr) {
        if (!isStockPriceAndQuantityNull(stockRangeStr)) {
            String[] stockRangeArray = stockRangeStr.split(" - ");
            //logger.info("Stock Starting Price: " + stockRangeArray[0]);
            return Double.parseDouble(stockRangeArray[0].replace(",","")); //remove comma on string values before converting String to Double
        }
        return null;
    }

    private static Boolean isStockPriceAndQuantityNull(String stockPriceAndQuantityStr) {
        return stockPriceAndQuantityStr.trim().equals("0.00 x");
    }

    public static Double getStockEndingPrice(String stockRangeStr) {
        String[] stockRangeArray = stockRangeStr.split(" - ");
        //logger.info("Stock Ending Price: " + stockRangeArray[1]);
        return Double.parseDouble(stockRangeArray[1].replace(",", "")); //remove comma on string values before converting String to Double
    }

    public static Integer removeCommasReturnInteger(String commaIntStr) {
        String numNoCommas = commaIntStr.replace(",", "");
        return Integer.parseInt(numNoCommas);
    }

    public static Double stringToDoubleWithChar(String marketCapStr) {    	
        //logger.info("Double String: " + marketCapStr);
        
        if (isStringNonApplicable(marketCapStr)) {
        	return null;
        }
        
        Integer startpos = 0;
        Integer endpos = marketCapStr.length() - 1;
        Double number = Double.parseDouble(marketCapStr.substring(startpos, endpos));
        //logger.info("Number Character Removed: " + number);
        //logger.info("number LongValue() = " + number.longValue());
        
        if (marketCapStr.contains("M")) {
            return number.doubleValue() * 1000000;
        } else if (marketCapStr.contains("B")) {
            return number.doubleValue() * 1000000000;
        } else if (marketCapStr.contains("T")) {
            return number.doubleValue() * 1000000000000L;
        } else {
            return number.doubleValue();
        }
    }
    
    
    public static Long stringToLong(String marketCapStr) {    	
        logger.info("Long String: " + marketCapStr);
        
        if (isStringNonApplicable(marketCapStr)) {
        	return null;
        }
        
        Integer startpos = 0;
        Integer endpos = marketCapStr.length() - 1;
        Double number = Double.parseDouble(marketCapStr.substring(startpos, endpos));
        //logger.info("Number Character Removed: " + number);
        //logger.info("number LongValue() = " + number.longValue());
        
        if (marketCapStr.contains("M")) {
            return number.longValue() * 1000000;
        } else if (marketCapStr.contains("B")) {
            return number.longValue() * 1000000000;
        } else if (marketCapStr.contains("T")) {
            return number.longValue() * 1000000000000L;
        } else {
            return number.longValue();
        }
    }

    public static BigDecimal stringToBigDecimal(String string) {
        logger.info("Big Decimal String: " + string);
        
        if (!isStringNonApplicable(string)) {
        	
        	if (string.length() > 1) {
                Integer startpos = 0;
                Integer endpos = string.length() - 1;
                Double doubleVar = Double.parseDouble(string.substring(startpos, endpos));
                BigDecimal number = BigDecimal.valueOf(doubleVar);
                //logger.info("doubleVar = " + doubleVar);
                //logger.info("number = " + number);
                
                if (string.contains("k")) {
                	return number.multiply(new BigDecimal(1000));
                } else if (string.contains("M")) {
                    return number.multiply(new BigDecimal(1000000));
                } else if (string.contains("B")) {
                    return number.multiply(new BigDecimal(1000000000));
                } else if (string.contains("T")) {
                    return number.multiply(new BigDecimal(1000000000000L));
                } else {
                    return number;
                }

        	}else {
        		return new BigDecimal(Double.parseDouble(string));
        	}
        	

        }
        
        return null;
        
    }
    
    public static BigDecimal stringPercentToBigDecimal(String string) {
        logger.info("Percent String: " + string);
        
        if (!isStringNonApplicable(string)) {
            Integer startpos = 0;
            Integer endpos = string.length() - 1;
            Double number = Double.parseDouble(string.substring(startpos, endpos));
            //logger.info("Number Character Removed: " + number);
            //logger.info("number Double Value() = " + number.doubleValue());
            
            if (string.contains("%")) {
            	//logger.info("return Double Value() = " + number.doubleValue() / 100L);
                return BigDecimal.valueOf(number.doubleValue()).divide(new BigDecimal(100));
            } else {
                return BigDecimal.valueOf(number.doubleValue());
            }

        }
        return null;
        
    }

    public static BigDecimal stringFractionToBigDecimal(String string) {
        //logger.info("Fraction String: " + string);
        
        if (!isStringNonApplicable(string)) {
        	String[] stringArray = string.split("/");
            
            //logger.info("stringArray[0] : " + stringArray[0]);
            //logger.info("stringArray[1] = " + stringArray[1]);    
 
            return BigDecimal.valueOf(Integer.parseInt(stringArray[0])).divide(new BigDecimal(Integer.parseInt(stringArray[1])));

        }
        return null;
        
    }


    public static Double stringPercentToDouble(String string) {
        //logger.info("Percent String: " + string);
        
        if (isStringNonApplicable(string)) {
            return null;
        }
        
        Integer startpos = 0;
        Integer endpos = string.length() - 1;
        Double number = Double.parseDouble(string.substring(startpos, endpos));
        //logger.info("Number Character Removed: " + number);
        //logger.info("number Double Value() = " + number.doubleValue());
        
        if (string.contains("%")) {
        	//logger.info("return Double Value() = " + number.doubleValue() / 100L);
            return number.doubleValue() / 100L;
        } else {
            return number.doubleValue();
        }
    }

    
    /*
     * MMM dd, yyyy format
     */
    public static Date stringToDateMMMDDYYYY(String dateStr) {
        if (!isStringNonApplicable(dateStr)) {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("MMM dd, yyyy");
            return formatter.parseDateTime(dateStr).toDate();
        }
        return null;
    }

    /*
     * yyyy-MM-dd format
     */
    public static Date stringToDateYYYYMMDD(String dateStr) {
        if (!isStringNonApplicable(dateStr)) {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
            return formatter.parseDateTime(dateStr).toDate();
        }
        return null;
    }

    public static Date getEarningsStartDate(String dateStr) {
        if (!isStringNonApplicable(dateStr)) {
            String[] dateArray = dateStr.split("-");
            //logger.info("Start Date Str: " + dateArray[0]);
            return stringToDateMMMDDYYYY(dateArray[0].trim());
        }
        return null;
    }

    public static Date getEarningsEndDate(String dateStr) {
        if (!isStringNonApplicable(dateStr)) {
            String[] dateArray = dateStr.split("-");
            //logger.info("End Date Str: " + dateArray[1]);
            return stringToDateMMMDDYYYY(dateArray[1].trim());
        }
        return null;
    }

    // Creating this method to return an array for using regular expressions like this can be memory intensive
    // and creating two separate  methods would be redundant
    public static String[] getDividendYieldArray(String dividendYieldStr) {
        if (!isStringNonApplicable(dividendYieldStr)) {
            String[] dividendYieldArray = null;
            Pattern pattern = Pattern.compile(dividendYieldRegex, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
            Matcher matcher = pattern.matcher(dividendYieldStr);
            if (matcher.find()) {
                dividendYieldArray = new String[]{matcher.group(1), matcher.group(2)};
            }
            return dividendYieldArray;
        }
        return null;
    }

    private static Boolean isStringNonApplicable(String naStr) {
        return naStr.trim().equals("N/A") || naStr.trim().equals("N/A (N/A)");
    }

    // TODO look into a better way of converting DateTime to Date - this was the only way that seemed to work
    public static Date dateTimeToDate(DateTime dateTime) {
        if (dateTime != null) {
            return new Date(String.valueOf(dateTime));
        }
        return null;
    }

    public static DateTime sqlDateToDateTime(String dateStr) {
        //logger.info("DateStr: " + dateStr);
        if (dateStr != null) {
            return DateTime.parse(dateStr, DateTimeFormat.forPattern("yyyy-MM-dd")).toDateTime();
        }
        return null;
    }

}