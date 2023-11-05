package deposyt;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import AccessLevel.Data;

public class ts extends Data{
	
	@Test
	public void Line58N59() throws Exception 
	{
		//Cases  create campaign button displayed on dashboard if the access is restricted &  created by user name shows wrong for admin user
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//Actions action = new Actions(driver);
		//JavascriptExecutor jse = (JavascriptExecutor)driver;
		
		String a = driver.findElement(By.id("globalcall_dialer")).getAttribute("data-companyid");
		int CID = Integer.parseInt(a);
		System.out.println(CID);
		
	}
}
