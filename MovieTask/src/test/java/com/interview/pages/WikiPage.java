package com.interview.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.interview.driver.Movies;

public class WikiPage extends Movies{

	public WikiPage(WebDriver oDriver) {
		PageFactory.initElements(oDriver, this);
	}
	
	@FindBy(xpath = "//th[text()='Country']/following-sibling::td")
	private WebElement CountryName;
	
	@FindBy(xpath ="//div[text()='Release date']/parent::th/following-sibling::td//li")
	private WebElement ReleaseDate;
	
	/********************************************************
	 * Method Name		: GetCountryFromWiki()
	 * Purpose			: This method returns Country Name from Wikipedia
	 ********************************************************/
	public String GetCountryFromWiki() {
		try {
			
			return CountryName.getText();
			
		}catch(Exception e) {
			System.out.println("Exception in GetCountryFromWiki Method : "+e);
			return null;
		}
	}
	
	/********************************************************
	 * Method Name		: GetReleaseDateFromWiki()
	 * Purpose			: This method returns Release Date from Wikipedia
	 ********************************************************/
	public String GetReleaseDateFromWiki() {
		try {
			
			String actualDate = ReleaseDate.getText();
			
			return appInd.FormatDate(actualDate, "d MMMM yyyy").toString();
			
		}catch(Exception e) {
			System.out.println("Exception in GetReleaseDateFromWiki Method : "+e);
			return null;
		}
	}
}
