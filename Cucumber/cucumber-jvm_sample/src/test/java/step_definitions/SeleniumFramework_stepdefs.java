package step_definitions;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;


public class SeleniumFramework_stepdefs{
    public WebDriver driver;

    
    @Before
    public void startDriver() throws MalformedURLException {
    	driver = new FirefoxDriver();
    }

      
    @Given("^I open seleniumframework website$")
    public void i_open_seleniumframework_website() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.get("http://www.seleniumframework.com");
        driver.manage().window().maximize();
        
    }

    @And("^I navigate to ABOUT link$")
    public void i_navigate_to_ABOUT_link() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.ignoring(WebDriverException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("ABOUT")));
        driver.findElement(By.linkText("ABOUT")).click();
    }

    @Then("^I print the html$")
    public void i_print_the_html() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
       System.out.println(driver.getTitle());
       
    }
    
    
    @After
    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public void quitDriver(Scenario scenario) {
       
        if(scenario.isFailed()) {
        try {
        	 scenario.write("Current Page URL is " + driver.getCurrentUrl());
            byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
        
        }
        driver.quit();
        
    }
    
}