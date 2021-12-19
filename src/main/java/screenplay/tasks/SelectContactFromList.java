package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.conditions.Check;
import screenplay.actions.LongPress;
import screenplay.ui.HomeScreen;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SelectContactFromList implements Task {
    enum ViewType {NAME, INDEX};

    int index;
    String name;
    ViewType type;
    public SelectContactFromList(ViewType type, int index, String name)
    {
        this.type = type;
        this.name = name;
        this.index = index;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Check.whether(this.type == ViewType.INDEX).andIfSo(
                        LongPress.on(HomeScreen.CONTACT_AT.of(String.valueOf(this.index)))
                ).otherwise(
                        LongPress.on(HomeScreen.CONTACT_NAME.of(this.name))
                )
        );
    }

    public static Performable at(int index)
    {
        return instrumented(ViewContactDetail.class, ViewContactDetail.ViewType.INDEX, index, "");
    }

    public static  Performable byName(String fullname)
    {
        return instrumented(ViewContactDetail.class, ViewContactDetail.ViewType.NAME, -1, fullname);
    }
}
