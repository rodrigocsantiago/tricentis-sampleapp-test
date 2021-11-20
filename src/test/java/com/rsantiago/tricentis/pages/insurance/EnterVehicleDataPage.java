package com.rsantiago.tricentis.pages.insurance;

import com.rsantiago.framework.SeleniumBasePage;
import com.rsantiago.tricentis.pages.insurance.model.VehicleType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static com.rsantiago.framework.SeleniumUtils.checkSingleValue;
import static com.rsantiago.framework.SeleniumUtils.selectValueFromElement;

public class EnterVehicleDataPage extends SeleniumBasePage {

    private static final String EXPECTED_PAGE_TITLE = "Enter Vehicle Data";

    private final VehicleType vehicleType;

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

    @FindBy(css = "input[name='Right Hand Drive']")
    private List<WebElement> rightHandDrive;

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

    public void selectMake(String make) {
        selectValueFromElement(this.make, make);
    }

    public void selectModel(String model) {
        selectValueFromElement(this.model, model);
    }

    public void enterCylinderCapacity(String cylinderCapacity) {
        this.cylinderCapacity.sendKeys(cylinderCapacity);
    }

    public void enterEnginePerformance(String enginePerformance) {
        this.enginePerformance.sendKeys(enginePerformance);
    }

    public void enterDateOfManufacture(String dateOfManufacture) {
        this.dateOfManufacture.sendKeys(dateOfManufacture);
    }

    public void selectNumberOfSeats(String numberOfSeats) {
        //  This field is different on Motorcycles
        if (vehicleType == VehicleType.MOTORCYCLE) {
            selectValueFromElement(this.numberOfSeatsMotorcycle, numberOfSeats);
        } else {
            selectValueFromElement(this.numberOfSeats, numberOfSeats);
        }
    }

    public void selectRightHandDrive(String rightHandDrive) {
        checkSingleValue(this.rightHandDrive, rightHandDrive);
    }

    public void selectFuelType(String fuelType) {
        selectValueFromElement(this.fuelType, fuelType);
    }

    public void enterPayload(String payload) {
        this.payload.sendKeys(payload);
    }

    public void enterTotalWeight(String totalWeight) {
        this.totalWeight.sendKeys(totalWeight);
    }

    public void enterListPrice(String listPrice) {
        this.listPrice.sendKeys(listPrice);
    }

    public void enterLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber.sendKeys(licensePlateNumber);
    }

    public void enterAnnualMileage(String annualMileage) {
        this.annualMileage.sendKeys(annualMileage);
    }

    public EnterVehicleDataPage enterVehicleData(String make) {
        //  Not all fields are displayed for all vehicle types
        switch (vehicleType) {
            case AUTOMOBILE:
                selectMake(make);
                enterEnginePerformance("250");
                enterDateOfManufacture("10/10/2010");
                selectNumberOfSeats("7");
                selectFuelType("Petrol");
                enterListPrice("20500");
                enterLicensePlateNumber("ABC1234");
                enterAnnualMileage("15000");
                break;
            case TRUCK:
                selectMake(make);
                enterEnginePerformance("350");
                enterDateOfManufacture("10/10/2019");
                selectNumberOfSeats("3");
                selectFuelType("Diesel");
                enterPayload("900");
                enterTotalWeight("12500");
                enterListPrice("35000");
                enterLicensePlateNumber("XYZ9876");
                enterAnnualMileage("25000");
                break;
            case MOTORCYCLE:
                selectMake(make);
                selectModel("Motorcycle");
                enterCylinderCapacity("1000");
                enterEnginePerformance("150");
                enterDateOfManufacture("10/15/2019");
                selectNumberOfSeats("2");
                enterListPrice("14400");
                enterAnnualMileage("35000");
                break;
            case CAMPER:
                selectMake(make);
                enterEnginePerformance("300");
                enterDateOfManufacture("09/08/2011");
                selectNumberOfSeats("8");
                selectRightHandDrive("No");
                selectFuelType("Electric Power");
                enterPayload("800");
                enterTotalWeight("35000");
                enterListPrice("55500");
                enterLicensePlateNumber("GHI9876");
                enterAnnualMileage("50000");
        }
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
