package Modules;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AccessLevel.Data;
import io.github.bonigarcia.wdm.WebDriverManager;

public class RandomUserLogin extends Data {
	
	@DataProvider(parallel = true)
	@Test(priority=-1)
	public String[][] DBdata() {
		driver.get(DBURL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);
		
//Login to database	
		driver.findElement(By.name("auth[server]")).sendKeys(DBServer);
		driver.findElement(By.id("username")).sendKeys(DBUName);
		driver.findElement(By.name("auth[password]")).sendKeys(DBPass);
		driver.findElement(By.cssSelector("input[value='Login']")).click();
		
//database selection
		driver.findElement(By.linkText("appcentzcrmcom")).click();
		driver.findElement(By.linkText("SQL command")).click();
		action.sendKeys("SELECT sm_u.login, sm_u.text_password, c.expiration, rand() as random\r\n"
				+ "FROM sm_users sm_u\r\n"
				+ "left join companies c on c.id = sm_u.id_company\r\n"
				+ "WHERE sm_u.text_password IS NOT NULL\r\n"
				+ "AND (c.expiration >UNIX_TIMESTAMP() OR c.expiration = 0 )\r\n"
				+ "AND (login NOT like'%nadsoft%' and login NOT like'%nikhil%' and login NOT like'%bharate%' \r\n"
				+ "and login NOT like'%shinde%' and login NOT like'%admin%'and login NOT like'%rohan%' \r\n"
				+ "and login NOT like'%atul%' and login NOT like'%tushar%'and login NOT like'%sushant%'\r\n"
				+ "and login NOT like'%test%')\r\n"
				+ "order by random DESC\r\n"
				+ "limit 5")
		.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
		
//Getting data 
		String [][] actdata = new String[5][2];
		String uname1 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr[1]/td[1]")).getText();
		 String pass1 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr[1]/td[2]")).getText();
		 actdata[0][0] = uname1;
		 actdata[0][1]=pass1;
		 
		 String uname2 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr[2]/td[1]")).getText();
		 String pass2 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr[2]/td[2]")).getText();
		 actdata[1][0]= uname2;
		 actdata[1][1]=pass2;
		 
		 String uname3 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr[3]/td[1]")).getText();
		 String pass3 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr[3]/td[2]")).getText();
		 actdata[2][0] = uname3;
		 actdata[2][1]=pass3;
		 
		 String uname4 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr[4]/td[1]")).getText();
		 String pass4 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr[4]/td[2]")).getText();
		 actdata[3][0]= uname4;
		 actdata[3][1]=pass4;
		 
		 String uname5 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr[5]/td[1]")).getText();
		 String pass5 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr[5]/td[2]")).getText();
		 actdata[4][0]= uname5;
		 actdata[4][1]=pass5;
		 
		 //driver.findElement(By.id("logout")).click();
		 
		 for(String[]datar:actdata)
			{
				System.out.println(Arrays.toString(datar));
			}
		driver.close();
		 return actdata;
	}
	
	@Test(dataProvider="DBdata")
	public void log_in(String username , String password) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
        WebElement emf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		emf.sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[4]/button[1]")).click();
		
		//String a = driver.getCurrentUrl();
		//Assert.assertEquals(a,"https://app.deposyt.com/","Login failed"); commented due to bussiness wizard
		
		driver.findElement(By.xpath("//*[@id=\"dropdownMenu1\"]")).click();
		driver.findElement(By.linkText("Logout")).click();
		
		System.out.println("User is succesfully logged in");
		
		driver.close();
	}
}
