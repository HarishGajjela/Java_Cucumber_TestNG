package utility;

import static utility.UrlVariables.BASE_URL;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebdriverSettings {

	public static WebDriver driver;

	@BeforeClass
	public static void setup() {

		// code for initial setting like environment variables

	}

	@BeforeMethod
	@Parameters("browser")
	public static WebDriver driverSettings(String browser) {
		Switch(browser);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get(BASE_URL);
		return driver;
	}

	public static WebDriver Switch(String browser) {

		if (browser.equalsIgnoreCase("FF")) {
			System.setProperty("webdriver.firefox.driver", "driver//firefoxdriver.exe");

			driver = new FirefoxDriver();

		} else if (browser.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", "driver//iedriver.exe");
			driver = new InternetExplorerDriver();

		} else if (browser.equalsIgnoreCase("Chrome")) {
			//System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
			options.addArguments("--headless");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);

		} else if (browser.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver", "driver//msedgedriver.exe");
			driver = new ChromeDriver();
		}
		return driver;
	}

	@AfterMethod
	public void tearDown() {

		driver.close();
	}

	@AfterClass()
	public static void finish() {
		driver.quit();

	}
}
