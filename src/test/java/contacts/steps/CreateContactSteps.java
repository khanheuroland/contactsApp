package contacts.steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import screenplay.abilities.CreateContact;
import screenplay.models.Contact;
import screenplay.questions.ConfirmationMessage;
import screenplay.questions.TheContactDetailInformation;
import screenplay.questions.TheCurrentActivity;
import screenplay.tasks.AddNewContact;
import screenplay.tasks.OpenCreateContact;
import screenplay.ui.ContactDetailScreen;
import screenplay.ui.CreateContactScreen;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateContactSteps {
    public Actor anna = Actor.named("Anna");

    @Managed(driver = "Appium")
    public WebDriver herBrowser;

    @Before
    public void annaCanBrowseTheWeb() throws Exception {
        anna.can(BrowseTheWeb.with(herBrowser));
        anna.can(CreateContact.withData());
    }

    @Given("^The Create contact on Device source is displayed$")
    public void the_create_contact_is_displayed() {
        anna.attemptsTo(
                OpenCreateContact.on("Device")
        );
    }

    @When("^The user attempt to add a new contact$")
    public void the_user_attempt_to_add_a_new_contact() {
        anna.attemptsTo(
                AddNewContact.thenSave()
        );
    }

    @When("^The user attempt to add a new contact then cancel$")
    public void the_user_attempt_to_add_a_new_contact_then_cancel() {
        anna.attemptsTo(
                AddNewContact.thenCancel()
        );
    }

    @Then("^The user should see these information on contact detail$")
    public void the_user_should_see_these_information_on_contact_detail() {
        Contact data = anna.abilityTo(CreateContact.class).takeAContact();
        anna.should(
                seeThat(TheContactDetailInformation.FullName(), equalTo(data.FirstName + " "+ data.LastName)),
                seeThat(TheContactDetailInformation.PhoneNumber(), equalTo(data.Phone)),
                seeThat(TheContactDetailInformation.Email(), equalTo(data.Email))
        );

    }

    @Then("^The message with content \"([^\"]*)\" will be showed with two option Discard and Save$")
    public void the_message_with_content_will_be_showed_with_two_option_discard_and_save(String content) {
        anna.should(
                seeThat(ConfirmationMessage.Display(), equalTo(content)),
                seeThat(the(CreateContactScreen.CONFIRM_DISCARD), isVisible()),
                seeThat(the(CreateContactScreen.CONFIRM_SAVE), isVisible())
        );
    }

    @And("^The action will be displayed for according contact's information$")
    public void the_action_will_be_displayed_for_according_contacts_information() {
        Contact data = anna.abilityTo(CreateContact.class).takeAContact();
        anna.should(
                seeThat(the(ContactDetailScreen.CALL_ACTION), isVisible()),
                seeThat(the(ContactDetailScreen.TEXT_MESSAGE_ACTION), isVisible())
        );

        if(!data.Email.isEmpty())
        {
            anna.should(
                    seeThat(the(ContactDetailScreen.SEND_EMAIL_ACTION),isVisible())
            );
        }

    }

    @And("^The user attempt to close the Create contact$")
    public void the_user_attempt_to_close_the_create_contact() {
        anna.attemptsTo(
                Click.on(CreateContactScreen.CANCEL)
        );
    }

    @And("^The Create contact will be closed for Discard option selected$")
    public void the_create_contact_will_be_closed_for_discard_option_selected() {
        anna.attemptsTo(
                Click.on(CreateContactScreen.CONFIRM_DISCARD)
        );

        anna.should(
                seeThat(TheCurrentActivity.Name(), equalTo("com.android.contacts.activities.PeopleActivity"))
        );
    }

    @And("^The user agree with Save option selected$")
    public void the_user_agree_with_save_option_selected() {
        anna.attemptsTo(
                Click.on(CreateContactScreen.CONFIRM_SAVE)
        );
    }
}
