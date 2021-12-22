package screenplay.actions;

import io.appium.java_client.android.AndroidDriver;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class OpenNotifications implements Interaction {
    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = ((WebDriverFacade) BrowseTheWeb.as(actor).getDriver()).getProxiedDriver();
        ((AndroidDriver)driver).openNotifications();
    }

    public static Interaction onScreen()
    {
        return instrumented(OpenNotifications.class);
    }
}
