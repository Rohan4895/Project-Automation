package Modules;

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

public class Lists extends Data{
	
	@Test
	public void createlist() throws Exception {
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
	Actions action = new Actions(driver);
	
	action.moveToElement(driver.findElement(By.cssSelector("div.hidden.sidebar-bg-color"))).perform();
	driver.findElement(By.cssSelector("a[title=\"Lists\"]")).click();
	
	driver.findElement(By.cssSelector("x	div.filters-section+div>a:first-of-type")).click();
	driver.findElement(By.cssSelector("div.input-group>input")).click();
	driver.findElement(By.cssSelector("div.input-group>input")).sendKeys("TestList110022");
	driver.findElement(By.id("ClistNextBtn")).click();
	Thread.sleep(1000);
	String logadded = driver.findElement(By.id("alertify-logs")).getText();
	String listid = driver.findElement(By.cssSelector("table>input:last-of-type")).getAttribute("value");
	WebElement search = driver.findElement(By.cssSelector("form#aec_form>input"));
	search.sendKeys("Weaired");
	action.sendKeys(Keys.ENTER).perform();
	Thread.sleep(2000);
	driver.findElement(By.cssSelector("input[name=\"ids[]\"]+span")).click();
	driver.findElement(By.cssSelector("div.adminbuttons>button")).click();
	driver.findElement(By.cssSelector("a[onclick=\"$('.edit_list_form').submit()\"]")).click();
	Thread.sleep(1000);
	String logupdated = driver.findElement(By.id("alertify-logs")).getText();
	Thread.sleep(2000);
	driver.findElement(By.cssSelector("a[data-listid= \""+listid+"\"]")).click();
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
	
	@Test
	public void createlistandaddexistingcontacts() throws Exception {
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
	String listid = driver.findElement(By.xpath("//input[@id='listtitle']")).getAttribute("value");
	//WebElement search = driver.findElement(By.cssSelector("form#aec_form>input"));
	//search.sendKeys("Weaired");
	//action.sendKeys(Keys.ENTER).perform();
	//Thread.sleep(2000);
	driver.findElement(By.cssSelector("#at0-cell-chk1-0 > label > span")).click();	
	//driver.findElement(By.cssSelector("input[name=\"ids[]\"]+span")).click();
	driver.findElement(By.cssSelector("div.adminbuttons>button")).click();
	driver.findElement(By.cssSelector("a[onclick=\"$('.edit_list_form').submit()\"]")).click();
	Thread.sleep(1000);
	String logupdated = driver.findElement(By.id("alertify-logs")).getText();
	Thread.sleep(2000);
	driver.findElement(By.cssSelector("a[data-listid= \""+listid+"\"]")).click();
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
	
	@Test
	public void ImportList() throws Exception {
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
	Actions action = new Actions(driver);
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	
	driver.navigate().to(Lists);
	
	try
	{
	//delete duplicate
	driver.findElement(By.name("search")).sendKeys("Auto Imported");
	action.sendKeys(Keys.ENTER).perform();
	driver.findElement(By.cssSelector("#at0-cell-action-0>div>a:last-of-type")).click();
	Thread.sleep(500);
	driver.findElement(By.cssSelector("#deleteform>div>button")).click();
	}
	catch(Exception e)
	{
	}
	System.out.println("Created List delete Successfully");
	
	driver.navigate().to(Customers);
	
	driver.findElement(By.xpath("//a[@id='showCustomerTVFilters']")).click();
	driver.findElement(By.xpath("//input[@value='In Pipeline:']")).click();
	
	List<WebElement>Pipelines = driver.findElements(By.cssSelector("#pipeline-typeFilterList>label"));
	String pipelinetext = driver.findElement(By.cssSelector("#pipeline-typeFilterList>label:first-of-type")).getAttribute("data-text");
	String pipelineid = driver.findElement(By.cssSelector("#pipeline-typeFilterList>label:first-of-type")).getAttribute("data-value");

	String pipelineid2 = null;
	try 
	{
	      pipelineid2 = driver.findElement(By.cssSelector("#pipeline-typeFilterList>label:nth-of-type(2)")).getAttribute("data-value");
	} 
	catch(Exception e) {
	
	}
	
	driver.findElement(By.cssSelector("#pipeline-typeFilterList>label:first-of-type")).click();
	
	java.util.List<WebElement> stage = driver.findElements(By.xpath("//input[@placeholder='In Stage:']"));
	stage.get(0).click(); 
	String stagetext = driver.findElement(By.cssSelector("#stagesFilterList > label:nth-of-type(2)")).getAttribute("data-text"); //store stage name
	String stageid = driver.findElement(By.cssSelector("#stagesFilterList > label:nth-of-type(2)")).getAttribute("data-Value");
	driver.findElement(By.cssSelector("#stagesFilterList > label:nth-child(3)")).click();
	stage.get(0).click();
	Thread.sleep(3000);
	String beforefiltercount = driver.findElement(By.xpath("//span[@class='total-count-for-change']")).getText();
	System.out.println("Pipeline count before import customers" + beforefiltercount);
	int Beforefilterint = Integer.parseInt(beforefiltercount);
	Beforefilterint = Beforefilterint + 5;
	beforefiltercount = Integer.toString(Beforefilterint);
	
	driver.navigate().to("https://app.deposyt.com/index.php?m=importcustomers&d=list");
	driver.findElement(By.name("list_title")).sendKeys("Auto imported");
	driver.findElement(By.cssSelector("button[value=\"Upload\"]")).click();
	driver.findElement(By.id("userfile")).sendKeys("C:\\Users\\Rohan kokare\\dummydata1.csv");
	driver.findElement(By.cssSelector("input[value=\"Upload\"]")).click();
	
	//Select all and checkbox select count verify
	driver.findElement(By.xpath("//a[@id='checkAll']")).click();//Deselect already selected
	Thread.sleep(500);
	driver.findElement(By.xpath("//table[@class='overflow-auto min-w-full divide-y divide-gray-200 admintable table table-striped table-hover fullstats']/tbody/tr[1]/td[1]/label/span")).click();
	int total = 0;
	java.util.List<WebElement> selectedcount= driver.findElements(By.xpath("//span[@data-totalcount='5']"));
	for(WebElement we : selectedcount){
	    total += Integer.parseInt(we.getText()); //get total count number from webelement.
	}
	Assert.assertEquals(total, 1);
	System.out.println("Selected count is correct");
	driver.findElement(By.xpath("//a[@id='checkAll']")).click();//Again select
	driver.findElement(By.xpath("//span[@data-totalcount='5']")).isDisplayed();
	System.out.println("Select all count is correct");
	
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
	driver.findElement(By.xpath("//input[@value='Default']")).click();
	driver.findElement(By.xpath("//input[@placeholder='Search Pipeline']")).click();
	driver.findElement(By.xpath("//input[@placeholder='Search Pipeline']")).sendKeys(pipelinetext);
	
	try 
	{
		driver.findElement(By.cssSelector("#pipeline-typeFilterList > label[data-value=\""+pipelineid2+"\"]")).click();
		Assert.assertTrue(false,"Pipeline Search not working properly");
	}
	catch(Exception e) {
		driver.findElement(By.cssSelector("#pipeline-typeFilterList > label[data-value=\""+pipelineid+"\"]")).click();
	}
	
	driver.findElement(By.xpath("//div[contains(text(),'Select Stage')]")).click();
	driver.findElement(By.xpath("//input[@placeholder='Search Stage']")).click();
	driver.findElement(By.xpath("//input[@placeholder='Search Stage']")).sendKeys(stagetext);
	driver.findElement(By.cssSelector("#pipelineOneOffstagesFilterList > label:nth-child(2) > span")).click();
	Thread.sleep(500);
	driver.findElement(By.cssSelector("button[value=\"Next\"]")).click();
	Thread.sleep(500);
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alert.alert-success")));
	//String success = driver.findElement(By.cssSelector("div.alert.alert-success")).getText();
	//System.out.println(success);
	
	//Again go to customer to validate contact added in pipeline
    driver.navigate().to(Customers);
	
	driver.findElement(By.xpath("//a[@id='showCustomerTVFilters']")).click();
	driver.findElement(By.xpath("//input[@value='In Pipeline:']")).click();
	driver.findElement(By.xpath("//input[@placeholder='Search Pipeline']")).sendKeys(pipelinetext);
	driver.findElement(By.cssSelector("#pipeline-typeFilterList > label:nth-child(2)")).click();
	Thread.sleep(1000);
	
	stage.get(0).click(); //stage filter dropdown
	driver.findElement(By.xpath("//input[@id=\"search_stage_list\"]")).sendKeys(stagetext);
	Thread.sleep(200);
	driver.findElement(By.cssSelector("#stagesFilterList > label:nth-child(3)")).click();
	stage.get(0).click();
	Thread.sleep(3000);
	String afterfiltercount = driver.findElement(By.xpath("//span[@class='total-count-for-change']")).getText();
	System.out.println("After adding contacts in pipeline count" +afterfiltercount);
	
	}
	
	public void Modalpopup() throws Exception 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Actions action = new Actions(driver);
		
		driver.navigate().to(Lists);
		driver.findElement(By.name("search")).sendKeys("Auto Imported");
		action.sendKeys(Keys.ENTER).perform();
		driver.findElement(By.id("at0-cell-name-0")).click();
		Thread.sleep(500);
		driver.findElement(By.cssSelector("#at0-cell-info-0>a")).click();
		
		Messages M = new Messages();
		M.CMP();
		
	}
}
