package listener;

import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestListener extends TestListenerAdapter {

	public ExtentSparkReporter sparkFail;
	public ExtentSparkReporter sparkAll;
	public ExtentReports extent;
	public ExtentTest test;

	public void onStart(ITestContext testContext) {
		String root_path = System.getProperty("user.dir");
		String all_report_path = root_path + "/Reports/completeReport.html";
		String fail_report_path = root_path + "/Reports/failReport.html";
		final File CONF = new File(root_path + "/src/test/resources/config/report-config.json");
		sparkFail = new ExtentSparkReporter(fail_report_path).filter().statusFilter().as(new Status[] { Status.FAIL })
				.apply();
		sparkAll = new ExtentSparkReporter(all_report_path);
		try {
			sparkAll.loadJSONConfig(CONF);
		} catch (IOException e) {
			e.printStackTrace();
		}

		extent = new ExtentReports();

		extent.attachReporter(sparkAll, sparkFail);
		extent.setSystemInfo("Project Name", "Test Automation");
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "Dev");
		extent.setSystemInfo("user", "QA");

	}

	public void onTestSuccess(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName() + ": " + result.getName()); // create new entry in the
																								// report

		test.log(Status.PASS, "Test Case PASSED : " + result.getTestClass() + " " + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName()); // create new entry in the report

		test.log(Status.FAIL, "TEST CASE FAILED : " + result.getTestClass() + " " + result.getName()); // to add name in
																										// extent report
		test.log(Status.FAIL, "TEST DESCRIPTION : " + result.getMethod().getDescription());

	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName()); // create new entry in the report
		test.log(Status.SKIP, "Test Case SKIPPED : " + result.getTestClass() + " " + result.getName());
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

}