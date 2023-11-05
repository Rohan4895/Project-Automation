package Modules;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import AccessLevel.Data;

public class Customers extends Data{
	
	@Test
	public void CountCheck() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
		Actions action = new Actions(driver);
		
		action.moveToElement(driver.findElement(By.cssSelector("div.hidden.sidebar-bg-color"))).perform();
		driver.findElement(By.linkText("Customers")).click();
		driver.findElement(By.id("selectAll")).click();
		Thread.sleep(1000);
		String Checkcount = driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[2]/div[2]/div/div[4]/div/span")).getText();
		String countonbord = driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[2]/div[2]/div/div[2]/span")).getText();
		System.out.println("Count Of Customers Loaded On Page: " +Checkcount);
		System.out.println("Count Of Customers Showing on page:" +countonbord);
	}
	
	@Test
	public void PopupCheck() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.cssSelector("div.hidden.sidebar-bg-color"))).perform();
		driver.findElement(By.linkText("Customers")).click();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
		driver.navigate().to("https://app.deposyt.com/index.php?m=customers");

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		System.out.println();
		System.out.println("Checking Visibility Of Popup's in Customer Module----->");

		// Bulk reply		
		driver.findElement(By.cssSelector("#selectAll+span")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.cust-selected-options>button:first-of-type")).click();
		Thread.sleep(1000);
		System.out.println("	Visibility of Bulk Reply popup = "+driver.findElement(By.cssSelector("div#send-sms-email-popup>div.modal-dialog ")).isDisplayed());
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div#send-sms-email-popup > div > div > div > div > div:nth-of-type(2) > a > i")).click();

		//Assign to user		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.cust-selected-options>button:nth-of-type(2)")).click();
		Thread.sleep(1000);
		System.out.println("	Visibility of Assign To user Popup = "+driver.findElement(By.cssSelector("div#assign-user-popup > div > div> div")).isDisplayed());
		driver.findElement(By.cssSelector("div#assign-user-popup > div > div > div > div > a > i")).click();

		//Tags		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.cust-selected-options>button:nth-of-type(3)")).click();
		Thread.sleep(1000);
		System.out.println("	Visibility of Bulk add/manage tags popup = "+driver.findElement(By.cssSelector("div#add-tagto-user-popup > div > div> div")).isDisplayed());
		driver.findElement(By.xpath("//*[@id=\"add-tagto-user-popup\"]/div/div/div/div[1]/div[2]/a/i")).click();
		
		//Remove tags
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.cust-selected-options>button:nth-of-type(4)")).click();
		Thread.sleep(1000);
		System.out.println("	Visibility of move Customers Popup = "+driver.findElement(By.cssSelector("#remove-tagto-user-popup>div>div>div")).isDisplayed());
		driver.findElement(By.cssSelector("#remove-tagto-user-popup>div>div>div>div>div+div>a")).click();

		//Move customers
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.cust-selected-options>button:nth-of-type(5)")).click();
		Thread.sleep(1000);
		System.out.println("	Visibility of Remove tags popup Popup = "+driver.findElement(By.cssSelector("div#move_pipeline_modal>div>div>div")).isDisplayed());
		driver.findElement(By.cssSelector("#addusertopipelinestage>div>a")).click();
		
		//Create list popup
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.cust-selected-options>button:nth-of-type(6)")).click();
		Thread.sleep(1000);
		System.out.println("	Visibility of Add To New List Popup = "+driver.findElement(By.cssSelector("div#create-list-popup>div>div")).isDisplayed());
		driver.findElement(By.cssSelector("div#create-list-popup > div > div > div > div > div:nth-of-type(2) > a > i")).click();

		//Export selected
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.cust-selected-options>button:nth-of-type(7)")).click();
		Thread.sleep(1000);
		System.out.println("	Visibility Of Export Selected user Popup = "+driver.findElement(By.cssSelector("div#export-option-popup > div > div")).isDisplayed());
		driver.findElement(By.cssSelector("div#export-option-popup > div > div > a")).click();

		//Delete Contact
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.cust-selected-options>button:nth-of-type(8)")).click();
		Thread.sleep(1000);
		System.out.println("	Visibility of Delete Contact Popup = "+driver.findElement(By.cssSelector("div#delete_modal>div.modal-dialog ")).isDisplayed());
		driver.findElement(By.cssSelector("form#ct_deleteContacts_form>div:first-of-type>a")).click();

		//Export Contacts
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div#block0body > div:nth-of-type(2) > div:nth-of-type(2) > div > div:nth-of-type(2) > div:nth-of-type(4) > a:nth-of-type(2)")).click();
		Thread.sleep(1000);
		System.out.println("	Visibility Of Export Contact Popup = "+driver.findElement(By.cssSelector("div#export-popup > div > div")).isDisplayed());
		driver.findElement(By.cssSelector("div#export-popup > div > div > a")).click();

		//Smart view
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div#block0body > div:nth-of-type(2) > div:nth-of-type(2) > div > div:nth-of-type(2) > div:nth-of-type(4) > a")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.flex.smart-view-box.hidden>div>a")).click();
		Thread.sleep(1000);
		System.out.println("	Visibility Of Smart View Popup = "+driver.findElement(By.cssSelector("div#smart-view-popup_new>div>div")).isDisplayed());
		driver.findElement(By.cssSelector("div#smart-view-popup_new > div > div > div > div:nth-of-type(5) > button")).click();
	}
	
	@Parameters ({"uname","pass","url"})
	@Test(priority =-1)
	public void CustomerFilters(String uname, String pass,String url) throws Exception {

		Actions action =new Actions(driver);
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
		System.out.println("Getting Filters Result ----->");
		action.moveToElement(driver.findElement(By.cssSelector("div.hidden.sidebar-bg-color"))).perform();
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/nav/div[5]/div[2]/div[1]/div/div[1]/a/i")).click();
		String[] CRMdata = new String[6]; 

		//opening filter popup
		driver.findElement(By.id("showCustomerTVFilters")).click();
		System.out.println("Visibility of Filter popup = "+driver.findElement(By.cssSelector("div.customer-adv-filters")).isDisplayed());
		Thread.sleep(2000);
		
	//validating unread count
		driver.findElement(By.id("showCustomerTVFilters")).click();
		driver.findElement(By.id("unreadconvocustomersfilter")).click();
		driver.findElement(By.cssSelector("div.flex.gap-2.mt-3>input")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name=selectallcheckbox]+span")));
		driver.findElement((By.cssSelector("input[name=selectallcheckbox]+span"))).click();
		String count0 = driver.findElement(By.cssSelector("input[name=allcustcount]")).getAttribute("value");
		driver.findElement(By.id("showCustomerTVFilters")).click();
		driver.findElement(By.cssSelector("div.flex.justify-between.h-12>div>a:first-of-type")).click();
		CRMdata[0] = count0;
		
		//Validating untouched customers 
		driver.findElement(By.id("showCustomerTVFilters")).click();
		driver.findElement(By.id("untouchedcustomersfilter")).click();
		driver.findElement(By.cssSelector("div.flex.gap-2.mt-3>input")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name=selectallcheckbox]+span")));
		driver.findElement((By.cssSelector("input[name=selectallcheckbox]+span"))).click();
		String count = driver.findElement(By.cssSelector("input[name=allcustcount]")).getAttribute("value");
		driver.findElement(By.id("showCustomerTVFilters")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.flex.justify-between.h-12>div>a:first-of-type")).click();
		CRMdata[1] = count;

		//Validating date of created
		Thread.sleep(2000);
		driver.findElement(By.id("showCustomerTVFilters")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("ctSchedulesStart")).click();
		driver.findElement(By.cssSelector("div.daterangepicker.ltr.show-calendar.opensright[style]>div.drp-buttons>button:last-of-type")).click();
		driver.findElement(By.cssSelector("div.flex.gap-2.mt-3>input")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name=selectallcheckbox]+span")));
		driver.findElement((By.cssSelector("input[name=selectallcheckbox]+span"))).click();
		String count1 = driver.findElement(By.cssSelector("input[name=allcustcount]")).getAttribute("value");
		driver.findElement(By.id("showCustomerTVFilters")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div#campuser > div > div > div > div > a")).click();
		CRMdata[2] = count1;

		//Validating pipeline filter
		Thread.sleep(3000);
		driver.findElement(By.id("showCustomerTVFilters")).click();
		driver.findElement(By.cssSelector("form#ct_filters_form > div > div:nth-of-type(4) > div")).click();
		driver.findElement(By.cssSelector("form#ct_filters_form > div > div:nth-of-type(4) > div > ul > li")).click();
		driver.findElement(By.cssSelector("input#ctStagesIds")).click();
		driver.findElement(By.cssSelector("div#stagesFilterList > label")).click();
		driver.findElement(By.cssSelector("input#ctStagesIds")).click();

		driver.findElement(By.cssSelector("div.flex.gap-2.mt-3>input")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name=selectallcheckbox]+span")));
		driver.findElement((By.cssSelector("input[name=selectallcheckbox]+span"))).click();
		String count2 = driver.findElement(By.cssSelector("input[name=allcustcount]")).getAttribute("value");
		driver.findElement(By.id("showCustomerTVFilters")).click();
		Thread.sleep(1000);
		String mystring3 = driver.findElement(By.cssSelector("div.filter-bottom-box>div>a>span")).getText();
		//String arr2[] = mystring3.split(" ", 2);
		//String firstWord2 = arr2[0]; 
		System.out.println("kjkj"+mystring3);
		Assert.assertEquals(count2, mystring3,"Count mismatched between customers loaded vs customers filtered for Assign user filter");
		driver.findElement(By.cssSelector("div#campuser > div > div > div > div > a")).click();

		//validating user assigned filter
		Thread.sleep(3000);
		driver.findElement(By.id("showCustomerTVFilters")).click();
		driver.findElement(By.cssSelector("input#ctAssignedToIds")).click();
		WebElement a= driver.findElement(By.cssSelector("div#assignedToFilterList > label"));
		String UID = a.getAttribute("data-value");
		a.click();
		driver.findElement(By.cssSelector("input#ctAssignedToIds")).click();
		driver.findElement(By.cssSelector("div.flex.gap-2.mt-3>input")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name=selectallcheckbox]+span")));
		driver.findElement((By.cssSelector("input[name=selectallcheckbox]+span"))).click();
		String count3 = driver.findElement(By.cssSelector("input[name=allcustcount]")).getAttribute("value");
		CRMdata[3] = count3;
		
	//Tersting Notes with remainder filter
		driver.findElement(By.id("showCustomerTVFilters")).click();
		driver.findElement(By.cssSelector("div#campuser > div > div > div > div > a")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("showCustomerTVFilters")).click();
		driver.findElement(By.id("withreminderfilter")).click();
		
		driver.findElement(By.cssSelector("div.flex.gap-2.mt-3>input")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name=selectallcheckbox]+span")));
		driver.findElement((By.cssSelector("input[name=selectallcheckbox]+span"))).click();
		String count4 = driver.findElement(By.cssSelector("input[name=allcustcount]")).getAttribute("value");
		CRMdata[4] = count4;
		
		
//Login to adminer ---------------------------------------------------->			
		driver.get("https://app.deposyt.com/files/adminer.php");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	//Login to database	
		driver.findElement(By.name("auth[server]")).sendKeys("db-mysql-sfo3-80697-do-user-9958608-0.b.db.ondigitalocean.com:25060");
		driver.findElement(By.id("username")).sendKeys("adminroot");
		driver.findElement(By.name("auth[password]")).sendKeys("AVNS_JAtYVS4R_ZeE_5Gsxpn");
		driver.findElement(By.cssSelector("input[value='Login']")).click();

	//database selection
		driver.findElement(By.linkText("appcentzcrmcom")).click();
		driver.findElement(By.linkText("SQL command")).click();
		action.sendKeys("select id_company\r\n"
				+ "from sm_users\r\n"
				+ "where login= '"+uname+"'\r\n"
				+ "and text_password = '"+pass+"'")
		.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
		String cid = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();
		
	//Testing count for unread customers
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).clear();
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).sendKeys("SELECT count(distinct c.id)\r\n"
				+ "FROM customers as c \r\n"
				+ "LEFT JOIN messagelog as mlq \r\n"
				+ "ON mlq.id_customer=c.id \r\n"
				+ "WHERE (c.unread != 0 AND c.id_company="+cid+" AND c.archived=0)");
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
		String DBunread = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();
				
	//Getting count of todays created customers
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).clear();
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).sendKeys("SELECT count(distinct c.id)\r\n"
				+ "FROM customers as c\r\n"
				+ "LEFT JOIN messagelog as mlg\r\n"
				+ "ON mlg.id_customer = c.id\r\n"
				+ "WHERE (c.timeadded > UNIX_TIMESTAMP(Current_date()) AND c.is_enabled=1 AND c.deleted=0 AND c.id_company="+cid+")");
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
		String CreatedToday = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();

	//untouched customers
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).clear();
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).sendKeys("select count(id)\r\n"
				+ "from customers as c\r\n"
				+ "where (id_company = "+cid+" and is_manually_contacted=0) AND c.is_enabled=1 AND c.deleted=0");
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
		String untouched = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();


	//assign to user
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).clear();
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).sendKeys("select count(id)\r\n"
				+ "from customers as c\r\n"
				+ "where (id_company = "+cid+" and addedby="+UID+" AND c.is_enabled=1 AND c.deleted=0)");
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
		String Assign = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();
		
	//Testing Notes with remainder count
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).clear();
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).sendKeys("Select count(distinct c.id)\r\n"
				+ "from customers c\r\n"
				+ "left join customer_notes cn\r\n"
				+ "on c.id = cn.id_customer\r\n"
				+ "left join messagelog ml\r\n"
				+ "on c.id = ml.id_customer\r\n"
				+ "where(cn.timescheduled != 0 and c.is_enabled=1 and c.deleted=0 and c.id_company = "+cid+")");
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
		String notes = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();
			
		//outputs
		System.out.println("	Unread Filter");
		System.out.println("		Filter Count From CRM: "+CRMdata[0]);
		System.out.println("		Filter Count From DB : "+DBunread);
		System.out.println();
		
		System.out.println("	Create date Filter(Today's Created)");
		System.out.println("		Filter Count From CRM: "+CRMdata[2]);
		System.out.println("		Filter Count From DB : "+CreatedToday);
		System.out.println();
		
		System.out.println("	Untouched Count Filter");
		System.out.println("		Filter Count From CRM: "+CRMdata[1]);
		System.out.println("		Filter Count From DB : "+untouched);
		System.out.println();
		
		System.out.println("	Assign To User Filter");
		System.out.println("		Filter Count From CRM: "+CRMdata[3]);
		System.out.println("		Filter Count From DB : "+Assign);
		System.out.println();
		
		System.out.println("	Notes With Remainder Filter");
		System.out.println("		Filter Count From CRM: "+CRMdata[4]);
		System.out.println("		Filter Count From DB : "+notes);
		System.out.println();
		
		//Assertions
		Assert.assertEquals(CRMdata[0],DBunread,"Unread count mismatch");
		Assert.assertEquals(CRMdata[1],untouched,"Untouched count mismatch");
		Assert.assertEquals(CRMdata[2],CreatedToday,"Created today count mismatch");
		Assert.assertEquals(CRMdata[3],Assign ,"Assign user count mismatch");
		//Assert.assertEquals(CRMdata[4],notes ,"Notes with remainder count mismatch");
		
		driver.navigate().to(url);
	}

	@Test
	public void AddCustomer() throws InterruptedException {
		Actions action =new Actions(driver);
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(3));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
		System.out.println();
		System.out.println("<----- Adding new user ------>");
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/nav/div[5]/div[2]/div[1]/div/div[1]/a/i")).click();
		
		driver.findElement(By.name("search")).sendKeys("7077190993");
		action.sendKeys(Keys.ENTER).build().perform();
		try {
			driver.findElement(By.id("at0-cell-actions_col-0")).click();
			driver.findElement(By.xpath("//*[@id=\"at0-cell-actions_col-0\"]/div/div/a[6]")).click();
			driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
		} catch (Exception NoSuchElementExcepation) 
		{
			driver.navigate().refresh();
		}
		try {
		String limitmsg= driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[1]/h1")).getText();
		System.out.println(limitmsg);
		}catch(Exception NoSuchElementException) {
			driver.close();
			System.out.println("Customer Limit is available");
		}
			
		Thread.sleep(5000);
		
