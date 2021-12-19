package screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import screenplay.ui.HomeScreen;

import java.util.List;

public class TheListOfContacts implements Question<List<String>>{
    @Override
    public List<String> answeredBy(Actor actor) {
        return Text.of(HomeScreen.CONTACT_NAMES).viewedBy(actor).asList();
    }

    public static Question<List<String>> Display()
    {
        return new TheListOfContacts();
    }
}
