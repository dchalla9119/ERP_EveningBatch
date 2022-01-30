package CommonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import Utilities.PropertyFileUtil;

public class FunctionLibrary {
	public static WebDriver driver;

	//method for launch browser
	public static WebDriver startBrowser() throws Throwable
	{
		if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dileep.Challa\\OneDrive\\Desktop\\eclipse december 2021\\Hybrid_ERP\\CommonDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();

		}
		else if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\Dileep.Challa\\OneDrive\\Desktop\\eclipse december 2021\\Hybrid_ERP\\CommonDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().deleteAllCookies();
		}
		else
		{
			Reporter.log("Browser value not matching",true);
		}
		return driver;
	}

	//method for url
	public static void openApplication(WebDriver driver) throws Throwable
	{
		driver.get(PropertyFileUtil.getValueForKey("Url"));
	}

	//method for wait for element
	public static void waitForElement(WebDriver driver,String locatortype,String locatorvalue,String testdata)
	{
		WebDriverWait myWait = new WebDriverWait(driver, Integer.parseInt(testdata));
		if(locatortype.equalsIgnoreCase("name"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorvalue)));
		}
		else if(locatortype.equalsIgnoreCase("xpath"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorvalue)));
		}
		else if(locatortype.equalsIgnoreCase("id"))
		{
			myWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(locatorvalue)));
		}
	}

	//method for type action
	public static void typeAction(WebDriver driver,String locatortype,String locatorvalue,String testdata)
	{
		if(locatortype.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorvalue)).clear();
			driver.findElement(By.id(locatorvalue)).sendKeys(testdata);
		}
		else if(locatortype.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(locatorvalue)).clear();
			driver.findElement(By.xpath(locatorvalue)).sendKeys(testdata);
		}
		else if(locatortype.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locatorvalue)).clear();
			driver.findElement(By.name(locatorvalue)).sendKeys(testdata);
		}
	}

	//method for click action
	public static void clickAction(WebDriver driver,String locatortype,String locatorvalue)
	{
		if(locatortype.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(locatorvalue)).click();
		}
		else if(locatortype.equalsIgnoreCase("name"))
		{
			driver.findElement(By.xpath(locatorvalue)).click();
		}
		else if(locatortype.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorvalue)).sendKeys(Keys.ENTER);
		}
	}

	//method for validating title
	public static void validateTitle(WebDriver driver,String expectedtitle)
	{
		String actualtitle = driver.getTitle();
		try {
			Assert.assertEquals(actualtitle, expectedtitle,"Title is not matching");
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage());
		}
	}

	//method for closing browser
	public static void closeBrowser(WebDriver driver)
	{
		driver.quit();
	}
}