//Adding customer-->
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/a[1]")).click();
		driver.findElement(By.name("first_name")).sendKeys("Testname");
		action.sendKeys(Keys.TAB);
		action.sendKeys(Keys.TAB);
		action.sendKeys(Keys.TAB);
		action.sendKeys("7077190993");
		action.build().perform();	
		driver.findElement(By.name("email")).sendKeys("rohankokare7663@gmail.com");
		driver.findElement(By.xpath("//*[@id=\"add_contact_form\"]/form[1]/div[2]/input[1]")).click();
		try {
		driver.findElement(By.id("section.alertify-logs")).isDisplayed();}
		catch(Exception NoSuchElementExcepation) {
			Thread.sleep(2000);
		}
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section[2]/article")));
			String cont =driver.findElement(By.xpath("/html/body/section[2]/article")).getText();
			System.out.println(cont);
		}catch(Exception TimeOutException) {
			System.out.println("Exception hit for user not Added");
		}

//Change in pipeline-->
		WebElement piplinebox = driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[2]/div[1]/div/div[1]/div[2]/article/div[2]/div[2]/div/div[1]/div[1]/div[1]/div/div[2]/div/div[2]/div"));
		String currentps = piplinebox.getText();
		System.out.println("Current pipeline stage:-" +currentps);
		piplinebox.click();
		action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"changedContactStage\"]/div")));
        driver.findElement(By.xpath("//*[@id=\"pp-change-customer-stage\"]/div/button[2]")).click();
		currentps=piplinebox.getText();
		System.out.println("Changed pipeline stage:-" +currentps);
	}
	
	@Test(priority =1)
	public void subscribe() throws InterruptedException 
	{
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action =new Actions(driver);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/nav/div[3]")).click();
		String b= driver.getTitle();
		Assert.assertEquals(b, "conversation :: deposyt.com", "Message tab not open");
		
		driver.findElement(By.xpath("//*[@id=\"search-input\"]")).sendKeys("Testname");
		action.sendKeys(Keys.ENTER).build().perform();
		driver.findElement(By.xpath("//*[@id=\"dashboard-conversation-text\"]"))
		.sendKeys("Hi this is test message for daily testing");
		driver.findElement(By.xpath("//*[@id=\"msgbox_messages\"]/div[3]/div[2]")).click();
		
//Unsubscribe from SMS----->			
		driver.findElement(By.id("dropdownMenu3")).click();
		jse.executeScript("document.querySelector('div#spacious-container > div:nth-of-type(4) > div > div > div > div:nth-of-type(3) > div').scrollTop=600");
		driver.findElement(By.xpath("//*[@id=\"newdrop\"]/a[11]")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("form#formdeleteactionmodaldiv > div > button")).click();

//Unsubscribe from mail--->			
		driver.findElement(By.id("dropdownMenu3")).click();
		jse.executeScript("document.querySelector('div#spacious-container > div:nth-of-type(4) > div > div > div > div:nth-of-type(3) > div').scrollTop=600");
		driver.findElement(By.xpath("//*[@id=\"newdrop\"]/a[10]")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("form#formdeleteactionmodaldiv > div > button")).click();
		String mailsubstat=  driver.findElement(By.xpath("//*[@id=\"spacious-container\"]/div[4]/div/div/div/div[3]/div/center/div/h1[3]")).getText();
		System.out.println("Mail subscription status: "+mailsubstat);
		String SMSsubstat = driver.findElement(By.xpath("//*[@id=\"spacious-container\"]/div[4]/div/div/div/div[3]/div/center/div/h1[4]")).getText();
		System.out.println("SMS subscription status: "+SMSsubstat);
//validate by checking absence of toster after clicking on send button
		driver.findElement(By.xpath("//*[@id=\"dashboard-conversation-text\"]"))
		.sendKeys("Hi this is test message for daily testing");
	
		try{
		Thread.sleep(3000);
		driver.findElement(By.id("convoSendSmsBtn")).click();//<---send
		}catch(Exception e) {
			System.out.println("Message not sent"+e);
		}
		driver.findElement(By.id("calling_email")).click();
		String togbtn =driver.findElement(By.xpath("//*[@id=\"spacious-container\"]/div[4]/div/div/div/div[1]/div[1]/label/div[1]")).getText();
		Assert.assertEquals(togbtn, "Only Show Unread");
		driver.findElement(By.id("subject_email"))
		.sendKeys("hi this is test mail ");
		action.sendKeys(Keys.TAB)
		.sendKeys("this is test mail")
		.build().perform();
		driver.findElement(By.xpath("//*[@id=\"emailbox_messages\"]/form/div[4]/div[1]/div/label/div[2]/div[2]")).click();
		try {
			Thread.sleep(1000);
			driver.findElement(By.id("convoSendEmailBtn")).click();
		}catch(Exception e){
			System.out.println("Mail not sent"+e);
		}
	}
	
	@Test(priority =-1)
	public void custvalidations() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
		
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/nav/div[5]/div[2]/div[1]/div/div[1]/a/i")).click();
		String custurl=driver.getCurrentUrl();
		JavascriptExecutor jse =  (JavascriptExecutor)driver; 
		Assert.assertEquals(custurl, "https://app.deposyt.com/index.php?m=customers");
		
