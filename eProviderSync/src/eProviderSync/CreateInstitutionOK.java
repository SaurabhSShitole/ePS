package eProviderSync;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateInstitutionOK {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60000));
		driver.get("https://ok-epscfg.simplifyhealthcare.com/DashBoard/Index");
		driver.findElement(By.xpath("//input[@name='UserName']")).sendKeys("saurabh.shitole");
		driver.findElement(By.xpath("//input[@name='Password']")).sendKeys("Summer@2018#", Keys.ENTER);
		System.out.println(driver.getTitle());
		driver.findElement(By.xpath("//a[@id='FoundationTemplate']")).click();
		
		WebElement add = driver.findElement(By.xpath("//td[@id=\"btnPortfolioSearchNew\"]"));
		JavascriptExecutor E=(JavascriptExecutor)driver;
		E.executeScript("arguments[0].click()", add);
		
		WebElement DD = driver.findElement(By.xpath("//select[@id=\"providerTypeDDL\"]"));
		Select D=new Select(DD);
		
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(60000));
		w.until(ExpectedConditions.elementToBeClickable(DD));
		D.selectByVisibleText("Institution");
		
	    driver.findElement(By.xpath("//input[@id=\"ProviderName\"]")).sendKeys("Test",Keys.ENTER);
	    driver.findElement(By.xpath("//input[@id=\"TaxID\"]")).sendKeys("787568777",Keys.ENTER);
	    driver.findElement(By.xpath("//input[@id=\"effectiveDate\"]")).sendKeys("04/04/2022",Keys.ENTER);
	   
	  //div/div[1]/button[1][contains(.,'Close')]
	    
	    WebElement category = driver.findElement(By.xpath("//select[@id=\"categoryDDL1\"]"));
		Select c=new Select(category);
		c.selectByValue("40");

		driver.findElement(By.xpath("//button[@id=\"btnFolderDialogSave\"]")).click();
		
		 WebElement Duplicate = driver.findElement(By.xpath("/html/body/div[13]/div[11]/div/button"));
		 if (Duplicate.isEnabled()) {
		 try { Duplicate.click();
		 System.out.println("TaxID entered is already present");
		// driver.quit();
			
		} catch (StaleElementReferenceException e2) {
			System.out.println("handled");
			
			driver.findElement(By.xpath("//input[@data-link=\"SupplierInformation.SupplierFrom\"]")).sendKeys("004/04/2022",Keys.ENTER);
			
		//Institution Address And Contact Information
		WebElement section = driver.findElement(By.xpath("//select[@class=\"section-menu\"]"));	
		Select Isections=new Select(section);
		Isections.selectByVisibleText("Institution Address And Contact Information");
		
		driver.findElement(By.xpath("//input[@data-link=\"SupplierAddressInformation.SupplierBillingInformation.AddressFromDate\"]")).sendKeys("005/06/2022",Keys.ENTER);
		driver.findElement(By.xpath("//input[@data-link=\"SupplierAddressInformation.SupplierBillingInformation.Line1\"]")).sendKeys("Line1",Keys.ENTER);
		driver.findElement(By.xpath("//input[@data-link=\"SupplierAddressInformation.SupplierBillingInformation.Line2\"]")).sendKeys("Line2",Keys.ENTER);
		driver.findElement(By.xpath("//input[@data-link=\"SupplierAddressInformation.SupplierBillingInformation.Line3\"]")).sendKeys("Line3",Keys.ENTER);
		driver.findElement(By.xpath("//input[@data-link=\"SupplierAddressInformation.SupplierBillingInformation.City\"]")).sendKeys("City",Keys.ENTER);
		WebElement state = driver.findElement(By.xpath("//select[@data-link=\"SupplierAddressInformation.SupplierBillingInformation.State\"]"));
		Select s=new Select(state);
		s.selectByValue("AL");
		driver.findElement(By.xpath("//input[@data-link=\"SupplierAddressInformation.SupplierBillingInformation.PostalCode\"]")).sendKeys("69769",Keys.ENTER);
		driver.findElement(By.xpath("//input[@data-link=\"SupplierAddressInformation.SupplierBillingInformation.PostalCode4\"]")).sendKeys("6878",Keys.ENTER);
		driver.findElement(By.xpath("//input[@data-link=\"SupplierAddressInformation.SupplierBillingInformation.FIPSCountry\"]")).sendKeys("NYC",Keys.ENTER);
		driver.findElement(By.xpath("//input[@data-link=\"SupplierAddressInformation.SupplierBillingInformation.Country\"]")).sendKeys("US",Keys.ENTER);
		driver.findElement(By.xpath("//input[@data-link=\"SupplierAddressInformation.SupplierBillingInformation.Latitude\"]")).sendKeys("6587",Keys.ENTER);
		driver.findElement(By.xpath("//input[@data-link=\"SupplierAddressInformation.SupplierBillingInformation.Longitude\"]")).sendKeys("-4532",Keys.ENTER);
		
		//Institution Location Information
		Isections.selectByVisibleText("Institution Location Information");
		driver.findElement(By.xpath("//div[@style=\"width: 148px; left: 553px; white-space: normal;\"]")).click();
		
		
		}
	    
	}
	}
}
