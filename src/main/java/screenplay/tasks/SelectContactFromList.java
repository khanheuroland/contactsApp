package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.conditions.Check;
import net.thucydides.core.annotations.Step;
import screenplay.actions.Tap;
import screenplay.ui.ManageContactScreen;

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
    @Step("{0} selects the contact at #{index}")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Check.whether(this.type == ViewType.INDEX).andIfSo(
                        Tap.on(ManageContactScreen.CONTACT_AT.of(String.valueOf(this.index))).inSecond(2).release()
                ).otherwise(
                        Tap.on(ManageContactScreen.CONTACT_NAME.of(this.name)).inSecond(2).release()
                )
        );
    }

    public static Performable at(int index)
    {
        return instrumented(SelectContactFromList.class, ViewType.INDEX, index, "");
    }

    public static  Performable byName(String fullname)
    {
        return instrumented(SelectContactFromList.class, ViewType.NAME, -1, fullname);
    }
}
