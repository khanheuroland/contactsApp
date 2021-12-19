package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class HomeScreen {
    public static final Target ADD_CONTACT = Target.the("Add new contact button")
            .located(By.id("com.google.android.contacts:id/floating_action_button"));

    public static final Target SELECT_CONTACT_SOURCE = Target.the("Select account button")
            .located(By.id("com.google.android.contacts:id/selected_account_disc"));

    public static final Target CONTACT_NAMES = Target.the("List of contact")
            .located(By.id("com.google.android.contacts:id/cliv_name_textview"));

    public static final Target CONTACT_AT = Target.the("Contact at {0}")
            .locatedBy("(//*[@resource-id=\"com.google.android.contacts:id/cliv_name_textview\"])[{0}]");

    public static final Target CONTACT_NAME = Target.the("Contact named {0}")
            .locatedBy("//android.widget.TextView[@content-desc=\"{0}\"]");
}
