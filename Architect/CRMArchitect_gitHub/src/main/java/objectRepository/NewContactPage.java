package objectRepository;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewContactPage {
	WebDriver driver;

	public NewContactPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "lastname")
	private WebElement lastNameTF;
	
	@FindBy(name = "support_start_date")
	private WebElement startDate;
	
	@FindBy(name = "support_end_date")
	private WebElement endDate;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy (xpath = "//input[@name='account_name']/following-sibling::img")
	private WebElement orgInContactBtn;
	
	@FindBy(id = "search_txt")
	private WebElement searchTF;
	
	@FindBy(name = "search")
	private WebElement searchBtn;
	
	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img")
	private WebElement orgIncontact;

	
	public void getSupportSystemDate(String start, String end) {
		startDate.clear();
		startDate.sendKeys(start);
		endDate.clear();
		endDate.sendKeys(end);
	        
	}

	public void addContactWithOrg(String orgName) {
		String parentWindow = driver.getWindowHandle();
		orgIncontact.click();
			// switch to child window to add org
		Set<String> allWin = driver.getWindowHandles();
		allWin.remove(parentWindow);
		for (String currWIn : allWin) {
			driver.switchTo().window(currWIn);
			searchTF.sendKeys(orgName);
			searchBtn.click();
			driver.findElement(By.linkText(orgName)).click();
		}
		driver.switchTo().window(parentWindow);

	}
	
	@FindBy(id = "dtlview_Support Start Date")
	private WebElement actualStartDate;
	

	@FindBy(id = "dtlview_Support End Date")
	private WebElement actualEndDate;


	
	public WebElement getOrgIncontact() {
		return orgIncontact;
	}

	public WebElement getActualStartDate() {
		return actualStartDate;
	}

	public WebElement getActualEndDate() {
		return actualEndDate;
	}

	public WebElement getOrgInContactBtn() {
		return orgInContactBtn;
	}

	public WebElement getSearchTF() {
		return searchTF;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getLastNameTF() {
		return lastNameTF;
	}

	public WebElement getStartDate() {
		return startDate;
	}

	public WebElement getEndDate() {
		return endDate;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	
}
