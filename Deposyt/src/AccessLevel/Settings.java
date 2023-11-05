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

public class Settings extends Data 
{
	
	@Test
	public void NoneAccess() throws Exception 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);
		
		System.out.println("Testing Seetings - None Access ----->");
		driver.navigate().to(UserManagment);
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
		
		action.sendKeys(Keys.PAGE_DOWN).perform();
		
		for(int i=2; i<=6;i++) 
		{
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("div.module-wrapper:first-of-type>div:nth-of-type("+i+")>div.flex.access-level-buttons>button:first-of-type")).click();
		}
		
		driver.findElement(By.cssSelector("button[data-type=access_level]")).click();//save button in access level
		driver.navigate().to("https://app.deposyt.com/index.php?m=usersmgmt&d=list");//user profile page
		driver.findElement(By.cssSelector("table>tbody>tr:first-of-type>td:nth-child(8)>div>a")).click();
		driver.findElement(By.cssSelector("table>tbody>tr:first-of-type>td:nth-child(8)>div>div>a:first-of-type")).click();//login to user account
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
		
		//Validation - By simple navigation
		driver.navigate().to(Settings);//Setting page
		try//business profile page
		{
			driver.findElement(By.cssSelector("div[onclick=\"document.location.href='index.php?m=settings&d=businessprofile';\"]")).isDisplayed();
			Assert.assertTrue(false,"Test Failed: Bussiness Profile page Option is Displyed in Seetings");
		}
		catch(Exception noSuchEelementException)
		{
			System.out.println("	Bussiness Profile page Option is Not Displyed in Settings");
		}
		
		try//Security option
		{
			driver.findElement(By.cssSelector("div[onclick=\"window.location.href = 'index.php?m=employeedetails&d=security';\"]")).isDisplayed();
			Assert.assertTrue(false,"Test Failed: Security option page Option is Displyed in Settings");
		}
		catch(Exception noSuchEelementException)
		{
			System.out.println("	Security Option is Not Displyed in Settings");
		}
		
		try//Calendar settings 
		{
			driver.findElement(By.cssSelector("div[onclick=\"document.location.href='index.php?m=settings&d=calendar&from=settings';\"]")).isDisplayed();
			Assert.assertTrue(false,"Test Failed: Calendar settings page Option is Displyed in Settings");
		}
		catch(Exception noSuchEelementException)
		{
			System.out.println("	Calendar Setting Option is Not Displyed in Settings");
		}
		
		try//My Profile 
		{
			driver.findElement(By.cssSelector("div[onclick=\"window.location.href = 'index.php?m=employeedetails&d=myprofile';\"]")).isDisplayed();
			Assert.assertTrue(false,"Test Failed: My Profile page Option is Displyed in Settings");
		}
		catch(Exception noSuchEelementException)
		{
			System.out.println("	My Profile Option is Not Displyed in Settings");
		}
		
		try//Access Level 
		{
			driver.findElement(By.cssSelector("div[onclick=\"window.location.href = 'index.php?m=usersmgmt&d=access_level_list';\"]")).isDisplayed();
			Assert.assertTrue(false,"Test Failed: Access Level page Option is Displyed in Settings");
		}
		catch(Exception noSuchEelementException)
		{
			System.out.println("	Access Level Option is Not Displyed in Settings");
		}

		//validations - Through Links navigation
		driver.navigate().to("https://app.deposyt.com/index.php?m=settings&d=businessprofile");//Business profile page
		//Assert.assertTrue(driver.getPageSource().contains("Access Denied!"));
		driver.navigate().to("https://app.deposyt.com/index.php?m=employeedetails&d=security");//Security
		//Assert.assertTrue(driver.getPageSource().contains("Access Denied!"));
		driver.navigate().to("https://app.deposyt.com/index.php?m=settings&d=calendar&from=settings");//Calendar setting
		//Assert.assertTrue(driver.getPageSource().contains("Access Denied!"));
		driver.navigate().to("https://app.deposyt.com/index.php?m=employeedetails&d=myprofile");//my profile
		//Assert.assertTrue(driver.getPageSource().contains("Access Denied!"));
		driver.navigate().to(AccessLevelPage);//access Level
		//Assert.assertTrue(driver.getPageSource().contains("Access Denied!"));
		driver.findElement(By.xpath("//*[@id=\"dropdownMenu1\"]")).click();
		driver.findElement(By.linkText("Re-Login Back")).click();
	}
	
	@Test (priority = 1)
	public void ViewAccess() throws Exception 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);
		JavascriptExecutor jse= (JavascriptExecutor)driver;	
		
		driver.navigate().to(UserManagment);
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
		
		action.sendKeys(Keys.PAGE_DOWN).perform();
		
		for(int i=2; i<=6;i++) 
		{
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("div.module-wrapper:first-of-type>div:nth-of-type("+i+")>div.flex.access-level-buttons>button:nth-of-type(2)")).click();
		}
		
		driver.findElement(By.cssSelector("button[data-type=access_level]")).click();//save button in access level
		driver.navigate().to("https://app.deposyt.com/index.php?m=usersmgmt&d=list");//user profile page
		driver.findElement(By.cssSelector("table>tbody>tr:first-of-type>td:nth-child(8)>div>a")).click();
		driver.findElement(By.cssSelector("table>tbody>tr:first-of-type>td:nth-child(8)>div>div>a:first-of-type")).click();//login to user account
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
		
		System.out.println();
		System.out.println("Testing Setting Module with View Access ----->");
		System.out.println("	None/View Validations: ");
		
		//Validation - By simple navigation- Checking all option are getting displayed
		driver.navigate().to(Settings);//Setting page
		try//business profile page
		{
			driver.findElement(By.cssSelector("div[onclick=\"document.location.href='index.php?m=settings&d=businessprofile';\"]")).isDisplayed();
			System.out.println("	Bussiness Profile Option is Displyed in Settings");
		}
		catch(Exception noSuchEelementException)
		{
			Assert.assertFalse(true,"Test Failed: Bussiness Profile page Option is Not Displyed in Seetings");
		}

		try//Security option
		{
			driver.findElement(By.cssSelector("div[onclick=\"window.location.href = 'index.php?m=employeedetails&d=security';\"]")).isDisplayed();
			System.out.println("	Security Option is Displyed in Settings");
		}
		catch(Exception noSuchEelementException)
		{
			Assert.assertFalse(true,"Test Failed: Security page Option is Not Displyed in Settings");
		}

		try//Calendar settings 
		{
			driver.findElement(By.cssSelector("div[onclick=\"document.location.href='index.php?m=settings&d=calendar&from=settings';\"]")).isDisplayed();
			System.out.println("	Calendar Setting Option is Displyed in Settings");
		}
		catch(Exception NoSuchEelementException)
		{
			Assert.assertFalse(true,"Test Failed: Calendar Setting page Option is Not Displyed in Settings");
		}

		try//My Profile 
		{
			driver.findElement(By.cssSelector("div[onclick=\"window.location.href = 'index.php?m=employeedetails&d=myprofile';\"]")).isDisplayed();
			System.out.println("	My Profile Option is Not Displyed in Settings");
		}
		catch(Exception NoSuchEelementException)
		{
			Assert.assertFalse(true,"Test Failed: My Profile page Option is Not Displyed in Settings");
		}

		//Validations For Direct navigation and Edit
		System.out.println();
		System.out.println("	Edit Validations: ");
		
		driver.navigate().to("https://app.deposyt.com/index.php?m=settings&d=businessprofile");//Business profile page
		Assert.assertFalse(driver.getPageSource().contains("Access Denied!"));
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		
		try //Checking Submit button 
		{
			driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
			Assert.assertFalse(true,"Test Failed: User is able to Edit The Bussiness Profile");
		}
		catch(Exception ElementNotInteractableException) 
		{	
			System.out.println("	User is Unable to Edit the bussiness profile page");
		}

		//Validations For Direct navigation and Edit For Security page
		driver.navigate().to("https://app.deposyt.com/index.php?m=employeedetails&d=security");//Security
		Assert.assertFalse(driver.getPageSource().contains("Access Denied!"));
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		
		try 
		{
			driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
			Assert.assertFalse(true,"Test Failed: User is able to Edit Security page");
		}
		catch(Exception ElementNotInteractableExceptione) 
		{
			System.out.println("	User is Unable to Edit the Security page");
		}
		
		//Validations For Direct navigation and Edit For Calendar setting page
		driver.navigate().to("https://app.deposyt.com/index.php?m=settings&d=calendar&from=settings");//Calendar setting
		Assert.assertFalse(driver.getPageSource().contains("Access Denied!"));
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		
		try 
		{
			driver.findElement(By.cssSelector("#aptstatus>div>a")).click();
			driver.findElement(By.cssSelector("#appointmentstatusform>div:Last-child>button:last-child")).click();
			System.out.println("	User is able to Edit Calendar Setting page ---> Test Failed");
			//Assert.assertFalse(true,"Test Failed: User is able to Edit Calendar Setting page");
		}
		catch(Exception ElementNotInteractableExceptione) 
		{
			System.out.println("	User is Unable to Edit the Calendar Settings page");
		}
		
		//Validations For Direct navigation and Edit For My profile page
		driver.navigate().to("https://app.deposyt.com/index.php?m=employeedetails&d=myprofile");//my profile
		Assert.assertFalse(driver.getPageSource().contains("Access Denied!"));
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		try 
		{
			driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
			Assert.assertFalse(true,"Test Failed: User is able to Edit My Profile page");
		}
		catch(Exception ElementNotInteractableExceptione) 
		{
			System.out.println("	User is Unable to Edit the My profile page");
		}
		
		//Validations For Direct navigation and Edit For Access Level page
		driver.navigate().to("https://app.deposyt.com/index.php?m=usersmgmt&d=access_level_list");//access Level
		Assert.assertFalse(driver.getPageSource().contains("Access Denied!"));
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		
		try 
		{
			driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
			Assert.assertFalse(true,"Test Failed: User is able to Edit Access Level page");
		}
		catch(Exception e) 
		{
			System.out.println("	User is unable to Edit Access Level page");
		}
		//Re- Login To Admin Acoount
		driver.findElement(By.xpath("//*[@id=\"dropdownMenu1\"]")).click();
		driver.findElement(By.linkText("Re-Login Back")).click();
	}
	
	@Test (priority = 2)
	public void ModifyAccess() throws Exception 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);
		JavascriptExecutor jse= (JavascriptExecutor)driver;	
		
		driver.navigate().to(UserManagment);
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
		
		//Settings - Modify access
		System.out.println();
		System.out.println("Testing Settings - Modify Access ----->");
		action.sendKeys(Keys.PAGE_DOWN).perform();
		
		for(int i=2; i<=6;i++) 
		{
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("div.module-wrapper:first-of-type>div:nth-of-type("+i+")>div.flex.access-level-buttons>button:nth-of-type(3)")).click();
		}
		
		driver.findElement(By.cssSelector("button[data-type=access_level]")).click();//save button in access level
		driver.navigate().to("https://app.deposyt.com/index.php?m=usersmgmt&d=list");//user profile page
		driver.findElement(By.cssSelector("table>tbody>tr:first-of-type>td:nth-child(8)>div>a")).click();
		driver.findElement(By.cssSelector("table>tbody>tr:first-of-type>td:nth-child(8)>div>div>a:first-of-type")).click();//login to user account
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
		
		//Validation - By simple navigation- Checking all option are getting displayed
		System.out.println("	None/View Validations: ");
		driver.navigate().to(Settings);//Setting page
		
		try//business profile page
		{
			driver.findElement(By.cssSelector("div[onclick=\"document.location.href='index.php?m=settings&d=businessprofile';\"]")).isDisplayed();
			System.out.println("	Bussiness Profile Option is Displyed in Settings");
		}
		catch(Exception noSuchEelementException)
		{
			Assert.assertFalse(true,"Test Failed: Bussiness Profile page Option is Not Displyed in Seetings");
		}

		try//Security option
		{
			driver.findElement(By.cssSelector("div[onclick=\"window.location.href = 'index.php?m=employeedetails&d=security';\"]")).isDisplayed();
			System.out.println("	Security Option is Displyed in Settings");
		}
		catch(Exception noSuchEelementException)
		{
			Assert.assertFalse(true,"Test Failed: Security page Option is Not Displyed in Settings");
		}

		try//Calendar settings 
		{
			driver.findElement(By.cssSelector("div[onclick=\"document.location.href='index.php?m=settings&d=calendar&from=settings';\"]")).isDisplayed();
			System.out.println("	Calendar Setting Option is Displyed in Settings");
		}
		catch(Exception NoSuchEelementException)
		{
			Assert.assertFalse(true,"Test Failed: Calendar Setting page Option is Not Displyed in Settings");
		}

		try//My Profile 
		{
			driver.findElement(By.cssSelector("div[onclick=\"window.location.href = 'index.php?m=employeedetails&d=myprofile';\"]")).isDisplayed();
			
		}
		catch(Exception NoSuchEelementException)
		{
			Assert.assertFalse(true,"Test Failed: My Profile page Option is Not Displyed in Settings");
		}

		//Validations For Direct navigation and Edit
		System.out.println();
		System.out.println("	Edit Validations: ");

		driver.navigate().to("https://app.deposyt.com/index.php?m=settings&d=businessprofile");//Business profile page
		Assert.assertFalse(driver.getPageSource().contains("Access Denied!"));
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		try //Checking Submit button 
		{
			driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
			System.out.println("	User is Able to Edit the bussiness profile page");
		}
		catch(Exception ElementNotInteractableException) 
		{	
			Assert.assertFalse(true,"Test Failed: User is Unable to Edit The Bussiness Profile");
		}

		//Validations For Direct navigation and Edit For Security page
		driver.navigate().to("https://app.deposyt.com/index.php?m=employeedetails&d=security");//Security
		Assert.assertFalse(driver.getPageSource().contains("Access Denied!"));
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		try 
		{
			driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
			System.out.println("	User is Able to Edit the Security page");
		}
		catch(Exception ElementNotInteractableExceptione) 
		{
			Assert.assertFalse(true,"Test Failed: User is Unable to Edit Security page");
		}

		//Validations For Direct navigation and Edit For Calendar setting page
		driver.navigate().to("https://app.deposyt.com/index.php?m=settings&d=calendar&from=settings");//Calendar setting
		Assert.assertFalse(driver.getPageSource().contains("Access Denied!"));
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		try 
		{
			driver.findElement(By.cssSelector("#aptstatus>div>a")).click();
			driver.findElement(By.cssSelector("#appointmentstatusform>div:Last-child>button:last-child")).click();
			System.out.println("	User is able to Edit the Calendar Settings page");
		}
		catch(Exception ElementNotInteractableExceptione) 
		{
			Assert.assertFalse(true,"Test Failed: User is Unable to Edit Calendar Setting page");
		}

		//Validations For Direct navigation and Edit For My profile page
		driver.navigate().to("https://app.deposyt.com/index.php?m=employeedetails&d=myprofile");//my profile
		Assert.assertFalse(driver.getPageSource().contains("Access Denied!"));
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		try 
		{
			driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
			System.out.println("	User is able to Edit My Profile page");
		}
		catch(Exception ElementNotInteractableExceptione) 
		{
			Assert.assertFalse(true,"Test Failed: User is Unable to Edit the My profile page");
		}

		//Validations For Direct navigation and Edit For Access Level page
		driver.navigate().to("https://app.deposyt.com/index.php?m=usersmgmt&d=access_level_list");//access Level
		Assert.assertFalse(driver.getPageSource().contains("Access Denied!"));
		driver.findElement(By.cssSelector("#accesslevel_section > div:nth-child(3) > div > div.flex.justify-center.items-center.relative.mt-5.mr-4 > a.flex.justify-center.items-center.text-xs.font-normal.medium-gray-font.rounded-lg.px-4.mx-5.py-2.cursor-pointer.btn-edit.dp-btn-sm.dp-outline-btn.dp-cancel-btn")).click();
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		try 
		{
			driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
			System.out.println("	User is able to Edit Access Level page");
		}
		catch(Exception ElementNotInteractableExceptione) 
		{
			Assert.assertFalse(true,"Test Failed: User is unable to Edit Access Level page");
		}
		//Re- Login To Admin Account
		driver.findElement(By.xpath("//*[@id=\"dropdownMenu1\"]")).click();
		driver.findElement(By.linkText("Re-Login Back")).click();
	}
}