//<-- Checking customer loaded vs number on dashboard
		driver.findElement(By.xpath("//*[@id=\"admintable_header0\"]/th[1]/label/span")).click();
		String selcust= driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[2]/div[2]/div/div[4]/div/span")).getText();
		String dispcust =driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[2]/div[2]/div/div[2]/span")).getText();
		System.out.println("Loaded customers: "+selcust);
		System.out.println("Total numbers: " +dispcust);
		
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/a[1]")).click();	
		
//Dropdowns
		driver.findElement(By.xpath("//*[@id=\"pipeline_stage_form\"]/div/div[1]/div")).click();
		try {
		driver.findElement(By.xpath("//*[@id=\"pipeline_stage_form\"]/div/div[1]/div/ul")).isDisplayed();
		}catch(Exception NoSuchElementException) {
			System.out.println("Pipline dropdown error");
		}
		driver.findElement(By.xpath("//*[@id=\"pipeline_stage_form\"]/div/div[2]/div")).click();
		try {
			driver.findElement(By.xpath("//*[@id=\"pipeline_stage_form\"]/div/div[2]/div/ul")).isDisplayed();
		}catch(Exception NoSuchElementException) {
			System.out.println("Pipeline stage dropdown not opening");
		}
		jse.executeScript("window.scroll(0,700)");
