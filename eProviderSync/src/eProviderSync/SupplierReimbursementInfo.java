package eProviderSync;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.netty.handler.timeout.TimeoutException;

public class SupplierReimbursementInfo {

	public static void main(String[] args) throws AWTException, InterruptedException {

		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60000));
		driver.get("https://bcbsne-epscfg.simplifyhealthcare.com/ConsumerAccount/Foundationsearch");
		driver.findElement(By.id("UserName")).sendKeys("saurabh.shitole");
		driver.findElement(By.id("Password")).sendKeys("Summer@2018#");

		driver.findElement(By.xpath("//*[contains(@type,'submit')]")).click();

		// Goto Portfolio
		driver.findElement(By.xpath("//a[@data-original-title='Portfolio Provider Management']")).click();

		// open existing supplier

		WebElement add = driver.findElement(By.xpath("//td[@id=\"btnPortfolioSearchNew\"]"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", add);

		WebElement DD = driver.findElement(By.xpath("//select[@id=\"providerTypeDDL\"]"));
		Select D = new Select(DD);

		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(60000));
		w.until(ExpectedConditions.elementToBeClickable(DD));
		D.selectByVisibleText("Supplier");
		driver.findElement(By.xpath("//input[@id=\"TaxID\"]")).sendKeys("987879878", Keys.ENTER);
		driver.findElement(By.xpath("//button[@id=\"btnTaxIDSearch\"]")).click();
		driver.findElement(By.xpath("//button[@id=\"btnOpenProvider\"]")).click();

		// To select section
		Select sections = new Select(driver.findElement(By.xpath("//div[@id=\"sectionMenu\"]/div/select")));
		sections.selectByVisibleText("Supplier Reimbursement Information");

		Select networkLevel = new Select(driver.findElement(By.xpath(
				"//select[@data-link=\"SupplierReimbursementInformation.IsReimbursementInformationapplicableataSupplierNetworkLevel\"]")));
		networkLevel.selectByValue("Yes");

		// To select Which reimbursement structures are applicable
		WebElement reimbursmentStructure = driver.findElement(By.xpath(
				"//select[@data-link=\"SupplierReimbursementInformation.WhichreimbursementstructuresareapplicableataSupplierNetworkLevel\"]"));
		Select s = new Select(reimbursmentStructure);
		if (s.isMultiple()) {
			s.selectByVisibleText("Professional");
			s.selectByVisibleText("ASC");
			s.selectByVisibleText("Institutional");
		}

		 executor.executeScript("window.scrollBy(0,300)");
		 
		 
		 //Reimbursement Table Information - Supplier Repeater
		WebElement r = driver.findElement(By.xpath("//input[@value=\"Reimbursement Table Information - Supplier\"]"));
		executor.executeScript("arguments[0].click()", r);
		
		//To select MO 
		 try 
		 {
			WebElement manualOverride = driver.findElement(By.xpath("//span[@title=\"false\"]"));
			Actions mo = new Actions(driver);
			mo.doubleClick(manualOverride).build().perform();
	     } 
	    catch (Exception e) 
		 {
			WebElement manualOverride1 = driver.findElement(By.xpath("//div[@col-id=\"ManualOverride\" and @ style=\"width: 171px; left: 499px; text-align: center;\"]"));
			Actions mo = new Actions(driver);
			mo.doubleClick(manualOverride1).build().perform();
	     }	   
		driver.findElement(By.xpath("//div[@style=\"width: 163px; left: 670px; white-space: normal;\"]")).click();
        Thread.sleep(2000);

		// To select table name
	    driver.findElement(By.xpath("//div[@style=\"width: 163px; left: 670px; white-space: normal;\"]")).click();
		Thread.sleep(2000);
		Select tablename = new Select(driver.findElement(By.xpath("//select[@id=\"idTableName_0\"]")));
		tablename.selectByVisibleText("ASC 2009");
		Thread.sleep(5000);
		
		
		
		//Category Rates - Supplier Network Repeater
		driver.findElement(By.xpath("//input[@value=\"Category Rates - Supplier Network\"]")).click();
		executor.executeScript("window.scrollBy(0,300)");
        Thread.sleep(3000);
        
        
        
        //Category Rates - Supplier Repeater
		driver.findElement(By.xpath("//input[@value=\"Category Rates - Supplier\"]")).click();
        Thread.sleep(4000);
        
        //To select categoryratesMO
		WebElement categoryratesMO = driver.findElement(By.xpath("(//div[@col-id=\"ManualOverride\"])[4]/span"));
		Actions action=new Actions(driver);
		action.doubleClick(categoryratesMO).build().perform();
		Thread.sleep(2000);
		
		//To send Value in ServiceCategoryReimPercent
		driver.findElement(By.xpath("(//div[@col-id=\"ServiceCategoryReimPercent\"])[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//div[@col-id=\"ServiceCategoryReimPercent\"])[2]")).sendKeys("010",Keys.ENTER);
		Thread.sleep(4000);
		executor.executeScript("window.scrollBy(0,300)");
		
		
		
		//Anesthesia Conversion Factor - Supplier Network Repeater
		driver.findElement(By.xpath("//input[@value=\"Anesthesia Conversion Factor - Supplier Network\"]")).click();
		executor.executeScript("window.scrollBy(0,450)");
		Thread.sleep(4000);
		
		//To select ServiceCategoryFromDate
		driver.findElement(By.xpath("(//div[@col-id=\"ServiceCategoryFrom\"])[2]")).sendKeys("007/11/2022",Keys.ENTER);
		Thread.sleep(4000);
		executor.executeScript("window.scrollBy(0,300)");

		//Anesthesia Conversion Factor - Supplier repeater
		driver.findElement(By.xpath("//input[@value=\"Anesthesia Conversion Factor - Supplier\"]")).click();
		Thread.sleep(4000);

		//To select Anesthesia Repeater MO
		try {
			WebElement AnesthesiaMO = driver.findElement(By.xpath("(//div[@col-id=\"ManualOverride\"])[12]/span"));
			Actions a=new Actions(driver);
			a.doubleClick(AnesthesiaMO).build().perform();
			
		} catch (Exception e) {
			WebElement AnesthesiaMO = driver.findElement(By.xpath("(//div[@col-id=\"ManualOverride\"])[12]/span"));
			Actions a=new Actions(driver);
			a.doubleClick(AnesthesiaMO).build().perform();

		}
		Thread.sleep(4000);

		
		executor.executeScript("window.scrollBy(0,300)");
		Thread.sleep(4000);


		//Benefit Network Discount - Supplier Network Repeater
		driver.findElement(By.xpath("//input[@value=\"Benefit Network Discount - Supplier Network\"]")).click();
		executor.executeScript("window.scrollBy(0,300)");
		Thread.sleep(2000);
		
		
		//Benefit Network Discount - Supplier Repeater
		driver.findElement(By.xpath("//input[@value=\"Benefit Network Discount - Supplier\"]")).click();
		
		//To select BenefitNetworkMO
		try {
			WebElement BenefitNetworkMO = driver.findElement(By.xpath("(//div[@col-id=\"ManualOverride\"])[16]/span"));
			Actions a=new Actions(driver);
			a.doubleClick(BenefitNetworkMO).build().perform();
			
		} catch (Exception e) {
			WebElement BenefitNetworkMO = driver.findElement(By.xpath("(//div[@col-id=\"ManualOverride\"])[16]/span"));
			Actions a=new Actions(driver);
			a.doubleClick(BenefitNetworkMO).build().perform();
		}
		
		
		
		executor.executeScript("window.scrollBy(0,300)");
		Thread.sleep(2000);
		
		//Charge Master Curb - Supplier Network Repeater 
		driver.findElement(By.xpath("//input[@value=\"Charge Master Curb - Supplier Network\"]")).click();
		executor.executeScript("window.scrollBy(0,300)");
		Thread.sleep(2000);
		
		//Charge Master Curb - Supplier Repeater
		driver.findElement(By.xpath("//input[@value=\"Charge Master Curb - Supplier\"]")).click();
		Thread.sleep(2000);
		executor.executeScript("window.scrollBy(0,300)");
		
		//Percentage Payment Rate - Supplier Network Repeater
		driver.findElement(By.xpath("//input[@value=\"Percentage Payment Rate - Supplier Network\"]")).click();
		Thread.sleep(2000);
		executor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		
		
		//Percentage Payment Rate - Supplier Repeater
		driver.findElement(By.xpath("//input[@value=\"Percentage Payment Rate - Supplier\"]")).click();


	}

}
