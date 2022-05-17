import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class NewPractitioner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60000));
		
		driver.get("https://ok-epscfg.simplifyhealthcare.com/DashBoard/Index");
		driver.findElement(By.xpath("//input[@name='UserName']")).sendKeys("saurabh.shitole");
		driver.findElement(By.xpath("//input[@name='Password']")).sendKeys("Summer@2018#", Keys.ENTER);
		System.out.println(driver.getTitle());
		driver.findElement(By.xpath("//a[@id='FoundationTemplate']")).click();
		
		WebElement ele = driver.findElement(By.xpath("//tr[@id=\"328450\"]"));
		System.out.println("Provider Details - " + ele.getAccessibleName());

		Actions a = new Actions(driver);
		a.doubleClick(ele).build().perform();

		WebElement License = driver.findElement(By.xpath("//select[@data-link=\"PractitionerInformation.LicensingType\"]"));
		Select s = new Select(License);
		s.selectByVisibleText("AuD");

		driver.findElement(By.xpath("//input[@data-link=\"PractitionerInformation.BirthDate\"]")).sendKeys("004/04/1999",Keys.ENTER);
		//driver.findElement(By.xpath("//a[contains(.,'3')]")).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,200)");

		driver.findElement(By.xpath("//input[@data-link=\"PractitionerInformation.FromDate\"]")).sendKeys("005/05/2022",Keys.ENTER);
		//driver.findElement(By.xpath("//a[contains(.,'16')]")).click();

		WebElement suffix = driver.findElement(By.xpath("//select[@data-link=\"PractitionerInformation.Suffix\"]"));
		Select s1 = new Select(suffix);
		s1.selectByValue("JR");

		WebElement gender = driver.findElement(By.xpath("//select[@data-link=\"PractitionerInformation.Gender\"]"));
		Select g = new Select(gender);
		g.selectByValue("Male");
		
		
		//Practitioner License Information
		WebElement sections = driver.findElement(By.xpath("//select[1][@class=\"section-menu\"]"));
		Select Psections=new Select(sections);
		Psections.selectByVisibleText("Practitioner License Information");
		
		WebElement LS = driver.findElement(By.xpath("//div[@comp-id=\"124\"]"));
		a.doubleClick(LS).build().perform();
		driver.findElement(By.xpath("//option[contains(.,'AK')]")).click();
		driver.findElement(By.xpath("//div[@comp-id=\"125\"]")).sendKeys("01999",Keys.ENTER);
		
		driver.findElement(By.xpath("//div[@comp-id=\"126\"]")).sendKeys("004/04/2040",Keys.ENTER);
		
	
		//Practitioner contact info
		Psections.selectByVisibleText("Practitioner Contact Information");
		driver.findElement(By.xpath("//input[@name=\"PRA2538TextBox101355931035\"]")).sendKeys("9779895956",Keys.ENTER);
		driver.findElement(By.xpath("//input[@name=\"PRA2538TextBox101221931035\"]")).sendKeys("9765",Keys.ENTER);
		driver.findElement(By.xpath("//input[@name=\"PRA2538TextBox101356931035\"]")).sendKeys("6789797687",Keys.ENTER);
		driver.findElement(By.xpath("//input[@name=\"PRA2538TextBox101357931035\"]")).sendKeys("6878",Keys.ENTER);
		driver.findElement(By.xpath("//input[@name=\"PRA2538TextBox101226931035\"]")).sendKeys("Auto@gmail.com",Keys.ENTER);
		driver.findElement(By.xpath("//input[@name=\"PRA2538TextBox101227931035\"]")).sendKeys("8768768986",Keys.ENTER);
		
		js.executeScript("window.scrollBy(0,200)");
		WebElement contacttype = driver.findElement(By.xpath("//div[@comp-id=\"264\"]"));
		a.doubleClick(contacttype).build().perform();
		
		WebElement type=driver.findElement(By.xpath("//select[@id=\"idTypeofContact_0\"]"));
		Select t=new Select(type);
		t.selectByValue("Administrator");
		
		//Practitioner Specialty Information
    
        Psections.selectByVisibleText("Practitioner Specialty Information");
		driver.findElement(By.xpath("//span[@title=\"Add new empty row\"]")).click();
		driver.findElement(By.xpath("//div[@comp-id=\"407\"]")).click();
		WebElement specialtycode = driver.findElement(By.xpath("//select[@id=\"idSpecialtyCode_5\"]"));
		Select Scode=new Select(specialtycode);
		Scode.selectByVisibleText("General Practice");
		
		driver.findElement(By.xpath("//div[@comp-id=\"408\"]")).click();
		WebElement specialtyrank = driver.findElement(By.xpath("//select[@id=\"idSpecialtyRank_5\"]"));
		Select rank=new Select(specialtyrank);
		rank.selectByVisibleText("Primary");
		
		driver.findElement(By.xpath("//div[@comp-id=\"409\"]")).sendKeys("004/04/2022",Keys.ENTER);
		
		//Practitioner ROle location info section
		Psections.selectByVisibleText("Practitioner Role Location Information");
		driver.findElement(By.xpath("//button[@class=\"btn btn-sm but-align pull-left btnSearchSupplier\"]")).click();
		driver.findElement(By.xpath("//button[@id=\"btnAdvanceSearchPopUp\"]")).click();
		driver.findElement(By.xpath("//input[@id=\"txtsearchValue2\"]")).sendKeys("878678768",Keys.ENTER);
        driver.findElement(By.xpath("//button[@id=\"btnAddCriteriaToProductSearch\"]")).click();
        driver.findElement(By.xpath("//span[@class=\"ag-selection-checkbox\"]")).click();
        
        //for sync data button and from date
        //driver.findElement(By.xpath("//button[@id=\"btnSyncData\"]")).click();
        //driver.findElement(By.xpath("//div[@comp-id=\"1710\"]")).sendKeys("004/04/2022",Keys.ENTER);
	
	}

}
