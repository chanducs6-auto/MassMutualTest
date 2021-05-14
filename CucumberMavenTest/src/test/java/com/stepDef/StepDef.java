package com.stepDef;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.generic.BaseClass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import PageObject.PageObject;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDef {

	public PageObject pageObject=new PageObject();
	public static WebDriver driver;
	public String testcasename;
	public static ExtentReports extent;
	public static ExtentTest test;
	static String extentreportPath;
	static String filename;
	public static BaseClass base;

	@Before()
	public void setUp() {


		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MMM_yyyy hh mm ss SSS");
		String formattedDate = sdf.format(date);
		String suiteStartTime = formattedDate.replace(":","_").replace(" ","_");
		filename = "MassMutualTest";
		extentreportPath = System.getProperty("user.dir")+"/ExtentReports/"+filename+""+suiteStartTime+".html";


		extent = new ExtentReports(extentreportPath,false);
		extent.addSystemInfo("Environment", "UAT2");
		extent.addSystemInfo("Browser", "Chrome");
		extent.loadConfig(new File(System.getProperty("user.dir")+"/config.xml"));
		test = extent.startTest(filename);

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		base = new BaseClass(driver , test);
	}

	@Given("^User navigate to balance test page$")
	public void User_navigate_to_Gmail_signup_page() throws Throwable {
		base.launchApplication();
	}

	@When("^User verifies right coloumn values appears on the page$")
	public void User_enter_firstname_as() throws Throwable {
		base.veirfyElementPresent(pageObject.balance1, "first balance");
		base.veirfyElementPresent(pageObject.balance2, "second balance");
		base.veirfyElementPresent(pageObject.balance3, "third balance");
		base.veirfyElementPresent(pageObject.balance4, "fourth balance");
		base.veirfyElementPresent(pageObject.balance5, "fifth balance");
	}

	@When("^User verify all values greater than zero$")
	public void User_enter_lastname_as() throws Throwable {
		base.veirfyValuesgreaterThanZero(pageObject.allBalance, "balances ");
	}

	@When("^User verify total balance is sum of all balance$")
	public void User_enter_email_id_as() throws Throwable {
		base.veirfyTotalBalance(pageObject.allBalance, pageObject.totalBalance , "Total balance match");
	}


	@When("^User verify balance is formatted in currency$")
	public void User_enter_password_as() throws Throwable {
		base.veirfyBalanceFormatterInCurrency(pageObject.allBalance, "balance currency format");
	}

	
	@After()
	public void tearDown() {
		driver.quit();
		extent.flush();

	}


}
