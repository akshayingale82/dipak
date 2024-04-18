package driverFactory;


import java.io.File;

import org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class AppTest extends AppUtil {
String inputpath="./FileInput/LoginData.xlsx";
String outputpath="./FileOutput/DataDrivenResults.xlsx";
ExtentReports report;
ExtentTest logger;
@Test
public void startTest() throws Throwable
{
	//define path of html report
	report=new ExtentReports("./target/Reports/LoginReports.html");
	//create object for ExcelfileUtil class
	ExcelFileUtil xl=new ExcelFileUtil(inputpath);
	//count no of rows in login sheet
	int rc =xl.rowCount("Login");
	Reporter.log("No of rows::"+rc,true);
	//iterate all rows in login sheet
	for(int i=1;i<=rc;i++)
	{
		logger=report.startTest("Validate Login");
		logger.assignAuthor("Akshay");
		String username=xl.getCellData("Login", i, 0);
		String password=xl.getCellData("Login", i, 1);
		logger.log(LogStatus.INFO, username+"---"+password);
		// call loginn method from function library class
		boolean res=FunctionLibrary.adminLogin(username, password);
		if(res)
		{
			//if res is true write as login success into results cell
			xl.setCellData("Login", i, 2, "Login Success","outputpath");
			//if res is true write as 
			xl.setCellData("Login", i, 3, "Pass", "outputpath");
			logger.log(LogStatus.PASS, "username and password are valid");
		}
		else
		{
			// take screen shot for fail
			File screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen, new File("./Screenshot/Iteration/"+i+"LoginPage.png"));
			//if res is fail write as login success into results cell
			xl.setCellData("Login", i, 2, "Login Fail","outputpath");
			//if res is false write as 
			xl.setCellData("Login", i, 3, "Fail", "outputpath");
			logger.log(LogStatus.FAIL, "Username and password are not valid");
		}
		report.endTest(logger);
		report.flush();
		
	}
}

}
