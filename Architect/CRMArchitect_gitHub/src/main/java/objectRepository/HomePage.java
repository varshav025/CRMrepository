package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.WebDriverUtility;

public class HomePage {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(linkText = "Organizations")
	private WebElement orgLink;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactLink;
	
	@FindBy(partialLinkText = "More")
	private WebElement moreLink;
	
	@FindBy(linkText = "Campaigns")
	private WebElement campLink;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement administrator;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signOutLink;

	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getContactLink() {
		return contactLink;
	}

	public WebElement getMoreLink() {
		return moreLink;
	}

	public WebElement getCampLink() {
		return campLink;
	}

	public WebElement getAdministrator() {
		return administrator;
	}

	public WebElement getSignOutLink() {
		return signOutLink;
	}
	WebDriverUtility wutil= new WebDriverUtility();
	
	public void logoOutOfApp() {
	wutil.moveToElement(driver, administrator);
	signOutLink.click();
	}

	public void logout() {
		Actions action= new Actions(driver);
		action.moveToElement(administrator).click().perform();
		signOutLink.click();
	}
	public void navigateToCampaign() {
		Actions action= new Actions(driver);
		action.moveToElement(moreLink).perform();
		campLink.click();
	}

}
