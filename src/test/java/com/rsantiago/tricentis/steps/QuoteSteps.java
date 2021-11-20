package com.rsantiago.tricentis.steps;

import com.rsantiago.framework.WebDriverManager;
import com.rsantiago.tricentis.pages.HomePage;
import com.rsantiago.tricentis.pages.insurance.EnterInsurantDataPage;
import com.rsantiago.tricentis.pages.insurance.EnterProductDataPage;
import com.rsantiago.tricentis.pages.insurance.EnterVehicleDataPage;
import com.rsantiago.tricentis.pages.insurance.SelectPriceOptionPage;
import com.rsantiago.tricentis.pages.insurance.SendQuotePage;
import com.rsantiago.tricentis.pages.insurance.model.VehicleType;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;

import java.util.EnumSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class QuoteSteps {

    private static WebDriver driver;

    private EnterVehicleDataPage enterVehicleDataPage;
    private EnterInsurantDataPage enterInsurantDataPage;
    private EnterProductDataPage enterProductDataPage;
    private SelectPriceOptionPage selectPriceOptionPage;
    private SendQuotePage sendQuotePage;

    @BeforeAll
    public static void beforeAll() {
        driver = WebDriverManager.getDriverFromArguments();
    }

    @AfterAll
    public static void afterAll() {
        if (driver != null) {
            WebDriverManager.closeDriver();
        }
    }

    //  Converts the vehicleType String received from the step to VehicleType Enum
    @ParameterType(".*")
    public VehicleType vehicleType(String type) {
        return EnumSet.allOf(VehicleType.class).stream()
                .filter(vehType -> vehType.getTypeName().equalsIgnoreCase(type))
                .findAny()
                .orElseThrow(() -> {
                    throw new InvalidArgumentException("Invalid Vehicle Type provided");
                });
    }

    @Given("I am at the {vehicleType} insurance quote page")
    public void iAmAtTheInsuranceQuotePage(VehicleType vehicleType) {
        HomePage homePage = new HomePage(driver);
        assertThat(homePage.getPageTitle(), is("Tricentis Vehicle Insurance"));
        enterVehicleDataPage = homePage.startInsuranceQuoteFor(vehicleType);
        assertThat(enterVehicleDataPage.getPageTitle(), is("Enter Vehicle Data"));
    }

    @And("I enter vehicle data for make {string} with valid values")
    public void iEnterVehicleData(String make) {
        enterVehicleDataPage.enterVehicleData(make);
        assertThat(enterVehicleDataPage.getMissingFieldCount(), is(0));
        enterInsurantDataPage = enterVehicleDataPage.clickNext();
    }

    @And("I enter insurant data with valid values")
    public void iEnterInsurantData() {
        //  Here I'm calling the page object using a "fluent" approach
        enterInsurantDataPage
                .enterFirstName("Primeironome")
                .enterLastName("Segundonome")
                .enterDateOfBirth("02/02/2000")
                .selectGender("Male")
                .enterStreetAddress("av paulista")
                .selectCountry("Brazil")
                .enterZipCode("01415000")
                .enterCity("Sao Paulo")
                .selectOccupation("Employee")
                .selectHobbies(new String[]{"Speeding", "Skydiving"})
                .enterWebSite("http://www.google.com");

        assertThat(enterInsurantDataPage.getMissingFieldCount(), is(0));

        enterProductDataPage = enterInsurantDataPage.clickNext();
    }

    @And("I enter product data for {string} damage insurance and sum of {string}")
    public void iEnterProductDataWithValidValues(String damageInsurance, String insuranceSum) {
        enterProductDataPage.enterProductData(insuranceSum, damageInsurance);
        assertThat(enterProductDataPage.getMissingFieldCount(), is(0));
        selectPriceOptionPage = enterProductDataPage.clickNext();
    }

    @And("I select a price option")
    public void iSelectAPriceOption() {
        selectPriceOptionPage.selectOption("Gold");
        assertThat(selectPriceOptionPage.getMissingFieldCount(), is(0));
        sendQuotePage = selectPriceOptionPage.clickNext();
    }

    @When("I send my user details")
    public void iSendMyUserDetails() {
        sendQuotePage
                .enterEmail("test@automatedtesting.com")
                .enterPhone("98765432")
                .enterUserName("testuser")
                .enterPasswordAndConfirm("MyPassword123")
                .enterComments("This is an automated test");

        assertThat(sendQuotePage.getMissingFieldCount(), is(0));

        sendQuotePage.clickSend();
    }

    @Then("I receive a success message")
    public void iReceiveASuccessMessage() {
        assertThat(sendQuotePage.getSuccessPopupMessage(), is("Sending e-mail success!"));
        sendQuotePage.clickOkOnSuccessPopup();
    }
}
