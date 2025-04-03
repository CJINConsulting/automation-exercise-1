Feature: Interacting with the Selenium Web Form

  Background:
    Given I am on the Selenium Web Form page

  @smoke @my-text
  Scenario Outline: Text input can submit text values
    When I enter "<text>" in the text input field
    When I click the "Submit" button
    Then I should see the message "Received!"

    Examples:
      | text                                 |
      |                                      |
      | Lorem Ipsum                          |
      | 1234567890                           |
      | +441234567890                        |
      | !£$%^&*()'#~/\-=                     |
      | A m1x 0f numbers, and speci4l chars! |

  @smoke @my-password
  Scenario Outline: Password input can submit text values
    When I enter "<text>" in the password input field
    And I click the "Submit" button
    Then I should see the message "Received!"

    Examples:
      | text                                 |
      |                                      |
      | Lorem Ipsum                          |
      | 1234567890                           |
      | +441234567890                        |
      | !£$%^&*()'#~/\-=                     |
      | A m1x 0f numbers, and speci4l chars! |

  @my-password
  Scenario: Password field type is correct
    When I enter "Lorem Ipsum" in the password input field
    Then The password input field type is 'password'
    And The password input text should not be accessible
    And The password input field value is "Lorem Ipsum"

  @smoke @my-textarea
  Scenario: TextArea field can submit text values
    When the user enters the following text into the textarea:
        """
        This is the first paragraph, one line.

        Second paragraph,
        which includes multiple
        lines.
        """
    And I click the "Submit" button
    Then I should see the message "Received!"


  @my-disabled
  Scenario: Disabled input is disabled
    Then the disabled input should be disabled

  @my-readonly
  Scenario: Readonly input is disabled
    Then the readonly input should be readonly

  Scenario Outline: Select a value from the dropdown
    Given I select "<value>" from the dropdown
    And I click the "Submit" button
    Then I should see the message "Received!"
    Examples:
      | value |
      | One   |
      | Two   |
      | Three |

  Scenario: Accept a suggested datalist value
    When the user types "Seattle" into the datalist input
    Then the datalist input should contain "Seattle"
    And I click the "Submit" button
    Then I should see the message "Received!"

  Scenario: Accept a custom value not in datalist
    When the user types "Atlantis" into the datalist input
    Then the datalist input should contain "Atlantis"
    And I click the "Submit" button
    Then I should see the message "Received!"

  Scenario: Data list accepts partial match
    When the user types "Sea" into the datalist input
    Then the datalist input should contain "Sea"
    And I click the "Submit" button
    Then I should see the message "Received!"

  Scenario: Checked checkbox should be checked
    Then the checkbox with value 1 should be checked

  Scenario: Default checkbox should not be checked
    Then the checkbox with value 2 should not be checked

  Scenario: Upload and submit a file
    When I upload the file "upload.txt"
    Then the file input should contain the file "upload.txt"
    And I click the "Submit" button
    Then I should see the message "Received!"