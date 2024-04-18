package driverfactory1;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.CustomerPage;
import config.AppUtil1;
import utilities.ExcelFileUtil;

public class AppTest  extends AppUtil1{
	String inputpath="./FileInput/CustomerData.xlsx";
	String outputpath="./FileOutput/CustomerResults.xlsx";
	ExtentReports report;
	ExtentTest logger;
	@Test
	public void startTest() throws Throwable
	{
		//define path for generate extent reports
		report=new ExtentReports("./target/Reports/Customer.html");
		CustomerPage cus=PageFactory.initElements(driver, CustomerPage.class);
		ExcelFileUtil xl=new ExcelFileUtil(inputpath);
		int rc=xl.rowCount("Customer");
		Reporter.log("No of rows are::"+rc,true);
		for(int i=1;i<=rc;i++)
			{
				logger=report.startTest("Customer Module");
				logger.assignAuthor("Ranga");
				String customername=xl.getCellData("Customer",i,0);
				String Address=xl.getCellData("Customer",i,1);
				String City=xl.getCellData("Customer",i,2);
				String Country=xl.getCellData("Customer",i,3);
				String contactperson=xl.getCellData("Customer",i,4);
				String phonenumber=xl.getCellData("Customer",i,5);
				String email=xl.getCellData("Customer",i,6);
				String mobilenumber=xl.getCellData("Customer",i,7);
				String Notes=xl.getCellData("Customer",i,8);
				logger.log(LogStatus.INFO,customername+"  "+Address+" "+City+" "+Country+" "+contactperson+" "+phonenumber+" "+email+" "+mobilenumber+" "+Notes);
				boolean res=cus.add_Customer(customername, Address, City, Country, contactperson, phonenumber, email, mobilenumber, Notes);
				if(res)
				{
					xl.setCellData("Customer", i, 9, "Pass", outputpath);
					logger.log(LogStatus.PASS,"Customer Added Success");
				}
				else
				{
					xl.setCellData("Customer", i, 9, "Fail", outputpath);
					logger.log(LogStatus.FAIL,"Customer Added Success");
				}
				
				report.endTest(logger);
				report.flush();
			}
			;
		
		
	}
}
