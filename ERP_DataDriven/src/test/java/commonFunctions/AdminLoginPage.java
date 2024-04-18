package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLoginPage {
//define repository
	@FindBy(name ="btnreset")
	WebElement ObjResetBtn;
	@FindBy(xpath="//input[@id='username']")
	WebElement Objuser;
	@FindBy(id="password")
	WebElement Objpass;
	@FindBy(name="btnsubmit")
	WebElement ObjLoginbtn;

	public void adminLogin(String user, String pass)
	{
		ObjResetBtn.click();
		Objuser.sendKeys(user);
		Objpass.sendKeys(pass);
		ObjLoginbtn.click();
		
	}

}
