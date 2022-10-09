package com.interview.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.interview.methods.AppDependentMethods;
import com.interview.methods.AppIndependentMethods;
import com.interview.pages.GooglePage;
import com.interview.pages.ImdbPage;
import com.interview.pages.WikiPage;
import com.interview.testdata.TestData;

public class Movies implements TestData{
	
	public static AppIndependentMethods appInd = null;
	public static AppDependentMethods appDep = null;
	public static WebDriver driver = null;
	
	GooglePage googlePage = null;
	WikiPage wikiPage = null;
	ImdbPage imdbPage = null;
	
	String CountryNameFromWiki = null;
	String ReleaseDateFromWiki = null;
	String CountryNameFromIMDb = null;
	String ReleaseDateFromIMDb = null;
	
	@BeforeSuite
	public void loadClasses() {
		
		try {
			appInd = new AppIndependentMethods();
			appDep = new AppDependentMethods();	
		}catch(Exception e)
		{
			System.out.println("Exception in loadClasses() : "+e);
		}			
	}
	
	@BeforeTest
	public void InitBrowser() {
		driver = appInd.launchBrowser(browserType);
		System.out.println("==> "+browserType+" Browser launched successfully");
	}
	
	@Test
	public void ExtractMovieData() throws InterruptedException {
		try {
			appDep.navigateURL(driver, URL);
			googlePage = new GooglePage(driver);
			wikiPage = new WikiPage(driver);
			imdbPage = new ImdbPage(driver);
			
			googlePage.SearchMovieName(driver,MovieName);
			googlePage.NavigateTo_Wiki_IMDb_Links(driver,MovieName);
			
			
			
			Set<String> windows = driver.getWindowHandles();
			List<String> windowsList = new ArrayList<>(windows);
			 
			 for(int i=0;i<windowsList.size(); i++) {
				 
				 driver.switchTo().window(windowsList.get(i));
				 String title = driver.getTitle();
				 System.out.println("Title : "+title);
				 if(title.contains("Wikipedia")) {
					 CountryNameFromWiki = wikiPage.GetCountryFromWiki();
					 ReleaseDateFromWiki = wikiPage.GetReleaseDateFromWiki();
				 }else if(title.contains("IMDb")) {
					 CountryNameFromIMDb = imdbPage.GetCountryFromIMDb();
					 ReleaseDateFromIMDb = imdbPage.GetReleaseDateFromIMDb();
				 }
			 }
			
			
			System.out.println("======Wiki Pushpa======");
			System.out.println("Release Date : "+ReleaseDateFromWiki);
			System.out.println("Country      : "+CountryNameFromWiki);
			System.out.println("======IMDb Pushpa======");
			System.out.println("Release Date : "+ReleaseDateFromIMDb);
			System.out.println("Country      : "+CountryNameFromIMDb);
			
			Thread.sleep(5000);
		}catch(Exception e) {
			System.out.println("Exception in ExtractMovieData method : "+e);
		}
	}
	
	@Test(dependsOnMethods= {"ExtractMovieData"})
	public void AssertionOfData() {
		try {
			
			Assert.assertEquals(CountryNameFromWiki, CountryNameFromIMDb, "Country Names are not same in both sites");
			Assert.assertEquals(ReleaseDateFromWiki, ReleaseDateFromIMDb, "Release Dates are not same in both sites");
			
		}catch(Exception e) {
			System.out.println("Exception in AssertionOfData method : "+e);
		}
	}
		
	
	@AfterTest
	public void CloseBrowser() {
		appInd.closeBrowser(driver);
		System.out.println("==> "+browserType+" Browser closed successfully");
	}
	
}
