package com.interview.methods;

import org.openqa.selenium.WebDriver;

import com.interview.driver.Movies;

public class AppDependentMethods extends Movies{
	/******************************************
	 * Method Name		: navigateURL()
	 * 
	 *******************************************/
	public void navigateURL(WebDriver oDriver, String URL)
	{
		try {
			oDriver.get(URL);
			System.out.println("==> Navigation to Google Page is successful");
		}catch(Exception e)
		{
			System.out.println("Exception in navigateURL method() : "+e);
		}
	}	
	
}
