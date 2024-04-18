package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class CustomerPage {
//define repository
	@FindBy(xpath="(//a[contains(text(),'Customers')])[2]")
	WebElement ObjCustomerLink;
	@FindBy(xpath="(//span[@data-caption='Add'])[1]")
	WebElement ObjAddIcon;
	@FindBy(name="x_Customer_Number")
	WebElement ObjCustomerNum;
	@FindBy(name="x_Customer_Name")
	WebElement ObjCustomerName;
	@FindBy(name="x_Address")
	WebElement ObjAddress;
	@FindBy(name="x_City")
	WebElement ObjCity;
	@FindBy(name="x_Country")
	WebElement ObjCountry;
	@FindBy(name="x_Contact_Person")
	WebElement ObjContactPerson;
	@FindBy(id="x_Phone_Number")
	WebElement ObjPhoneNumber;
	@FindBy(name="x__Email")
	WebElement ObjEmail;
	@FindBy(id="x_Mobile_Number")
	WebElement ObjMobileNumber;
	@FindBy(name="x_Notes")
	WebElement ObjNotes;
	@FindBy(name="btnAction")
	WebElement ObjClickAddBtn;
	@FindBy(xpath="//button[contains(text(),'OK!')]")
	WebElement ObjConfirmOkbtn;
	@FindBy(name="(//button[contains(text(),'OK')])[6]")
	WebElement ObjAlertOkBtn;
	@FindBy(xpath="//span[@data-caption='Search']")
	WebElement ObjSearchPanel;
	@FindBy(xpath="//input[@id='psearch']")
	WebElement ObjSearchTextBox;
	@FindBy(xpath="//button[@id='btnsubmit']")
	WebElement ObjSearchBtn;
	@FindBy(xpath="//table[@class='table ewTable']/tbody/tr[1]/td[5]/div/span/span")
	WebElement webtable;
	public boolean add_Customer(String customerName,String Address,String City,
			String Country,String contactperson,
			String PhoneNumber,String Email,String MobileNumber,String Notes) throws Throwable{
		

	{
		ObjCustomerLink.click();
		ObjAddIcon.click();
		String Exp_Num=ObjCustomerNum.getAttribute("value");
		ObjCustomerName.sendKeys(customerName);
		ObjAddress.sendKeys(Address);
		ObjCity.sendKeys(City);
		ObjCountry.sendKeys(Country);
		ObjContactPerson.sendKeys(contactperson);
		ObjPhoneNumber.sendKeys(PhoneNumber);
		ObjEmail.sendKeys(Email);
		ObjMobileNumber.sendKeys(MobileNumber);
		ObjNotes.sendKeys(Notes);
		ObjClickAddBtn.click();
		ObjConfirmOkbtn.click();
		Thread.sleep(2000);
		ObjAlertOkBtn.click();
		Thread.sleep(2000);
		//if search text box not displayed then click search panel
		if(!ObjSearchTextBox.isDisplayed())
			ObjSearchPanel.click();
		ObjSearchTextBox.sendKeys(Exp_Num);
		ObjSearchBtn.click();
		Thread.sleep(3000);
		String Act_Num=webtable.getText();
		Thread.sleep(3000);
		if(Exp_Num.equals(Act_Num))
		{
			Reporter.log("Customer Number Matching::"+Exp_Num+"    "+Act_Num,true);
			return true;
		}
		else
		{
			Reporter.log("Customer Number  Not Matching::"+Exp_Num+"    "+Act_Num,true);
			return false;
			
		}
		}
	}
	
}
