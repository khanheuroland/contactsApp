package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CreateContactScreen {
    public static Target FIRST_NAME = Target.the("First Name")
            .locatedBy("((//*[@resource-id=\"com.google.android.contacts:id/kind_editors\"])[1]//android.widget.EditText)[1]");

    public static Target LAST_NAME = Target.the("Last Name")
            .locatedBy("((//*[@resource-id=\"com.google.android.contacts:id/kind_editors\"])[1]//android.widget.EditText)[2]");

    public static Target COMPANY = Target.the("Company")
            .locatedBy("//*[@text=\"Company\"]");

    public static Target PHONE = Target.the("Phone number")
            .locatedBy("//*[@text=\"Phone\"]");

    public static Target EMAIL = Target.the("Email Address")
            .locatedBy("//*[@text=\"Email\"]");

    public static Target CANCEL = Target.the("Cancel button")
            .locatedBy("//android.widget.ImageButton[@content-desc=\"Cancel\"]");

    public static Target SAVE_CONTACT = Target.the("Save contact button")
            .located(By.id("com.google.android.contacts:id/menu_save"));

    public static Target CONFIRM_MESSAGE = Target.the("Confirm message")
            .located(By.id("android:id/message"));

    public static Target CONFIRM_SAVE = Target.the("Save button")
            .located(By.id("android:id/button1"));

    public static Target CONFIRM_DISCARD = Target.the("Discard button")
            .located(By.id("android:id/button2"));
}
