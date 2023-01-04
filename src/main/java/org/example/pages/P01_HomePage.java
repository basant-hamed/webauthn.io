package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.locators.RelativeLocator;

public class P01_HomePage {
	WebDriver driver;

	public P01_HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	// home page title
	private final By pageTitle= RelativeLocator.with(By.xpath("(//h2)[1]"));
	public String getHomePageTitle(){return driver.findElement(pageTitle).getText();}
	// email field
	private final By emailField= RelativeLocator.with(By.id("input-email"));
	public void addEmail(){driver.findElement(emailField).sendKeys(PageBase.generateString(5));}
	// register button
	private final By registerButton= RelativeLocator.with(By.xpath("(//button)[1]"));
	public void clickRegistrationButton(){driver.findElement(registerButton).click();}
	// authenticate button
	private final By authenticationButton= RelativeLocator.with(By.xpath("(//button)[2]"));
	public void clickAuthenticationButton(){driver.findElement(authenticationButton).click();}
	// advanced settings button
	private final By advancedSettingsButton= RelativeLocator.with(By.xpath("(//button)[3]"));
	public void clickAdvancedSettingsButton(){driver.findElement(advancedSettingsButton).click();}
}
