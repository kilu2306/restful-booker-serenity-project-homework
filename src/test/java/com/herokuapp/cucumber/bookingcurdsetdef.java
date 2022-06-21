package com.herokuapp.cucumber;

import com.herokuapp.bookinfo.BookInfoTest;
import com.herokuapp.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;

public class bookingcurdsetdef {
    static String name = "kiya" + TestUtils.getRandomValue();
    static String lastname = "patel";
    static int price = 30;
    static boolean result = true;

    static HashMap<String, String> dates;
    static String needs = "breakfast";
    static int bookingId;
    ValidatableResponse response;
    @Steps
    BookInfoTest bookInfoTest;

    @When("^user send a post request to creat booking$")
    public void userSendAPostRequestToCreatBooking() {
        dates = new HashMap<>();
        dates.put("checkin", "2018-01-01");
        dates.put("checkout", "2019-01-01");
        response= bookInfoTest.creatBooking(name,lastname,price,result,dates,needs);
    }

    @And("^user insert name, lastname, price, result, dates, needs$")
    public void userInsertNameLastnamePriceResultDatesNeeds() {
    }

    @Then("^user should be able to creat booking successfully$")
    public void userShouldBeAbleToCreatBookingSuccessfully() {
        response.statusCode(200);
        bookingId=response.extract().path("bookingid");
        System.out.println(bookingId);
    }

    @When("^user send get request to fatch record by single id$")
    public void userSendGetRequestToFatchRecordBySingleId() {
        response=bookInfoTest.getingSingleBooking(bookingId);

    }

    @Then("^user should be able to get booking in responsebody$")
    public void userShouldBeAbleToGetBookingInResponsebody() {
        response.statusCode(200);
    }

    @And("^verify booking name$")
    public void verifyBookingName() {
        response.body("firstname",equalTo(name));

    }

    @When("^user send Put patch request for updating booking$")
    public void userSendPutPatchRequestForUpdatingBooking() {
        name=name+"updated001";
        dates = new HashMap<>();
        dates.put("checkin", "2018-01-01");
        dates.put("checkout", "2019-01-01");
        response=bookInfoTest.updatingBooking(bookingId,name,lastname,price,result,dates,needs);

    }

    @And("^user add name in name field$")
    public void userAddNameInNameField() {
    }

    @Then("^user should be able to update booking succefully$")
    public void userShouldBeAbleToUpdateBookingSuccefully() {
        response.statusCode(200);

    }

    @When("^user send delete request for deleting booking$")
    public void userSendDeleteRequestForDeletingBooking() {
        response=bookInfoTest.deleteSingleBooking(bookingId);

    }

    @Then("^booking should be succesffuly deleted$")
    public void bookingShouldBeSuccesffulyDeleted() {
        response.statusCode(200);
    }

    @And("^to verify booking is deleted$")
    public void toVerifyBookingIsDeleted() {
        response=bookInfoTest.deleteSingleBooking(bookingId);
        response.statusCode(404);

    }
}
