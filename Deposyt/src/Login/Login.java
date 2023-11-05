package Login;


import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login{
	protected WebDriver driver;
	
	@Parameters({"bname","url"})
	@BeforeTest
	public void invokebrowser(String bname, String url) {
		switch(bname) {
		case"chrome":
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
			break;
		case"edge":
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
			break;
		case"firefox":
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
			break;
		default:
			System.err.println("invalid browser name");
			break;
		}	
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	@Test(dataProvider="actualdata", dataProviderClass= darray.class)
	public void log_in(String username , String password) throws InterruptedException {
		
// -- valid username,valid password
		
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
        WebElement emf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		emf.sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[4]/button[1]")).click();
		String a = driver.getTitle();
		Assert.assertEquals(a,"Dashboard :: deposyt.com","Login failed");
		driver.findElement(By.xpath("//*[@id=\"dropdownMenu1\"]")).click();
		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(2000);
		System.out.println("User is Succesfully logged in");
		
	}
	
	@Test(priority = 2)
	public void invalidcredentials()
	{	
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(3));
		
// -- Logo visibility
		try {
		driver.findElement(By.xpath("//*[@id=\"spacious-container\"]/div[1]/div[1]/img")).isDisplayed();
		System.out.println("Logo is displayed");
		}catch(Exception NoSuchElement) {
			System.out.println("Logo is not displayed");
		}
// -- valid username,invalid password
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		driver.findElement(By.id("email")).sendKeys("tushargadade061@gmail.com");
		driver.findElement(By.id("password")).sendKeys("tushar161");
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[4]/button[1]")).click();
		String b = driver.findElement(By.xpath("//*[@id=\"spacious-container\"]/div[1]/div[2]/div[1]/div[1]")).getText();
		Assert.assertEquals(b,"You have entered an incorrect username or password.");
		System.out.println("valid username,invalid password- passed");
		
// -- invalid username, valid password 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		driver.findElement(By.id("email")).sendKeys("tushar061@gmail.com");
		driver.findElement(By.id("password")).sendKeys("tushar061");
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[4]/button[1]")).click();
		String a = driver.findElement(By.xpath("//*[@id=\"spacious-container\"]/div[1]/div[2]/div[1]/div[1]")).getText();
		Assert.assertEquals(a,"You have entered an incorrect username or password.");
		System.out.println("invalid username, valid password- passed");

// -- invalid username,invalid password
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		driver.findElement(By.id("email")).sendKeys("tushar061@gmail.com");
		driver.findElement(By.id("password")).sendKeys("tushar");
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[4]/button[1]")).click();
		String c = driver.findElement(By.xpath("//*[@id=\"spacious-container\"]/div[1]/div[2]/div[1]/div[1]")).getText();
		Assert.assertEquals(c,"You have entered an incorrect username or password.");
		System.out.println("invalid username,invalid password- passed");
	}
	
	@Test(priority =3)
	public void forgotpassword() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.linkText("Forgot password?")).click();
		Thread.sleep(3000);
		driver.findElement(By.name("email")).sendKeys("rohan@nadsoftdesign1.com");
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[2]/button[1]")).click();
		Thread.sleep(1000);
		try {
			String d = driver.findElement(By.xpath("//*[@id=\"spacious-container\"]/div[1]/div[2]/div[1]/div[1]")).getText();
			System.out.println(d);
		}catch(Exception e) {}
		try {
		String f= driver.findElement(By.cssSelector("article.alertify-log")).getText();
		System.out.println(f);
		}catch(Exception e) {}
	
	}

	@Test(priority= 1)
	public void rememberme() {
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.id("email")).sendKeys("tushargadade061@gmail.com");
		driver.findElement(By.id("password")).sendKeys("tushar061");
		driver.findElement(By.id("remember-me")).click();
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[4]/button[1]")).click();
		String a = driver.getTitle();
		Assert.assertEquals(a,"Dashboard :: deposyt.com");
		driver.findElement(By.xpath("//*[@id=\"dropdownMenu1\"]")).click();
		driver.findElement(By.linkText("Logout")).click();
		System.out.println("Remember me Checkbox checked");
	}
	
	@Parameters("url")
	@Test(priority = 6)
	public void TC(String url) {
		
		driver.navigate().to(url);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.xpath("//a[contains(text(),'Terms and Conditions')]")).click();
		
	    ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
	    driver.switchTo().window(newTab.get(1));
	    
		String URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, "https://www.deposyt.com/terms/" );
		
		System.out.println("User is navigated to "+URL);
		driver.quit();
		
	}
	
}
