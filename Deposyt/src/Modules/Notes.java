package Modules;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import AccessLevel.Data;

public class Notes extends Data{
	
	@Test
	public void NT() throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		
		action.moveToElement(driver.findElement(By.cssSelector("div.hidden.sidebar-bg-color"))).perform();
		jse.executeScript("document.querySelector('div#block0body > div > div > div > div > div > ul').scrollTop=200");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("html > body > div > div:nth-of-type(2) > div > div > div:nth-of-type(2) > nav > div:nth-of-type(5) > div:nth-of-type(2) > div:nth-of-type(4) > div > a")).click();
		
// Adding new Note
		driver.findElement(By.xpath("//*[@id=\"notes-toggler\"]/div[1]/div[1]/div[1]/div[1]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"js-tags-selectorpop-selectized\"]")).sendKeys("a");
		action.sendKeys(Keys.ENTER).perform();
		Thread.sleep(1000);
		driver.findElement(By.id("dashboard_note_text")).sendKeys("This is Test Note 1103");
		driver.findElement(By.id("add_a_note_popup_btn")).click();
		System.out.println();
		String Log = driver.findElement(By.cssSelector("div#toast-container > div > div")).getText();
		System.out.println(Log);
		
//Deleting note
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("a[id=dropdownMenu2]:first-of-type")).click();
		driver.findElement(By.xpath("//*[@id=\"notes-toggler\"]/div[1]/div[1]/div[3]/div/div/div/div[3]/div/div/div[1]/div[2]/div[2]/div/a[3]")).click();
		driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
		Thread.sleep(2000);
		String log = driver.findElement(By.id("alertify-logs")).getText();
		System.out.println(log);		
	}
	public void Modalpopup() throws Exception 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.navigate().to(Notes);
		driver.findElement(By.cssSelector("#pinnedNotesDiv+div+div>div>div>div>div>div>a")).click();
		
		Messages M = new Messages();
		M.CMP();
	}
}