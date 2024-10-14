package utility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
	public void waitForPagToLoad(WebDriver driver) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	public void waitForVisibilityOfElement(WebDriver driver, WebElement element) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void switchToFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}
	public void switchToFrame(WebDriver driver, String nameOrId) {
		driver.switchTo().frame(nameOrId);
	}
	
	public void switchToFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}
	public void select(WebElement element, int index) {
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}
	public void select(WebElement element, String value) {
		Select sel = new Select(element);
		sel.selectByValue(value);
	}
	public void select(String visibleText, WebElement element) {
		Select sel = new Select(element);
		sel.selectByVisibleText(visibleText);
	}
	public void switchToAlertAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void toSwitchToWindow(WebDriver driver, String partialExpTitle) {
		Set<String> allIds = driver.getWindowHandles();
		for(String winId: allIds) {
			String windowTitle= driver.switchTo().window(winId).getTitle();
			if(windowTitle.contains(partialExpTitle)) {
				break;
			}
		}
	}
	public void moveToElement(WebDriver driver, WebElement ele) {
		Actions act= new Actions(driver);
		act.moveToElement(ele).perform();		
	}
	public void doubleClick(WebDriver driver, WebElement ele) {
		Actions act= new Actions(driver);
		act.doubleClick(ele).perform();
	}
	public void dragAndDrop(WebDriver driver, WebElement src, WebElement target) {
		Actions act= new Actions(driver);
		act.dragAndDrop(src, target).perform();
	}
		
	public void takeScreenshotOfWebPage(WebDriver driver, String ssName) throws IOException {
		TakesScreenshot tks= (TakesScreenshot) driver;
		File src = tks.getScreenshotAs(OutputType.FILE);
		File dest= new File("./errorShots/"+ssName+".png");
		FileHandler.copy(src, dest);
	}
	public void toScrollWindow(WebDriver driver, WebElement ele) {
		JavascriptExecutor jse= (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true)", ele);
		
	}
	

	
}
