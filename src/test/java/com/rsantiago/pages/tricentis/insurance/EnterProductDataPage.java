package com.rsantiago.pages.tricentis.insurance;

import com.rsantiago.model.VehicleType;
import com.rsantiago.pages.SeleniumBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static com.rsantiago.framework.SeleniumUtils.*;

public class EnterProductDataPage extends SeleniumBasePage {

    private static final String EXPECTED_PAGE_TITLE = "Enter Product Data";

    private VehicleType vehicleType;

    @FindBy(id = "startdate")
    private WebElement startDate;

    @FindBy(id = "insurancesum")
    private WebElement insuranceSum;

    @FindBy(id = "meritrating")
    private WebElement meritRating;

    @FindBy(id = "damageinsurance")
    private WebElement damageInsurance;

    @FindBy(css = "input[name^='Optional Products']")
    private List<WebElement> optionalProducts;

    @FindBy(id = "courtesycar")
    private WebElement courtesyCar;

    @FindBy(id = "nextselectpriceoption")
    private WebElement next;

    @FindBy(css = "a#enterproductdata > span.counter")
    private WebElement missingFieldsCounter;

    public EnterProductDataPage enterStartDate(String startDate) {
        this.startDate.sendKeys(startDate);
        return this;
    }

    public EnterProductDataPage selectInsuranceSum(String insuranceSum) {
        selectValueFromElement(this.insuranceSum, insuranceSum);
        return this;
    }

    public EnterProductDataPage selectMeritRating(String meritRating) {
        selectValueFromElement(this.meritRating, meritRating);
        return this;
    }

    public EnterProductDataPage selectDamageInsurance(String damageInsurance) {
        selectValueFromElement(this.damageInsurance, damageInsurance);
        return this;
    }

    public EnterProductDataPage selectOptionalProducts(String[] optionalProducts) {
        checkMultipleValues(this.optionalProducts, "id", optionalProducts);
        return this;
    }

    public EnterProductDataPage selectCourtesyCar(String courtesyCar) {
        selectValueFromElement(this.courtesyCar, courtesyCar);
        return this;
    }

    public int getMissingFieldCount() {
        return Integer.parseInt(missingFieldsCounter.getText());
    }

    public SelectPriceOptionPage clickNext() {
        next.click();
        return new SelectPriceOptionPage(driver, vehicleType);
    }

    public EnterProductDataPage(WebDriver driver, VehicleType vehicleType) {
        super(driver);
        this.vehicleType = vehicleType;
        //  Wait until we get the correct page title for this page
        wait.until(ExpectedConditions.titleIs(EXPECTED_PAGE_TITLE));
    }
}
