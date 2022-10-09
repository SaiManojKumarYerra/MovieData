package com.interview.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.interview.driver.Movies;

public class ImdbPage extends Movies{

	public ImdbPage(WebDriver oDriver) {
		PageFactory.initElements(oDriver, this);
	}

	@FindBy(xpath = "//span[text()='Country of origin']/following-sibling::div//a")
	private WebElement CountryOfOrigin;
	
	@FindBy(xpath ="//a[text()='Release date']/following-sibling::div//a")
	private WebElement Releasedate;
	
	/********************************************************
	 * Method Name		: GetCountryFromIMDb()
	 * Purpose			: This method returns Country Name from IMDb
	 ********************************************************/
	public String GetCountryFromIMDb() {
		try {
			
			return CountryOfOrigin.getText();
			
		}catch(Exception e) {
			
			System.out.println("Exception in GetCountryFromWiki Method : "+e);
			return null;
		}
	}
	
	/********************************************************
	 * Method Name		: GetReleaseDateFromIMDb()
	 * Purpose			: This method returns Release Date from IMDb
	 ********************************************************/
	public String GetReleaseDateFromIMDb() {
		try {
			
			String DateWithUS = Releasedate.getText();
			
			String actualDate = DateWithUS.substring(0, DateWithUS.indexOf('(')-1);
			
			return appInd.FormatDate(actualDate, "MMMM d, yyyy").toString();
			
		}catch(Exception e) {
			System.out.println("Exception in GetCountryFromWiki Method : "+e);
			return null;
		}
	}
}
