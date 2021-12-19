package screenplay.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import screenplay.ui.ContactDetailScreen;

public class TheContactDetailInformation {
    public static Question<String> FullName() {
        return actor -> Text.of(ContactDetailScreen.FullName).viewedBy(actor).asString();
    }

    public static Question<String> PhoneNumber(){
        return actor->Text.of(ContactDetailScreen.PhoneNumber).viewedBy(actor).asString().replaceAll(" ","");
    }

    public static Question<String> Email(){
        return actor->Text.of(ContactDetailScreen.Email).viewedBy(actor).asString();
    }
}
