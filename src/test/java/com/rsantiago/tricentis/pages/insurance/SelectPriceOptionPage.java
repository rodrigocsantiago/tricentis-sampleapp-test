package com.rsantiago.tricentis.pages.insurance;

import com.rsantiago.framework.SeleniumBasePage;
import com.rsantiago.tricentis.pages.insurance.model.VehicleType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static com.rsantiago.framework.SeleniumUtils.checkSingleValue;

public class SelectPriceOptionPage extends SeleniumBasePage {

    private static final String EXPECTED_PAGE_TITLE = "Select Price Option";

    private VehicleType vehicleType;

    @FindBy(css = "input[name='Select Option']")
    private List<WebElement> option;

    @FindBy(id = "nextsendquote")
    private WebElement next;

    @FindBy(css = "a#selectpriceoption > span.counter")
    private WebElement missingFieldsCounter;

    public SelectPriceOptionPage selectOption(String option) {
        checkSingleValue(this.option, option);
        return this;
    }

    public int getMissingFieldCount() {
        return Integer.parseInt(missingFieldsCounter.getText());
    }

    public SendQuotePage clickNext() {
    	//	Have to wait a little longer here
    	wait.until(ExpectedConditions.elementToBeClickable(next)).click();
        return new SendQuotePage(driver, vehicleType);
    }

    public SelectPriceOptionPage(WebDriver driver, VehicleType vehicleType) {
        super(driver);
        this.vehicleType = vehicleType;
        //  Wait until we get the correct page title for this page
        wait.until(ExpectedConditions.titleIs(EXPECTED_PAGE_TITLE));
    }
}
