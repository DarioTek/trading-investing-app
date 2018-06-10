package com.dariotek.test;

import java.math.BigDecimal;
import java.util.HashMap;

import com.dariotek.entity.HistoricalStockPrice;
import com.dariotek.webscraper.YahooFinanceWebScraperUtils;

public class TestApp2 {

	public static void main(String[] args) {
		
		String string = "+0.08 (+0.06%)";
		String[] stringArray = string.split(" ");
		System.out.println(stringArray[0]);
		System.out.println(stringArray[1]);
		
		BigDecimal valueChange = new BigDecimal(Double.parseDouble(stringArray[0]));
		System.out.println(valueChange);
		
		System.out.println(stringArray[1].substring(1, stringArray[1].length()));
		
		String s = stringArray[1].substring(1, stringArray[1].length());
		s = s.substring(0, s.length()-1);
		System.out.println(s);
		
		BigDecimal percentChange = YahooFinanceWebScraperUtils.stringPercentToBigDecimal(s);
		System.out.println(percentChange);
		
		HashMap hm = new HashMap();
		hm.put("Value Change", valueChange);
		hm.put("Percent Change", percentChange);
		System.out.println("Value Change = " + (BigDecimal)hm.get("Value Change"));
		System.out.println("Percent Change = " +(BigDecimal)hm.get("Percent Change"));
		
	}

}
