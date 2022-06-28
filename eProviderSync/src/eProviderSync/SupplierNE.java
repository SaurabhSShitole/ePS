package eProviderSync;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class SupplierNE {

	public static void main(String[] args) throws IOException, InterruptedException {
//Script for Creation of Supplier

		// Instantiate a ChromeDriver class.

		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120000));

		String excelfilePath = ".\\datafiles\\SupplierData1 (2).xlsx";

		FileInputStream data = new FileInputStream(excelfilePath);

		// Creating a workbook

		XSSFWorkbook workbook = new XSSFWorkbook(data);
		String value;
		XSSFSheet sheet;

		// Used to pick int values from the excel

		DataFormatter formatter = new DataFormatter();

		// Append date to the screenshots

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/mm/yyyy");

		LocalDateTime now = LocalDateTime.now();

		// Vertical scroll down

		JavascriptExecutor executor = (JavascriptExecutor) driver;

		// Launch WebSite

		sheet = workbook.getSheetAt(0);

		value = sheet.getRow(1).getCell(1).getStringCellValue();

		driver.navigate().to(value);

		// Maximise the browser

		driver.manage().window().maximize();

		// Enter credentials

		value = sheet.getRow(1).getCell(2).getStringCellValue();

		driver.findElement(By.id("UserName")).sendKeys(value);

		value = sheet.getRow(1).getCell(3).getStringCellValue();

		driver.findElement(By.id("Password")).sendKeys(value);

		driver.findElement(By.xpath("//*[contains(@type,'submit')]")).click();

		// Goto Portfolio

		driver.findElement(By.xpath("//a[@data-original-title='Portfolio Provider Management']")).click();

		try {
			driver.findElement(By.xpath("//td[@id='btnPortfolioSearchNew']")).click();

			
		} catch (ElementClickInterceptedException e) {
			WebElement add = driver.findElement(By.xpath("//td[@id=\"btnPortfolioSearchNew\"]"));
			JavascriptExecutor executor1 = (JavascriptExecutor) driver;
			executor1.executeScript("arguments[0].click()", add);
		}

		Thread.sleep(2000);

		// Create a supplier

		sheet = workbook.getSheetAt(1);

		Select drpCountry = new Select(driver.findElement(By.id("providerTypeDDL")));

		value = sheet.getRow(1).getCell(1).getStringCellValue();

		drpCountry.selectByVisibleText(value);

		value = formatter.formatCellValue(sheet.getRow(1).getCell(2));

		// value = sheet.getRow(1).getCell(4).getStringCellValue();

		driver.findElement(By.id("TaxID")).sendKeys(value);

		driver.findElement(By.id("btnTaxIDSearch")).click();

	

		// If Tax id not present, then add mandatory details

		value = sheet.getRow(1).getCell(3).getStringCellValue();

		driver.findElement(By.id("ProviderName")).sendKeys(value);

		WebElement effectiveDate = driver.findElement(By.id("effectiveDate"));

		effectiveDate.click();

		value = formatter.formatCellValue(sheet.getRow(1).getCell(4));

		effectiveDate.sendKeys(value);

		Select drpCategory = new Select(driver.findElement(By.id("categoryDDL1")));

		value = sheet.getRow(1).getCell(5).getStringCellValue();

		drpCategory.selectByVisibleText(value);


		driver.findElement(By.id("btnFolderDialogSave")).click();

		// Section 1 :: Supplier Information section

		// Entering folder effective date

		sheet = workbook.getSheetAt(2);

		Thread.sleep(20000);

		WebElement fromDate = driver.findElement(By.xpath("//div[@id=\"mainsection\"]/div[4]/div[2]/input"));

		value = formatter.formatCellValue(sheet.getRow(1).getCell(1));

		fromDate.click();

		fromDate.sendKeys(value);

		// Select Credentialing status

		Select credential = new Select(
				driver.findElement(By.xpath("//div/select[@data-link=\"SupplierInformation.CredentialedStatus\"]")));

		value = sheet.getRow(1).getCell(2).getStringCellValue();

		credential.selectByVisibleText(value);

		// Select Delegated Credentialing status

		Select delegated = new Select(driver
				.findElement(By.xpath("//div/select[@data-link=\"SupplierInformation.DelegatedCredentialing\"]")));

		value = sheet.getRow(1).getCell(3).getStringCellValue();

		delegated.selectByVisibleText(value);

		
		// Save the section

		driver.findElement(By.id("btnSaveFormData")).click();

		// Close the save popup

		driver.findElement(By.xpath("(//a[@class=\"ui-dialog-titlebar-close ui-corner-all\"])[3]")).click();

		// Navigating to next section :: Supplier Network Information

		sheet = workbook.getSheetAt(3);

		Select section2 = new Select(
				driver.findElement(By.xpath("//div[@id='sectionMenu']/div/select[@class='section-menu']")));

		value = sheet.getRow(1).getCell(1).getStringCellValue();

		section2.selectByVisibleText(value);

		Thread.sleep(10000);

		driver.findElement(By.xpath("//div[@row-index='0']/div[@col-id='ContractingEntityName']")).click();

		// Adding Supplier Network Contracting Entity Name record

		// Select a contracting entity

		Select contactingEntity = new Select(driver.findElement(By.id("idContractingEntityName_0")));

		value = sheet.getRow(1).getCell(2).getStringCellValue();

		contactingEntity.selectByVisibleText(value);

		// Select a From date
		Thread.sleep(3000);

		WebElement contactFromDate = driver
				.findElement(By.xpath("(//div[@col-id=\"ProviderContractingEntityFromDate\"])[2]"));

		contactFromDate.click();
		Thread.sleep(3000);

		value = formatter.formatCellValue(sheet.getRow(1).getCell(3));

		driver.findElement(By.xpath("(//div[@col-id=\"ProviderContractingEntityFromDate\"])[2]")).sendKeys(value);

		// Click on the screen

		Thread.sleep(3000);

		driver.findElement(By.xpath("(//div[@col-id=\"FIRSTCOLUMN\"])[2]")).click();

		// Opening 2nd repeater :: Supplier Network Contracting Entity Name record

		driver.findElement(By.xpath("//div[@data-journal=\"SupplierNetwork.SupplierContractingEntityName\"]")).click();

		// Vertical scroll

		Thread.sleep(3000);

		executor.executeScript("window.scrollBy(0,250)");

		// Supplier Benefit Network repeater

		// Click the repeater

		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@value=\"Supplier Benefit Network\"]")).click();

		Thread.sleep(8000);

		// Tick MO as yes

		Actions mo = new Actions(driver);

		// Double click on element

		WebElement manualOverride = driver.findElement(By.xpath("(//div[@col-id=\"ManualOverride\"])[4]"));

		mo.doubleClick(manualOverride).perform();

		// Click on the screen

		Thread.sleep(3000);

		driver.findElement(By.xpath("(//div[@col-id=\"FIRSTCOLUMN\"])[6]")).click();

		Thread.sleep(2000);

		// Enter a Benefit Network from date

		WebElement bnFromDate = driver.findElement(By.xpath("(//div[@col-id=\"NetworkFrom\"])[2]"));

		bnFromDate.click();

		value = formatter.formatCellValue(sheet.getRow(1).getCell(4));

		driver.findElement(By.xpath("//input[contains(@id,\"NetworkFrom_0\")]")).sendKeys(value);

		// Click on the screen

		driver.findElement(By.xpath("(//div[@col-id=\"FIRSTCOLUMN\"])[6]")).click();

		Thread.sleep(5000);

		// Select a benefit network

		Actions mo1 = new Actions(driver);

		// Double click on element

		WebElement manualOverride1 = driver.findElement(By.xpath("(//div[@col-id=\"BenefitNetwork\"])[2]"));

		mo1.doubleClick(manualOverride1).perform();

		Select benefitNet = new Select(driver.findElement(By.id("idBenefitNetwork_0")));

		benefitNet.selectByIndex(1);

		// Click on the screen

		driver.findElement(By.xpath("(//div[@col-id=\"FIRSTCOLUMN\"])[6]")).click();

		Thread.sleep(2000);

		executor.executeScript("window.scrollBy(0,250)");

		// Supplier PHO repeater

		// Click the repeater

		driver.findElement(By.xpath("//input[@value=\"Supplier PHO\"]")).click();

		// Select a PHO

		Thread.sleep(1000);

		Actions pho = new Actions(driver);

		// Double click on element

		WebElement clickPho = driver.findElement(By.xpath("(//div[@col-id=\"PHOName\"])[2]"));

		pho.doubleClick(clickPho).perform();

		Thread.sleep(3000);

		Select selectPHO = new Select(driver.findElement(By.id("idPHOName_0")));

		selectPHO.selectByIndex(1);

		// Click on the screen

		driver.findElement(By.xpath("(//div[@col-id=\"FIRSTCOLUMN\"])[8]")).click();

		Thread.sleep(2000);

		// Enter a PHO From date

		WebElement phoFromDate = driver.findElement(By.xpath("(//div[@col-id=\"PHOFrom\"])[2]"));

		phoFromDate.click();

		value = formatter.formatCellValue(sheet.getRow(1).getCell(5));

		driver.findElement(By.xpath("//input[contains(@id,\"PHOFrom_0\")]")).sendKeys(value);

		// Click on the screen

		driver.findElement(By.xpath("(//div[@col-id=\"FIRSTCOLUMN\"])[8]")).click();

		Thread.sleep(2000);

		// Vertical scroll

		executor.executeScript("window.scrollBy(0,250)");

		// Supplier Other Grouping repeater

		// Click the repeater

		Thread.sleep(1000);

		driver.findElement(By.xpath("//input[@value=\"Supplier Other Grouping\"]")).click();

		Thread.sleep(1000);

		// Select Other Grouping

		Actions og = new Actions(driver);

		// Double click on element

		WebElement clickOG = driver.findElement(By.xpath("(//div[@col-id=\"OtherGroupingName\"])[2]"));

		og.doubleClick(clickOG).perform();

		Thread.sleep(3000);

		Select selectOG = new Select(driver.findElement(By.id("idOtherGroupingName_0")));

		selectOG.selectByIndex(1);

		// Click on the screen

		Thread.sleep(3000);

		driver.findElement(By.xpath("(//div[@col-id=\"FIRSTCOLUMN\"])[10]")).click();

		// Enter Other Grouping From Date

		Thread.sleep(3000);

		WebElement ogFromDate = driver.findElement(By.xpath("(//div[@col-id=\"OtherGroupingFrom\"])[2]"));

		ogFromDate.click();

		value = formatter.formatCellValue(sheet.getRow(1).getCell(6));

		driver.findElement(By.xpath("//input[contains(@id,\"OtherGroupingFrom\")]")).sendKeys(value);


		driver.findElement(By.xpath("(//div[@col-id=\"FIRSTCOLUMN\"])[10]")).click();

		Thread.sleep(2000);

		// Save the section

		driver.findElement(By.id("btnSaveFormData")).click();

		// Close the save popup

		driver.findElement(By.xpath("(//a[@class=\"ui-dialog-titlebar-close ui-corner-all\"])[3]")).click();

		// Navigating to next section :: Supplier Address and Contact Information

		sheet = workbook.getSheetAt(4);

		Select section3 = new Select(
				driver.findElement(By.xpath("//div[@id='sectionMenu']/div/select[@class='section-menu']")));

		value = sheet.getRow(1).getCell(1).getStringCellValue();

		section3.selectByVisibleText(value);

		Thread.sleep(5000);

		value = formatter.formatCellValue(sheet.getRow(1).getCell(2));

		driver.findElement(By
				.xpath("//input[@data-link=\"SupplierAddressInformation.SupplierBillingInformation.AddressFromDate\"]"))
				.sendKeys(value);

		value = sheet.getRow(1).getCell(3).getStringCellValue();

		driver.findElement(
				By.xpath("//input[@data-journal=\"SupplierAddressInformation.SupplierBillingInformation.Line1\"]"))
				.sendKeys(value);

		value = sheet.getRow(1).getCell(4).getStringCellValue();

		driver.findElement(
				By.xpath("//input[@data-journal=\"SupplierAddressInformation.SupplierBillingInformation.Line2\"]"))
				.sendKeys(value);

		value = sheet.getRow(1).getCell(5).getStringCellValue();

		driver.findElement(
				By.xpath("//input[@data-journal=\"SupplierAddressInformation.SupplierBillingInformation.Line3\"]"))
				.sendKeys(value);

		value = sheet.getRow(1).getCell(6).getStringCellValue();

		driver.findElement(
				By.xpath("//input[@data-journal=\"SupplierAddressInformation.SupplierBillingInformation.City\"]"))
				.sendKeys(value);

		Select state = new Select(driver.findElement(
				By.xpath("//select[@data-journal=\"SupplierAddressInformation.SupplierBillingInformation.State\"]")));

		value = sheet.getRow(1).getCell(7).getStringCellValue();

		state.selectByVisibleText(value);

		value = formatter.formatCellValue(sheet.getRow(1).getCell(8));

		driver.findElement(
				By.xpath("//input[@data-journal=\"SupplierAddressInformation.SupplierBillingInformation.PostalCode\"]"))
				.sendKeys(value);

		value = formatter.formatCellValue(sheet.getRow(1).getCell(9));

		driver.findElement(By
				.xpath("//input[@data-journal=\"SupplierAddressInformation.SupplierBillingInformation.PostalCode4\"]"))
				.sendKeys(value);

		value = formatter.formatCellValue(sheet.getRow(1).getCell(10));

		driver.findElement(By
				.xpath("//input[@data-journal=\"SupplierAddressInformation.SupplierBillingInformation.FIPSCountry\"]"))
				.sendKeys(value);

		value = sheet.getRow(1).getCell(11).getStringCellValue();

		driver.findElement(
				By.xpath("//input[@data-journal=\"SupplierAddressInformation.SupplierBillingInformation.Country\"]"))
				.sendKeys(value);

		value = formatter.formatCellValue(sheet.getRow(1).getCell(12));

		driver.findElement(
				By.xpath("//input[@data-journal=\"SupplierAddressInformation.SupplierBillingInformation.Latitude\"]"))
				.sendKeys(value);

		value = formatter.formatCellValue(sheet.getRow(1).getCell(13));

		driver.findElement(
				By.xpath("//input[@data-journal=\"SupplierAddressInformation.SupplierBillingInformation.Longitude\"]"))
				.sendKeys(value);


		Thread.sleep(3000);

		executor.executeScript("window.scrollBy(0,600)");

		// Click on element

		WebElement checkboxBA = driver
				.findElement(By.xpath("//input[@data-journal=\"SupplierAddressInformation.SameasBillingAddress\"]"));

		checkboxBA.click();

		value = formatter.formatCellValue(sheet.getRow(1).getCell(14));

		driver.findElement(By.xpath(
				"//input[@data-link=\"SupplierAddressInformation.SupplierCorrespondenceInformation.AddressFromDate\"]"))
				.sendKeys(value);

	
		// Save the section

		driver.findElement(By.id("btnSaveFormData")).click();

		// Close the save popup

		driver.findElement(By.xpath("(//a[@class=\"ui-dialog-titlebar-close ui-corner-all\"])[3]")).click();

