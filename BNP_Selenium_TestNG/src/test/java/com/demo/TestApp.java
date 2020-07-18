package com.demo;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class TestApp {
	public WebDriver driver;
	public String url = "http://automationpractice.com/index.php";
	public String path = "E:\\Tools\\drivers\\chromedriver.exe";
	
	@BeforeTest
	public void initialize() {
		System.setProperty("webdriver.chrome.driver", path);
	    driver = new ChromeDriver();
	    driver.get(url);
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	    driver.manage().window().maximize(); 
	}
	
  @Test(priority=0)
  public void registerTest() throws InterruptedException {
	  driver.findElement(By.className("login")).click();
	  driver.findElement(By.name("email_create")).sendKeys("email90@gmail.com");
	  driver.findElement(By.id("SubmitCreate")).click();
	  Thread.sleep(5000);
	  driver.findElement(By.name("id_gender")).click();
	  driver.findElement(By.id("customer_firstname")).sendKeys("Ram");
	  driver.findElement(By.id("customer_lastname")).sendKeys("Kamath");
	  driver.findElement(By.id("passwd")).sendKeys("password@!");
	  Select days = new Select(driver.findElement(By.name("days")));
	  days.selectByValue("11");
	  Select months = new Select(driver.findElement(By.name("months")));
	  months.selectByValue("10");
	  Select years = new Select(driver.findElement(By.name("years")));
	  years.selectByValue("1998");
	  Thread.sleep(4000);
	  //driver.findElement(By.id("firstname")).sendKeys("Ram");
	  //driver.findElement(By.id("lastname")).sendKeys("Kamath");
	  driver.findElement(By.id("company")).sendKeys("BNP Paribas");
	  driver.findElement(By.id("address1")).sendKeys("101, Arizona Building, Marigold Street,");
	  driver.findElement(By.id("city")).sendKeys("New York City");
	  Select state = new Select(driver.findElement(By.name("id_state")));
	  state.selectByVisibleText("New York");
	  driver.findElement(By.id("postcode")).sendKeys("00000");
	  Select country = new Select(driver.findElement(By.name("id_country")));
	  country.selectByVisibleText("United States");
	  
	  driver.findElement(By.id("phone")).sendKeys("27765033");
	  driver.findElement(By.id("phone_mobile")).sendKeys("9876543210");
	  driver.findElement(By.id("alias")).clear();
	  driver.findElement(By.id("alias")).sendKeys("altmail@gmail.com");
	  Thread.sleep(5000);
	  driver.findElement(By.id("submitAccount")).click();
	  Thread.sleep(5000);
	  
	  String expectedURL = "http://automationpractice.com/index.php?controller=my-account";
	  String actualURL = driver.getCurrentUrl();
	  if(actualURL.equalsIgnoreCase(expectedURL)) {
		  System.out.println("Register test completed successfully. New user registered");
		  driver.findElement(By.className("logout")).click();
	  }
	  else {
		  System.out.println("Test failed..");
	  } 
	  Thread.sleep(10000);
	  //driver.navigate().to(url);
  }
  
  @Test(priority=1)
  public void loginTest() throws InterruptedException {
	  driver.findElement(By.className("login")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.name("email")).sendKeys("email123@gmail.com");
	  driver.findElement(By.name("passwd")).sendKeys("password@!");
	  Thread.sleep(5000);
	  driver.findElement(By.name("SubmitLogin")).click();
	  String expected = "http://automationpractice.com/index.php?controller=my-account";
	  String actual = driver.getCurrentUrl();
	  try {
		  Assert.assertEquals(expected, actual);
		  System.out.println("Login Successful");
		  }
		  catch (AssertionError e) {
			  System.out.println("Login failed.."+e.getMessage());
		  }
	  Thread.sleep(5000);
	  driver.navigate().to(url);
	  Thread.sleep(5000);
	  
  }
  
  @Test(priority=2)
  public void searchItemTest() throws InterruptedException {
	  driver.findElement(By.name("search_query")).sendKeys("Blouse");
	  Thread.sleep(5000);
	  driver.findElement(By.name("submit_search")).click();
	  Thread.sleep(5000);
	  String expectedTitle = "Search - My Store";
	  String actualTitle = driver.getTitle();
	  try {
		  Assert.assertEquals(expectedTitle, actualTitle);
		  System.out.println("Search item successful");
		  
	  } catch (AssertionError e) {
		  System.out.println("Search failed.."+e.getMessage());
	  }
  }
  
  @Test(priority=3)
  public void addToCartTest() throws InterruptedException {
	  //driver.findElement(By.xpath("//img[contains(@alt,'Blouse')]")).click();
	  //driver.findElement(By.className("product_img_link")).click();
	 //driver.findElement(By.className("product-name")).click();
	  driver.findElement(By.partialLinkText("Blouse")).click();
	  Thread.sleep(5000);
	  driver.findElement(By.name("Submit")).click();
	  String addedcartURL = "http://automationpractice.com/index.php?id_product=2&controller=product";
	  String currentURL = driver.getCurrentUrl();
	  try {
		  Assert.assertEquals(addedcartURL, currentURL);
		  System.out.println("Successfully added to cart");
		  }
		  catch (AssertionError e) {
			  System.out.println("Adding to cart failed .."+e.getMessage());
		  }
	  driver.findElement(By.partialLinkText("Proceed")).click();
	  Thread.sleep(5000);
  }
  @AfterTest
  public void close() {
	  driver.quit();
  }
  
  
}
