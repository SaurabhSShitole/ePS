import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Loop {

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
        
	
			driver.findElement(By.xpath("//*[@id=\"btnPortfolioSearchNew\"]/div")).click();
		
		
	}

}   
