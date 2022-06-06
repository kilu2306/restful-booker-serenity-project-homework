package com.herokuapp.bookinfo;

import com.herokuapp.testbase.TestBase;
import com.herokuapp.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
public class BookCurdTest extends TestBase {
    static String name = "kiya" + TestUtils.getRandomValue();
    static String lastname = "patel";
    static int price = 30;
    static boolean result = true;

    static HashMap<String, String> dates;
    static String needs = "breakfast";
    static int bookingId;
    @Steps
    BookInfoTest bookInfoTest;

    @Title("creating booking ")
    @Test
    public void test001() {
        dates = new HashMap<>();
        dates.put("checkin", "2018-01-01");
        dates.put("checkout", "2019-01-01");
        ValidatableResponse response = bookInfoTest.creatBooking(name, lastname, price, result, dates, needs);
        response.log().all().statusCode(200);

        bookingId = response.extract().path("bookingid");
        System.out.println(bookingId);
    }
    @Title("getting single data")
    @Test
    public void test002(){
        ValidatableResponse response = bookInfoTest.getingSingleBooking(bookingId);
        response.log().all().statusCode(200);

        response.body("firstname",equalTo(name));

    }
    @Title("updating booking ")
    @Test
    public void test003() {
        name=name+"updated001";
        dates = new HashMap<>();
        dates.put("checkin", "2018-01-01");
        dates.put("checkout", "2019-01-01");
        ValidatableResponse response = bookInfoTest.updatingBooking(bookingId,name, lastname,price, result, dates, needs);
        response.log().all().statusCode(200);


    }
    @Title("deleting booking")
    @Test
    public void test004(){
        ValidatableResponse response = bookInfoTest.deleteSingleBooking(bookingId);
        response.log().all().statusCode(201);

        ValidatableResponse response1 = bookInfoTest.getingSingleBooking(bookingId);
        response1.log().all().statusCode(404);

    }
}
