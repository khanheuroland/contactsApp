package screenplay.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import screenplay.ui.ToastMessageScreen;

public class ToastMessage {
    public static Question<Boolean> isShow()
    {
        return actor-> ToastMessageScreen.TOAST_MESSAGE.resolveAllFor(actor).size()>0;
    }

    public static Question<String> Content()
    {
        return actor -> Text.of(ToastMessageScreen.TOAST_MESSAGE).viewedBy(actor).asString();
    }
}
