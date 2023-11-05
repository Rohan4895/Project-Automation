package Modules;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import AccessLevel.Data;

public class Pipeline extends Data{
	@Test(priority =1)
	public void createnew() throws InterruptedException {
		
		JavascriptExecutor jse= (JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);
		
		WebElement e = driver.findElement(By.cssSelector("div.hidden.sidebar-bg-color"));
		action.moveToElement(e).perform();
		//driver.findElement(By.linkText("Pipelines")).click();
		//Assert.assertEquals(driver.getCurrentUrl(), "https://app.deposyt.com/index.php?m=pipelines", "Assertion failed for Pipeline URL");
		
		String name = driver.findElement(By.cssSelector("div#kanban402 > div > div:nth-of-type(2)")).getText();
		Assert.assertEquals(name, "Test Live");
		
//Checking for duplicate

		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/div[1]")).click();
		jse.executeScript("document.querySelector('div#block0body > div > div > div > div > div > ul').scrollTop=300");
		String as= driver.findElement(By.cssSelector("div#block0body > div > div > div > div > div > ul > li:last-of-type")).getText();
		Boolean test =as.equalsIgnoreCase("ZzzzzNew Test Pipeline");
		if(test) {
			System.out.println("duplicate found");
			deletepipeline();
		}
		
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[2]/a[1]")).click();
		driver.findElement(By.name("pipeline_title")).sendKeys("ZzzzzNew Test Pipeline");
		action.sendKeys(Keys.TAB).sendKeys("Stage 1").build().perform();
		driver.findElement(By.xpath("//*[@id=\"pp-create-stage-form\"]/div[3]/button")).click();//<<--Add pipeline stage
		driver.findElement(By.xpath("//*[@id=\"dropdownPallete\"]/i")).click();
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/main/div/div[1]/div[6]/div/div/div[2]/form/div[2]/div/div/div/div[1]/a[5]")).click();
		System.out.println("Color for Pipeline stage is changed");
		
		driver.findElement(By.xpath("//*[@id=\"pp-create-stage-form\"]/div[3]/button")).click();//<<--Add pipeline stage
		driver.findElement(By.xpath("//*[@id=\"sortable\"]/div[3]/a/i")).click();
		System.out.println("Pipeline stage added and deleted");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"pp-create-stage-form\"]/div[4]/button")).click();
		try {
			String hh = driver.findElement(By.id("alertify-logs")).getText();
			System.out.println("Pipeline created with Empty stage name: " +hh);
		}catch(Exception NoSuchElementException) {
			System.out.println("Verified pipeline not creating with empty stage name");
		}
		action.sendKeys("Stage 2").perform();
		driver.findElement(By.xpath("//*[@id=\"pp-create-stage-form\"]/div[4]/button")).click();
		String alert= driver.findElement(By.id("alertify-logs")).getText();
		System.out.println(alert);
		
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[3]/div/div[2]/div/div/div[2]/div/div[3]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("new-status-title")).sendKeys("Stage 3");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"pp-create-status-form\"]/div[2]/button")).click();
		String stgadd = driver.findElement(By.id("alertify-logs")).getText();
		System.out.println("Addind stage from dashboard: "+stgadd);
		
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/a[1]")).click();
		jse.executeScript("window.scroll(0, 200)");
		driver.findElement(By.xpath("//*[@id=\"sortable\"]/div[3]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"pp-direct-delete-pipeline-stage-form\"]/button")).click();
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[2]/div/div[1]/a")).click();
	
	}
	
	@Test (priority = 2)
	public void addtopipeline() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		JavascriptExecutor jse= (JavascriptExecutor)driver;
		Actions action = new Actions(driver);
		
		WebElement sidebar = driver.findElement(By.cssSelector("div.hidden.sidebar-bg-color"));
		action.moveToElement(sidebar).perform();
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/nav/div[5]/div[2]/div[2]")).click();
		
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/div[1]")).click();
		jse.executeScript("document.querySelector('div#block0body > div > div > div > div > div > ul').scrollTop=300");
		String as= driver.findElement(By.cssSelector("div#block0body > div > div > div > div > div > ul > li:last-of-type")).getText();
		Boolean test =as.equalsIgnoreCase("ZzzzzNew Test Pipeline");
		if(test) {
			System.out.println("Test pipeline found");
		}else {
			System.out.println("Creating new test pipeline");
			createnew();
		}
		driver.findElement(By.cssSelector("div#block0body > div > div > div > div > div > ul > li:last-of-type")).click();
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/div[2]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/div[2]/ul/li[1]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("_createNewContact")).click();
		driver.findElement(By.name("first_name")).sendKeys("Testname");
		driver.findElement(By.id("cellphone")).sendKeys("7077190993");
		
