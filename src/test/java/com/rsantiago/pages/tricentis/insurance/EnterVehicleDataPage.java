package com.rsantiago.pages.tricentis.insurance;

import com.rsantiago.model.VehicleType;
import com.rsantiago.pages.SeleniumBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.rsantiago.framework.SeleniumUtils.selectValueFromElement;

public class EnterVehicleDataPage extends SeleniumBasePage {

    private static final String EXPECTED_PAGE_TITLE = "Enter Vehicle Data";

    private VehicleType vehicleType;

    @FindBy(id = "make")
    private WebElement make;

    @FindBy(id = "model")
    private WebElement model;

    @FindBy(id = "cylindercapacity")
    private WebElement cylinderCapacity;

    @FindBy(id = "engineperformance")
    private WebElement enginePerformance;

    @FindBy(id = "dateofmanufacture")
    private WebElement dateOfManufacture;

    @FindBy(id = "numberofseats")
    private WebElement numberOfSeats;

    @FindBy(id = "righthanddriveyes")
    private WebElement rightHandDriveYes;

    @FindBy(id = "righthanddriveno")
    private WebElement rightHandDriveNo;

    @FindBy(id = "numberofseatsmotorcycle")
    private WebElement numberOfSeatsMotorcycle;

    @FindBy(id = "fuel")
    private WebElement fuelType;

    @FindBy(id = "payload")
    private WebElement payload;

    @FindBy(id = "totalweight")
    private WebElement totalWeight;

    @FindBy(id = "listprice")
    private WebElement listPrice;

    @FindBy(id = "licenseplatenumber")
    private WebElement licensePlateNumber;

    @FindBy(id = "annualmileage")
    private WebElement annualMileage;

    @FindBy(id = "nextenterinsurantdata")
    private WebElement next;

    @FindBy(css = "a#entervehicledata > span.counter")
    private WebElement missingFieldsCounter;

    public EnterVehicleDataPage selectMake(String make) {
        selectValueFromElement(this.make, make);
        return this;
    }

    public EnterVehicleDataPage enterEnginePerformance(String enginePerformance) {
        this.enginePerformance.sendKeys(enginePerformance);
        return this;
    }

    public EnterVehicleDataPage enterDateOfManufacture(String dateOfManufacture) {
        this.dateOfManufacture.sendKeys(dateOfManufacture);
        return this;
    }

    public EnterVehicleDataPage selectNumberOfSeats(String numberOfSeats) {
        selectValueFromElement(this.numberOfSeats, numberOfSeats);
        return this;
    }

    public EnterVehicleDataPage selectFuelType(String fuelType) {
        selectValueFromElement(this.fuelType, fuelType);
        return this;
    }

    public EnterVehicleDataPage enterListPrice(String listPrice) {
        this.listPrice.sendKeys(listPrice);
        return this;
    }

    public EnterVehicleDataPage enterLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber.sendKeys(licensePlateNumber);
        return this;
    }

    public EnterVehicleDataPage enterAnnualMileage(String annualMileage) {
        this.annualMileage.sendKeys(annualMileage);
        return this;
    }

    public EnterVehicleDataPage enterAutomobileVehicleData(String make,
                                                            String enginePerformance,
                                                            String dateOfManufacture,
                                                            String numberOfSeats,
                                                            String fuelType,
                                                            String listPrice,
                                                            String licensePlateNumber,
                                                            String annualMileage) {

        selectMake(make);
        enterEnginePerformance(enginePerformance);
        enterDateOfManufacture(dateOfManufacture);
        selectNumberOfSeats(numberOfSeats);
        selectFuelType(fuelType);
        enterListPrice(listPrice);
        enterLicensePlateNumber(licensePlateNumber);
        enterAnnualMileage(annualMileage);
        return this;
    }

    public int getMissingFieldCount() {
        return Integer.parseInt(missingFieldsCounter.getText());
    }

    public EnterInsurantDataPage clickNext() {
        next.click();
        return new EnterInsurantDataPage(driver, vehicleType);
    }

    public EnterVehicleDataPage(WebDriver driver, VehicleType vehicleType) {
        super(driver);
        this.vehicleType = vehicleType;
        //  Wait until we get the correct page title for this page
        wait.until(ExpectedConditions.titleIs(EXPECTED_PAGE_TITLE));
    }
}
