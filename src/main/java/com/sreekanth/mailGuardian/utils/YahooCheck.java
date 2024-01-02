package com.sreekanth.mailGuardian.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class YahooCheck {

	public static boolean checkYahoo(String uname) {

		try {
			// Create a new instance of the HtmlUnitDriver
			WebDriver driver = new HtmlUnitDriver();
			System.out.println("uname is " + uname);
			// Navigate to the Yahoo login page
			driver.get("https://login.yahoo.com/");

			// Find the username input field and enter your username
			WebElement usernameField = driver.findElement(By.id("login-username"));
			usernameField.sendKeys(uname);

			// Press Enter to submit the form
	        WebElement submitButton = driver.findElement(By.id("login-signin"));
	        submitButton.click();

	        // You can add additional logic or wait statements here if needed

	        // Capture the current page source (response)
	    

			// Wait for some time to allow the page to load (you might need to adjust the
			// time)
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// Capture the response (you may need to refine this based on your requirements)
			String pageSource = driver.getPageSource();
			driver.quit();
			System.out.println("<<------------------------------------------------------------------------------>>");
		
			System.out.println(pageSource);
			
			
			System.out.println("<<------------------------------------------------------------------------------>>");
			
			System.out.println("Page Source Contains -->" + pageSource.contains("messages.INVALID_USERNAME"));// Check if the response contains the word "error"
			if (pageSource.contains("messages.INVALID_USERNAME")) {
				System.out.println("Contains Error");
				return false;
			} else {
				System.out.println("No Error");
				return true;
			}

			// Close the browser

		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}
}
