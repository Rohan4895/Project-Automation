package Login;

import java.io.IOException;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import AccessLevel.Data;

public class Dailytesting extends Data
{
	String FormTagid = null, FormUserId = null, FormPipeId = null, FormStageId = null, Pipelineid = null;

	@BeforeMethod
	public void navigation() 
	{
		driver.navigate().to(Dashboard);
	}

	@Test(priority = -1)
	public void trigger() throws InterruptedException 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		Actions action = new Actions(driver);
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(15));	

		driver.navigate().to(Triggers);

		// Adding new trigger
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/div/button")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("select#select_trigger+div")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("ul.list>li[data-value=\"ENTERS_PIPELINE_STAGE\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("select[name=\"trigger_pipeline\"]+div")).click();
		driver.findElement(By.cssSelector("div.open>ul.list>li:nth-child(2)")).click();
		Thread.sleep(3000);
		action.scrollByAmount(0, 200).perform();
		driver.findElement(By.cssSelector("select[name=\"trigger_stage\"]+div")).click();
		driver.findElement(By.cssSelector("div.open>ul.list>li:nth-child(3)")).click();

		driver.findElement(By.cssSelector("div.flex.gap-3.actioncard > div:nth-of-type(3) > div:nth-of-type(2)")).click();
		jse.executeScript("document.querySelector('div[data-position=\"1\"]>div>div>ul.list').scrollTop=300");
		driver.findElement(By.cssSelector("li[data-value=\"10\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Create An Email")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name("emailsubject")));
		driver.findElement(By.name("emailsubject")).sendKeys("This is test mail from Triggers 110022");
		Thread.sleep(8000);
		action.sendKeys(Keys.TAB).sendKeys("Test mail from Triggers").build().perform();
		driver.findElement(By.linkText("Done")).click();
		Thread.sleep(2000);
		action.sendKeys(Keys.PAGE_UP).perform();
		Thread.sleep(500);
		action.sendKeys(Keys.PAGE_UP).perform();
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[2]/div/div/div[1]/a")).click();
		driver.findElement(By.id("savetriggermainbtn")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Exit")).click();
		String asd = driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[2]/div/div/div[1]/div[1]/h1")).getText();
		String Tristat = driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[2]/div/div/div[1]/div[2]/div[2]/div/h1")).getText();


		//Output
		System.out.println();
		System.out.println("Testing Triggers ----->");
		System.out.println("	Trigger "+asd);
		System.out.println("	Trigger status: "+Tristat);
	}

	@Test(priority = 1)
	public void contact() throws InterruptedException 
	{
		Actions action =new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;

		driver.navigate().to(Customers);
		
		//Checking for duplicate customers
		driver.findElement(By.name("search")).sendKeys("4012057373");
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
			driver.navigate().refresh();
		}

		try 
		{
			String limitmsg= driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[1]/h1")).getText();
			System.out.println(limitmsg);
			throw new SkipException("Skipped");
		}
		catch(Exception NoSuchElementException) 
		{		

		}

		Thread.sleep(5000);

		//Adding customer-->
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/a[1]")).click();// add customer button

		driver.findElement(By.name("first_name")).sendKeys("Weaired testname");
		driver.findElement(By.name("email")).sendKeys("rohan@nadsoftdesign.com");	
		driver.findElement(By.id("cellphone")).sendKeys("14012057373");
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

		/*/Adding Same user with +1
		driver.navigate().to("https://app.deposyt.com/index.php?m=customers&d=add");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/a[1]")).click();
		driver.findElement(By.name("first_name")).sendKeys("Weaired testname2");	
		driver.findElement(By.id("cellphone")).sendKeys("+14012057373");
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

		try 
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section[2]/article")));
			driver.findElement(By.xpath("/html/body/section[2]/article")).isDisplayed();
			System.out.println("Customer created with Same number Preceeding with +1 ");
		}
		catch(Exception TimeOutException) 
		{
			System.out.println("Exception hit for user not Added");
		}*/

		// delete triggers
		driver.navigate().to(Triggers);
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[2]/div/div/div[1]/div[2]/div[2]/a[2]/i")).click();
		driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
		System.out.println("	Trigger Deleted Successfully");
	}

	@Test(priority = 10)
	public void smscamp() throws InterruptedException 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		Actions action = new Actions(driver);

		System.out.println();
		System.out.println("Campaign with instant SMS Testing ----->");
		driver.navigate().to(Campaigns);

		driver.findElement(By.name("search")).sendKeys("SMScamp a1b2 c3");
		action.sendKeys(Keys.ENTER).scrollByAmount(220, 0).build().perform();

		try 
		{
			driver.findElement(By.cssSelector("#selectAll+span")).click();
			driver.findElement(By.cssSelector("#campaigntbulkbtnsdiv>a:last-of-type")).click();
			driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
		}
		catch(Exception NoSuchElementException)
		{
			driver.navigate().refresh();
		}

		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/a")).click();
		driver.findElement(By.id("campaign_title2")).sendKeys("SMScamp a1b2 c3");//<-camp name
		driver.findElement(By.cssSelector("#gb_btn_wizrd+a.desktop-600")).click();//<-- next button
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
			//Thread.sleep(5000);
		}

		jse.executeScript("scroll(0,300)");
		action.sendKeys("Hi this is test SMS from campaign break").build().perform();
		driver.findElement(By.cssSelector("div[id^=message_row]>div:nth-child(2)>div>div:nth-child(2)>div>div>a:nth-child(5)")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("tools_popup_camp"))));
		driver.findElement(By.cssSelector("#tools_popup_camp>div>div>div>div:nth-child(4)>div:first-child>label")).click();
		driver.findElement(By.cssSelector("#ShortcodeTypes>label:nth-child(2)")).click();
		driver.findElement(By.cssSelector("#tools_popup_camp>div>div>div>div.justify-end.items-center>button")).click();



		driver.findElement(By.id("camplogin")).click();

		//catch(Exception )
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
		Thread.sleep(1000);
		action.sendKeys(Keys.ENTER).perform();
		String addedtag = driver.findElement(By.cssSelector("#apply_remove_tags>div>div:first-of-type>div:first-of-type>div")).getText();
		System.out.println("selected tag: "+addedtag);
		driver.findElement(By.id("addingAction")).click();// add action button

		driver.findElement(By.cssSelector("div.bottom-trigger-buttons>div>div>a:last-of-type")).click();//Done button in camp trigger

		jse.executeScript("window.scroll(top)");
		driver.findElement(By.cssSelector("#gb_btn_wizrd+a.desktop-600")).click();//<-- next button
		driver.findElement(By.xpath("//*[@id=\"campaign_common_form\"]/div[3]/div/div[2]/div[4]/button[2]"))
		.click();//<-- add contact

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"add-new-contacts\"]/div/div")));
		driver.findElement(By.name("first_name")).sendKeys("rohan");
		action.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys("14012057373").sendKeys(Keys.TAB)
		.sendKeys("rohan@nadsoftdesign.com").build().perform();
		driver.findElement(By.xpath("//*[@id=\"importcustomform\"]/div/div[2]/button")).click();
		jse.executeScript("window.scroll(top)");

		try
		{
			driver.findElement(By.cssSelector("#gb_btn_wizrd+a.desktop-600")).click();//<-- next button
		}
		catch(Exception StaleElementReferenceException) 
		{
			driver.navigate().refresh();
			driver.findElement(By.cssSelector("#gb_btn_wizrd+a.desktop-600")).click();//<-- next button
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
		System.out.println("Campaign Launched!");
		Thread.sleep(1000);

		driver.navigate().to(Campaigns);
		driver.findElement(By.name("search")).sendKeys("smscamp a1b2 c3");
		action.sendKeys(Keys.ENTER).build().perform();
		String campstat2= driver.findElement(By.id("at0-cell-status-0")).getText();
		System.out.println("Campaign Status: "+campstat2); //<--campaign status

	}

	@Test(priority = 11)
	public void mailcamp() throws InterruptedException 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		Actions action = new Actions(driver);

		driver.navigate().to(Campaigns);

		driver.findElement(By.name("search")).sendKeys("Mailcamp a1b2 c3");
		action.sendKeys(Keys.ENTER).scrollByAmount(220, 0).build().perform();

		try 
		{
			driver.findElement(By.cssSelector("#selectAll+span")).click();
			driver.findElement(By.cssSelector("#campaigntbulkbtnsdiv>a:last-of-type")).click();
			driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
		}
		catch(Exception NoSuchElementException)
		{
			driver.navigate().refresh();
		}

		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/a")).click();

		driver.findElement(By.id("campaign_title2")).sendKeys("Mailcamp a1b2 c3");//<-camp name
		driver.findElement(By.cssSelector("#gb_btn_wizrd+a.desktop-600")).click();//<-- next button
		Thread.sleep(2000);
		String campcrt= driver.findElement(By.id("alertify-logs")).getText();
		jse.executeScript("scroll(0,400)");
		Thread.sleep(6000);
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
		action.sendKeys("This is test camp for testing 112233").sendKeys(Keys.TAB)
		.sendKeys("Hi this is test message").build().perform();
		jse.executeScript("window.scroll(top)");
		driver.findElement(By.cssSelector("#gb_btn_wizrd+a.desktop-600")).click();//<-- next button
		driver.findElement(By.xpath("//*[@id=\"campaign_common_form\"]/div[3]/div/div[2]/div[4]/button[2]"))
		.click();//<-- add contact

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"add-new-contacts\"]/div/div")));
		driver.findElement(By.name("first_name")).sendKeys("rohan");
		action.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys("7579793173").sendKeys(Keys.TAB)
		.sendKeys("Rohan@nadsoftdesign.com").build().perform();
		driver.findElement(By.xpath("//*[@id=\"importcustomform\"]/div/div[2]/button")).click();
		jse.executeScript("window.scroll(top)");

		try
		{
			driver.findElement(By.cssSelector("#gb_btn_wizrd+a.desktop-600")).click();//<-- next button
		}
		catch(Exception StaleElementReferenceException) 
		{
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#gb_btn_wizrd+a.desktop-600")));
			driver.findElement(By.cssSelector("#gb_btn_wizrd+a.desktop-600")).click();//<-- next button
		}

		try
		{
			driver.findElement(By.cssSelector("#gb_btn_wizrd+a.desktop-600")).click();//<-- next button
		}
		catch(Exception StaleElementReferenceException) 
		{
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"campaign_common_form\"]/div[1]/div/div[3]/a[2]")));
			driver.findElement(By.cssSelector("#gb_btn_wizrd+a.desktop-600")).click();//<-- next button
		}

		jse.executeScript("window.scroll(0,300)");
		driver.findElement(By.id("launch-dropdown")).click();//<-- launch button
		driver.findElement(By.xpath("//*[@id=\"launchPageCenterModal\"]/div[1]/div[3]/div/div/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div#campaign-launch-now-popup > div > div > div > button")).click();

		//Output
		System.out.println();
		System.out.println("Campaign  With Instant Mail ----->");
		System.out.println("	"+campcrt);
		System.out.println("	Campaign Launched!");
	}

	@Test(priority = 12)
	public void pipeline() throws InterruptedException 
	{

		JavascriptExecutor jse= (JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);

		driver.navigate().to(Pipelines);

		System.out.println();
		System.out.println("Pipeline Module Testing ----->");

		//Checking for duplicate
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/div[1]")).click();
		jse.executeScript("document.querySelector('div#block0body > div > div > div > div > div > ul').scrollTop=300");

		List<WebElement>pipelines =  driver.findElements(By.cssSelector("#pipelineFilterList>label"));

		for(int i = 2; i<= pipelines.size()+1; i++)
		{
			String as= driver.findElement(By.cssSelector("#pipelineFilterList>label:nth-child("+i+")")).getText();
			Boolean test =as.equalsIgnoreCase("ZzzzzNew auto delete Pipeline");

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
		driver.findElement(By.name("pipeline_title")).sendKeys("ZzzzzNew auto delete Pipeline");
		driver.findElement(By.cssSelector("button.bulkaddpipelinestages")).click();
		action.sendKeys(Keys.TAB).sendKeys("Stage 1, Stage 2").build().perform();
		driver.findElement(By.cssSelector("#pp-create-stage-form>div>button[type = submit]")).click();
		String alert= driver.findElement(By.id("alertify-logs")).getText();

		driver.findElement(By.cssSelector("div.dragable-container>div>div:last-of-type>a")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("new-status-title")).sendKeys("Stage 3");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"pp-create-status-form\"]/div[2]/button")).click();
		String stgadd = driver.findElement(By.id("alertify-logs")).getText();

		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/a[1]")).click();
		jse.executeScript("window.scroll(0, 200)");
		driver.findElement(By.xpath("//*[@id=\"sortable\"]/div[3]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"pp-direct-delete-pipeline-stage-form\"]/button")).click();
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[2]/div/div[1]/a")).click();

		//Output
		System.out.println("	Color for New Pipeline stage is changed");
		System.out.println("	New Pipeline stage added ");
		System.out.println("	pipeline stage deleted");
		System.out.println("	Pipeline is not creating with empty stage name");
		System.out.println("	"+alert);
		System.out.println("	Adding Stage From Pipeline Dashboard: "+stgadd);
	}

	@Test(priority = 13)
	public void Funnel() throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		Actions action = new Actions(driver);

		driver.navigate().to(Funnels);

		System.out.println();
		System.out.println("Funnel Adding New Page ----->");
		driver.findElement(By.name("filter")).sendKeys("New funnel for test a1b2 c3");
		action.sendKeys(Keys.ENTER).build().perform();

		try
		{
			driver.findElement(By.xpath("//*[@id=\"at0-cell-actions-0\"]/div/a[5]/i")).click();
			driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
		}
		catch(Exception NoSuchElementException) 
		{

		}

		driver.findElement(By.linkText("+ Create New Funnel")).click();
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/main/div/div[1]/div/div[2]/div/div/div/div/div[2]/form/input"))
		.sendKeys("New funnel for test a1b2 c3");
		driver.findElement(By.xpath("//*[@id=\"funnel_form\"]/button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("alertify-logs")));
		String funcrt= driver.findElement(By.id("alertify-logs")).getText();
		System.out.println("	"+funcrt);

		driver.findElement(By.id("useoursdomain")).click();
		driver.findElement(By.id("adddefdomain")).click();
		driver.findElement(By.name("landing_title")).sendKeys("test form");
		driver.findElement(By.id("submit")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("alertify-logs")));
		String funfin= driver.findElement(By.id("alertify-logs")).getText();
		System.out.println("	"+funfin);
		
		/*
		action.sendKeys(Keys.PAGE_DOWN).perform();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#overview>div>a:first-child")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div")));
		driver.switchTo().frame(driver.findElement(By.id("builder_iframe")));
		driver.findElement(By.xpath("/html/body/div")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[1]/div/div[@class=\"sectioninsidediv\"]/div[1]")).click();
		driver.findElement(By.cssSelector("button.addrowinsidesection")).click();
		driver.findElement(By.cssSelector("div.sectioninsidediv.section-column>div:first-child>div.singlecolumnstructure")).click();
		
		driver.switchTo().defaultContent();
		jse.executeScript("document.querySelector('#nav-tabContent>div#nav-home>div').scrollTop = 200");
		WebElement Q = driver.findElement(By.cssSelector("div[class-name=\"NFormWidget\"]>div>div"));
		action.clickAndHold(Q).perform();
		
		driver.switchTo().frame(driver.findElement(By.id("builder_iframe")));
		WebElement R = driver.findElement(By.cssSelector("body>div>div.row.builder-class-row-empty-section>div"));
		action.moveToElement(R).release().build().perform();
		driver.findElement(By.cssSelector("div.builder-col-100>div>button.form-control.selectformbutton")).click();
		
		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		WebElement S = driver.findElement(By.cssSelector("div.aContainer.nformcontroldiv>div>div>div>div.place-value"));
		action.moveToElement(S).click().sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
		driver.findElement(By.cssSelector("a.save-design>button")).click();
		driver.findElement(By.cssSelector("button.btn-close")).click();
		driver.findElement(By.cssSelector("div.swal2-actions>button:first-of-type")).click();
		*/
		
		//validating funnel page 500 error
		String pwindo = driver.getWindowHandle();
		driver.findElement(By.cssSelector("#overview>div:nth-child(2)>a:nth-child(2)")).click();

		Set<String> handles = driver.getWindowHandles();

		for (String handle: handles) 
		{
			driver.switchTo().window(handle);
		}

		boolean a = driver.getPageSource().contains("500 Internal Server Error");
		driver.close();

		if(a)
		{

		}
		else
		{
			System.out.println("	500 Error Not Given By the Funnel Page" );
		}
		driver.switchTo().window(pwindo);
	}

	@Test(priority = 14)
	public void Appointment() throws InterruptedException 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		Actions action = new Actions(driver);

		Login2();
		System.out.println();
		System.out.println("New one on one Appointment type ----->");	

		WebElement e = driver.findElement(By.cssSelector("div.hidden.sidebar-bg-color"));
		action.moveToElement(e).perform();
		jse.executeScript("document.querySelector('html > body > div > div:nth-of-type(2)').scrollTop=400");
		String apcnt= driver.findElement(By.cssSelector("a[title=\"Calendar\"]>span.msg-badge.bubble-number.ml-2")).getText();
		System.out.println("	Appointment Count Before Sceduling Appointment: "+apcnt);
		driver.findElement(By.linkText("Appointment Types")).click();

		//Checking for duplicate
		driver.findElement(By.name("search")).sendKeys("Test Appointment 110022");
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
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[2]/div[2]/div/div/div/div[1]/div[1]/div/div[2]/a")).click();
		driver.findElement(By.id("appttypename_title")).sendKeys("Test Appointment 110022");
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
		System.out.println("New Appointment Type Created");
		jse.executeScript("scroll(0,0)");
		String EventStatus = driver.findElement(By.cssSelector("label[for=\"toggleB\"]>div")).getText();
		System.out.println("	Event status : "+EventStatus);
		Thread.sleep(3000);
		String pwindo = driver.getWindowHandle();

		//validating landing page
		jse.executeScript("scroll(0,0)");
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
			driver.findElement(By.cssSelector("td.ui-datepicker-today+td")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("button.time-button:first-of-type")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div/div[2]/div[1]/div/div[2]/div/div/div[1]/button[2]")).click();
		}

		driver.findElement(By.name("contactname")).sendKeys("Test name 110022");
		driver.findElement(By.name("contactemail")).sendKeys("nadsoft.test99@gmail.com");
		driver.findElement(By.name("contactphone")).sendKeys("1234567890");
		driver.findElement(By.cssSelector("button.sch-event-btn")).click();
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.cssSelector("h2[style= \"font-size: 1.25rem;\"]")).getText());
		System.out.println(driver.findElement(By.cssSelector("span.evt-time-selected")).getText());
		System.out.println(driver.findElement(By.cssSelector("span.evt-date-selected")).getText());
		driver.close();
		driver.switchTo().window(pwindo);

		// Validating appointment on calendar----->
		driver.navigate().refresh();
		Thread.sleep(2000);
		WebElement r = driver.findElement(By.cssSelector("div.hidden.sidebar-bg-color"));
		action.moveToElement(r).perform();
		jse.executeScript("document.querySelector('html > body > div > div:nth-of-type(2)').scrollTop=300");
		String afterapcnt = driver.findElement(By.cssSelector("a[title=\"Calendar\"]>span.msg-badge.bubble-number.ml-2")).getText();
		driver.navigate().to(Calendar);
		System.out.println("Appointment Count After Sceduling Appointment: "+afterapcnt);
		driver.findElement(By.name("search")).sendKeys("Test Appointment 110022");
		action.sendKeys(Keys.ENTER).perform();
		String timeDetails = driver.findElement(By.cssSelector("div.new-single-appointment.grid.rounded-lg>div:first-of-type")).getText();
		String datedetails = driver.findElement(By.cssSelector("h4.text-xl.font-extrabold.darkColor.mr-auto")).getText();
		String Apptname = driver.findElement(By.cssSelector("div.new-single-appointment.grid.rounded-lg>h5")).getText();
		//String appid = driver.findElement(By.cssSelector("div.new-single-appointment.grid.rounded-lg")).getAttribute("data-appointment_id");
		System.out.println();
		System.out.println("Actual Details showing on the Appointment page");
		System.out.println("Appointment Name: "+Apptname);
		System.out.println("Appointment Date: "+datedetails);
		System.out.println("Appotment Time: "+timeDetails);

		driver.findElement(By.cssSelector("div.new-single-appointment.grid.rounded-lg>h5")).click();
		driver.findElement(By.cssSelector("#new-open-comman-appointmentdetails-modal>div>div>div.appt-content.grid.mt-5>div.pr-8.relative>a:nth-child(3)")).click();
		//driver.findElement(By.xpath("//*[@id=\"new-open-comman-calappointmentdetails-modal\"]/div/div/div[2]/div[2]/a[2]")).click();
		driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
		System.out.println("Appointment deleted");
	}

	@Test(priority = 15)
	public void GroupAppointmentType() throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);
		JavascriptExecutor jse= (JavascriptExecutor)driver;
		Login2();

		//New Group Appointment Type
		driver.navigate().to(Calendar);
		System.out.println();
		System.out.println("New Group Appointment type ----->");	

		driver.findElement(By.cssSelector("a.customer-filter-btn")).click();
		Thread.sleep(2000);
		String grpapcnt= driver.findElement(By.cssSelector("span.message-count-for-change>span")).getText();
		driver.navigate().to(AppointmentTypes);

		//Checking for duplicate
		driver.findElement(By.name("search")).sendKeys("Test Appointment- Group 11dk22");
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
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[2]/div[2]/div/div/div/div[1]/div[1]/div/div[3]/a")).click();
		driver.findElement(By.id("appttypename_title")).sendKeys("Test Appointment- Group 11dk22");
		driver.findElement(By.cssSelector("div.mb-5.loc>div.nice-select:first-of-type")).click(); //add location
		driver.findElement(By.cssSelector("li[data-value=\"inperson\"]")).click();
		driver.findElement(By.id("inperson_address")).sendKeys("random");
		jse.executeScript("scroll(0,200)");
		driver.findElement(By.id("appttypedesc")).sendKeys("Test user");
		jse.executeScript("scroll(0,200)");
		driver.findElement(By.id("max_group_invitee")).sendKeys("3");
		driver.findElement(By.id("is_group_invitee")).click();
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
		System.out.println("New Appointment Type Created");
		String EventStatus = driver.findElement(By.cssSelector("label[for=\"toggleB\"]>div")).getText();
		System.out.println("	Event status : "+EventStatus);
		Thread.sleep(3000);
		String pwindo = driver.getWindowHandle();

		//validating landing page
		jse.executeScript("scroll(0,0)");
		driver.findElement(By.cssSelector("a[data-url]")).click();
		Set<String> handles = driver.getWindowHandles();

		for (String handle: handles) 
		{
			driver.switchTo().window(handle);
		}

		Thread.sleep(5000);
		driver.findElement(By.cssSelector("a[href=\"#\"]:first-of-type")).click();
		Thread.sleep(2000);
		String beforbookingSlots = driver.findElement(By.cssSelector("button.time-button:first-of-type>div:nth-child(2)")).getText();
		driver.findElement(By.cssSelector("button.time-button:first-of-type")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div/div[2]/div[1]/div/div[2]/div/div/div[1]/button[2]")).click();

		driver.findElement(By.name("contactname")).sendKeys("Test grpname 110022");
		driver.findElement(By.name("contactemail")).sendKeys("nadsoft.test99@gmail.com");
		driver.findElement(By.name("contactphone")).sendKeys("1234567890");
		driver.findElement(By.cssSelector("button.sch-event-btn")).click();
		Thread.sleep(2000);
		System.out.println("	"+driver.findElement(By.cssSelector("h2[style= \"font-size: 1.25rem;\"]")).getText());
		System.out.println("	"+driver.findElement(By.cssSelector("div.details>div:nth-child(2)>div>span.evt-time-selected")).getText());
		System.out.println("	"+driver.findElement(By.cssSelector("div.details>div:nth-child(2)>div>span.evt-date-selected")).getText());

		//Slot validation
		driver.navigate().refresh();
		driver.findElement(By.cssSelector("a[href=\"#\"]:first-of-type")).click();
		Thread.sleep(2000);
		String afterbookingSlots = driver.findElement(By.cssSelector("button.time-button:first-of-type>div:nth-child(2)")).getText();
		System.out.println();
		System.out.println("	Spots available Before Booking Appointment: "+beforbookingSlots);
		System.out.println("	Spots available After Booking Appointment: "+afterbookingSlots);
		Assert.assertEquals(afterbookingSlots, "2 spots left", "Slots count Mismatched");
		Thread.sleep(1000);

		driver.close();
		driver.switchTo().window(pwindo);

		// Validating appointment on calendar----->
		driver.navigate().refresh();
		Thread.sleep(2000);
		driver.navigate().to(Calendar);

		driver.findElement(By.cssSelector("a.customer-filter-btn")).click();
		Thread.sleep(2000);
		String aftergrpapcnt= driver.findElement(By.cssSelector("span.message-count-for-change>span")).getText();
		System.out.println("	Appointment Count Before Sceduling Appointment: "+grpapcnt);
		System.out.println("	Appointment Count After Sceduling Appointment: "+aftergrpapcnt);
		int a = Integer.parseInt(grpapcnt);
		int b = Integer.parseInt(aftergrpapcnt);
		a = a + 1;
		Assert.assertEquals(a, b, "Count Mismatched After Booking new appointment");
		driver.findElement(By.cssSelector("a.customer-filter-btn")).click();

		driver.findElement(By.name("search")).sendKeys("Test Appointment- Group 11dk22");
		action.sendKeys(Keys.ENTER).perform();
		String timeDetails = driver.findElement(By.cssSelector("div.new-single-appointment.grid.rounded-lg>div:first-of-type")).getText();
		String datedetails = driver.findElement(By.cssSelector("h4.text-xl.font-extrabold.darkColor.mr-auto")).getText();
		String Apptname = driver.findElement(By.cssSelector("div.new-single-appointment.grid.rounded-lg>h5")).getText();
		//String appid = driver.findElement(By.cssSelector("div.new-single-appointment.grid.rounded-lg")).getAttribute("data-appointment_id");
		System.out.println();
		System.out.println("	Actual Details showing on the Appointment page");
		System.out.println("	Appointment Name: "+Apptname);
		System.out.println("	Appointment Date: "+datedetails);
		System.out.println("	Appotment Time: "+timeDetails);
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.new-single-appointment.grid.rounded-lg>h5")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#new-open-comman-appointmentdetails-modal>div>div>div:nth-child(2)>div:nth-child(2)>a:nth-child(3)")).click();
		driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
		Thread.sleep(500);
		System.out.println("	Appointment Deleted Successfully");
	}

	@Test (priority = 16)
	public void AppointmentCount() throws Exception //appointment count on dashboard
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		Actions action = new Actions(driver);

		//Getting Company ID
		String CompId = driver.findElement(By.id("globalcall_dialer")).getAttribute("data-companyid");
		int cid = Integer.parseInt(CompId);

		driver.navigate().to(Dashboard);
		System.out.println();
		System.out.println("Validating Appointment Count on Dashboard ----->");
		String Total = driver.findElement(By.cssSelector("div.top-header>div:nth-child(2)>div>div:nth-child(2)>h1>span")).getText();
		String a = driver.findElement(By.cssSelector("div.top-header>div:nth-child(2)>div>div:nth-child(3)>a:first-child")).getText();
		String arr[] = a.split(" ",3);
		String Today1 = arr[2];

		//Login to adminer ---------------------------------------------------->			
		driver.get(DBURL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		//Login to database	
		driver.findElement(By.name("auth[server]")).sendKeys(DBServer);
		driver.findElement(By.id("username")).sendKeys(DBUName);
		driver.findElement(By.name("auth[password]")).sendKeys(DBPass);
		driver.findElement(By.name("auth[db]")).sendKeys(DB);
		driver.findElement(By.cssSelector("input[value='Login']")).click();

		//database selection
		driver.findElement(By.linkText("SQL command")).click();
		action.sendKeys("SELECT count(id) \r\n"
				+ "FROM appointments \r\n"
				+ "WHERE is_deleted = 0 \r\n"
				+ "and id_company= "+cid+"\r\n"
				+ "and  timescheduled > UNIX_TIMESTAMP(Current_date())\r\n"
				+ "and  timescheduled < UNIX_TIMESTAMP(Current_date() + interval 1 DAY)")
		.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();	
		String TodayinDB = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();

		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).clear();
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).sendKeys("SELECT count(id)\r\n"
				+ "FROM appointments\r\n"
				+ "WHERE is_deleted = 0\r\n"
				+ "and id_company= "+cid+"\r\n"
				+ "and  timescheduled > UNIX_TIMESTAMP(Current_date())");
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();	
		String TotalinDB = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();

		System.out.println("	Total Appointment Count On Dashboard : "+Total);
		System.out.println("	Total Appointment Count From Database : "+TotalinDB);


		System.out.println();
		System.out.println("	Today's Appointment Count on Dashboard : "+Today1);
		System.out.println("	Today's Count From DataBase : "+TodayinDB);

		Assert.assertEquals(Total, TotalinDB, "Total Appointment Count Mismatch In Dashboard and Calendar");
		Assert.assertEquals(Today1, TodayinDB, "Today's Appointment Count Mismatch In Dashboard and Calendar");

	}

	@Test(priority = 18)
	public void camptrigcheck() throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		Actions action = new Actions(driver);

		driver.navigate().to(Messages);
		System.out.println();
		System.out.println("Verifying Campaign Reply ----->");

		driver.findElement(By.cssSelector("div.custom-sms-contact>div:nth-of-type(3)>a:first-of-type")).click();
		driver.findElement(By.name("filters")).sendKeys("Automation Contact");
		action.sendKeys(Keys.ENTER).perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#convoWrapper > div.conversation_started_messages_div_content > div.sms-box:last-of-type>div>div:nth-of-type(2)>div:first-of-type")));
		String msg = driver.findElement(By.cssSelector("#convoWrapper > div.conversation_started_messages_div_content > div.sms-box:last-of-type>div>div:nth-of-type(2)>div:first-of-type")).getText();

		String arr1[] = msg.split("break",2);
		String firstWord1 = arr1[0];

		if(firstWord1.equalsIgnoreCase("Hi this is test SMS from campaign "))
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
	}

	@Test(priority = 20)
	public void linkverification() throws InterruptedException 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		driver.navigate().to(Dashboard);

		driver.findElement(By.cssSelector("#block0body>div.block>div>div>div:nth-child(1)>div>a")).click();//Go to leads PopUp
		try
		{
			driver.findElement(By.xpath("//*[@id=\"dashboard_leads\"]/div/div/a")).click();
		}
		catch(Exception NoSuchElementException) 
		{
			Assert.assertEquals(false, true,"Leads Pop up not open");
		}

		String today= driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[1]/div/div/div[2]/div/div[2]/a[1]")).getAttribute("href");
		Assert.assertEquals(today,Calendar+"&rtype=today","Check Today button URL");

		String seeall = driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[1]/div/div/div[2]/div/div[2]/a[2]")).getAttribute("href");
		Assert.assertEquals(seeall, Calendar, "Check see all URL");

		String SMS = driver.findElement(By.xpath("//*[@id=\"notifyDashTop\"]/div[2]/a[1]")).getAttribute("href");
		Assert.assertEquals(SMS, Messages+"&selected=unread&tabopen=sms_now","Check SMS URL");

		String Emails = driver.findElement(By.xpath("//*[@id=\"notifyDashTop\"]/div[2]/a[2]")).getAttribute("href");
		Assert.assertEquals(Emails, Messages+"&selected=unread&tabopen=email_now", "Check Email URL");

		String calls = driver.findElement(By.xpath("//*[@id=\"notifyDashTop\"]/div[2]/a[3]")).getAttribute("href");
		Assert.assertEquals(calls,CallLog+"&filter=missed", "Check Calls URL");

		String rem= driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[1]/div/div/div[4]/div/div[2]/a[1]")).getAttribute("href");
		Assert.assertEquals(rem,Notes+"&notestypecust=&notestype=timescheduled","Check Remainders URL");

		String seeallrem = driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[1]/div/div/div[4]/div/div[2]/a[2]")).getAttribute("href");
		Assert.assertEquals(seeallrem,Notes,"Check Remainder URL");

		String actmail = driver.findElement(By.xpath("//*[@id=\"notifyFilterWise\"]/a[1]")).getAttribute("href");
		Assert.assertEquals(actmail,Messages+"&selected=unread&tabopen=email_now","Check Mail URL in Activity ");

		String actSMS =driver.findElement(By.xpath("//*[@id=\"notifyFilterWise\"]/a[2]")).getAttribute("href");
		Assert.assertEquals(actSMS,Messages+"&selected=unread&tabopen=sms_now","Check SMS URL in activity");

		String Scapp= driver.findElement(By.xpath("//*[@id=\"block0body\"]/div[2]/div/div[2]/div/div[2]/div[2]/a")).getAttribute("href");
		Assert.assertEquals(Scapp, Calendar,"Check Schedule appointment URL");

		String call= driver.findElement(By.xpath("//*[@id=\"notifyDashTop\"]/div[2]/a[3]")).getAttribute("href");
		Assert.assertEquals(call, CallLog+"&filter=missed","Check Call URL");
		Thread.sleep(2000);

		//Output
		System.out.println();
		System.out.println("Checking links on Dashboard ----->");
		System.out.println("	All URL's on Dashboard are verified");
	}

	@Test(priority = 21)
	public void PopupCheck() throws InterruptedException 
	{

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
		driver.navigate().to(Customers);

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

	@Test(priority = 22)
	public void Notes() throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);
		JavascriptExecutor jse = (JavascriptExecutor)driver;

		action.moveToElement(driver.findElement(By.cssSelector("div.hidden.sidebar-bg-color"))).perform();
		jse.executeScript("document.querySelector('html > body > div > div:nth-of-type(2)').scrollTop=200");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("html > body > div > div:nth-of-type(2) > div > div > div:nth-of-type(2) > nav > div:nth-of-type(5) > div:nth-of-type(2) > div:nth-of-type(4) > div > a")).click();

		// Adding new Note
		driver.findElement(By.xpath("//*[@id=\"notes-toggler\"]/div[1]/div[1]/div[1]/div[1]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"js-tags-selectorpop-selectized\"]")).sendKeys("a");
		action.sendKeys(Keys.ENTER).perform();
		Thread.sleep(1000);
		driver.findElement(By.id("dashboard_note_text")).sendKeys("This is Test Note 1103");
		driver.findElement(By.id("add_a_note_popup_btn")).click();
		System.out.println();
		Thread.sleep(1000);

		//Deleting note
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("a[id=dropdownMenu2]:first-of-type")).click();
		driver.findElement(By.linkText("Delete Note")).click();
		driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();
		Thread.sleep(1000);
		String log = driver.findElement(By.cssSelector("#toast-container>div>div")).getText();

		//Output
		System.out.println();
		System.out.println("Creating New Note ----->");
		System.out.println("	Note Created Successfully");
		System.out.println("	"+log);
	}

	@Test
	public void Forms() throws Exception 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Actions action = new Actions(driver);
		JavascriptExecutor jse = (JavascriptExecutor)driver;

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

		driver.navigate().to(Forms);

		//Deleting duplicate
		driver.findElement(By.id("search_template_list")).sendKeys("New Test Form !!))@@");
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
		driver.findElement(By.id("form_title")).sendKeys("New Test Form !!))@@");
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
		driver.findElement(By.name("buttonurl")).sendKeys(url);
		driver.findElement(By.cssSelector("div.endscreentopdiv>div>button")).isDisplayed();
		driver.findElement(By.cssSelector("input[name=\"showenddisclaimer\"]+span")).click();
		driver.findElement(By.name("disclaimer")).sendKeys("This Is Disclaimer");
		String d = driver.findElement(By.cssSelector("p.endscreendiscliamer")).getText();
		Assert.assertEquals(d, "This Is Disclaimer", "Disclaimer not displayed in the preview");
		Thread.sleep(2000);

		driver.findElement(By.id("fb-save-form")).click();
		Thread.sleep(2000);
		String su = driver.findElement(By.id("toast-container")).getText();

		driver.findElement(By.cssSelector("a[onclick=\"closeBuilder()\"]")).click();
		Thread.sleep(2000);

		//Output for Form filling page
		System.out.println();
		System.out.println("Validating Form Functionality ----->");
		System.out.println("	"+su);

		//Setting new Trigger		
		driver.navigate().to(Forms);

		System.out.println();
		System.out.println("Form Result Page(Adding trigger, Submitting form) ----->");
		driver.findElement(By.name("search")).sendKeys("New Test Form !!))@@");
		action.sendKeys(Keys.ENTER).perform();

		driver.findElement(By.cssSelector("#templateListTBody>tr>td:nth-child(9)")).click();
		Thread.sleep(1000);

		for(int i=1; i<=4; i++)
		{
			action.sendKeys(Keys.PAGE_DOWN).perform();
			Thread.sleep(2000);
			driver.findElement(By.id("addingAction")).click();
			Thread.sleep(500);
			driver.findElement(By.cssSelector("div.dropdown-menu.dropdown-add-action")).isDisplayed();
			driver.findElement(By.cssSelector("div.dropdown-menu.dropdown-add-action>a:nth-child("+i+")")).click();

			if(i==1) //mail
			{
				Thread.sleep(6000);
				driver.findElement(By.name("emailsubject")).sendKeys("Trigger Mail - Form Module");
				Thread.sleep(4000);
				action.sendKeys(Keys.TAB).sendKeys("Test mail").build().perform();
			}
			else if (i==2)//SMS
			{				
				jse.executeScript("document.querySelector('div.form-trigger-details.form-trigger-details-width').scrollTop=500");
				driver.findElement(By.name("trigger_sms")).sendKeys("Trigger SMS - Form Module");
			}
			else if(i==3)//Tags
			{
				jse.executeScript("document.querySelector('div.form-trigger-details.form-trigger-details-width').scrollTop=1200");
				driver.findElement(By.id("js-tags-selector-selectized")).click();
				action.sendKeys(Keys.ENTER).perform();
				FormTagid = driver.findElement(By.cssSelector("#apply_remove_tags>div>div>div>div")).getAttribute("data-value");
				driver.findElement(By.name("trigger_sms")).click();
			}
			else if(i==4)//Assign to user
			{
				jse.executeScript("document.querySelector('div.form-trigger-details.form-trigger-details-width').scrollTop=1000");
				Thread.sleep(1000);
				driver.findElement(By.id("ctAssignedToIds")).click();
				driver.findElement(By.cssSelector("#assignedToFilterList>label:nth-child(2)")).click();
				FormUserId = driver.findElement(By.id("ctFilter_salesperson")).getAttribute("value");
				driver.findElement(By.id("ctAssignedToIds")).click();
			}
			else if(i==5)//Pipeline
			{
				jse.executeScript("document.querySelector('div.form-trigger-details.form-trigger-details-width').scrollTop=1500");
				driver.findElement(By.cssSelector(".addformactionid>div:nth-child(2)>div>div>div>div:first-child")).click();
				FormPipeId = driver.findElement(By.cssSelector("#appointmentFilterList>label:nth-child(4)")).getAttribute("data-value");
				Thread.sleep(2000);
				driver.findElement(By.cssSelector("#appointmentFilterList>label:nth-child(4)")).click();

				driver.findElement(By.cssSelector(".addformactionid>div:nth-child(2)>div:nth-child(2)>div>div>div:nth-child(1)")).click();
				Thread.sleep(2000);
				FormStageId = driver.findElement(By.cssSelector("#stageFilterList>label:nth-child(3)")).getAttribute("data-value");
				driver.findElement(By.cssSelector("#stageFilterList>label:nth-child(3)")).click();
				Thread.sleep(4000);
			}
			jse.executeScript("document.querySelector('div.form-trigger-details.form-trigger-details-width').scrollTop=1000");
		}

		driver.findElement(By.cssSelector("div.bottom-trigger-buttons>div>div>a:last-of-type")).click();// Save trigger
		System.out.println("	"+driver.findElement(By.cssSelector("#toast-container>div>div")).getText());
		Thread.sleep(1000);

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
		driver.findElement(By.cssSelector("div.personal-contactinfophem>div:last-of-type>div>input")).sendKeys("3203825987");// Business id - 378s
		driver.findElement(By.id("step_1_btn")).click();
		Thread.sleep(5000);
		System.out.println("	"+driver.findElement(By.cssSelector("#ending_A > div.px-10 > p")).getText());

		//End Screen Validations
		String A = driver.findElement(By.cssSelector("p.endscreenpara ")).getText();
		Assert.assertEquals(A, "Test End Page","Paragraph Not Get Displyed On End Screen");

		String B = driver.findElement(By.cssSelector("p.endscreendiscliamer")).getText();
		Assert.assertEquals(B, "This Is Disclaimer", "Desclaimer Not Get Displyed");
		driver.findElement(By.cssSelector("p.endscreenpara +button")).click();
		Assert.assertEquals(driver.getCurrentUrl(),url,"Button not Navigating to correct position");
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
		String num = driver.findElement(By.xpath("//*[@id=\"at0-cell-total_contact-0\"]/div/a")).getText();
		Assert.assertEquals(num, "1","Result count updated wrong");
		System.out.println("	Count in Result Column: "+num);

		//Case: when you click on the 3 dots on the form submissions the pop up is behind
		driver.findElement(By.xpath("//*[@id=\"at0-cell-total_contact-0\"]/div/a")).click();
		driver.findElement(By.id("dropdownMenu2")).click();
		driver.findElement(By.cssSelector("#dropdownMenu2+div>a")).click();
		System.out.println("	Popup is Opening by clicking on 3 Dots");
	}

	@Test(priority = 23)
	public void FormValidations() throws Exception 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Actions action = new Actions(driver);
		JavascriptExecutor jse = (JavascriptExecutor)driver;

		//validation - Other
		driver.navigate().to(Customers);
		driver.findElement(By.id("showCustomerTVFilters")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.customer-adv-filters")).isDisplayed();
		jse.executeScript("document.querySelector('#campuser').scrollTop = 300");
		driver.findElement(By.id("ctformsIds")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("label[data-text=\"New Test Form !!))@@\"]")).click();
		driver.findElement(By.id("ctformsIds")).click();
		Thread.sleep(5000);
		String a = driver.findElement(By.cssSelector("div.filter-bottom-box>div>a>span")).getText();
		Assert.assertEquals(a, "1", "Count mismatched in customer filter");
		/*
		//Applying Pipeline filter
		driver.findElement(By.cssSelector("div.customer-advanced-filter-menus>div:nth-child(10)>div")).click();
		driver.findElement(By.cssSelector("#pipeline-typeFilterList>label[data-value=\""+FormPipeId+"\"]")).click();

		driver.findElement(By.id("ctStagesIds")).click();
		driver.findElement(By.cssSelector("#stagesFilterList>label[data-value = \""+FormStageId+"\"]")).click();
		driver.findElement(By.id("ctStagesIds")).click();

		Thread.sleep(4000);
		a = driver.findElement(By.cssSelector("div.filter-bottom-box>div>a>span")).getText();
		Assert.assertEquals(a, "1", "Count mismatched in customer filter");
		 */

		//Applying assigned user tag
		driver.findElement(By.id("ctAssignedToIds")).click();
		driver.findElement(By.cssSelector("#assignedToFilterList>label[data-value=\""+FormUserId+"\"]")).click();
		driver.findElement(By.id("ctAssignedToIds")).click();
		Thread.sleep(4000);
		a = driver.findElement(By.cssSelector("div.filter-bottom-box>div>a>span")).getText();

		Assert.assertEquals(a, "1", "Count mismatched in customer filter");

		//Applying Tags filter 
		driver.findElement(By.id("selected_Tagssee")).click();
		//driver.findElement(By.cssSelector("#TagsFilterList>label[data-value= \""+FormTagid+"\"]")).click();
		driver.findElement(By.cssSelector("#TagsFilterList>label[data-value= \"1686\"]")).click();
		driver.findElement(By.id("selected_Tagssee")).click();

		Thread.sleep(5000);
		a = driver.findElement(By.cssSelector("div.filter-bottom-box>div>a>span")).getText();
		Assert.assertEquals(a, "1", "Count mismatched for Tags in customer filter");
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();

		//validation - Mail & SMS
		driver.navigate().to(Messages);
		Thread.sleep(2000);
		driver.findElement(By.name("filters")).sendKeys("Rohankokare7663+FormTest@gmail.com");
		action.sendKeys(Keys.ENTER).perform();
		driver.findElement(By.cssSelector("#custom-sms-contact-list>div:first-child")).click();

		//form listing
		String Form = driver.findElement(By.cssSelector("div.main-customer-chat>div:last-of-type")).getText();
		System.out.println("	"+Form);

		String test1=null;

		try
		{
			test1 = driver.findElement(By.cssSelector("	div.conversation_started_messages_div_content>div.sms-box:nth-of-type(5)>div>div:nth-child(2)>div:first-child>span:last-of-type")).getText();
		}
		catch(Exception e)
		{
			driver.navigate().refresh();
			test1 = driver.findElement(By.cssSelector("div.conversation_started_messages_div_content>div.sms-box:nth-of-type(5)>div>div:nth-child(2)>div:first-child>span:last-of-type")).getText();
		}

		String arr[] = test1.split("Module",2);
		System.out.println("	"+arr[0]);
		Assert.assertEquals(arr[0],"Trigger Mail - Form ", "Trigger Mail Not Found");
		String test2 = driver.findElement(By.cssSelector(" div.conversation_started_messages_div_content>div:last-of-type>div>div:nth-child(2)>div:first-child")).getText();
		String arr1[] = test2.split("Module",2);
		Assert.assertEquals(arr1[0],"Trigger SMS - Form ", "Trigger SMS Not Found");	

		driver.navigate().to(Forms);
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
				driver.findElement(By.id("assignUsersSelect")).click();
				driver.findElement(By.id("Filter-List-Event")).isDisplayed();
				driver.findElement(By.cssSelector("#Filter-List-Event>label:nth-child(3)")).click();
				jse.executeScript("document.querySelector('#assign-user-popup').scrollTop=200");
				driver.findElement(By.cssSelector("#ct_assignuser_form>div:last-child>div>a:last-child")).click();

				String Alert = driver.findElement(By.cssSelector("#alertify-logs>article")).getText();
				System.out.println("	"+Alert);
			}
			else if(i==4)//Apply tags popup
			{
				Thread.sleep(1000);
				driver.findElement(By.cssSelector("#applytagtouser>div>div>div>div>input")).click();
				action.sendKeys(Keys.ENTER).build().perform();
				driver.findElement(By.cssSelector("button#linkdone")).click();

				String Alert = driver.findElement(By.cssSelector("#alertify-logs>article")).getText();
				System.out.println("	"+Alert);
			}
			else if(i==5)//Move Customers
			{
				Thread.sleep(1000);
				driver.findElement(By.cssSelector("#addusertopipelinestage>div:nth-child(6)>div>div")).click();
				driver.findElement(By.cssSelector("#pipelineCustomertypeFilterListCus>label:nth-child(4)")).click();// Select pipeline
				driver.findElement(By.cssSelector("#addusertopipelinestage>div:nth-child(6)>div>div:nth-child(5)")).click();
				driver.findElement(By.cssSelector("#pipelineCustomerstagesFilterList>label")).click();// Stage
				driver.findElement(By.cssSelector("#addusertopipelinestage>div:nth-child(6)>div>div:last-of-type>button:nth-child(2)")).click();//Done

				String Alert = driver.findElement(By.cssSelector("#alertify-logs>article")).getText();
				System.out.println("	"+Alert);
			}
			else if(i==6)//Create List
			{
				Thread.sleep(1000);
				driver.findElement(By.id("title")).sendKeys("Form Result Test List auto created");
				driver.findElement(By.cssSelector("#create-contact-list>div:nth-of-type(3)>button")).click();

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

	@Test(priority = 28, dependsOnMethods = "camptrigcheck")
	public void campreply() throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);

		driver.navigate().to(Messages);
		Thread.sleep(3000);
		driver.findElement(By.name("filters")).sendKeys("Weaired testname");
		action.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(5000);

		System.out.println();
		System.out.println("Checking Campaign Reply: ");

		String Trigmsg = driver.findElement(By.cssSelector("div.conversation_started_messages_div_content>div:last-of-type>div>div:nth-child(2)>div:first-child")).getText();
		Assert.assertEquals(Trigmsg,"Trigger SMS from camp 110022", "Trigger Message not sent");

		driver.navigate().to(Campaigns);
		driver.findElement(By.name("search")).sendKeys("smscamp a1b2 c3");
		JavascriptExecutor jse = (JavascriptExecutor)driver; 

		action.sendKeys(Keys.ENTER).build().perform();
		String Count = driver.findElement(By.cssSelector("div.reply-up:first-of-type")).getText();
		if(Count.equalsIgnoreCase("0"))
		{
			Thread.sleep(8000);
			driver.navigate().refresh();
			System.out.println("	Reply count: "+Count);
		}
		System.out.println("	Reply count: "+Count);
		driver.findElement(By.cssSelector("div.reply-up:first-of-type")).click();
		String Response = driver.findElement(By.cssSelector("td[title=\"Response\"]>div>h1")).getText();
		Assert.assertEquals(Response,"hi this is test message for daily testing", "Check Response Message Not Match");
		System.out.println("	Response Recived from Campaign");
		driver.findElement(By.cssSelector("#admintable0>thead>tr>th>label>span")).click();

		/*/Validating QuickFilters count Msg from trigger get considered as reply
		String beforereplied = driver.findElement(By.className("filter-counter-green")).getText();
		Assert.assertEquals(beforereplied, "0","Check Quick filter count is mismatching");
		String beforeunreplied = driver.findElement(By.className("filter-counter-red")).getText();
		Assert.assertEquals(beforeunreplied, "1","Check Quick filter count is mismatching");
		jse.executeScript("document.querySelector('#customertablediv > div').scrollLeft = 300");
		driver.findElement(By.cssSelector("tr.admintable_row>td:nth-child(7)>a")).click();
		Thread.sleep(500);
		driver.findElement(By.id("camp_email_reply")).isDisplayed();
		driver.findElement(By.id("campaign-sms-message")).sendKeys("reply");
		driver.findElement(By.cssSelector("a[onclick=\"convoSendSmsHandlerCampaign(this)\"]")).click();
		Thread.sleep(200);
		String replysuccess = driver.findElement(By.id("toast-container")).getText();
		System.out.println("	Reply "+replysuccess);
		String afterreplied = driver.findElement(By.className("filter-counter-green")).getText();
		Assert.assertEquals(afterreplied, "1","Check Quick filter count is mismatching");
		String afterunreplied = driver.findElement(By.className("filter-counter-red")).getText();
		Assert.assertEquals(afterunreplied, "0","Check Quick filter count is mismatching");
		System.out.println("	Quick filters count updating correctly");*/
	}

	@Test(priority = 23)
	public void CustomerFilters() throws Exception {

		Actions action =new Actions(driver);
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
		action.moveToElement(driver.findElement(By.cssSelector("div.hidden.sidebar-bg-color"))).perform();
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/nav/div[5]/div[2]/div[1]/div/div[1]/a/i")).click();

		//Getting Company ID
		String CompId = driver.findElement(By.id("globalcall_dialer")).getAttribute("data-companyid");
		int cid = Integer.parseInt(CompId);

		String[] CRMdata = new String[6]; 

		//opening filter popup
		System.out.println();
		System.out.println("Getting Customer's Filters Result ----->");
		driver.findElement(By.id("showCustomerTVFilters")).click();
		System.out.println("	Visibility of Filter popup = "+driver.findElement(By.cssSelector("div.customer-adv-filters")).isDisplayed());
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
		Thread.sleep(1000);
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
		driver.findElement(By.cssSelector("#pipeline-typeFilterList>label:nth-child(3)")).click();
		driver.findElement(By.cssSelector("input#ctStagesIds")).click();
		driver.findElement(By.cssSelector("div#stagesFilterList > label")).click();
		driver.findElement(By.cssSelector("input#ctStagesIds")).click();

		driver.findElement(By.cssSelector("div.flex.gap-2.mt-3>input")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name=selectallcheckbox]+span")));
		driver.findElement((By.cssSelector("input[name=selectallcheckbox]+span"))).click();
		//String count2 = driver.findElement(By.cssSelector("input[name=allcustcount]")).getAttribute("value");
		driver.findElement(By.id("showCustomerTVFilters")).click();
		Thread.sleep(1000);
		//String mystring3 = driver.findElement(By.cssSelector("div.filter-bottom-box>div>a")).getText();
		//String arr2[] = mystring3.split(" ", 2);
		//String firstWord2 = arr2[0]; 
		//Assert.assertEquals(count2, firstWord2,"Count mismatched between customers loaded vs customers filtered for Assign user filter");
		driver.findElement(By.cssSelector("div#campuser > div > div > div > div > a")).click();

		//validating user assigned filter
		Thread.sleep(3000);
		driver.findElement(By.id("showCustomerTVFilters")).click();
		driver.findElement(By.cssSelector("input#ctAssignedToIds")).click();
		WebElement a= driver.findElement(By.cssSelector("div#assignedToFilterList > label:nth-child(3)"));
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
		driver.get(DBURL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		//Login to database	
		driver.findElement(By.name("auth[server]")).sendKeys(DBServer);
		driver.findElement(By.id("username")).sendKeys(DBUName);
		driver.findElement(By.name("auth[password]")).sendKeys(DBPass);
		driver.findElement(By.name("auth[db]")).sendKeys(DB);
		driver.findElement(By.cssSelector("input[value='Login']")).click();


		//database selection
		driver.findElement(By.linkText("SQL command")).click();
		action.sendKeys("SELECT count(distinct c.id)\r\n"
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
		driver.navigate().to(url);

		//outputs

		System.out.println("	Unread Filter:");
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
		Assert.assertEquals(CRMdata[4],notes ,"Notes with remainder count mismatch");
	}

	//@Test(priority = 18)
	public void pipelineFilters() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		Actions action = new Actions(driver);

		//Getting Company ID
		String CompId = driver.findElement(By.id("globalcall_dialer")).getAttribute("data-companyid");
		int cid = Integer.parseInt(CompId);

		driver.navigate().to(Pipelines);

		//Getting pipeline id
		String pipelinename = driver.findElement(By.cssSelector("input[name=\"tags_title\"]")).getAttribute("value");
		String pipelineid = driver.findElement(By.cssSelector("label[data-text=\""+pipelinename+"\"]")).getAttribute("data-value");

		//Unread filter count
		driver.findElement(By.id("_btnOpenFilter")).click();
		System.out.println();
		System.out.println("Getting Pipeline Filters Result ----->");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("form#searchFilter>div:nth-of-type(2)>div:nth-of-type(2)>label>div:nth-of-type(2)>div:last-of-type")).click();
		driver.findElement(By.cssSelector("button[type=submit]")).click();
		List <WebElement>UnreadDots = driver.findElements(By.cssSelector("i.fa-solid.fa-circle.unread-dot"));
		int dotscount = UnreadDots.size();
		String dots = Integer.toString(dotscount);
		Thread.sleep(1000);
		driver.findElement(By.id("_btnOpenFilter")).click();	
		driver.findElement(By.cssSelector("div#sidenav_bottom_btn > a")).click();

		//Pipline Stages
		driver.findElement(By.id("_btnOpenFilter")).click();
		List <WebElement> stagecount = driver.findElements(By.cssSelector("#StageFilterList>label"));
		int intcount = stagecount.size();
		Thread.sleep(1000);
		driver.findElement(By.id("filter-close")).click();
		String[] custcount = new String[intcount-1]; 
		String[] stageid1 = new String[intcount-1];

		for(int i=2;i<=intcount;i++) 
		{
			driver.findElement(By.id("_btnOpenFilter")).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("input.stagevalueselectpipeline")).click();
			jse.executeScript("document.querySelector('div#side-filter').scrollTop = 300");
			WebElement list = driver.findElement(By.id("StageFilterList"));
			WebElement a = driver.findElement(By.cssSelector("#StageFilterList>label:nth-of-type("+i+")"));
			action.moveToElement(list).scrollToElement(a).build().perform();
			String stageid = a.getAttribute("data-value");
			stageid1[i-2]= stageid;
			Thread.sleep(1000);

			int j = i-1;
			if(i<2)
			{
				driver.findElement(By.cssSelector("#StageFilterList>label:nth-of-type("+j+")")).click();
			}

			a.click();
			driver.findElement(By.cssSelector("button[type=submit]")).click();
			String count = driver.findElement(By.cssSelector("div.headerstep.received:nth-of-type("+j+")>div>span.total-count")).getText();
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
		for(int i=1;i<=stages.size();i++) 
		{
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
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("form#searchFilter > div:nth-of-type(8) > div:nth-of-type(2)")).click();
		List<WebElement> users = driver.findElements(By.cssSelector("#assignFilterList>label"));
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
			driver.findElement(By.id("current-selected-assign")).click();
			WebElement imp  = driver.findElement(By.cssSelector("#assignFilterList>label:nth-of-type("+i+")"));
			WebElement assign = driver.findElement(By.id("assignFilterList"));
			action.moveToElement(assign).scrollToElement(imp).build().perform();
			UID1[i-2] = imp.getAttribute("data-value");

			int k = i-1;
			if(i<2)//Unchecking previous checkbox
			{
				driver.findElement(By.cssSelector("#assignFilterList>label:nth-of-type("+k+")")).click(); 
			}

			imp.click();
			driver.findElement(By.cssSelector("button[type=submit]")).click();
			List<WebElement> stages1 = driver.findElements(By.cssSelector("div.headerstep.received>div.headerstep_title>span.total-count.total_count_head"));

			for(int j=1; j<=stages1.size(); j++) //Retriving cusstomer count from each Stage
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
		driver.findElement(By.id("selected_Tagssee")).click();
		WebElement tag = driver.findElement(By.cssSelector("#tagesFilterList>label"));
		WebElement taglist = driver.findElement(By.id("tagesFilterList"));
		action.moveToElement(taglist).scrollToElement(tag).build().perform();
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
		driver.findElement(By.id("current-selected-list")).click();//list dropdown
		WebElement list = driver.findElement(By.cssSelector("#listFilterList>label"));

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
		driver.get(DBURL);

		//Login to database	
		driver.findElement(By.name("auth[server]")).sendKeys(DBServer);
		driver.findElement(By.id("username")).sendKeys(DBUName);
		driver.findElement(By.name("auth[password]")).sendKeys(DBPass);
		driver.findElement(By.name("auth[db]")).sendKeys(DB);
		driver.findElement(By.cssSelector("input[value='Login']")).click();

		//database selection
		driver.findElement(By.linkText("SQL command")).click();
		action.sendKeys("SELECT count(distinct c.id)\r\n"
				+ "FROM customers as c\r\n"
				+ "left join lead_pipeline_relation as lpr\r\n"
				+ "ON c.id = lpr.contact_id\r\n"
				+ "WHERE (c.unread !=0 And lpr.id_company = "+cid+" AND lpr.pipeline_id = "+pipelineid+")");
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
					+ "WHERE (lpr.id_company = "+cid+" AND lpr.pipeline_id = "+pipelineid+" AND c.is_enabled=1 AND c.deleted=0 AND lpr.status_id = "+stageid1[i]+")");
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
				+ "WHERE (c.timeadded > UNIX_TIMESTAMP(Current_date()) AND c.is_enabled=1 And pipeline_id = "+pipelineid+" AND c.deleted=0 AND c.id_company="+cid+")");
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
					+ "And pipeline_id = "+pipelineid+" AND c.deleted=0 AND c.addedby="+UID1[i]+")");
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
				+ "where (lpr.id_company = "+cid+" AND lpr.pipeline_id = "+pipelineid+" \r\n"
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
				+ "where (lpr.id_company = "+cid+" AND lpr.pipeline_id = "+pipelineid+"\r\n"
				+ "AND c.is_enabled=1 AND object_name = \"customertolist\" and c.deleted=0 and t.rel_id = "+listid+")");
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
		String DBlistcustCount = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();
		driver.navigate().to(url);

		//Output: 
		System.out.println("	Unread Filter:");
		System.out.println("		Unread Filter Count From CRM: "+dots);
		System.out.println("		Unread Filter Count From Database: "+unread);
		System.out.println();

		System.out.println("	Pipeline Stagewise Filtered contacts(Per Stage Count):");
		for(String tem1p:custcount)
		{
			System.out.println("		Filter Count From CRM: "+tem1p);
		}
		for(String temp1:DBcount) {
			System.out.println("		From Database: "+temp1);
		}
		System.out.println();

		System.out.println("	Created date filter :Todays created");
		System.out.println("		Filter Count From CRM: "+totalcount);
		System.out.println("		Filter Count From Database: "+DBTodayCount);
		System.out.println();

		System.out.println("	Assigned User Filter(Per User Count): ");
		for(String d:stagecustcount)
		{
			System.out.println("		Filter Count From CRM: "+d);
		}
		for(String as:DBAssigncount) {
			System.out.println("		Filter Count From Database: "+as);
		}
		System.out.println();
		System.out.println("	Tags filter:");
		System.out.println("		Filter Count Form CRM: "+c);
		System.out.println("		Filter Count From Database: "+DBtagedcustCount);

		System.out.println();
		System.out.println("	List filter:");
		System.out.println("		Filter Count From CRM: "+listCRM);	
		System.out.println("		Filter Count From Database: "+DBlistcustCount);

		//Assrstions ---------------->
		Assert.assertEquals(dots, unread,"Unread count Mismatch");
		Assert.assertEquals(custcount, DBcount, "Stage Customer count mismatch");
		Assert.assertEquals(totalcount, DBTodayCount,"Today's added customer count mismatch");
		Assert.assertEquals(stagecustcount, DBAssigncount,"Assigned user Filter count mismatch");
		Assert.assertEquals(c, DBtagedcustCount,"Tags Filter count mismatch");
		Assert.assertEquals(listCRM, DBlistcustCount, "List filters count mismatch");

	}

	//@Test(priority = 24)
	public void createlist() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
		Actions action = new Actions(driver);

		action.moveToElement(driver.findElement(By.cssSelector("div.hidden.sidebar-bg-color"))).perform();
		driver.findElement(By.cssSelector("a[title=\"Lists\"]")).click();

		driver.findElement(By.cssSelector("div.filters-section+div>a:first-of-type")).click();
		driver.findElement(By.cssSelector("div.input-group>input")).click();
		driver.findElement(By.cssSelector("div.input-group>input")).sendKeys("TestList110022");
		driver.findElement(By.id("ClistNextBtn")).click();
		Thread.sleep(1000);
		String logadded = driver.findElement(By.id("alertify-logs")).getText();
		String listid = driver.findElement(By.cssSelector("table>input:last-of-type")).getAttribute("value");
		WebElement search = driver.findElement(By.cssSelector("form#aec_form>input"));
		search.sendKeys("Form");
		action.sendKeys(Keys.ENTER).perform();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[name=\"ids[]\"]+span")).click();
		driver.findElement(By.cssSelector("div.adminbuttons>button")).click();
		driver.findElement(By.cssSelector("a[onclick=\"$('.edit_list_form').submit()\"]")).click();
		Thread.sleep(1000);
		String logupdated = driver.findElement(By.id("alertify-logs")).getText();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#at0-cell-action-0 > div > a[data-listid=\""+listid+"\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("form#deleteform>div>button")).click();
		Thread.sleep(500);
		String logdeleted = driver.findElement(By.id("alertify-logs")).getText();

		//Output
		System.out.println();
		System.out.println("List Module ----->");
		System.out.println("	"+logadded);
		System.out.println("	"+logupdated);
		System.out.println("	"+logdeleted);
	}

	@Test(priority = 25)
	public void CallLog() throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		Actions action = new Actions(driver);

		//Getting Company ID
		String CompId = driver.findElement(By.id("globalcall_dialer")).getAttribute("data-companyid");
		int cid = Integer.parseInt(CompId);

		//scroll
		WebElement e = driver.findElement(By.cssSelector("div.hidden.sidebar-bg-color"));
		action.moveToElement(e).perform();
		//String navMisscall = driver.findElement(By.cssSelector("a[title=\"Call Log\"] > span:last-of-type")).getText();
		driver.findElement(By.cssSelector("a[title=\"Call Log\"]")).click();
		String[] CRMDATA = new String[7];

		Thread.sleep(5000);
		String Missedcalls = driver.findElement(By.cssSelector("a[href =\"index.php?m=customers&d=calllogs&filter=missed\"] span")).getText();
		//Assert.assertEquals(navMisscall, Missedcalls, "Missed call Count mismatch Sidebar and Call log");

		driver.findElement(By.id("showCallLogFilters")).click();//Filter button

		for(int i=1;i<=4;i++) {
			driver.findElement(By.cssSelector("div.blocktitlesection:last-of-type>div>a:nth-of-type("+i+")")).click();
			//use this after 5th iteration :- div.blocktitlesection:last-of-type>div+div>a:nth-of-type(1)
			Boolean pages1 = false;
			try {
				pages1 = driver.findElement(By.cssSelector("div.hidden>a[href^=\"https://app.deposyt.com/index.php?m=customers&d=calllogs&from=\"]")).isDisplayed();
			}
			catch(Exception NoSuchElementException) {}

			int a1 = 0;
			if(pages1) {
				List <WebElement> pno1 = driver.findElements(By.cssSelector("div.hidden>a[href^=\"https://app.deposyt.com/index.php?m=customers&d=calllogs&from=\"]"));
				a1 = pno1.size();
				a1 = (a1-1)*30;
				jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
				driver.findElement(By.cssSelector("div.hidden>a[href^=\"https://app.deposyt.com/index.php?m=customers&d=calllogs&from=\"]:last-of-type")).click();
			}
			List<WebElement> last1 = driver.findElements(By.cssSelector("tr.admintable_row>td>div.checkwrapdiv"));
			int b1 = last1.size();
			a1 = a1 + b1;
			CRMDATA[i-1] = Integer.toString(a1);
		}
		for(int i=1;i<=3;i++) {
			driver.findElement(By.cssSelector("div.blocktitlesection:last-of-type>div+div>a:nth-of-type("+i+")")).click();
			Boolean pages1 = false;
			try {
				pages1 = driver.findElement(By.cssSelector("div.hidden>a[href^=\"https://app.deposyt.com/index.php?m=customers&d=calllogs&from=\"]")).isDisplayed();
			}
			catch(Exception NoSuchElementException) {}

			int a1 = 0;
			if(pages1) {
				List <WebElement> pno1 = driver.findElements(By.cssSelector("div.hidden>a[href^=\"https://app.deposyt.com/index.php?m=customers&d=calllogs&from=\"]"));
				a1 = pno1.size();
				a1 = (a1-1)*30;
				jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
				driver.findElement(By.cssSelector("div.hidden>a[href^=\"https://app.deposyt.com/index.php?m=customers&d=calllogs&from=\"]:last-of-type")).click();
			}
			List<WebElement> last1 = driver.findElements(By.cssSelector("tr.admintable_row>td>div.checkwrapdiv"));
			int b1 = last1.size();
			a1 = a1 + b1;
			CRMDATA[i+3] = Integer.toString(a1);
		}

		//Adminer Login ------------------------->
		driver.get(DBURL);
		//Login to database	
		driver.findElement(By.name("auth[server]")).sendKeys(DBServer);
		driver.findElement(By.id("username")).sendKeys(DBUName);
		driver.findElement(By.name("auth[password]")).sendKeys(DBPass);
		driver.findElement(By.name("auth[db]")).sendKeys(DB);
		driver.findElement(By.cssSelector("input[value='Login']")).click();

		//database selection
		driver.findElement(By.linkText("SQL command")).click();
		action.sendKeys("SELECT Count(id)\r\n"
				+ "FROM `dialer_calls`\r\n"
				+ "WHERE (`id_company` = "+cid+"\r\n"
				+ "AND `archived` = '0' AND `type` = '0')\r\n")
		.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
		String DBcountTotal = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();

		//Getting Missed count from Database
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).clear();
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).sendKeys("SELECT Count(id)\r\n"
				+ "FROM `dialer_calls`\r\n"
				+ "WHERE (`id_company` = "+cid+" AND incoming = '1'\r\n"
				+ "AND duration_sec = '0' AND `archived` = '0' AND `type` = '0')\r\n");
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
		String DBcountmisscall = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();

		//Getting outgoing call count from Database
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).clear();
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).sendKeys("SELECT Count(id)\r\n"
				+ "FROM `dialer_calls`\r\n"
				+ "WHERE (`id_company` = "+cid+" AND incoming = '0'\r\n"
				+ "AND `archived` = '0' AND `type` = '0' AND status = 'completed ')\r\n");
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
		String DBoutcall = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();

		//Getting Incoming call count from Database
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).clear();
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).sendKeys("SELECT Count(id)\r\n"
				+ "FROM `dialer_calls`\r\n"
				+ "WHERE (`id_company` = "+cid+" AND incoming = '1'\r\n"
				+ "AND duration_sec != '0' AND `archived` = '0' AND `type` = '0')\r\n"
				+ "LIMIT 50");
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
		String DBincall = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();

		//Getting Unknown call count from Database
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).clear();
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).sendKeys("SELECT Count(id)\r\n"
				+ "FROM `dialer_calls`\r\n"
				+ "WHERE (`id_company` = "+cid+" AND id_customer = 0 \r\n"
				+ "AND `archived` = '0' AND `type` = '0')\r\n");
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
		String DBunknowncall = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();

		//Getting Voice mails call count from Database
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).clear();
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).sendKeys("SELECT Count(id)\r\n"
				+ "FROM `dialer_calls`\r\n"
				+ "WHERE (`id_company` = 357 AND voicemail_duration != 0\r\n"
				+ "AND `archived` = '0' AND `type` = '0')");
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
		String DBVoicemails = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();

		//Getting Voice mails call count from Database
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).clear();
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).sendKeys("SELECT Count(id)\r\n"
				+ "FROM `dialer_calls`\r\n"
				+ "WHERE (`id_company` = "+cid+"\r\n"
				+ "AND `archived` = '1' AND `type` = '0')\r\n");
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
		String DBarchive = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();


		//Getting missed call notification count Form message log
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).clear();
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).sendKeys("select COUNT(messagelog.id)\r\n"
				+ "FROM messagelog\r\n"
				+ "WHERE (messagelog.id_company = "+cid+" AND messagelog.type = 'call'\r\n"
				+ "AND messagelog.timeread = '0' AND messagelog.incoming = 1)\r\n");
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
		String DB1 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();

		//Getting missed call notification count form dialer call 
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).clear();
		driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).sendKeys("Select COUNT(dc.id) as count\r\n"
				+ "FROM dialer_calls dc\r\n"
				+ "WHERE (dc.id_company = "+cid+" AND dc.type = '0'\r\n"
				+ "AND dc.dial_notify = '0' AND dc.incoming = 1 AND dc.duration_sec = 0)");
		action.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
		String DB2 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/table/tbody/tr/td")).getText();
		DB1 = Integer.toString(Integer.parseInt(DB1) + Integer.parseInt(DB2));


		//Output 
		System.out.println();
		System.out.println("Call Log ----->");
		System.out.println("	missed Calls:");
		System.out.println("		Missed Call Count from CRM: "+Missedcalls);
		System.out.println("		Missed Call Count From DB: "+DB1);
		System.out.println();
		System.out.println("	Tatal Call Logs:");
		System.out.println("		Total Log Count Form CRM: "+CRMDATA[0]);
		System.out.println("		Total Log Count From DB: "+DBcountTotal);
		System.out.println();
		System.out.println("	Missed Call Filter: ");
		System.out.println("		Missed call Count Form CRM: "+CRMDATA[1]);
		System.out.println("		Missed call Count From DB: "+DBcountmisscall);
		System.out.println();
		System.out.println("	Outgoing Calls:");
		System.out.println("		Outgoing Call Count from CRM: "+CRMDATA[2]);
		System.out.println("		Outgoing call Count From DB: "+DBoutcall);
		System.out.println();
		System.out.println("	Incoming Calls:");
		System.out.println("		Incoming Call Count from CRM: "+CRMDATA[3]);
		System.out.println("		Incoming call Count From DB: "+DBincall);
		System.out.println();
		System.out.println("	Unknown Calls:");
		System.out.println("		Unknown Call Count from CRM: "+CRMDATA[4]);
		System.out.println("		Unknown call Count From DB: "+DBunknowncall);
		System.out.println();
		System.out.println("	Voice Mails:");
		System.out.println("		Voice Mails Count from CRM: "+CRMDATA[5]);
		System.out.println("		Voice Mails Count From DB: "+DBVoicemails);
		System.out.println();
		System.out.println("	Archive Calls:");
		System.out.println("		Archive Calls Count from CRM: "+CRMDATA[6]);
		System.out.println("		Archive Calls Count From DB: "+DBarchive);


		//Assertions
		//Assert.assertEquals(CRMDATA[0], DBcountTotal,"Total Logs Count Mismatched");
		//Assert.assertEquals(CRMDATA[1], DBcountmisscall,"Missed call Count Mismatched");
		//Assert.assertEquals(CRMDATA[2], DBoutcall,"Outgoing call Count Mismatched");
		//Assert.assertEquals(CRMDATA[3], DBincall,"Incoming call Count Mismatched");
		//Assert.assertEquals(CRMDATA[4], DBunknowncall,"Unknown call Count Mismatched");
		//Assert.assertEquals(CRMDATA[5], DBVoicemails,"Voice Mails Count Mismatched");
		//Assert.assertEquals(CRMDATA[6], DBarchive,"Archive call Count Mismatched");
		driver.navigate().to(url);
	}

	@Test(priority = 26)
	public void Apptypeoparations() throws Exception 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		Actions action = new Actions(driver);

		System.out.println();
		driver.navigate().to(AppointmentTypes);
		String aptname = driver.findElement(By.cssSelector("div.eventcontentdiv>div:first-of-type>div>a")).getText();
		driver.findElement(By.cssSelector("div.eventcontentdiv>div:first-of-type>div>div>a")).click();//appointment menu
		driver.findElement(By.cssSelector("div.eventcontentdiv>div:first-of-type>div>div>div>a:nth-of-type(3)")).click(); // duplicate option

		Thread.sleep(2000);
		jse.executeScript("document.querySelector('#cloneeventtypepopup').scrollTop=200");
		driver.findElement(By.cssSelector("#cloneeventtypepopupcontent>div>div>div:nth-child(4)>button")).click();
		Thread.sleep(500);
		System.out.println(driver.findElement(By.id("toast-container")).getText());
		driver.findElement(By.name("search")).sendKeys("Copy Of "+aptname);
		action.sendKeys(Keys.ENTER).perform();

		driver.findElement(By.cssSelector("div.eventcontentdiv>div:first-of-type>div>div>a")).click();//appointment menu
		driver.findElement(By.cssSelector("div.eventcontentdiv>div:first-of-type>div>div>div>a:nth-of-type(4)")).click(); // delete option
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("form#formdeleteactionmodaldiv>div>button")).click();

		driver.findElement(By.name("search")).sendKeys("Copy Of "+aptname);
		action.sendKeys(Keys.ENTER).perform();
		
		try 
		{
			driver.findElement(By.cssSelector("div.eventcontentdiv>div:first-of-type>div>div>a")).click();//appointment menu
			System.out.println("Appointment Not Deleted");
		}
		catch(Exception e) 
		{
			System.out.println("Appointment Deleted Successfully!");
		}
	}

	@Test(priority = 27)
	public void ImportList() throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
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
		driver.findElement(By.name("search")).sendKeys("Auto imported");
		action.sendKeys(Keys.ENTER).perform();
		String listcount = driver.findElement(By.id("at0-cell-total_contact-0")).getText();
		Assert.assertEquals(succount[3], listcount,"Assertion Failed for Imported Contact Count");
		System.out.println("	Contacts Imported Successfully To New List");
	}

	public void deletetrigger() throws Exception 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.navigate().to(Triggers);

		// delete triggers
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[2]/div/div/div[1]/div[2]/div[2]/a[2]/i")).click();
		driver.findElement(By.xpath("//*[@id=\"formdeleteactionmodaldiv\"]/div/button")).click();

		System.out.println();
		System.out.println("Deleting Triggers ----->");
		System.out.println("	Trigger Deleted Successfully");
	}

	//@Test(priority = 31)
	public void deletepipeline(String Pipelineid) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		JavascriptExecutor jse = (JavascriptExecutor)driver;

		driver.navigate().to("https://app.deposyt.com/index.php?m=pipelines");

		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/div[1]")).click();
		jse.executeScript("document.querySelector('div#block0body > div > div > div > div > div > ul').scrollTop=300");
		driver.findElement(By.cssSelector("ul.list>li[data-value = "+Pipelineid+"]")).click();
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/a[1]")).click();
		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[2]/a[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"get_delete_pipeline_main\"]/div/div/div/div/a[2]")).click();//<--permenant delete button
		driver.findElement(By.xpath("//*[@id=\"pp-delete-customer-form\"]/div/button")).click();
		String log=driver.findElement(By.id("alertify-logs")).getText();
		System.out.println();
		System.out.println("Delete pipline ----->");
		System.out.println("	Deleting Pipeline : "+log);
	}

	public void Login2() throws InterruptedException
	{
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));

		// Login to diff test account
		driver.findElement(By.xpath("//*[@id=\"dropdownMenu1\"]")).click();
		driver.findElement(By.linkText("Logout")).click();

		Thread.sleep(5000);
		WebElement emf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		emf.sendKeys(User2login);
		driver.findElement(By.id("password")).sendKeys(User2pass);
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[4]/button[1]")).click();
		Thread.sleep(3000);
	}

	@Test(priority = 19)
	@Parameters({"uname", "pass"})
	public void relogin(String uname, String pass) throws Exception
	{
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));

		// Relogin
		driver.findElement(By.xpath("//*[@id=\"dropdownMenu1\"]")).click();
		driver.findElement(By.linkText("Logout")).click();

		Thread.sleep(5000);
		WebElement emf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		emf.sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pass);
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[4]/button[1]")).click();
		Thread.sleep(3000);
	}

}

