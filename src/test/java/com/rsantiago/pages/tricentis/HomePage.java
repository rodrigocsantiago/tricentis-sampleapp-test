package com.rsantiago.pages.tricentis;

import com.rsantiago.model.VehicleType;
import com.rsantiago.pages.SeleniumBasePage;
import com.rsantiago.pages.tricentis.insurance.EnterVehicleDataPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends SeleniumBasePage {

    private static final String HOME_PAGE_URL = "http://sampleapp.tricentis.com/101/";

    @FindBy(css = "div.main-navigation #nav_automobile")
    private WebElement automobileMenu;

    @FindBy(css = "div.main-navigation #nav_truck")
    private WebElement truckMenu;

    @FindBy(css = "div.main-navigation #nav_motorcycle")
    private WebElement motorcycleMenu;

    @FindBy(css = "div.main-navigation #nav_camper")
    private WebElement camperMenu;

    public EnterVehicleDataPage startInsuranceQuoteFor(VehicleType vehicleType) {
        switch (vehicleType) {
            case AUTOMOBILE:
                automobileMenu.click();
                break;
            case TRUCK:
                truckMenu.click();
                break;
            case MOTORCYCLE:
                motorcycleMenu.click();
                break;
            case CAMPER:
                camperMenu.click();
        }
        return new EnterVehicleDataPage(driver, vehicleType);
    }

    public HomePage(WebDriver driver) {
        super(driver);
        driver.get(HOME_PAGE_URL);
    }
}
