package com.interview.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.interview.driver.Movies;

public class GooglePage extends Movies {

	public GooglePage(WebDriver oDriver) {
		PageFactory.initElements(oDriver, this);
	}
	
	@FindBy(xpath ="//input[@name='q']")
	private WebElement SearchInputField;
	
	@FindBy(xpath="//h3[contains(text(),'Wikipedia')]")
	private WebElement h3Wikipedia;
	
	@FindBy(xpath="//h3[contains(text(),'IMDb')]")
	private WebElement h3IMDb;
	
	@FindBy(xpath = "//img[@alt='Google']")
	private WebElement googleImg;
	
	@FindBy(xpath ="//h3[contains(text(),'Wikipedia')]/parent::a")
	private WebElement h3WikipediaLink;
	
	@FindBy(xpath="//h3[contains(text(),'IMDb')]/parent::a")
	private WebElement h3IMDbLink;

	/********************************************************
	 * Method Name		: SearchMovieName()
	 * Purpose			: This method searches the movie name in GoogleSearch page
	 ********************************************************/
	public void SearchMovieName(WebDriver oDriver, String moviename) {
		
		try {
			appInd.setObject(oDriver, SearchInputField, moviename);
			appInd.clickEnter(oDriver,SearchInputField);
			appInd.waitForElement(oDriver, googleImg, "visible", 25);
			
			if(appInd.verifyElementExist(oDriver, googleImg)) {
				System.out.println("==> Google Search is successful for "+moviename);
			}
			
		}catch(Exception e) {
			System.out.println("Exception in SearchMovieName method : "+e);
		}	
	}

	/********************************************************
	 * Method Name		: NavigateTo_Wiki_IMDb_Links()
	 * Purpose			: This method opens the Wiki and IMDb Links of particular Movie Name
	 ********************************************************/
	public void NavigateTo_Wiki_IMDb_Links(WebDriver oDriver, String moviename) {
		
		boolean linksExist = false;
		List<WebElement> linksList = null;
		
		try {
			linksList = new ArrayList<>();
			
			if(appInd.verifyElementExist(oDriver, h3Wikipedia)) {
				
				linksList.add(h3WikipediaLink);
				System.out.println("==> Wiki Link is available for "+moviename);
				
				if(appInd.verifyElementExist(oDriver, h3IMDb)) {
					
					linksList.add(h3IMDbLink);
					System.out.println("==> IMDb Link is available for "+moviename);
					
					linksExist = true;
					
				}else {
					System.out.println("==> IMDb link not available for "+moviename);
				}
				
			}else {
				System.out.println("==> Wikipedia link not available for "+moviename);
			}
			
			
			if(linksExist) {
				for(int i=0; i<linksList.size(); i++) {
					String Tabs = Keys.chord(Keys.CONTROL,Keys.ENTER);
					linksList.get(i).sendKeys(Tabs);
				}
			System.out.println("==> Both Wikipedia and IMDb Links opened in new Tabs");
			}
		}catch(Exception e) {
			 System.out.println("Exception in NavigateTo_Wiki_IMDb_Links method : "+e);
		 }
		
	}
}
