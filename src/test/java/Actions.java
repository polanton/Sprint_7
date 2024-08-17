import config.Configs;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import requests.CreateCourierJsonRequest;
import requests.DeleteCourierJsonRequest;
import requests.LoginJsonRequest;
import requests.OrderCreateJsonRequest;

import static io.restassured.RestAssured.given;

public class Actions {


    @Step("отправка запроса на создание курьера")
    public static Response sendCreate(CreateCourierJsonRequest requestJson){
        return given()
                .header("Content-type", "application/json")
                .body(requestJson)
                .when()
                .post(Configs.CREATE_COURIER_URL);
    }
    @Step("отправка запроса на логин в системе")
    public static Response sendLogin(LoginJsonRequest requestJson){
        return given()
                .header("Content-type", "application/json")
                .body(requestJson)
                .post(Configs.LOGIN_URL);
    }
    @Step("отправка запроса на удаление курьера")
    public static Response sendDelete(DeleteCourierJsonRequest requestJson){
        return given()
                .header("Content-type", "application/json")
                .body(requestJson)
                .delete(String.format(Configs.DELETE_COURIER_URL, requestJson.getId() ));
    }

    @Step("Отправка запроса на создание заказа")
    public static Response sendOrderCreate(OrderCreateJsonRequest requestJson){
        return given()
                .header("Content-type", "application/json")
                .body(requestJson)
                .post(Configs.CREATE_ORDER_URL);
    }

    @Step("Отправка запроса на получение списка заказов с параметрами limit и page")
    public static Response sendGetOrderListByLimitAndPage(String limit, String page){
        return given()
                .header("Content-type", "application/json")
                .get(Configs.ORDER_LIST_URL+String.format("?limit=%s&page=%s",limit,page));
    }
}
