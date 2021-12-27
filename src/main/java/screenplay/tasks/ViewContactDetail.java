package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.conditions.Check;
import screenplay.ui.ManageContactScreen;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ViewContactDetail implements Task {
    enum ViewType {NAME, INDEX};
    int index;
    String name;
    ViewType type;
    public ViewContactDetail(ViewType type, int index, String name)
    {
        this.type = type;
        this.name = name;
        this.index = index;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Check.whether(this.type == ViewType.INDEX).andIfSo(
                        Click.on(ManageContactScreen.CONTACT_AT.of(String.valueOf(this.index)))
                ).otherwise(
                        Click.on(ManageContactScreen.CONTACT_NAME.of(this.name))
                )
        );
    }

    public static Performable at(int index)
    {
        return instrumented(ViewContactDetail.class, ViewType.INDEX, index, "");
    }

    public static  Performable byName(String fullname)
    {
        return instrumented(ViewContactDetail.class, ViewType.NAME, -1, fullname);
    }
}
