package test.java.com.selenium.example;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

 
@RunWith(Cucumber.class)
@CucumberOptions(
		format = {"pretty", "html:target/cucumber"},
				glue="test.java.com.selenium.example",
		features="src/test/resources/com/selenium/example/"
		)
public class TestRunner {
}
