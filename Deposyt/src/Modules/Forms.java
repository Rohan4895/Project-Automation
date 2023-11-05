package Modules;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import AccessLevel.Data;

public class Forms extends Data
{

	@Test
	public void Form() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);
		JavascriptExecutor jse = (JavascriptExecutor)driver;

		driver.navigate().to(Forms);

		System.out.println();
		System.out.println("Testing Form Editor ----->");

		//Deleting duplicate
		driver.findElement(By.id("search_template_list")).sendKeys("New Test Form !!))@@");
		driver.findElement(By.cssSelector("#admintable0>thead>tr>th>label>span.checkmark")).click();
		Thread.sleep(500);
		driver.findElement(By.cssSelector("#formlist>a")).click();
		Thread.sleep(500);
		try
		{
			driver.findElement(By.cssSelector("#multiple-delete-modal>div>div>div>div>button")).click();
		}
		catch(Exception elementNotInteractableException)
		{
			
		}
		//Creating new Form
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div#spacious-container>div>div>div>div>div>div:nth-of-type(2)>a")).click();
		driver.findElement(By.id("form_title")).sendKeys("New Test Form !!))@@");
		driver.findElement(By.id("form_slug")).sendKeys("newtestform");
		driver.findElement(By.id("formcreation-submit")).click();

		Thread.sleep(1000);
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

		//End Screen
		driver.findElement(By.cssSelector("span.spanendingdiv")).click();
		driver.findElement(By.cssSelector("div.end-screen-box.px-0.endscreentopdiv")).isDisplayed();
		jse.executeScript("document.querySelector('#fb-endingsettings').scrollTop = 400");
		driver.findElement(By.cssSelector("input[name=\"showendparagraph\"]+span")).click();//Show paragraph button
		driver.findElement(By.name("paragraph")).clear();
		driver.findElement(By.name("paragraph")).sendKeys("Test End Page");
		String p = driver.findElement(By.cssSelector("div.endscreentopdiv>div>p:nth-child(3)")).getText();
		Assert.assertEquals(p, "Test End Page","Preview Not Get Displyed For Paragraph");

		driver.findElement(By.cssSelector("input[name=\"showendbutton\"]+span")).click();//Show button
		driver.findElement(By.name("buttonurl")).sendKeys("https://app.deposyt.com");
		driver.findElement(By.cssSelector("div.endscreentopdiv>div>button")).isDisplayed();
		driver.findElement(By.cssSelector("input[name=\"showenddisclaimer\"]+span")).click();
		driver.findElement(By.name("disclaimer")).sendKeys("This Is Disclaimer");
		String d = driver.findElement(By.cssSelector("p.endscreendiscliamer")).getText();
		Assert.assertEquals(d, "This Is Disclaimer", "Disclaimer not displayed in the preview");

		driver.findElement(By.id("fb-save-form")).click();
		String su = driver.findElement(By.id("toast-container")).getText();

		driver.navigate().back();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"formcreation\"]/div/div/div/div[4]/a")).click();

		//Output for Form filling page
		System.out.println();
		System.out.println("Validating Form Functionality ----->");
		System.out.println("	"+su);

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

		//Setting new Trigger		
		driver.navigate().to(Forms);

		System.out.println();
		System.out.println("Form Result Page(Adding trigger, Submitting form) ----->");
		driver.findElement(By.name("search")).sendKeys("New Test Form !!))@@");
		action.sendKeys(Keys.ENTER).perform();

		driver.findElement(By.cssSelector("#templateListTBody>tr>td:nth-child(8)")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.form-trigger-details")).isDisplayed();

		String Tagid=null, UserId = null;//, PipeId = null, StageId = null;

		for(int i=1; i<=4; i++)
		{
			driver.findElement(By.id("addingAction")).click();
			Thread.sleep(500);
			driver.findElement(By.cssSelector("div.dropdown-menu.dropdown-add-action")).isDisplayed();
			driver.findElement(By.cssSelector("div.dropdown-menu.dropdown-add-action>a:nth-child("+i+")")).click();

			if(i==1)//mail
			{
				Thread.sleep(6000);
				driver.findElement(By.name("emailsubject")).sendKeys("Trigger Mail - Form Module");
				action.sendKeys(Keys.TAB).sendKeys("Test mail").build().perform();
				Thread.sleep(2000);
			}
			else if (i==2)//SMS
			{				
				jse.executeScript("document.querySelector('div#block0body>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(6)').scrollTop=500");
				driver.findElement(By.name("trigger_sms")).sendKeys("Trigger SMS - Form Module");
			}
			else if(i==3)//Tags
			{
				jse.executeScript("document.querySelector('div#block0body>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(6)').scrollTop=1000");
				driver.findElement(By.id("js-tags-selector-selectized")).click();
				action.sendKeys(Keys.ENTER).perform();
				Tagid = driver.findElement(By.cssSelector("#apply_remove_tags>div>div>div>div")).getAttribute("data-value");
				driver.findElement(By.name("trigger_sms")).click();
			}
			else if(i==4)//Assign to user
			{
				jse.executeScript("document.querySelector('div#block0body>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(6)').scrollTop=1000");
				Thread.sleep(1000);
				driver.findElement(By.id("ctAssignedToIds")).click();
				driver.findElement(By.cssSelector("#assignedToFilterList>label:nth-child(2)")).click();
				UserId = driver.findElement(By.id("ctFilter_salesperson")).getAttribute("value");
				driver.findElement(By.id("ctAssignedToIds")).click();
			}
			/*else if(i==5)//Pipeline
			{
				jse.executeScript("document.querySelector('div#block0body>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(6)').scrollTop=1000");
				driver.findElement(By.cssSelector("select[name=trigger_pipeline]+div")).click();
				PipeId = driver.findElement(By.cssSelector("select[name=trigger_pipeline]+div>ul>li:nth-child(3)")).getAttribute("data-value");
				Thread.sleep(2000);
				driver.findElement(By.cssSelector("select[name=trigger_pipeline]+div>ul>li:nth-child(3)")).click();

				driver.findElement(By.cssSelector("select[name=trigger_stage]+div")).click();
				Thread.sleep(2000);
				StageId = driver.findElement(By.cssSelector("select[name=trigger_stage]+div>ul>li:nth-child(2)")).getAttribute("data-value");
				driver.findElement(By.cssSelector("select[name=trigger_stage]+div>ul>li:nth-child(2)")).click();
			}*/
			jse.executeScript("document.querySelector('div#block0body>div:nth-of-type(2)>div:nth-of-type(2)>div>div>div:nth-of-type(6)').scrollTop=1000");
		}

		driver.findElement(By.cssSelector("div.bottom-trigger-buttons>div>div>a:last-of-type")).click();
		System.out.println("	"+driver.findElement(By.cssSelector("#toast-container>div>div")).getText());

		String win1 = driver.getWindowHandle();
		driver.findElement(By.cssSelector("#templateListTBody>tr>td:nth-child(5)")).click();

		Set<String> handles = driver.getWindowHandles();
		for(String handle:handles){
			driver.switchTo().window(handle);	
		}

		// Form filling
		driver.findElement(By.cssSelector("div.personal-contactinfo>div:first-of-type>input")).sendKeys("Form");
		driver.findElement(By.cssSelector("div.personal-contactinfo>div:last-of-type>input")).sendKeys("Contact112233");
		driver.findElement(By.cssSelector("div.personal-contactinfophem>div:first-of-type>input")).sendKeys("Rohankokare7663+FormTest@gmail.com");
		driver.findElement(By.cssSelector("div.personal-contactinfophem>div:last-of-type>div>input")).sendKeys("7579793173");
		driver.findElement(By.id("step_1_btn")).click();
		Thread.sleep(5000);
		System.out.println("	"+driver.findElement(By.cssSelector("#ending_A > div.px-10 > p")).getText());

		//End Screen Validations
		String A = driver.findElement(By.cssSelector("p.endscreenpara ")).getText();
		Assert.assertEquals(A, "Test End Page","Paragraph Not Get Displyed On End Screen");

		String B = driver.findElement(By.cssSelector("p.endscreendiscliamer")).getText();
		Assert.assertEquals(B, "This Is Disclaimer", "Desclaimer Not Get Displyed");
		driver.findElement(By.cssSelector("p.endscreenpara +button")).click();
		Assert.assertEquals(driver.getCurrentUrl(),"https://app.deposyt.com/","Button not Navigating to correct position");
		driver.close();

		driver.switchTo().window(win1);

		//Listing, increment and delete
		try 
		{
			String name = driver.findElement(By.cssSelector("tr.admintable_row.at-row-0.at-row-pair.cursor-pointer:first-of-type>td:nth-of-type(2)>div>a")).getText();
			Assert.assertEquals(name, "New Test Form !!))@@","Listing not Found");
		}
		catch (Exception e) 
		{
			driver.findElement(By.xpath("//*[@id=\"formcreation\"]/div/div/div/div[4]/a")).click();
			String name = driver.findElement(By.cssSelector("tr.admintable_row.at-row-0.at-row-pair.cursor-pointer:first-of-type>td:nth-of-type(2)>div>a")).getText();
			Assert.assertEquals(name, "New Test Form !!))@@","Listing not Found");
		}		
		Thread.sleep(3000);
		driver.navigate().refresh();
		String num =driver.findElement(By.xpath("//*[@id=\"at0-cell-total_contact-0\"]/div/a")).getText();
		Assert.assertEquals(num, "1","Result count updated wrong");
		//String cust = driver.findElement(By.xpath("//*[@id=\"at0-cell-total_contact-0\"]/div/a")).getAttribute("href");//customer id
		System.out.println("	Count in Result Column: "+num);


		//validation - Other
		Thread.sleep(10000);
		driver.navigate().to("https://app.deposyt.com/index.php?m=customers");
		driver.findElement(By.id("showCustomerTVFilters")).click();
		Thread.sleep(300);
		driver.findElement(By.cssSelector("div.customer-adv-filters")).isDisplayed();
		jse.executeScript("document.querySelector('#campuser').scrollTop = 300");
		driver.findElement(By.id("ctformsIds")).click();
		Thread.sleep(300);
		driver.findElement(By.cssSelector("label[data-text=\"New Test Form !!))@@\"]")).click();
		driver.findElement(By.id("ctformsIds")).click();
		Thread.sleep(5000);
		String a = driver.findElement(By.cssSelector("div.filter-bottom-box>div>a>span")).getText();
		Assert.assertEquals(a, "1", "Count mismatched in customer filter");

		/*/Applying Pipeline filter
		driver.findElement(By.cssSelector("div.customer-advanced-filter-menus>div:nth-child(10)>div")).click();
		driver.findElement(By.cssSelector("div.customer-advanced-filter-menus>div:nth-child(10)>div>ul>li[data-value=\""+PipeId+"\"]")).click();

		driver.findElement(By.id("ctStagesIds")).click();
		driver.findElement(By.cssSelector("#stagesFilterList>label[data-value = \""+StageId+"\"]")).click();
		driver.findElement(By.id("ctStagesIds")).click();

		Thread.sleep(4000);
		a = driver.findElement(By.cssSelector("div.filter-bottom-box>div>a>span")).getText();
		Assert.assertEquals(a, "1", "Count mismatched in customer filter");
		 */

		//Applying assigned user tag
		driver.findElement(By.id("ctAssignedToIds")).click();
		driver.findElement(By.cssSelector("#assignedToFilterList>label[data-value=\""+UserId+"\"]")).click();
		driver.findElement(By.id("ctAssignedToIds")).click();
		Thread.sleep(5000);
		a = driver.findElement(By.cssSelector("div.filter-bottom-box>div>a>span")).getText();
		Assert.assertEquals(a, "1", "Count mismatched in customer filter");

		//Applying Tags filter 
		driver.findElement(By.id("selected_Tagssee")).click();
		driver.findElement(By.cssSelector("#TagsFilterList>label[data-value= \""+Tagid+"\"]")).click();
		driver.findElement(By.id("selected_Tagssee")).click();

		Thread.sleep(5000);
		a = driver.findElement(By.cssSelector("div.filter-bottom-box>div>a>span")).getText();
		Assert.assertEquals(a, "1", "Count mismatched in customer filter");
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		//driver.findElement(By.cssSelector("div.customer-more-option>div>a[href = \""+cust+"\"]")).isDisplayed();

		//validation - Mail & SMS
		driver.navigate().to("https://app.deposyt.com/index.php?m=conversation");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.desktop-sms-search-field>div>form>input:last-of-type")).sendKeys("Rohankokare7663+FormTest@gmail.com");
		action.sendKeys(Keys.ENTER).perform();
		driver.findElement(By.cssSelector("#custom-sms-contact-list>div:first-child")).click();

		String test1=null;
		try
		{
			test1 = driver.findElement(By.cssSelector("div.conversation_started_messages_div_content>div.sms-box:nth-of-type(5)>div>div:nth-child(2)>div:first-child")).getText();
		}
		catch(Exception e)
		{
			driver.navigate().refresh();
			test1 = driver.findElement(By.cssSelector("div.conversation_started_messages_div_content>div.sms-box:nth-of-type(5)>div>div:nth-child(2)>div:first-child")).getText();
		}
		String arr[] = test1.split("Module",2);
		Assert.assertEquals(arr[0],"Trigger SMS - Form ", "Trigger SMS Not Found");
		String test2 = driver.findElement(By.cssSelector("div.conversation_started_messages_div_content>div.sms-box:last-of-type>div>div:nth-child(2)>span:last-of-type")).getText();
		String arr1[] = test2.split("Module",2);
		Assert.assertEquals(arr1[0],"Trigger Mail - Form ", "Trigger Mail Not Found");		
	}

	@Test(priority = 2)
	public void FormResult() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);
		driver.navigate().to("https://app.deposyt.com/index.php?m=addformtemplates");

		System.out.println();
		System.out.println("Form Result Page ----->");
		driver.findElement(By.name("search")).sendKeys("New Test Form !!))@@");

		driver.findElement(By.xpath("//*[@id=\"at0-cell-total_contact-0\"]/div/a")).click();

		Thread.sleep(1000);

		for(int i=2; i<=7;i++)
		{
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("#selectAll+span")).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("#formlist>button:nth-child("+i+")")).click();
			Thread.sleep(1000);

			if(i==2)//Bulk reply popup
			{
				Thread.sleep(1000);
				driver.findElement(By.id("bulk_reply_sms")).sendKeys("Test SMS");
				driver.findElement(By.id("SendBulkSmsBtn")).click();
				Thread.sleep(200);
				String Alert = driver.findElement(By.cssSelector("#alertify-logs>article")).getText();
				System.out.println("	"+Alert);
			}
			else if(i==3)//assign to user popup
			{
				Thread.sleep(1000);
				driver.findElement(By.id("assign-user-dropdown")).click();
				driver.findElement(By.cssSelector("#assign-user-dropdown>ul")).isDisplayed();
				driver.findElement(By.cssSelector("#assign-user-dropdown>ul>li:nth-child(2)")).click();
				driver.findElement(By.cssSelector("#ct_assignuser_form>div:nth-child(5)>div>a:last-child")).click();

				String Alert = driver.findElement(By.cssSelector("#alertify-logs>article")).getText();
				System.out.println("	"+Alert);
			}
			else if(i==4)//Apply tags popup
			{
				Thread.sleep(1000);
				driver.findElement(By.cssSelector("#applytagtouser>div>div>div>div>input")).click();
				action.sendKeys(Keys.ENTER).build().perform();
				driver.findElement(By.cssSelector("#applytagtouser>div:nth-child(4)>button:last-child")).click();

				String Alert = driver.findElement(By.cssSelector("#alertify-logs>article")).getText();
				System.out.println("	"+Alert);
			}
			else if(i==5)//Move Customers
			{
				Thread.sleep(1000);
				driver.findElement(By.cssSelector("#addusertopipelinestage>div:nth-child(6)>div>div")).click();
				driver.findElement(By.cssSelector("#addusertopipelinestage>div:nth-child(6)>div>div>ul>li:nth-child(3)")).click();// Select pipeline
				driver.findElement(By.cssSelector("#addusertopipelinestage>div:nth-child(6)>div:nth-child(5)")).click();
				driver.findElement(By.cssSelector("#addusertopipelinestage>div:nth-child(6)>div:nth-child(5)>ul>li:nth-child(2)")).click();// Stage
				driver.findElement(By.cssSelector("#addusertopipelinestage>div:nth-child(11)>button:last-child")).click();//Done

				String Alert = driver.findElement(By.cssSelector("#alertify-logs>article")).getText();
				System.out.println("	"+Alert);
			}
			else if(i==6)//Create List
			{
				Thread.sleep(1000);
				driver.findElement(By.id("title")).sendKeys("Form Result Test List auto created");
				driver.findElement(By.cssSelector("#create-contact-list>div:nth-child(3)>button")).click();

				System.out.println("	List Created Wait for cron");
			}
			else if(i==7)//Export Selected
			{
				Thread.sleep(1000);
				driver.findElement(By.cssSelector("#export-popup>div>div>div>div:nth-child(5)>button")).click();
				Thread.sleep(1000);

				String Alert = driver.findElement(By.cssSelector("#toast-container>div>div")).getText();
				System.out.println("	"+Alert);
			}
		}
	}

	public void Modalpopup() throws Exception 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.navigate().to(Forms);
		driver.findElement(By.id("search_template_list")).sendKeys("New Test Form !!))@@");
		driver.findElement(By.cssSelector("#admintable0>thead>tr>th>label>span.checkmark")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"at0-cell-total_contact-0\"]/div/a")).click();
		
		Messages M = new Messages();
		M.CMP();
		
	}
	
	@Test
	public void Formtestcases() throws Exception 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);
		JavascriptExecutor jse = (JavascriptExecutor)driver;

		driver.navigate().to("https://app.deposyt.com/index.php?m=addformtemplates");

		System.out.println();
		System.out.println("Testing Form Editor ----->");
		//Deleting duplicate
		driver.findElement(By.id("search_template_list")).sendKeys("New Test Form !!))@@");
		driver.findElement(By.cssSelector("#admintable0>thead>tr>th>label>span.checkmark")).click();
		Thread.sleep(500);
		driver.findElement(By.cssSelector("#formlist>a")).click();
		Thread.sleep(500);
		try
		{
			driver.findElement(By.cssSelector("#multiple-delete-modal>div>div>div>div>button")).click();
		}
		catch(Exception elementNotInteractableException)
		{
			
		}

		//Creating new Form With Empty name Field
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div#spacious-container>div>div>div>div>div>div:nth-of-type(2)>a")).click();
		driver.findElement(By.id("formcreation-submit")).click();

		try
		{
			System.out.println("	Validation Message for empty Form Name: "
					+driver.findElement(By.cssSelector("span.error_formname")).getText());

		}
		catch(Exception noSuchElementException)
		{
			Assert.assertTrue(false,"Validation Message not Found for Empty Form Title ");
		}

		//Creating Form For Testing Drag and Drop
		Thread.sleep(500);
		driver.findElement(By.id("form_title")).sendKeys("New Test Form !!))@@");
		driver.findElement(By.id("form_slug")).sendKeys("newtestform");
		driver.findElement(By.id("formcreation-submit")).click();

		try
		{
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("#fbtitle+a")).isDisplayed();
		}
		catch(Exception nosuchElementException)
		{
			Assert.assertFalse(true,"Form  Not Created");
		}

		Thread.sleep(1000);
		System.out.println();
		for(int i = 2; i<=28; i++)
		{
			if(i==10||i==16||i==21||i==23)
			{
				continue;
			}
			WebElement A = driver.findElement(By.cssSelector("ul.frmb-control.ui-sortable>li:nth-child("+i+")"));
			WebElement y = driver.findElement(By.cssSelector("ul[data-id=\"fb-cust-builder-1\"]"));
			action.dragAndDrop(A,y).perform();
			String Ename = driver.findElement(By.cssSelector("ul.frmb-control.ui-sortable>li:nth-child("+i+")>span")).getText();
			System.out.println("	Element Drag and dropped: "+Ename);

			if(i==8||i==14||i==20||i==26)
			{
				int scroll = 250;
				jse.executeScript("document.querySelector('div.cb-wrap.pull-left>ul').scrollTop= "+scroll+"");
				scroll = scroll+250;
			}
		}
		System.out.println();
		driver.findElement(By.id("fb-save-form")).click();
		driver.findElement(By.cssSelector("a[onclick=\"closeBuilder()\"]")).click();

		//Creating new Form for slug 
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div#spacious-container>div>div>div>div>div>div:nth-of-type(2)>a")).click();
		driver.findElement(By.id("form_title")).sendKeys("New Test Form !!))@@");
		driver.findElement(By.id("form_slug")).sendKeys("newtestform");
		driver.findElement(By.id("formcreation-submit")).click();
		Thread.sleep(500);
		System.out.println("Creating Duplicate Slug");
		System.out.println("	Error Message while creating duplicate slug: "+driver.findElement(By.cssSelector("span.error.error_slug")).getText());
		driver.navigate().to(Forms);

		//Deleting duplicate
		driver.findElement(By.id("search_template_list")).sendKeys("New Test Form !!))@@");
		driver.findElement(By.cssSelector("#admintable0>thead>tr>th>label>span.checkmark")).click();
		Thread.sleep(500);
		driver.findElement(By.cssSelector("#formlist>a")).click();
		Thread.sleep(500);
		driver.findElement(By.cssSelector("#multiple-delete-modal>div>div>div>div>button")).click();
		
		//Creating New Form for testing Captcha and hidden input field
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div#spacious-container>div>div>div>div>div>div:nth-of-type(2)>a")).click();
		driver.findElement(By.id("form_title")).sendKeys("New Test Form !!))@@");
		driver.findElement(By.id("form_slug")).sendKeys("newtestform");
		driver.findElement(By.id("formcreation-submit")).click();
		
		Thread.sleep(5000);
		jse.executeScript("document.querySelector('div.cb-wrap.pull-left>ul').scrollTop= 900");
		WebElement HIF = driver.findElement(By.cssSelector("ul.frmb-control.ui-sortable>li:nth-child(26)"));
		WebElement y = driver.findElement(By.cssSelector("ul[data-id=\"fb-cust-builder-1\"]"));
		action.dragAndDrop(HIF,y).perform();	
	}
}
