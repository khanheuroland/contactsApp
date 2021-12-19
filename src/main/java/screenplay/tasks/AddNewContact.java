package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.conditions.Check;
import screenplay.abilities.CreateContact;
import screenplay.models.Contact;
import screenplay.ui.CreateContactScreen;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class AddNewContact implements Task {
    private boolean isSave;
    public AddNewContact(boolean isSave)
    {
        this.isSave = isSave;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        Contact contactData = actor.abilityTo(CreateContact.class).takeAContact();
        actor.attemptsTo(
                Enter.theValue(contactData.FirstName).into(CreateContactScreen.FIRST_NAME),
                Enter.theValue(contactData.LastName).into(CreateContactScreen.LAST_NAME),
                Enter.theValue(contactData.Company).into(CreateContactScreen.COMPANY),
                Enter.theValue(contactData.Phone).into(CreateContactScreen.PHONE),
                Enter.theValue(contactData.Email).into(CreateContactScreen.EMAIL),
                Check.whether(this.isSave).andIfSo(Click.on(CreateContactScreen.SAVE_CONTACT))
                        .otherwise(Click.on(CreateContactScreen.CANCEL))
        );
    }

    public static Performable thenSave()
    {
        return instrumented(AddNewContact.class, true);
    }

    public static Performable thenCancel()
    {
        return instrumented(AddNewContact.class, false);
    }
}