//Checking customer limit		
		try {
		driver.findElement(By.xpath("//*[@id=\"add_contact_form\"]/form[1]/div[2]/input[1]")).click();
		}catch(Exception NoSuchElement) {
			System.out.println("Please cheack your customers limit");
			Assert.assertEquals(false, true,"Failed due to customer limit");
		}
		
//checking for duplicate contact
		try {
			if(driver.findElement(By.xpath("//*[@id=\"spacious-container\"]/div[1]")).isDisplayed())
			{
				driver.findElement(By.id("cellphone")).sendKeys("9987654321");
				driver.findElement(By.xpath("//*[@id=\"add_contact_form\"]/form[1]/div[2]/input[1]")).click();
				System.out.println("****Please Delete Duplicate Contacts****");
			}
		}
		catch(Exception e) {
			Thread.sleep(1000);
			}
		
		String contadd =driver.findElement(By.id("alertify-logs")).getText();
		System.out.println("Adding customer to Satge 1: " +contadd);
		/*
// Checking Add value functionality
		driver.findElement(By.cssSelector("div#add-customer-value-modal > div > div > div:nth-of-type(2) > div")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("value_add")).sendKeys("10");
		driver.findElement(By.xpath("//*[@id=\"add-customer-value-modal\"]/div/div/div[2]/div[2]/button[2]")).click();
		*/
