package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLogOutPage {
@FindBy(xpath="(//a[starts-with(text(),' Logout')])[2]")
WebElement Objlogout;
public void adminLogout()
{
	
	Objlogout.click();
}

}
