package com.assignment.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;

import com.assignment.utils.Utilities;



public class TestBase {

	public static  WebDriver driver;
	static Properties prop = new Properties();
	static Properties prop1 = new Properties();
	public static String file;
	
	public static void loadProperties(){

		File file = new File(System.getProperty("user.dir")+ "/src/test/resources/configFiles/Properties.properties");
		File datafile = new File(System.getProperty("user.dir")+ "/src/test/resources/testData/TestData.properties");
		
		try {
			FileInputStream fis = new FileInputStream(file);
			FileInputStream fis1 = new FileInputStream(datafile);
			prop.load(fis);
			prop1.load(fis1);


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static WebDriver initializeDriver(String browser) {

		if(getValue("browser").equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);

		}else if(getValue("browser").equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		return driver;
	}


	public static void navigateToUrl(WebDriver driver, String url) {
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		
	}


	public static void takeScreenshot(String fileDest) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File(fileDest));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@BeforeSuite
	public void setup() {
		loadProperties();
		
	}

	public static String getValue(String key) {
		return prop.getProperty(key);
	}
	
	public static String getData(String key) {
		return prop1.getProperty(key);
	}

	
}
