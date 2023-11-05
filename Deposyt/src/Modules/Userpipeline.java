package Modules;

import java.time.Duration;
import java.util.List;

import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import AccessLevel.Data;

public class Userpipeline extends Data{
	@Test
	public void f() throws Exception {
		JavascriptExecutor jse= (JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Actions action = new Actions(driver);

		driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/div[1]")).click();
		jse.executeScript("document.querySelector('div#block0body > div > div > div > div > div > ul').scrollTop=300");

		String[] pipelineid,UID = null;

		driver.navigate().to("https://app.deposyt.com/index.php?m=usersmgmt&d=list");
		List <WebElement> user = driver.findElements(By.cssSelector("div.table-responsive>table>tbody>tr"));

		for(int h=1;h<=user.size(); h++) {
			driver.navigate().to("https://app.deposyt.com/index.php?m=usersmgmt&d=list");
			driver.findElement(By.cssSelector("tr:nth-of-type("+h+")>td>div>a#usertableoptions")).click();
			UID = new String[user.size()];
			UID[h-1]= driver.findElement(By.cssSelector("div.table-responsive>table>tbody>tr:nth-of-type("+h+")")).getAttribute("data-id");
			System.out.println(UID[h-1]);
			driver.findElement(By.cssSelector("div.open>div.dropdown-menu.useroptions>a:first-of-type")).click();
			driver.switchTo().alert().accept();

			try {
				action.moveToElement(driver.findElement(By.cssSelector("div.hidden.sidebar-bg-color"))).perform();
			}
			catch(Exception e) {
				action.moveToElement(driver.findElement(By.cssSelector("div.hidden.sidebar-bg-color"))).perform();
			}
			driver.findElement(By.cssSelector("a[title=\"Pipelines\"]")).click();

			Thread.sleep(2000);

			driver.findElement(By.xpath("//*[@id=\"block0body\"]/div/div[1]/div/div[1]/div[1]")).click();

			List <WebElement> pipelinelist = driver.findElements(By.cssSelector("div.blocktitlesection>div>ul.list>li"));

			pipelineid = new String[pipelinelist.size()];
			System.out.println("Pipeline numbers:"+pipelinelist.size() );
			for(int i=1; i<=pipelinelist.size();i++)
			{
				pipelineid[i-1] = driver.findElement(By.cssSelector("div.blocktitlesection>div>ul>li:nth-of-type("+i+")")).getAttribute("data-value");
			}
			for (String a:pipelineid)
			{
				System.out.println(a);
			}
			driver.findElement(By.id("dropdownMenu1")).click();
			driver.findElement(By.cssSelector("div[aria-labelledby=\"dropdownMenu1\"]>a:nth-of-type(3)")).click();
		}
	}
}