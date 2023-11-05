package Modules;

import java.io.IOException;
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

public class CallLog extends Data{
	
	@Parameters({"uname", "pass", "url"})
	@Test(priority=1)
	public void LogFilter(String uname, String pass, String url) throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		Actions action = new Actions(driver);
		
//scroll
		WebElement e = driver.findElement(By.cssSelector("div.hidden.sidebar-bg-color"));
		action.moveToElement(e).perform();
		String navMisscall = driver.findElement(By.cssSelector("a[title=\"Call Log\"] > span:last-of-type")).getText();
		driver.findElement(By.cssSelector("a[title=\"Call Log\"]")).click();
		String[] CRMDATA = new String[7];
		
		Thread.sleep(5000);
		String Missedcalls = driver.findElement(By.cssSelector("a[href =\"index.php?m=customers&d=calllogs&filter=missed\"] span")).getText();
		Assert.assertEquals(navMisscall, Missedcalls, "Missed call Count mismatch Sidebar and Call log");
		
			driver.findElement(By.id("showCallLogFilters")).click();//Filter button

			for(int i=1;i<=7;i++) {
				driver.findElement(By.cssSelector("div.blocktitlesection:last-of-type>a:nth-of-type("+i+")")).click();

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
			
		//Getting Total log count from Database
			driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).clear();
			driver.findElement(By.cssSelector("pre[contenteditable=\"true\"]")).sendKeys("SELECT Count(id)\r\n"
					+ "FROM `dialer_calls`\r\n"
					+ "WHERE (`id_company` = "+cid+"\r\n"
					+ "AND `archived` = '0' AND `type` = '0')\r\n");
			action.keyDown(Keys.CONTROL).sendKeys(Keys.ENTER).keyUp(Keys.CONTROL).build().perform();
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
					+ "AND `archived` = '0' AND `type` = '0')\r\n");
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
			System.out.println("		Outging Call Count from CRM: "+CRMDATA[2]);
			System.out.println("		Outging call Count From DB: "+DBoutcall);
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
			Assert.assertEquals(CRMDATA[0], DBcountTotal,"Total Logs Count Mismatched");
			Assert.assertEquals(CRMDATA[1], DBcountTotal,"Missed call Count Mismatched");
	}
}