// Navigate to Supplier Reimbursement Information section
		
		
		// To select section
		
			try {
				Select sections = new Select(driver.findElement(By.xpath("//div[@id=\"sectionMenu\"]/div/select")));
				sections.selectByVisibleText("Supplier Reimbursement Information");
				
			} catch (StaleElementReferenceException e) {
				Select sections = new Select(driver.findElement(By.xpath("//div[@id=\"sectionMenu\"]/div/select")));
				sections.selectByVisibleText("Supplier Reimbursement Information");
				
			}

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
			Thread.sleep(4000);
			//To select MO 
			 try 
			 {
				WebElement manualOverride13 = driver.findElement(By.xpath("//span[@title=\"false\"]"));
				Actions mo13 = new Actions(driver);
				mo13.doubleClick(manualOverride13).build().perform();
		     } 
		    catch (StaleElementReferenceException e) 
			 {
				WebElement manualOverride14 = driver.findElement(By.xpath("//div[@col-id=\"ManualOverride\" and @ style=\"width: 171px; left: 499px; text-align: center;\"]"));
				Actions mo14 = new Actions(driver);
				mo14.doubleClick(manualOverride14).build().perform();
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
			//To select ServiceCategoryFromDate
			driver.findElement(By.xpath("(//div[@col-id=\"ServiceCategoryFrom\"])[2]")).sendKeys("007/11/2022",Keys.ENTER);
			Thread.sleep(4000);
			executor.executeScript("window.scrollBy(0,300)");
			
			
			
			//Anesthesia Conversion Factor - Supplier Network Repeater
			driver.findElement(By.xpath("//input[@value=\"Anesthesia Conversion Factor - Supplier Network\"]")).click();
			executor.executeScript("window.scrollBy(0,300)");
			Thread.sleep(4000);
			
		
			//executor.executeScript("window.scrollBy(0,450)");

			//Anesthesia Conversion Factor - Supplier repeater
			driver.findElement(By.xpath("//input[@value=\"Anesthesia Conversion Factor - Supplier\"]")).click();
			executor.executeScript("window.scrollBy(0,300)");
			Thread.sleep(4000);
			

			//To select Anesthesia Repeater MO
			try {
				WebElement AnesthesiaMO1 = driver.findElement(By.xpath("(//div[@col-id=\"ManualOverride\"])[13]"));
				Actions a1=new Actions(driver);
				a1.doubleClick(AnesthesiaMO1).build().perform();
				
			} catch (StaleElementReferenceException e) {
				
				WebElement AnesthesiaMO = driver.findElement(By.xpath("(//div[@col-id=\"ManualOverride\"])[13]/span"));
				Actions a=new Actions(driver);
				a.doubleClick(AnesthesiaMO).build().perform();

			}
			Thread.sleep(4000);
			driver.findElement(By.xpath("(//div[@col-id=\"AnesthesiaConversionFactorThruDate\"])[9]")).click();
			Thread.sleep(4000);

			//driver.findElement(By.xpath("//input[@class=\"form-control calendar hasDatepicker\"]")).sendKeys("04/04/2032",Keys.ENTER);
			executor.executeScript("window.scrollBy(0,300)");

		
			
			

	         //executor.executeScript("window.scrollBy(0,300)");
			Thread.sleep(4000);
			//Benefit Network Discount - Supplier Network Repeater
			driver.findElement(By.xpath("//input[@value=\"Benefit Network Discount - Supplier Network\"]")).click();
			executor.executeScript("window.scrollBy(0,300)");
			Thread.sleep(2000);
			
			
			//Benefit Network Discount - Supplier Repeater
			driver.findElement(By.xpath("//input[@value=\"Benefit Network Discount - Supplier\"]")).click();
			Thread.sleep(5000);
			executor.executeScript("window.scrollBy(0,300)");

			//To select BenefitNetworkMO
			WebElement BenefitNetworkMO = driver.findElement(By.xpath("(//div[@col-id=\"ManualOverride\"])[16]/span"));
			Actions a=new Actions(driver);
			a.doubleClick(BenefitNetworkMO).build().perform();
			 Thread.sleep(4000);
			 Actions mo15=new Actions(driver);
		     mo15.doubleClick(driver.findElement(By.xpath("(//div[@col-id=\"ManualOverride\"])[15]/span"))).build().perform();
		     Thread.sleep(4000);
		     driver.findElement(By.xpath("(//div[@col-id=\"Whichattributestobeoverridden\"])[3]")).click();
			 Thread.sleep(2000);
	         driver.findElement(By.xpath("//button[@class=\"multiselect dropdown-toggle btn btn-default\"]")).click();
	         driver.findElement(By.xpath("//input[@value=\"multiselect-all\"]")).click();
		    Thread.sleep(4000);
	        driver.findElement(By.xpath("//button[@class=\"multiselect dropdown-toggle btn btn-default\"]")).click();

		   
		    
		
			
			
			
			Thread.sleep(2000);
			
			//Charge Master Curb - Supplier Network Repeater 
			driver.findElement(By.xpath("//input[@value=\"Charge Master Curb - Supplier Network\"]")).click();
			executor.executeScript("window.scrollBy(0,300)");
			Thread.sleep(4000);
			
			//Charge Master Curb - Supplier Repeater
			executor.executeScript("window.scrollBy(0,300)");
			driver.findElement(By.xpath("//input[@value=\"Charge Master Curb - Supplier\"]")).click();
			Thread.sleep(4000);
			executor.executeScript("window.scrollBy(0,300)");
			
			//Percentage Payment Rate - Supplier Network Repeater
			driver.findElement(By.xpath("//input[@value=\"Percentage Payment Rate - Supplier Network\"]")).click();
			Thread.sleep(4000);
			executor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
			
			
			//Percentage Payment Rate - Supplier Repeater
			driver.findElement(By.xpath("//input[@value=\"Percentage Payment Rate - Supplier\"]")).click();
			executor.executeScript("window.scrollBy(0,document.body.scrollHeight)");

		// Navigating to next section :: Supplier Remit Bank Information

		sheet = workbook.getSheetAt(5);

		Select section5 = new Select(
				driver.findElement(By.xpath("//div[@id='sectionMenu']/div/select[@class='section-menu']")));

		value = sheet.getRow(1).getCell(1).getStringCellValue();

		section5.selectByVisibleText(value);

		Thread.sleep(5000);

		Select remitType = new Select(
				driver.findElement(By.xpath("//select[@data-journal=\"SupplierRemitBankInformation.RemitType\"]")));

		remitType.selectByIndex(1);

		value = formatter.formatCellValue(sheet.getRow(1).getCell(2));

		driver.findElement(By.xpath("//input[@data-link=\"SupplierRemitBankInformation.RemitInfoFrom\"]"))
				.sendKeys(value);

		Select paymentType = new Select(
				driver.findElement(By.xpath("//select[@data-link=\"SupplierRemitBankInformation.PaymentType\"]")));

		paymentType.selectByIndex(1);

		

		value = formatter.formatCellValue(sheet.getRow(1).getCell(3));

		driver.findElement(By.xpath("//input[@data-journal=\"SupplierRemitBankInformation.AccountNumber\"]"))
				.sendKeys(value);

		value = formatter.formatCellValue(sheet.getRow(1).getCell(4));

		driver.findElement(By.xpath("//input[@data-journal=\"SupplierRemitBankInformation.RoutingNumber\"]"))
				.sendKeys(value);

		Select bankAccountType = new Select(
				driver.findElement(By.xpath("//select[@data-link=\"SupplierRemitBankInformation.BankAccountType\"]")));

		bankAccountType.selectByIndex(1);

		
		// Save the section

		driver.findElement(By.id("btnSaveFormData")).click();

		// Close the save popup

		driver.findElement(By.xpath("(//a[@class=\"ui-dialog-titlebar-close ui-corner-all\"])[3]")).click();

// Navigating to next section :: Supplier Location Information

		sheet = workbook.getSheetAt(7);

		Select section7 = new Select(

				driver.findElement(By.xpath("//div[@id='sectionMenu']/div/select[@class='section-menu']")));

		value = sheet.getRow(1).getCell(1).getStringCellValue();

		section7.selectByVisibleText(value);

		Thread.sleep(5000);

		// Select location type

		WebElement supplierLocationType = driver.findElement(By.xpath("(//div[@col-id=\"SupplierType\"])[2]"));

		supplierLocationType.click();

		Select type = new Select(driver.findElement(By.id("idSupplierType_0")));

		type.selectByIndex(3);

		// Clicking the supplier id

		WebElement supplierLocationID = driver.findElement(By.xpath("(//div[@col-id=\"SupplierLocationID\"])[2]"));

		supplierLocationID.getText();

		supplierLocationID.sendKeys(Keys.TAB);

		Thread.sleep(5000);

		// Entering Directory name

		WebElement directory = driver.findElement(By.xpath("(//div[@col-id=\"DirectoryName\"])[2]"));

		Thread.sleep(1000);

		value = sheet.getRow(1).getCell(2).getStringCellValue();

		directory.sendKeys(value);

		Thread.sleep(3000);

		try {
			directory.click();
			
		} catch (StaleElementReferenceException e) {
			directory.click();
		}

		Thread.sleep(1000);

		directory.sendKeys(Keys.TAB);

		// Entering NPI

		Thread.sleep(5000);

		WebElement npi = driver.findElement(By.xpath("(//div[@col-id=\"NPI\"])[2]"));

		value = formatter.formatCellValue(sheet.getRow(1).getCell(3));

		npi.sendKeys(value);

		Thread.sleep(1000);

		npi.click();

		Thread.sleep(1000);

		npi.sendKeys(Keys.TAB);

		Thread.sleep(5000);

		// Add Location from date

		WebElement frmDate = driver.findElement(By.xpath("(//div[@col-id=\"SupplierLocationFromDate\"])[2]"));

		frmDate.click();

		Thread.sleep(2000);

		driver.findElement(
				By.xpath("//div[@id=\"ui-datepicker-div\"]/table[@class=\"ui-datepicker-calendar\"]/tbody/tr[3]/td[2]"))
				.click();

		Thread.sleep(2000);

		
		// Save the section

		driver.findElement(By.id("btnSaveFormData")).click();

		// Close the save popup

		driver.findElement(By.xpath("(//a[@class=\"ui-dialog-titlebar-close ui-corner-all\"])[3]")).click();

		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@value=\"Supplier Location Contracting Entity Name\"]")).click();

		// Vertical scroll

		executor.executeScript("window.scrollBy(0,250)");

		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@value=\"Supplier Location Benefit Network Name\"]")).click();

	

		// Vertical scroll

		executor.executeScript("window.scrollBy(0,250)");

		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@value=\"Supplier Location PHO Name\"]")).click();

		// Vertical scroll

		executor.executeScript("window.scrollBy(0,250)");

		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@value=\"Supplier Location Other Grouping Name\"]")).click();

		
		

		// Vertical scroll

		executor.executeScript("window.scrollBy(0,250)");

		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@value=\"Taxonomy Ranking\"]")).click();

		// Vertical scroll

		executor.executeScript("window.scrollBy(0,500)");

		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@value=\"Supplier Location Billing Address\"]")).click();

		

		// Vertical scroll

		executor.executeScript("window.scrollBy(0,250)");

		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@value=\"Supplier Location Correspondence Address\"]")).click();

		// Vertical scroll

		executor.executeScript("window.scrollBy(0,1000)");

		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@value=\"Supplier Location Remit Bank Info\"]")).click();

		Thread.sleep(1000);
		

		// Navigate to Supplier location Reiumburment information section

		WebElement section = driver.findElement(By.xpath("//*[@id=\"sectionMenu\"]/div/select"));
		Select drpSelect = new Select(section);
		drpSelect.selectByVisibleText("Supplier Location Reimbursement Information");
		

