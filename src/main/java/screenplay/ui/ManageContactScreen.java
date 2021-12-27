package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ManageContactScreen {
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

    public static final Target SELECT_CONTACT_TOOLBAR = Target.the("Selected action")
            .located(By.id("com.google.android.contacts:id/alternate_toolbar"));

    public static final Target SELECT_COUNT_ON_TOOLBAR = Target.the("Selected count")
            .locatedBy("//*[@resource-id=\"com.google.android.contacts:id/alternate_toolbar\"]//android.widget.TextView");

    public static final Target SELECT_DELETE_BUTTON = Target.the("Delete button on toolbar")
            .located(By.id("com.google.android.contacts:id/menu_delete"));

    public static final Target ALERT_POPUP = Target.the("Alert popup")
            .located(By.id("com.google.android.contacts:id/action_bar_root"));

    public static final Target ALERT_TITLE = Target.the("Title of alert")
            .located(By.id("com.google.android.contacts:id/alertTitle"));

    public static final Target ALERT_MESSAGE = Target.the("Content of alert")
            .located(By.id("android:id/message"));

    public static final Target ALERT_CANCEL = Target.the("Cancel option")
            .located(By.id("android:id/button2"));

    public static final Target ALERT_DELETE = Target.the("Delete option")
            .located(By.id("android:id/button1"));
}
