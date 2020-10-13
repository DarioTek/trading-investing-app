package com.dariotek.webscraper.ameritrade;

import org.joda.time.DateTime;
//import com.yahoofinance.webscrape.beans.QuoteSummary;
//import com.yahoofinance.webscrape.utils.WebScrapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dariotek.dao.YahooFinanceStockQuoteSummaryDAO;
import com.dariotek.entity.HistoricalStockPrice;
import com.dariotek.webscraper.entity.YahooFinanceStockQuoteSummary;

import java.io.IOException;
import java.time.Duration;
import java.util.Date;

/**
 * This maps to URL = https://finance.yahoo.com/quote/[symbol]
 * This is the contents of the Summary tab
 */
@Component
public class AmeritradeScraper {

    private Logger logger = LoggerFactory.getLogger(AmeritradeScraper.class);
    
    public AmeritradeScraper() {
    	super();
    }
        
    public void connectToAmeritrade() {
        try {
        	String url = "https://www.tdameritrade.com/home.page";
        	logger.info("url = " + url);
            Document doc = Jsoup.connect(url).get();
            
            logger.debug(doc.toString());
            
           
        } catch (IOException e) {
            logger.error("An error occurred while trying to retrieve the information from Yahoo Finance: \n" + e);
        }
    }
    
    public void loginToAmeritrade(String userID, String loginPassword, String q1, String q2, String q3, String q4) {
    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\dmont\\Selenium\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://www.tdameritrade.com/home.page");
            
            WebElement userid = driver.findElement(By.id("userid"));
            WebElement password = driver.findElement(By.id("password"));            
            //WebElement loginButton = driver.findElement(By.tagName("button"));
            //WebElement loginButton = driver.findElement(By.className("main-header-login-submit main-header-login-label btn btn-green-solid"));
            //WebElement loginButton = driver.findElement(By.linkText("main-header-login-submit main-header-login-label btn btn-green-solid"));
            WebElement loginForm = driver.findElement(By.id("form-login"));
            
            
            userid.sendKeys(userID);
            password.sendKeys(loginPassword);
            loginForm.submit();

            String url = driver.getCurrentUrl();
            System.out.println("URL = " + url);
            
            driver.get("https://invest.ameritrade.com/grid/p/login");
            
            //WebElement secretAnswerToSecurityQuestion = driver.findElement(By.id("Aa4e8d1ed-0d9d-11eb-bcde-005056b47d65challengeAnswer"));
            //WebElement secretAnswerToSecurityQuestion = driver.findElement(By.className("dijitReset dijitInputInner"));
            WebElement secretAnswerToSecurityQuestion = driver.findElement(By.name("challengeAnswer"));
            
            //WebElement continueButton = driver.findElement(By.id("Aa4e8d1ed-0d9d-11eb-bcde-005056b47d65securityChallenge_label"));
            WebElement securityQuestion = driver.findElement(By.className("securityP"));
            //WebElement securityQuestionForm = driver.findElement(By.id("A43560b1e-0da1-11eb-bcde-005056b47d65securityChallengeForm"));
            
            WebElement secretAnswerToSecurityQuestionForm = driver.findElement(By.tagName("form"));
            
            String birthCityQuestion = "Question: In what city were you born? (Enter full name of city only.)";
            String marriedCityQuestion = "Question: In what city were you married? (Enter full name of city only.)";
            String highSchoolQuestion = "Question: What was the name of your junior high school? (Enter only 'Dell' for Dell Junior High School.)";
            String highSchoolCityQuestion = "Question: In what city was your high school? (Enter full name of city only.)";

            String text = securityQuestion.getText();
            System.out.println("text = " + text);            
            
            if (securityQuestion.getText().equals(birthCityQuestion)){
            	secretAnswerToSecurityQuestion.sendKeys(q1);
            	secretAnswerToSecurityQuestionForm.submit();
            }
            
            if (securityQuestion.getText().equals(marriedCityQuestion)){
            	secretAnswerToSecurityQuestion.sendKeys(q2);
            	secretAnswerToSecurityQuestionForm.submit();
            }

            if (securityQuestion.getText().equals(highSchoolQuestion)){
            	secretAnswerToSecurityQuestion.sendKeys(q3);
            	secretAnswerToSecurityQuestionForm.submit();
            }

            if (securityQuestion.getText().equals(highSchoolCityQuestion)){
            	secretAnswerToSecurityQuestion.sendKeys(q4);
            	secretAnswerToSecurityQuestionForm.submit();
            }
            
            url = driver.getCurrentUrl();
            System.out.println("URL = " + url);
            
            
        } finally {
            driver.quit();
        }    	
    	
    }
    
    public static void main(String[] args) {
    	AmeritradeScraper as = new AmeritradeScraper();
    	//as.connectToAmeritrade();
    	as.loginToAmeritrade(args[0], args[1], args[2], args[3], args[4], args[5]);
    }
}