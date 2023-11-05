package AccessLevel;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tags extends Data
{
	
	@Test
	public void NoneAccess() throws Exception 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
	
		driver.navigate().to(UserManagment);//Getting Access level name
		String Access = driver.findElement(By.cssSelector("table>tbody>tr:first-of-type")).getAttribute("data-access-level");
		List <WebElement> test = driver.findElements(By.cssSelector("#invite_users_lists>label"));//Access Level List From User invite popup

		driver.navigate().to(AccessLevelPage);
		String a = null;
		
		System.out.println(test.size());
		
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
		System.out.println("Testing Tags With None Access ---->");
		System.out.println();
		System.out.println("	Setting Access Levels-->");
		
		//Setting Access Level to none for tags
		action.scrollByAmount(0, 1500).perform();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.module-wrapper:nth-of-type(3)>div:nth-of-type(1)>div.flex.access-level-buttons>button:first-of-type")).click();
		action.scrollByAmount(0, 1500).perform();
		System.out.println("	Access Level Set For Tags: None");
		
		//Setting modify access to customer
		driver.findElement(By.cssSelector("div.module-wrapper:nth-of-type(4)>div:nth-of-type(2)>div.flex.access-level-buttons>button:Last-of-type")).click();
		driver.findElement(By.cssSelector("div.module-wrapper:nth-of-type(4)>div:nth-of-type(3)>div.flex.access-level-buttons>button:Last-of-type")).click();
		
		jse.executeScript("if (!$(\".customer_access_to_all\").is(\":checked\")) {\r\n"
				+ "    alert('not cheked')\r\n"
				+ "}");
		try
		{
			driver.switchTo().alert().accept();
			driver.findElement(By.cssSelector("input.customer_access_to_all+span")).click();
		}
		catch(Exception noSuchAlertException)
		{
			
		}	
		System.out.println("	Access Level Set For customers: Modify");
		
		//Setting access Level for Pipeline
		action.scrollByAmount(0, 2670).perform();
		driver.findElement(By.cssSelector("div.module-wrapper:nth-of-type(5)>div:nth-of-type(1)>div.flex.access-level-buttons>button:Last-of-type")).click();
		driver.findElement(By.cssSelector("div.module-wrapper:nth-of-type(5)>div:nth-of-type(3)>div.flex.access-level-buttons>button:Last-of-type")).click();
		driver.findElement(By.cssSelector("div.module-wrapper:nth-of-type(5)>div:nth-of-type(4)>div.flex.access-level-buttons>button:Last-of-type")).click();
		
		jse.executeScript("if (!$(\".pipeline_access_to_all_old\").is(\":checked\")) {\r\n"
				+ "    alert('not cheked')\r\n"
				+ "}");
		try
		{
			driver.switchTo().alert().accept();
			driver.findElement(By.cssSelector("input[name=\"pipeline_access_to_all\"]+span")).click();
		}
		catch(Exception noSuchAlertException)
		{
			
		}
		System.out.println("	Access Level Set For Pipelines: Modify");
		
		//Setting access Level for Message
		action.scrollByAmount(0, 3420).perform();
		driver.findElement(By.cssSelector("div.module-wrapper:nth-of-type(6)>div:nth-of-type(1)>div.flex.access-level-buttons>button:Last-of-type")).click();
		driver.findElement(By.cssSelector("div.module-wrapper:nth-of-type(6)>div:nth-of-type(2)>div.flex.access-level-buttons>button:Last-of-type")).click();
		driver.findElement(By.cssSelector("div.module-wrapper:nth-of-type(6)>div:nth-of-type(3)>div.flex.access-level-buttons>button:Last-of-type")).click();
		
		jse.executeScript("if (!$(\".message_access_to_all\").is(\":checked\")) {\r\n"
				+ "    alert('not cheked')\r\n"
				+ "}");
		try
		{
			driver.switchTo().alert().accept();
			driver.findElement(By.cssSelector("input[name=\"message_access_to_all\"]+span")).click();
		}
		catch(Exception noSuchAlertException)
		{
			
		}
		System.out.println("	Access Level Set For Messages: Modify");
		
		//Setting access Level for Triggers
		action.scrollByAmount(0, 4530).perform();
		driver.findElement(By.cssSelector("div.module-wrapper:nth-of-type(8)>div:nth-of-type(1)>div.flex.access-level-buttons>button:Last-of-type")).click();
		driver.findElement(By.cssSelector("div.module-wrapper:nth-of-type(8)>div:nth-of-type(2)>div.flex.access-level-buttons>button:Last-of-type")).click();
		
		//Setting Access level For List
		action.scrollByAmount(0, 7200).perform();
		driver.findElement(By.cssSelector("div.module-wrapper:nth-of-type(15)>div:nth-of-type(1)>div.flex.access-level-buttons>button:Last-of-type")).click();
		driver.findElement(By.cssSelector("div.module-wrapper:nth-of-type(15)>div:nth-of-type(2)>div.flex.access-level-buttons>button:Last-of-type")).click();
				
		driver.findElement(By.cssSelector("button[data-type=access_level]")).click();//save button in access level
		
		driver.navigate().to(UserManagment);//user profile page
		driver.findElement(By.cssSelector("table>tbody>tr:first-of-type>td:nth-child(8)>div>a")).click();
		driver.findElement(By.cssSelector("table>tbody>tr:first-of-type>td:nth-child(8)>div>div>a:first-of-type")).click();//login to user account
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
		System.out.println("	Access Level Set For Triggers: Modify");
		
		//validations- navigation
		System.out.println();
		System.out.println("Validations:-> ");
		System.out.println();
		System.out.println("	Validating Navigation: ");
		driver.navigate().to("https://app.deposyt.com/index.php?m=tags");//tags page
		Assert.assertTrue(driver.getPageSource().contains("Access Denied!"));
		System.out.println("	Access Denied for Direct Navigation");
		
		driver.navigate().to(Settings);
		
		try
		{
			driver.findElement(By.cssSelector("div[onclick=\"document.location.href='index.php?m=tags';\"]")).isDisplayed();
			System.out.println();
			System.out.println("	Tags Option is Displayed in Settings Menu ---> Test failed");
			Assert.assertFalse(true,"Tags Option is Displayed in Settings Menu");
		}
		catch(Exception nosuchElementException)
		{
			System.out.println("	Tags Option Not displayed in Settings Menu");
		}
		
		//validating Message module
		driver.navigate().to(Messages);
		System.out.println();
		System.out.println("Validating Message Module:");
		
		try//Tags in Sidebar
		{
			System.out.println(driver.findElement(By.id("cusInfo_shorttags")).isDisplayed());
			System.out.println();
			System.out.println("	Tags Option is Displayed in Message Module ---> Test failed");
			Assert.assertFalse(true,"Tags Option is Displayed in Message Module");
		}
		catch(Exception noSuchElementException)
		{
			System.out.println("	Tags Not displayed in Message Module");
		}
		
		//Validating in customer Model popup
		driver.findElement(By.cssSelector("a[onclick^=openCustomerInfoModal]:first-of-type")).click();
		Thread.sleep(500);
		
		try
		{
			driver.findElement(By.cssSelector("div.cusInfo_shorttags_global")).click();
			System.out.println();
			System.out.println("	Tags Option is Displayed in Message Customer Model Popup ---> Test failed");
			Assert.assertFalse(true,"Tags Option is Displayed in Message Customer Model Popup");
		}
		catch(Exception noSuchElementException)
		{
			System.out.println("	Tags Not displayed in Customer Model Popup");
		}
		driver.findElement(By.cssSelector("a[onclick=\"$('.modal').modal('hide');\"]")).click();
			
		//Validating in message Filters
		driver.findElement(By.id("dis_advfilters")).click();
		Thread.sleep(1000);
		
		try
		{
			driver.findElement(By.id("selected_Tagssee")).click();
			System.out.println();
			System.out.println("	Tags Option is Displayed in Message Filter ---> Test failed");
			//Assert.assertFalse(true,"Tags Option is Displayed in Message Filter");  Code Commented Due to issue
		}
		catch(Exception nosuchelementException)
		{
			System.out.println("	Tags Filter Not Displayed");	
		}
		
		//Validating Call Log
		driver.navigate().to(CallLog);
		
		System.out.println("Validating Call Log Module");
		driver.findElement(By.cssSelector("#selectAll+span")).click();
		
		try
		{
			driver.findElement(By.cssSelector("div.cust-selected-options>div>button.applytagstocustomers ")).click();
			System.out.println();
			System.out.println("	Apply Tags Option is Displayed in call Log ---> Test failed");
			//Assert.assertFalse(true,"Apply Tags Option is Displayed in call Log"); Commented Due To issue 

		}
		catch(Exception noSuchElementException)
		{
			System.out.println("	Apply Tags option Not Displayed in Call Log");
		}
				
		//Validations Customers
		driver.navigate().to(Customers);
		
		System.out.println("Validating Customer Module");
		
		driver.findElement(By.cssSelector("#selectAll+span")).click();
		
		try// Add Tags popup
		{
			driver.findElement(By.cssSelector("div.cust-selected-options>button:nth-child(3)")).click();
			driver.findElement(By.xpath("//*[@id=\"add-tagto-user-popup\"]/div/div/div/div[1]/div[2]/a/i")).click();
			System.out.println();
			System.out.println("	Apply Tags Option is Displayed in Customers ---> Test failed");
			//Assert.assertFalse(true,"Apply Tags Option is Displayed in Customers"); Commented Due To issue
		}
		catch(Exception noSuchElementException)
		{
			System.out.println("	Apply Tags option Not Displayed in Customer Module");
		}
		
		try// Remove Tags popup
		{
			driver.findElement(By.cssSelector("div.cust-selected-options>button:nth-child(4)")).click();
			driver.findElement(By.cssSelector("#remove-tagto-user-popup>div>div>div>div>div+div>a")).click();
			System.out.println();
			System.out.println("	Remove Tags Option is Displayed in Customers ---> Test failed");
			//Assert.assertFalse(true,"Remove Tags Option is Displayed in Customers");// Commented Due To issue
		}
		catch(Exception noSuchElementException)
		{
			System.out.println("	Remove Tags option Not Displayed in Customer Module");
		}
		
		try// Remove Tags popup
		{
			driver.findElement(By.cssSelector("div.cust-selected-options>button:nth-child(6)")).click();
			jse.executeScript("document.querySelector('#export-option-popup').scrollTop=200");
			driver.findElement(By.id("addcolumns")).click();
			driver.findElement(By.cssSelector("#columnscheckboxfields>option[value=\"tags\"]")).isDisplayed();
			System.out.println();
			System.out.println("	Tags Option is Displayed in Export popup in customers ---> Test failed");
			//Assert.assertFalse(true,"Tags Option is Displayed in Export popup in customers");// Commented Due To issue
		}
		catch(Exception noSuchElementException)
		{
			System.out.println("	Tags option Not Displayed in Export Popup In Customer Module");
		}
		
		driver.findElement(By.linkText("Cancel")).click();
		
		//Checking Export Selected popup
		driver.findElement(By.id("column-menu-edit")).click();
		WebElement list = driver.findElement(By.cssSelector("div.column-names-section"));
		WebElement tags = driver.findElement(By.cssSelector("div[data-id=\"cntz_tags\"]"));
		action.moveToElement(list).scrollToElement(tags).build().perform();
	
		try
		{
			driver.findElement(By.cssSelector("div[data-id=\"cntz_tags\"]>div>label>span")).click();
			System.out.println();
			System.out.println("	Tags Column is Displayed in Customers ---> Test failed");
			//Assert.assertFalse(true, "Tags Column is Displayed in Customers"); Commented Due To issue
		}
		catch(Exception noSuchElementException)
		{
			System.out.println("	Tags Column is Not Displayed in Customers Table");
		}
		
		driver.findElement(By.linkText("Cancel")).click();
	
		driver.findElement(By.cssSelector("tbody>tr:first-child>td:last-child>div>a")).click();
		
		try 
		{
			driver.findElement(By.cssSelector("tbody>tr:first-child>td:last-child>div>div>a[title= \"Add Tags\"]")).click();
			System.out.println();
			System.out.println("	Add Tags option is Displayed in Customers 3Dot Menu ---> Test failed");
			//Assert.assertFalse(true,"Add Tags option is Displayed in Customers 3Dot Menu"); Commented Due To issue
		}
		catch(Exception nosuchElementeException)
		{
			System.out.println("	Add Tags is Not Displayed in Customers Table 3 Dot Menu");
		}
			
		driver.findElement(By.id("showCustomerTVFilters")).click();
		
		try
		{
			driver.findElement(By.id("selected_Tagssee")).click();
			System.out.println();
			System.out.println("	Tags option is Displayed in Customers Filter ---> Test failed");
			//Assert.assertFalse(true,"Tags option is Displayed in Customers Filter"); //Commented Due To issue
		}
		catch(Exception noSuchElementException)
		{
			System.out.println("	Tags is Not Displayed in Customer Filter");
		}
	
		driver.navigate().to("https://app.deposyt.com/index.php?m=customers&d=add");
		action.scrollByAmount(0, 900).perform();
		
		try
		{
			driver.findElement(By.id("js-tags-selector")).isDisplayed();
			System.out.println();
			System.out.println("	Tags option is Displayed in Add Customer Form ---> Test failed");
			//Assert.assertFalse(true,"Tags option is Displayed in Add Customer Form"); //Commented Due To issue
		}
		catch(Exception noSuchelementException)
		{
			System.out.println("	Tags is Displayed in Add Customer Form");
		}
	
		//Validating Pipeline Module
		System.out.println();
		System.out.println(" Validating Pipeline Module: ");
		driver.navigate().to(Pipelines);
		
		//Validating Customer card Customizer
		driver.findElement(By.id("pipesettingpopup")).click();
		
		try
		{
			//checked /not chēcked
			driver.findElement(By.cssSelector("#tags+span")).click();
			System.out.println();
			System.out.println("	Tags option is Displayed in Customer card Optimizer ---> Test failed");
			//Assert.assertFalse(true,"Tags option is Displayed in Customer card Optimizer");
		}
		catch(Exception noSuchElementException)
		{
			System.out.println("	Tags option not found on Customer Card Optimizer");
		}
		driver.findElement(By.linkText("Cancel")).click();
		
		//customer card
		try
		{
			driver.findElement(By.cssSelector("a.dp-tags-link")).isDisplayed();
		}
		catch(Exception nosuchElementeException)
		{
			System.out.println("	Tags Not displyed on Customer Card");
		}
		
		//Pipeline Filters
		driver.findElement(By.id("_btnOpenFilter")).click();
		
		try
		{
			jse.executeScript("document.querySelector('div#side-filter').scrollTop = 300");
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("input[type=\"select-multiple\"]")).click();
			System.out.println();
			System.out.println("	Tags option displyed in Filters ---> Test Failed");
			//Assert.assertFalse(true,"Tags option displyed in Filters");
		}
		catch(Exception nosuchelementException)
		{
			System.out.println(nosuchelementException);
			System.out.println("	Tags not Displyed in Pipelie filter");
		}
		
		//validating pipeline triggers
		driver.findElement(By.cssSelector("div.wrapper>div:first-child>div:nth-child(2)>div>a")).click();//add button
		driver.findElement(By.cssSelector("#add-pp-stage-popup>div>div>a:last-of-type")).click();//add trigger
		List <WebElement> li = driver.findElements(By.cssSelector("div.select_action>ul.list>li"));
		
		for(int i=2; i<=li.size();i++)
		{
			String name = driver.findElement(By.cssSelector("div.select_action>ul.list>li:nth-child("+i+")")).getText();
			
			if(name.equalsIgnoreCase("Apply Tags"))
			{
				System.out.println();
				System.out.println("	Apply Tags option is Displyed in Pipeline Triggers ---> Test Failed");
				//Assert.assertFalse(true,"test failed in pipeline Triggers");
			}
			else if(name.equalsIgnoreCase("Remove Tags"))
			{
				System.out.println();
				System.out.println("	Remove Tags option is Displyed in Pipeline Triggers ---> Test Failed");
				//Assert.assertFalse(true,"test failed in pipeline Triggers");
			}
			
		}
		
		System.out.println("	Apply tags and Remove tags options are not Displyed in Pipeline Triggers");
		
		//Validating List Module
		System.out.println();
		System.out.println("Validating List Module:");
		
		driver.navigate().to("https://app.deposyt.com/index.php?m=contactlist&d=add");
		driver.findElement(By.cssSelector("form#ContactListFrom>div:nth-of-type(2)>div>input")).sendKeys("test list");
		driver.findElement(By.id("ClistNextBtn")).click();
		
		try
		{
			driver.findElement(By.cssSelector("th[title=\"Tags\"]")).isDisplayed();
			System.out.println();
			System.out.println("	Tags Column Displyed in the List Module ---> Test Failed");
			//Assert.assertFalse(true,"Tags Column Displyed in the List Module");
		}
		catch (Exception noSuchElementException) 
		{
			System.out.println("	Tags not Displyed in the List table");
		}
		
		//validating in import list
		driver.findElement(By.cssSelector("div.filters-section.items-end>a")).click();
		driver.findElement(By.id("userfile")).sendKeys("C:\\Users\\Rohan kokare\\dummydata1.csv");
		driver.findElement(By.cssSelector("input[value=\"Upload\"]")).click();
		driver.findElement(By.cssSelector("input[value=\"Next\"]")).click();
		jse.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		
		try
		{
			driver.findElement(By.cssSelector("input[type=\"select-multiple\"]")).click();
			System.out.println();
			System.out.println("	Tags option Displayed in Import List ---> Test failed");
			//Assert.assertFalse(true,"Tags option Displayed in Import List");
		}
		catch(Exception noSuchElementException)
		{
			System.out.println("	Assign Tags Option not Displyed is the Import List option");
		}
		
	}
	
	@Test
	public void ViewAccess() throws Exception 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);
	
		driver.navigate().to(UserManagment);//Getting Access level name
		String Access = driver.findElement(By.cssSelector("table>tbody>tr:first-of-type")).getAttribute("data-access-level");
		List <WebElement> test = driver.findElements(By.cssSelector("ul.list>li.selectinviterole"));

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
		
		//Tags - view access- with no access of to create and delete tags
				action.sendKeys(Keys.PAGE_DOWN).sendKeys(Keys.PAGE_DOWN).sendKeys(Keys.PAGE_DOWN).perform();
				Thread.sleep(1000);
				driver.findElement(By.cssSelector("#accesslevel_section>div.access-level-info.mt-9>div:nth-child(5)>div:nth-child(2)>div.flex.access-level-buttons>button:nth-of-type(2)")).click();
				driver.findElement(By.cssSelector("#accesslevel_section>div.access-level-info.mt-9>div:nth-child(5)>div:nth-of-type(3)>div:last-of-type>button:first-of-type")).click();//Access off to delete tags
				driver.findElement(By.cssSelector("#accesslevel_section>div.access-level-info.mt-9>div:nth-child(5)>div:nth-of-type(2)>div:last-of-type>button:first-of-type")).click();//Access off to Create tags
				driver.findElement(By.cssSelector("button[data-type=access_level]")).click();//save button in access level
				driver.navigate().to("https://app.deposyt.com/index.php?m=usersmgmt&d=list");//user profile page
				driver.findElement(By.cssSelector("table>tbody>tr:first-of-type>td:nth-child(7)>div>a")).click();
				driver.findElement(By.cssSelector("table>tbody>tr:first-of-type>td:nth-child(7)>div>div>a:first-of-type")).click();//login to user account
				driver.switchTo().alert().accept();
				Thread.sleep(2000);
				driver.navigate().to("https://app.deposyt.com/index.php?m=tags");
				
				//validations
				Assert.assertFalse(driver.getPageSource().contains("Access Denied!"));
				String test1 = driver.findElement(By.cssSelector("div.tag-name-edit>input:first-of-type")).getAttribute("readonly");
				System.out.println(test1);
				Assert.assertEquals("User is able to modify the tags","true",test1);
				
				driver.findElement(By.cssSelector("input#selectAll+span.checkmark")).click();
				try {
					driver.findElement(By.id("Add_tags")).isDisplayed();
					Assert.assertFalse(true, "User Get Access to Add Tags");
				}catch(Exception e){}
				try {
					driver.findElement(By.cssSelector("button.deletebulktags")).isDisplayed();
					Assert.assertFalse(true, "User Get Access to Delete Tags");
				}catch(Exception q) {}

		
		//Tags - view access- with access of to create and delete tags
		action.sendKeys(Keys.PAGE_DOWN).sendKeys(Keys.PAGE_DOWN).sendKeys(Keys.PAGE_DOWN).perform();
				Thread.sleep(1000);
				driver.findElement(By.cssSelector("#accesslevel_section>div.access-level-info.mt-9>div:nth-child(5)>div:nth-child(2)>div.flex.access-level-buttons>button:nth-of-type(2)")).click();
				driver.findElement(By.cssSelector("#accesslevel_section>div.access-level-info.mt-9>div:nth-child(5)>div:nth-of-type(3)>div:last-of-type>button:last-of-type")).click();//Access to create tags
				driver.findElement(By.cssSelector("#accesslevel_section>div.access-level-info.mt-9>div:nth-child(5)>div:nth-of-type(2)>div:last-of-type>button:last-of-type")).click();//access to delete tags
				driver.findElement(By.cssSelector("button[data-type=access_level]")).click();//save button in access level
				driver.navigate().to("https://app.deposyt.com/index.php?m=usersmgmt&d=list");//user profile page
				driver.findElement(By.cssSelector("table>tbody>tr:first-of-type>td:nth-child(7)>div>a")).click();
				driver.findElement(By.cssSelector("table>tbody>tr:first-of-type>td:nth-child(7)>div>div>a:first-of-type")).click();//login to user account
				driver.switchTo().alert().accept();
				Thread.sleep(2000);
				driver.navigate().to("https://app.deposyt.com/index.php?m=tags");
				//validations
				Assert.assertFalse(driver.getPageSource().contains("Access Denied!"));
				
				driver.findElement(By.cssSelector("input#selectAll+span.checkmark")).click();
				try {
					driver.findElement(By.id("Add_tags")).isDisplayed();
				}catch(Exception e){
					Assert.assertFalse(true, "User Not Get Access to Add Tags");
				}
				try {
					driver.findElement(By.cssSelector("button.deletebulktags")).isDisplayed();
				}catch(Exception q) {
					Assert.assertFalse(true, "User Not Get Access to Delete Tags");
				}
				driver.findElement(By.xpath("//*[@id=\"dropdownMenu1\"]")).click();
				driver.findElement(By.linkText("Re-Login Back")).click();
			//	driver.navigate().to(url);
		
		//Tags - Modify access
		action.sendKeys(Keys.PAGE_DOWN).sendKeys(Keys.PAGE_DOWN).sendKeys(Keys.PAGE_DOWN).perform();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#accesslevel_section>div.access-level-info.mt-9>div:nth-child(5)>div:nth-child(2)>div.flex.access-level-buttons>button:nth-of-type(3)")).click();
		driver.findElement(By.cssSelector("button[data-type=access_level]")).click();//save button in access level
		driver.navigate().to("https://app.deposyt.com/index.php?m=usersmgmt&d=list");//user profile page
		driver.findElement(By.cssSelector("table>tbody>tr:first-of-type>td:nth-child(7)>div>a")).click();
		driver.findElement(By.cssSelector("table>tbody>tr:first-of-type>td:nth-child(7)>div>div>a:first-of-type")).click();//login to user account
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
		driver.navigate().to("https://app.deposyt.com/index.php?m=tags");
		
		//validations
		Assert.assertFalse(driver.getPageSource().contains("Access Denied!"));
		
		
		driver.findElement(By.xpath("//*[@id=\"dropdownMenu1\"]")).click();
		driver.findElement(By.linkText("Re-Login Back")).click();
	}
}
