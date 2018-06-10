package com.dariotek.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class YahooStatistics {
	String sharesOutstanding = "Shares Outstanding:";

	public YahooStatistics(String s){
		System.out.println("start YahooStatistics(" + s +")");
	    String keyStatisticsURL = ("https://finance.yahoo.com/q/ks?s="+s+"+Key+Statistics");
	
	    System.out.println("here :: " + keyStatisticsURL);
	    //Attempt 1       
	    try {
	    	System.out.println("here 1");
	        Document doc = Jsoup.connect(keyStatisticsURL).get();
	
	        System.out.println("here 2");
	        for (Element table : doc.select("table-qsp-stats Mt(10px)"))  {
	            for (Element row : table.select("tr"))  {
	                Elements tds = row.select("td");
	                for (Element td : tds.select(sharesOutstanding)) {
	                    System.out.println(td.ownText());
	                }
	            }
	        }
	    }catch (IOException ex)   {
	        ex.printStackTrace();
	    }
	
	    //Attempt 2
	    /*
	    try {
	    Document doc = Jsoup.connect(keyStatisticsURL).get();
	
	        for (Element table : doc.select("table.yfnc_datamodoutline1"))    {
	            for (Element row : table.select("tr"))   {
	                Elements tds = row.select("td");
	                for (int j = 0; j < tds.size() - 1; j++) {
	                    Element td = tds.get(j);
	                    if ((td.ownText()).equals(sharesOutstanding)) {
	                    System.out.println(tds.get(j+1).ownText());
	                    }
	                }
	            }
	        }
	    }
	    catch(IOException ex)   {
	        ex.printStackTrace();
	    }
	    */
	}
	
	public static void main(String[] args) {
		YahooStatistics appl = new YahooStatistics("AAPL");
	}
}