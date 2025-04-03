package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WebFormPage extends BasePage {
    @FindBy(name = "my-text")
    public WebElement textInput;
    @FindBy(name = "my-password")
    public WebElement passwordInput;
    @FindBy(name = "my-textarea")
    public WebElement textAreaInput;
    @FindBy(name = "my-disabled")
    public WebElement disabledInput;
    @FindBy(name = "my-readonly")
    public WebElement readOnlyInput;
    @FindBy(name = "my-select")
    public WebElement numberSelect;
    @FindBy(name = "my-datalist")
    public WebElement dataSelect;
    @FindBy(name = "my-file")
    public WebElement fileInput;
    @FindBy(name = "my-check")
    public List<WebElement> checkboxes;
    @FindBy(name = "my-radio")
    public List<WebElement> radioButtons;
    @FindBy(css = "button[type='submit']")
    public WebElement submitButton;
    @FindBy(name = "my-colors")
    public WebElement colorPicker;
    @FindBy(name = "my-date")
    public WebElement datePicker;
    @FindBy(name = "my-range")
    public WebElement rangeSelector;

    public WebFormPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
    }

    public WebElement getCheckboxByIndex(int index) {
        String id = "my-check-" + index;
        return driver.findElement(By.id(id));
    }

    public boolean isCheckboxChecked(int index) {
        return getCheckboxByIndex(index).isSelected();
    }

    public void setCheckboxState(int index, boolean shouldBeChecked) {
        WebElement checkbox = getCheckboxByIndex(index);
        if (checkbox.isSelected() != shouldBeChecked) {
            checkbox.click();
        }
    }
}