//list dropdown
		driver.findElement(By.xpath("//*[@id=\"admintablerow-list\"]/td[2]/div")).click();
		try {
			driver.findElement(By.xpath("//*[@id=\"admintablerow-list\"]/td[2]/div/ul")).isDisplayed();
		}catch(Exception NoSuchElementException) {
			System.out.println("Add to list Dropdown not open");
		}
		driver.findElement(By.xpath("//*[@id=\"admintablerow-list\"]/td[2]/div")).click();
		//assign user
		driver.findElement(By.xpath("//*[@id=\"admintablerow-assigned_user\"]/td[2]/div")).click();
		try {
			driver.findElement(By.xpath("//*[@id=\"admintablerow-assigned_user\"]/td[2]/div/ul")).isDisplayed();
		}catch(Exception NoSuchElementException) {
			System.out.println("Assigned user dropdown not open");
		}
		driver.findElement(By.xpath("//*[@id=\"admintablerow-assigned_user\"]/td[2]/div")).click();
		
		driver.findElement(By.xpath("//*[@id=\"admintablerow-id_location\"]/td[2]/div")).click();
		try {
			driver.findElement(By.xpath("//*[@id=\"admintablerow-id_location\"]/td[2]/div/ul")).isDisplayed();
			System.out.println("All Dropdowns are working fine");
		}catch(Exception NoSuchElementException) {
			System.out.println("Location dropdown not open");
		}
