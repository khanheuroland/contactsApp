Feature: Create Contact
  Scenario: Show contact detail screen after created.
    Given The Create contact on Device source is displayed
    When The user attempt to add a new contact
    Then The user should see these information on contact detail
    And The action will be displayed for according contact's information

  Scenario: Show confirmation before close Create contact with inputted information
    Given The Create contact on Device source is displayed
    When The user attempt to add a new contact then cancel
    Then The message with content "Your changes have not been saved" will be showed with two option Discard and Save
    And The Create contact will be closed for Discard option selected

  Scenario: The contact was created for Save confirmation from cancellation popup confirm
    Given The Create contact on Device source is displayed
    When The user attempt to add a new contact then cancel
    And The user agree with Save option selected
    Then The user should see these information on contact detail
    And The action will be displayed for according contact's information