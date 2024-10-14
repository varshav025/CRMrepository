package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "user_name")
	private WebElement userNameTF;
	
	@FindBy(name = "user_password")
	private WebElement passwordTF;
	
	@FindAll({@FindBy(id = "submitButton"),@FindBy(xpath = "//input[@value='Login' and @type='submit']")})
	private WebElement loginBtn;

	public WebElement getUserNameTF() {
		return userNameTF;
	}

	public WebElement getPasswordTF() {
		return passwordTF;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	public void loginToApp(String username, String password) {
		userNameTF.sendKeys(username);
		passwordTF.sendKeys(password);
		loginBtn.click();
		
	}//business method/logic/library
	

}
