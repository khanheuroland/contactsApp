package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ContactSourceScreen {
    public static Target SOURCE_NAME = Target.the("On {0}")
            .locatedBy("//*[@text=\"{0}\"]");

    public static Target CONTACT_SOURCE_POPUP = Target.the("Contact sources popup")
            .located(By.id("com.google.android.contacts:id/action_bar_root"));
}
