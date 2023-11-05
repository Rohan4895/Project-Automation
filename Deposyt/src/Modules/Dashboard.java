package Modules;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import AccessLevel.Data;

public class Dashboard extends Data {
	
	@Test(priority=1)
	public void retrivedata(){
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action =  new Actions(driver); 
		
		WebElement e = driver.findElement(By.cssSelector("div.hidden.sidebar-bg-color"));
		action.moveToElement(e).perform();
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/nav/div[1]/div/a")).click();
		System.out.println("");
		String leads= driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[1]/div/div/div[1]/div/div/h1[2]/span")).getText();
		System.out.println("Total leads: "+leads);System.out.println("");
	
		
		String Appointments= driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[1]/div/div/div[2]/div")).getText();
		System.out.println(Appointments);
		System.out.println("");
		
		String notifications= driver.findElement(By.id("notifyDashTop")).getText();
		System.out.println("Total Notifications: "+notifications);	
		
		String notes = driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[1]/div/div/div[4]/div")).getText();
		System.out.println(notes);
	}
	
	@Test
	public void activity() {
		Actions action =  new Actions(driver); 
		
		WebElement e = driver.findElement(By.cssSelector("div.hidden.sidebar-bg-color"));
		action.moveToElement(e).perform();
		
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/nav/div[1]/div/a")).click();
		//Day
		System.out.println("");
		System.out.println("Day Activity");
		String mailactivity= driver.findElement(By.xpath("//*[@id=\"notifyFilterWise\"]/a[1]")).getText();
		System.out.println(mailactivity);
		System.out.println("");
		
		String SMSactivity = driver.findElement(By.xpath("//*[@id=\"notifyFilterWise\"]/a[2]")).getText();
		System.out.println(SMSactivity);
		System.out.println("");
		
		String callsactivity = driver.findElement(By.xpath("//*[@id=\"notifyFilterWise\"]/a[3]")).getText();
		System.out.println(callsactivity);
		System.out.println("");
		
		
	}
	
	@Test
	public void appdetails() {
		Actions action =  new Actions(driver); 
		
		WebElement e = driver.findElement(By.cssSelector("div.hidden.sidebar-bg-color"));
		action.moveToElement(e).perform();
		
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/nav/div[1]/div/a")).click();
		try {
		String appactivity = driver.findElement(By.cssSelector("div.mini-msg-title.ml-1>div:nth-of-type(2)>div>div")).getText();
		System.out.println("Next Appointment Details");
		System.out.println(appactivity);
		System.out.println("");
		}catch (Exception NoSuchElementException){
			System.out.println("No Next Appointment Scheduled");
			System.out.println();
		}
	}
	
	@Test
	public void campdetails() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action =  new Actions(driver); 
		
		WebElement e = driver.findElement(By.cssSelector("div.hidden.sidebar-bg-color"));
		action.moveToElement(e).perform();
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/nav/div[1]/div/a")).click();
		String campstatus = driver.findElement(By.xpath("//*[@id=\"spacious-container\"]/div[2]/div/div/div[2]/div[1]/div[1]")).getText();
		System.out.println("Camp status: "+campstatus);
		System.out.println("");
		String campdetails = driver.findElement(By.xpath("//*[@id=\"spacious-container\"]/div[2]/div/div/div[2]/div[1]/div[2]")).getText();
		System.out.println("Camp Details: "+campdetails);
		System.out.println("");
	}
	
	@Test
	public void linkverification() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action =  new Actions(driver); 
		
		WebElement e = driver.findElement(By.cssSelector("div.hidden.sidebar-bg-color"));
		action.moveToElement(e).perform();
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/nav/div[1]/div/a")).click();
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[1]/div/div/div[1]/div/a")).click();
		try{
			driver.findElement(By.xpath("//*[@id=\"dashboard_leads\"]/div/div"));
			driver.findElement(By.xpath("//*[@id=\"dashboard_leads\"]/div/div/a")).click();
		}catch(Exception NoSuchElementException) {
			Assert.assertEquals(false, true,"Leads Pop up not open");
		}
		String today= driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[1]/div/div/div[2]/div/div[2]/a[1]")).getAttribute("href");
		Assert.assertEquals(today,"https://app.deposyt.com/index.php?m=appointments&d=tableview&rtype=today","Check Today button URL");
		
		String seeall = driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[1]/div/div/div[2]/div/div[2]/a[2]")).getAttribute("href");
		Assert.assertEquals(seeall, "https://app.deposyt.com/index.php?m=appointments&d=tableview", "Check see all URL");
		
		String SMS = driver.findElement(By.xpath("//*[@id=\"notifyDashTop\"]/div[2]/a[1]")).getAttribute("href");
		Assert.assertEquals(SMS, "https://app.deposyt.com/index.php?m=conversation&selected=unread&tabopen=sms_now","Check SMS URL");
		
		String Emails = driver.findElement(By.xpath("//*[@id=\"notifyDashTop\"]/div[2]/a[2]")).getAttribute("href");
		Assert.assertEquals(Emails, "https://app.deposyt.com/index.php?m=conversation&selected=unread&tabopen=email_now", "Check Email URL");
		
		String calls = driver.findElement(By.xpath("//*[@id=\"notifyDashTop\"]/div[2]/a[3]")).getAttribute("href");
		Assert.assertEquals(calls,"https://app.deposyt.com/index.php?m=customers&d=calllogs&filter=missed", "Check Calls URL");
		
		String rem= driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[1]/div/div/div[4]/div/div[2]/a[1]")).getAttribute("href");
		Assert.assertEquals(rem,"https://app.deposyt.com/index.php?m=notesmain&d=allnotes&notestypecust=&notestype=timescheduled","Check Remainders URL");
		
		String seeallrem = driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[1]/div/div/div[4]/div/div[2]/a[2]")).getAttribute("href");
		Assert.assertEquals(seeallrem,"https://app.deposyt.com/index.php?m=notesmain&d=allnotes","Check Remainder URL");
		
		String actmail = driver.findElement(By.xpath("//*[@id=\"notifyFilterWise\"]/a[1]")).getAttribute("href");
		Assert.assertEquals(actmail,"https://app.deposyt.com/index.php?m=conversation&selected=unread&tabopen=email_now","Check Mail URL in Activity ");
		
		String actSMS =driver.findElement(By.xpath("//*[@id=\"notifyFilterWise\"]/a[2]")).getAttribute("href");
		Assert.assertEquals(actSMS,"https://app.deposyt.com/index.php?m=conversation&selected=unread&tabopen=sms_now","Check SMS URL in activity");
		
		String Scapp= driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[2]/div/div[2]/div/div[2]/div[2]/a")).getAttribute("href");
		Assert.assertEquals(Scapp, "https://app.deposyt.com/index.php?m=appointments&d=tableview","Check Schedule appointment URL");
		
		System.out.println("All URL's on Dashboard are verified Except Call in Activity");
	}
	
	public void ModalPopup() throws InterruptedException 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.navigate().to(Dashboard);
		try
		{
			driver.findElement(By.cssSelector("a[target-off=\"_blank\"]")).click();
			Messages M = new Messages();
			M.CMP();
		}
		catch(Exception E)
		{
			System.out.println("No Appointment Found");
		}
	}	
		

}
