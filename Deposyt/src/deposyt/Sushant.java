package deposyt;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import AccessLevel.Data;

public class Sushant extends Data
{
	@BeforeMethod
	public void navigation() 
	{
		driver.navigate().to(Dashboard);
	}

	@Test(priority = 1)
	public void CC_001() throws InterruptedException 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		Actions action = new Actions(driver);
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(15));

		System.out.println("CC_001: Check after added new customer and its count update in Dashboard and Customer tab");

		//Checking for duplicate customers
		driver.navigate().to(Customers);
		driver.findElement(By.name("search")).sendKeys("4012057373");
		action.sendKeys(Keys.ENTER).build().perform();

		try 
		{
			driver.findElement(By.id("at0-cell-actions_col-0")).click();
			driver.findElement(By.xpath("//*[@id=\"at0-cell-actions_col-0\"]/div/div/a[6]")).click();
			driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();	
		}
		catch (Exception NoSuchElementExcepation) 
		{
			driver.navigate().refresh();
		}	

		driver.findElement(By.name("search")).clear();
		driver.findElement(By.name("search")).sendKeys("rohan@nadsoftdesign.com");
		action.sendKeys(Keys.ENTER).build().perform();

		try 
		{
			driver.findElement(By.id("at0-cell-actions_col-0")).click();
			driver.findElement(By.xpath("//*[@id=\"at0-cell-actions_col-0\"]/div/div/a[6]")).click();
			driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
			Thread.sleep(5000);	
		}
		catch (Exception NoSuchElementExcepation) 
		{
		}

		driver.navigate().to(Dashboard);
		String BeforeDashboard1 = driver.findElement(By.cssSelector("div.leads>div>h1>span")).getText();
		System.out.println("	Count On Dashboard before Adding Customer: "+BeforeDashboard1);
		int BeforeDashboardint = Integer.parseInt(BeforeDashboard1);
		BeforeDashboardint = BeforeDashboardint + 1;
		String BeforeDashboard = Integer.toString(BeforeDashboardint);

		driver.navigate().to(Customers);
		String BeforeCustomers1 = driver.findElement(By.cssSelector("input[name=\"allcustcount\"]")).getAttribute("value");
		System.out.println("	Count in Customers before Adding Customer: "+BeforeCustomers1);
		int BeforeCustomersint = Integer.parseInt(BeforeCustomers1);
		BeforeCustomersint = BeforeCustomersint + 1;
		String BeforeCustomers = Integer.toString(BeforeCustomersint);

		//Adding customer-->
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/a[1]")).click();// add customer button
		driver.findElement(By.name("first_name")).sendKeys("Weaired testname");
		driver.findElement(By.name("email")).sendKeys("rohan@nadsoftdesign.com");	
		driver.findElement(By.id("cellphone")).sendKeys("14012057373");
		driver.findElement(By.xpath("//*[@id=\"add_contact_form\"]/form[1]/div[2]/input[1]")).click();

		try 
		{
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("section.alertify-logs")).isDisplayed();
		}
		catch(Exception NoSuchElementExcepation) 
		{
			driver.findElement(By.xpath("//*[@id=\"add_contact_form\"]/form[1]/div[2]/input[1]")).click();
		}

		String success = null;
		try 
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("alertify-logs")));
			success = driver.findElement(By.id("alertify-logs")).getText();
		}
		catch(Exception TimeOutException) 
		{
		}
		System.out.println("	"+success);

		driver.navigate().to(Dashboard);
		String AfterDashboard = driver.findElement(By.cssSelector("div.leads>div>h1>span")).getText();
		System.out.println("	Count on Dashboard After Adding New Customer: "+AfterDashboard);

		driver.navigate().to(Customers);
		String AfterCustomers = driver.findElement(By.cssSelector("input[name=\"allcustcount\"]")).getAttribute("value");
		System.out.println("	Count in Customers After Adding New Customer: "+AfterCustomers);

		//Check after delete customer its count updated in dashboard and in customer tab 
		System.out.println();
		System.out.println("CC_002: Check after delete customer its count updated in dashboard and in customer tab ");

		driver.findElement(By.name("search")).sendKeys("4012057373");
		action.sendKeys(Keys.ENTER).build().perform();

		driver.findElement(By.id("at0-cell-actions_col-0")).click();
		driver.findElement(By.xpath("//*[@id=\"at0-cell-actions_col-0\"]/div/div/a[6]")).click();
		driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
		Thread.sleep(1000);
		String Delete = driver.findElement(By.id("toast-container")).getText();
		System.out.println("	"+Delete);

		driver.navigate().to(Dashboard);
		String AfterDeleteDashboard = driver.findElement(By.cssSelector("div.leads>div>h1>span")).getText();
		System.out.println("	Count on Dashboard After Deleting Customer: "+AfterDeleteDashboard);

		driver.navigate().to(Customers);
		String AfterDeleteCustomers = driver.findElement(By.cssSelector("input[name=\"allcustcount\"]")).getAttribute("value");
		System.out.println("	Count in Customers After Deleting Customer: "+AfterDeleteCustomers);

		System.out.println();
		System.out.println("CC_003: Check after delete customer From Message its count updated on Dashboard");

		//Adding customer-->
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/a[1]")).click();// add customer button
		driver.findElement(By.name("first_name")).sendKeys("Weaired testname");
		driver.findElement(By.name("email")).sendKeys("rohan@nadsoftdesign.com");	
		driver.findElement(By.id("cellphone")).sendKeys("14012057373");
		driver.findElement(By.xpath("//*[@id=\"add_contact_form\"]/form[1]/div[2]/input[1]")).click();

		try 
		{
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("section.alertify-logs")).isDisplayed();
		}
		catch(Exception NoSuchElementExcepation) 
		{
			driver.findElement(By.xpath("//*[@id=\"add_contact_form\"]/form[1]/div[2]/input[1]")).click();
		}

		try 
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("alertify-logs")));
			success = driver.findElement(By.id("alertify-logs")).getText();
		}
		catch(Exception TimeOutException) 
		{
		}
		System.out.println("	"+success);

		driver.navigate().to(Dashboard);
		String BDFMDash = driver.findElement(By.cssSelector("div.leads>div>h1>span")).getText();
		System.out.println("	Count on Dashboard Before Deleting Customer From Messages: "+BDFMDash);

		driver.navigate().to(Messages);
		Thread.sleep(5000);
		driver.findElement(By.name("filters")).sendKeys("Weaired testname");
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(4000);

		driver.findElement(By.cssSelector("div.desktop.customer-info-slideout>div.selectact>div:first-child>div>#dropdownMenu3")).click();
		jse.executeScript("document.querySelector('a[aria-expanded = \"true\"]#dropdownMenu3+div').scrollTop = 500");
		driver.findElement(By.cssSelector("a[aria-expanded = \"true\"]#dropdownMenu3+div>a:last-of-type")).click();
		driver.findElement(By.cssSelector("button.deleteactionperform")).click();

		try
		{
			Thread.sleep(2000);
			String DeleteFM  = driver.findElement(By.cssSelector("toast-container")).getText();
			System.out.println("	"+DeleteFM);
		}
		catch(Exception e)
		{
		}

		driver.navigate().to(Dashboard);
		String ADFMDash = driver.findElement(By.cssSelector("div.leads>div>h1>span")).getText();
		System.out.println("	Count on Dashboard After Deleting Customer From Messages: "+ADFMDash);

		//Assertions
		Assert.assertEquals(BeforeDashboard, AfterDashboard, "Count On Dashbord Not Updating After Adding New Customer");
		Assert.assertEquals(BeforeCustomers, AfterCustomers, "Count in Customers Not Updating After Adding New Customer");
		Assert.assertEquals(BeforeCustomers1, AfterDeleteCustomers, "Count in Customers Not Updating After Deleting Customer");
		Assert.assertEquals(BeforeCustomers1, AfterDeleteDashboard, "Count in Customers Not Updating After Deleting Customer");
		Assert.assertEquals(AfterDeleteDashboard, ADFMDash);
	}

	@Test(priority = 2)
	public void CC_004() throws InterruptedException 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(15));

		//Checking for duplicate customers
		driver.navigate().to(Customers);
		driver.findElement(By.name("search")).sendKeys("4566541234");
		action.sendKeys(Keys.ENTER).build().perform();

		try 
		{
			driver.findElement(By.id("at0-cell-actions_col-0")).click();
			driver.findElement(By.xpath("//*[@id=\"at0-cell-actions_col-0\"]/div/div/a[6]")).click();
			driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();	
		}
		catch (Exception NoSuchElementExcepation) 
		{
			driver.navigate().refresh();
		}	

		//Adding customer-->
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/a[1]")).click();// add customer button
		driver.findElement(By.name("first_name")).sendKeys("ForNote testname");	
		driver.findElement(By.id("cellphone")).sendKeys("4566541234");
		driver.findElement(By.xpath("//*[@id=\"add_contact_form\"]/form[1]/div[2]/input[1]")).click();

		try 
		{
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("section.alertify-logs")).isDisplayed();
		}
		catch(Exception NoSuchElementExcepation) 
		{
			driver.findElement(By.xpath("//*[@id=\"add_contact_form\"]/form[1]/div[2]/input[1]")).click();
		}

		String success = null;
		try 
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("alertify-logs")));
			success = driver.findElement(By.id("alertify-logs")).getText();
		}
		catch(Exception TimeOutException) 
		{
		}
		System.out.println("	"+success);

		driver.navigate().to(Dashboard);
		System.out.println();
		System.out.println("CC_004: Check After adding note With Reminder its count is updated"
				+ "CC_005	Check add ongoing reminder its count updated or not ");
		String BeforeNoteDashboard1 = driver.findElement(By.cssSelector("div.mynotes>div>h1>span")).getText();
		System.out.println("	Notes Count On Dashboard Before Adding Note: "+BeforeNoteDashboard1);
		int BeforeNoteDashboardint = Integer.parseInt(BeforeNoteDashboard1);
		BeforeNoteDashboardint = BeforeNoteDashboardint + 1;
		String BeforeNoteDashboard = Integer.toString(BeforeNoteDashboardint);

		String BeforeReminderDashboard1 = driver.findElement(By.cssSelector("div.mynotes>div>a:first-child>span")).getText();
		System.out.println("	Reminder Count On Dashboard Before Adding Reminder: "+BeforeReminderDashboard1);
		int BeforeReminderDashboardint = Integer.parseInt(BeforeReminderDashboard1);
		BeforeReminderDashboardint = BeforeReminderDashboardint + 1;
		String BeforeReminderDashboard = Integer.toString(BeforeReminderDashboardint);

		driver.navigate().to(Notes);
		//Adding new Note With basic Reminder
		driver.findElement(By.xpath("//*[@id=\"notes-toggler\"]/div[1]/div[1]/div[1]/div[1]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"js-tags-selectorpop-selectized\"]")).sendKeys("ForNote testname");
		action.sendKeys(Keys.ENTER).perform();
		Thread.sleep(1000);
		driver.findElement(By.id("dashboard_note_text")).sendKeys("This is Test Note sdndsl");
		driver.findElement(By.cssSelector("div.note-option.anc_set_reminder>a")).click();
		driver.findElement(By.cssSelector("#editreminderco+a")).click();
		driver.findElement(By.id("setnotereminder")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input#basicOnOff+span")).click();
		driver.findElement(By.cssSelector("#error_messagesbasic+a")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("add_a_note_popup_btn")).click();
		Thread.sleep(2000);

		driver.navigate().to(Dashboard);
		String AfterNoteDashboard = driver.findElement(By.cssSelector("div.mynotes>div>h1>span")).getText();
		System.out.println("	Notes Count On Dashboard After Adding Note: "+AfterNoteDashboard);

		String AfterReminderDashboard = driver.findElement(By.cssSelector("div.mynotes>div>a:first-child>span")).getText();
		System.out.println("	Reminder Count On Dashboard After Adding Reminder: "+AfterReminderDashboard);

		//Assertions - After Adding Note with reminder	
		Assert.assertEquals(BeforeNoteDashboard, AfterNoteDashboard, "Notes Count Not Updating On Dashboard");
		Assert.assertEquals(BeforeReminderDashboard, AfterReminderDashboard, "Reminder Count Not Updating On Dashboard");

		driver.navigate().to(Notes);
		System.out.println();
		System.out.println("CC_006:	Check after clear reminder ongoing and verify in dashboard it count updated or not ");

		driver.findElement(By.name("search")).sendKeys("This is Test Note sdndsl");
		action.sendKeys(Keys.ENTER).perform();
		Thread.sleep(1000);
		WebElement note = driver.findElement(By.id("pinnedNotesDiv"));
		action.moveToElement(note).perform();
		driver.findElement(By.cssSelector("#setnotereminder+div")).click();
		Thread.sleep(2000);

		driver.navigate().to(Dashboard);
		String ARRNoteDashboard = driver.findElement(By.cssSelector("div.mynotes>div>h1>span")).getText();
		System.out.println("	Notes Count On Dashboard After Removing Reminder: "+ARRNoteDashboard);

		String ARRReminderDashboard = driver.findElement(By.cssSelector("div.mynotes>div>a:first-child>span")).getText();
		System.out.println("	Reminder Count On Dashboard After Removing Reminder: "+ARRReminderDashboard);

		//Assertions - after removing Reminder
		Assert.assertEquals(ARRNoteDashboard, AfterNoteDashboard, "Note Count Changed after Removing Reminder");
		Assert.assertEquals(ARRReminderDashboard, BeforeReminderDashboard1, "Reminder Count Not updating after Removing Reminder");

		driver.navigate().to(Notes);
		System.out.println();
		System.out.println();
		driver.findElement(By.name("search")).sendKeys("This is Test Note sdndsl");
		action.sendKeys(Keys.ENTER).perform();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.note-option>a")).click();
		driver.findElement(By.cssSelector("#dropdownMenu2[aria-expanded=\"true\"]+div>a:nth-child(3)")).click();
		Thread.sleep(500);
		driver.findElement(By.cssSelector("button.deleteactionperform")).click();
		Thread.sleep(2000);

		driver.navigate().to(Dashboard);
		String ADNNoteDashboard = driver.findElement(By.cssSelector("div.mynotes>div>h1>span")).getText();
		System.out.println("	Notes Count On Dashboard After Deleting Note: "+ADNNoteDashboard);

		String ADNReminderDashboard = driver.findElement(By.cssSelector("div.mynotes>div>a:first-child>span")).getText();
		System.out.println("	Reminder Count On Dashboard After Deleting Note: "+ADNReminderDashboard);

		//Assertions - After Deleting Note
		Assert.assertEquals(ADNNoteDashboard , BeforeNoteDashboard1, "Note Count Not Updating");
		Assert.assertEquals(ADNReminderDashboard, ARRReminderDashboard,"Reminder count is updating if user delete only Note W/O Reminder");
	}

	@Test(priority = 3)
	public void CC_008() throws InterruptedException 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		Actions action = new Actions(driver);

		System.out.println();
		System.out.println("CC_008: Check count of appointment after booking appointment");	

		String BeforeAppointmentDashboard = driver.findElement(By.cssSelector("div.top-header>div:nth-child(2)>div>div>h1>span")).getText();
		System.out.println("	Appointment Count On Dashboard Before Booking Appointment: "+BeforeAppointmentDashboard);
		int BeforeDashboardint = Integer.parseInt(BeforeAppointmentDashboard);
		BeforeDashboardint = BeforeDashboardint + 1;
		BeforeAppointmentDashboard = Integer.toString(BeforeDashboardint);

		String BATD = driver.findElement(By.cssSelector("div.top-header>div:nth-child(2)>div>div+div>a:first-child")).getText();
		String BATDashboard[] = BATD.split(" ",3); 
		int BATDashboardint = Integer.parseInt(BATDashboard[2]);
		BATDashboardint = BATDashboardint + 1;
		BATDashboard[1] = Integer.toString(BATDashboardint);

		driver.navigate().to(AppointmentTypes);
		//Checking for duplicate
		driver.findElement(By.name("search")).sendKeys("Test Appointment Sushat Script dfsknl");
		action.sendKeys(Keys.ENTER).perform();

		try 
		{
			driver.findElement(By.cssSelector("div.eventcontentdiv>div:first-of-type>div:nth-of-type(2)>a:nth-child(2)")).click();
			Thread.sleep(2000);
		}
		catch(Exception q) 
		{	
			//Creating New Appointment
			driver.findElement(By.id("eventtypebutton")).click();
			driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[2]/div[2]/div/div/div/div[1]/div[1]/div/div[2]/a")).click();
			driver.findElement(By.id("appttypename_title")).sendKeys("Test Appointment Sushat Script dfsknl");
			driver.findElement(By.cssSelector("div.mb-5.loc>div.nice-select:first-of-type")).click();
			jse.executeScript("scroll(0,200)");
			driver.findElement(By.cssSelector("li[data-value=\"inperson\"]")).click();
			driver.findElement(By.id("inperson_address")).sendKeys("random");
			driver.findElement(By.id("appttypedesc")).sendKeys("Test user");
			jse.executeScript("scroll(0,0)");
			driver.findElement(By.cssSelector("div.appt-head-section>div:nth-of-type(3)>button[type=\"button\"]")).click();
			Thread.sleep(2000);

			jse.executeScript("scroll(0,500)");		
			driver.findElement(By.cssSelector("div.scheduleinnerdata>div:first-child>div>div+div")).click();
			driver.findElement(By.cssSelector("span.select2-selection__rendered")).click();
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
			jse.executeScript("scroll(0,0)");
			driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[2]/div[2]/div/div/div[1]/div[1]/div[1]/div/div[4]/div[1]/div[2]/button")).click();
			Thread.sleep(2000);
			jse.executeScript("scroll(0,200)");
			driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[2]/div[2]/div/div/div[1]/div[1]/div[1]/div/div[6]/div[1]/div[2]/button")).click();
			Thread.sleep(2000);
			jse.executeScript("scroll(0,400)");
			driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[2]/div[2]/div/div/div[1]/div[1]/div[1]/div/div[7]/div[1]/div[2]/button")).click();
			Thread.sleep(2000);
			jse.executeScript("scroll(0,400)");
			driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[2]/div[2]/div/div/div[1]/div[1]/div[1]/div/div[8]/div[1]/div[2]/button")).click();
			jse.executeScript("scroll(0,0)");
		}

		String pwindo = driver.getWindowHandle();
		//landing page
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

		try 
		{
			driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div/div[2]/div[1]/div/div[2]/div/div/div[1]/button[2]")).click();
		}
		catch(Exception NoSuchElementException) 
		{
			driver.findElement(By.cssSelector("tr:nth-of-type(4)>td[data-handler=selectDay]:nth-of-type(2)>a[href=\"#\"]")).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("button.time-button:first-of-type")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div/div[2]/div[1]/div/div[2]/div/div/div[1]/button[2]")).click();
		}

		driver.findElement(By.name("contactname")).sendKeys("Test name 110022");
		driver.findElement(By.name("contactemail")).sendKeys("nadsoft.test99@gmail.com");
		driver.findElement(By.name("contactphone")).sendKeys("1234567890");
		driver.findElement(By.cssSelector("button.sch-event-btn")).click();
		Thread.sleep(2000);
		driver.close();
		driver.switchTo().window(pwindo);

		driver.navigate().to(Dashboard);
		//Total Appointment count -After 
		String AfterAppointmentDashboard = driver.findElement(By.cssSelector("div.top-header>div:nth-child(2)>div>div>h1>span")).getText();
		System.out.println("	Total Appointment Count on Dashboard After Scheduling Appointment: "+AfterAppointmentDashboard);

		//Today's Appointment Count-After
		System.out.println();
		System.out.println("CC_009: Check after booking in dashboard today appointment count updated or not");	

		System.out.println("	Today's Appointment Count On Dashboard Before Booking Appointment: "+BATDashboard[2]);
		String AATD = driver.findElement(By.cssSelector("div.top-header>div:nth-child(2)>div>div+div>a:first-child")).getText();
		String AATDashboard[] = AATD.split(" ",3); 
		System.out.println("	Today's Appointment Count On Dashboard After Booking Appointment: "+AATDashboard[2]);

		//Assertions- After booking Appointment
		Assert.assertEquals(BeforeAppointmentDashboard, AfterAppointmentDashboard, "Total Appointment count not Updating On Dashboard");
		Assert.assertEquals(BATDashboard[1], AATDashboard[2], "Today's Appointment Count Not Updating");
		driver.navigate().to(Calendar);
		driver.findElement(By.name("search")).sendKeys("Test Appointment Sushat Script dfsknl");
		action.sendKeys(Keys.ENTER).perform();

		driver.findElement(By.cssSelector("div.new-single-appointment.grid.rounded-lg>h5")).click();
		driver.findElement(By.cssSelector("#new-open-comman-appointmentdetails-modal>div>div>div.appt-content.grid.mt-5>div.pr-8.relative>a:nth-child(3)")).click();
		driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
	}

	@Test(priority = 4)
	public void CC_010() throws InterruptedException 
	{
		Actions action =new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		System.out.println();
		System.out.println("CC_010: Check after sent SMS in dashboard its sent SMS count updated");

		String BeforeSent  = driver.findElement(By.cssSelector("div.emails.pt-7.mb-3>div:nth-Child(2)>h3:first-child>span")).getText();
		System.out.println("	Sent SMS Count On Dashboard Before Sending SMS: "+BeforeSent);
		int BSInt = Integer.parseInt(BeforeSent);
		BSInt = BSInt + 1;
		BeforeSent = Integer.toString(BSInt);

		//<--Message-->
		driver.navigate().to(Messages);
		driver.findElement(By.name("filters")).sendKeys("Weaired testname");
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(4000);

		driver.findElement(By.id("dashboard-conversation-text"))
		.sendKeys("Hi this is test message for daily testing cVUj4KSj ");
		driver.findElement(By.xpath("//*[@id=\"msgbox_messages\"]/div[3]/div[2]")).click();//<---send
		String msg = null;

		try
		{
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"toast-container\"]/div")));
			msg = driver.findElement(By.id("toast-container")).getText();
		}
		catch(Exception TimeOutException) 
		{
			System.out.println("	Message not sent");
		}
		System.out.println("	"+msg);

		driver.navigate().to(Dashboard);
		String AfterSent  = driver.findElement(By.cssSelector("div.emails.pt-7.mb-3>div:nth-Child(2)>h3:first-child>span")).getText();
		System.out.println("	Sent SMS Count On Dashboard After Sending SMS: "+AfterSent);
		Assert.assertEquals(BeforeSent, AfterSent, "Dashboard Count Not Updating After Sending Message");
	}

	@Test(priority = 5)
	@Parameters({"uname","pass"})
	public void CC_011(String uname, String pass) throws InterruptedException 
	{
		Actions action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));

		System.out.println();
		System.out.println("CC_011: Check after receiving SMS count update in message tab and also in dashboard");
		
		String Beforenotifications1 = driver.findElement(By.cssSelector("#notifyDashTop>div>h1>span")).getText();
		System.out.println("	Total Notifications: "+Beforenotifications1);
		int BNInt = Integer.parseInt(Beforenotifications1);
		BNInt = BNInt + 1;
		String Beforenotifications = Integer.toString(BNInt);
		
		String BeforeSMS1 = driver.findElement(By.cssSelector("a.notifyDashTopSms>span")).getText();
		System.out.println("	Notification SMS Count: "+BeforeSMS1);
		int BSMSInt = Integer.parseInt(BeforeSMS1);
		BSMSInt = BSMSInt + 1;
		String BeforeSMS = Integer.toString(BSMSInt);

		String BeforeReceive  = driver.findElement(By.cssSelector("div.emails.pt-7.mb-3>div:nth-Child(2)>h3:nth-child(2)>span")).getText();
		System.out.println("	Recived SMS Count On Dashboard Before Receiving New SMS: "+BeforeReceive);
		int BSInt = Integer.parseInt(BeforeReceive);
		BSInt = BSInt + 1;
		BeforeReceive = Integer.toString(BSInt);

		String BeforeUnread1  = driver.findElement(By.cssSelector("div.emails.pt-7.mb-3>div:nth-Child(2)>h3:nth-child(3)>p")).getText();
		System.out.println("	Unread SMS Count On Dashboard Before Receiving New SMS: "+BeforeUnread1);
		int BUInt = Integer.parseInt(BeforeUnread1);
		BUInt = BUInt + 1;
		String BeforeUnread = Integer.toString(BUInt);

		String MyNumber = driver.findElement(By.cssSelector("#showallCategory>div>div.relative.linkssubdiv>input")).getAttribute("value");
		
		//Login To anather test account
		logout();
		WebElement emf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		emf.sendKeys(MyAccLogin);
		driver.findElement(By.id("password")).sendKeys(MyAccPass);
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[4]/button[1]")).click();

		driver.navigate().to(Customers);
		//Checking for duplicate customers
		driver.findElement(By.name("search")).sendKeys(MyNumber);
		action.sendKeys(Keys.ENTER).build().perform();

		String name = null;
		try 
		{
			driver.findElement(By.id("at0-cell-actions_col-0")).click();
			driver.findElement(By.cssSelector("div.customer-more-option.open>div>a[title=\"Go To Profile\"]")).click();
			Thread.sleep(500);
			name = driver.findElement(By.name("first_name")).getText(); 
		}
		catch (org.openqa.selenium.NoSuchElementException e) 
		{
			driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/a[1]")).click();// add customer button

			driver.findElement(By.name("first_name")).sendKeys("Count Test");	
			driver.findElement(By.id("cellphone")).sendKeys(MyNumber);
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
			name = "Count Test";
		}

		driver.navigate().to(Messages);
		driver.findElement(By.name("filters")).sendKeys(name);
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(4000);

		driver.findElement(By.id("dashboard-conversation-text"))
		.sendKeys("Hi Check Count ute97d");
		driver.findElement(By.xpath("//*[@id=\"msgbox_messages\"]/div[3]/div[2]")).click();//<---send
		String msg = null;

		try
		{
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"toast-container\"]/div")));
			msg = driver.findElement(By.id("toast-container")).getText();
		}
		catch(Exception TimeOutException) 
		{
			System.out.println("	Message not sent");
		}
		System.out.println();
		System.out.println("	"+msg+" From Account Two");
		System.out.println();

		String Number2 = driver.findElement(By.cssSelector("#showallCategory>div>div.relative.linkssubdiv>input")).getAttribute("value");
		logout();
		
		//Login to test account
		WebElement emf1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		emf1.sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pass);
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[4]/button[1]")).click();
		Thread.sleep(4000);
		
		String	Afternotifications = driver.findElement(By.cssSelector("#notifyDashTop>div>h1>span")).getText();
		System.out.println("	Total Notifications After Reciving SMS: "+Afternotifications);
		
		String AfterSMS = driver.findElement(By.cssSelector("a.notifyDashTopSms>span")).getText();
		System.out.println("	Notification SMS Count After Reciving SMS: "+AfterSMS);
		
		String AfterReceive = driver.findElement(By.cssSelector("div.emails.pt-7.mb-3>div:nth-Child(2)>h3:nth-child(2)>span")).getText();
		System.out.println("	Recived SMS Count On Dashboard After Receiving SMS: "+AfterReceive);

		String AfterUnread = driver.findElement(By.cssSelector("div.emails.pt-7.mb-3>div:nth-Child(2)>h3:nth-child(3)>p")).getText();
		System.out.println("	Unread SMS Count On Dashboard After Receiving SMS: "+AfterUnread);
		
		driver.navigate().to(Customers);
		//Checking for duplicate customers
		driver.findElement(By.name("search")).sendKeys(Number2);
		action.sendKeys(Keys.ENTER).build().perform();

		//Marking Unread SMS as Read
		String name1 = null;
		try 
		{
			driver.findElement(By.id("at0-cell-actions_col-0")).click();
			driver.findElement(By.cssSelector("div.customer-more-option.open>div>a[title=\"Go To Profile\"]")).click();
			Thread.sleep(500);
			name1 = driver.findElement(By.name("first_name")).getText(); 
		}
		catch (org.openqa.selenium.NoSuchElementException e) 
		{
			driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/a[1]")).click();// add customer button

			driver.findElement(By.name("first_name")).sendKeys("Count Test");	
			driver.findElement(By.id("cellphone")).sendKeys(Number2);
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
			name1 = "Count Test";
		}

		driver.navigate().to(Messages);
		driver.findElement(By.name("filters")).sendKeys(name1);
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(4000);
		
		driver.findElement(By.cssSelector("#cntzissendmessageclick+a")).click();//Unread QuickFilter
		WebElement Icon	= driver.findElement(By.id("read_unread_dd"));
		WebElement Hover = driver.findElement(By.cssSelector("#custom-sms-contact-list>div:first-child"));
		action.moveToElement(Hover).perform();
		Icon.click();
		driver.findElement(By.cssSelector("#read_unread_dd[aria-expanded=\"true\"]+div>a:nth-child(2)")).click();//Mark as read
		
		driver.navigate().to(Dashboard);
		String	Afternotifications1 = driver.findElement(By.cssSelector("#notifyDashTop>div>h1>span")).getText();
		System.out.println("	Total Notifications After Marking SMS as Read: "+Afternotifications1);
		
		String AfterUnread1 = driver.findElement(By.cssSelector("div.emails.pt-7.mb-3>div:nth-Child(2)>h3:nth-child(3)>p")).getText();
		System.out.println("	Unread SMS Count On Dashboard After Marking SMS as Read: "+AfterUnread1);
		
		String AfterSMS1 = driver.findElement(By.cssSelector("a.notifyDashTopSms>span")).getText();
		System.out.println("	Notification SMS Count After Reciving SMS: "+AfterSMS1);
		 
		//Assertions
		Assert.assertEquals(Beforenotifications, Afternotifications,"Notification count is not updating after receiving msg");
		Assert.assertEquals(BeforeSMS, AfterSMS,"SMS notification count not updating after reciving SMS");
		Assert.assertEquals(BeforeReceive, AfterReceive,"Recive SMS Count on Dasbord is not updating After Reciving SMS");
		Assert.assertEquals(BeforeUnread, AfterUnread,"Unread SMS Count Not Updating After Reciving SMS");
		Assert.assertEquals(Beforenotifications1, Afternotifications1,"Notification Count is not updating After marking SMS as Unread");
		Assert.assertEquals(BeforeUnread1, AfterUnread1,"unread SMS Count is not updating After marking SMS as Unread");
		Assert.assertEquals(BeforeSMS1, AfterSMS1,"SMS Notification Count Not Updating After Marking SMS As Unread");
	}

	@Test(priority = 6)
	public void CC_013() throws InterruptedException 
	{
		Actions action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));

		System.out.println();
		System.out.println("CC_012: Check after sent email, in dashboard its sent email count updated");

		String BeforeSent  = driver.findElement(By.cssSelector("div.emails.mb-2>div:nth-child(2)>h3:first-child>span")).getText();
		System.out.println("	Sent Mail Count On Dashboard Before Sending Mail: "+BeforeSent);
		int BSInt = Integer.parseInt(BeforeSent);
		BSInt = BSInt + 1;
		BeforeSent = Integer.toString(BSInt);

		driver.navigate().to(Messages);
		Thread.sleep(5000);
		driver.findElement(By.name("filters")).sendKeys("@gmail.com");
		action.sendKeys(Keys.ENTER).build().perform();

		Thread.sleep(4000);
		driver.findElement(By.id("calling_email")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"emailbox_messages\"]/form/div[4]/div[1]")));

		driver.findElement(By.id("subject_email"))
		.sendKeys("Hi this is test mail ");
		action.sendKeys(Keys.TAB)
		.sendKeys("This is test mail")
		.build().perform();

		Thread.sleep(2000);
		driver.findElement(By.cssSelector("a#convoSendEmailBtn")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("calling_sms")).click();
		Thread.sleep(1000);
		String mail = driver.findElement(By.cssSelector("div#toast-container > div > div")).getText();
		System.out.println("	"+mail);

		driver.navigate().to(Dashboard);
		String AfterSent  = driver.findElement(By.cssSelector("div.emails.mb-2>div:nth-child(2)>h3:first-child>span")).getText();
		System.out.println("	Sent Mail Count On Dashboard After Sending Mail: "+AfterSent);
		int ASInt = Integer.parseInt(AfterSent);
		BSInt = BSInt + 1;
		AfterSent = Integer.toString(ASInt);
		Assert.assertEquals(BeforeSent, AfterSent);
	}	

	@Test(priority = 7)//Check Local File path
	public void CC_015() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
		Actions action = new Actions(driver);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		driver.navigate().to(Lists);

		//Deleting Duplicate
		driver.findElement(By.name("search")).sendKeys("Test List - d8dks8");
		action.sendKeys(Keys.ENTER).perform();
		Thread.sleep(2000);

		try
		{
			driver.findElement(By.cssSelector("td#at0-cell-action-0>div>a[title=\"Delete\"]")).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("#deleteform>div>button")).click();
		}
		catch(Exception nosuchelementException)
		{
		}

		driver.findElement(By.cssSelector("div.list-second-btn-response>a:last-child")).click();
		driver.findElement(By.cssSelector("div.list-wrapper:first-of-type>a")).click();

		System.out.println();
		System.out.println("CC_015: Check in list added multiple user in any contact list and verify its count updated or not");

		driver.findElement(By.name("list_title")).sendKeys("Test List - d8dks8");
		driver.findElement(By.cssSelector("button[value=\"Upload\"]")).click();
		driver.findElement(By.id("userfile")).sendKeys("C:\\Users\\Rohan kokare\\eclipse-workspace\\CentzCRM\\Sources\\dummydata1.csv");//Import List path
		driver.findElement(By.cssSelector("input[value=\"Upload\"]")).click();
		driver.findElement(By.cssSelector("input[value=\"Next\"]")).click();

		for(int i=2; i <= 5; i++)
		{
			int j = i-1;
			driver.findElement(By.cssSelector("div.addcustomer>div:nth-of-type("+i+")>div.col-md-6>div")).click();
			driver.findElement(By.cssSelector("div.addcustomer>div:nth-of-type("+i+")>div.col-md-6>div>ul>li[data-value=\""+j+"\"]")).click();
			if(i==4)
			{
				action.scrollByAmount(0, 300).perform();
			}
		}
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		driver.findElement(By.cssSelector("button[value=\"Next\"]")).click();
		Thread.sleep(1000);
		String Count = driver.findElement(By.cssSelector(".alert.alert-primary>p")).getText();
		String succount[] = Count.split(" ",4);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admintable0")));
		Thread.sleep(2000);

		driver.navigate().to(Lists);
		driver.findElement(By.name("search")).sendKeys("Test List - d8dks8");
		action.sendKeys(Keys.ENTER).perform();
		String listcount = driver.findElement(By.id("at0-cell-total_contact-0")).getText();
		System.out.println("	Count After Adding Multiple Contacts To the list: "+listcount);
		Assert.assertEquals(succount[3], listcount,"Assertion Failed for Imported Contact Count");
	}

	@Test(priority = 8)
	public void CC_016() throws Exception 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		System.out.println();
		System.out.println("CC_016: Check in list remove any user from any list and verify its count updated or not");

		driver.navigate().to(Lists);
		driver.findElement(By.name("search")).sendKeys("Test List - d8dks8");
		String Contacts = driver.findElement(By.id("at0-cell-total_contact-0")).getText();
		Boolean list = Contacts.equalsIgnoreCase("0");
		if(list)
		{
			driver.findElement(By.cssSelector("tr.admintable_row.at-row-0>td:last-of-type>div>a:last-of-type")).click();
			driver.findElement(By.cssSelector("#deleteform>div>button")).click();
			CC_015();
		}
		else
		{
			//Removing contact from list
			String ListCount = driver.findElement(By.id("at0-cell-total_contact-0")).getText();
			System.out.println("	Count in List Before Removing Contact: "+ListCount);
			int ListCountint = Integer.parseInt(ListCount);
			ListCountint = ListCountint - 1;
			ListCount = Integer.toString(ListCountint);

			driver.findElement(By.cssSelector("#at0-cell-action-0>div>a:first-of-type")).click();//list edit button
			driver.findElement(By.id("at0-cell-chk1-0")).click();

			driver.findElement(By.cssSelector("div.edit-check-options>button:nth-of-type(3)")).click();
			driver.findElement(By.cssSelector("#delete_confirmmodal>div>div>div>h1+a")).click();
			driver.navigate().to(Lists);
			String ListCountAfter = driver.findElement(By.id("at0-cell-total_contact-0")).getText();
			System.out.println("	Count in List 2 After Removing Contact: "+ListCountAfter);
			Assert.assertEquals(ListCount, ListCountAfter,"Count is not updating in List after Removing contact from list");

		}


	}	

	@Test(priority = 9)
	public void CC_017() throws Exception 
	{
		Actions action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		System.out.println();
		System.out.println("CC_015: Check after move any contact from one list to other list then verify both list count updated or not");

		driver.navigate().to(Lists);
		driver.findElement(By.name("search")).sendKeys("Test List - d8dks8");
		String Contacts = driver.findElement(By.id("at0-cell-total_contact-0")).getText();
		Boolean list = Contacts.equalsIgnoreCase("0");
		if(list)
		{
			driver.findElement(By.cssSelector("tr.admintable_row.at-row-0>td:last-of-type>div>a:last-of-type")).click();
			driver.findElement(By.cssSelector("#deleteform>div>button")).click();
			CC_015();
		}
		else
		{
			//Moving contact from list
			String before = driver.findElement(By.id("at0-cell-total_contact-0")).getText();
			System.out.println("	Count in List Before Moving Contact: "+before);
			int beforeCountint = Integer.parseInt(before);
			beforeCountint = beforeCountint - 1;
			before = Integer.toString(beforeCountint);

			String OtherListCount = driver.findElement(By.id("at0-cell-total_contact-1")).getText();
			System.out.println("	Count in List 2 Before Moving Contact: "+OtherListCount);
			int OtherListCountint = Integer.parseInt(OtherListCount);
			OtherListCountint = OtherListCountint + 1;
			OtherListCount = Integer.toString(OtherListCountint);

			String OtherListId = driver.findElement(By.cssSelector("#at0-cell-action-1>div>a:last-of-type")).getAttribute("data-listid");
			driver.findElement(By.cssSelector("#at0-cell-action-0>div>a:first-of-type")).click();//list edit button
			driver.findElement(By.id("at0-cell-chk1-0")).click();

			driver.findElement(By.cssSelector("div.edit-check-options>a:first-of-type")).click();
			driver.findElement(By.cssSelector("#contactlist+div")).click();
			WebElement a = driver.findElement(By.cssSelector("#contactlist+div>ul"));
			WebElement b = driver.findElement(By.cssSelector("#contactlist+div>ul>li[data-value=\""+OtherListId+"\"]"));
			action.moveToElement(a).scrollToElement(b).build().perform();
			b.click();
			driver.findElement(By.cssSelector("#contactlist+div+button")).click();
			String after = driver.findElement(By.id("at0-cell-total_contact-0")).getText();
			System.out.println("	Count in List After Moving Contact: "+after);
			String OtherListCountAfter = driver.findElement(By.id("at0-cell-total_contact-1")).getText();
			System.out.println("	Count in List 2 After Moving Contact: "+OtherListCountAfter);
			Assert.assertEquals(after, before,"Existing List Count Not updating After moving contacts to other list");
			Assert.assertEquals(OtherListCount, OtherListCountAfter,"Count is not updating in List 2");
		}
	}

	@Test
	public void CC_018() throws Exception 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Actions action = new Actions(driver);
		
		System.out.println();
		System.out.println("CC_018:	After fill Form with new customer then customer count updated in dashboard and customer");

		//Deleting Duplicate contact
		driver.navigate().to(Customers);
		driver.findElement(By.name("search")).sendKeys("Rohankokare7663+FormTest@gmail.com");
		action.sendKeys(Keys.ENTER).build().perform();

		try 
		{
			driver.findElement(By.id("at0-cell-actions_col-0")).click();
			driver.findElement(By.xpath("//*[@id=\"at0-cell-actions_col-0\"]/div/div/a[6]")).click();
			driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
			Thread.sleep(5000);	
			System.out.println("	Duplicate Contact Deleted");
		}
		catch (Exception NoSuchElementExcepation) 
		{
			System.out.println();
			System.out.println("	Duplicate Contact not found");
		}	

		driver.navigate().to(Dashboard);
		String BeforeDashboard = driver.findElement(By.cssSelector("div.leads>div>h1>span")).getText();
		System.out.println("	Count On Dashboard before Filling Form: "+BeforeDashboard);
		int BeforeDashboardint = Integer.parseInt(BeforeDashboard);
		BeforeDashboardint = BeforeDashboardint + 1;
		BeforeDashboard = Integer.toString(BeforeDashboardint);

		driver.navigate().to(Forms);

		//Deleting duplicate
		driver.findElement(By.id("search_template_list")).sendKeys("New Test Form i5fd9k");
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

		//Creating new Form
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div#spacious-container>div>div>div>div>div>div:nth-of-type(2)>a")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("form_title")).sendKeys("New Test Form i5fd9k");
		Thread.sleep(1000);
		driver.findElement(By.id("formcreation-submit")).click();
		Thread.sleep(2000);
		WebElement x = driver.findElement(By.cssSelector("li[data-type=\"contactinfogroupinfo\"]"));
		WebElement y = driver.findElement(By.cssSelector("ul[data-id=\"fb-cust-builder-1\"]"));
		action.dragAndDrop(x,y).perform();

		try
		{
			driver.findElement(By.cssSelector("li[type=contactinfogroupinfo]")).isDisplayed();
		}
		catch(Exception e) 
		{
			action.dragAndDrop(x,y).perform();
		}
		Thread.sleep(2000);

		driver.findElement(By.id("fb-save-form")).click();
		Thread.sleep(2000);

		driver.navigate().to(Forms);
		String win1 = driver.getWindowHandle();
		driver.findElement(By.cssSelector("#templateListTBody>tr>td:nth-child(6)")).click();

		Set<String> handles = driver.getWindowHandles();

		for(String handle:handles)
		{
			driver.switchTo().window(handle);	
		}

		// Form filling
		driver.findElement(By.cssSelector("div.personal-contactinfo>div:first-of-type>input")).sendKeys("Form");
		driver.findElement(By.cssSelector("div.personal-contactinfo>div:last-of-type>input")).sendKeys("Contact112233");
		driver.findElement(By.cssSelector("div.personal-contactinfophem>div:first-of-type>input")).sendKeys("Rohankokare7663+FormTest@gmail.com");
		driver.findElement(By.cssSelector("div.personal-contactinfophem>div:last-of-type>div>input")).sendKeys("7579793173");
		driver.findElement(By.id("step_1_btn")).click();
		Thread.sleep(5000);
		
		driver.switchTo().window(win1);

		//Listing, increment and delete
		try 
		{
			String name = driver.findElement(By.cssSelector("tr.admintable_row.at-row-0.at-row-pair.cursor-pointer:first-of-type>td:nth-of-type(2)>div>a")).getText();
			Assert.assertEquals(name, "New Test Form i5fd9k","Listing not Found");
		}
		catch (Exception e) 
		{
			driver.findElement(By.xpath("//*[@id=\"formcreation\"]/div/div/div/div[4]/a")).click();
			String name = driver.findElement(By.cssSelector("tr.admintable_row.at-row-0.at-row-pair.cursor-pointer:first-of-type>td:nth-of-type(2)>div>a")).getText();
			Assert.assertEquals(name, "New Test Form i5fd9k","Listing not Found");
		}

		Thread.sleep(3000);
		driver.navigate().refresh();
		String num = driver.findElement(By.xpath("//*[@id=\"at0-cell-total_contact-0\"]/div/a")).getText();
		
		driver.findElement(By.cssSelector("a[data-original-title=\"Card View\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("search_template_list")).sendKeys("New Test Form i5fd9k");
		String num1 = driver.findElement(By.cssSelector("div.fourth-arrow-list-new>a>h1")).getText();

		driver.navigate().to(Dashboard);
		String AfterDashboard = driver.findElement(By.cssSelector("div.leads>div>h1>span")).getText();
		System.out.println("	Count on Dashboard After Filling form with New Customer: "+AfterDashboard);
		
		System.out.println("	Count in Result Column List View: "+num);
		
		System.out.println();
		System.out.println("CC_019: After fill form count updated in Card View");
		System.out.println("	Count in Result Column Card View: "+num1);
		//Assertions
		Assert.assertEquals(num, "1","Result count updated wrong - List View");
		Assert.assertEquals(num1, "1","Result count updated wrong - Card View");
		Assert.assertEquals(BeforeDashboard, AfterDashboard, "Customer Count Not Updated on Dashboard");
	}

}
