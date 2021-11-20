package com.rsantiago;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestRunner {

    @Test
    public void test1() throws InterruptedException {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();

        //driver.get("http://sampleapp.tricentis.com/101/app.php");

        /*InsurancePage page = new HomePage(driver)
                .startInsuranceQuoteFor(VehicleType.AUTOMOBILE);*/

        //page.enterVehicleData();

        Thread.sleep(5000);

        driver.quit();

    }
}
