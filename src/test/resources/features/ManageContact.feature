Feature: Manage contact
  Scenario: Show contact list on specific source
    Given The home screen displays
    When The user attempt to change the contact source to device
    Then The list of device contacts will be showed

  Scenario: Show select contact source for add new contact with all resource selected
    Given The home screen displays
    When  The user attempt to open the create contact
    Then The contact source will be showed for selection

  Scenario: Show contact information screen after tap on add button
    Given The home screen displays
    When The user attempt to change the contact source to device
    And The user attempt to open the create contact
    Then The new contact screen will be showed

  Scenario: Show Detail Contact screen after tap on specific contact from the list
    Given The home screen displays
    When The user attempt to view a contact detail
    Then The detail contact screen should be showed

  Scenario: Select the contact from list by long press on the item from the list
    Given The home screen displays
    When The user attempt to select a contact from the list
    Then The selected toolbar will be showed with Delete button

  Scenario: The delete confirmation will be showed for delete event
    Given The home screen displays
    When The user attempt to select a contact from the list
    And The user attempt to delete this selected contact
    Then The message with header "Delete contact?" and content "This contact will be permanently deleted from your device" will be showed
    And The confirmation button are "Cancel" and "Delete"