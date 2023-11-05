package Modules;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import AccessLevel.Data;

public class Triggers extends Data {

	@Test
	public void trigger() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		Actions action = new Actions(driver);

		System.out.println();
		System.out.println("Testing Triggers ----->");	

		WebElement e = driver.findElement(By.cssSelector("div.hidden.sidebar-bg-color"));
		action.moveToElement(e).perform();
		jse.executeScript("document.querySelector('html > body > div > div:nth-of-type(2)').scrollTop=400");
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/nav/div[7]/div[2]/div[5]/div/a")).click();

		// Adding new trigger
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/div/button")).click();
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[2]/div/div/div[3]/div[1]/div/div[2]")).click();
		action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();

		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div#filter_fields_1424 > div > div"))));
		Thread.sleep(2000);
		action.sendKeys(Keys.TAB).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(1000);
		action.sendKeys(Keys.TAB).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();

		driver.findElement(By.cssSelector("div.flex.gap-3.actioncard > div:nth-of-type(3) > div:nth-of-type(2)")).click();
		jse.executeScript("document.querySelector('div[data-position=\"1\"]>div>div>ul.list').scrollTop=300");
		driver.findElement(By.cssSelector("div[data-position=\"1\"]>div>div>ul.list>li[data-value=\"10\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Create An Email")).click();
		Thread.sleep(3000);
		driver.findElement(By.name("emailsubject")).sendKeys("This is test mail from Triggers 110022");
		Thread.sleep(6000);
		action.sendKeys(Keys.TAB).sendKeys("Test mail from Triggers").build().perform();
		driver.findElement(By.linkText("Done")).click();
		String Suc = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div")).getText();
		System.out.println(Suc);
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[2]/div/div/div[1]/a")).click();
		driver.findElement(By.id("savetriggermainbtn")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Exit")).click();
		String asd = driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[2]/div/div/div[1]/div[1]/h1")).getText();
		System.out.println("Trigger updated: "+asd);
		String Tristat = driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[2]/div/div/div[1]/div[2]/div[2]/div/h1")).getText();
		System.out.println("Trigger status: "+Tristat);
	}

	@Test
	public void AppointmentTrigger() throws InterruptedException 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		Actions action = new Actions(driver);

		System.out.println();
		System.out.println("Testing Appointment Triggers ----->");	

		driver.navigate().to(Triggers);

		// Adding new trigger
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/div/button")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("select#select_trigger+div")).click();
		Thread.sleep(1000);
		jse.executeScript("document.querySelector('#select_trigger+div>ul.list').scrollTop=100");
		driver.findElement(By.cssSelector("ul.list>li[data-value=\"APPOINTMENT_BOOKED\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.mt-4.centz-add-filterpp>a")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("select[id^=select_filter_]+div")).click();
		driver.findElement(By.cssSelector("div.open>ul.list>li:nth-child(3)")).click();
		
		driver.findElement(By.cssSelector("div.flex.gap-3.actioncard>div:nth-of-type(3)>div:nth-of-type(2)")).click();//action select
		action.sendKeys("pipeline").perform();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("ul.list>li[style=\"display: list-item;\"]")).click();
		Thread.sleep(1500);
		driver.findElement(By.cssSelector("select[name=\"pipeline\"]+div")).click();
		driver.findElement(By.cssSelector("select[name=\"pipeline\"]+div>ul>li:nth-child(3)")).click();
		Thread.sleep(1500);
		driver.findElement(By.cssSelector("select[name=\"stage\"]+div")).click();
		driver.findElement(By.cssSelector("select[name=\"stage\"]+div>ul>li:nth-child(2)")).click();
		driver.findElement(By.cssSelector("div.actionslist+div>a")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.actionslist>div:nth-of-type(2)>div:last-of-type>div:nth-of-type(2)")).click();
		
		
		
		jse.executeScript("document.querySelector('div[data-position=\"1\"]>div>div>ul.list').scrollTop=300");
	
	}
	
	@Test
	public void DeleteCampTrig() throws InterruptedException 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		JavascriptExecutor jse = (JavascriptExecutor)driver;

		driver.navigate().to(Triggers);
		driver.findElement(By.cssSelector("div.triggerlist>div")).click();
		driver.findElement(By.cssSelector("div.triggerlist>div>ul>li:nth-child(4)")).click();

		for(int i=0;i<2;i++)
		{
			jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			driver.findElement(By.cssSelector("div.bg-white.triggerparentdiv:nth-last-of-type(4)>div:last-of-type>div:last-of-type>a:last-of-type")).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("#formdeleteactionmodaldiv>div>button")).click();	
		}
	}

}
