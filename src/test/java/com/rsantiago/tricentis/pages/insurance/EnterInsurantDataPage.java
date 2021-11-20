package com.rsantiago.tricentis.pages.insurance;

import com.rsantiago.framework.SeleniumBasePage;
import com.rsantiago.tricentis.pages.insurance.model.VehicleType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static com.rsantiago.framework.SeleniumUtils.*;

public class EnterInsurantDataPage extends SeleniumBasePage {

    private static final String EXPECTED_PAGE_TITLE = "Enter Insurant Data";

    private final VehicleType vehicleType;

    @FindBy(id = "firstname")
    private WebElement firstName;

    @FindBy(id = "lastname")
    private WebElement lastName;

    @FindBy(id = "birthdate")
    private WebElement dateOfBirth;

    @FindBy(css = "input[name='Gender']")
    private List<WebElement> gender;

    @FindBy(id ="streetaddress")
    private WebElement streetAddress;

    @FindBy(id = "country")
    private WebElement country;

    @FindBy(id = "zipcode")
    private WebElement zipCode;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "occupation")
    private WebElement occupation;

    @FindBy(css = "input[name='Hobbies']")
    private List<WebElement> hobbies;

    @FindBy(id = "website")
    private WebElement webSite;

    @FindBy(id = "nextenterproductdata")
    private WebElement next;

    @FindBy(css = "a#enterinsurantdata > span.counter")
    private WebElement missingFieldsCounter;

    public EnterInsurantDataPage enterFirstName(String firstName) {
        this.firstName.sendKeys(firstName);
        return this;
    }

    public EnterInsurantDataPage enterLastName(String lastName) {
        this.lastName.sendKeys(lastName);
        return this;
    }

    public EnterInsurantDataPage enterDateOfBirth(String dateOfBirth) {
        this.dateOfBirth.sendKeys(dateOfBirth);
        return this;
    }

    public EnterInsurantDataPage selectGender(String gender) {
        checkSingleValue(this.gender, gender);
        return this;
    }

    public EnterInsurantDataPage enterStreetAddress(String streetAddress) {
        this.streetAddress.sendKeys(streetAddress);
        return this;
    }

    public EnterInsurantDataPage selectCountry(String country) {
        selectValueFromElement(this.country, country);
        return this;
    }

    public EnterInsurantDataPage enterZipCode(String zipCode) {
        this.zipCode.sendKeys(zipCode);
        return this;
    }

    public EnterInsurantDataPage enterCity(String city) {
        this.city.sendKeys(city);
        return this;
    }

    public EnterInsurantDataPage selectOccupation(String occupation) {
        selectValueFromElement(this.occupation, occupation);
        return this;
    }

    public EnterInsurantDataPage selectHobbies(String[] hobbies) {
        checkMultipleValues(this.hobbies, "value", hobbies);
        return this;
    }

    public EnterInsurantDataPage enterWebSite(String webSite) {
        this.webSite.sendKeys(webSite);
        return this;
    }

    public int getMissingFieldCount() {
        return Integer.parseInt(missingFieldsCounter.getText());
    }

    public EnterProductDataPage clickNext() {
        next.click();
        return new EnterProductDataPage(driver, vehicleType);
    }

    public EnterInsurantDataPage(WebDriver driver, VehicleType vehicleType) {
        super(driver);
        this.vehicleType = vehicleType;
        //  Wait until we get the correct page title for this page
        wait.until(ExpectedConditions.titleIs(EXPECTED_PAGE_TITLE));
    }
}
