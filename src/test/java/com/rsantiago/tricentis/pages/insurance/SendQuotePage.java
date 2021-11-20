package com.rsantiago.tricentis.pages.insurance;

import com.rsantiago.framework.SeleniumBasePage;
import com.rsantiago.tricentis.pages.insurance.model.VehicleType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SendQuotePage extends SeleniumBasePage {

    private static final String EXPECTED_PAGE_TITLE = "Send Quote";

    private VehicleType vehicleType;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "phone")
    private WebElement phone;

    @FindBy(id = "username")
    private WebElement userName;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "confirmpassword")
    private WebElement confirmPassword;

    @FindBy(id = "Comments")
    private WebElement comments;

    @FindBy(id = "sendemail")
    private WebElement send;

    @FindBy(css = "a#sendquote > span.counter")
    private WebElement missingFieldsCounter;

    @FindBy(css="div.sweet-alert")
    private WebElement sendEmailSuccessPopup;

    @FindBy(css="div.sweet-alert > h2")
    private WebElement sendEmailSuccessMessage;

    @FindBy(css = "button.confirm")
    private WebElement sendEmailOk;

    public SendQuotePage enterEmail(String email) {
        this.email.sendKeys(email);
        return this;
    }

    public SendQuotePage enterPhone(String phone) {
        this.phone.sendKeys(phone);
        return this;
    }

    public SendQuotePage enterUserName(String userName) {
        this.userName.sendKeys(userName);
        return this;
    }

    public SendQuotePage enterPasswordAndConfirm(String password) {
        this.password.sendKeys(password);
        this.confirmPassword.sendKeys(password);
        return this;
    }

    public SendQuotePage enterComments(String comments) {
        this.comments.sendKeys(comments);
        return this;
    }

    public int getMissingFieldCount() {
        return Integer.parseInt(missingFieldsCounter.getText());
    }

    public String getSuccessPopupMessage() {
        wait.until(ExpectedConditions.elementToBeClickable(sendEmailSuccessMessage));
        return sendEmailSuccessMessage.getText();
    }

    public void clickOkOnSuccessPopup() {
        wait.until(ExpectedConditions.elementToBeClickable(sendEmailOk));
        sendEmailOk.click();
    }

    public void clickSend() {
        send.click();
        //  Wait until the success popup displays
        wait.until(ExpectedConditions.elementToBeClickable(sendEmailSuccessPopup));
    }

    public SendQuotePage(WebDriver driver, VehicleType vehicleType) {
        super(driver);
        this.vehicleType = vehicleType;
        //  Wait until we get the correct page title for this page
        wait.until(ExpectedConditions.titleIs(EXPECTED_PAGE_TITLE));
    }
}
