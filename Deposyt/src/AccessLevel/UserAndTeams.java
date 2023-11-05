package AccessLevel;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class UserAndTeams extends Data {
	
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
		
		action.scrollByAmount(0, 900).perform();
		
		for(int i=2; i<=6;i++) 
		{
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("div.module-wrapper:first-of-type>div:nth-of-type("+i+")>div.flex.access-level-buttons>button:first-of-type")).click();
		}
	}

}
