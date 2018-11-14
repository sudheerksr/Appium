package com.appium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class Appium {

	public static final String MobileElement = null;
	AppiumDriver<WebElement> driver;
	MobileElement appTitle;
	WebDriverWait wait;

	@SuppressWarnings("rawtypes")
	@Test
	public void nativeApps() throws MalformedURLException, InterruptedException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		cap.setCapability(MobileCapabilityType.NO_RESET, true);
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "100");
		// Native App Calculator
		cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.android.calculator2");
		cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.android.calculator2.Calculator");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "5.1.1");
		cap.setCapability("avd", "androidtest");
		driver = new AndroidDriver<WebElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
		WebDriverWait wait = new WebDriverWait(driver, 100);
		/*
		 * WebElement element =
		 * driver.findElementByXPath("//*[@content-desc='Calculator']");
		 * 
		 * wait.until(ExpectedConditions.visibilityOf(element)); element.click();
		 */
		Thread.sleep(10000);
		driver.findElementById("com.android.calculator2:id/digit_8").click();
		driver.findElementById("com.android.calculator2:id/op_mul").click();
		driver.findElementById("com.android.calculator2:id/digit_8").click();

	}

	@SuppressWarnings("unchecked")
	@Test
	public void playStore() throws MalformedURLException, InterruptedException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy J6");
		cap.setCapability(MobileCapabilityType.NO_RESET, true);
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "100");
		cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.android.vending");
		cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.google.android.finsky.activities.MainActivity");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "8.0.0");
		// cap.setCapability("avd", "androidtest");
		driver = new AndroidDriver<WebElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
		// String appName = "TeamViewer for Remote Control";
		String appName = "Linux  Commands for  Beginners";
		// How to scroll to specific text
		/*
		 * MobileElement scrollToText = (MobileElement)
		 * driver.findElement(MobileBy.AndroidUIAutomator(
		 * "new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().text(\""
		 * + appName + "\"));")); scrollToText.click();
		 * 
		 * // Verifying the app detail page appTitle = (MobileElement)
		 * driver.findElementById("com.android.vending:id/title_title");
		 * Assert.assertTrue(appName.equals(appTitle.getText().trim()));
		 * driver.navigate().back();
		 */

		MobileElement scrollToElement = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(new UiSelector().description(\"Search\"));"));
		scrollToElement.click();
		MobileElement editText = (MobileElement) driver.findElementById("com.android.vending:id/search_box_text_input");
		editText.sendKeys(appName);
		Thread.sleep(5000);
		List<WebElement> elements = driver.findElementsById("com.android.vending:id/suggest_text");

		for (WebElement element : elements) {
			if (appName.equals(element.getText().trim())) {
				element.click();
				break;
			}
		}
		Thread.sleep(5000);
		// appTitle = (MobileElement)
		appTitle = (io.appium.java_client.MobileElement) driver.findElementById("com.android.vending:id/title_title");// com.android.vending:id/li_title
		// appTitle = (MobileElement)
		// driver.findElementById("com.android.vending:id/li_title");
		Assert.assertTrue(appName.equals(appTitle.getText().trim()));
		Thread.sleep(5000);
		// driver.findElement(MobileBy.AndroidUIAutomator("new
		// UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.vending:id/title\").text(\"INSTALL\")")).click();
		driver.findElementByClassName("android.widget.Button").click();
		Thread.sleep(5000);
//		driver.findElementByClassName("android.widget.Button").click();
		wait = new WebDriverWait(driver, 360);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElementById("com.android.vending:id/progress_bar")));
		Thread.sleep(10000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(
				"//android.view.ViewGroup[@resource-id='com.android.vending:id/item_details_panel']//android.view.ViewGroup/android.view.ViewGroup[@resource-id='com.android.vending:id/button_container']/android.widget.Button[1]"))));
		driver.findElement(By.xpath(
				"//android.view.ViewGroup[@resource-id='com.android.vending:id/item_details_panel']//android.view.ViewGroup/android.view.ViewGroup[@resource-id='com.android.vending:id/button_container']/android.widget.Button[1]"))
				.click();
		Thread.sleep(3000);
		driver.findElementById("android:id/button1").click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(	"//android.view.ViewGroup[@resource-id='com.android.vending:id/item_details_panel']//android.view.ViewGroup/android.view.ViewGroup[@resource-id='com.android.vending:id/button_container']/android.widget.Button[1]"))));

		WebElement installButton = driver.findElement(By.xpath(	"//android.view.ViewGroup[@resource-id='com.android.vending:id/item_details_panel']//android.view.ViewGroup/android.view.ViewGroup[@resource-id='com.android.vending:id/button_container']/android.widget.Button[1]"));

		if (installButton.isDisplayed()) {
			Assert.assertTrue(true, "App UnInstalled Successfully");
		} else {
			Assert.assertTrue(false, "App UnInstalled Successfully");
		}
	}	
}
