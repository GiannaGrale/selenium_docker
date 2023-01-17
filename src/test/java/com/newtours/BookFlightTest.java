package com.newtours;

import com.BaseTest;
import com.pages.newtours.FlightAvailabilityPage;
import com.pages.newtours.FlightDetailsPage;
import com.pages.newtours.RegistrationConfirmationPage;
import com.pages.newtours.RegistrationPage;
import org.testng.annotations.*;

public class BookFlightTest extends BaseTest {
    private String noOfPassengers;

    @BeforeTest
    @Parameters({"noOfPassengers"})
    public void setUpParameter(String noOfPassengers) {
        this.noOfPassengers = noOfPassengers;
    }

    @Test
    public void registrationPageTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo();
        registrationPage.enterUserDetails("selenium", "docker");
        registrationPage.enterUserCredentials("selenium", "docker");
        registrationPage.submit();
    }

    @Test(dependsOnMethods = "registrationPageTest")
    public void registrationConfirmationPageTest() {
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        registrationConfirmationPage.goToFlightDetailsPage();
    }

    @Test(dependsOnMethods = "registrationConfirmationPageTest")
    public void flightDetailsPageTest() {
        FlightDetailsPage detailsPage = new FlightDetailsPage(driver);
        detailsPage.goToReservationPage();
        detailsPage.selectPassengers("2");
        detailsPage.goToFindFlightsPage();
    }

    @Test(dependsOnMethods = "flightDetailsPageTest")
    public void flightConfirmationPage() {
        FlightAvailabilityPage flightAvailabilityPage = new FlightAvailabilityPage(driver);
        flightAvailabilityPage.checkAvailability();
    }
}
