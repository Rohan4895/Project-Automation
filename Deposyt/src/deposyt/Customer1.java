package deposyt;

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
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import AccessLevel.Data;


@Test (priority=1)
public class Customer1 extends Data
{
	String TagId = "2088";
	String AssignId= "817";

	/*public void tests() throws InterruptedException
	{
		// Bulk delete msg
		Actions actions=new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.navigate().to(Customers);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".relative.flex.justify-center.items-center.gap-2.column-total-section>span")).click(); 
		driver.findElement(By.cssSelector("#admintable0>thead>tr>th>label>input+span")).click();
		Thread.sleep(1000);

		try
		{
			driver.findElement(By.cssSelector("div.cust-selected-options.cust-selected-click-opt>button.deleteContacts.deleteselectedcustomerpopup")).click();
			driver.findElement(By.id("deleteblkpopup")).click();
		}
		catch(Exception e)
		{

		}

		// Import List 49
		Actions action = new Actions(driver);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

		driver.navigate().to(Lists);

		driver.findElement(By.name("search")).sendKeys("Auto imported");
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
		System.out.println("Importing Contacts To the List");

		driver.findElement(By.name("list_title")).sendKeys("Auto imported");
		driver.findElement(By.cssSelector("button[value=\"Upload\"]")).click();
		driver.findElement(By.id("userfile")).sendKeys("C:\\Users\\Rohan kokare\\Downloads\\49ContactList.csv");//Import List path
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
		driver.findElement(By.name("search")).sendKeys("Auto imported");
		action.sendKeys(Keys.ENTER).perform();
		String listcount = driver.findElement(By.id("at0-cell-total_contact-0")).getText();
		Assert.assertEquals(succount[3], listcount,"Assertion Failed for Imported Contact Count");
		System.out.println("	Contacts Imported Successfully To New List");

		// Bulk Sms

		//Actions action = new Actions(driver);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.navigate().to(Customers);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#admintable0>thead>tr>th>label>input+span")).click(); //
		driver.findElement(By.cssSelector(".cust-selected-options.cust-selected-click-opt>button")).click(); 
		driver.findElement(By.cssSelector(".flex.py-3.selected-wrapper-popup>div>button")).click(); 
		driver.findElement(By.cssSelector("#send-sms-email-popup>div>div")).click(); // sms
		driver.findElement(By.cssSelector(".w-full.mb-2.bulk_textarea_one1>textarea")).sendKeys("Successfully send sms via Deposyt @");
		driver.findElement(By.cssSelector(".text-danger.smserrordisplay+div>a+a")).click();	

		Thread.sleep(60000); 
		driver.navigate().to(Messages);
		driver.findElement(By.cssSelector("input[name='filters'][type='text']")).sendKeys("40");
		action.sendKeys(Keys.ENTER).perform();

		String ActualSms = driver.findElement(By.cssSelector(".space-y-6.text-base.font-medium ")).getText();	 // sms located
		String[] parts = ActualSms.split("@", 2); 
		System.out.println(ActualSms);

		String ExpectedSms = "Successfully send sms via Deposyt ";
		System.out.println(ExpectedSms);
		AssertJUnit.assertEquals(ExpectedSms, parts[0]);

		// bulk email
		driver.navigate().to(Customers);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#admintable0>thead>tr>th>label>input+span")).click(); //select all
		driver.findElement(By.cssSelector(".cust-selected-options.cust-selected-click-opt>button")).click(); //open bulk reply
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".flex.py-3.selected-wrapper-popup>div>button+button")).click();	// clik email
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.tox-editor-container")));
		driver.findElement(By.id("subject")).sendKeys("Regarding Task Done");
		Thread.sleep(2000);
		actions.sendKeys(Keys.TAB).sendKeys("Hi sir, How are u doing").build().perform();
		driver.findElement(By.id("SendBulkEmailBtn")).click();

		// Assign To USer
		driver.navigate().to(Customers);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#admintable0>thead>tr>th>label>input+span")).click(); //select all
		driver.findElement(By.cssSelector(".cust-selected-options.cust-selected-click-opt>button+button")).click(); // clik assin user
		driver.findElement(By.cssSelector("#ct_assignuser_form>div>div>div>div>input")).click(); // click drp dwn

		driver.findElement(By.cssSelector("#Filter-List-Event>div+label")).click(); // slect yash 
		driver.findElement(By.cssSelector(".flex.justify-end.items-center.gap-4.mt-4.w-full>a+a")).click(); // clcik done btn	


		// Apply Tags
		driver.navigate().to(Customers);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#admintable0>thead>tr>th>label>input+span")).click(); //select all
		driver.findElement(By.cssSelector(".cust-selected-options.cust-selected-click-opt>button+button+button")).click(); 
		driver.findElement(By.cssSelector(".mx-auto.w-full>select+div>div")).click();// clik add tag
		driver.findElement(By.cssSelector(".mx-auto.w-full>select+div>div>div")).click();

		driver.findElement(By.cssSelector("button[type='button']#linkdone")).click();


		// Create Lists
		driver.navigate().to(Customers);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#admintable0>thead>tr>th>label>input+span")).click(); //select all
		driver.findElement(By.cssSelector(".cust-selected-options.cust-selected-click-opt>button:nth-child(6)")).click(); 
		driver.findElement(By.cssSelector(".flex.justify-start.gap-1>h1+div>input")).sendKeys("List Is Created");
		driver.findElement(By.cssSelector(".px-6.py-3.font-semibold.text-sm.bulk_cencel_bt.dp-btn.dp-transparent-btn.dp-cancel-btn+button")).click(); 
	}
	 */
	@Test(priority=2)
	public void pipelines() throws InterruptedException

	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.navigate().to(Customers);

