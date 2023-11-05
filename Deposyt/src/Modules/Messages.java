package Modules;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import AccessLevel.Data;

public class Messages extends Data 
{

	@Test(priority = 2)
	public void SendSMS() throws InterruptedException 
	{
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.navigate().to(Messages);

		System.out.println();
		System.out.println("SMS Functionality ----->");

		//<--Message-->
		Thread.sleep(4000);
		driver.findElement(By.name("filters")).sendKeys("Weaired testname");
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(3000);

		driver.findElement(By.id("dashboard-conversation-text"))
		.sendKeys("Hi this is test message for daily testing cVUj4KSj ");
		driver.findElement(By.xpath("//*[@id=\"msgbox_messages\"]/div[3]/div[2]")).click();//<---send
		String msg=null;

		try
		{
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"toast-container\"]/div")));
			msg = driver.findElement(By.id("toast-container")).getText();
		}
		catch(Exception TimeOutException) 
		{
			System.out.println("Message not sent");
		}
		System.out.println("	"+msg);
	}

	@Test (priority = 3)
	public void SMSInsertOptions() throws InterruptedException 
	{
		Actions action =new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.navigate().to(Messages);
		String b= driver.getTitle();
		Assert.assertEquals(b, "conversation :: deposyt.com", "Message tab not open");

		Thread.sleep(4000);
		driver.findElement(By.name("filters")).sendKeys("Weaired testname");
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(3000);

		System.out.println();
		System.out.println("SMS Functionality ----->");

		//validating insert deposyt template functionality
		driver.findElement(By.cssSelector("a[data-original-title=\"Insert Message Template\"]")).click();

		try 
		{
			boolean a = driver.findElement(By.cssSelector("#category-centz-sms-templates>li")).isDisplayed();
			System.out.println("	Visibility of Deposyt templetes in SMS:"+a);	
		}
		catch(Exception nosuchelementException)
		{
			System.out.println("	Deposyt Templates Are Not Loaded");
		}

		driver.findElement(By.cssSelector("#category-centz-sms-templates>li:first-child>a")).click();//click on most used templates
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#df-sms-templates-box>div:first-child>div")).click();
		String Tempselected = driver.findElement(By.cssSelector("#df-sms-templates-box>div:first-child>div>p")).getText();
		System.out.println("	Inserted Template: "+Tempselected);
		driver.findElement(By.cssSelector("div#sms-templates>div>div>div.template-cta-buttons>a:last-of-type")).click();//insert button
		driver.findElement(By.id("convoSendSmsBtn")).click();//<---send

		String msg=null;
		try
		{
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"toast-container\"]/div")));
			msg = driver.findElement(By.id("toast-container")).getText();
		}
		catch(Exception TimeOutException) 
		{
			System.out.println("	Message not sent");
		}
		System.out.println("	"+msg+" By Inserting Deposyt Templates");


		//Inserting my Template 
		System.out.println();
		driver.findElement(By.cssSelector("a[data-original-title=\"Insert Message Template\"]")).click();

		Thread.sleep(2000);
		driver.findElement(By.className("smsTabCustom")).click();

		try
		{
			boolean bb = driver.findElement(By.cssSelector("#category-user-sms-templates-mu>li")).isDisplayed();
			System.out.println("	Visibility of My templetes in SMS:"+bb);
		}
		catch(Exception nosuchelementException)
		{
			System.out.println("	My Templetes Are Not Loaded");
		}

		driver.findElement(By.cssSelector("#category-user-sms-templates-mu>li:first-child>a")).click();//click on most used templates
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#custom-sms-templates-box>div:first-child>div")).click();
		String Tempselected1 = driver.findElement(By.cssSelector("#custom-sms-templates-box>div:first-child>div>p")).getText();
		System.out.println("	Inserted My Template: "+Tempselected1);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div#sms-templates>div>div>div.template-cta-buttons>a:last-of-type")).click();
		driver.findElement(By.id("convoSendSmsBtn")).click();//<---send

		String msg1=null;
		try
		{
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"toast-container\"]/div")));
			msg1 = driver.findElement(By.id("toast-container")).getText();
		}
		catch(Exception TimeOutException) 
		{
			System.out.println("	Message not sent");
		}
		System.out.println("	"+msg1+" By Inserting My Templates");

		//Inserting Booking Invite
		driver.findElement(By.cssSelector("#BookingInviteAppointmentTypes>label:first-of-type")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.modal.fade.dp-new-popup.in#tools_popup")));
		driver.findElement(By.cssSelector("#toolspopup>div:nth-of-type(2)>div")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#BookingInviteAppointmentTypes>label:nth-child(3)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#toggleBookingLink+div+div")).click();
		driver.findElement(By.cssSelector("#toolspopup>div:last-of-type>button")).click();//insert button
		Thread.sleep(2000);

		driver.findElement(By.id("convoSendSmsBtn")).click();//<---send

		String msg2=null;
		try
		{
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"toast-container\"]/div")));
			msg2 = driver.findElement(By.id("toast-container")).getText();
		}
		catch(Exception TimeOutException) 
		{
			System.out.println("	Message not sent");
		}
		System.out.println("	"+msg2+" By Inserting Booking Invite");

		//Insert image/pdf
		driver.findElement(By.cssSelector("#msgbox_messages>div.bottom-options>div>a:nth-child(3)")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#toolspopup>div:nth-of-type(3)")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("#toolspopup>div:nth-of-type(3)>ul>li:nth-child(3)")).click();
		driver.findElement(By.cssSelector("#toolspopup>div:last-of-type>button")).click();//insert button
		Thread.sleep(2000);

		driver.findElement(By.id("convoSendSmsBtn")).click();//<---send

		String msg3=null;
		try
		{
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"toast-container\"]/div")));
			msg3 = driver.findElement(By.cssSelector("#toast-container>div:last-of-type")).getText();
		}
		catch(Exception TimeOutException) 
		{
			System.out.println("	Message not sent");
		}
		System.out.println("	"+msg3+" By Inserting Image/PDF");

		// Send SMS by inserting Shortcodes
		driver.findElement(By.cssSelector("#msgbox_messages>div.bottom-options>div>a:nth-child(5)")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toolspopup")));
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#toolspopup>div:nth-of-type(2)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("label[data-value=\"{FIRST_NAME}\"]")).click();
		driver.findElement(By.cssSelector("#toolspopup>div:last-of-type>button")).click();//insert button
		Thread.sleep(2000);

		driver.findElement(By.id("convoSendSmsBtn")).click();//<---send

		String msg4=null;
		try
		{
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"toast-container\"]/div")));
			msg4 = driver.findElement(By.cssSelector("#toast-container>div:last-of-type")).getText();
		}
		catch(Exception TimeOutException) 
		{
			System.out.println("	Message not sent");
		}
		System.out.println("	"+msg4+" By Inserting Shortcode");

		//validating Shortcode replacement
		Thread.sleep(4000);
		String shortcode = driver.findElement(By.cssSelector("div.conversation_started_messages_div_content>div:last-of-type>div>div:nth-child(2)>div:first-child")).getText();
		//System.out.println(shortcode);
		Assert.assertEquals(shortcode, "Weaired testname","Assertion Failed For Shorcode Message");
	}

	@Test (priority = 4)
	public void SendMail() throws InterruptedException 
	{
		Actions action =new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.navigate().to(Messages);
		System.out.println();
		System.out.println("Send Email Functionality ----->");
		Thread.sleep(4000);
		driver.findElement(By.name("filters")).sendKeys("Weaired testname");
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(3000);

		driver.findElement(By.id("calling_email")).click();
		Thread.sleep(3000);

		driver.findElement(By.id("subject_email"))
		.sendKeys("Hi this is test mail ");
		action.sendKeys(Keys.TAB)
		.sendKeys("This is test mail")
		.build().perform();

		driver.findElement(By.cssSelector("#toggleSign+div+div")).click();//sign toggle switch
		driver.findElement(By.xpath("//*[@id=\"emailbox_messages\"]/div/a[3]")).click();//test email
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"test-email-popup\"]/div/div/div[2]/div[2]/div/input")).sendKeys("Rohan@nadsoftdesign.com");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"test-email-popup\"]/div/div/div[2]/div[3]/button")).click();
		String tmail = driver.findElement(By.cssSelector("div.toast-message:first-of-type")).getText();
		System.out.println("	"+tmail);

		Thread.sleep(2000);
		driver.findElement(By.cssSelector("a#convoSendEmailBtn")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("calling_sms")).click();
		Thread.sleep(1000);
		String mail = driver.findElement(By.cssSelector("div#toast-container > div > div")).getText();
		System.out.println("	"+mail);

		Thread.sleep(2000);
		WebElement MFrame = driver.findElement(By.cssSelector("div#convoWrapper>div>div.sms-box:nth-last-of-type(1)>div>div:nth-child(2)>div:nth-child(5)>div>iframe[id^=\"iframe\"]"));
		driver.switchTo().frame(MFrame);

		try
		{
			
			driver.findElement(By.cssSelector("div.usersign")).isDisplayed();
			System.out.println("	Customer signature found in mail");
			driver.findElement(By.cssSelector("a[href ^= \"https://app.deposyt.com/index.php?m=unsubscribe\"]")).isDisplayed();
			System.out.println("	unsubscribe link is found in mail");
		}
		catch(Exception e)
		{
			System.out.println("<-----Check Customer Signature/ Unsubscribe link not get added in the mail----->");
		}
	}

	@Test(priority = 1)
	public void MOTD() throws InterruptedException 
	{
		Actions action =new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.navigate().to(Messages);
		System.out.println();
		System.out.println("Message Of The Day ----->");
		Thread.sleep(5000);
		driver.findElement(By.name("filters")).sendKeys("Weaired testname");
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(4000);

		driver.findElement(By.cssSelector("#msgbox_messages>div.bottom-options>div>a:nth-child(7)")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("msg-day-popup")));
		driver.findElement(By.id("create-new-day-temlate")).click();

		driver.findElement(By.id("message-title-new")).sendKeys("New Automation Message of the day");
		action.sendKeys(Keys.TAB).sendKeys("New message for testing").build().perform();
		driver.findElement(By.cssSelector("a.save_template_btn.add_message_for_day")).click();//save button

		// Check Created Message get added in Message of the day Folder
		Thread.sleep(2000);
		try
		{
			driver.findElement(By.id("see-all-messages-for-day")).click();
		}
		catch(Exception e)
		{
			driver.findElement(By.id("see-all-messages-for-day")).click();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sms-templates")));
		driver.findElement(By.cssSelector("a.sms_cust_ctgs_md_target ")).click();
		Thread.sleep(2000);
		String MOTD = driver.findElement(By.cssSelector("#custom-sms-templates-box>div:last-of-type>div>div>h1")).getText();
		Assert.assertEquals(MOTD, "New Automation Message of the day","Message of the day title mismatched");
		driver.findElement(By.cssSelector("#user-templates-sms>div:first-child>div:nth-child(2)>a:nth-Child(2)")).click();//popup Close icon

		//sharing with users
		driver.findElement(By.id("user-dropdown")).click();
		String UID = driver.findElement(By.cssSelector("div.overflow-auto.h-52>label:first-child>input")).getAttribute("data-id");
		driver.findElement(By.cssSelector("div.overflow-auto.h-52>label:first-child")).click();
		driver.findElement(By.id("sharewithteam")).click();
		String up = driver.findElement(By.cssSelector("div#toast-container > div > div")).getText();
		System.out.println("	"+up);
		driver.findElement(By.id("convoSendDaySmsBtn")).click();//send button

		driver.navigate().to(UserManagment);
		driver.findElement(By.cssSelector("tr[data-id=\""+UID+"\"]>td:last-of-type>div>a")).click();
		driver.findElement(By.cssSelector("#usertableoptions[aria-expanded=\"true\"]+div>a:first-child")).click();
		driver.switchTo().alert().accept();
		Thread.sleep(2000);

		driver.navigate().to(Messages);
		driver.findElement(By.cssSelector("#msgbox_messages>div.bottom-options>div>a:nth-child(7)")).click();
		driver.findElement(By.id("see-all-messages-for-day")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sms-templates")));
		driver.findElement(By.cssSelector("a.sms_cust_ctgs_md_target ")).click();
		Thread.sleep(2000);
		String MOTD1 = driver.findElement(By.cssSelector("#custom-sms-templates-box>div:last-of-type>div>div>h1")).getText();
		driver.findElement(By.cssSelector("#custom-sms-templates-box>div:last-of-type>div>div>div")).click();
		Assert.assertEquals(MOTD1, "New Automation Message of the day","Message of the day title mismatched");
		driver.findElement(By.cssSelector("#user-templates-sms>div:first-child>div:nth-child(2)>a:nth-Child(2)")).click();//popup Close icon

		//Relogin to Admin account 
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("h1.profile-user-name")).click();
		Thread.sleep(500);
		driver.findElement(By.linkText("Re-Login Back")).click();

		//Saving Template as MOTD(Most Used Temp)
		driver.navigate().to(Messages);
		driver.findElement(By.cssSelector("#msgbox_messages>div.bottom-options>div>a:nth-child(7)")).click();
		driver.findElement(By.id("see-all-messages-for-day")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sms-templates")));
		driver.findElement(By.cssSelector("a.sms_cust_ctgs_md_target ")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#custom-sms-templates-box>div:last-of-type>div>div>div")).click();//remove icon
		driver.findElement(By.cssSelector("#user-templates-sms>div:first-child>div:nth-child(2)>a:nth-Child(2)")).click();//popup Close icon
		Thread.sleep(1000);
		driver.findElement(By.id("create-new-day-temlate")).click();	
		driver.findElement(By.cssSelector("#create-new-day-temlate+a")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#category-user-sms-templates-mu>li:nth-child(2)")).click();
		String heading1 = driver.findElement(By.cssSelector("#custom-sms-templates-box>div:first-child>div>div>h1")).getText();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("#custom-sms-templates-box>div:first-child>div")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div#sms-templates>div>div>div.template-cta-buttons>a:last-of-type")).click();//insert button
		driver.findElement(By.cssSelector("a.save_template_btn.add_message_for_day")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("see-all-messages-for-day")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sms-templates")));
		driver.findElement(By.cssSelector("a.sms_cust_ctgs_md_target ")).click();
		Thread.sleep(2000);
		String heading2 = driver.findElement(By.cssSelector("#custom-sms-templates-box>div:last-of-type>div>div>h1")).getText();
		Assert.assertEquals(heading2, heading1,"Message of the day title mismatched inserted from template");
		driver.findElement(By.cssSelector("#user-templates-sms>div:first-child>div:nth-child(2)>a:nth-Child(2)")).click();//popup Close icon
	}

	@Test (priority = 5)
	public void MailInsertOptions() throws InterruptedException 
	{
		Actions action =new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.navigate().to(Messages);
		System.out.println();
		System.out.println("Email Functionality ----->");
		Thread.sleep(5000);
		driver.findElement(By.name("filters")).sendKeys("Weaired testname");
		action.sendKeys(Keys.ENTER).build().perform();

		Thread.sleep(4000);

		driver.findElement(By.id("calling_email")).click();
		Thread.sleep(1000);

		//validating insert template fuctionality
		driver.findElement(By.cssSelector("a[data-original-title=\"Insert Email Template\"]")).click();
		Thread.sleep(1000);

		try 
		{
			boolean a = driver.findElement(By.cssSelector("#category-centz-email-templates>li")).isDisplayed();
			System.out.println("	Visibility of Deposyt templetes in Email:"+a);	
		}
		catch(Exception nosuchelementException)
		{
			System.out.println("Deposyt Templetes Are Not Loaded");
		}
		driver.findElement(By.cssSelector("#category-centz-email-templates>li:first-child>a")).click();//click on most used templates
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#df-email-templates-box>div:first-child>div")).click();
		String Tempselected = driver.findElement(By.cssSelector("#df-email-templates-box>div:first-child>div>div>h1")).getText();
		System.out.println("	Inserted Template: "+Tempselected);
		driver.findElement(By.cssSelector("div#email-templates>div>div>div.template-cta-buttons>a:last-of-type")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("a#convoSendEmailBtn")).click();// send mail button

		String msg=null;
		try
		{
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"toast-container\"]/div")));
			msg = driver.findElement(By.id("toast-container")).getText();
		}
		catch(Exception TimeOutException) 
		{
			System.out.println("	Email not sent");
		}
		System.out.println("	"+msg+" By Inserting Deposyt Templates");


		//Inserting my Template 
		System.out.println();
		driver.findElement(By.cssSelector("a[data-original-title=\"Insert Email Template\"]")).click();

		Thread.sleep(2000);
		driver.findElement(By.className("emailTabCustom")).click();

		try
		{
			boolean bb = driver.findElement(By.cssSelector("#category-user-email-templates-mu>li")).isDisplayed();
			System.out.println("	Visibility of My templetes in Email:"+bb);
		}
		catch(Exception nosuchelementException)
		{
			System.out.println("	My Templetes Are Not Loaded");
		}

		driver.findElement(By.cssSelector("#category-user-email-templates-mu>li:first-child>a")).click();//click on most used templates
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".centz-templates-email.hidden+div>div>#custom-email-templates-box>div:first-child>div")).click();
		String Tempselected1 = driver.findElement(By.cssSelector("#custom-email-templates-box>div:first-child>div>div>h1")).getText();
		System.out.println("	Inserted My Template: "+Tempselected1);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div#email-templates>div>div>div.template-cta-buttons>a:last-of-type")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("a#convoSendEmailBtn")).click();

		String msg1=null;
		try
		{
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"toast-container\"]/div")));
			msg1 = driver.findElement(By.id("toast-container")).getText();
		}
		catch(Exception TimeOutException) 
		{
			System.out.println("	Mail not sent");
		}
		System.out.println("	"+msg1+" By Inserting My Templates");

		//Inserting Booking Invite
		driver.findElement(By.id("subject_email"))
		.sendKeys("Inserting Booking invite");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#emailbox_messages>form>div.bottom-options>div>a[data-original-title=\"Insert Booking Invite\"]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.modal.fade.dp-new-popup.in#tools_popup")));
		driver.findElement(By.cssSelector("#toolspopup>div:nth-of-type(2)>div")).click();
		driver.findElement(By.cssSelector("#BookingInviteAppointmentTypes>label:nth-child(3)")).click();
		driver.findElement(By.cssSelector("#toggleBookingLink+div+div")).click();
		driver.findElement(By.cssSelector("#toolspopup>div:last-of-type>button")).click();//insert button
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("a#convoSendEmailBtn")).click();//Send button
		Thread.sleep(2000);

		//Insert image/pdf
		driver.findElement(By.id("subject_email"))
		.sendKeys("Inserting Image /Pdf link ");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#emailbox_messages>form>div.bottom-options>div>a[data-original-title=\"Insert Image/PDF\"]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toolspopup")));
		driver.findElement(By.cssSelector("#toolspopup>div:nth-of-type(3)")).click();
		driver.findElement(By.cssSelector("#toolspopup>div:nth-of-type(3)>ul>li:nth-child(3)")).click();
		driver.findElement(By.cssSelector("#toolspopup>div:last-of-type>button")).click();//insert button
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("a#convoSendEmailBtn")).click();//Send button

		String msg3=null;
		try
		{
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"toast-container\"]/div")));
			msg3 = driver.findElement(By.cssSelector("#toast-container>div:last-of-type")).getText();
		}
		catch(Exception TimeOutException) 
		{
			System.out.println("	Mail not sent");
		}
		System.out.println("	"+msg3+" By Inserting Image/PDF");

		//inserting Shrotcodes
		driver.findElement(By.id("subject_email"))
		.sendKeys("Inserting shortcodes");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div[role=\"application\"]+div>div>a[data-original-title=\"Insert Shortcode\"]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toolspopup")));
		driver.findElement(By.cssSelector("label[data-title=\"Choose Shortcode\"]")).click();
		driver.findElement(By.cssSelector("label[data-title=\"{FIRST_NAME} - first name of recipient\"]")).click();
		driver.findElement(By.cssSelector("#toolspopup>div:nth-of-type(4)>button")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("a#convoSendEmailBtn")).click();//Send button
		Thread.sleep(1000);
		String mail = driver.findElement(By.cssSelector("div#toast-container > div > div")).getText();
		System.out.println("	"+mail+" By Inserting Shortcodes");
		Thread.sleep(2000);
		String Mailbody = driver.findElement(By.cssSelector("div.dash-conversation-divider:nth-last-of-type(2)+div>div>div:nth-child(2)>div:nth-child(2)>div")).getText();//mailbody
		Assert.assertEquals(Mailbody, "Weaired testname Unsubscribe", "Shortcode not Get Replaced");
		System.out.println("	Shortcode Get Replaced");
	}

	@Test (priority = 6)
	public void Notes() throws Exception 
	{
		Actions action =new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.navigate().to(Messages);

		System.out.println();
		System.out.println("Testing Notes in Message ----->");
		Thread.sleep(4000);
		driver.findElement(By.name("filters")).sendKeys("Weaired testname");
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(3000);

		driver.findElement(By.id("calling_notes")).click();
		driver.findElement(By.id("msg_note_text")).sendKeys("Adding new note for @");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchresult")));
		//String user = driver.findElement(By.cssSelector("#searchresult>div:first-child")).getAttribute("id");
		driver.findElement(By.cssSelector("#searchresult>div:first-child")).click();
		driver.findElement(By.id("msg_note_submit_btn")).click();
		String note = driver.findElement(By.cssSelector("div#toast-container > div > div")).getText();
		System.out.println("	"+note);
		Thread.sleep(1000);

		//Editing Note From middel panel
		driver.navigate().refresh();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("div.dash-conversation-divider:nth-last-of-type(2)+div>div+div>div>a")).click();
		driver.findElement(By.cssSelector("div.dash-conversation-divider:nth-last-of-type(2)+div>div+div>div>div>a:first-child")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("messagemodal")));
		driver.findElement(By.id("dashboard_note_text")).sendKeys(" by Automation");
		driver.findElement(By.cssSelector("#error_message_orignal+div>button:last-of-type")).click();
		String update = driver.findElement(By.cssSelector("div#toast-container > div > div")).getText();
		System.out.println("	"+update);
		String notetext = driver.findElement(By.cssSelector("div.dash-conversation-divider:nth-last-of-type(2)+div>div+div>p")).getText();
		System.out.println("	After Editing Note: "+notetext);
		//Assert.assertEquals(notetext, "Adding New Note Nor @"+user+" By Automation", "Note not edited");
		Thread.sleep(4000);

		//deleting Note
		driver.findElement(By.cssSelector("div.dash-conversation-divider:nth-last-of-type(2)+div>div+div>div>a")).click();
		driver.findElement(By.cssSelector("div.dash-conversation-divider:nth-last-of-type(2)+div>div+div>div>div>a:last-of-type")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("delete_action_modal_div")));
		driver.findElement(By.cssSelector("#formdeleteactionmodaldiv>div>button")).click();
		String delete = driver.findElement(By.cssSelector("#toast-container>div:first-child")).getText();
		System.out.println("	"+delete);
	}

	@Test (priority = 7)
	public void MessageRightPanel() throws Exception 
	{
		Actions action =new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.navigate().to(Messages);

		Thread.sleep(4000);
		driver.findElement(By.name("filters")).sendKeys("Weaired testname");
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(3000);

		System.out.println();
		System.out.println("Changing Pipeline from Right panale :- ");
		String pipelinename = driver.findElement(By.cssSelector("div[data-target=\"#manage-pipeline-stage\"]>div>div:first-child>div>input.pipeline_selected_name_sidebar")).getAttribute("value");
		String stagename = driver.findElement(By.cssSelector("#pipeline_stagesside>div>input.stage_selected_title_sidebar")).getAttribute("value");

		System.out.println("	Current Pipeline: "+pipelinename);
		System.out.println("	Current Stage: "+stagename);

		driver.findElement(By.cssSelector("div[data-target=\"#manage-pipeline-stage\"]>div")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div#manage-pipeline-stage")));

		driver.findElement(By.id("piplineBookingNameselectedConversation")).click();//changing pipeline 
		driver.findElement(By.cssSelector("#piplinebooking_pipeline_list_conversation>label:nth-of-type(2)")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("booking_invite_pipe_stage_conversation")).click();//changing stage
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#piplinebooking_stage_list_conversation>label:nth-of-type(2)")).click();
		driver.findElement(By.cssSelector("#manage-pipeline-stage>div>div>div:last-of-type>button:last-of-type")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("customer_moving_div")));
		String movedto = driver.findElement(By.cssSelector("#customer_moving_div>div:nth-of-type(2)>h1")).getText();
		driver.findElement(By.id("myCustomer_close")).click();
		Thread.sleep(3000);
		System.out.println("	Customer Moved to pipline/Stage: "+movedto);

		//Adding Note From right Pannel 
		driver.findElement(By.cssSelector("#bottom_noteswrapper>div>textarea")).click();
		driver.findElement(By.id("cisp_note_text")).sendKeys("Right pannel note For @");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchresult")));
		driver.findElement(By.cssSelector("#searchresult>div:first-child")).click();
		driver.findElement(By.id("cisp_note_submit_btn")).click();
		String note1 = driver.findElement(By.cssSelector("div#toast-container > div > div")).getText();
		System.out.println("	"+note1);

		//Verfying Tags 
		System.out.println();
		System.out.println("Changing Tag from Right panale :- ");
		try
		{
			driver.findElement(By.cssSelector("#cusInfo_shorttags>div>a")).click();
		}
		catch(Exception ElementNotIntractableExcepation)
		{
			driver.findElement(By.cssSelector("div.item.dp-tags.eachiteams")).click();
		}
		driver.findElement(By.cssSelector("#manage-tag-popup>div>div>div>div:nth-of-type(3)>div:first-child")).click();
		driver.findElement(By.cssSelector("#manage-tag-popup>div>div>div>div:nth-of-type(3)>div:last-of-type>div>div:nth-of-type(1)")).click();
		driver.findElement(By.cssSelector("#manage-tag-popup>div>div>div>div:nth-of-type(3)>div:last-of-type>div>div:nth-of-type(2)")).click();
		driver.findElement(By.cssSelector("#manage-tag-popup>div>div>div>div:first-child")).click();//randon click on popup to close dropdown
		driver.findElement(By.cssSelector("#manage-tag-popup>div>div>div>div:last-of-type>div>button")).click();
		Thread.sleep(2000);
		String get = driver.findElement(By.cssSelector("div.toast.toast-success>div")).getText();
		System.out.println("	"+get);

		System.out.println("	Validating actions From Action Menu:");
	}

	@Test(priority = 8)
	public void ActionMenu() throws Exception 
	{
		Actions action =new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor jse = (JavascriptExecutor)driver;

		driver.navigate().to(Messages);

		Thread.sleep(4000);
		driver.findElement(By.name("filters")).sendKeys("Weaired testname");
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(3000);

		System.out.println();
		System.out.println("Validating Action Menu Actions: ");

		//Action menu- Schedule mail
		//Getting Quick filter count For Scheduled Messages
		driver.findElement(By.cssSelector("a#show_old_remaining_filters.desktopoption")).click();
		String BScCount1 = driver.findElement(By.cssSelector("a#show_old_remaining_filters.desktopoption+a+a+a>span")).getText();
		System.out.println("	Count Of Scheduled Messages Before Scheduling Mail: "+BScCount1);
		int ScCountint = Integer.parseInt(BScCount1);
		ScCountint = ScCountint + 1;
		String BScCount = Integer.toString(ScCountint);

		//Scheduling Mail
		driver.findElement(By.cssSelector("div.desktop >div.selectact>div:first-child>div>a#dropdownMenu3")).click();
		driver.findElement(By.cssSelector("div.desktop >div.selectact>div:first-child>div>div>a:nth-child(2)")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("schEmailForm")));
		driver.findElement(By.id("schEmailSubject")).sendKeys("Hi This is Scheduled Mail");
		action.sendKeys(Keys.TAB).sendKeys("Schedule mail body").sendKeys(Keys.PAGE_DOWN).build().perform();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#hr+div")).click();
		WebElement list = driver.findElement(By.cssSelector("#hr+div>ul"));
		WebElement time = driver.findElement(By.cssSelector("#hr+div>ul>li.selected"));
		action.moveToElement(list).scrollToElement(time).build().perform();
		driver.findElement(By.cssSelector("#hr+div>ul>li.selected+li")).click();
		driver.findElement(By.cssSelector(".schedule_msgmail_toggle+div>a:last-of-type")).click();
		String Scmail = driver.findElement(By.cssSelector("div#toast-container > div > div")).getText();
		System.out.println("	"+Scmail);

		String AScCount = driver.findElement(By.cssSelector("a#show_old_remaining_filters.desktopoption+a+a+a>span")).getText();
		System.out.println("	Count Of Scheduled Messages After Scheduling Mail: "+AScCount);
		Assert.assertEquals(BScCount, AScCount,"Schedule Message Count is Not Updating");
		driver.findElement(By.cssSelector("a#show_old_remaining_filters.desktopoption+a+a+a")).click();

		try
		{
			if(driver.findElement(By.cssSelector("#custom-sms-contact-list>div.msg-list")).isDisplayed())
			{
				System.out.println("	Listing Found After Applying Quick Filter");
			}

		}
		catch(Exception e)
		{
			Assert.assertTrue(false,"Listing not found After Applying Quick filter");
		}

		//Cancelling Rescheduled msg
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.response-schedule-toggle>div>div>div.absolute.right-1>div>a")).click();
		driver.findElement(By.cssSelector("a[data-target=\"#schedule_email_modal\"]+a")).click();//Cancel option
		driver.findElement(By.cssSelector("#deleteConfirmation_form>div>button")).click();

		try
		{
			if(driver.findElement(By.cssSelector("#custom-sms-contact-list>div.msg-list")).isDisplayed())
			{
				System.out.println("	Listing Found After Applying Quick Filter - After Cancel msg");
			}

		}
		catch(Exception e)
		{
			System.out.println("	Listing Not Found After Applying Quick Filter - After Cancel msg");
		}

		Thread.sleep(2000);
		String AScCount1 = driver.findElement(By.cssSelector("a#show_old_remaining_filters.desktopoption+a+a+a>span")).getText();
		System.out.println("	Count Of Scheduled Messages After Cancelling Scheduling Mail: "+AScCount1);
		Assert.assertEquals(BScCount1, AScCount1,"Count Not Updating after Cancelling Scheduled Mail");

		driver.navigate().back();
		Thread.sleep(2000);
		//Action menu- Schedule SMS
		driver.findElement(By.cssSelector("div.desktop >div.selectact>div:first-child>div>a#dropdownMenu3")).click();
		driver.findElement(By.cssSelector("div.desktop >div.selectact>div:first-child>div>div>a:nth-child(3)")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("schSmsForm")));
		driver.findElement(By.id("display_schedule_template")).sendKeys("Hi This is Scheduled SMS");
		driver.findElement(By.cssSelector(".schedule-date-picker>div:nth-child(2)>div>div:first-of-type")).click();
		WebElement list1 = driver.findElement(By.cssSelector(".schedule-date-picker>div:nth-child(2)>div>div:first-of-type>ul"));
		WebElement time1 = driver.findElement(By.cssSelector(".schedule-date-picker>div:nth-child(2)>div>div:first-of-type>ul>li.selected+li"));
		action.moveToElement(list1).scrollToElement(time1).build().perform();
		driver.findElement(By.cssSelector(".schedule-date-picker>div:nth-child(2)>div>div:first-of-type>ul>li.selected+li")).click();
		driver.findElement(By.cssSelector("#schSmsSubmitBtn+a+a")).click();
		String Scsms = driver.findElement(By.cssSelector("div#toast-container > div > div")).getText();
		System.out.println("	"+Scsms);

		driver.findElement(By.cssSelector("a#show_old_remaining_filters.desktopoption")).click();
		String ASSMS = driver.findElement(By.cssSelector("a#show_old_remaining_filters.desktopoption+a+a+a>span")).getText();
		System.out.println("	Count Of Scheduled Messages After Scheduling SMS: "+ASSMS);
		Assert.assertEquals(ASSMS, AScCount, "Count Not Updating after Cancelling Scheduled Mail");

		driver.findElement(By.cssSelector("a#show_old_remaining_filters.desktopoption+a+a+a")).click();
		try
		{
			if(driver.findElement(By.cssSelector("#custom-sms-contact-list>div.msg-list")).isDisplayed())
			{
				System.out.println("	Listing Found After Applying Quick Filter");
			}

		}
		catch(Exception e)
		{
			Assert.assertTrue(false,"Listing not found After Applying Quick filter");
		}
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.response-schedule-toggle>div>div>div>div>a")).click();
		driver.findElement(By.cssSelector("div.dropdown-schedule-sms>a:nth-child(2)")).click();
		driver.findElement(By.cssSelector("#deleteConfirmation_form>div>button")).click();

		try
		{
			if(driver.findElement(By.cssSelector("#custom-sms-contact-list>div.msg-list")).isDisplayed())
			{
				System.out.println("	Listing Found After Applying Quick Filter");
			}

		}
		catch(Exception e)
		{
			Assert.assertTrue(false,"Listing not found After Applying Quick filter");
		}
		Thread.sleep(2000);
		String ADSMS = driver.findElement(By.cssSelector("a#show_old_remaining_filters.desktopoption+a+a+a>span")).getText();
		System.out.println("	Count Of Scheduled Messages After Canelling Scheduled SMS: "+ADSMS);
		Assert.assertEquals(ADSMS, AScCount1, "Count Not Updating after Cancelling Scheduled Mail");

		driver.navigate().back();

		//Action menu- Send booking invite
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.desktop >div.selectact>div:first-child>div>a#dropdownMenu3")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.desktop >div.selectact>div:first-child>div>div>a:nth-child(5)")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("new-booking-invite-global")));
		driver.findElement(By.id("booking_invite_eventname_id")).click();
		driver.findElement(By.cssSelector("#booking_invite_list_event>label:first-of-type")).click();
		driver.findElement(By.cssSelector(".invite_error_msgs+div>div>button")).click();
		String BI = driver.findElement(By.cssSelector("div#toast-container > div > div")).getText();
		System.out.println("	"+BI);

		//Action menu - Add To campaign
		driver.findElement(By.cssSelector("div.desktop >div.selectact>div:first-child>div>a#dropdownMenu3")).click();
		Thread.sleep(1000);
		jse.executeScript("document.querySelector('div.mt-2.selectact>div:first-child>div>div').scrollTop = 200");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.desktop >div.selectact>div:first-child>div>div>a:nth-child(6)")));
		driver.findElement(By.cssSelector("div.desktop >div.selectact>div:first-child>div>div>a:nth-child(6)")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-campaign-global")));
		jse.executeScript("document.querySelector('div.mt-2.selectact>div:first-child>div>div').scrollTop = 200");
		driver.findElement(By.cssSelector("select#campaign+div")).click();
		driver.findElement(By.cssSelector("select#campaign+div>ul>li:nth-child(2)")).click();
		driver.findElement(By.cssSelector("div.add_camp_form+div+div>button")).click();
		String add = driver.findElement(By.cssSelector("div#toast-container > div > div")).getText();
		System.out.println("	Adding Customer to the campaign:"+add);
		Thread.sleep(2000);

		//Action menu - Add note
		driver.findElement(By.cssSelector("div.desktop >div.selectact>div:first-child>div>a#dropdownMenu3")).click();
		jse.executeScript("document.querySelector('div.mt-2.selectact>div:first-child>div>div').scrollTop = 200");
		driver.findElement(By.cssSelector("div.desktop >div.selectact>div:first-child>div>div>a:nth-child(7)")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("dashboard_note_text")).sendKeys("Test Note From action menu");
		driver.findElement(By.id("add_a_note_popup_btn")).click();
		String noteadd = driver.findElement(By.cssSelector("div#toast-container > div > div")).getText();
		System.out.println("	Adding Note From Action menu"+noteadd);

		//Action menu- Schedule Appointment
		driver.findElement(By.cssSelector("div.desktop >div.selectact>div:first-child>div>a#dropdownMenu3")).click();
		driver.findElement(By.cssSelector("div.desktop >div.selectact>div:first-child>div>div>a:nth-child(4)")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("new-open-comman-appointment-modal-global")));
		driver.findElement(By.id("meeting_titles")).sendKeys("One off meeting from message");
		action.sendKeys(Keys.PAGE_DOWN).perform();
		driver.findElement(By.cssSelector("#userbooking+div>button")).click();
		String Scapp = driver.findElement(By.cssSelector("div#toast-container > div > div")).getText();
		System.out.println("	"+Scapp);
		//validating in calendar
		driver.navigate().to(Calendar);
		driver.findElement(By.name("appsearch")).sendKeys("One off meeting from message");
		action.sendKeys(Keys.ENTER).perform();
		String timeDetails = driver.findElement(By.cssSelector("div.new-single-appointment.grid.rounded-lg>div:first-of-type")).getText();
		String datedetails = driver.findElement(By.cssSelector("h4.text-xl.font-extrabold.darkColor.mr-auto")).getText();
		String Apptname = driver.findElement(By.cssSelector("div.new-single-appointment.grid.rounded-lg>h5")).getText();

		System.out.println();
		System.out.println("	Appointment Details From Appointment page");
		System.out.println("	Appointment Name: "+Apptname);
		System.out.println("	Appointment Date: "+datedetails);
		System.out.println("	Appotment Time: "+timeDetails);

		driver.findElement(By.cssSelector("div.new-appointment-box>div>div:nth-child(3)>div>h5")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#new-open-comman-appointmentdetails-modal>div>div>div:nth-child(2)>div:nth-child(2)>a:nth-child(3)")).click();
		driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
	}

	//@Test
	public void MessageFilters() throws Exception 
	{
		Actions action =new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
		JavascriptExecutor jse= (JavascriptExecutor)driver;

		System.out.println();
		System.out.println("Getting Message Filters Result ----->");

		//Getting Company ID
		String CompId = driver.findElement(By.id("globalcall_dialer")).getAttribute("data-companyid");
		int cid = Integer.parseInt(CompId);

		driver.navigate().to(Messages);
		Thread.sleep(2000);

		String[] FilterCount = new String[10];
		String[] FilterContent = new String[10];

		for(int i = 0; i<=9; i++)
		{
			if(i == 0)
			{
				// verifying current date filter
				System.out.println();
				System.out.println("Filter Applied: Date Range(For Today's Date)");
				driver.findElement(By.id("dis_advfilters")).click();//filter button
				Thread.sleep(1000);
				driver.findElement(By.id("ctSchedulesStart")).click();
				driver.findElement(By.cssSelector("button.applyBtn")).click();//Apply button In Date picker
			}
			else if(i == 1)
			{
				//Applying Filter for notes with reminder 
				driver.navigate().to(Messages);
				Thread.sleep(1000);
				System.out.println();
				System.out.println("Filter Applied: Has Notes With Reminder");
				driver.findElement(By.id("dis_advfilters")).click();//filter button
				driver.findElement(By.cssSelector("div#notesremindercustomersmessagefilter > a")).click();
			}
			else if(i == 2)
			{
				//Applying Filter for pipeline W/O Stage
				driver.navigate().to(Messages);
				System.out.println();
				System.out.println("Filter Applied: Pipeline W/O Stage");

				driver.findElement(By.id("dis_advfilters")).click();//filter button
				Thread.sleep(1000);
				driver.findElement(By.id("ctPipelineIds")).click();
				String PipelineId = driver.findElement(By.cssSelector("#pipeline-typeFilterList>label:nth-child(2)")).getAttribute("data-value");
				System.out.println(PipelineId);
				driver.findElement(By.cssSelector("#pipeline-typeFilterList>label:nth-child(2)")).click();
			}
			else if(i == 3)
			{
				//Applying Filter for pipeline With Stage
				System.out.println();
				System.out.println("Filter Applied: Pipeline With Stage");

				driver.findElement(By.id("dis_advfilters")).click();//filter button
				Thread.sleep(1000);
				driver.findElement(By.id("ctStagesIds")).click();
				String StageId = driver.findElement(By.cssSelector("#stagesFilterList>label:nth-child(3)")).getAttribute("data-value");
				System.out.println(StageId);
				driver.findElement(By.cssSelector("#stagesFilterList>label:nth-child(3)")).click();
			}
			else if(i == 4)
			{
				//Assigned customers
				System.out.println();
				System.out.println("Filter Applied: Assigned users");

				driver.navigate().to(Messages);
				driver.findElement(By.id("dis_advfilters")).click();//filter button
				Thread.sleep(1000);
				driver.findElement(By.id("ctAssignedToIds")).click();

				WebElement assign1 =  driver.findElement(By.cssSelector("div#assignedToFilterList>label:nth-of-type(2)"));
				String UserId = assign1.getAttribute("data-value");
				System.out.println(UserId);
				assign1.click();
			}
			else if(i == 5)
			{
				//With Tags
				System.out.println();
				System.out.println("Filter Applied: With Tags");

				driver.navigate().to(Messages);
				driver.findElement(By.id("dis_advfilters")).click();//filter button
				Thread.sleep(1000);
				driver.findElement(By.id("selected_Tagssee")).click();
				String TagId = driver.findElement(By.cssSelector("#tagesFilterList>label:nth-child(3)")).getAttribute("data-value");
				System.out.println(TagId);
				driver.findElement(By.cssSelector("#tagesFilterList>label:nth-child(3)")).click();
			}
			else if(i == 6)
			{
				//Campaigns Received
				System.out.println();
				System.out.println("Filter Applied: Campaigns Received");

				driver.navigate().to(Messages);
				driver.findElement(By.id("dis_advfilters")).click();//filter button
				Thread.sleep(1000);
				driver.findElement(By.id("campaignIds")).click();
				String CampId = driver.findElement(By.cssSelector("#campaignFilterList>label:nth-child(3)")).getAttribute("data-value");
				System.out.println(CampId);
				driver.findElement(By.cssSelector("#campaignFilterList>label:nth-child(3)")).click();
			}
			else if(i == 7)
			{
				//Form Filled
				System.out.println();
				System.out.println("Filter Applied: Form Filled");

				driver.navigate().to(Messages);
				driver.findElement(By.id("dis_advfilters")).click();//filter button
				Thread.sleep(1000);
				driver.findElement(By.id("ctformsIds")).click();
				String FormId = driver.findElement(By.cssSelector("#formsFilterList>label:nth-child(3)")).getAttribute("data-value");
				System.out.println(FormId);
				driver.findElement(By.cssSelector("#formsFilterList>label:nth-child(3)")).click();
			}
			else if(i == 8)
			{
				//App Type Booked
				System.out.println();
				System.out.println("Filter Applied: App Type Booked");

				driver.navigate().to(Messages);
				driver.findElement(By.id("dis_advfilters")).click();//filter button
				Thread.sleep(1000);
				driver.findElement(By.id("ctformsIds")).click();
				jse.executeScript("document.querySelector('form#ml_filters_form > div').scrollTop=300");
				String AptId = driver.findElement(By.cssSelector("#cteventsIds>label:nth-child(3)")).getAttribute("data-value");
				System.out.println(AptId);
				driver.findElement(By.cssSelector("#cteventsIds>label:nth-child(3)")).click();
				driver.findElement(By.id("ctformsIds")).click();
			}
			else if(i == 9)
			{
				//In List
				System.out.println();
				System.out.println("Filter Applied: In List");

				driver.navigate().to(Messages);
				driver.findElement(By.id("dis_advfilters")).click();//filter button
				Thread.sleep(1000);
				driver.findElement(By.id("ctformsIds")).click();
				jse.executeScript("document.querySelector('form#ml_filters_form > div').scrollTop=300");
				String ListId = driver.findElement(By.cssSelector("#ctListsIds>label:nth-child(3)")).getAttribute("data-value");
				System.out.println(ListId);
				driver.findElement(By.cssSelector("#ctListsIds>label:nth-child(3)")).click();
				driver.findElement(By.id("ctformsIds")).click();
			}

			jse.executeScript("document.querySelector('form#ml_filters_form > div').scrollTop=300");
			Thread.sleep(3000);
			FilterCount[i] = driver.findElement(By.cssSelector("span.message-count-for-change")).getText();//filter count
			System.out.println("	Contact Count on Filter: "+FilterCount[i]);
			driver.findElement(By.cssSelector("input[value=Search]")).click();

			Thread.sleep(2000);
			List<WebElement> CDF = driver.findElements(By.cssSelector("div.msg-list.relative.flex.items-center"));
			int count = CDF.size(); 

			do
			{
				int a = 0; 
				a = a + 2000;
				jse.executeScript("document.querySelector('#custom-sms-contact-list').scrollTop = "+a+"");
				Thread.sleep(2000);
				CDF = driver.findElements(By.cssSelector("div.msg-list.relative.flex.items-center"));
				count = CDF.size();
				FilterContent[i] = Integer.toString(count);
				if(FilterCount[i].equals(FilterContent[i]))
				{
					break;
				}
			}
			while(count%20 == 0);

			System.out.println("	Actual Contacts Filtered: "+FilterContent[i]);
		}

		/*/adminer login------------------------>
		driver.get(DBURL);	
		driver.findElement(By.name("auth[server]")).sendKeys(DBServer);
		driver.findElement(By.id("username")).sendKeys(DBUName);
		driver.findElement(By.name("auth[password]")).sendKeys(DBPass);
		driver.findElement(By.name("auth[db]")).sendKeys(DB);
		driver.findElement(By.cssSelector("input[value='Login']")).click();

		//Getting Count For Date Filter
		driver.findElement(By.linkText("SQL command")).click();
		action.sendKeys("SELECT count(distinct c.id)\r\n"
				+ "FROM customers as c \r\n"
				+ "LEFT JOIN messagelog as mlg \r\n"
				+ "ON mlg.id_customer=c.id \r\n"
				+ "WHERE (mlg.timesent > UNIX_TIMESTAMP(Current_date()) AND c.is_enabled=1 AND c.deleted=0 AND c.id_company="+cid+" AND c.archived=0) ");
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
		String CreatedToday = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();

		//Getting count for unread customers
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).clear();
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).sendKeys("SELECT count(distinct c.id)\r\n"
				+ "FROM customers as c \r\n"
				+ "LEFT JOIN messagelog as mlq \r\n"
				+ "ON mlq.id_customer=c.id \r\n"
				+ "WHERE (c.unread != 0 AND c.id_company="+cid+" AND c.archived=0)");
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
		String DBunread = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();

		//Testing count of untouched customers	
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).clear();
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).sendKeys("SELECT  count(distinct c.id)\r\n"
				+ "FROM customers as c \r\n"
				+ "LEFT JOIN messagelog as mlq \r\n"
				+ "ON mlq.id_customer=c.id \r\n"
				+ "WHERE (c.is_manually_contacted = 0 AND c.is_enabled=1 AND c.deleted=0 AND c.id_company="+cid+" AND c.archived=0)");
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
		String untouch = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();
		System.out.println("From Database: "+untouch);

		//Testing Notes with remainder count
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).clear();
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).sendKeys("Select count(distinct c.id)\r\n"
				+ "from customers c \r\n"
				+ "left join customer_notes cn\r\n"
				+ "on c.id = cn.id_customer \r\n"
				+ "where(cn.timescheduled != 0 and c.is_enabled=1 and c.deleted=0 and c.id_company = "+cid+")");
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
		String notes = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();
		System.out.println("From Database: "+notes);

		//Testing Assigned user 
		String[] DBAUC = new String[usercount];
		int j = 0;
		for(int i=1;i<=usercount;i++)
		{
			driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).clear();
			String UID = arruserid[j];
			j++;		
			driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).sendKeys("SELECT count(distinct c.id)\r\n"
					+ "FROM customers c\r\n"
					+ "LEFT JOIN messagelog as mlq \r\n"
					+ "ON mlq.id_customer=c.id \r\n"
					+ "where (c.id_company = "+cid+" and c.addedby="+UID+" \r\n"
					+ "AND c.is_enabled=1 AND c.deleted=0 AND c.archived=0)");
			action.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
			String DBAssignedusercount = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();
			DBAUC[i-1] = DBAssignedusercount;
		}

		//Testing Form filled filters
		for(int i=1;i<=usercount;i++)
		{
			driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).clear();
			String UID = arruserid[j];
			j++;		
			driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).sendKeys("SELECT count(distinct c.id)\r\n"
					+ "FROM customers c\r\n"
					+ "LEFT JOIN messagelog as mlq \r\n"
					+ "ON mlq.id_customer=c.id \r\n"
					+ "where (c.id_company = "+cid+" and c.addedby="+UID+" \r\n"
					+ "AND c.is_enabled=1 AND c.deleted=0 AND c.archived=0)");
			action.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
			String DBAssignedusercount = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();
			DBAUC[i-1] = DBAssignedusercount;
		}



		System.out.println("	Unread Filter:");
		System.out.println("		Filter Count From CRM: "+s);
		System.out.println("		Filter Count From DB : "+DBunread);
		System.out.println();

		System.out.println("	Create date Filter(Today's Created)");
		System.out.println("		Filter Count From CRM: "+s1);
		System.out.println("		Filter Count From DB : "+CreatedToday);
		System.out.println();

		System.out.println("	Untouched Count Filter");
		System.out.println("		Filter Count From CRM: "+s2);
		System.out.println("		Filter Count From DB : "+untouched);
		System.out.println();

		System.out.println("	Assign To User Filter: ");
		for(String datar:arrAssigncustomercount)
		{
			System.out.println("		Filter Count From CRM: "+datar);
		}
		for(String datar1:DBAUC)
		{
			System.out.println("		Filter Count From DB : "+datar1);
		}

		System.out.println();
		System.out.println("	Form Filled Filter: ");
		for(String datar1:arrformcount)
		{
			System.out.println("		Filter Count From CRM: "+datar1);
		}
		for(String datar1:DBAUC)
		{
			System.out.println("		Filter Count From DB : "+datar1);
		}

		System.out.println();
		System.out.println("	Notes With Remainder Filter");
		System.out.println("		Filter Count From CRM: "+s3);
		System.out.println("		Filter Count From DB : "+notes);
		System.out.println();
		 */
	}

	//@Test
	public void CMP() throws Exception 
	{
		Actions action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor jse = (JavascriptExecutor)driver;

		/*/Adding New Tag
		driver.navigate().to(TagManager);
		driver.findElement(By.name("searchtag")).sendKeys("CMPAutomationTag");
		action.sendKeys(Keys.ENTER).perform();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#selectAll+span")).click();
		driver.findElement(By.cssSelector("button.deletebulktags ")).click();
		driver.findElement(By.cssSelector("div.selectize-control.custom-tag-input>div>input")).sendKeys("CMPAutomationTag");
		action.sendKeys(Keys.ENTER).perform();
		driver.findElement(By.id("Add_tags")).click();
		 */
		System.out.println();
		System.out.println("Checking Customer Model Popup ----->");

		driver.navigate().to(Messages);
		String MName = "Messages";// Update String With Modules
		Thread.sleep(4000);
		String BScCount = driver.findElement(By.cssSelector("a#show_old_remaining_filters.desktopoption+a")).getAttribute("data-scheduledcount");

		driver.findElement(By.name("filters")).sendKeys("Weaired testname");
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(3000);

		System.out.println("	Checking Pipeline in Customer Model popup ");
		String PipelineID = driver.findElement(By.cssSelector("div[data-target=\"#manage-pipeline-stage\"]>div>div:first-child>div>input.pipeline_selected_id_sidebar")).getAttribute("value");
		System.out.println("	Current Pipeline: "+PipelineID);

		List <WebElement> Tags = driver.findElements(By.cssSelector("#cusInfo_shorttags>div.inline-tags-div>div"));

		String[] Tag = new String[Tags.size()];
		for(int i = 1; i <= Tags.size(); i++)
		{
			Tag[i-1] = driver.findElement(By.cssSelector("#cusInfo_shorttags>div.inline-tags-div>div:nth-child("+i+")")).getText();
		}

		driver.findElement(By.cssSelector("#hide_message_slideout+div>div>a")).click();
		Thread.sleep(500);
		driver.findElement(By.id("customer-global-modal")).isDisplayed();

		/*		String[] Tagonpopup = new String[Tags.size()];
		for(int i = 1; i <= Tags.size(); i++)
		{
			Tagonpopup[i-1] = driver.findElement(By.cssSelector("div.addmoretags>div:nth-child("+i+")")).getText();
		}
		Assert.assertEquals(Tag, Tagonpopup, "Tags Mismatched on Popup");

		//Applying New tags
		driver.findElement(By.cssSelector("div.cusInfo_appendtage>div>div>a")).click();
		driver.findElement(By.id("js-tags-selector-selectized")).sendKeys("CMPAutomationTag");
		action.sendKeys(Keys.ENTER).build().perform();
		driver.findElement(By.cssSelector("#manage-tag-popup>div>div>div>div")).click();
		driver.findElement(By.cssSelector("button#linkdone")).click();
		Thread.sleep(3000);
		String MSG = driver.findElement(By.id("toast-container")).getText();
		System.out.println("	"+MSG);	

		//Checking Pipeline in CMP
		String PipelineCMP = driver.findElement(By.cssSelector("input.pipelinestageslisthiddensideglobal")).getAttribute("value");

		Assert.assertEquals(PipelineID, PipelineCMP, "Pipeline Name Not Matching on CMP");

		//Editing Customer Details
		Thread.sleep(1000);
		driver.findElement(By.id("CIM_editfields")).click();
		Thread.sleep(500);
		driver.findElement(By.name("company")).clear();
		driver.findElement(By.name("company")).sendKeys("Nadsoft IT Solutions");
		driver.findElement(By.name("city")).clear();
		driver.findElement(By.name("city")).sendKeys("Pune");
		driver.findElement(By.name("value")).clear();
		driver.findElement(By.name("value")).sendKeys("10");
		driver.findElement(By.id("CIM_savefields")).click();
		Thread.sleep(3000);
		String update = driver.findElement(By.id("toast-container")).getText();
		System.out.println("	"+update);

		//Send SMS
		driver.findElement(By.cssSelector("a.cust_open_message_tab")).click();
		driver.findElement(By.id("global-dashboard-conversation-text")).sendKeys("SMS Sent From CMP Module:" +MName);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("a.convoSendSmsBtn")).click();
		Thread.sleep(2000);

		//Sending Email
		driver.findElement(By.id("CM_calling_email")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("subject_email_global")).sendKeys("This Email From CMP Module:" +MName);
		action.sendKeys(Keys.TAB).sendKeys("Test Mail From CMP").build().perform();
		Thread.sleep(2000);
		driver.findElement(By.id("globalConvoSendEmailBtn")).click();
		 */
		//Scheduling Mail
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.cust-popup-sidebar>div>div>div>a#dropdownMenu3")).click();
		Thread.sleep(500);
		driver.findElement(By.cssSelector("a.popup-schedule-email-btn")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("schEmailForm")));
		driver.findElement(By.id("schEmailSubject")).sendKeys("Hi This is Scheduled Mail From CMP Module:" +MName);
		action.sendKeys(Keys.TAB).sendKeys("Schedule mail body").sendKeys(Keys.PAGE_DOWN).build().perform();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#hr+div")).click();
		WebElement list = driver.findElement(By.cssSelector("#hr+div>ul"));
		WebElement time = driver.findElement(By.cssSelector("#hr+div>ul>li.selected"));
		action.moveToElement(list).scrollToElement(time).build().perform();
		driver.findElement(By.cssSelector("#hr+div>ul>li.selected+li")).click();
		driver.findElement(By.cssSelector(".schedule_msgmail_toggle+div>a:last-of-type")).click();
		String Scmail = driver.findElement(By.cssSelector("div#toast-container > div > div")).getText();
		System.out.println("	"+Scmail);

		//Sceduled Message Count Validations
		String AScCount = driver.findElement(By.cssSelector("a#show_old_remaining_filters.desktopoption+a")).getAttribute("data-scheduledcount");
		System.out.println("	Count Of Scheduled Messages Before Scheduling Mail: "+BScCount);
		System.out.println("	Count Of Scheduled Messages After Scheduling Mail: "+AScCount);
		//Assert.assertEquals(BScCount, AScCount,"Schedule Message Count is Not Updating");

		Thread.sleep(2000);
		String AScCount1 = driver.findElement(By.cssSelector("a#show_old_remaining_filters.desktopoption+a+a+a>span")).getText();
		System.out.println("	Count Of Scheduled Messages After Cancelling Scheduling Mail: "+AScCount1);
		//Assert.assertEquals(BScCount1, AScCount1,"Count Not Updating after Cancelling Scheduled Mail");

		Thread.sleep(2000);

		//Action menu - Schedule SMS
		driver.findElement(By.cssSelector("div.desktop >div.selectact>div:first-child>div>a#dropdownMenu3")).click();
		driver.findElement(By.cssSelector("div.desktop >div.selectact>div:first-child>div>div>a:nth-child(3)")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("schSmsForm")));
		driver.findElement(By.id("display_schedule_template")).sendKeys("Hi This is Scheduled SMS");
		driver.findElement(By.cssSelector(".schedule-date-picker>div:nth-child(2)>div>div:first-of-type")).click();
		WebElement list1 = driver.findElement(By.cssSelector(".schedule-date-picker>div:nth-child(2)>div>div:first-of-type>ul"));
		WebElement time1 = driver.findElement(By.cssSelector(".schedule-date-picker>div:nth-child(2)>div>div:first-of-type>ul>li.selected+li"));
		action.moveToElement(list1).scrollToElement(time1).build().perform();
		driver.findElement(By.cssSelector(".schedule-date-picker>div:nth-child(2)>div>div:first-of-type>ul>li.selected+li")).click();
		driver.findElement(By.cssSelector("#schSmsSubmitBtn+a+a")).click();
		String Scsms = driver.findElement(By.cssSelector("div#toast-container > div > div")).getText();
		System.out.println("	"+Scsms);

		driver.findElement(By.cssSelector("a#show_old_remaining_filters.desktopoption")).click();
		String ASSMS = driver.findElement(By.cssSelector("a#show_old_remaining_filters.desktopoption+a+a+a>span")).getText();
		System.out.println("	Count Of Scheduled Messages After Scheduling SMS: "+ASSMS);


		driver.findElement(By.cssSelector("a#show_old_remaining_filters.desktopoption+a+a+a")).click();
		try
		{
			if(driver.findElement(By.cssSelector("#custom-sms-contact-list>div.msg-list")).isDisplayed())
			{
				System.out.println("	Listing Found After Applying Quick Filter");
			}

		}
		catch(Exception e)
		{
			Assert.assertTrue(false,"Listing not found After Applying Quick filter");
		}
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.response-schedule-toggle>div>div>div>div>a")).click();
		driver.findElement(By.cssSelector("div.dropdown-schedule-sms>a:nth-child(2)")).click();
		driver.findElement(By.cssSelector("#deleteConfirmation_form>div>button")).click();

		try
		{
			if(driver.findElement(By.cssSelector("#custom-sms-contact-list>div.msg-list")).isDisplayed())
			{
				System.out.println("	Listing Found After Applying Quick Filter");
			}

		}
		catch(Exception e)
		{
			Assert.assertTrue(false,"Listing not found After Applying Quick filter");
		}
		Thread.sleep(2000);
		String ADSMS = driver.findElement(By.cssSelector("a#show_old_remaining_filters.desktopoption+a+a+a>span")).getText();
		System.out.println("	Count Of Scheduled Messages After Canelling Scheduled SMS: "+ADSMS);
		Assert.assertEquals(ADSMS, AScCount1, "Count Not Updating after Cancelling Scheduled Mail");

		driver.navigate().back();

		//Action menu- Send booking invite
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.desktop >div.selectact>div:first-child>div>a#dropdownMenu3")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.desktop >div.selectact>div:first-child>div>div>a:nth-child(5)")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("new-booking-invite-global")));
		driver.findElement(By.id("booking_invite_eventname_id")).click();
		driver.findElement(By.cssSelector("#booking_invite_list_event>label:first-of-type")).click();
		driver.findElement(By.cssSelector(".invite_error_msgs+div>div>button")).click();
		String BI = driver.findElement(By.cssSelector("div#toast-container > div > div")).getText();
		System.out.println("	"+BI);

		//Action menu - Add To campaign
		driver.findElement(By.cssSelector("div.desktop >div.selectact>div:first-child>div>a#dropdownMenu3")).click();
		Thread.sleep(1000);
		jse.executeScript("document.querySelector('div.mt-2.selectact>div:first-child>div>div').scrollTop = 200");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.desktop >div.selectact>div:first-child>div>div>a:nth-child(6)")));
		driver.findElement(By.cssSelector("div.desktop >div.selectact>div:first-child>div>div>a:nth-child(6)")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-campaign-global")));
		jse.executeScript("document.querySelector('div.mt-2.selectact>div:first-child>div>div').scrollTop = 200");
		driver.findElement(By.cssSelector("select#campaign+div")).click();
		driver.findElement(By.cssSelector("select#campaign+div>ul>li:nth-child(2)")).click();
		driver.findElement(By.cssSelector("div.add_camp_form+div+div>button")).click();
		String add = driver.findElement(By.cssSelector("div#toast-container > div > div")).getText();
		System.out.println("	Adding Customer to the campaign:"+add);
		Thread.sleep(2000);

		//Action menu - Add note
		driver.findElement(By.cssSelector("div.desktop >div.selectact>div:first-child>div>a#dropdownMenu3")).click();
		jse.executeScript("document.querySelector('div.mt-2.selectact>div:first-child>div>div').scrollTop = 200");
		driver.findElement(By.cssSelector("div.desktop >div.selectact>div:first-child>div>div>a:nth-child(7)")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("dashboard_note_text")).sendKeys("Test Note From action menu");
		driver.findElement(By.id("add_a_note_popup_btn")).click();
		String noteadd = driver.findElement(By.cssSelector("div#toast-container > div > div")).getText();
		System.out.println("	Adding Note From Action menu"+noteadd);

		//Action menu- Schedule Appointment
		driver.findElement(By.cssSelector("div.desktop >div.selectact>div:first-child>div>a#dropdownMenu3")).click();
		driver.findElement(By.cssSelector("div.desktop >div.selectact>div:first-child>div>div>a:nth-child(4)")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("new-open-comman-appointment-modal-global")));
		driver.findElement(By.id("meeting_titles")).sendKeys("One off meeting from message");
		action.sendKeys(Keys.PAGE_DOWN).perform();
		driver.findElement(By.cssSelector("#userbooking+div>button")).click();
		String Scapp = driver.findElement(By.cssSelector("div#toast-container > div > div")).getText();
		System.out.println("	"+Scapp);
		//validating in calendar
		driver.navigate().to(Calendar);
		driver.findElement(By.name("appsearch")).sendKeys("One off meeting from message");
		action.sendKeys(Keys.ENTER).perform();
		String timeDetails = driver.findElement(By.cssSelector("div.new-single-appointment.grid.rounded-lg>div:first-of-type")).getText();
		String datedetails = driver.findElement(By.cssSelector("h4.text-xl.font-extrabold.darkColor.mr-auto")).getText();
		String Apptname = driver.findElement(By.cssSelector("div.new-single-appointment.grid.rounded-lg>h5")).getText();

		System.out.println();
		System.out.println("	Appointment Details From Appointment page");
		System.out.println("	Appointment Name: "+Apptname);
		System.out.println("	Appointment Date: "+datedetails);
		System.out.println("	Appotment Time: "+timeDetails);

		driver.findElement(By.cssSelector("div.new-appointment-box>div>div:nth-child(3)>div>h5")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#new-open-comman-appointmentdetails-modal>div>div>div:nth-child(2)>div:nth-child(2)>a:nth-child(3)")).click();
		driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
	}
}
