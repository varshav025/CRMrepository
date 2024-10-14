package listenerUtility;

import java.io.File;
import java.io.IOException;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import baseClassUtility.BaseClass;
import utility.javaUtility;

public class ListenerImplementationClass implements ITestListener, ISuiteListener{
public ExtentReports report;
public ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report configuration");
		javaUtility jutil= new javaUtility();
		String time = jutil.getSysDateForScreenshot();
		ExtentSparkReporter spark= new ExtentSparkReporter("./AdvanceReport/report_"+time+".html");
		spark.config().setDocumentTitle("VTIGER_CRM");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.STANDARD);
		report= new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows11");
		report.setSystemInfo("Browser", "Chrome");
		
	}

	@Override
	public void onFinish(ISuite suite) {
	System.out.println("Report Back-up");
	report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		
		System.out.println("======START====="+methodName+"========");
		report.createTest(methodName);
		test.log(Status.INFO, "======START====="+methodName+"========");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		
		test.log(Status.INFO, "======PASSED====="+methodName);
	    
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test= report.createTest(methodName);
		javaUtility jutil= new javaUtility();
		String currentTime = jutil.getSysDateForScreenshot();
		
		TakesScreenshot tks= (TakesScreenshot) BaseClass.sdriver;
		String temp = tks.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(temp, methodName+"_"+currentTime);
		test.log(Status.FAIL, "======FAILURE====="+methodName);
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("======SKIPPED====="+result.getMethod().getMethodName()+"========");
	}
	

}
