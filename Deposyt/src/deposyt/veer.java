package deposyt;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import AccessLevel.Data;

public class veer extends Data 
{
	@BeforeMethod
	public void navigation() 
	{
		driver.navigate().to(Dashboard);
	}

	@Test(priority = 1)
	public void Caseid001_002_003() throws Exception////Create campaign button displayed on dashboard if the access is restricted &  created by user name shows wrong for admin user 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);
		JavascriptExecutor jse = (JavascriptExecutor)driver;

		driver.navigate().to(UserManagment);//Getting Access level name
		String Access = driver.findElement(By.cssSelector("table>tbody>tr:first-of-type")).getAttribute("data-access-level");
		List <WebElement> test = driver.findElements(By.cssSelector("#invite_users_lists>label"));

		driver.navigate().to(AccessLevelPage);
		String a = null;

		for(int i=2; i<= test.size()+1;i++)
		{
			a = driver.findElement(By.cssSelector("div#accesslevel_section>div:nth-of-type("+i+")>div>div>div>h1:first-of-type")).getText();

			if(Access.equalsIgnoreCase (a))
			{
				driver.findElement(By.cssSelector("div#accesslevel_section>div:nth-of-type("+i+")>div>div+div>a:first-of-type")).click();
				break;
			}
		}

		System.out.println();
		System.out.println("Caseid_001:  create campaign button displayed on dashboard if the access is restricted");
		System.out.println("	Setting Access Levels-->");

		//Setting Access Level to None for Campaign
		action.scrollByAmount(0, 6165).perform();
		Thread.sleep(1000);

		for(int i=1;i<=3;i++)
		{
			driver.findElement(By.cssSelector("div.module-wrapper:nth-of-type(11)>div:nth-of-type("+i+")>div.flex.access-level-buttons>button:first-of-type")).click();
		}
		System.out.println("	Access Level Set For Campaign: None");

		//Setting Access Level to Modify for Notes
		action.scrollByAmount(0, 7135).perform();
		Thread.sleep(1000);

		for(int i=1;i<=3;i++)
		{
			driver.findElement(By.cssSelector("div.module-wrapper:nth-of-type(14)>div:nth-of-type("+i+")>div.flex.access-level-buttons>button:Last-of-type")).click();
		}
		System.out.println("	Access Level Set For Notes: Modify");

		driver.findElement(By.cssSelector("button[data-type=access_level]")).click();//save button in access level
		Thread.sleep(5000);

		driver.navigate().to(UserManagment);//user profile page

