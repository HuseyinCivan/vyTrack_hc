
package com.vytrack.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {
    //same for everyone
    private static WebDriver driver;

    //so no one can create object of Driver class
    //everyone should call static getter method instead
    private Driver() {

    }

    public static WebDriver getDriver() {
        //if webdriver object doesn't exist
        //create it
        if (driver == null) {
            //specify browser type in configuration.properties file
            String browser = ConfigurationReader.getProperty("browser").toLowerCase();
            switch (browser) {


                case "chrome":
                    WebDriverManager.chromedriver().version("79").setup();
                    driver = new ChromeDriver();
                    break;

                case "chromeHeadless":
                    WebDriverManager.chromedriver().version("79").setup();
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;

                case "firefoxHeadless":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true));
                    break;

                case "ie":
                    if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                        throw new WebDriverException("You are operating Mac OS which doesn't support Internet Explorer");
                    } else {
                        WebDriverManager.iedriver().setup();
                        driver = new InternetExplorerDriver();
                    }
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;

                case "safari":
                    if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                        throw new WebDriverException("You are operating Windows OS which doesn't support Safari");
                    } else {
                        WebDriverManager.getInstance(SafariDriver.class).setup();
                        driver = new SafariDriver();
                    }
                    break;

                default:
                    throw new RuntimeException("Illegal browser type!");
            }
        }
        return driver;
    }

        public static void closeDriver () {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        }
    }