		driver.findElement(By.cssSelector("#at0-cell-name-0>a>div+span")).click();
		Thread.sleep(1000);

		String PipelineId = driver.findElement(By.cssSelector("input.pipelinestageslisthiddensideglobal")).getAttribute("value");
		//System.out.println(PipelineId);

		String StageId = driver.findElement(By.cssSelector("#piplinebooking_stage_list_global>Label:nth-child(2)")).getAttribute("data-value");
		//System.out.println(StageId);

		List <WebElement> name1 = driver.findElements(By.cssSelector("#pipelinebooking_customer_popup>label"));

		for (int i = 1; i <= name1.size(); i++) 
		{
			String a = driver.findElement(By.cssSelector("#pipelinebooking_customer_popup>label:nth-of-type("+i+")")).getAttribute("data-value");
			//System.out.println(a)

			if (!a.equals(PipelineId))
			{
				driver.findElement(By.cssSelector("div.stagebox  >div>div:first-child>div:nth-of-type(1)>div:first-child")).click();
				driver.findElement(By.cssSelector("#pipelinebooking_customer_popup>label:nth-of-type("+i+")")).click();
				break;
			}
		}

		driver.findElement(By.cssSelector("div.stagebox  >div>div>div:nth-child(1)>div")).click(); // drop dwns
		Thread.sleep(1000);

		driver.findElement(By.cssSelector("#piplinebooking_stage_list_global>label:first-of-type")).click();
		driver.findElement(By.cssSelector("#message_newnumber_popup_heading+div+div+a")).click(); // cancel popup
		
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#at0-cell-actions_col-0>div>a")).click(); // click on more o
		driver.findElement(By.cssSelector("a.dp-dropdown-item.dropdown-item.movetostagelink.font-semibold.darkColor")).click(); // clk on move to pipeli
		driver.findElement(By.cssSelector("#pp-move-stage-form>div:first-of-type>div.relative>div:first-child")).click(); //click pipeline
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#pipelinemovecustomer>label[data-value = \""+PipelineId+"\"]")).click(); // select and validate

		driver.findElement(By.cssSelector("#hiddenstageid+input+input+div+div>div.relative.filter_input")).click();// stage click
		driver.findElement(By.cssSelector("#pipelineCustomerstagesFilterList>Label[data-value=\""+StageId+"\"]")).click(); // stage selelct and validate
		driver.findElement(By.cssSelector("#select-stage-list-form+div>a+button")).click(); // done btn


		driver.findElement(By.cssSelector("#at0-cell-name-0>a>div+span")).click();
		Thread.sleep(1000);

		String PipelineIdNew = driver.findElement(By.cssSelector("input.pipelinestageslisthiddensideglobal")).getAttribute("value");
		//System.out.println(PipelineIdNew);

		String StageIdNew = driver.findElement(By.cssSelector("#piplinebooking_stage_list_global>Label:nth-child(2)")).getAttribute("data-value");
		//System.out.println(StageIdNew);

		Assert.assertEquals(PipelineId, PipelineIdNew,"Pipeline Id Mismatch");
		Assert.assertEquals(StageId, StageIdNew,"Stage Id Mismatch");

		System.out.println("Pipeline changed Successfully");	


