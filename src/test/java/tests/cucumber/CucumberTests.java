package tests.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "steps",
        plugin = {
                "pretty",
                "json:target/cucumber-report.json",
                "html:target/cucumber-report",
                "io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm",
                //"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        }
)
public class CucumberTests {
}
