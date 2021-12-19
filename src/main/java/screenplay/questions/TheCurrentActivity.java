package screenplay.questions;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.WebDriver;

public class TheCurrentActivity implements Question<String> {
    @Override
    public String answeredBy(Actor actor) {
        WebDriver driver = ((WebDriverFacade) BrowseTheWeb.as(actor).getDriver()).getProxiedDriver();
        return ((AndroidDriver<MobileElement>) driver).currentActivity();
    }

    /***
     * Get the Name of screen activity
     * @return Question<String>
     */
    public static Question<String> Name()
    {
        return new TheCurrentActivity();
    }
}
