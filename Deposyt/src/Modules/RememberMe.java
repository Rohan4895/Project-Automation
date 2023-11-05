package Modules;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RememberMe{
	
	public static void main(String[] args) throws Exception {
		
		WebDriverManager.chromedriver().browserVersion("112.0");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		driver.manage().window().maximize();
		
		driver.get("https://app.deposyt.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.id("email")).sendKeys("rohan@nadsoftdesign.com");
		driver.findElement(By.id("password")).sendKeys("rohankokare");
		driver.findElement(By.id("remember-me")).click();
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[4]/button[1]")).click();
		String a = driver.getTitle();
		Assert.assertEquals(a,"Dashboard :: deposyt.com");
		driver.navigate().to("https://app.deposyt.com/index.php?m=usersmgmt&d=list");
		driver.findElement(By.cssSelector("tr:first-of-type>td>div>a#usertableoptions")).click();
		driver.findElement(By.cssSelector("div.open>div.dropdown-menu.useroptions>a:first-of-type")).click();
		driver.switchTo().alert().accept();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"dropdownMenu1\"]")).click();
		driver.findElement(By.linkText("Re-Login Back")).click();
	
		String win1 = driver.getWindowHandle();
		jse.executeScript("window.open('about:blank','_blank');");
		String win2 = driver.getWindowHandle();
		//Set<String> handles = driver.getWindowHandles();
		driver.switchTo().window(win1).close();
		//driver.close();
		driver.switchTo().window(win2);
		driver.get("https://app.deposyt.com/");
		//driver.findElement(By.xpath("//*[@id=\"dropdownMenu1\"]")).click();
		//driver.findElement(By.linkText("Logout")).click();
	}
}