		// Add Note

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.navigate().to(Customers);

		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#at0-cell-actions_col-0>div>a")).click();
		//driver.findElement(By.cssSelector("#at0-cell-actions_col-0>div>a")).click();
		driver.findElement(By.cssSelector("#at0-cell-actions_col-0>div>a+div>a+a+a")).click();
		driver.findElement(By.cssSelector("#note-header>div+i+div>div+div")).click(); //n
		driver.findElement(By.cssSelector("#newCustomerID+p+textarea")).sendKeys("This is Nadsoft");
		driver.findElement(By.cssSelector("#add_a_note_popup_btn")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#at0-cell-name-0>a>div+span")).click();

		String actualText = driver.findElement(By.cssSelector("div.email-message-preview.break-words>div+div")).getText();
		String expectedText = "This Is Nadsoft";
		Assert.assertEquals(expectedText, actualText);

		// Add Tags

		Actions action = new Actions(driver);

		// Adding New Tag
		driver.navigate().to(TagManager);
		driver.findElement(By.name("searchtag")).sendKeys("YashNadsoft");
		action.sendKeys(Keys.ENTER).perform();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#selectAll+span")).click();
		driver.findElement(By.cssSelector("button.deletebulktags ")).click();
		driver.findElement(By.cssSelector("div.selectize-control.custom-tag-input>div>input")).sendKeys("YashNadsoft");
		action.sendKeys(Keys.ENTER).perform();
		driver.findElement(By.id("Add_tags")).click();


		// start add tag
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.navigate().to(Customers);
		driver.findElement(By.cssSelector("#at0-cell-actions_col-0>div>a")).click();
		driver.findElement(By.cssSelector("div.customer-more-option.open>div>a:nth-child(4)")).click();

		driver.findElement(By.cssSelector("div.selectize-control.custom-tag-input>div>input")).sendKeys("YashNadsoftss");
		action.sendKeys(Keys.ENTER).perform();
		driver.findElement(By.cssSelector("#pipeline-tag-popup>div>div>a+div>div>div>span")).click();
		driver.findElement(By.cssSelector("#tagerror+a+div+hr+div>a+div>a+button")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#showCustomerTVFiltersMainDiv>a")).click();
		driver.findElement(By.cssSelector("#campuser>div>div>div")).click();

		driver.findElement(By.cssSelector("#withreminderflag+div+div+div+div+div+div+div>i+div>i+input")).click();

		String actualTag = driver.findElement(By.cssSelector("#TagsFilterList>div+label+label")).getText();
		String ExpectedTag= "YashNadsoftss";
		AssertJUnit.assertEquals(ExpectedTag, actualTag);

		// Delete


		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.navigate().to(Customers);

		String BeforeDeletecust = driver.findElement(By.cssSelector("#at0-cell-chk1-0>div>label")).getAttribute("class"); // delete cust no
		//System.out.println(BeforeDeletecust);
		driver.findElement(By.cssSelector("#at0-cell-actions_col-0>div>a")).click();
		driver.findElement(By.cssSelector("div.customer-more-option.open>div>a:nth-child(4)+a+a")).click();
		driver.findElement(By.cssSelector("#formdeleteactionmodaldiv>div>button")).click();

		driver.navigate().refresh();	
		String AfterDelleteCust = driver.findElement(By.cssSelector("#at0-cell-chk1-0>div>label")).getAttribute("class");
		//System.out.println(AfterDelleteCust);

		if (BeforeDeletecust == AfterDelleteCust) {

			System.out.println("Customer is not deleted");
		}
		else
		{
			System.out.println("Customer is deleted successfully");
		}
	}

	/*/@Test(priority=3)
	public void validateAll() throws InterruptedException
	{

		// Validate Tags
		Thread.sleep(30000);
		driver.navigate().to(Customers);
		driver.findElement(By.cssSelector("#showCustomerTVFiltersMainDiv>a")).click(); // filter
		driver.findElement(By.cssSelector("#selected_Tagssee")).click(); // drp dwn
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("label[data-value=\""+TagId+"\"]")).click();  //  click user
		//driver.findElement(By.cssSelector("label[data-value=\"2088\"]")).click(); 
		Thread.sleep(4000);
		String CountTags = driver.findElement(By.cssSelector(".filter-bottom-box>div>a>span")).getText(); // Total count 48
		System.out.println(CountTags);

		if (!"48".equals(CountTags))
		{
			Thread.sleep(30000);

		}

		// valdate customer is get assigned
		driver.navigate().to(Customers);
		driver.findElement(By.cssSelector("#showCustomerTVFiltersMainDiv>a")).click(); // filter
		driver.findElement(By.cssSelector("#ctAssignedToIds[type=\"text\"]")).click(); // drp dwn
		driver.findElement(By.cssSelector("label[data-value=\""+AssignId+"\"]")).click(); // clcik user

		Thread.sleep(4000);
		String Countuser = driver.findElement(By.cssSelector(".filter-bottom-box>div>a>span")).getText(); // Total count 48
		System.out.println(Countuser);

		if (!"48".equals(Countuser))
		{
			Thread.sleep(30000);

		}

		// validate Move to Pipline


		// Check New List Is get Created in list Module
		driver.navigate().to(Customers);
		driver.findElement(By.cssSelector("#showCustomerTVFiltersMainDiv>a")).click(); // filter
		driver.findElement(By.cssSelector("input[type=\"text\"]#ctListsIds")).click(); // dr dwn
		driver.findElement(By.cssSelector("#listsFilterList>div+label")).click(); // select list
		Thread.sleep(4000);

		String CountList = driver.findElement(By.cssSelector(".filter-bottom-box>div>a>span")).getText(); // get text
		System.out.println(CountList);

		if (!"48".equals(CountList))
		{
			Thread.sleep(30000);

		}

		// Delete List after created
		driver.navigate().to(Customers);
		driver.findElement(By.cssSelector("#admintable0>thead>tr>th>label>input+span")).click();//select all
		driver.findElement(By.cssSelector("div.cust-selected-options.cust-selected-click-opt>button.deleteContacts.deleteselectedcustomerpopup")).click();
		driver.findElement(By.id("deleteblkpopup")).click();

	}*/
}


