package com.dariotek.webscraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dariotek.webscraper.entity.YahooFinanceStockQuoteStatistics;

import java.io.IOException;
import java.util.Date;

/**
 * This maps to URL = https://finance.yahoo.com/quote/[symbol]/key-statistics
 * This is the contents of the Summary tab
 */
public class YahooFinanceStockQuoteStatisticsScraper {

    private Logger logger = LoggerFactory.getLogger(YahooFinanceStockQuoteStatisticsScraper.class);
    private String cssQuery = "td[class=\"Fz(s) Fw(500) Ta(end)\"]";

    public YahooFinanceStockQuoteStatistics getQuoteStatistics(String tickerSymbol) {
        // Instantiating new instance of the Quote Statistics Class
        YahooFinanceStockQuoteStatistics quoteStatistics = new YahooFinanceStockQuoteStatistics();
        try {
        	
        	String url = "https://finance.yahoo.com/quote/" + tickerSymbol + "/key-statistics?p=" + tickerSymbol;
        	logger.info("url = " + url);
            Document doc = Jsoup.connect(url).get();
            quoteStatistics.setTickerSymbol(tickerSymbol);

            /*
             * Valuation Measures
             */
            quoteStatistics.getValueationMeasures().setMarketCap(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(0).text()));
            quoteStatistics.getValueationMeasures().setEnterpriseValue(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(1).text()));
            quoteStatistics.getValueationMeasures().setTrailingPE(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(2).text()));            
            quoteStatistics.getValueationMeasures().setForwardPE(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(3).text()));
            quoteStatistics.getValueationMeasures().setPegRatio5yearExpected(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(4).text()));
            quoteStatistics.getValueationMeasures().setPriceToSales(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(5).text()));
            quoteStatistics.getValueationMeasures().setPriceToBook(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(6).text()));
            quoteStatistics.getValueationMeasures().setEnterpriseValueRevenue(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(7).text()));
            quoteStatistics.getValueationMeasures().setEnterpriseValueEBITDA(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(8).text()));            
            
            /*
             * Financial Highlights
             */
            
            // Fiscal Year
            quoteStatistics.getFinancialHighlight().getFiscalYear().setFiscalYearEnd(YahooFinanceWebScraperUtils.stringToDateMMMDDYYYY(doc.select(cssQuery).get(9).text()));
            quoteStatistics.getFinancialHighlight().getFiscalYear().setMostRecentQuarter(YahooFinanceWebScraperUtils.stringToDateMMMDDYYYY(doc.select(cssQuery).get(10).text()));
            
            // Profitability
            quoteStatistics.getFinancialHighlight().getProfitability().setProfitMargin(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(11).text()));
            quoteStatistics.getFinancialHighlight().getProfitability().setOperatingMargin(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(12).text()));
            
            // Management Effectiveness
            quoteStatistics.getFinancialHighlight().getManagementEffectiveness().setReturnOnAssets(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(13).text()));
            quoteStatistics.getFinancialHighlight().getManagementEffectiveness().setReturnOnEquity(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(14).text()));
            
            // Income Statement
            quoteStatistics.getFinancialHighlight().getIncomeStatement().setRevenue(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(15).text()));
            quoteStatistics.getFinancialHighlight().getIncomeStatement().setRevenuePerShare(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(16).text()));
            quoteStatistics.getFinancialHighlight().getIncomeStatement().setQuarterlyRevenueGrowthYoy(YahooFinanceWebScraperUtils.stringPercentToBigDecimal(doc.select(cssQuery).get(17).text()));
            quoteStatistics.getFinancialHighlight().getIncomeStatement().setGrossProfit(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(18).text()));
            quoteStatistics.getFinancialHighlight().getIncomeStatement().setEbitda(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(19).text()));
            
            quoteStatistics.getFinancialHighlight().getIncomeStatement().setNetIncomeAviToCommon(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(20).text()));
            quoteStatistics.getFinancialHighlight().getIncomeStatement().setDillutedEPS(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(21).text()));
            quoteStatistics.getFinancialHighlight().getIncomeStatement().setQuarterlyEarningsGrowthYoy(YahooFinanceWebScraperUtils.stringPercentToBigDecimal(doc.select(cssQuery).get(22).text()));
            
            // Balance Sheet
            quoteStatistics.getFinancialHighlight().getBalanceSheet().setTotalCash(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(23).text()));
            quoteStatistics.getFinancialHighlight().getBalanceSheet().setTotalCashPerShare(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(24).text()));
            quoteStatistics.getFinancialHighlight().getBalanceSheet().setTotalDebt(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(25).text()));
            quoteStatistics.getFinancialHighlight().getBalanceSheet().setTotalDebtToEquity(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(26).text()));
            quoteStatistics.getFinancialHighlight().getBalanceSheet().setCurrentRatio(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(27).text()));
            quoteStatistics.getFinancialHighlight().getBalanceSheet().setBookValuePerShare(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(28).text()));
            
            // Cash Flow Statement
            quoteStatistics.getFinancialHighlight().getCashFlowStatement().setOperatingCashFlow(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(29).text()));
            quoteStatistics.getFinancialHighlight().getCashFlowStatement().setLeveredFreeCashFlow(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(30).text()));
            
            /*
             * Trading Information
             */
            
            // Stock Price History
            quoteStatistics.getTradingInformation().getStockPriceHistory().setBeta(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(31).text()));
            quoteStatistics.getTradingInformation().getStockPriceHistory().setChange52Week(YahooFinanceWebScraperUtils.stringPercentToBigDecimal(doc.select(cssQuery).get(32).text()));
            quoteStatistics.getTradingInformation().getStockPriceHistory().setSp500Change52Week(YahooFinanceWebScraperUtils.stringPercentToBigDecimal(doc.select(cssQuery).get(33).text()));
            quoteStatistics.getTradingInformation().getStockPriceHistory().setWeek52High(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(34).text()));
            quoteStatistics.getTradingInformation().getStockPriceHistory().setWeek52Low(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(35).text()));
            quoteStatistics.getTradingInformation().getStockPriceHistory().setDay50MovingAverage(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(36).text()));
            quoteStatistics.getTradingInformation().getStockPriceHistory().setDay200MovingAverage(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(37).text()));
            
            // Share Statistics
            quoteStatistics.getTradingInformation().getShareStatistics().setAverageVolume3Months(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(38).text()));
            quoteStatistics.getTradingInformation().getShareStatistics().setAverageVolume10Days(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(39).text()));
            quoteStatistics.getTradingInformation().getShareStatistics().setSharesOutstanding(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(40).text()));
            quoteStatistics.getTradingInformation().getShareStatistics().setSharesFloat(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(41).text()));
            quoteStatistics.getTradingInformation().getShareStatistics().setPercentHeldByInsiders(YahooFinanceWebScraperUtils.stringPercentToBigDecimal(doc.select(cssQuery).get(42).text()));
            quoteStatistics.getTradingInformation().getShareStatistics().setPercentHeldByInstitutions(YahooFinanceWebScraperUtils.stringPercentToBigDecimal(doc.select(cssQuery).get(43).text()));
            quoteStatistics.getTradingInformation().getShareStatistics().setSharesShort(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(44).text()));
            quoteStatistics.getTradingInformation().getShareStatistics().setShortRatio(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(45).text()));
            quoteStatistics.getTradingInformation().getShareStatistics().setShortPercentOfFloat(YahooFinanceWebScraperUtils.stringPercentToBigDecimal(doc.select(cssQuery).get(46).text()));
            quoteStatistics.getTradingInformation().getShareStatistics().setSharesShortPriorMonth(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(47).text()));
            
            // Dividends & Splits
            quoteStatistics.getTradingInformation().getDividendsAndSplits().setForwardAnnualDividendRate(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(48).text()));
            quoteStatistics.getTradingInformation().getDividendsAndSplits().setForwardAnnualDividendYieldPercent(YahooFinanceWebScraperUtils.stringPercentToBigDecimal(doc.select(cssQuery).get(49).text()));
            quoteStatistics.getTradingInformation().getDividendsAndSplits().setTrailingAnnualDividendRate(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(50).text()));
            quoteStatistics.getTradingInformation().getDividendsAndSplits().setTrailingAnnualDividendYieldPercent(YahooFinanceWebScraperUtils.stringPercentToBigDecimal(doc.select(cssQuery).get(51).text()));
            quoteStatistics.getTradingInformation().getDividendsAndSplits().setAverage5YearDividendYield(YahooFinanceWebScraperUtils.stringToBigDecimal(doc.select(cssQuery).get(52).text()));
            quoteStatistics.getTradingInformation().getDividendsAndSplits().setPayoutRatioPercent(YahooFinanceWebScraperUtils.stringPercentToBigDecimal(doc.select(cssQuery).get(53).text()));
            quoteStatistics.getTradingInformation().getDividendsAndSplits().setDividendDate(YahooFinanceWebScraperUtils.stringToDateMMMDDYYYY(doc.select(cssQuery).get(54).text()));
            quoteStatistics.getTradingInformation().getDividendsAndSplits().setExDividendDate(YahooFinanceWebScraperUtils.stringToDateMMMDDYYYY(doc.select(cssQuery).get(55).text()));            
            quoteStatistics.getTradingInformation().getDividendsAndSplits().setLastSplitFactor(YahooFinanceWebScraperUtils.stringFractionToBigDecimal(doc.select(cssQuery).get(56).text()));
            quoteStatistics.getTradingInformation().getDividendsAndSplits().setLastSplitDate(YahooFinanceWebScraperUtils.stringToDateMMMDDYYYY(doc.select(cssQuery).get(57).text()));
        }catch(Exception e) {
        	e.printStackTrace();
        }
        
        return quoteStatistics;
    }
}