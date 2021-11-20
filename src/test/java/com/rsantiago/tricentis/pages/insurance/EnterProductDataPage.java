package com.rsantiago.tricentis.pages.insurance;

import com.rsantiago.framework.SeleniumBasePage;
import com.rsantiago.tricentis.pages.insurance.model.VehicleType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.rsantiago.framework.SeleniumUtils.checkMultipleValues;
import static com.rsantiago.framework.SeleniumUtils.selectValueFromElement;

public class EnterProductDataPage extends SeleniumBasePage {

    private static final String EXPECTED_PAGE_TITLE = "Enter Product Data";

    private final VehicleType vehicleType;

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

    public void enterStartDate(String startDate) {
        this.startDate.sendKeys(startDate);
    }

    public void selectInsuranceSum(String insuranceSum) {
        selectValueFromElement(this.insuranceSum, insuranceSum);
    }

    public void selectMeritRating(String meritRating) {
        selectValueFromElement(this.meritRating, meritRating);
    }

    public void selectDamageInsurance(String damageInsurance) {
        selectValueFromElement(this.damageInsurance, damageInsurance);
    }

    public void selectOptionalProducts(String[] optionalProducts) {
        checkMultipleValues(this.optionalProducts, "id", optionalProducts);
    }

    public void selectCourtesyCar(String courtesyCar) {
        selectValueFromElement(this.courtesyCar, courtesyCar);
    }

    public EnterProductDataPage enterProductData(String insuranceSum, String damageInsurance) {
        //  Start date must be more than 1 month in the future. So, I'm using 2 months ahead
        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = currentDate.plusMonths(2);

        enterStartDate(startDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        selectInsuranceSum(insuranceSum);
        //  Only automobiles have this field
        if (vehicleType == VehicleType.AUTOMOBILE) {
            selectMeritRating("Bonus 9");
        }
        selectDamageInsurance(damageInsurance);
        selectOptionalProducts(new String[]{"EuroProtection", "LegalDefenseInsurance"});
        //  Only automobiles have this field
        if (vehicleType == VehicleType.AUTOMOBILE) {
            selectCourtesyCar("No");
        }
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
