package runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import testbase.TestBase;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/resources/features",glue="cucumbersteps")
public class RunCuke extends TestBase{

}
