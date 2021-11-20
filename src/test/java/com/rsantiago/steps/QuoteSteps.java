package com.rsantiago.steps;

import com.rsantiago.framework.WebDriverManager;
import com.rsantiago.model.VehicleType;
import com.rsantiago.pages.tricentis.HomePage;
import com.rsantiago.pages.tricentis.insurance.*;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    @And("I enter vehicle data with valid values")
    public void iEnterVehicleData() {
        //  Here I'm calling the page object using a simple method
        enterVehicleDataPage.enterAutomobileVehicleData("BMW",
                "250",
                "10/10/2010",
                "7",
                "Diesel",
                "20500",
                "ABC1234",
                "15000");

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

    @And("I enter product data with valid values")
    public void iEnterProductDataWithValidValues() {
        //  Start date must be more than 1 month in future
        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = currentDate.plusMonths(2);

        enterProductDataPage
                .enterStartDate(startDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")))
                .selectInsuranceSum("5000000")
                .selectMeritRating("Bonus 9")
                .selectDamageInsurance("Full Coverage")
                .selectOptionalProducts(new String[]{"EuroProtection", "LegalDefenseInsurance"})
                .selectCourtesyCar("No");

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