//Changinge pipline stage for the customer 
	//String before = driver.findElement(By.cssSelector("div.headerstep_title>span:first-of-type")).getText();
		WebElement customer = driver.findElement(By.cssSelector("div.leadcontainer>div:first-of-type"));
		WebElement stage2= driver.findElement(By.cssSelector("div.dragable-container>div.wrapper>div:nth-of-type(2)"));
		action.clickAndHold(customer).moveToElement(stage2).release().build().perform();
		driver.navigate().refresh();
		String after =driver.findElement(By.cssSelector("div.headerstep_title>span:first-of-type")).getText();
			System.out.println("Contact moved succesfully from stage 1: "+after);
	}
	
	@Parameters({"uname","pass","url"})
	@Test(priority =3)
	public void pipelineFilters(String uname, String pass, String url) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		Actions action = new Actions(driver);
		
		WebElement e = driver.findElement(By.cssSelector("div.hidden.sidebar-bg-color"));
		action.moveToElement(e).perform();
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/nav/div[5]/div[2]/div[2]")).click();
		
	//Getting pipeline id
		String piplineid = driver.findElement(By.cssSelector("li.option.selected.text-base")).getAttribute("data-value");
		
	//Unread filter count
	    driver.findElement(By.id("_btnOpenFilter")).click();
		System.out.println("Visibility of Piplines filter: "+driver.findElement(By.id("side-filter")).isDisplayed());
		driver.findElement(By.cssSelector("form#searchFilter > div:nth-of-type(2) > div:nth-of-type(2) > label > div:nth-of-type(2) > div")).click();
		driver.findElement(By.cssSelector("button[type=submit]")).click();
		List <WebElement>UnreadDots = driver.findElements(By.cssSelector("i.fa-solid.fa-circle.unread-dot"));
		int dotscount = UnreadDots.size();
		String dots = Integer.toString(dotscount);
		Thread.sleep(1000);
		driver.findElement(By.id("_btnOpenFilter")).click();	
		driver.findElement(By.cssSelector("div#sidenav_bottom_btn > a")).click();
		
	//Pipline Stages
		driver.findElement(By.id("_btnOpenFilter")).click();
		List <WebElement> stagecount = driver.findElements(By.cssSelector("ul.list.filterstageselected>li"));
		int intcount = stagecount.size();
		Thread.sleep(1000);
		driver.findElement(By.id("filter-close")).click();
		String[] custcount = new String[intcount-1]; 
		String[] stageid1 = new String[intcount-1];
		
		for(int i=2;i<=intcount;i++) {
		driver.findElement(By.id("_btnOpenFilter")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("span.current.pipestagespan")).click();
		WebElement a = driver.findElement(By.cssSelector("ul.list.filterstageselected>li:nth-of-type("+i+")"));
		String stageid = a.getAttribute("data-value");
		stageid1[i-2]= stageid;
		a.click();
		driver.findElement(By.cssSelector("button[type=submit]")).click();
		int temp =i-1;
		String count = driver.findElement(By.cssSelector("div.headerstep.received:nth-of-type("+temp+")>div>span.total-count")).getText();
		custcount[i-2] = count;
		}
		
		
		//Date created filter 
		driver.findElement(By.id("_btnOpenFilter")).click();
		driver.findElement(By.cssSelector("div#sidenav_bottom_btn > a")).click();//Clear filter button
		
		driver.findElement(By.id("_btnOpenFilter")).click();
		jse.executeScript("document.querySelector('div#side-filter').scrollTop = 300");
		Thread.sleep(1000);
		driver.findElement(By.id("usercreateddaterange")).click();
		driver.findElement(By.cssSelector("button.applyBtn.btn.btn-sm.btn-primary")).click();
		driver.findElement(By.cssSelector("button[type=submit]")).click();
		List<WebElement> stages = driver.findElements(By.cssSelector("div.headerstep.received>div.headerstep_title>span.total-count.total_count_head"));
		
		int a,b=0;
		for(int i=1;i<=stages.size();i++) {
		String custcount1 = driver.findElement(By.cssSelector("div.headerstep.received:nth-of-type("+i+")>div.headerstep_title>span.total-count.total_count_head")).getText();
		a = Integer.parseInt(custcount1);
		b = b + a;
		}
		String totalcount = Integer.toString(b);
		
		//Assignee filter 
		driver.findElement(By.id("_btnOpenFilter")).click();
		driver.findElement(By.cssSelector("div#sidenav_bottom_btn > a")).click();//Clear filter button
		
		driver.findElement(By.id("_btnOpenFilter")).click();
		jse.executeScript("document.querySelector('div#side-filter').scrollTop = 500");
		driver.findElement(By.cssSelector("form#searchFilter > div:nth-of-type(8) > div:nth-of-type(2)")).click();
		List<WebElement> users = driver.findElements(By.cssSelector("form#searchFilter > div:nth-of-type(8) > div:nth-of-type(2)>ul>li"));
		driver.findElement(By.id("filter-close")).click();
		String[] stagecustcount = new String[users.size()-1]; 
		String[] UID1 = new String[users.size()];
		for(int i=2;i<=users.size();i++)
		{
			int a1,b1=0;
			driver.findElement(By.id("_btnOpenFilter")).click();
			Thread.sleep(1000);
			jse.executeScript("document.querySelector('div#side-filter').scrollTop = 600");
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("form#searchFilter > div:nth-of-type(8) > div:nth-of-type(2)")).click();
			WebElement imp  = driver.findElement(By.cssSelector("form#searchFilter > div:nth-of-type(8) > div:nth-of-type(2)>ul>li:nth-of-type("+i+")"));
			if(i>4) {
			jse.executeScript("document.querySelector('form#searchFilter > div:nth-of-type(8) > div:nth-of-type(2)>ul').scrollTop=100");
			}
			UID1[i-2] = imp.getAttribute("data-value");
			imp.click();
			driver.findElement(By.cssSelector("button[type=submit]")).click();
			List<WebElement> stages1 = driver.findElements(By.cssSelector("div.headerstep.received>div.headerstep_title>span.total-count.total_count_head"));
			
			for(int j=1; j<=stages1.size(); j++) 
			{
				String custcount2 = driver.findElement(By.cssSelector("div.headerstep.received:nth-of-type("+j+")>div.headerstep_title>span.total-count.total_count_head")).getText();
				a1 = Integer.parseInt(custcount2);
				b1 = b1 + a1;
			}
			String c = Integer.toString(b1);
			stagecustcount[i-2] = c;
		}
		
		//Tags filter
		driver.findElement(By.id("_btnOpenFilter")).click();
		driver.findElement(By.cssSelector("div#sidenav_bottom_btn > a")).click();//Clear filter button
		
		driver.findElement(By.id("_btnOpenFilter")).click();
		jse.executeScript("document.querySelector('div#side-filter').scrollTop = 300");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input[type=\"select-multiple\"]")).click();
		WebElement tag = driver.findElement(By.cssSelector("div.selectize-dropdown-content>div:nth-of-type(2)"));
		String tagid = tag.getAttribute("data-value");
		tag.click();
		driver.findElement(By.cssSelector("button[type=submit]")).click();
		List<WebElement> stages2 = driver.findElements(By.cssSelector("div.headerstep.received>div.headerstep_title>span.total-count.total_count_head"));
		int a3,b3=0;
		for(int i=1; i<=stages2.size(); i++) 
		{
			String custcount3 = driver.findElement(By.cssSelector("div.headerstep.received:nth-of-type("+i+")>div.headerstep_title>span.total-count.total_count_head")).getText();
			a3 = Integer.parseInt(custcount3);
			b3 = b3 + a3;
		}
		String c = Integer.toString(b3);
		
		//List filter
		driver.findElement(By.id("_btnOpenFilter")).click();
		driver.findElement(By.cssSelector("div#sidenav_bottom_btn > a")).click();//Clear filter button
		
		driver.findElement(By.id("_btnOpenFilter")).click();
		jse.executeScript("document.querySelector('div#side-filter').scrollTop = 300");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("form#searchFilter>div:nth-of-type(7)>div:nth-of-type(2)")).click();
		WebElement list = driver.findElement(By.cssSelector("form#searchFilter>div:nth-of-type(7)>div:nth-of-type(2)>ul>li:nth-of-type(2)"));
		String listid = list.getAttribute("data-value");
		list.click();
		driver.findElement(By.cssSelector("button[type=submit]")).click();
		List<WebElement> stages3 = driver.findElements(By.cssSelector("div.headerstep.received>div.headerstep_title>span.total-count.total_count_head"));
		int a4,b4=0;
		for(int i=1; i<=stages3.size(); i++) 
		{
			String custcount4 = driver.findElement(By.cssSelector("div.headerstep.received:nth-of-type("+i+")>div.headerstep_title>span.total-count.total_count_head")).getText();
			a4 = Integer.parseInt(custcount4);
			b4 = b4 + a4;
		}
		String listCRM = Integer.toString(b4);
		
