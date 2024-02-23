package DuckDuckGo.Runner;

import DuckDuckGo.Utils.Driver;
import io.cucumber.java.AfterAll;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features",
        glue = "DuckDuckGo/StepDefinitions",
        dryRun = false,
        tags = "@DuckDuckGo",
        plugin = {"pretty","html:target/cucumber-reports"}
)
public class Runner {

}
