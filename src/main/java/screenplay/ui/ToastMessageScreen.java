package screenplay.ui;


import net.serenitybdd.screenplay.targets.Target;

public class ToastMessageScreen {
    public static Target TOAST_MESSAGE = Target.the("Toast Message")
            .locatedBy("//android.widget.Toast");
}
