package Modules;

import java.io.File;
import java.io.IOException;
import java.time.Duration;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import AccessLevel.Data;


public class Campaign extends Data{
	
	@Test(priority=1)
	public void mailcamp() throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		Actions action = new Actions(driver);

		WebElement e = driver.findElement(By.cssSelector("div.hidden.sidebar-bg-color"));
		action.moveToElement(e).perform();
		jse.executeScript("document.querySelector('html > body > div > div:nth-of-type(2)').scrollTop=400");
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/nav/div[7]/div[2]/div[1]/div/a")).click();
		try {
			String alrt = driver.findElement(By.cssSelector("div.tablewrapper>div>h1")).getText();
			System.out.println(alrt);
			//Assert.assertEquals(false, true);
		}catch(Exception NoSuchElementException) {}
		driver.findElement(By.name("search")).sendKeys("mailcamp a1b2 c3");
		action.sendKeys(Keys.ENTER).scrollByAmount(0, 200).build().perform();
		try {
				driver.findElement(By.xpath("//*[@id=\"at0-cell-actions-0\"]/div/span[2]")).click();
				driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
			
		}
		catch(Exception NoSuchElementException){
			driver.navigate().refresh();
		}

		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/a")).click();
		driver.findElement(By.id("campaign_title2")).sendKeys("mailcamp a1b2 c3");//<-camp name
		driver.findElement(By.xpath("//*[@id=\"campaign_common_form\"]/div[3]/div/div/button"))
		.click();//<-- next button
		Thread.sleep(2000);
		String campcrt= driver.findElement(By.id("alertify-logs")).getText();
		jse.executeScript("scroll(0,400)");
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/main/div/div[1]/div/form/div[3]/div/div[2]/div[4]/div/button[1]"))
		.click();
		try { 
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"message_row_259\"]/div[2]/div/div[1]/div[1]")));
		}
		catch(Exception TimeoutException) {
			Thread.sleep(2000);
		}

		jse.executeScript("scroll(0,700)");
		action.sendKeys("This is test camp for testing 112233").sendKeys(Keys.TAB)
		.sendKeys("Hi this is test message").build().perform();
		jse.executeScript("window.scroll(top)");
		driver.findElement(By.xpath("//*[@id=\"campaign_common_form\"]/div[1]/div/div[3]/a[2]")).click();
		driver.findElement(By.xpath("//*[@id=\"campaign_common_form\"]/div[3]/div/div[2]/div[4]/button[2]"))
		.click();//<-- add contact

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"add-new-contacts\"]/div/div")));
		driver.findElement(By.name("first_name")).sendKeys("rohan");
		action.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys("7579793173").sendKeys(Keys.TAB)
		.sendKeys("Rohan@nadsoftdesign.com").build().perform();
		driver.findElement(By.xpath("//*[@id=\"importcustomform\"]/div/div[2]/button")).click();
		jse.executeScript("window.scroll(top)");
		try{
			driver.findElement(By.xpath("//*[@id=\"campaign_common_form\"]/div[1]/div/div[3]/a[2]")).click();//<--next button keep 2 clicks
		}catch(Exception StaleElementReferenceException) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"campaign_common_form\"]/div[1]/div/div[3]/a[2]")));
			driver.findElement(By.xpath("//*[@id=\"campaign_common_form\"]/div[1]/div/div[3]/a[2]"))
			.click();//<-- don't consider as duplicate
		}
		try{
			driver.findElement(By.xpath("//*[@id=\"campaign_common_form\"]/div[1]/div/div[3]/a[2]")).click();//<--next button keep 2 clicks
		}catch(Exception StaleElementReferenceException) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"campaign_common_form\"]/div[1]/div/div[3]/a[2]")));
			driver.findElement(By.xpath("//*[@id=\"campaign_common_form\"]/div[1]/div/div[3]/a[2]"))
			.click();//<-- don't consider as duplicate
		}
		jse.executeScript("window.scroll(0,300)");
		driver.findElement(By.id("launch-dropdown")).click();//<-- launch button
		driver.findElement(By.xpath("//*[@id=\"launchPageCenterModal\"]/div[1]/div[3]/div/div/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div#campaign-launch-now-popup > div > div > div > button")).click();
		driver.findElement(By.linkText("OK")).click();
		driver.findElement(By.name("search")).sendKeys("mailcamp a1b2 c3");
		action.sendKeys(Keys.ENTER).build().perform();
		String campstat1= driver.findElement(By.id("at0-cell-status-0")).getText();
		
		//Output
		System.out.println();
		System.out.println("Campaign  With Instant Mail ----->");
		System.out.println("	"+campcrt);
		System.out.println("	Campaign Launched!");	
		System.out.println("Campaign Status: "+campstat1); //<--campaign status
		
		try {
			driver.findElement(By.id("at0-cell-title-0")).click();
		}
		catch(Exception NoSuchElementException){
			System.out.println("Campaign not found");
		}
		jse.executeScript("window.scroll(0,300)");
		TakesScreenshot TS = (TakesScreenshot)driver;
		File file = TS.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file,new File( ".\\Screenshots\\Mailcamp.png"));
	}
	
	@Test(priority=1)
	public void smscamp() throws InterruptedException, IOException 
	{
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	Actions action = new Actions(driver);
	
	System.out.println();
	System.out.println("This is Campaign with instant SMS Testing------>");
	
	driver.navigate().to(Campaigns);
	
	driver.findElement(By.name("search")).sendKeys("SMScamp a1b2 c3");
	action.sendKeys(Keys.ENTER).scrollByAmount(220, 0).build().perform();
	
	try 
	{
		driver.findElement(By.cssSelector("#selectAll+span")).click();
		driver.findElement(By.cssSelector("div.adminbuttons>button")).click();
		driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
	}
	catch(Exception NoSuchElementException){
		driver.navigate().refresh();
	}
	driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/a")).click();
	driver.findElement(By.id("campaign_title2")).sendKeys("SMScamp a1b2 c3");//<-camp name
	driver.findElement(By.xpath("//*[@id=\"campaign_common_form\"]/div[3]/div/div/button"))
	.click();//<-- next button
	String campcrt= driver.findElement(By.xpath("//*[@id=\"alertify-logs\"]/article")).getText();
	System.out.println(campcrt);
	jse.executeScript("scroll(0,400)");
	driver.findElement(By.xpath("//*[@id=\"templateparent\"]/div[4]/div/button[2]")).click();
	
	try 
	{ 
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("sequence_text_335")));
	}
	catch(Exception TimeoutException) 
	{
		Thread.sleep(2000);
	}
	
	jse.executeScript("scroll(0,300)");
	
	//Sending Test SMS Without Adding SMS
	driver.findElement(By.cssSelector("div.new-template-row.getmessage-test>div:nth-child(2)>div>div>div:nth-child(2)>a")).click();
	
	
	// Adding SMS With ShortCodes
	action.sendKeys("Hi this is test SMS from campaign {FIRST_NAME} {LAST_NAME} {CONTACT_NAME} {EMAIL} {CELLPHONE} {BUSINESS} {BUSINESS-CELLPHONE} {ASSIGNED-USER} {COMPANY-WEBSITE}")
	.build().perform();
	driver.findElement(By.id("camplogin")).click();
	Thread.sleep(1000);
	driver.findElement(By.id("appendTriggerActionList")).isDisplayed();
	//driver.findElement(By.cssSelector("#appendTriggerActionList > div > div:nth-child(2) > div:nth-child(3) > h1")).click();
	//driver.findElement(By.name("emailsubject")).sendKeys("This is campaign trigger mail");
	//Thread.sleep(5000);
	//action.sendKeys(Keys.TAB).sendKeys("hi this is camp trig mail").build().perform();
	driver.findElement(By.cssSelector("div.add-camp-action>div:nth-of-type(4)")).click();
	driver.findElement(By.id("trigger_sms_message")).sendKeys("Trigger SMS from camp 110022");
	jse.executeScript("document.querySelector('body > div.form-trigger-details.form-trigger-details-width').scrollTop=800");
	driver.findElement(By.id("addingAction")).click();// add action button
	driver.findElement(By.cssSelector("a[data-action=\"5\"]")).click();
	jse.executeScript("document.querySelector('body > div.form-trigger-details.form-trigger-details-width').scrollTop=800");
	driver.findElement(By.cssSelector("#apply_remove_tags>div>div:first-of-type>div:first-of-type")).click();
	action.sendKeys(Keys.ENTER).build().perform();
	String addedtag = driver.findElement(By.cssSelector("#apply_remove_tags>div>div:first-of-type>div:first-of-type>div")).getText();
	System.out.println("selected tag: "+addedtag);
	driver.findElement(By.id("addingAction")).click();// add action button

	driver.findElement(By.cssSelector("div.bottom-trigger-buttons>div>div>a:last-of-type")).click();//Done button in camp trigger
	jse.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	driver.findElement(By.id("camplogin")).click();
	Thread.sleep(1000);
	driver.findElement(By.id("appendTriggerActionList")).isDisplayed();
	jse.executeScript("document.querySelector('body > div.form-trigger-details.form-trigger-details-width').scrollTop=800");
	driver.findElement(By.id("addingAction")).click();// add action button
    driver.findElement(By.cssSelector("a[data-action=\"7\"]")).click();
	action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
	
	
	driver.findElement(By.cssSelector("div.bottom-trigger-buttons>div>div>a:last-of-type")).click();//Done button in camp trigger
	
	jse.executeScript("window.scroll(top)");
	driver.findElement(By.xpath("//*[@id=\"campaign_common_form\"]/div[1]/div/div[3]/a[2]")).click();
	driver.findElement(By.xpath("//*[@id=\"campaign_common_form\"]/div[3]/div/div[2]/div[4]/button[2]"))
	.click();//<-- add contact
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"add-new-contacts\"]/div/div")));
	driver.findElement(By.name("first_name")).sendKeys("rohan");
	action.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys("14012057373").sendKeys(Keys.TAB)
	.sendKeys("Rohankokare7663@gmail.com").build().perform();
	driver.findElement(By.xpath("//*[@id=\"importcustomform\"]/div/div[2]/button")).click();
	jse.executeScript("window.scroll(top)");
	try{
		driver.findElement(By.xpath("//*[@id=\"campaign_common_form\"]/div[1]/div/div[3]/a[2]")).click();//<--next button keep 2 clicks
	}catch(Exception StaleElementReferenceException) {
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"campaign_common_form\"]/div[1]/div/div[3]/a[2]")));
	driver.findElement(By.xpath("//*[@id=\"campaign_common_form\"]/div[1]/div/div[3]/a[2]"))
	.click();//<-- don't consider as duplicate
	}
	try{
		driver.findElement(By.xpath("//*[@id=\"campaign_common_form\"]/div[1]/div/div[3]/a[2]")).click();//<--next button keep 2 clicks
	}catch(Exception StaleElementReferenceException) {
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"campaign_common_form\"]/div[1]/div/div[3]/a[2]")));
	driver.findElement(By.xpath("//*[@id=\"campaign_common_form\"]/div[1]/div/div[3]/a[2]"))
	.click();//<-- don't consider as duplicate
	}
	jse.executeScript("window.scroll(0,300)");
	driver.findElement(By.id("launch-dropdown")).click();//<-- launch button
	driver.findElement(By.xpath("//*[@id=\"launchPageCenterModal\"]/div[1]/div[3]/div/div/a")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//*[@id=\"campaign-launch-now-popup\"]/div/div/div/button")).click();
	System.out.println("Campaign Launched!");
	driver.findElement(By.linkText("OK")).click();
	
	driver.findElement(By.name("search")).sendKeys("mailcamp a1b2 c3");
	action.sendKeys(Keys.ENTER).build().perform();
	String campstat2= driver.findElement(By.id("at0-cell-status-0")).getText();
	System.out.println("Campaign Status: "+campstat2); //<--campaign status
	
	/*try {
		driver.findElement(By.id("at0-cell-title-0")).click();
	}
	catch(Exception NoSuchElementException){
		System.out.println("Campaign not found");
	}
	jse.executeScript("window.scroll(0,300)");
	TakesScreenshot TS = (TakesScreenshot)driver;
	File file = TS.getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(file,new File( ".\\Screenshots\\SMScamp.png"));*/
	}
	
	@Test(priority=2)
	@Parameters({"uname", "pass"})
	public void camptrigcheck(String uname, String pass) throws InterruptedException
	{
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
	Actions action = new Actions(driver);
	
	driver.findElement(By.xpath("//*[@id=\"dropdownMenu1\"]")).click();
	driver.findElement(By.linkText("Logout")).click();
	
	Thread.sleep(10000);
	WebElement emf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
	emf.sendKeys("nadsoft.test02@gmail.com");
	driver.findElement(By.id("password")).sendKeys(" nadsoft@123");
	driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[4]/button[1]")).click();
	Thread.sleep(5000);
	driver.navigate().to("https://app.deposyt.com/index.php?m=conversation");
	
	driver.findElement(By.cssSelector("div.custom-sms-contact>div:nth-of-type(3)>a:first-of-type")).click();
	driver.findElement(By.cssSelector("div.custom-sms-contact>div:nth-of-type(2)>div>form>input#search-input"))
	.sendKeys("Automation Contact");
	action.sendKeys(Keys.ENTER).perform();
	//driver.findElement(By.cssSelector("#convoWrapper > div.conversation_started_messages_div_content > div.sms-box:last-of-type>div>div:nth-of-type(2)>div:first-of-type")).click();
	String msg = driver.findElement(By.cssSelector("#convoWrapper > div.conversation_started_messages_div_content > div.sms-box:last-of-type>div>div:nth-of-type(2)>div:first-of-type")).getText();
	if(msg.equalsIgnoreCase("Hi this is test SMS from campaign"))
	{
		System.out.println("Campaign Message Recived");
	}else {
		Thread.sleep(5000);
		driver.navigate().refresh();
		String msg1 = driver.findElement(By.cssSelector("#convoWrapper > div.conversation_started_messages_div_content > div.sms-box:last-of-type>div>div:nth-of-type(2)>div:first-of-type")).getText();
		Assert.assertEquals(msg1,"Hi this is test SMS from campaign", "Campaign Message Not Recived");
	}

	driver.findElement(By.xpath("//*[@id=\"dashboard-conversation-text\"]"))
	
	.sendKeys("Hi this is test message for daily testing");
	driver.findElement(By.xpath("//*[@id=\"msgbox_messages\"]/div[3]/div[2]")).click();//<---send
	try{
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"toast-container\"]/div")));
		String msg1 = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div")).getText();
		System.out.println("Reply "+msg1);
	}catch(Exception TimeOutException) {
		System.out.println("Message not sent");
	}
	
	driver.findElement(By.xpath("//*[@id=\"dropdownMenu1\"]")).click();
	driver.findElement(By.linkText("Logout")).click();
	WebElement emf1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
	emf1.sendKeys(uname);
	driver.findElement(By.id("password")).sendKeys(pass);
	driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[4]/button[1]")).click();//Login button
	}
	
	@Test(priority=5)
	public void campreply() throws InterruptedException
	{
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	Actions action = new Actions(driver);
	driver.navigate().to("https://app.deposyt.com/index.php?m=campaigns");
	driver.findElement(By.name("search")).sendKeys("smscamp a1b2 c3");

	
	action.sendKeys(Keys.ENTER).build().perform();
	String Count = driver.findElement(By.cssSelector("div.reply-up:first-of-type")).getText();
	if(Count.equalsIgnoreCase("0"))
	{
		Thread.sleep(8000);
		driver.navigate().refresh();
		System.out.println("Reply count: "+Count);
	}
	System.out.println("Reply count: "+Count);
	driver.findElement(By.cssSelector("div.reply-up:first-of-type")).click();
	String Response = driver.findElement(By.cssSelector("td[title=\"Response\"]>div>h1")).getText();
	System.out.println("response"+Response);
	Assert.assertEquals(Response,"hi this is test message for daily testing", "Check Response Message Not Match");
	driver.findElement(By.cssSelector("#admintable0>thead>tr>th>label>span")).click();
	//Check all popups are opening
	for(int i=1;i<=6;i++)
	{
		driver.findElement(By.cssSelector("div.check-options>button:nth-of-type("+i+")")).click();
		if(i==1)
		{
			driver.findElement(By.cssSelector("#send-sms-email-popup>div>div")).isDisplayed();
			action.scrollByAmount(0, 600).perform();
			driver.findElement(By.cssSelector("#SendBulkSms>div:nth-of-type(3)>div>a")).click();
		}else if(i==2)
		{
			driver.findElement(By.cssSelector("#assign-user-popup-campaign>div>div")).isDisplayed();
			driver.findElement(By.cssSelector("#ct_assignuser_form_campaign>div:nth-of-type(3)>a")).click();
		}else if(i==3)
		{
			driver.findElement(By.cssSelector("#add-tagto-user-popup-campaign>div>div>div")).isDisplayed();
			driver.findElement(By.cssSelector("#applytagtousercampaignfrm>div:nth-of-type(2)>button:first-of-type")).click();
		}else if(i==4)
		{
			driver.findElement(By.cssSelector("#move_pipeline_modal_campaign>div>div>div")).isDisplayed();
			driver.findElement(By.cssSelector("input[name=hiddensqlquerypopuppipe]+button:first-of-type")).click();
		}else if(i==5)
		{
			driver.findElement(By.cssSelector("#export-option-popup-campaign>div>div")).isDisplayed();
			driver.findElement(By.cssSelector("div.export-inner-wrapper+div>a")).click();
		}else if(i==6)
		{
			driver.findElement(By.id("ct_deleteContacts_form")).isDisplayed();
			driver.findElement(By.cssSelector("#ct_deleteContacts_form>div:nth-of-type(3)>button:first-child")).click();
		}
	}
	
	//Validating QuickFilters count
		String beforereplied = driver.findElement(By.className("filter-counter-green")).getText();
		Assert.assertEquals(beforereplied, "0","Check Quick filter count is mismatching");
		String beforeunreplied = driver.findElement(By.className("filter-counter-red")).getText();
		Assert.assertEquals(beforeunreplied, "1","Check Quick filter count is mismatching");
		driver.findElement(By.cssSelector("tr.admintable_row>td:nth-child(7)>a")).click();
		Thread.sleep(500);
		driver.findElement(By.id("camp_email_reply")).isDisplayed();
		driver.findElement(By.id("campaign-sms-message")).sendKeys("reply");
		driver.findElement(By.cssSelector("a[onclick=\"convoSendSmsHandlerCampaign(this)\"]")).click();
		Thread.sleep(200);
		String replysuccess = driver.findElement(By.id("toast-container")).getText();
		System.out.println("Reply "+replysuccess);
		String afterreplied = driver.findElement(By.className("filter-counter-green")).getText();
		Assert.assertEquals(afterreplied, "1","Check Quick filter count is mismatching");
		String afterunreplied = driver.findElement(By.className("filter-counter-red")).getText();
		Assert.assertEquals(afterunreplied, "0","Check Quick filter count is mismatching");
		System.out.println("Quick filters count updating correctly");
		
	driver.navigate().to("https://app.deposyt.com/index.php?m=conversation");
	Thread.sleep(3000);
	driver.findElement(By.cssSelector("div.custom-sms-contact>div:nth-of-type(2)>div>form>input#search-input")).sendKeys("Rohankokare7663@gmail.com");
	action.sendKeys(Keys.ENTER).perform();
	String Trigmsg = driver.findElement(By.cssSelector("#convoWrapper > div.conversation_started_messages_div_content > div.sms-box:last-of-type>div>div:nth-of-type(2)>div:first-of-type")).getText();
	Assert.assertEquals(Trigmsg,"Trigger SMS from camp 110022", "Trigger Message not sent");
	}
}
