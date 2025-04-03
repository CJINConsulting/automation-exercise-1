package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pages.SubmittedFormPage;
import pages.WebFormPage;
import base.TestContext;

import java.io.File;

public class WebFormSteps extends BaseSteps {
    private final WebFormPage webFormPage;
    private final SubmittedFormPage submittedFormPage;

    public WebFormSteps(TestContext context) {
        this.webFormPage = new WebFormPage(context.getDriver());
        this.submittedFormPage = new SubmittedFormPage(context.getDriver());
    }

    @Given("I am on the Selenium Web Form page")
    public void i_am_on_the_selenium_web_form_page() {
        webFormPage.open();
    }

    @When("I enter {string} in the text input field")
    public void i_enter_in_the_text_input_field(String text) {
        webFormPage.textInput.sendKeys(text);
    }

    @When("I enter {string} in the password input field")
    public void i_enter_in_the_password_input_field(String password) {
        webFormPage.passwordInput.sendKeys(password);
    }

    @Then("The password input text should not be accessible")
    public void password_field_text_hidden() {
        Assertions.assertEquals("", webFormPage.passwordInput.getText());
    }

    @Then("The password input field value is {string}")
    public void password_field_value_match(String passwordValue) {
        Assertions.assertEquals(passwordValue, webFormPage.passwordInput.getDomProperty("value"));
    }

    @Then("The password input field type is 'password'")
    public void password_field_type_is_password() {
        Assertions.assertEquals("password", webFormPage.passwordInput.getDomAttribute("type"));
    }

    @When("I leave the text input field empty")
    public void i_leave_the_text_input_field_empty() {
        webFormPage.textInput.clear();
    }

    @When("the user enters the following text into the textarea:")
    public void enterParagraphTextInTextarea(String text) {
        webFormPage.textAreaInput.sendKeys(text);
    }

    @Then("the disabled input should be disabled")
    public void verifyDisabledInputIsDisabled() {
        Assertions.assertFalse(webFormPage.disabledInput.isEnabled(), "Expected input to be disabled");
    }

    @Then("the readonly input should be readonly")
    public void verifyReadonlyInputIsReadonly() {
       Assertions.assertTrue(webFormPage.readOnlyInput.getAttribute("readonly") != null, "Expected input to be readonly");
    }

    @When("I check the {string} checkbox")
    public void i_check_the_checkbox(String checkboxLabel) {
        // Assuming you want to interact with the first checkbox for now
        if (!webFormPage.checkboxes.get(0).isSelected()) {
            webFormPage.checkboxes.get(0).click();
        }
    }

    @When("I uncheck the {string} checkbox")
    public void i_uncheck_the_checkbox(String checkboxLabel) {
        if (webFormPage.checkboxes.get(0).isSelected()) {
            webFormPage.checkboxes.get(0).click();
        }
    }

    @Then("the {string} checkbox should be checked")
    public void the_checkbox_should_be_checked(String checkboxLabel) {
        Assertions.assertTrue(webFormPage.checkboxes.get(0).isSelected(), "Checkbox '" + checkboxLabel + "' should be checked");
    }

    @Then("the {string} checkbox should be unchecked")
    public void the_checkbox_should_be_unchecked(String checkboxLabel) {
        Assertions.assertFalse(webFormPage.checkboxes.get(0).isSelected(), "Checkbox '" + checkboxLabel + "' should be unchecked");
    }

    @When("I select {string} from the dropdown")
    public void i_select_from_the_dropdown(String option) {
        new Select(webFormPage.numberSelect).selectByVisibleText(option);
    }

    @When("the user types {string} into the datalist input")
    public void typeIntoDatalist(String value) {
        webFormPage.dataSelect.sendKeys(value);
    }

    @Then("the datalist input should contain {string}")
    public void datalistShouldContain(String expected) {
        Assertions.assertEquals(expected, webFormPage.dataSelect.getAttribute("value"));
    }

    @Then("the checkbox with value {int} should be checked")
    public void checkboxShouldBeChecked(int value) {
        Assertions.assertTrue(webFormPage.isCheckboxChecked(value), "Checkbox " + String.valueOf(value) + " should be checked");
    }

    @Then("the checkbox with value {int} should not be checked")
    public void checkboxShouldNotBeChecked(int value) {
        Assertions.assertFalse(webFormPage.isCheckboxChecked(value), "Checkbox " + value + " should NOT be checked");
    }

    @When("I select the {string} radio button")
    public void i_select_the_radio_button(String radioLabel) {
        for (WebElement radioButton : webFormPage.radioButtons) {
            if (radioButton.getAttribute("value").equalsIgnoreCase(radioLabel)) {
                radioButton.click();
                break;
            }
        }
    }

    @Then("the {string} radio button should be selected")
    public void the_radio_button_should_be_selected(String radioLabel) {
        boolean foundAndSelected = false;
        for (WebElement radioButton : webFormPage.radioButtons) {
            if (radioButton.getAttribute("value").equalsIgnoreCase(radioLabel) && radioButton.isSelected()) {
                foundAndSelected = true;
                break;
            }
        }
        Assertions.assertTrue(foundAndSelected, "Radio button '" + radioLabel + "' should be selected");
    }

    @When("I enter {string} in the textarea field")
    public void i_enter_in_the_textarea_field(String text) {
        webFormPage.textAreaInput.sendKeys(text);
    }

    @When("I upload the file {string}")
    public void i_upload_the_file(String fileName) {
        String resourcePath = "data/" + fileName;
        ClassLoader classLoader = getClass().getClassLoader();
        File file;

        try {
            file = new File(classLoader.getResource(resourcePath).toURI());
            webFormPage.fileInput.sendKeys(file.getAbsolutePath());
        } catch (Exception e) {
            throw new RuntimeException("Failed to load file: " + resourcePath, e);
        }
    }

    @Then("the file input should contain the file {string}")
    public void the_file_input_should_contain_the_file(String fileName) {
        String actualValue = webFormPage.fileInput.getAttribute("value");

        // Normalize and assert it contains the expected file name
        Assertions.assertTrue(actualValue.contains(fileName),
                "Expected file input to contain '" + fileName + "', but got: '" + actualValue + "'");
    }

    @When("I click the {string} button")
    public void i_click_the_button(String buttonText) {
        webFormPage.submitButton.click();
    }

    @Then("I should see the message {string}")
    public void i_should_see_the_message(String expectedMessage) {
        submittedFormPage.wait.until(ExpectedConditions.visibilityOf(submittedFormPage.receivedMessage));
        Assertions.assertEquals(expectedMessage, submittedFormPage.receivedMessage.getText());
    }

    @When("I set the color picker to {string}")
    public void i_set_the_color_picker_to(String colorCode) {
        webFormPage.colorPicker.sendKeys(colorCode);
    }

    @When("I set the date input to {string}")
    public void i_set_the_date_input_to(String date) {
        webFormPage.datePicker.sendKeys(date);
    }

    @When("I set the range input to {string}")
    public void i_set_the_range_input_to(String value) {
        webFormPage.rangeSelector.sendKeys(value);
    }
}