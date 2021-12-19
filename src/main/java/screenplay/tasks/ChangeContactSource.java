package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import screenplay.ui.ContactSourceScreen;
import screenplay.ui.HomeScreen;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ChangeContactSource implements Task {
    String source;
    public ChangeContactSource(String source)
    {
        this.source = source;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(HomeScreen.SELECT_CONTACT_SOURCE),
                Click.on(ContactSourceScreen.SOURCE_NAME.of(this.source))
        );
    }

    public static Performable to(String sourceName)
    {
        return instrumented(ChangeContactSource.class, sourceName);
    }
}
