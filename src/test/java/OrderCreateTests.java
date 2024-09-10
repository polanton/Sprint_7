import config.Configs;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import requests.OrderCreateJsonRequest;
import testdata.TestData;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasKey;

@RunWith(Parameterized.class)
public class OrderCreateTests {
    //Добавь необходимые поля
    OrderCreateJsonRequest orderCreateRequest;
    Boolean isCreated;

    public OrderCreateTests(OrderCreateJsonRequest orderCreateRequest, Boolean isCreated) {
        this.orderCreateRequest = orderCreateRequest;
        this.isCreated = isCreated;
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        //Сгенерируй тестовые данные (свою учётку и несколько случайных)
        return new Object[][] {
                {new OrderCreateJsonRequest(TestData.generateFirstName(),TestData.generateLastName(),TestData.generateAddress(),TestData.generateMetroStation(),TestData.generatePhone(),TestData.generateRentTime(),TestData.generateDeliveryDate(),TestData.generateComment(), new ArrayList<String>(Arrays.asList("BLACK"))),true},
                {new OrderCreateJsonRequest(TestData.generateFirstName(),TestData.generateLastName(),TestData.generateAddress(),TestData.generateMetroStation(),TestData.generatePhone(),TestData.generateRentTime(),TestData.generateDeliveryDate(),TestData.generateComment(), new ArrayList<String>(Arrays.asList( "GREY"))),true},
                {new OrderCreateJsonRequest(TestData.generateFirstName(),TestData.generateLastName(),TestData.generateAddress(),TestData.generateMetroStation(),TestData.generatePhone(),TestData.generateRentTime(),TestData.generateDeliveryDate(),TestData.generateComment(), new ArrayList<String>(Arrays.asList("BLACK", "GREY"))),true},
                {new OrderCreateJsonRequest(TestData.generateFirstName(),TestData.generateLastName(),TestData.generateAddress(),TestData.generateMetroStation(),TestData.generatePhone(),TestData.generateRentTime(),TestData.generateDeliveryDate(),TestData.generateComment(), new ArrayList<String>()),true},
                {new OrderCreateJsonRequest(TestData.generateFirstName(),TestData.generateLastName(),TestData.generateAddress(),TestData.generateMetroStation(),TestData.generatePhone(),TestData.generateRentTime(),TestData.generateDeliveryDate(),TestData.generateComment()),true},
        };
    }

    @Before
    public void setUp(){
        RestAssured.baseURI = Configs.YASCOOTER_API_BASEURL;
    }

    @DisplayName("Создание заказа с различными цветами")
    @Test
    public void createOrderWithDifferentColors() {
        Actions.sendOrderCreate(orderCreateRequest)
                .then()
                .assertThat().body("$", hasKey("track"))
                .and()
                .statusCode(201);

    }
}