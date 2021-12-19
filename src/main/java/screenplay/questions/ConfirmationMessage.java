package screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import screenplay.ui.CreateContactScreen;

public class ConfirmationMessage implements Question<String> {
    @Override
    public String answeredBy(Actor actor) {
        return Text.of(CreateContactScreen.CONFIRM_MESSAGE).viewedBy(actor).asString();
    }

    public static Question<String> Display()
    {
        return new ConfirmationMessage();
    }
}
