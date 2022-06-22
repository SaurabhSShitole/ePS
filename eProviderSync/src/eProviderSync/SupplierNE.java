package eProviderSync;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class SupplierNE {

	public static void main(String[] args) throws InterruptedException, IOException {
		

        // Instantiate a ChromeDriver class.
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
		driver.get("https://bcbsne-epscfg.simplifyhealthcare.com/ConsumerAccount/Foundationsearch");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        
       String excelfilePath=".\\datafiles\\SupplierData1 (2).xlsx";
                              
        FileInputStream data = new FileInputStream(excelfilePath);
        
     // Creating a workbook
        XSSFWorkbook workbook = new XSSFWorkbook(data);
        
        String value;
        XSSFSheet sheet;
        
        //Used to pick int values from the excel
        DataFormatter formatter = new DataFormatter();
        
        
        //Append date to the screenshots
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
        

        driver.findElement(By.xpath("//td[@id='btnPortfolioSearchNew']")).click();
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
        

        /*
        // If Tax Id is already present 
          WebElement open =
          driver.findElement(By.xpath("//div[text()=\"Open\"]")); 
          if (open != null)
           {
          open.click();
           }
        
*/
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
        

        // Take the screenshot
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File(
                                      "D:\\Caputred Images\\Create Supplier\\"+"_"+dtf.format(now)+".png"));

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
        
        Select credential = new Select(driver.findElement(By.xpath("//div/select[@data-link=\"SupplierInformation.CredentialedStatus\"]")));
        value = sheet.getRow(1).getCell(2).getStringCellValue();
        credential.selectByVisibleText(value);
        

        // Select Delegated Credentialing status
        Select delegated = new Select(driver.findElement(By.xpath("//div/select[@data-link=\"SupplierInformation.DelegatedCredentialing\"]")));
        value = sheet.getRow(1).getCell(3).getStringCellValue();
        delegated.selectByVisibleText(value);
       

        // Take the screenshot
        File screenshotFile1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile1, new File("D:\\Caputred Images\\Supplier Information\\"+"_"+dtf.format(now)+".png"));
        
        // Save the section
        driver.findElement(By.id("btnSaveFormData")).click();

        // Close the save popup
       
        driver.findElement(By.xpath("(//a[@class=\"ui-dialog-titlebar-close ui-corner-all\"])[3]")).click();
        

        // Navigating to next section :: Supplier Network Information
        sheet = workbook.getSheetAt(3);
        Select section2 = new Select(driver.findElement(By.xpath("//div[@id='sectionMenu']/div/select[@class='section-menu']")));
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
        
        WebElement contactFromDate = driver
                                      .findElement(By.xpath("(//div[@col-id=\"ProviderContractingEntityFromDate\"])[2]"));
        contactFromDate.click();
        
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

        // Take the screenshot
        File screenshotFile3 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile3, new File(
                                      "D:\\Caputred Images\\SuplierNetwork1\\"+"_"+dtf.format(now)+".png"));
        
        // Vertical scroll
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

        
        // Take the screenshot
        File screenshotFile4 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile4, new File(
                                      "D:\\Caputred Images\\SupplierNetwork2\\"+"_"+dtf.format(now)+".png"));
     // Click on the screen
        driver.findElement(By.xpath("(//div[@col-id=\"FIRSTCOLUMN\"])[10]")).click();
        Thread.sleep(2000);

        // Save the section
        driver.findElement(By.id("btnSaveFormData")).click();

        // Close the save popup
        
        driver.findElement(By.xpath("(//a[@class=\"ui-dialog-titlebar-close ui-corner-all\"])[3]")).click();

        // Navigating to next section :: Supplier Address and Contact Information
        sheet = workbook.getSheetAt(4);
        Select section3 = new Select(driver.findElement(By.xpath("//div[@id='sectionMenu']/div/select[@class='section-menu']")));
        value = sheet.getRow(1).getCell(1).getStringCellValue();
        section3.selectByVisibleText(value);
        Thread.sleep(5000);

        value = formatter.formatCellValue(sheet.getRow(1).getCell(2));
        driver.findElement(By.xpath("//input[@data-link=\"SupplierAddressInformation.SupplierBillingInformation.AddressFromDate\"]")).sendKeys(value);
        
        value = sheet.getRow(1).getCell(3).getStringCellValue();
        driver.findElement(By.xpath("//input[@data-journal=\"SupplierAddressInformation.SupplierBillingInformation.Line1\"]")).sendKeys(value);
       

        value = sheet.getRow(1).getCell(4).getStringCellValue();
        driver.findElement(By.xpath("//input[@data-journal=\"SupplierAddressInformation.SupplierBillingInformation.Line2\"]")).sendKeys(value);
        

        value = sheet.getRow(1).getCell(5).getStringCellValue();
        driver.findElement(By.xpath("//input[@data-journal=\"SupplierAddressInformation.SupplierBillingInformation.Line3\"]")).sendKeys(value);
       

        value = sheet.getRow(1).getCell(6).getStringCellValue();
        driver.findElement( By.xpath("//input[@data-journal=\"SupplierAddressInformation.SupplierBillingInformation.City\"]")).sendKeys(value);
       

        Select state = new Select(driver.findElement(By.xpath("//select[@data-journal=\"SupplierAddressInformation.SupplierBillingInformation.State\"]")));
        value = sheet.getRow(1).getCell(7).getStringCellValue();
        state.selectByVisibleText(value);
        

        value = formatter.formatCellValue(sheet.getRow(1).getCell(8));
        driver.findElement(By.xpath("//input[@data-journal=\"SupplierAddressInformation.SupplierBillingInformation.PostalCode\"]")).sendKeys(value);
        

        value = formatter.formatCellValue(sheet.getRow(1).getCell(9));
        driver.findElement(By.xpath("//input[@data-journal=\"SupplierAddressInformation.SupplierBillingInformation.PostalCode4\"]")).sendKeys(value);
       

        value = formatter.formatCellValue(sheet.getRow(1).getCell(10));
        driver.findElement(By.xpath("//input[@data-journal=\"SupplierAddressInformation.SupplierBillingInformation.FIPSCountry\"]")).sendKeys(value);
        

        value = sheet.getRow(1).getCell(11).getStringCellValue();
        driver.findElement( By.xpath("//input[@data-journal=\"SupplierAddressInformation.SupplierBillingInformation.Country\"]")).sendKeys(value);
        

        value = formatter.formatCellValue(sheet.getRow(1).getCell(12));
        driver.findElement(By.xpath("//input[@data-journal=\"SupplierAddressInformation.SupplierBillingInformation.Latitude\"]")).sendKeys(value);
        

        value = formatter.formatCellValue(sheet.getRow(1).getCell(13));
        driver.findElement(By.xpath("//input[@data-journal=\"SupplierAddressInformation.SupplierBillingInformation.Longitude\"]")).sendKeys(value);
        
        
     // Take the screenshot
        File screenshotFile5 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile5, new File(
                                      "D:\\Caputred Images\\SupplierContactInfo1\\"+"_"+dtf.format(now)+".png"));

        // Vertical scroll
        Thread.sleep(3000);
        executor.executeScript("window.scrollBy(0,600)");

        // Click on element
        WebElement checkboxBA = driver.findElement(By.xpath("//input[@data-journal=\"SupplierAddressInformation.SameasBillingAddress\"]"));
        checkboxBA.click();

        value = formatter.formatCellValue(sheet.getRow(1).getCell(14));
        driver.findElement(By.xpath("//input[@data-link=\"SupplierAddressInformation.SupplierCorrespondenceInformation.AddressFromDate\"]")) .sendKeys(value);
        

        // Take the screenshot
        File screenshotFile6 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile6, new File(
                                      "D:\\Caputred Images\\SupplierContactInfo2\\"+"_"+dtf.format(now)+".png"));
        // Save the section
        driver.findElement(By.id("btnSaveFormData")).click();

        // Close the save popup
        
        driver.findElement(By.xpath("(//a[@class=\"ui-dialog-titlebar-close ui-corner-all\"])[3]")).click();

        // Navigating to next section :: Supplier Remit Bank Information
        sheet = workbook.getSheetAt(5);
        Select section5 = new Select(driver.findElement(By.xpath("//div[@id='sectionMenu']/div/select[@class='section-menu']")));
        value = sheet.getRow(1).getCell(1).getStringCellValue();
        section5.selectByVisibleText(value);
        Thread.sleep(5000);

        Select remitType = new Select(driver.findElement(By.xpath("//select[@data-journal=\"SupplierRemitBankInformation.RemitType\"]")));
        remitType.selectByIndex(1);
        

        value = formatter.formatCellValue(sheet.getRow(1).getCell(2));
        driver.findElement(By.xpath("//input[@data-link=\"SupplierRemitBankInformation.RemitInfoFrom\"]")).sendKeys(value);

        
        Select paymentType = new Select(driver.findElement(By.xpath("//select[@data-link=\"SupplierRemitBankInformation.PaymentType\"]")));
        paymentType.selectByIndex(1);
        
     // Take the screenshot
        File screenshotFile7 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile7, new File(
                                      "D:\\Caputred Images\\SupplierRemitBankInfo1\\"+"_"+dtf.format(now)+".png"));

        
        value = formatter.formatCellValue(sheet.getRow(1).getCell(3));
        driver.findElement(By.xpath("//input[@data-journal=\"SupplierRemitBankInformation.AccountNumber\"]")).sendKeys(value);

        
        value = formatter.formatCellValue(sheet.getRow(1).getCell(4));
        driver.findElement(By.xpath("//input[@data-journal=\"SupplierRemitBankInformation.RoutingNumber\"]")).sendKeys(value);

       
        Select bankAccountType = new Select( driver.findElement(By.xpath("//select[@data-link=\"SupplierRemitBankInformation.BankAccountType\"]")));
        bankAccountType.selectByIndex(1);
        
     // Take the screenshot
        File screenshotFile11 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile11, new File(
                                      "D:\\Caputred Images\\SupplierRemitBankInfo2\\"+"_"+dtf.format(now)+".png"));
        
        // Save the section
        driver.findElement(By.id("btnSaveFormData")).click();

        // Close the save popup
        
        driver.findElement(By.xpath("(//a[@class=\"ui-dialog-titlebar-close ui-corner-all\"])[3]")).click();

        // Navigating to next section :: Supplier Location Information
        sheet = workbook.getSheetAt(6);
        Select section6 = new Select(
                                      driver.findElement(By.xpath("//div[@id='sectionMenu']/div/select[@class='section-menu']")));
        value = sheet.getRow(1).getCell(1).getStringCellValue();
        section6.selectByVisibleText(value);
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
        directory.click();
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
        driver.findElement(By.xpath("//div[@id=\"ui-datepicker-div\"]/table[@class=\"ui-datepicker-calendar\"]/tbody/tr[3]/td[2]")).click();
        Thread.sleep(2000);

        // Take the screenshot
        File screenshotFile8 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile8, new File( "D:\\Caputred Images\\SupplierLocationInfo1\\"+"_"+dtf.format(now)+".png"));

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

        // Take the screenshot
        File screenshotFile9 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile9, new File("D:\\Caputred Images\\SupplierLocationInfo2\\"+"_"+dtf.format(now)+".png"));

        // Vertical scroll
        
        executor.executeScript("window.scrollBy(0,250)");

        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@value=\"Supplier Location PHO Name\"]")).click();

        // Vertical scroll
        
        executor.executeScript("window.scrollBy(0,250)");

        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@value=\"Supplier Location Other Grouping Name\"]")).click();
        
     // Take the screenshot
        File screenshotFile12 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile12, new File(
                                      "D:\\Caputred Images\\SupplierLocationInfo3\\"+"_"+dtf.format(now)+".png"));

        // Vertical scroll
        
        executor.executeScript("window.scrollBy(0,250)");

        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@value=\"Taxonomy Ranking\"]")).click();

        // Vertical scroll
        
        executor.executeScript("window.scrollBy(0,500)");

        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@value=\"Supplier Location Billing Address\"]")).click();
        
        // Take the screenshot
        File screenshotFile13 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile13, new File(
                                      "D:\\Caputred Images\\SupplierLocationInfo4\\"+"_"+dtf.format(now)+".png"));

        // Vertical scroll
        
        executor.executeScript("window.scrollBy(0,250)");

        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@value=\"Supplier Location Correspondence Address\"]")).click();

        // Vertical scroll
        
        executor.executeScript("window.scrollBy(0,800)");

        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@value=\"Supplier Location Remit Bank Info\"]")).click();
        
        Thread.sleep(1000);
        // Take the screenshot
        File screenshotFile10 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile10, new File(
                                      "D:\\Caputred Images\\SupplierLocationInfo5\\"+"_"+dtf.format(now)+".png"));



         

	}}
