package AccessLevel;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Data //Imp Class all Navigation, Local paths, login credentials and login process is saved in this class
{ 
	//Modules
	protected String url		= "https://app.deposyt.com/";
	protected String Dashboard	= url; //Dashboard page
	protected String Messages 	= url+"index.php?m=conversation"; //Message module
	protected String CallLog	= url+"index.php?m=customers&d=calllogs"; // Call Log
	protected String Customers	= url+"index.php?m=customers"; //Customer
	protected String Pipelines	= url+"index.php?m=pipelines"; //Pipelines
	protected String Calendar	= url+"index.php?m=appointments&d=tableview"; // Calendar 
	protected String Lists		= url+"index.php?m=contactlist"; // List Module
	protected String Notes 	 	= url+"index.php?m=notesmain&d=allnotes"; //Notes&Task
	protected String Campaigns	= url+"index.php?m=campaigns";// Campaign
	protected String Forms	 	= url+"index.php?m=addformtemplates";//Form
	protected String Funnels 	= url+"index.php?m=landingpage";//Funnel
	protected String Domains 	= url+"index.php?m=landingpage&d=manage_domain";//Domain
	protected String Triggers	= url+"index.php?m=triggers";//Triggers
	protected String Videos	 	= url+"index.php?m=ffmpeg";//Videos
	protected String Support	= url+"index.php?m=help&d=list";//Support
	protected String Sales	 	= url+"index.php?m=purchases";//Sales
	protected String Products	= url+"index.php?m=products";//products
	protected String Settings	= url+"index.php?m=settings&d=useractions";//Settings page
	protected String MyProfile	= url+"index.php?m=employeedetails&d=myprofile";//My Profile Page
	protected String UserManagment	 	= url+"index.php?m=usersmgmt&d=list";//User management page
	protected String AccessLevelPage 	= url+"index.php?m=usersmgmt&d=access_level_list";//Access level list page
	protected String AppointmentTypes 	= url+"index.php?m=appointments&d=eventtypes";// Appointment Types
	protected String Availibility		= url+"index.php?m=appointments&d=eventavailability";
	protected String TagManager	= url+"index.php?m=tags";
	
	
	//Database
	protected String DBURL		 = "https://app.deposyt.com/files/adminer.php" ;
	protected String DBServer 	 = "deposyt-prod.cluster-c5kd5q6fphiu.us-west-2.rds.amazonaws.com";
	protected String DBUName	 = "centz_prod";
	protected String DBPass		 = "aDYiFw9eRPQWC7AP2Jyr";
	protected String DB			 = "deposyt_prod";
	
	//CRM Logins
	protected String User2login	 = "nadsoft.test02@gmail.com";
	protected String User2pass	 =	"n2Nafcqm";
	
	protected String MyAccLogin	 = "rohan@nadsoftdesign.com";
	protected String MyAccPass 	 = "icruMOgk";
	
	protected String DaveLogin 	 = "davidcarlinpayments@gmail.com";
	protected String DavePass	 = "jugNRBaT";
	
	protected String SALogin 	 = "admin";
	protected String SAPass		 = "LafC!TQgk2ghQv3WIi";
	protected String SATestAct	 = "https://app.deposyt.com/index.php?m=companiesmgmt&d=switchcompany&id=1";
	
	//webmail login
	protected String WMLogin 	 = "qa@nadsoftdesign.com";
	protected String WMPass		 = "qa@123#";
	
	//Local Paths
	protected String imgpath = "C:\\Users\\Rohan kokare\\eclipse-workspace\\CentzCRM\\Sources\\Test Images\\" ;
	protected String CSVpath = "C:\\Users\\Rohan kokare\\dummydata1.csv";


	public WebDriver driver;

	@Parameters({"bname","uname","pass"})
	@BeforeSuite
	public void invokebrowser(String bname, String uname, String pass) 
	{

		switch(bname) 
		{
		case"chrome":
			//WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Rohan kokare\\eclipse-workspace\\CentzCRM\\Sources\\Drivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Users\\Rohan kokare\\Downloads\\chrome-win64 (1)\\chrome-win64\\chrome.exe");
			driver = new ChromeDriver();
			break;
		case"edge":
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
			break;
		case"firefox":
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
			break;
		default:
			System.err.println("invalid browser name");
			break;
		}

		driver.manage().window().maximize();
		driver.get(url);
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(3));
		WebElement emf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		emf.sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pass);
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[4]/button[1]")).click();
	}
	public void logout(){
		driver.findElement(By.xpath("//*[@id=\"dropdownMenu1\"]")).click();
		driver.findElement(By.linkText("Logout")).click();
	}
}
