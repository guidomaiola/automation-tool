package test.java.com.selenium.example;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class stepDefinition {

  WebDriver driver = null;
  WebElement enter = null;
  String OS = System.getProperty("os.name").toLowerCase();

  @Before
  public void setUp() {
    if (OS.contains("windows")) {
      System.setProperty("webdriver.gecko.driver",
          "src/test/resources/com/selenium/example/windows/geckodriver.exe");
    } else if (OS.contains("linux")) {
      System.setProperty("webdriver.gecko.driver",
          "src/test/resources/com/selenium/example/linux/geckodriver");
    }
    
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setBrowserName("firefox");
    capabilities.setCapability("acceptInsecureCerts", true);
    
    driver = new FirefoxDriver(capabilities);
    driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS).implicitlyWait(30, TimeUnit.SECONDS);
  }

  @After
  public void after() {
    driver.quit();
  }

  @Given("^I navigate to \"([^\"]*)\" in firefox$")
  public void i_navigate_to(String url) throws Throwable {
    setUp();
    driver.get(url);
  }

  @Given("^I press enter into field having xpath \"([^\"]*)\"$")
  public void i_press_enter_into_input_field_having_xpath(String xpath) throws Throwable {
    WebElement element = driver.findElement(By.xpath(xpath));
    element.click();
  }

  @Given("^I press enter into element with id \"([^\"]*)\"$")
  public void i_press_enter_into_input_field_having_id(String id) throws Throwable {
    WebElement element = driver.findElement(By.id(id));
    element.sendKeys(Keys.ENTER);
  }

  @Given("^I enter \"([^\"]*)\" into input field having xpath \"([^\"]*)\"$")
  public void i_enter_into_input_field_having_xpath(String fname, String xpath) throws Throwable {
    driver.findElement(By.xpath(xpath)).sendKeys(fname);
  }

  @Given("^I enter \"([^\"]*)\" into input field having id \"([^\"]*)\"$")
  public void i_enter_into_input_field_having_id(String fname, String id) throws Throwable {
    driver.findElement(By.id(id)).sendKeys(fname);
  }

  @When("^I wait for (\\d+) sec$")
  public void i_wait_for_sec(int sec) throws Throwable {
    Thread.sleep(sec * 1000);
  }

  @When("^take screenshot with filename \"([^\"]*)\"$")
  public void take_screenshot_with_filename(String fileName) throws Throwable {
    File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(scrFile, new File("src/Screenshot/" + fileName));
  }

  @Then("^I close browser$")
  public void i_close_browser() throws Throwable {
    driver.quit();
  }
}
