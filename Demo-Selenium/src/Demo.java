import java.time.Duration;
import java.util.Set;

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

public class Demo {

	public static void main(String[] args) throws InterruptedException  {

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
		System.out.println("Provider Details - "+ele.getAccessibleName());
		
		Actions a = new Actions(driver);
		a.doubleClick(ele).build().perform();
		Thread.sleep(7000);
		
		driver.findElement(By.xpath("//*[@id=\"PRA2538Section101263\"]")).click();
		Thread.sleep(7000);		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,800)");
		
		driver.findElement(By.xpath("//input[@value=\"Practitioner Role Contract Information\"]")).click();
		driver.findElement(By.xpath("//input[@value=\"Practitioner Role Specialty Info\"]")).click();
		js.executeScript("window.scrollBy(0,800)");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@value=\"Institution Location Practicing Address Information\"]")).click();
		
		//driver.quit();
	
	}

}