//Adminer Login ------------------------->
		driver.get("https://app.deposyt.com/files/adminer.php");
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
		
//Getting unread count from Database
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).clear();
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).sendKeys("SELECT count(distinct c.id)\r\n"
				+ "FROM customers as c\r\n"
				+ "left join lead_pipeline_relation as lpr\r\n"
				+ "ON c.id = lpr.contact_id\r\n"
				+ "WHERE (c.unread !=0 And lpr.id_company = "+cid+" AND lpr.pipeline_id = "+piplineid+")");
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
		String unread = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();
		
//Getting stage wise count from data base
		String[] DBcount = new String[intcount-1];
		for(int i=0;i<=intcount-2;i++){
			driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).clear();
			driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).sendKeys("SELECT count(distinct c.id)\r\n"
				+ "FROM customers as c\r\n"
				+ "left join lead_pipeline_relation as lpr\r\n"
				+ "ON c.id = lpr.contact_id\r\n"
				+ "WHERE (lpr.id_company = "+cid+" AND lpr.pipeline_id = "+piplineid+" AND c.is_enabled=1 AND c.deleted=0 AND lpr.status_id = "+stageid1[i]+")");
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
		String stagecust = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();
		DBcount[i]=stagecust;
		}
		
		//Getting DB count of Today's customers
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).clear();
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).sendKeys("SELECT count(distinct c.id)\r\n"
				+ "FROM customers as c \r\n"
				+ "LEFT JOIN lead_pipeline_relation as lpr\r\n"
				+ "ON lpr.contact_id=c.id\r\n"
				+ "WHERE (c.timeadded > UNIX_TIMESTAMP(Current_date()) AND c.is_enabled=1 And pipeline_id = "+piplineid+" AND c.deleted=0 AND c.id_company="+cid+")");
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
		String DBTodayCount = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();
		
		//Assigned user contact count from DB
		String[] DBAssigncount = new String[users.size()-1]; 
		for(int i=0;i<=users.size()-2;i++)
		{
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).clear();
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).sendKeys("select count(distinct c.id)\r\n"
				+ "from customers as c\r\n"
				+ "left join lead_pipeline_relation lpr\r\n"
				+ "on c.id = lpr.contact_id\r\n"
				+ "where (lpr.id_company = "+cid+" and c.deleted = 0 AND c.is_enabled=1 \r\n"
				+ "And pipeline_id = "+piplineid+" AND c.deleted=0 AND c.addedby="+UID1[i]+")");
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
		String DBassignedCount = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();
		DBAssigncount[i] = DBassignedCount;
		}
		
	//Tags filter Data
	 driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).clear();
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).sendKeys("Select Count(Distinct c.id)\r\n"
				+ "from customers c\r\n"
				+ "left join taxonomy t\r\n"
				+ "on c.id = t.object_id\r\n"
				+ "left join lead_pipeline_relation lpr\r\n"
				+ "on c.id = lpr.contact_id\r\n"
				+ "where (lpr.id_company = "+cid+" AND lpr.pipeline_id = "+piplineid+" \r\n"
				+ "AND c.is_enabled=1 AND object_name = \"customertotags\" and c.deleted=0 and t.rel_id="+tagid+")");
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
		String DBtagedcustCount = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();

	//List Filter Data
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).clear();
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).sendKeys("Select count(distinct c.id)\r\n"
				+ "From customers  c\r\n"
				+ "left join taxonomy t\r\n"
				+ "on c.id = t.object_id\r\n"
				+ "left join lead_pipeline_relation lpr\r\n"
				+ "on c.id = lpr.contact_id\r\n"
				+ "where (lpr.id_company = "+cid+" AND lpr.pipeline_id = "+piplineid+"\r\n"
				+ "AND c.is_enabled=1 AND object_name = \"customertolist\" and c.deleted=0 and t.rel_id = "+listid+")");
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
		String DBlistcustCount = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();
		
	//Output: 
		System.out.println("Unread Filter: ");
		System.out.println("Filter Count From CRM: "+dots);
		System.out.println("Filter Count From Database: "+unread);
		System.out.println();
		
		System.out.println("Pipeline Stagewise Filtered contacts(Per Stage Count)");
		for(String tem1p:custcount)
		{
			System.out.println("Filter Count From CRM: "+tem1p);
		}
		for(String temp1:DBcount) {
			System.out.println("From Database: "+temp1);
		}
		System.out.println();
		
		System.out.println("Created date filter :Todays created");
		System.out.println("Filter Count From CRM: "+totalcount);
		System.out.println("Filter Count From Database: "+DBTodayCount);
		System.out.println();
		
		System.out.println("Assigned User Filter(Per User Count): ");
		for(String d:stagecustcount)
		{
			System.out.println("Filter Count From CRM: "+d);
		}
		for(String as:DBAssigncount) {
			System.out.println("Filter Count From Database: "+as);
		}
		
		System.out.println("Tags filter");
		System.out.println("Filter Count Form CRM: "+c);
		System.out.println("Filter Count From Database: "+DBtagedcustCount);
		
		System.out.println("Filter Count From CRM: "+listCRM);	
		System.out.println("Filter Count From Database: "+DBlistcustCount);
		
	//Assrstions ---------------->
		Assert.assertEquals(dots, unread,"Unread count Mismatch");
		Assert.assertEquals(custcount, DBcount, "Stage Customer count mismatch");
		Assert.assertEquals(totalcount, DBTodayCount,"Today's added customer count mismatch");
		Assert.assertEquals(stagecustcount, DBAssigncount,"Assigned user Filter count mismatch");
		Assert.assertEquals(c, DBtagedcustCount,"Tags Filter count mismatch");
		Assert.assertEquals(listCRM, DBlistcustCount, "List filters count mismatch");
}
	public void Modalpopup() throws Exception 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.navigate().to(Notes);
		driver.findElement(By.cssSelector("div.headerstep.received>div.headerstep_title>span.total-count.total_count_head")).click();
		
		Messages M = new Messages();
		M.CMP();
	}
	
	@Test(priority =4)
	public void deletepipeline() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		Actions action = new Actions(driver);
		
		WebElement e = driver.findElement(By.cssSelector("div.hidden.sidebar-bg-color"));
		action.moveToElement(e).perform();
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/nav/div[5]/div[2]/div[2]")).click();
		
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/div[1]")).click();
		jse.executeScript("document.querySelector('div#block0body > div > div > div > div > div > ul').scrollTop=300");
		driver.findElement(By.cssSelector("div#block0body > div > div > div > div > div > ul > li:last-of-type")).click();
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/a[1]")).click();
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[2]/a[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"get_delete_pipeline_main\"]/div/div/div/div/a[2]")).click();//<--permenant delete button
		driver.findElement(By.xpath("//*[@id=\"pp-delete-customer-form\"]/div/button")).click();
		String log=driver.findElement(By.id("alertify-logs")).getText();
		System.out.println();
		System.out.println("Pipeline deletion: "+log);
		
	}
}
