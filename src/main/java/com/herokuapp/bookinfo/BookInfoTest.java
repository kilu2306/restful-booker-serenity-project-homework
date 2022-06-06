package com.herokuapp.bookinfo;

import com.herokuapp.constant.EndPoints;
import com.herokuapp.model.BookingPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class BookInfoTest {
    @Step
    public ValidatableResponse creatBooking(String name, String lName, int price,
                                            boolean result, HashMap<String,String> dates,String needs){
        BookingPojo bookingPojo= new BookingPojo();
        bookingPojo.setFirstname(name);
        bookingPojo.setLastname(lName);
        bookingPojo.setTotalprice(price);
        bookingPojo.setDepositpaid(result);
        bookingPojo.setBookingdates(dates);
        bookingPojo.setAdditionalneeds(needs);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(bookingPojo)
                .when()
                .post(EndPoints.CREAT_BOOKING)
                .then();

    }
    @Step
    public ValidatableResponse getingSingleBooking(int bookingId){
        return SerenityRest.given().log().all()
                .pathParam("bookingId",bookingId)
                .when()
                .get(EndPoints.GET_SINGLE_BOOKING_BY_ID)
                .then();
    }
    @Step
    public ValidatableResponse updatingBooking(int bookingId,String name, String lName,
    int price, boolean result, HashMap<String,String> dates,String needs){
        BookingPojo bookingPojo= new BookingPojo();
        bookingPojo.setFirstname(name);
        bookingPojo.setLastname(lName);
        bookingPojo.setTotalprice(price);
        bookingPojo.setDepositpaid(result);
        bookingPojo.setBookingdates(dates);
        bookingPojo.setAdditionalneeds(needs);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer b31937964406b8b")
                .header("Cookie","token=b31937964406b8b")

                .pathParam("bookingId",bookingId)
                .body(bookingPojo)
                .when()
                .put(EndPoints.UPDATE_BOOKING_BY_ID)
                .then();

    }
    @Step
    public ValidatableResponse deleteSingleBooking(int bookingId){
        return SerenityRest.given().log().all()
                .header("Authorization","Bearer b31937964406b8b")
                .header("Cookie","token=b31937964406b8b")

                .pathParam("bookingId",bookingId)
                .when()
                .delete(EndPoints.DELETE_BOOKING_BY_ID)
                .then();
    }
}
