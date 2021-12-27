package contacts.acceptancetests;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/resources/features/ManageContact.feature", glue= "contacts.steps", dryRun = false)
public class AcceptanceTestSuite {}