// Click on all repeaters 
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Supplier Location contracting Information - Read Only
		driver.findElement(By.xpath("//input[@value=\"Supplier Location Contracting Entity Information - Read Only\"]"))
				.click();

//Supplier Location Benefit Network Information - Read Only
		WebElement Element17 = driver
				.findElement(By.xpath("//input[@value=\"Supplier Location Benefit Network Information - Read Only\"]"));
		js.executeScript("arguments[0].scrollIntoView(true);", Element17);
		Element17.click();
//Taxonomy Ranking - Read Only
		WebElement Element18 = driver.findElement(By.xpath("//input[@value=\"Taxonomy Ranking - Read Only\"]"));
		js.executeScript("arguments[0].scrollIntoView(true)", Element18);
		Element18.click();
		js.executeScript("window.scrollBy(0,500)");
//Reimbursement Table - Professional
		driver.findElement(By.xpath("//input[@value=\"Reimbursement Table - Professional\"]")).click();
//Reimbursement Table - ASC
		driver.findElement(By.xpath("//input[@value=\"Reimbursement Table - ASC\"]")).click();
//Reimbursement Table - Institutional
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value=\"Reimbursement Table - Institutional\"]")).click();
		js.executeScript("window.scrollBy(0,500)");
		driver.findElement(
				By.xpath("//input[@value=\"Percentage Payment Rate Information - Supplier Location - Professional\"]"))
				.click();
