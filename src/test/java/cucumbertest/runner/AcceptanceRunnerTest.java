package cucumbertest.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format={"json:target/Destination/cucumber.json", "pretty", "html:target/cucumber"},
        //format = { "pretty", "html:target/cucumber" },
        //features = { "classpath:catalog/tests" },
        features = { "classpath:acceptance/tests" },
        glue = {"microservice/testlibraries"}
)

public class AcceptanceRunnerTest {
}
