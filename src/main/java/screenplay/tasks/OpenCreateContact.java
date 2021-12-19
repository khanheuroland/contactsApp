package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;
import screenplay.ui.ContactSourceScreen;
import screenplay.ui.HomeScreen;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class OpenCreateContact implements Task {
    String source;
    public OpenCreateContact(String source)
    {
        this.source = source;
    }

    @Override
    @Step("{0} Open the create contact screen on #source source")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(HomeScreen.ADD_CONTACT),
                Click.on(ContactSourceScreen.SOURCE_NAME.of(this.source))
        );
    }

    public static Performable on(String source)
    {
        return instrumented(OpenCreateContact.class, source);
    }
}
