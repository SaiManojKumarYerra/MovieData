package com.interview.methods;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.interview.driver.Movies;

public class AppIndependentMethods extends Movies {
	
	
	/***********************************************
	 * Method Name		: jsClickObject()
	 *
	 ************************************************/
	public boolean jsClickObject(WebDriver oDriver, WebElement objEle)
	{
		JavascriptExecutor js = null;
		try {
			js = (JavascriptExecutor) oDriver;
		    js.executeScript("arguments[0].click();", objEle);
		    System.out.println("==> WebElement with Locator :"+Locator(objEle)+ " clicked successfully");
		    return true;
		}catch(Exception e)
		{
			System.out.println("Exception in jsClickObject method : "+e);
			return false;
		}
	}
	
	/***********************************************
	 * Method Name		: setObject()
	 *
	 ************************************************/
	public boolean setObject(WebDriver oDriver, WebElement objEle, String strValue)
	{
		try {
			if(objEle != null) {
				objEle.sendKeys(strValue);
				System.out.println("==> Entered "+strValue+" into Element with Locator "+Locator(objEle));
				return true;
			}else {
				return false;
			}
		}catch(Exception e)
		{
			System.out.println("Exception in SetObject method : "+e);
			return false;
		}
		
	}
	
	/***********************************************
	 * Method Name		: clickEnter()
	 *
	 ************************************************/
	public boolean clickEnter(WebDriver oDriver, WebElement objEle) {
		try {
			if(objEle!=null) {
				objEle.sendKeys(Keys.ENTER);
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			System.out.println("Exception in clickEnter method : "+e);
			return false;
		}
	}
	/***********************************************
	 * Method Name		: clearAndSetObject()
	 *
	 ************************************************/
	public boolean clearAndSetObject(WebDriver oDriver, WebElement objEle, String strValue)
	{

		try {
			if(objEle != null) {
				objEle.clear();
				objEle.sendKeys(strValue);
				System.out.println("==> Cleared & Entered "+strValue+" into Element with Locator "+Locator(objEle));
				return true;
			}else {
				return false;
			}
				
		}catch(Exception e)
		{
			System.out.println("Exception in clearAndSetObject method : "+e);
			return false;
		}
	}
	
	/***********************************************
	 * Method Name		: compareValues()
	 *
	 ************************************************/
	public boolean compareValues(WebDriver oDriver, String actual, String expected)
	{
		try {
			if(actual.equalsIgnoreCase(expected)) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e)
		{
			System.out.println("Exception in compareValues method : "+e);
			return false;
		}
	}
	
	
	/***********************************************
	 * Method Name		: verifyElementExist()
	 *
	 ************************************************/
	public boolean verifyElementExist(WebDriver oDriver, WebElement objEle)
	{
		try {
			if(objEle.isDisplayed() ) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e)
		{
			System.out.println("Exception in verifyElementExist method : "+e);
			return false;
		}
	}
		
	/***********************************************
	 * Method Name		: launchBrowser()
	 *
	 ************************************************/
	public WebDriver launchBrowser(String browserType)
	{
		WebDriver oDriver = null;
		try {
			switch(browserType.toLowerCase()) {
				case "chrome":
					System.setProperty("webdriver.chrome.driver", ".\\Library\\drivers\\chromedriver.exe");
					oDriver = new ChromeDriver();
					break;
				case "firefox":
					System.setProperty("webdriver.gecko.driver", ".\\Library\\drivers\\geckodriver.exe");
					oDriver = new FirefoxDriver();
					break;
				default:
			}
			
			if(oDriver!=null) {
				oDriver.manage().window().maximize();
				oDriver.manage().deleteAllCookies();
				
				oDriver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
				oDriver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

				return oDriver;
			}else {
				return null;
			}
		}catch(Exception e)
		{
			System.out.println("Exception in launchBrowser method : "+e);
			return null;
		}
	}
		
	/******************************************
	 * Method Name		: closeBrowser()
	 * 
	 *******************************************/
	public boolean closeBrowser(WebDriver oDriver)
	{
		try {
			oDriver.quit();
			oDriver = null;
			return true;
		}catch(Exception e)
		{
			System.out.println("Exception in closeBrowser method : "+e);
			return false;
		}
	}

	/********************************************************
	 * Method Name		: waitForElement()
	 * Purpose			:
	 ********************************************************/
	public boolean waitForElement(WebDriver oDriver, WebElement objEle, String strWaitReason, int timeout)
	{
		WebDriverWait oWait = null;
		try {
			oWait = new WebDriverWait(oDriver, timeout);
			
			switch(strWaitReason.toLowerCase())
			{
				case "clickable":
					oWait.until(ExpectedConditions.elementToBeClickable(objEle));
					break;
				case "visible":
					oWait.until(ExpectedConditions.visibilityOf(objEle));
					break;
				case "invisible":
					oWait.until(ExpectedConditions.invisibilityOf(objEle));
					break;
				default:
					System.out.println("Invalid wait condition '"+strWaitReason+"' was provided");
			}
			return true;
		}catch(Exception e)
		{
			System.out.println("Exception in waitForElement() method. " + e);
			return false;
		}
		finally {
			oWait = null;
		}
	}
	
	/********************************************************
	 * Method Name		: Locator()
	 * Purpose			: to get the locator of WebElement
	 ********************************************************/
	public String Locator(WebElement objEle) {
		try {
			String s = objEle.toString();
			return s.substring(s.indexOf('>')+2, s.length()-1);
		}catch(Exception e) {
			System.out.println("Exception in Locator Method : "+e);
			return null;
		}
	}
	
	/********************************************************
	 * Method Name		: FormatDate()
	 * Purpose			: Converts below mentioned format to YYYY-MM-DD
	 ********************************************************/
	public LocalDate FormatDate(String date, String format) {
		DateTimeFormatter dtf = null;
		try {
			switch(format) {
			case "MMMM d, yyyy":
				dtf = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
				break;
			case "d MMMM yyyy":
				dtf = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);
				break;
			default:
				System.out.println("Invalid Date Format");
				break;
			}
			
			LocalDate date1 = LocalDate.parse(date, dtf);	
			return date1;
			
		}catch(Exception e) {
			System.out.println("Exception in FormatDate method : "+e);
			return null;
		}
	}
}
