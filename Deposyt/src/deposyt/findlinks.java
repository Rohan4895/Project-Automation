package deposyt;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class findlinks {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3)); 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://app.deposyt.com/index.php?m=account");
		 WebElement emf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
			emf.sendKeys("tushargadade061@gmail.com");
			driver.findElement(By.id("password")).sendKeys("tushar061");
			driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[4]/button[1]")).click();
			String a = driver.getTitle();
			Assert.assertEquals(a,"Dashboard :: centz.com","Login failed");
			
		List<WebElement> links=  driver.findElements(By.tagName("a"));
			for(int i=0;i<=links.size();i++) {
				System.out.println(links.get(i).getAttribute("href"));
			}
	}

}