		action.moveByOffset(100, 100).click().sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).build().perform();
		driver.findElement(By.cssSelector("table>tbody>tr:first-of-type>td:nth-child(10)>div>a")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("table>tbody>tr:first-of-type>td:nth-child(10)>div>div>a:first-of-type")).click();//login to user account
		driver.switchTo().alert().accept();

		//Validation For Campaign
		action.sendKeys(Keys.PAGE_DOWN).perform();
		try
		{
			driver.findElement(By.cssSelector("div.new-campaign-wrapper>div>i")).click();
		}
		catch(Exception nosuchElementException)
		{
			System.out.println("	Create Campaign Button Not Displyed");
		}

		//Creating New note
		driver.navigate().to(Notes);
		driver.findElement(By.xpath("//*[@id=\"notes-toggler\"]/div[1]/div[1]/div[1]/div[1]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"js-tags-selectorpop-selectized\"]")).sendKeys("a");
		action.sendKeys(Keys.ENTER).perform();
		Thread.sleep(1000);
		driver.findElement(By.id("dashboard_note_text")).sendKeys("This is Test Note 1103");
		driver.findElement(By.id("add_a_note_popup_btn")).click();
		Thread.sleep(2000);
		driver.navigate().refresh();
		System.out.println();

		String user = driver.findElement(By.xpath("//*[@id=\"notes-toggler\"]/div[1]/div[1]/div[3]/div/div/div/div[3]/div/div/div[1]/div[1]/div[1]/h1")).getText();
		String[] user1 = user.split("by",2);


		String loggeduser = driver.findElement(By.cssSelector("h1.profile-user-name")).getText();
		System.out.println("Caseid_002:   created by user name shows wrong for admin user");
		System.out.println("	Logged in User: "+loggeduser);
		System.out.println("	Note Created By(User Login):"+user1[1]);
		Thread.sleep(2000);

		//Relogin to Admin account 
		driver.findElement(By.cssSelector("h1.profile-user-name")).click();
		Thread.sleep(500);
		driver.findElement(By.linkText("Re-Login Back")).click();

		driver.navigate().to(Notes);

		String user22 = driver.findElement(By.cssSelector("#pinnedNotesDiv+div+div>div>div>div>div>div>h1")).getText();
		String[] user12 = user22.split("by",2);
		System.out.println("Note Created By(Admin Login):"+user12[1]);
		assertEquals(user1[1], user12[1],"assertion failed");

		// All notes are not loaded
		System.out.println();
		System.out.println("Caseid_003:  All notes are not loaded")	;

		driver.findElement(By.cssSelector("#notestype>li:first-child")).click();//All Notes filter
		jse.executeScript("document.querySelector('div.appointment-overview').scrollTop=600");
		Thread.sleep(3000);
		String Count = driver.findElement(By.cssSelector("span.notes-tasks-count")).getText();
		System.out.println("	Filter Count: "+Count);

		int count = Integer.parseInt(Count);
		int aa = 0;

		for (int i=0; i<=2; i++)
		{
			jse.executeScript("window.scrollTo(0,document.body.scrollHeight)");
			List <WebElement>  notes = driver.findElements(By.cssSelector(" div.notes-wrapper"));
			aa = notes.size();

			int b = 0;
			if(aa==b)
			{
				System.out.println("	Notes are Not Loading");
				assertEquals(true,false);
			}
			b = aa;
			if(aa!=count)
			{
				i=0;
			}
			Thread.sleep(3000);

		}
		System.out.println("	Notes Loaded:"+aa);
		jse.executeScript("scroll(0,0)");

		//Deleting note
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("a[id=dropdownMenu2]:first-of-type")).click();
		driver.findElement(By.linkText("Delete Note")).click();
		driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
	}

	@Parameters({"uname", "pass"})
	//@Test(priority = 2)
	public void Caseid_004(String uname, String pass) throws Exception 
	{
		driver.get("https://appdev.deposyt.com/index.php?m=account");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		System.out.println();
		System.out.println("Caseid_004:  appdev login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		driver.findElement(By.id("email")).sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pass);
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[4]/button[1]")).click();
		driver.findElement(By.cssSelector("a.openleadpipelinepopup")).click();
		System.out.println("	Login Successful");
	}

	@Test(priority = 3)
	public void Caseid_005() throws Exception // image upload issue (check multiple size images for profile image upload functionality)
	{	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor jse = (JavascriptExecutor)driver;

		driver.navigate().to(MyProfile);
		System.out.println();
		System.out.println("Caseid_005: Image upload issue (check multiple size images for profile image upload functionality)");

		for(int i=0; i<=3;i++)
		{
			Thread.sleep(2000);
			String SRC = driver.findElement(By.id("show_croped_img")).getAttribute("src");
			String a[] = SRC.split("=",2);
			driver.findElement(By.cssSelector("a.removeprofileimagelogo+h1")).click();//Change image button
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("favicon-upload-popup")));
			String msg = null;

			if(i==0) 
			{
				driver.findElement(By.id("favicon_file")).sendKeys(imgpath+"JPEG TEST.jpg");
				msg = " With JPG Format";
			}
			else if(i==1)
			{
				Thread.sleep(1000);
				driver.findElement(By.id("favicon_file")).sendKeys(imgpath+"PNG TEST.png");
				msg = " With PNG Format";
			}
			else if(i==2)
			{
				Thread.sleep(1000);
				driver.findElement(By.id("favicon_file")).sendKeys(imgpath+"test 1.jpg");
				msg = " With JPG Format Test Size 1";
			}
			else if(i==3)
			{
				Thread.sleep(1000);
				driver.findElement(By.id("favicon_file")).sendKeys(imgpath+"test 2.jpg");
				msg = " With JPG Format Test Size 2";
			}

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crop")));
			driver.findElement(By.id("crop")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("savecropimg")).click();
			jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			driver.findElement(By.cssSelector("#users_section>form>div:last-of-type>button")).click();
			Thread.sleep(1000);
			String SRC1 = driver.findElement(By.id("show_croped_img")).getAttribute("src");
			String a1[] = SRC1.split("=",2);
			if(a[1].equalsIgnoreCase(a1[1]))
			{
				System.out.println("	Profile Picture not Updated");
				Assert.assertFalse(true);
			}
			else
			{
				System.out.println("	Profile Picture updated"+msg);
				System.out.println();
			}
		}

	}

	@Test(priority = 4)
	public void Caseid_006() throws Exception //Unread filter autorefresh
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
		System.out.println();
		System.out.println("Caseid_006:  Unread filter autorefresh");
		driver.navigate().to(Messages+"&filters=&dfilters=1");
		List<WebElement> Unread = driver.findElements(By.cssSelector("#custom-sms-contact-list>div"));
		int before = Unread.size();
		Thread.sleep(7000);
		List<WebElement> Unread1 = driver.findElements(By.cssSelector("#custom-sms-contact-list>div"));
		int after = Unread1.size();
		if(before == after)
		{
			System.out.println("	Page Not Refreshed");
		}
		else
		{
			System.out.println("	Page Got Refreshed");
			Assert.assertTrue(false);
		}
	}
	
	@Parameters({"uname", "pass"})
	@Test(priority = 29)
	public void Caseid_007(String uname, String pass ) throws Exception//cancel button not working on campaign response page(add note popup)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
		Actions action = new Actions(driver);
		
		Relogin(uname, pass);
		Thread.sleep(2000);
		driver.navigate().to(Campaigns);
		System.out.println();
		System.out.println("Caseid_007: cancel button not working on campaign response page(add note popup)");

		driver.findElement(By.name("search")).sendKeys("Test Camp Pipeline - o48wj");// update campaign name here
		action.sendKeys(Keys.ENTER).perform();

		String ReplyCount = driver.findElement(By.cssSelector("div.reply-up")).getText();

		if(ReplyCount.equalsIgnoreCase("0"))
		{
			System.out.println("There is No Reply For selected Campaign");
			Assert.assertTrue(false);
		}

		driver.findElement(By.cssSelector("div.reply-up")).click();
		driver.findElement(By.cssSelector("div.customer-more-option:first-of-type>a")).click();
		Thread.sleep(500);
		driver.findElement(By.cssSelector("div.customer-more-option:first-of-type>div>a:nth-child(3)")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#dashboard_note_text+div+div>button:first-child")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.custom-switch>ul>li:first-child>a")).click();
		System.out.println("	Cancel Button on Notes PopUp is working");

	}

	@Parameters({"uname", "pass"})
	@Test(priority = 6)
	public void Case_008_009_010_011(String uname, String pass ) throws Exception
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Actions action =new Actions(driver);

		//Login to david account
		driver.findElement(By.xpath("//*[@id=\"dropdownMenu1\"]")).click();
		driver.findElement(By.linkText("Logout")).click();

		Thread.sleep(4000);
		WebElement emf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		emf.sendKeys(DaveLogin);
		driver.findElement(By.id("password")).sendKeys(DavePass);
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[4]/button[1]")).click();
		Thread.sleep(3000);

		action.moveToElement(driver.findElement(By.cssSelector("div.hidden.sidebar-bg-color"))).perform();
		Thread.sleep(1000);
		String msgcount = driver.findElement(By.cssSelector("#ms-call-noti-count")).getText();
		String Topcount = driver.findElement(By.cssSelector("#call_notification>div")).getText();

		System.out.println();
		System.out.println("Caseid_008: missed call count displayed in messages");
		System.out.println("	Missed call count In Messages: "+msgcount);
		System.out.println("	Missed call Counr On Topbar: "+Topcount);

		System.out.println();
		System.out.println("Caseid_009: Incoming text not recieved for david account");

		driver.findElement(By.id("NotificationMenu")).click();
		driver.findElement(By.cssSelector("a[data-type=\"sms\"]")).click();

		try
		{	
			try
			{
				Thread.sleep(4000);
				System.out.println("	SMS Recived For David's Account On Today: "+driver.findElement(By.cssSelector("h2[data-type=\"today\"]+div")).getAttribute("data-total"));
			}
			catch(Exception e)
			{
				System.out.println("	No SMS Recived On Today");
				System.out.println("	SMS Recived For David's Account On yesterday:" +driver.findElement(By.cssSelector("h2[data-type=\"yes\"]+div")).getAttribute("data-total"));
			}
		}
		catch(Exception e)
		{
			System.out.println("No Recent incoming Message Found for David's Account");
			Assert.assertFalse(true,"No Recent incoming Message Found for David's Account");
		}

		//Custom link I have saved are completely gone overnight
		System.out.println();
		System.out.println("Caseid_010: Custom link I have saved are completely gone overnight");
		driver.findElement(By.cssSelector("#log+a")).click();
		Thread.sleep(500);
		driver.findElement(By.id("coustomurlbtn")).click();

		List<WebElement> CustomLinks = driver.findElements(By.cssSelector("#insertCustomUrl>div"));
		int linkcount  = CustomLinks.size();
		System.out.println("	Custom Link Count For David's Account: "+linkcount);

		//Form url broken for client account
		System.out.println();
		System.out.println("Caseid_011:  Form url broken for client account");
		driver.navigate().to(Forms);

		String win1 = driver.getWindowHandle();
		driver.findElement(By.cssSelector("#templateListTBody>tr:first-child>td:nth-child(6)>div>a")).click();

		Set<String> handles = driver.getWindowHandles();

		for(String handle:handles)
		{
			driver.switchTo().window(handle);	
		}

		System.out.println("	Form URL Working For Client Account: "+driver.findElement(By.cssSelector("body.allbody")).isDisplayed());
		driver.switchTo().window(win1);


		driver.findElement(By.xpath("//*[@id=\"dropdownMenu1\"]")).click();
		driver.findElement(By.linkText("Logout")).click();

		WebElement emf1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		emf1.sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pass);
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[4]/button[1]")).click();
		Assert.assertEquals(msgcount, Topcount,"Missed Call count Missmatched");
	}

	@Test(priority = 7)
	public void Caseid_012() throws Exception//Editing a scheduled message freezes
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Actions action = new Actions(driver);

		System.out.println();
		System.out.println("Caseid_012: Editing a scheduled message freezes");

		driver.navigate().to(Messages);

		Thread.sleep(1000);
		driver.findElement(By.name("filters")).sendKeys("Automation contact");
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(1000);

		//Scheduling MSG
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
		Thread.sleep(4000);

		try
		{
			driver.findElement(By.cssSelector("div.response-schedule-toggle>div>div>div>div>a")).click();
		}
		catch(Exception e)
		{
			//System.out.println(e);
		}

		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.open>div.dropdown-schedule-sms>a:first-of-type")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("display_schedule_template")).sendKeys(" - Edited");
		driver.findElement(By.cssSelector("#schSmsSubmitBtn+a+a")).click();
		String Scsms1 = driver.findElement(By.cssSelector("div#toast-container > div > div")).getText();
		System.out.println("	After Editing: "+Scsms1);
	}

	@Test(priority = 8)
	public void Caseid_013() throws Exception//Editing a note reminder doesn’t save and freezes
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);

		driver.navigate().to(Notes);

		System.out.println();
		System.out.println("Caseid_013: Editing a note reminder doesn’t save and freezes");

		//Adding Note with Reminder
		driver.findElement(By.xpath("//*[@id=\"notes-toggler\"]/div[1]/div[1]/div[1]/div[1]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"js-tags-selectorpop-selectized\"]")).sendKeys("a");
		action.sendKeys(Keys.ENTER).perform();
		Thread.sleep(1000);
		driver.findElement(By.id("dashboard_note_text")).sendKeys("This is Test Note 11000333");
		driver.findElement(By.cssSelector("div.note-option.anc_set_reminder")).click();
		driver.findElement(By.cssSelector("#editreminderco+a")).click();
		driver.findElement(By.id("setnotereminder")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("ul.nav-pills.reminder-pills>li:nth-of-type(2)>a")).click();//advance reminder
		driver.findElement(By.cssSelector("#togBtn_2+span")).click();
		driver.findElement(By.cssSelector("#advanced>a:first-of-type")).click();//set button
		Thread.sleep(1000);
		driver.findElement(By.id("add_a_note_popup_btn")).click();//save note
		Thread.sleep(1000);

		driver.findElement(By.id("dropdownMenu2")).click();
		driver.findElement(By.cssSelector("div.note-option>a[aria-expanded=\"true\"]+div>a:first-of-type")).click();
		Thread.sleep(1000);

		driver.findElement(By.cssSelector("div.note-option.anc_set_reminder>a")).click();
		driver.findElement(By.id("editreminderco")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("ul.nav-pills.reminder-pills>li:nth-of-type(2)>a")).click();
		driver.findElement(By.name("schedule")).click();
		driver.findElement(By.cssSelector("td.ui-datepicker-today")).click();
		driver.findElement(By.cssSelector("div.initniceselect.timeofreminder")).click();
		WebElement two = driver.findElement(By.cssSelector("div.timeofreminder>ul")); 
		WebElement one = driver.findElement(By.cssSelector("div.timeofreminder>ul>li:nth-child(3)"));
		action.moveToElement(two).scrollToElement(one).build().perform();
		one.click();
		driver.findElement(By.cssSelector("div.reminder-tab-content>div:nth-child(2)>a:first-of-type")).click();//set button
		String error = driver.findElement(By.id("error_messages_adv_pop")).getText();
		System.out.println("	Error message Displyed: "+error);
		driver.findElement(By.cssSelector("div.initniceselect.timeofreminder")).click();
		WebElement three = driver.findElement(By.cssSelector("div.timeofreminder>ul>li:last-of-type"));
		action.moveToElement(two).scrollToElement(three).build().perform();
		three.click();
		driver.findElement(By.cssSelector("div.reminder-tab-content>div:nth-child(2)>a:first-of-type")).click();//set button
		Thread.sleep(4000);
		driver.findElement(By.id("add_a_note_popup_btn")).click();// Save Note
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"notes-toggler\"]/div[1]/div[1]/div[1]/div[1]/a")).click();
		driver.findElement(By.cssSelector("#error_message_orignal+div>button:first-child")).click();
		Thread.sleep(2000);

		//Deleting note
		Thread.sleep(1000);
		try
		{
			driver.findElement(By.cssSelector("#pinnedNotesDiv>div>div>div>div>div>div:nth-child(2)>a")).click();
			driver.findElement(By.linkText("Delete Note")).click();
			driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
		}
		catch(Exception e)
		{

		}
	}

	@Test(priority = 9)
	public void Caseid_014() throws Exception// in the “send booking invite” add new appointment type not working
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Actions action = new Actions(driver);

		driver.navigate().to(Messages);

		System.out.println();
		System.out.println("Caseid_014: In the “send booking invite” add new appointment type not working");

		Thread.sleep(1000);
		driver.findElement(By.name("filters")).sendKeys("Automation contact");
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(1000);

		//Action menu- Send booking invite
		driver.findElement(By.cssSelector("div.desktop>div.selectact>div:first-child>div>a#dropdownMenu3")).click();
		driver.findElement(By.cssSelector("div.desktop>div.selectact>div:first-child>div>div>a:nth-child(5)")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("new-booking-invite-global")));
		driver.findElement(By.id("booking_invite_eventname_id")).click();
		WebElement one = driver.findElement(By.id("booking_invite_list_event"));
		WebElement two = driver.findElement(By.cssSelector("#booking_invite_list_event>li"));
		action.moveToElement(one).scrollToElement(two).build().perform();
		two.click();
		Thread.sleep(3000);
		String nav = driver.getCurrentUrl();
		Assert.assertEquals(nav, url+"index.php?m=appointments&d=neweventtype", "Add New aapointment type button not working");
		System.out.println("	User is navigating to the New appointment page");
	}

	@Test(priority = 10)
	public void Caseid_015() throws InterruptedException 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		Actions action = new Actions(driver);

		System.out.println();
		System.out.println("Caseid_015: I added a unavailable day on a appt and it didnt block off the times");	

		driver.navigate().to(AppointmentTypes);

		//Checking for duplicate
		driver.findElement(By.name("search")).sendKeys("Test Appointment- Collective 110022");
		action.sendKeys(Keys.ENTER).perform();
		try 
		{
			driver.findElement(By.cssSelector("div.eventcontentdiv>div:first-of-type>div>div>a")).click();//appointment menu
			driver.findElement(By.cssSelector("div.eventcontentdiv>div:first-of-type>div>div>div>a:nth-of-type(4)")).click(); // delete option
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("form#formdeleteactionmodaldiv>div>button")).click();
		}
		catch(Exception q) 
		{

		}

		//Creating New Appointment
		driver.findElement(By.id("eventtypebutton")).click();
		action.sendKeys(Keys.PAGE_DOWN).perform();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[2]/div[2]/div/div/div/div[1]/div[1]/div/div[4]/a")).click();

		driver.findElement(By.cssSelector("#host-popup>div>div>div>div>div:first-child>input")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#host-popup>div>div>div>div>div:first-child>div>div:nth-child(2)")).click();
		driver.findElement(By.id("linkdoneapplyss")).click();

		driver.findElement(By.id("appttypename_title")).sendKeys("Test Appointment- Collective 110022");
		driver.findElement(By.id("appttypedesc")).sendKeys("Test user");
		jse.executeScript("scroll(0,0)");
		driver.findElement(By.cssSelector("div.appt-head-section>div:nth-of-type(3)>button[type=\"button\"]")).click();
		Thread.sleep(2000);
		jse.executeScript("scroll(0,200)");

		driver.findElement(By.cssSelector("div.mb-5.loc>div.appt-location")).click();
		driver.findElement(By.cssSelector("div.appt-location.open>ul>li[data-value=\"inperson\"]")).click();
		driver.findElement(By.id("inperson_address")).sendKeys("Test Address");
		jse.executeScript("scroll(0,0)");
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[2]/div[2]/div/div/div[1]/div[1]/div[1]/div/div[4]/div[1]/div[2]/button")).click();// next button
		Thread.sleep(1000);
		jse.executeScript("scroll(0,0)");
		jse.executeScript("scroll(0,800)");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.appt-availability-section>div>div:nth-child(5)>div>div:nth-child(5)>span")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.appt-availability-section>div>div:nth-child(5)>div>div:nth-child(5)>ul>li:nth-child(2)")).click();
		Thread.sleep(1000);

		//setting Custom availibility
		driver.findElement(By.cssSelector("span[id^=select2-state]")).click();
		driver.findElement(By.cssSelector("input.select2-search__field")).sendKeys("Asia/Kolkata");
		action.sendKeys(Keys.ENTER).perform();

		try
		{
			jse.executeScript("document.getElementsByName('to_time[0][]')[0].setAttribute('value','11:45 PM')");
		}
		catch (Exception JavaScriptException)
		{
			driver.findElement(By.cssSelector("a[data-dayid = \"0\"]")).click();
			jse.executeScript("document.getElementsByName('to_time[0][]')[0].setAttribute('value','11:45 PM')");
		}

		driver.findElement(By.cssSelector("#deactive-time-div-0+div>div>a")).click();

		for(int i=3;i<=8;i++)
		{
			driver.findElement(By.cssSelector("#deactive-time-div-0+div>div>div>label:nth-child("+i+")>span")).click();
		}
		driver.findElement(By.cssSelector("#deactive-time-div-0+div>div>div>div>button")).click();

		//Setting unavailable for specific date
		driver.findElement(By.cssSelector("a.adddateoverridedatebtn")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("td.ui-datepicker-today>a")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button#linkdone")).click();

		jse.executeScript("scroll(0,0)");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[2]/div[2]/div/div/div[1]/div[1]/div[1]/div/div[5]/div[1]/div[2]/button")).click();
		String pwindo = driver.getWindowHandle();
		jse.executeScript("scroll(0,0)");

		//validating landing page
		driver.findElement(By.cssSelector("a[data-url]")).click();
		Set<String> handles = driver.getWindowHandles();

		for (String handle: handles) 
		{
			driver.switchTo().window(handle);
		}

		Thread.sleep(5000);
		driver.findElement(By.cssSelector("a[href=\"#\"]:first-of-type")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button.time-button:first-of-type")).click();
		Thread.sleep(1000);

		String Text = driver.findElement(By.cssSelector("button.time-button")).getText();

		driver.close();
		driver.switchTo().window(pwindo);

		if(Text.equalsIgnoreCase("Slots not available"))
		{
			System.out.println("	Test Passed: "+Text);
		}
		else
		{
			Assert.assertTrue(false,"Please Check Slots Manually Slots Showing Available");
		}
		driver.switchTo().window(pwindo);
	}

	@Test(priority = 11)
	public void Caseid_016() throws InterruptedException //search feature doesn’t work when searching the pipeline in add customer -
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.navigate().to(Customers+"&d=add");

		System.out.println();
		System.out.println("Caseid_016: search feature doesn’t work when searching the pipeline in add customer");

		driver.findElement(By.id("ctPipelineIds")).click();
		driver.findElement(By.id("customerPipelineData")).sendKeys("Default");

		if(driver.findElement(By.cssSelector("label[data-text=\"Default\"]")).isDisplayed())
		{
			System.out.println("	Search Result is Displyed");
		}
		else
		{
			Assert.assertTrue(false, "Search Result Not Found");
		}
	}

	@Test(priority = 12)
	public void Caseid_017() throws InterruptedException //“Unconfirmed / Confirmed” Shows different on the dashboard
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Actions action = new Actions(driver);

		driver.navigate().to(Dashboard);
		System.out.println();
		System.out.println("Caseid_017: “Unconfirmed / Confirmed” Shows different on the dashboard");

		String statusdash = driver.findElement(By.cssSelector("div.appointment-box>div>h1:nth-child(2)>span")).getText();
		System.out.println("	Text on Dashboard: "+statusdash);
		driver.navigate().to(Calendar);

		WebElement nextappt  = driver.findElement(By.cssSelector("label.nxt-apt-header"));
		action.scrollToElement(nextappt).perform();
		driver.findElement(By.cssSelector("label.nxt-apt-header+div>h5")).click();
		Thread.sleep(2000);
		String statuscal = driver.findElement(By.cssSelector("div.left-appt-details>div:nth-of-type(2)>h4>span:last-of-type")).getText();
		System.out.println("	Text in Calendar: "+statuscal);
		Assert.assertEquals(statusdash, statuscal, "Appointment Status Mismatched On Dashboard and Calendar");
	}

	@Parameters({"uname", "pass"})
	@Test(priority = 13)
	public void Caseid_018(String uname, String pass ) throws Exception
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor jse = (JavascriptExecutor)driver;

		//Login to SA account
		driver.findElement(By.xpath("//*[@id=\"dropdownMenu1\"]")).click();
		driver.findElement(By.linkText("Logout")).click();

		Thread.sleep(4000);
		WebElement emf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		emf.sendKeys(SALogin);
		driver.findElement(By.id("password")).sendKeys(SAPass);
		driver.findElement(By.id("remember-me")).click();//Remeber me checkbox
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[4]/button[1]")).click();
		Thread.sleep(3000);

		System.out.println();
		System.out.println("Caseid_018: Remember me");

		String pwindo = driver.getWindowHandle();

		driver.switchTo().newWindow(WindowType.TAB);
		String nwindo = driver.getWindowHandle();
		driver.switchTo().window(pwindo).close();
		driver.switchTo().window(nwindo);
		driver.get(url);
		Assert.assertEquals(driver.getCurrentUrl(),url);
		System.out.println("	Case passed: Remember Me");


		//System.out.println();
		//System.out.println("Case: In the backend of admin when logging into a paying members account I am getting a page error");



		//Relogin
		driver.findElement(By.xpath("//*[@id=\"dropdownMenu1\"]")).click();
		jse.executeScript("document.querySelector('div.header-dropdown-menu').scrollTop=500");
		driver.findElement(By.linkText("Logout")).click();
		WebElement emf1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		emf1.sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pass);
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[4]/button[1]")).click();
	}

	@Test(priority = 14)
	public void Caseid_019() throws Exception //pipeline session issue
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);
		driver.navigate().to(UserManagment);

		System.out.println();
		System.out.println("Caseid_019: pipeline session issue");
		//Login to User account
		action.moveByOffset(100, 100).click().sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).build().perform();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("table>tbody>tr:first-of-type>td:nth-child(10)>div>a")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("table>tbody>tr:first-of-type>td:nth-child(10)>div>div>a:first-of-type")).click();//login to user account
		driver.switchTo().alert().accept();
		Thread.sleep(2000);

		driver.navigate().to(Pipelines);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/div[1]")).click();
		String PipelineName = driver.findElement(By.cssSelector("#pipelineFilterList>label:nth-of-type(3)")).getAttribute("data-text");
		driver.findElement(By.cssSelector("#pipelineFilterList>label:nth-of-type(3)")).click();
		Thread.sleep(2000);

		//Relogin to Admin account 
		driver.findElement(By.cssSelector("h1.profile-user-name")).click();
		Thread.sleep(500);
		driver.findElement(By.linkText("Re-Login Back")).click();

		driver.navigate().to(Pipelines);
		String PipelineName2 = driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/div[1]")).getAttribute("value");

		if(PipelineName.equalsIgnoreCase(PipelineName2)) 
		{
			Assert.assertEquals("Pipeline Session issue", "Pipeline Session Issue Found");
		}
		else
		{
			System.out.println("	Test Passed");
		}
	}

	@Test(priority = 15)
	public void Caseid_020() throws Exception //If same booking link is there then next button not working
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);
		JavascriptExecutor jse = (JavascriptExecutor)driver;

		System.out.println();
		System.out.println("Caseid_020: If same booking link is there then next button not working");	

		driver.navigate().to(AppointmentTypes);

		//Checking for duplicate
		driver.findElement(By.name("search")).sendKeys("Test Appointment mhfkss");
		action.sendKeys(Keys.ENTER).perform();

		try 
		{
			driver.findElement(By.cssSelector("div.eventcontentdiv>div:first-of-type>div>div>a")).click();//appointment menu
		}
		catch(Exception q) 
		{	
			//Creating New Appointment
			driver.findElement(By.id("eventtypebutton")).click();
			driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[2]/div[2]/div/div/div/div[1]/div[1]/div/div[2]/a")).click();
			driver.findElement(By.id("appttypename_title")).sendKeys("Test Appointment mhfkss");
			driver.findElement(By.cssSelector("div.mb-5.loc>div.nice-select:first-of-type")).click();
			jse.executeScript("scroll(0,200)");
			driver.findElement(By.cssSelector("li[data-value=\"inperson\"]")).click();
			driver.findElement(By.id("appttypedesc")).sendKeys("Test user");
			jse.executeScript("scroll(0,0)");
			driver.findElement(By.cssSelector("div.appt-head-section>div:nth-of-type(3)>button[type=\"button\"]")).click();
			Thread.sleep(2000);
			jse.executeScript("scroll(0,0)");
			driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[2]/div[2]/div/div/div[1]/div[1]/div[1]/div/div[4]/div[1]/div[2]/button")).click();
			driver.navigate().to(AppointmentTypes);
		}

		//Creating New Appointment - Duplicate slug
		driver.findElement(By.id("eventtypebutton")).click();
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[2]/div[2]/div/div/div/div[1]/div[1]/div/div[2]/a")).click();
		driver.findElement(By.id("appttypename_title")).sendKeys("Test Appointment mhfkss");
		driver.findElement(By.cssSelector("div.mb-5.loc>div.nice-select:first-of-type")).click();
		jse.executeScript("scroll(0,200)");
		driver.findElement(By.cssSelector("li[data-value=\"inperson\"]")).click();
		driver.findElement(By.id("inperson_address")).sendKeys("random");
		driver.findElement(By.id("appttypedesc")).sendKeys("Test user");
		jse.executeScript("scroll(0,0)");
		driver.findElement(By.cssSelector("div.appt-head-section>div:nth-of-type(3)>button[type=\"button\"]")).click();
		action.scrollByAmount(0, 660).perform();

		try
		{
			Thread.sleep(1000);
			String SlugError = driver.findElement(By.cssSelector("label.error.appttypebookingslug_error")).getText();
			System.out.println("	Validation Message Found: "+SlugError);
		}
		catch(NoSuchElementException e)
		{
			System.out.println("Duplicate Slug Message Not Found");
			Assert.assertTrue(false);
		}
	}



	@Test(priority = 16)
	public void Caseid_021() throws Exception //for cloned form next button not displayed(multiple step)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);
		JavascriptExecutor jse = (JavascriptExecutor)driver;

		System.out.println();
		System.out.println("Caseid_021: For cloned form next button not displayed(multiple step)");

		driver.navigate().to(Forms);

		//Deleting duplicate
		driver.findElement(By.id("search_template_list")).sendKeys("New Test Form jsufbj");
		driver.findElement(By.cssSelector("#admintable0>thead>tr>th>label>span.checkmark")).click();

		try
		{
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("#formlist>a:nth-child(3)")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("#multiple-delete-modal>div>div>div>div>button")).click();
		}
		catch(Exception elementNotInteractableException)
		{

		}

		//Deleting duplicate
		driver.findElement(By.id("search_template_list")).sendKeys("copy of New Test Form jsufbj");
		try
		{
			driver.findElement(By.cssSelector("#admintable0>thead>tr>th>label>span.checkmark")).click();
		}
		catch(Exception E)
		{
			driver.findElement(By.cssSelector("#admintable0>thead>tr>th>label>span.checkmark")).click();
		}


		try
		{
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("#formlist>a:nth-child(3)")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("#multiple-delete-modal>div>div>div>div>button")).click();
		}
		catch(Exception elementNotInteractableException)
		{

		}

		//Creating new Form
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div#spacious-container>div>div>div>div>div>div:nth-of-type(2)>a")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("form_title")).sendKeys("New Test Form jsufbj");
		Thread.sleep(1000);
		driver.findElement(By.id("formcreation-submit")).click();
		Thread.sleep(2000);
		WebElement x = driver.findElement(By.cssSelector("li[data-type=\"dualfirstandlast\"]"));
		WebElement y = driver.findElement(By.cssSelector("ul[data-id=\"fb-cust-builder-1\"]"));
		action.dragAndDrop(x,y).perform();

		try
		{
			driver.findElement(By.cssSelector("li[type=contactinfogroupinfo]")).isDisplayed();
		}
		catch(Exception e1) 
		{
			action.dragAndDrop(x,y).perform();
		}
		Thread.sleep(2000);

		jse.executeScript("document.querySelector('div.form-wrap.form-builder ').scrollTop = 2000");
		driver.findElement(By.cssSelector("#fb-add-step>li>span")).click();
		jse.executeScript("document.querySelector('div.form-wrap.form-builder ').scrollTop = 2000");

		WebElement A = driver.findElement(By.cssSelector("li[data-type=\"companyName\"]"));
		WebElement B = driver.findElement(By.cssSelector("#editform-components+div>ul:nth-of-type(3)>li"));

		action.dragAndDrop(A, B).perform();

		try
		{
			driver.findElement(By.cssSelector("li[type= \"companyName\"]")).isDisplayed();
		}
		catch(Exception e2) 
		{
			action.dragAndDrop(A, B).perform();
		}
		Thread.sleep(2000);

		driver.findElement(By.id("fb-save-form")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("a[onclick=\"closeBuilder()\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("search_template_list")).sendKeys("New Test Form jsufbj");
		driver.findElement(By.cssSelector("#templateListTBody>tr:first-child>td:nth-of-type(7)")).click();


		Thread.sleep(5000);
		driver.findElement(By.id("search_template_list")).clear();
		driver.findElement(By.id("search_template_list")).sendKeys("copy of New Test Form jsufbj");
		String pwindo = driver.getWindowHandle();
		driver.findElement(By.cssSelector("#templateListTBody>tr:first-child>td:nth-of-type(6)")).click();//Clonned form Open button

		Set<String> handles = driver.getWindowHandles();

		for (String handle: handles) 
		{
			driver.switchTo().window(handle);
		}
		String btncap = driver.findElement(By.id("step_1_btn")).getText();
		driver.close();
		driver.switchTo().window(pwindo);
		Assert.assertEquals(btncap,  "Next", "Next Button Not Get Displyed");
	}

	//@Test(priority = 17)
	public void Caseid_022() throws Exception //access level issues (wrong user count displayed in team section & by default
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		System.out.println();
		System.out.println("Caseid_022: Access level issues (wrong user count displayed in team section & by default");

		driver.navigate().to(UserManagment+"&d=teams_list");
		String before = driver.findElement(By.cssSelector("div.carousel>div:first-child>div>div>div+div>h1+h1")).getText();
		driver.findElement(By.cssSelector("div.carousel>div:first-child>div>div>div+div>h1+h1")).click();
		driver.findElement(By.cssSelector("#teams_section>div:nth-of-type(3)>div>div>div>a")).click();
		Thread.sleep(1000);

		String count = driver.findElement(By.cssSelector("#createTeamForm>div:nth-child(2)>div>h1:nth-child(2)")).getText();
		System.out.println(count);
		if(count.equalsIgnoreCase("1"))
		{
			driver.findElement(By.cssSelector("div.teamusers.selectteamfrompopup")).click();
		}
		driver.findElement(By.cssSelector("div.teamusers.selectteamfrompopup.selected")).click();
		driver.findElement(By.cssSelector("button.create-newTeam")).click();
		Thread.sleep(2000);
		String after = driver.findElement(By.cssSelector("div.carousel>div:first-child>div>div>div+div>h1+h1")).getText();

		System.out.println("	User Count in Team Before Updating: "+before);
		System.out.println("	User Count in Team After Updating: "+after);

		if(before.equalsIgnoreCase(after))
		{
			Assert.assertTrue(false,"User Count Not Updating");
		}
	}

	@Parameters({"uname", "pass"})
	@Test(priority = 18)
	public void Caseid_023(String uname, String pass ) throws Exception//Unacrhive user not able to revert (message sent from superadmin acnt)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Actions action = new Actions(driver);

		//Login to SA account
		driver.findElement(By.xpath("//*[@id=\"dropdownMenu1\"]")).click();
		driver.findElement(By.linkText("Logout")).click();

		WebElement emf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		emf.sendKeys(SALogin);
		driver.findElement(By.id("password")).sendKeys(SAPass);
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[4]/button[1]")).click();

		System.out.println();
		System.out.println("Caseid_023: Uracrhive user not able to revert");

		driver.navigate().to(SATestAct);
		driver.navigate().to(Messages);
		Thread.sleep(1000);
		String id = driver.findElement(By.cssSelector("#custom-sms-contact-list>div:first-child>div:first-child")).getAttribute("id");
		WebElement Icon	= driver.findElement(By.id("read_unread_dd"));
		WebElement Hover = driver.findElement(By.id(id));
		action.moveToElement(Hover).perform();
		Icon.click();
		driver.findElement(By.cssSelector("#read_unread_dd[aria-expanded=\"true\"]+div>a:nth-child(2)")).click();//Archive option 
		driver.findElement(By.cssSelector("#oldfiltersgap_newrow>a:nth-child(4)")).click();
		WebElement archive = driver.findElement(By.cssSelector("#"+id+"+div"));
		WebElement Hover1 = driver.findElement(By.id(id));
		action.moveToElement(Hover1).perform();
		Thread.sleep(500);
		archive.click();
		driver.findElement(By.cssSelector("#read_unread_dd[aria-expanded=\"true\"]+div>a:last-of-type")).click();//UnArchive option

		try
		{
			Thread.sleep(500);
			driver.findElement(By.id(id));
			Relogin(uname, pass);
			Assert.assertTrue(false,"Customer Not Unarchived");
		}
		catch(Exception e)
		{
			System.out.println("	Test Passed");
		}

		System.out.println();
		System.out.println("Caseid_024: Superadmin account after click on week then day tab displayed");
		driver.navigate().to(Calendar);
		driver.findElement(By.cssSelector("div.calendar-view-btn>button:nth-child(3)")).click();
		String week = driver.getCurrentUrl();
		Assert.assertEquals(week, "https://app.deposyt.com/index.php?m=appointments&d=calendar&view=week","URL Not Matching after navigating to Week view");
		Thread.sleep(2000);
		System.out.println("	Test Passed: "+driver.findElement(By.cssSelector("div.fc-timegrid.fc-timeGridWeek-view")).isDisplayed());
		Relogin(uname, pass);
	}

	@Test(priority = 19)
	public void Caseid_025() throws Exception //done button stuck on calendar agenda page
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		System.out.println();
		System.out.println("Caseid_025: done button stuck on calendar agenda page");
		driver.navigate().to(Calendar);

		try//For Appointment
		{
			driver.findElement(By.cssSelector("div.new-single-appointment-header+div>div:last-of-type+a+a")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("div.new-single-appointment-header+div>div:last-of-type+a+a")).click();
		}
		catch(Exception e)
		{

		}
		try//Existing Reminder
		{
			driver.findElement(By.cssSelector("div.new-single-appointment-header+div>div>div:nth-of-type(3)")).click();
		}
		catch(Exception e1)
		{
			driver.findElement(By.cssSelector("div.new-single-appointment-header+div>div>div")).click();
		}
		Thread.sleep(1000);
		driver.findElement(By.id("dashboard_note_text")).sendKeys("Test Note");
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(By.id("setnotereminder")));
		}
		catch(Exception e)
		{
			driver.findElement(By.cssSelector("a.delete-reminder")).click();
			Thread.sleep(500);
			driver.findElement(By.cssSelector("button.deleteactionperform ")).click();
			Thread.sleep(1000);
			driver.navigate().refresh();
			driver.findElement(By.cssSelector("div.new-single-appointment-header+div>div>div+div")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("dashboard_note_text")).sendKeys("Test Note");
		}
		driver.findElement(By.id("setnotereminder")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("ul.reminder-pills>li:nth-child(2)")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input.button_calendar+span")).click();
		driver.findElement(By.cssSelector("div.advanced.active>a:first-of-type")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("add_a_note_popup_btn")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.new-appointment-box>div>div[data-note_id]>a:last-of-type")).click();
		Thread.sleep(2000);
		String Caption = driver.findElement(By.cssSelector("div.new-appointment-box>div>div[data-note_id]>div:nth-of-type(4)>a")).getText();
		driver.findElement(By.cssSelector("div.new-appointment-box>div>div[data-note_id]>div:nth-of-type(4)>a")).click();
		Assert.assertEquals(Caption, "Dismiss","Remainder Not Get marked As Done");
	}

	@Test(priority = 20)
	public void Caseid_026() throws Exception //All contacts not displayed on add note popup
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		System.out.println();
		System.out.println("Caseid_026: All contacts not displayed on add note popup");

		driver.navigate().to(Notes);

		driver.findElement(By.xpath("//*[@id=\"notes-toggler\"]/div[1]/div[1]/div[1]/div[1]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"js-tags-selectorpop-selectized\"]")).click();
		List <WebElement> Contacts = driver.findElements(By.cssSelector("div.selectize-dropdown-content>div"));
		int a = Contacts.size();

		driver.navigate().to(Customers);
		String AllContacts = driver.findElement(By.cssSelector("input[name=allcustcount]")).getAttribute("value");
		System.out.println(AllContacts);
		int b = Integer.parseInt(AllContacts);
		Assert.assertEquals(a, b,"All Contacts Count Mismatched"+a+" != "+b );

	}

	@Test(priority = 21)
	public void Caseid_027() throws Exception //when I go to “availability” Whether from appt types, calendar, etc and add A extra availability to a user
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		JavascriptExecutor jse = (JavascriptExecutor)driver;

		System.out.println();
		System.out.println("Caseid_027: when I go to “availability” Whether from appt types, calendar, etc and add A extra availability to a user");

		driver.navigate().to(Availibility);

		driver.findElement(By.id("selectedUserId")).click();
		jse.executeScript("document.querySelector('#pipeline-typeFilterList').scrollTop = 5000");
		driver.findElement(By.cssSelector("#pipeline-typeFilterList>label:last-of-type")).click();
		driver.findElement(By.cssSelector("a.new-schedule-popup")).click();
		driver.findElement(By.cssSelector("input.schedulename")).sendKeys("Test Schedule dsvknv");
		driver.findElement(By.cssSelector("button.newschedulebtnsubmit")).click();
		Thread.sleep(4000);
		String Scname = driver.findElement(By.cssSelector("a.hour-btn>span")).getText();
		Assert.assertEquals(Scname, "Test Schedule Dsvknv","Wrong Schedule Found:"+Scname);
		driver.findElement(By.id("dropdownMenu2")).click();
		driver.findElement(By.cssSelector("a.deleteeventschedular")).click();
		driver.findElement(By.cssSelector("button.deleteactionperform")).click();
		String log = driver.findElement(By.id("alertify-logs")).getText();
		System.out.println("	"+log);
	}

	@Test(priority = -3)
	public void Caseid_028() throws InterruptedException 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		Actions action = new Actions(driver);

		System.out.println();
		System.out.println("Caseid_028: Need to add contact in campaign through List");

		//Setting Contact
		driver.navigate().to(Lists);
		driver.findElement(By.name("search")).sendKeys("Camp List");

		try
		{
			driver.findElement(By.cssSelector("tr.admintable_row.at-row-0>td:last-of-type>div>a:last-of-type")).click();
			driver.findElement(By.cssSelector("#deleteform>div>button")).click();
		}
		catch(NoSuchElementException e)
		{
		}

		driver.navigate().to(Messages);

		Thread.sleep(2000);
		driver.findElement(By.name("filters")).sendKeys("nadsoft.test99@gmail.com");
		Thread.sleep(1000);
		action.sendKeys(Keys.ENTER).perform();
		Thread.sleep(2000);

		try 
		{
			driver.findElement(By.cssSelector("#custom-sms-contact-list>div.custom-msg-sidebar-wrapper")).click();
		}
		catch (org.openqa.selenium.NoSuchElementException e) 
		{
			driver.navigate().to(Customers);
			driver.findElement(By.name("search")).sendKeys("2543205948");
			action.sendKeys(Keys.ENTER).build().perform();

			try 
			{
				driver.findElement(By.id("at0-cell-actions_col-0")).click();
				driver.findElement(By.cssSelector("div.customer-more-option.open>div>a:last-of-type")).click();
				Thread.sleep(1000);
				driver.findElement(By.cssSelector("button.deleteactionperform ")).click();
			}
			catch(Exception w)
			{	
			}
			//Adding customer-->
			driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/a[1]")).click();// add customer button

			driver.findElement(By.name("first_name")).sendKeys("List Contact");	
			driver.findElement(By.id("cellphone")).sendKeys("18148023128");
			driver.findElement(By.id("email")).sendKeys("nadsoft.test99@gmail.com");
			driver.findElement(By.xpath("//*[@id=\"add_contact_form\"]/form[1]/div[2]/input[1]")).click();

			try 
			{
				driver.findElement(By.cssSelector("section.alertify-logs")).isDisplayed();
			}
			catch(Exception NoSuchElementExcepation) 
			{
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"add_contact_form\"]/form[1]/div[2]/input[1]")).click();
			}
			driver.navigate().to(Messages);

			driver.findElement(By.name("filters")).sendKeys("nadsoft.test99@gmail.com");
			action.sendKeys(Keys.ENTER).perform();
			Thread.sleep(4000);
			driver.findElement(By.cssSelector("#custom-sms-contact-list>div.custom-msg-sidebar-wrapper")).click();
		}

		driver.findElement(By.id("calling_email")).click();
		Thread.sleep(3000);

		driver.findElement(By.id("subject_email"))
		.sendKeys("Hi this is test mail from veer automation");
		action.sendKeys(Keys.TAB)
		.sendKeys("This is test mail veer daily test")
		.build().perform();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("a#convoSendEmailBtn")).click();

		driver.navigate().to(Lists+"&d=add");
		driver.findElement(By.cssSelector("div.input-group>input")).sendKeys("Camp List");
		driver.findElement(By.id("ClistNextBtn")).click();
		driver.findElement(By.cssSelector("#aec_form>input")).sendKeys("nadsoft.test99@gmail.com");
		action.sendKeys(Keys.ENTER).perform();
		//Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#selectAll+span")));
		driver.findElement(By.cssSelector("#selectAll+span")).click();
		driver.findElement(By.cssSelector("div.adminbuttons>button")).click();
		driver.findElement(By.cssSelector("div.edit-filter-wrapper>div:nth-child(2)>a:first-child")).click();
		String Alert = driver.findElement(By.id("alertify-logs")).getText();
		System.out.println("	"+Alert);

		//Creating New Camp
		driver.navigate().to(Campaigns);

		//Checking For Duplicate
		driver.findElement(By.name("search")).sendKeys("Test Camp List - o48wj");
		action.sendKeys(Keys.ENTER).scrollByAmount(220, 0).build().perform();

		try 
		{
			driver.findElement(By.cssSelector("#selectAll+span")).click();
			driver.findElement(By.cssSelector("div#campaigntbulkbtnsdiv>a:nth-child(5)")).click();
			driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
		}
		catch(Exception NoSuchElementException)
		{
			driver.navigate().refresh();
		}

		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/a")).click();
		driver.findElement(By.id("campaign_title2")).sendKeys("Test Camp List - o48wj");//<-camp name
		driver.findElement(By.cssSelector("#gb_btn_wizrd+a.desktop-600")).click();//<-- next button
		jse.executeScript("scroll(0,400)");
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/main/div/div[1]/div/form/div[3]/div/div[2]/div[4]/div/button[1]"))
		.click();

		try 
		{ 
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"message_row_259\"]/div[2]/div/div[1]/div[1]")));
		}
		catch(Exception TimeoutException) 
		{
			Thread.sleep(2000);
		}

		jse.executeScript("scroll(0,700)");
		action.sendKeys("Contact in Campaign Through list").sendKeys(Keys.TAB)
		.sendKeys("Hi this is test message").build().perform();
		//jse.executeScript("window.scroll(top)");
		action.sendKeys(Keys.PAGE_UP).sendKeys(Keys.PAGE_UP).sendKeys(Keys.PAGE_UP).sendKeys(Keys.PAGE_UP).build().perform();
		driver.findElement(By.cssSelector("#gb_btn_wizrd+a.desktop-600")).click();//<-- next button

		driver.findElement(By.cssSelector("div.contact-btn-group>button:first-of-type")).click(); // add by List
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#addListSelectDiv>div>div>div:first-child")).click();
		action.sendKeys("Camp List").sendKeys(Keys.ENTER).build().perform();
		driver.findElement(By.cssSelector("i.fa-address-book+h1")).click();
		driver.findElement(By.cssSelector("#addListSelectDiv+div>button")).click();

		try
		{
			driver.findElement(By.cssSelector("#gb_btn_wizrd+a.desktop-600")).click();//<-- next button
		}
		catch(Exception StaleElementReferenceException) 
		{
			driver.navigate().refresh();
			driver.findElement(By.cssSelector("#gb_btn_wizrd+a.desktop-600")).click();//<-- next button
		}

		String  Contcount = driver.findElement(By.cssSelector("div.new-contact-count1")).getText();
		System.out.println("	"+Contcount+" Contacts Added in Campaing");

		if(Contcount.equals("0")) 
		{
			Assert.assertFalse(true,"No Contacts Found in Campaign");
		}

		try
		{
			driver.findElement(By.cssSelector("#gb_btn_wizrd+a.desktop-600")).click();//<-- next button
		}
		catch(Exception StaleElementReferenceException) 
		{
			driver.navigate().refresh();
			driver.findElement(By.cssSelector("#gb_btn_wizrd+a.desktop-600")).click();//<-- next button
		}

		jse.executeScript("window.scroll(0,300)");
		driver.findElement(By.id("launch-dropdown")).click();//<-- launch button
		driver.findElement(By.xpath("//*[@id=\"launchPageCenterModal\"]/div[1]/div[3]/div/div/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"campaign-launch-now-popup\"]/div/div/div/button")).click();
		System.out.println("	Campaign Launched!");

		driver.navigate().to(Campaigns);
		driver.findElement(By.name("search")).sendKeys("Test Camp List - o48wj");
		action.sendKeys(Keys.ENTER).build().perform();
		String campstat2= driver.findElement(By.id("at0-cell-status-0")).getText();
		System.out.println("	Campaign Status: "+campstat2); //<--campaign status
	}

	@Test(priority = -2)
	public void Caseid_029() throws InterruptedException 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		Actions action = new Actions(driver);

		System.out.println();
		System.out.println("Caseid_029: need to add contact in campaign through tags");

		//Adding New Tag
		driver.navigate().to(TagManager);
		driver.findElement(By.name("searchtag")).sendKeys("AutomationCampaignTag");
		action.sendKeys(Keys.ENTER).perform();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#selectAll+span")).click();
		driver.findElement(By.cssSelector("button.deletebulktags ")).click();
		driver.findElement(By.cssSelector("div.selectize-control.custom-tag-input>div>input")).sendKeys("AutomationCampaignTag");
		action.sendKeys(Keys.ENTER).perform();
		driver.findElement(By.id("Add_tags")).click();

		//Setting Contact
		driver.navigate().to(Customers);

		driver.findElement(By.name("search")).sendKeys("4059934665");
		action.sendKeys(Keys.ENTER).build().perform();

		try 
		{
			driver.findElement(By.id("at0-cell-actions_col-0")).click();
		}
		catch (org.openqa.selenium.NoSuchElementException e) 
		{
			//Adding customer-->
			driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/a[1]")).click();// add customer button

			driver.findElement(By.name("first_name")).sendKeys("Tag Test");	
			driver.findElement(By.id("cellphone")).sendKeys("4059934665");
			driver.findElement(By.xpath("//*[@id=\"add_contact_form\"]/form[1]/div[2]/input[1]")).click();

			try 
			{
				driver.findElement(By.cssSelector("section.alertify-logs")).isDisplayed();
			}
			catch(Exception NoSuchElementExcepation) 
			{
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"add_contact_form\"]/form[1]/div[2]/input[1]")).click();
			}
			driver.findElement(By.id("at0-cell-actions_col-0")).click();
		}

		driver.findElement(By.cssSelector("div.customer-more-option.open>div>a[title=\"Add Tags\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#js-tags-selector+div>div>input")).sendKeys("AutomationCampaignTag");
		action.sendKeys(Keys.ENTER).perform();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("p.font-medium.text-15.darkColor")).click();
		driver.findElement(By.cssSelector("div.flex.justify-between.items-center.gap-8.mt-4.w-full>div>button")).click();
		Thread.sleep(2000);

		driver.navigate().to(Messages);
		Thread.sleep(2000);
		driver.findElement(By.name("filters")).sendKeys("Tag Test");
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(2000);
		driver.findElement(By.id("dashboard-conversation-text"))
		.sendKeys("test message ");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"msgbox_messages\"]/div[3]/div[2]")).click();//<---send

		driver.navigate().to(Campaigns);

		//Checking For Duplicate
		driver.findElement(By.name("search")).sendKeys("Test Camp Tags - o48wj");
		action.sendKeys(Keys.ENTER).scrollByAmount(220, 0).build().perform();

		try 
		{
			driver.findElement(By.cssSelector("#selectAll+span")).click();
			driver.findElement(By.cssSelector("div#campaigntbulkbtnsdiv>a:nth-child(5)")).click();
			driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
		}
		catch(Exception NoSuchElementException)
		{
			driver.navigate().refresh();
		}

		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/a")).click();
		driver.findElement(By.id("campaign_title2")).sendKeys("Test Camp Tags - o48wj");//<-camp name
		driver.findElement(By.cssSelector("#gb_btn_wizrd+a.desktop-600")).click();//<-- next button
		jse.executeScript("scroll(0,400)");
		driver.findElement(By.xpath("//*[@id=\"templateparent\"]/div[4]/div/button[2]")).click();

		try 
		{ 
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("sequence_text_335")));
		}
		catch(Exception TimeoutException) 
		{
		}

		jse.executeScript("scroll(0,300)");
		action.sendKeys("Contact in campaign through Tag ;").build().perform();
		driver.findElement(By.cssSelector("div[id^=message_row]>div:nth-child(2)>div>div:nth-child(2)>div>div>a:nth-child(5)")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("tools_popup_camp"))));

		//Inserting Shortcode 
		driver.findElement(By.cssSelector("#tools_popup_camp>div>div>div>div:nth-child(4)>div:first-child>label")).click();
		driver.findElement(By.cssSelector("#ShortcodeTypes>label:nth-child(2)")).click();
		driver.findElement(By.cssSelector("#tools_popup_camp>div>div>div>div.justify-end.items-center>button")).click();
		Thread.sleep(2000);

		//jse.executeScript("window.scroll(top)");
		action.sendKeys(Keys.PAGE_UP).sendKeys(Keys.PAGE_UP).sendKeys(Keys.PAGE_UP).build().perform();
		driver.findElement(By.cssSelector("#gb_btn_wizrd+a.desktop-600")).click();

		driver.findElement(By.cssSelector("div.contact-btn-group>a:nth-child(2)")).click(); // add by Tags
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#campaign-tags-selector+div+div")).click();
		driver.findElement(By.cssSelector("#campaign-tags-selector+div+div>div>input")).sendKeys("AutomationCampaignTag");
		Thread.sleep(1000);
		action.sendKeys(Keys.ENTER).perform();
		driver.findElement(By.cssSelector("#campaign_tag_contact_form>div>i")).click();
		driver.findElement(By.cssSelector("#campaign_tag_contact_form>div>div:last-of-type>button")).click();

		try
		{
			driver.findElement(By.cssSelector("#gb_btn_wizrd+a.desktop-600")).click();//<--next button keep 2 clicks
		}
		catch(Exception StaleElementReferenceException) 
		{
			driver.navigate().refresh();
			driver.findElement(By.xpath("//*[@id=\"campaign_common_form\"]/div[1]/div/div[3]/a[2]"))
			.click();//<-- don't consider as duplicate
		}

		String  Contcount = driver.findElement(By.cssSelector("div.new-contact-count1")).getText();
		System.out.println("	"+Contcount+" Contacts Added in Campaing");

		if(Contcount.equals("0")) 
		{
			Assert.assertFalse(true,"No Contacts Found in Campaign");
		}

		try
		{
			driver.findElement(By.cssSelector("#gb_btn_wizrd+a.desktop-600")).click();//<--next button keep 2 clicks
		}
		catch(Exception StaleElementReferenceException) 
		{
			driver.navigate().refresh();
			driver.findElement(By.cssSelector("#gb_btn_wizrd+a.desktop-600"))
			.click();//<-- don't consider as duplicate
		}

		jse.executeScript("window.scroll(0,300)");
		driver.findElement(By.id("launch-dropdown")).click();//<-- launch button
		driver.findElement(By.xpath("//*[@id=\"launchPageCenterModal\"]/div[1]/div[3]/div/div/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"campaign-launch-now-popup\"]/div/div/div/button")).click();
		System.out.println("	Campaign Launched!");

		driver.navigate().to(Campaigns);
		driver.findElement(By.name("search")).sendKeys("Test Camp Tags - o48wj");
		action.sendKeys(Keys.ENTER).build().perform();
		String campstat2= driver.findElement(By.id("at0-cell-status-0")).getText();
		System.out.println("	Campaign Status: "+campstat2); //<--campaign status

		String CampId = driver.findElement(By.cssSelector("tr.admintable_row.at-row-0.at-row-pair>td>center>label>input")).getAttribute("Value");

		System.out.println();
		System.out.println("Caseid_031: Campaign not listed in trigger");
		driver.navigate().to(Triggers);
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/div/button")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#select_trigger+div")).click();
		action.scrollByAmount(0, 180).perform();
		driver.findElement(By.cssSelector("#select_trigger+div>div+ul>li:nth-child(4)")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.campaigns_trigger_filter")).click();
		System.out.println("	Campaign Visibility in Trigger: "+driver.findElement(By.cssSelector("div.campaigns_trigger_filter>ul>li[data-value=\""+CampId+"\"]")).isDisplayed());

		driver.findElement(By.cssSelector("div.flex.gap-3.actioncard > div:nth-of-type(3) > div:nth-of-type(2)")).click();
		Thread.sleep(500);
		driver.findElement(By.cssSelector("li[data-value=\"1\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.action_wrapper>div>select[name=\"campaign\"]+div")).click();
		Thread.sleep(2000);
		System.out.println("	Campaign Visibility in Action: "+driver.findElement(By.cssSelector("div.action_wrapper>div>select[name=\"campaign\"]+div>ul>li[data-value=\""+CampId+"\"]")).isDisplayed());
	}

	@Test(priority = -1)
	public void Caseid_030() throws InterruptedException 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		Actions action = new Actions(driver);
		String Pipelineid = null;

		System.out.println();
		System.out.println("Caseid_030: Need to add contact in campaign through Pipeline");

		driver.navigate().to(Pipelines);

		//Checking for duplicate
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/div[1]")).click();
		jse.executeScript("document.querySelector('div#block0body > div > div > div > div > div > ul').scrollTop=300");

		List<WebElement>pipelines =  driver.findElements(By.cssSelector("#pipelineFilterList>label"));

		for(int i = 2; i<= pipelines.size()+1; i++)
		{
			String as= driver.findElement(By.cssSelector("#pipelineFilterList>label:nth-child("+i+")")).getText();
			Boolean test =as.equalsIgnoreCase("Campaign Pipeline - Auto delete");

			if(test) 
			{
				Pipelineid = driver.findElement(By.cssSelector("#pipelineFilterList>label:nth-child("+i+")")).getAttribute("data-value");
				driver.findElement(By.cssSelector("#pipelineFilterList>label[data-value = \""+Pipelineid+"\"]")).click();
				driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/a[1]")).click();
				driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[2]/a[2]")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id=\"get_delete_pipeline_main\"]/div/div/div/div/a[2]")).click();//<--permenant delete button
				driver.findElement(By.xpath("//*[@id=\"pp-delete-customer-form\"]/div/button")).click();
				break;
			}
		}

		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[2]/a[1]")).click();
		driver.findElement(By.name("pipeline_title")).sendKeys("Campaign Pipeline - Auto delete");
		driver.findElement(By.cssSelector("button.bulkaddpipelinestages")).click();
		action.sendKeys(Keys.TAB).sendKeys("Camp stage").build().perform();
		driver.findElement(By.cssSelector("#pp-create-stage-form>div>button[type = submit]")).click();
		Thread.sleep(1000);
		String pid = driver.findElement(By.id("hiddenpipeid")).getAttribute("value");

		//Setting Contact
		driver.navigate().to(Customers);
		driver.findElement(By.name("search")).sendKeys("15088599996");
		action.sendKeys(Keys.ENTER).build().perform();

		try 
		{
			driver.findElement(By.id("at0-cell-actions_col-0")).click();
			driver.findElement(By.xpath("//*[@id=\"at0-cell-actions_col-0\"]/div/div/a[2]")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("customerPipelineIds")).click();
			WebElement a =  driver.findElement(By.id("pipelinemovecustomer"));
			WebElement b = driver.findElement(By.cssSelector("#pipelinemovecustomer>label[data-text=\"Campaign Pipeline - Auto delete\"]"));
			action.moveToElement(a).scrollToElement(b).build().perform();
			b.click();
			driver.findElement(By.id("customerStagesIds")).click();
			driver.findElement(By.cssSelector("#pipelineCustomerstagesFilterList>label")).click();

			driver.navigate().to(Messages);
			Thread.sleep(2000);
			driver.findElement(By.name("filters")).sendKeys("Pipeline contact");
			action.sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(2000);
			driver.findElement(By.id("dashboard-conversation-text"))
			.sendKeys("test message ");
			driver.findElement(By.xpath("//*[@id=\"msgbox_messages\"]/div[3]/div[2]")).click();//<---send
		}
		catch (org.openqa.selenium.NoSuchElementException e) 
		{
			//Adding customer-->
			driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/a[1]")).click();// add customer button

			driver.findElement(By.id("ctPipelineIds")).click();
			WebElement c = driver.findElement(By.id("pipeline-typeFilterList"));//pipeline
			WebElement d = driver.findElement(By.cssSelector("#pipeline-typeFilterList>label[data-text=\"Campaign Pipeline - Auto delete\"]"));
			action.moveToElement(c).scrollToElement(d).build().perform();
			d.click();
			driver.findElement(By.id("ctStagesIdsMeting")).click();//stage
			driver.findElement(By.cssSelector("#pipelineOneOffstagesFilterList>label")).click();

			driver.findElement(By.name("first_name")).sendKeys("Pipeline contact");	
			driver.findElement(By.id("cellphone")).sendKeys("15088599996");
			driver.findElement(By.xpath("//*[@id=\"add_contact_form\"]/form[1]/div[2]/input[1]")).click();

			try 
			{
				driver.findElement(By.cssSelector("section.alertify-logs")).isDisplayed();
			}
			catch(Exception NoSuchElementExcepation) 
			{
				Thread.sleep(2000);
				driver.findElement(By.xpath("//*[@id=\"add_contact_form\"]/form[1]/div[2]/input[1]")).click();
			}
		}

		//Creating New Camp
		driver.navigate().to(Campaigns);

		//Checking For Duplicate
		driver.findElement(By.name("search")).sendKeys("Test Camp Pipeline - o48wj");
		action.sendKeys(Keys.ENTER).scrollByAmount(220, 0).build().perform();

		try 
		{
			driver.findElement(By.cssSelector("#selectAll+span")).click();
			driver.findElement(By.cssSelector("div#campaigntbulkbtnsdiv>a:nth-child(5)")).click();
			driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
		}
		catch(Exception NoSuchElementException)
		{
			driver.navigate().refresh();
		}

		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/a")).click();
		driver.findElement(By.id("campaign_title2")).sendKeys("Test Camp Pipeline - o48wj");//<-camp name
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#gb_btn_wizrd+a.desktop-600")).click();//<-- next button

		jse.executeScript("scroll(0,400)");
		driver.findElement(By.xpath("//*[@id=\"templateparent\"]/div[4]/div/button[2]")).click();

		try 
		{ 
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("sequence_text_335")));
		}
		catch(Exception TimeoutException) 
		{
		}

		jse.executeScript("scroll(0,300)");
		action.sendKeys("Contact in campaign through Pipeline break").build().perform();
		//jse.executeScript("window.scroll(top)");
		action.sendKeys(Keys.PAGE_UP).sendKeys(Keys.PAGE_UP).sendKeys(Keys.PAGE_UP).build().perform();
		driver.findElement(By.cssSelector("#gb_btn_wizrd+a.desktop-600")).click();//<-- next button

		driver.findElement(By.cssSelector("div.contact-btn-group>a:first-child")).click(); // add by pipeline
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#campaign_pipeline_form>div>div:first-of-type>div>div.relative.filter_input")).click();
		WebElement a =  driver.findElement(By.id("listFilterList"));
		WebElement b = driver.findElement(By.cssSelector("#listFilterList>label[data-value=\""+pid+"\"]"));
		action.moveToElement(a).scrollToElement(b).build().perform();
		b.click();
		driver.findElement(By.id("currentstageselected")).click();
		driver.findElement(By.cssSelector("#stageFilterList>label:nth-child(3)")).click();
		driver.findElement(By.cssSelector("#campaign_pipeline_form>div>div:nth-of-type(4)>button")).click();

		try
		{
			driver.findElement(By.cssSelector("#gb_btn_wizrd+a.desktop-600")).click();//<-- next button
		}
		catch(Exception StaleElementReferenceException) 
		{
			driver.navigate().refresh();
			driver.findElement(By.cssSelector("#gb_btn_wizrd+a.desktop-600")).click();//<-- next button
		}

		String  Contcount = driver.findElement(By.cssSelector("div.new-contact-count1")).getText();
		System.out.println("	"+Contcount+" Contacts Added in Campaing");

		if(Contcount.equals("0")) 
		{
			Assert.assertFalse(true,"No Contacts Found in Campaign");
		}

		try
		{
			driver.findElement(By.cssSelector("#gb_btn_wizrd+a.desktop-600")).click();//<-- next button
		}
		catch(Exception StaleElementReferenceException) 
		{
			driver.navigate().refresh();
			driver.findElement(By.cssSelector("#gb_btn_wizrd+a.desktop-600")).click();//<-- next button
		}

		jse.executeScript("window.scroll(0,300)");
		driver.findElement(By.id("launch-dropdown")).click();//<-- launch button
		driver.findElement(By.xpath("//*[@id=\"launchPageCenterModal\"]/div[1]/div[3]/div/div/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"campaign-launch-now-popup\"]/div/div/div/button")).click();
		System.out.println("	Campaign Launched!");

		driver.navigate().to(Campaigns);
		driver.findElement(By.name("search")).sendKeys("Test Camp List - o48wj");
		action.sendKeys(Keys.ENTER).build().perform();
		String campstat2= driver.findElement(By.id("at0-cell-status-0")).getText();
		System.out.println("	Campaign Status: "+campstat2); //<--campaign status
	}

	@Test(priority = 22)
	public void Caseid_051() throws InterruptedException
	{
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
		driver.findElement(By.id("dashboard_note_text")).sendKeys("\"This is Test Note 1103\"");
		driver.findElement(By.id("add_a_note_popup_btn")).click();
		Thread.sleep(1000);
		System.out.println();
		String Note = driver.findElement(By.cssSelector("div.flex.items-center.mt-5.gap-2+div>div>div>div>div:nth-child(4)>div")).getText();
		System.out.println("Note added: "+Note);
		Assert.assertEquals(Note, "\"This is Test Note 1103\"", "Note not Updated Correctly ");

		//Deleting note
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.flex.items-center.mt-5.gap-2+div>div>div>div>div>div:nth-child(2)>div.note-option>a")).click();
		driver.findElement(By.cssSelector("div.flex.items-center.mt-5.gap-2+div>div>div>div>div>div:nth-child(2)>div.note-option>div>a:nth-child(3)")).click();
		driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
	}
	
	@Test(priority = 25, dependsOnMethods = "Caseid_028")
	public void CountInListCamp() throws Exception 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);

		System.out.println();
		System.out.println("Caseid_28 Validation Test: ");
		//Validating List Campaign Launch -Validating previous campaign 
		driver.navigate().to(Campaigns);

		driver.findElement(By.name("search")).sendKeys("Test Camp List - o48wj");
		action.sendKeys(Keys.ENTER).scrollByAmount(220, 0).build().perform();
		String Count = driver.findElement(By.cssSelector("tr.admintable_row.at-row-0.at-row-pair>td:nth-child(7)>div")).getText();
		Assert.assertEquals(Count, "1","Sent Count Listing Failed in Campaign");

		String RecCount = driver.findElement(By.cssSelector("tr.admintable_row.at-row-0.at-row-pair>td:nth-child(8)>div")).getText();
		Assert.assertEquals(RecCount, "1","Received Count Listing Failed in Campaign");

		driver.navigate().to(Messages);

		driver.findElement(By.name("filters")).sendKeys("List Contact");
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(4000);
		String shortcode = driver.findElement(By.cssSelector("div.conversation_started_messages_div_content>div:last-of-type>div>div:nth-child(2)>div:first-child>span:nth-child(2)")).getText();
		Assert.assertEquals(shortcode, "Contact in Campaign Through list","List Campaign Message Not Found");
		System.out.println("	Test Passed");
	}

	@Test(priority = 26)
	public void CountInTagCamp() throws Exception 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);

		System.out.println();
		System.out.println("Caseid_29 Validation Test: ");

		driver.navigate().to(Campaigns);
		//Validating Tags Campaign Launch -Validating previous campaign 
		driver.findElement(By.name("search")).clear();
		driver.findElement(By.name("search")).sendKeys("Test Camp Tags - o48wj");
		action.sendKeys(Keys.ENTER).scrollByAmount(220, 0).build().perform();

		String Count1 = driver.findElement(By.cssSelector("tr.admintable_row.at-row-0.at-row-pair>td:nth-child(7)>div")).getText();
		Assert.assertEquals(Count1, "1","Sent Count Listing Failed in Campaign");

		String RecCount1 = driver.findElement(By.cssSelector("tr.admintable_row.at-row-0.at-row-pair>td:nth-child(8)>div")).getText();
		Assert.assertEquals(RecCount1, "1","Recived Count Listing Failed in Campaign");

		driver.navigate().to(Messages);

		driver.findElement(By.name("filters")).sendKeys("Tag Test");
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(4000);
		String shortcode1 = driver.findElement(By.cssSelector("div.conversation_started_messages_div_content>div:last-of-type>div>div:nth-child(2)>div:first-child")).getText();
		System.out.println(shortcode1);
		String x[] = shortcode1.split(";",2);
		Assert.assertEquals(x[0], "Contact in campaign through Tag ","Campaign message not recived - Added by Tag");
		System.out.println("	Shortcode REplacement For First Name: "+x[1]);
		System.out.println("	Test Passed");
	}

	@Test(priority = 28, dependsOnMethods = "Caseid_030")
	public void CountInPiplineCamp() throws Exception 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);

		//Validating Pipeline Campaign Launch - Validating previous campaign 
		driver.navigate().to(Campaigns);

		System.out.println();
		System.out.println("Caseid_30 Validation Test: ");
		driver.findElement(By.name("search")).sendKeys("Test Camp Pipeline - o48wj");
		action.sendKeys(Keys.ENTER).scrollByAmount(220, 0).build().perform();
		String Count = driver.findElement(By.cssSelector("tr.admintable_row.at-row-0.at-row-pair>td:nth-child(7)>div")).getText();
		Assert.assertEquals(Count, "1","Sent Count Listing Failed in Campaign");

		String RecCount = driver.findElement(By.cssSelector("tr.admintable_row.at-row-0.at-row-pair>td:nth-child(8)>div")).getText();
		Assert.assertEquals(RecCount, "1","Received Count Listing Failed in Campaign");

		driver.navigate().to(Messages);

		driver.findElement(By.name("filters")).sendKeys("Pipeline Contact");
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(4000);
		String shortcode = driver.findElement(By.cssSelector("div.conversation_started_messages_div_content>div:last-of-type>div>div:nth-child(2)>div:first-child")).getText();
		
		String arr1[] = shortcode.split("break",2);
		String firstWord1 = arr1[0];

		Assert.assertEquals(firstWord1, "Contact in campaign through Pipeline ","Pipeline Campaign Message Not Found");
		System.out.println("	Test Passed");
	}
	
	@Parameters({"uname","pass"})
	@Test(priority = 27)
	public void CampReply(String uname, String pass) throws Exception
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		Actions action = new Actions(driver);
		
		Relogin("nadsoft.test99@gmail.com", "Nadsoft@1234");

		driver.navigate().to(Messages);
		System.out.println();
		System.out.println("Verifying Campaign Reply ----->");

		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.custom-sms-contact>div:nth-of-type(3)>a:first-of-type")).click();
		driver.findElement(By.name("filters")).sendKeys("Automation test");
		Thread.sleep(2000);
		action.sendKeys(Keys.ENTER).perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#convoWrapper > div.conversation_started_messages_div_content > div.sms-box:last-of-type>div>div:nth-of-type(2)>div:first-of-type")));
		String msg = driver.findElement(By.cssSelector("#convoWrapper > div.conversation_started_messages_div_content > div.sms-box:last-of-type>div>div:nth-of-type(2)>div:first-of-type")).getText();

		String arr1[] = msg.split("break",2);
		String firstWord1 = arr1[0];

		if(firstWord1.equalsIgnoreCase("Contact in campaign through Pipeline "))
		{
			System.out.println("	Campaign Message Recived");
			
		}
		else 
		{
			Thread.sleep(5000);
			driver.navigate().refresh();
			String msg1 = driver.findElement(By.cssSelector("#convoWrapper > div.conversation_started_messages_div_content > div.sms-box:last-of-type>div>div:nth-of-type(2)>div:first-of-type")).getText();
			String arr2[] = msg1.split("break", 2);
			String firstWord2 = arr2[0]; 
			System.out.println("Recived message:"+firstWord2);
			Assert.assertEquals(firstWord2,"Hi this is test SMS from campaign ", "Campaign Message Not Recived");
		}

		driver.findElement(By.xpath("//*[@id=\"dashboard-conversation-text\"]"))
		.sendKeys("Hi this is test message for daily testing");
		driver.findElement(By.xpath("//*[@id=\"msgbox_messages\"]/div[3]/div[2]")).click();//<---send

		try
		{
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"toast-container\"]/div")));
			String msg1 = driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div")).getText();
			System.out.println("	Reply "+msg1);
		}
		catch(Exception TimeOutException) 
		{
			System.out.println("Message not sent");
		}
		Relogin(uname, pass);
	}

	//@Test(priority = 25)
	public void PiplineCountInCamp() throws Exception 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//JavascriptExecutor jse = (JavascriptExecutor)driver;
	}

	//@Test
	public void PipelineCase() throws Exception//Automation Pending Due to issue
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
		driver.navigate().to(Pipelines);
		Actions action = new Actions(driver);

		//all contacts move in one stage (check automation trigger popup function and add customer in stage feature)
		//String beforecount =  driver.findElement(By.cssSelector("div.dragable-header>div>div:nth-of-type(2)>div>span.total-count")).getText();
		driver.findElement(By.cssSelector("div.dragable-header>div>div:nth-of-type(2)>div:nth-of-type(2)>div>a")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#add-pp-stage-popup>div>div>a:nth-of-type(3)")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#js-tags-selector44+div>div:first-child")).click();
		action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
		driver.findElement(By.cssSelector("#add-contact-popup>div>div>div:last-of-type>div:last-of-type>button")).click();

	}

	@Parameters({"uname", "pass"})
	public void Relogin(String uname, String pass ) throws Exception//Uracrhive user not able to revert (message sent from superadmin acnt)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor jse = (JavascriptExecutor)driver;

		//Relogin
		driver.findElement(By.xpath("//*[@id=\"dropdownMenu1\"]")).click();
		jse.executeScript("document.querySelector('div.header-dropdown-menu').scrollTop=500");
		driver.findElement(By.linkText("Logout")).click();
		WebElement emf1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		emf1.sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pass);
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[4]/button[1]")).click();
	}
}