//All fields empty
		driver.findElement(By.xpath("//*[@id=\"add_contact_form\"]/form[1]/div[2]/input[1]")).click();
		String attcls = driver.findElement(By.xpath("//*[@id=\"admintablerow-first_name\"]/td[1]")).getAttribute("class");
		Assert.assertEquals(attcls, "moveitup");
		System.out.println("Tested with all required fields are empty");
		
//Number field empty
		driver.findElement(By.name("first_name")).sendKeys("Test name123");
		driver.findElement(By.xpath("//*[@id=\"add_contact_form\"]/form[1]/div[2]/input[1]")).click();
		String attnum = driver.findElement(By.xpath("//*[@id=\"admintablerow-cellphone\"]/td[1]")).getAttribute("class");
		Assert.assertEquals(attnum, "moveitup");
		System.out.println("Tested With number field is empty");
		
		driver.findElement(By.name("cellphone")).sendKeys("123456789");
		driver.findElement(By.xpath("//*[@id=\"add_contact_form\"]/form[1]/div[2]/input[1]")).click();
		String err = driver.findElement(By.xpath("//*[@id=\"spacious-container\"]/div[1]")).getText();
		System.out.println("Less than 11 number: "+err);
		
		driver.findElement(By.name("cellphone")).sendKeys("0");
		driver.findElement(By.xpath("//*[@id=\"add_contact_form\"]/form[1]/div[2]/input[1]")).click();
		String errms = driver.findElement(By.xpath("//*[@id=\"spacious-container\"]/div[1]")).getText();
		System.out.println("Duplicate: "+errms);
		
		driver.findElement(By.name("cellphone")).sendKeys("0123");
		driver.findElement(By.xpath("//*[@id=\"add_contact_form\"]/form[1]/div[2]/input[1]")).click();
		String errmsg = driver.findElement(By.xpath("//*[@id=\"spacious-container\"]/div[1]")).getText();
		System.out.println("More than 11 number: "+errmsg);
		
	}
	
	@Test(priority = 2)
	public void CMP() 
	{
		
		Actions action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;

		driver.navigate().to(Customers);
		
		driver.findElement(By.name("search")).sendKeys("Weaired testname");
		action.sendKeys(Keys.ENTER).build().perform();
	
		CMP();
	}
	
	
	@Test(priority = 3)
	public void deletecontact() {
		Actions action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;

		WebElement e = driver.findElement(By.cssSelector("div.hidden.sidebar-bg-color"));
		action.moveToElement(e).perform();
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/nav/div[5]/div[2]/div[1]/div/div[1]/a/i")).click();

		driver.findElement(By.name("search")).sendKeys("Weaired testname");
		action.sendKeys(Keys.ENTER).build().perform();
		System.out.println();
		System.out.println("Deleting Contact ----->");
		driver.findElement(By.name("search")).sendKeys("contact112233");
		action.sendKeys(Keys.ENTER).build().perform();
		try {
			driver.findElement(By.id("at0-cell-actions_col-0")).click();
			driver.findElement(By.xpath("//*[@id=\"at0-cell-actions_col-0\"]/div/div/a[6]")).click();
			driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
			Thread.sleep(5000);	
			System.out.println("	Contact Deleted");
		} catch (Exception NoSuchElementExcepation) 
		{
			System.out.println();
			System.out.println("	Contact not found");
		}	
	}
	
}