//Percentage Payment Rate Information - Supplier Location - ASC
		driver.findElement(
				By.xpath("//input[@value=\"Percentage Payment Rate Information - Supplier Location - ASC\"]")).click();
//Percentage Payment Rate Information - Supplier Location - Institutional
		driver.findElement(
				By.xpath("//input[@value=\"Percentage Payment Rate Information - Supplier Location - Institutional\"]"))
				.click();
		js.executeScript("window.scrollBy(0,500)");
//Category Rates - Supplier Location
		driver.findElement(By.xpath("//input[@value=\"Category Rates - Supplier Location\"]")).click();
//Anesthesia Conversion Factor - Supplier Location
		driver.findElement(By.xpath("//input[@value=\"Anesthesia Conversion Factor - Supplier Location\"]")).click();
//Charge Master Curb - Supplier Location
		driver.findElement(By.xpath("//input[@value=\"Charge Master Curb - Supplier Location\"]")).click();
		js.executeScript("window.scrollBy(0,500)");
//Benefit Network Percentage Payment Rate - Supplier Location
		driver.findElement(By.xpath("//input[@value=\"Benefit Network Percentage Payment Rate - Supplier Location\"]"))
				.click();
//Observation Services - Supplier Location
		driver.findElement(By.xpath("//input[@value=\"Observation Services - Supplier Location\"]")).click();
		js.executeScript("window.scrollBy(0,500)");
//Alternate Percentage Payment Rate - Supplier Location
		driver.findElement(By.xpath("//input[@value=\"Alternate Percentage Payment Rate - Supplier Location\"]"))
				.click();

//APR-DRG Base Rate
		driver.findElement(By.xpath("//input[@value=\"APR-DRG Base Rate\"]")).click();
		js.executeScript("window.scrollBy(0,500)");
//EAPG Base Rate
		driver.findElement(By.xpath("//input[@value=\"EAPG Base Rate\"]")).click();
		js.executeScript("window.scrollBy(0,500)");
//RCC Factor
		driver.findElement(By.xpath("//input[@value=\"RCC Factor\"]")).click();
		
		
		

	}

}
