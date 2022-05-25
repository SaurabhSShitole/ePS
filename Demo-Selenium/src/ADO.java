import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ADO {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60000));

		driver.get("https://dev.azure.com/SimplifyHealthcare/");	
		
		driver.findElement(By.xpath("//input[@data-report-event=\"Signin_Email_Phone_Skype\"]")).sendKeys("saurabh.shitole@simplifyhealthcare.com",Keys.ENTER);
		driver.findElement(By.xpath("//input[@class=\"form-control input ext-input text-box ext-text-box\"]")).sendKeys("Pass@123",Keys.ENTER);
		driver.findElement(By.xpath("//div[@class=\"table-cell text-left content\"]")).click();
		driver.findElement(By.xpath("//a[@href=\"/SimplifyHealthcare/ePS%20Oklahoma\"]")).click();
		driver.findElement(By.xpath("//a[@aria-label=\"Boards\"]")).click();
		
		driver.findElement(By.xpath("//a[@href=\"/SimplifyHealthcare/ePS%20Oklahoma/_workitems/edit/215802/\"]")).click();
		driver.findElement(By.xpath("//span[@class=\"page-button icon bowtie-icon bowtie-navigate-history\"]")).click();
		
		List<WebElement> ST = driver.findElements(By.xpath("//div[@class=\"state\"]"));
		List<WebElement> name = driver.findElements(By.xpath("//span[@class=\"identity-picker-resolved-name\"]"));
		driver.findElement(By.xpath("//a[contains(.,'Show all')]")).click();
		
		for (WebElement state : ST) 
		{
			
			System.out.println(state.getText());
			System.err.println("================================");
		}		
	}

}
