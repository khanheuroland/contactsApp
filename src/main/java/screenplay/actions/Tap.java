package screenplay.actions;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Tap implements Interaction {
    Target targetElement;
    int holdSecond = 0;
    boolean swipeLeft=false;
    boolean swipeRight=false;
    boolean swipeUp=false;
    boolean swipeDown=false;
    public Tap(Target targetElement, int holdSecond,
               boolean swipeLeft, boolean swipeRight, boolean swipeUp, boolean swipeDown)
    {
        this.targetElement = targetElement;
        this.holdSecond = holdSecond;
        this.swipeLeft = swipeLeft;
        this.swipeRight = swipeRight;
        this.swipeUp =  swipeUp;
        this.swipeDown = swipeDown;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = ((WebDriverFacade) BrowseTheWeb.as(actor).getDriver()).getProxiedDriver();
        AndroidTouchAction touch = new AndroidTouchAction((AndroidDriver<MobileElement>)driver);
        WebElement targetElement = this.targetElement.resolveFor(actor);
        int startX = targetElement.getLocation().x + targetElement.getSize().width/2;
        int startY = targetElement.getLocation().y + targetElement.getSize().height/2;

        if(this.swipeLeft)
        {
            touch.longPress(PointOption.point(startX, startY))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(this.holdSecond)))
                    .moveTo(PointOption.point(0,startY))
                    .release()
                    .perform();
        }
        else if(this.swipeRight)
        {
            touch.longPress(PointOption.point(startX, startY))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(this.holdSecond)))
                    .moveTo(PointOption.point(startX + targetElement.getSize().width/2, startY))
                    .release()
                    .perform();
        }
        else if(this.swipeUp)
        {
            touch.longPress(PointOption.point(startX, startY))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(this.holdSecond)))
                    .moveTo(PointOption.point(startX, 0))
                    .release()
                    .perform();
        }
        else if(this.swipeDown)
        {
            touch.longPress(PointOption.point(startX, startY))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(this.holdSecond)))
                    .moveTo(PointOption.point(startX, startY + targetElement.getSize().height/2))
                    .release()
                    .perform();
        }
        else {
            if (holdSecond > 0)
            {
                touch.longPress(PointOption.point(startX, startY))
                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(this.holdSecond)))
                        .release()
                        .perform();
            }
            else
            {
                touch.press(PointOption.point(startX, startY)).perform();
            }
        }
    }

    public static TapActionBuilder on(Target target)
    {
        return new TapActionBuilder(target);
    }

    public static class TapActionBuilder{
        private Target element;
        private int holdSecond;
        private boolean swipeLeft;
        private boolean swipeRight;
        private boolean swipeUp;
        private boolean swipeDown;
        public TapActionBuilder(Target target)
        {
            this.element = target;
        }

        public TapActionBuilder inSecond(int holdSecond)
        {
            this.holdSecond = holdSecond;
            return this;
        }

        public TapActionBuilder thenSwipeLeft()
        {
            this.swipeRight = false;
            this.swipeUp = false;
            this.swipeDown = false;
            this.swipeLeft = true;
            return this;
        }

        public TapActionBuilder thenSwipeRight()
        {
            this.swipeRight = true;
            this.swipeLeft =false;
            this.swipeUp = false;
            this.swipeDown=false;
            return this;
        }

        public TapActionBuilder thenSwipeDown()
        {
            this.swipeRight = false;
            this.swipeLeft = false;
            this.swipeUp = false;
            this.swipeDown = true;
            return  this;
        }

        public TapActionBuilder thenSwipeUp()
        {
            this.swipeRight = false;
            this.swipeLeft = false;
            this.swipeUp = true;
            this.swipeDown = false;
            return  this;
        }

        public Performable release()
        {
            return instrumented(Tap.class, this.element, this.holdSecond,
                    this.swipeLeft, this.swipeRight, this.swipeUp, this.swipeDown);
        }
    }


}
