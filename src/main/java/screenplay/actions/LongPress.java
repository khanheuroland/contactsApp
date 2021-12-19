package screenplay.actions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.WebDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.interactions.touch.TouchActions;

import java.time.Duration;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class LongPress implements Interaction {
    Target targetLocator;
    public LongPress(Target target)
    {
        this.targetLocator = target;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = ((WebDriverFacade) BrowseTheWeb.as(actor).getDriver()).getProxiedDriver();
        /*
        AndroidTouchAction touch = new AndroidTouchAction((AppiumDriver)driver);
        touch.longPress(LongPressOptions.longPressOptions()
                        .withElement (ElementOption.element (targetLocator.resolveFor(actor))))
                .perform ();
        */
        TouchActions action = new TouchActions(driver);
        action.clickAndHold(this.targetLocator.resolveFor(actor)).perform();
    }

    public static Interaction on(Target target)
    {
        return instrumented(LongPress.class, target);
    }
}
