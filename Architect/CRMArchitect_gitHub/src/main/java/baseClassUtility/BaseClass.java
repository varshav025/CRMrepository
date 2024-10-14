package baseClassUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import objectRepository.HomePage;
import objectRepository.LoginPage;
import utility.PropertyFileUtility;
import utility.WebDriverUtility;

public class BaseClass {
	public WebDriver driver= null;
	public static WebDriver sdriver= null;
	PropertyFileUtility putil= new PropertyFileUtility();
	WebDriverUtility wutil= new WebDriverUtility();
	
	@BeforeSuite(groups = {"SmokeTest", "RegressionTest"})
	public void configBS() {
		Reporter.log("Established the database connection, Report configuration", true);
		
	}
	@BeforeTest(groups = {"SmokeTest", "RegressionTest"})
	public void configBT() {
		Reporter.log("Pre-condition", true);	
	}
	//@Parameters("Browser")//cross browser testing
	@BeforeClass(groups = {"SmokeTest", "RegressionTest"})
	public void configBC(/*String browser*/) throws IOException {
		String BROWSER = putil.readDataFromProperties("Browser");
		//String BROWSER= browser;
		String URL= putil.readDataFromProperties("Url");
		
		
		if(BROWSER.equalsIgnoreCase("chrome")) {
			driver= new ChromeDriver();
		}else if(BROWSER.equalsIgnoreCase("firefox")) {
			driver= new FirefoxDriver();
		}else if(BROWSER.equalsIgnoreCase("edge")) {
			driver= new EdgeDriver();
		}else {
			driver= new ChromeDriver();
		}
		sdriver= driver; //listener Implementation purpose
		driver.get(URL);
		driver.manage().window().maximize();
		wutil.waitForPagToLoad(driver);
		
	}
	@BeforeMethod(groups = {"SmokeTest", "RegressionTest"})
	public void configBM() throws IOException {
		String UNAME=putil.readDataFromProperties("Username");
		String PWD= putil.readDataFromProperties("Password");
		LoginPage lp= new LoginPage(driver);
		lp.loginToApp(UNAME, PWD);
		
	}
	@AfterMethod(groups = {"SmokeTest", "RegressionTest"})
	public void configAM() {
		HomePage hp= new HomePage(driver);
		hp.logout();
	}
	@AfterClass(groups = {"SmokeTest", "RegressionTest"})
	public void configAC() {
		driver.quit();
	}
	@AfterTest
	public void configAT() {
		Reporter.log("Post-condition", true);		
	}
	@AfterSuite(groups = {"SmokeTest", "RegressionTest"})
	public void configAS() {
		Reporter.log("Closed the database connection, report back up", true);
	}
	

}
