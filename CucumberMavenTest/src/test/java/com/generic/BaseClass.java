package com.generic;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import static org.junit.Assert.*;

public class BaseClass {
	WebDriver driver;
	public static ExtentTest test;
	static Properties props = ToolUtil.openPropertyFile(new File(System.getProperty("user.dir")+File.separator+"config.properties"));

	public BaseClass(WebDriver driver , ExtentTest test){
		this.driver = driver;
		this.test = test;
	}

	public String takeScreenshot(String testCaseName) {
		String imageName=null;
		String imageName1=null;
		try {
			final File OUTPUT_DIRECTORY = new File(System.getProperty("user.dir")+"/ExtentReports/resultsfolder");
			File location = OUTPUT_DIRECTORY;
			// To be sure that the output directory already exist
			if(!location.exists()){
				location.mkdirs();
			}

			imageName=testCaseName+ToolUtil.getTimeSecondsStamp()+".png";
			File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			imageName1 = imageName;
			FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"/ExtentReports/resultsfolder"+File.separator+imageName));  
		}
		catch (IOException e)
		{
			e.printStackTrace();

		}   

		return "resultsfolder"+File.separator+imageName1;
	}

	public void launchApplication() throws Exception {
		try {
			String url = props.getProperty("URL");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30 , TimeUnit.SECONDS);
			driver.get(url);
			String screenshortName=takeScreenshot("launch url");
			test.log(LogStatus.PASS, "Launch application ","Successfully navigated to <b>"+url+"</b>"+test.addScreenCapture(screenshortName));

		} catch (RuntimeException localRuntimeException) {
			System.out.println("Failed to navgate to application:" + localRuntimeException.getMessage() + "Fail");
			String image = test.addScreenCapture("launch url");
			test.log(LogStatus.FAIL, "Launch application ", localRuntimeException+image);
			throw localRuntimeException;
		}
	}

	public void click(By elem , String elementDetails) throws Exception {
		try {
			driver.findElement(elem).click();
			String screenshortName=takeScreenshot(elementDetails);
			test.log(LogStatus.PASS, "Click on element","Clicked on <b>"+elementDetails+"</b>"+test.addScreenCapture(screenshortName));

		} catch (RuntimeException localRuntimeException) {
			System.out.println("Error in clicking the element:" + localRuntimeException.getMessage() + "Fail");
			String image = test.addScreenCapture(elementDetails);
			test.log(LogStatus.FAIL, "Click on element", localRuntimeException+image);
			throw localRuntimeException;
		}
	}

	public void SendKeys(By elem, String text , String elementDetails) throws Exception {
		try {
			driver.findElement(elem).clear();
			driver.findElement(elem).sendKeys(text);
			String screenshortName=takeScreenshot(elementDetails);
			test.log(LogStatus.PASS, "Enter Text", "<b>"+text+"</b> Entered in <b>"+elementDetails+"</b>"+test.addScreenCapture(screenshortName));

		} catch (RuntimeException localRuntimeException) {
			System.out.println("Error in entering the text in element:" + localRuntimeException.getMessage() + "Fail");
			String image = test.addScreenCapture(elementDetails);
			test.log(LogStatus.FAIL, "Enter Text", localRuntimeException+image);
			throw localRuntimeException;
		}
	}

	public void veirfyElementPresent(By elem, String elementDetails) throws Exception {
		try {
			driver.findElement(elem).isDisplayed();
			String screenshortName=takeScreenshot(elementDetails);
			test.log(LogStatus.PASS, "Element present", ": "+elementDetails+test.addScreenCapture(screenshortName));

		} catch (Exception e) {
			System.out.println("Error in finding element in page:" + e.getMessage() + "Fail");
			String image = test.addScreenCapture(elementDetails);
			test.log(LogStatus.FAIL, "Element is not found ", e+image);
			throw e;
		}
	}

	public void veirfyValuesgreaterThanZero(By elem, String elementDetails) throws Exception {
		try {
			List<WebElement> elements = driver.findElements(elem);
			for(WebElement e : elements) {
				assertTrue (Double.parseDouble(e.getText().replace("$", ""))>0);
			}
			String screenshortName=takeScreenshot(elementDetails);
			test.log(LogStatus.PASS, "Balance greater than zero", ": "+elementDetails+test.addScreenCapture(screenshortName));

		} catch (Exception e) {
			String image = test.addScreenCapture(elementDetails);
			test.log(LogStatus.FAIL, "Balance is not greater than zero ", e+image);
			throw e;
		}
	}
	
	public void veirfyTotalBalance(By balances, By ttlBal, String elementDetails) throws Exception {
		try {
			List<WebElement> elements = driver.findElements(balances);
			double balance = 0.00;
			for(WebElement e : elements) {
				balance = balance + Double.parseDouble(e.getText().replace("$", ""));
			}
			WebElement totalBalance = driver.findElement(ttlBal);
			double ttobalance = Double.parseDouble(totalBalance.getText().replace("$", ""));
			assertTrue (ttobalance==balance);
			String screenshortName=takeScreenshot(elementDetails);
			test.log(LogStatus.PASS, "Balance matched", ": "+elementDetails+test.addScreenCapture(screenshortName));

		} catch (Exception e) {
			String image = test.addScreenCapture(elementDetails);
			test.log(LogStatus.FAIL, "Total balance is not matching ", e+image);
			throw e;
		}
	}
	
	public void veirfyBalanceFormatterInCurrency(By elem, String elementDetails) throws Exception {
		try {
			List<WebElement> elements = driver.findElements(elem);
			for(WebElement e : elements) {
				assertTrue (e.getText().trim().charAt(0) == '$');
			}
			String screenshortName=takeScreenshot(elementDetails);
			test.log(LogStatus.PASS, "Balance formatted in currency", ": "+elementDetails+test.addScreenCapture(screenshortName));

		} catch (Exception e) {
			String image = test.addScreenCapture(elementDetails);
			test.log(LogStatus.FAIL, "Balance not formatted in currency ", e+image);
			throw e;
		}
	}



}
