package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SubmittedFormPage extends BasePage {
    @FindBy(id = "message")
    public
    WebElement receivedMessage;

    public SubmittedFormPage(WebDriver driver) {
        super(driver);
    }
    
}
