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
        
        //open existing supplier
        
    	WebElement add = driver.findElement(By.xpath("//td[@id=\"btnPortfolioSearchNew\"]"));
		JavascriptExecutor E=(JavascriptExecutor)driver;
		E.executeScript("arguments[0].click()", add);
		
		WebElement DD = driver.findElement(By.xpath("//select[@id=\"providerTypeDDL\"]"));
		Select D=new Select(DD);
		
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(60000));
		w.until(ExpectedConditions.elementToBeClickable(DD));
		D.selectByVisibleText("Supplier");
		driver.findElement(By.xpath("//input[@id=\"TaxID\"]")).sendKeys("273899944",Keys.ENTER);
		driver.findElement(By.xpath("//button[@id=\"btnTaxIDSearch\"]")).click();
		driver.findElement(By.xpath("//button[@id=\"btnOpenProvider\"]")).click();		
      
        
        //To select section
        Select sections=new Select(driver.findElement(By.xpath("//div[@id=\"sectionMenu\"]/div/select")));
        sections.selectByVisibleText("Supplier Reimbursement Information");
           
        Select networkLevel=new Select(driver.findElement(By.xpath("//select[@data-link=\"SupplierReimbursementInformation.IsReimbursementInformationapplicableataSupplierNetworkLevel\"]")));
        networkLevel.selectByValue("Yes");
       
        //To select Which reimbursement structures are applicable
       WebElement reimbursmentStructure = driver.findElement(By.xpath("//select[@data-link=\"SupplierReimbursementInformation.WhichreimbursementstructuresareapplicableataSupplierNetworkLevel\"]"));
       Select s=new Select(reimbursmentStructure);
       if (s.isMultiple()) 
       {
    	   s.selectByVisibleText("Professional");
    	   s.selectByVisibleText("ASC");
    	   s.selectByVisibleText("Institutional");
	   }
       
       JavascriptExecutor executor = (JavascriptExecutor) driver;
       executor.executeScript("window.scrollBy(0,250)");

       WebElement r = driver.findElement(By.xpath("//input[@value=\"Reimbursement Table Information - Supplier\"]"));
       executor.executeScript("arguments[0].click()", r);
       
	  
       try {
    	   WebElement manualOverride = driver.findElement(By.xpath("//span[@title=\"false\"]"));  
    	   Actions mo=new Actions(driver);
           mo.doubleClick(manualOverride).build().perform();
      
		
	} catch (Exception e) {
		  WebElement manualOverride1 = driver.findElement(By.xpath("//div[@comp-id=\"204\"]"));  
	       Actions mo=new Actions(driver);
	       mo.doubleClick(manualOverride1).build().perform();

	}
       driver.findElement(By.xpath("//input[@value=\"Category Rates - Supplier Network\"]")).click();
       driver.findElement(By.xpath("//div[@style=\"width: 163px; left: 670px; white-space: normal;\"]")).click();

       //To select table name 
       driver.findElement(By.xpath("//div[@style=\"width: 163px; left: 670px; white-space: normal;\"]")).click();
       Select tablename=new Select(driver.findElement(By.xpath("//select[@id=\"idTableName_0\"]")));
       tablename.selectByVisibleText("Home Health OP 2019");
      
       //Scroll
       executor.executeScript("window.scrollBy(0,500)");

       driver.findElement(By.xpath("//input[@value=\"Category Rates - Supplier\"]")).click();
       driver.findElement(By.xpath("//input[@value=\"Anesthesia Conversion Factor - Supplier Network\"]")).click();
      
      // driver.findElement(By.xpath("value=\"Anesthesia Conversion Factor - Supplier\"")).click();
     
      
       
         

	}

}
