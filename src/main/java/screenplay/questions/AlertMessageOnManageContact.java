package screenplay.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import screenplay.ui.ManageContactScreen;

import java.lang.annotation.Target;

public class AlertMessageOnManageContact {
    public static Question<String> Header()
    {
        return actor-> Text.of(ManageContactScreen.ALERT_TITLE).viewedBy(actor).asString();
    }

    public static Question<String> Message()
    {
        return actor -> Text.of(ManageContactScreen.ALERT_MESSAGE).viewedBy(actor).asString();
    }

    public static Question<String> TextOfCancelButton()
    {
        return actor -> Text.of(ManageContactScreen.ALERT_CANCEL).viewedBy(actor).asString();
    }

    public static Question<String> TextOfDeleteButton()
    {
        return actor -> Text.of(ManageContactScreen.ALERT_DELETE).viewedBy(actor).asString();
    }

    public static Question<Boolean> isNotShow()
    {
        return actor -> ManageContactScreen.ALERT_TITLE.resolveAllFor(actor).size()==0;
    }
}
