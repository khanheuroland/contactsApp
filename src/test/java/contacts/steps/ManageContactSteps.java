package contacts.steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import net.serenitybdd.screenplay.actions.Click;
import org.hamcrest.CoreMatchers;
import screenplay.questions.AlertMessageOnManageContact;
import screenplay.questions.TheCurrentActivity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import screenplay.questions.TheListOfContacts;
import screenplay.questions.ToastMessage;
import screenplay.tasks.ChangeContactSource;
import screenplay.tasks.SelectContactFromList;
import screenplay.tasks.ViewContactDetail;
import screenplay.ui.ContactSourceScreen;
import screenplay.ui.CreateContactScreen;
import screenplay.ui.ManageContactScreen;
import screenplay.ui.ManageContactScreen;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;
import static org.assertj.core.api.Assertions.not;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;

public class ManageContactSteps {
    public Actor anna = Actor.named("Anna");

    @Managed(driver = "Appium")
    public WebDriver herBrowser;

    @Before
    public void annaCanBrowseTheWeb() {
        anna.can(BrowseTheWeb.with(herBrowser));
    }

    @Given("The home screen displays")
    public void the_home_screen_displays() {
        anna.should(seeThat(TheCurrentActivity.Name(),
                equalTo("com.android.contacts.activities.PeopleActivity")));
    }

    @When("The user attempt to open the create contact")
    public void the_user_attempt_to_open_the_create_contact() {
        anna.attemptsTo(
                Click.on(ManageContactScreen.ADD_CONTACT)
        );
    }

    @When("^The user attempt to view a contact detail$")
    public void the_user_attempt_to_view_a_contact_detail() {
        anna.attemptsTo(
                ViewContactDetail.at(1)
        );
    }

    @When("^The user attempt to select a contact from the list$")
    public void the_user_attempt_to_select_a_contact_from_the_list() {
        //Store the contact name selected (for delete)
        anna.remember("delete_contact_name", TheListOfContacts.Display().answeredBy(anna).get(0));
        anna.attemptsTo(
                SelectContactFromList.at(1)
        );
    }

    @Then("^The detail contact screen should be showed$")
    public void the_detail_contact_screen_should_be_showed() {
        anna.should(
                seeThat(TheCurrentActivity.Name(), equalTo("com.google.android.apps.contacts.quickcontact.QuickContactActivity"))
        );
    }

    @Then("^The selected toolbar will be showed with Delete button$")
    public void the_selected_toolbar_will_be_showed_with_delete_button() {
        anna.should(
                seeThat(the(ManageContactScreen.SELECT_CONTACT_TOOLBAR), isVisible())
        );

    }

    @Then("^The message with header \"([^\"]*)\" and content \"([^\"]*)\" will be showed$")
    public void the_message_with_header_and_content_will_be_showed(String header, String content) {
        anna.should(
                seeThat(AlertMessageOnManageContact.Header(), equalTo(header)),
                seeThat(AlertMessageOnManageContact.Message(), equalTo(content))
        );
    }

    @And("^The user attempt to delete this selected contact$")
    public void the_user_attempt_to_delete_this_selected_contact() {
        anna.attemptsTo(
                Click.on(ManageContactScreen.SELECT_DELETE_BUTTON)
        );
    }

    @And("^The confirmation button are \"([^\"]*)\" and \"([^\"]*)\"$")
    public void the_confirmation_button_are(String cancel, String delete) {
        anna.should(
                seeThat(AlertMessageOnManageContact.TextOfCancelButton(), equalTo(cancel)),
                seeThat(AlertMessageOnManageContact.TextOfDeleteButton(), equalTo(delete))
        );
    }

    @And("^The user decline the delete confirmation$")
    public void the_user_decline_the_delete_confirmation() {
        anna.attemptsTo(
                Click.on(ManageContactScreen.ALERT_CANCEL)
        );
    }

    @And("^The user agree with the delete confirmation$")
    public void the_user_agree_with_the_delete_confirmation() {
        anna.attemptsTo(
                Click.on(ManageContactScreen.ALERT_DELETE)
        );
    }

    @When("The user attempt to change the contact source to device")
    public void the_user_attempt_to_change_the_contact_source_to_device() {
        anna.attemptsTo(ChangeContactSource.to("Device"));
    }

    @Then("The list of device contacts will be showed")
    public void the_list_of_device_contact_will_be_showed() {
        anna.should(
                seeThat(TheListOfContacts.Display(), hasSize(equalTo(8))),
                seeThat(TheListOfContacts.Display(), hasItem("Dinh Luyen"))
        );
    }

    @Then("The contact source will be showed for selection")
    public void the_contact_source_will_be_showed_for_selection() {
        anna.should(
                seeThat(the(ContactSourceScreen.CONTACT_SOURCE_POPUP), isVisible())
        );
    }

    @Then("^The confirmation popup will be hidden$")
    public void the_confirmation_popup_will_be_hidden() {
        anna.should(
                seeThat(AlertMessageOnManageContact.isNotShow(), equalTo(true))
        );
    }

    @Then("The new contact screen will be showed")
    public void the_new_contact_screen_will_be_showed() {
        anna.should(
                seeThat(TheCurrentActivity.Name(),
                        equalTo("com.google.android.apps.contacts.editor.ContactEditorActivity"))
        );
    }

    @Then("^The selected contact will be deleted from contact list$")
    public void the_selected_contact_will_be_deleted_from_contact_list() {
        String delete_contact_name = anna.recall("delete_contact_name");
        anna.should(
                seeThat(ToastMessage.Content(), equalTo("1 contact deleted")),
                seeThat(TheListOfContacts.isContains(delete_contact_name), equalTo(false))
        );
    }
}
