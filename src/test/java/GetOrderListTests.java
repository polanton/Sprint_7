import config.Configs;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import requests.GetOrdersJsonResponse;

import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrderListTests {

    @Before
    public void setUp(){
        RestAssured.baseURI = Configs.YASCOOTER_API_BASEURL;
    }

    @Test
    @DisplayName("Успешное получение списка заказов")
    public void getOrderListReturnsValidBody(){
        Response response = Actions.sendGetOrderListByLimitAndPage("10","0");

        checkGetOrderListResponse(response);

    }

    @Step("Проверка ответа на запрос списка заказов")
    public void checkGetOrderListResponse(Response response){
        response
                .then()
                .assertThat()
                .statusCode(200);

        GetOrdersJsonResponse getOrdersResponseBody =
                response.as(GetOrdersJsonResponse.class);

        MatcherAssert.assertThat(getOrdersResponseBody, notNullValue());
        MatcherAssert.assertThat(String.valueOf(!getOrdersResponseBody.getOrders().isEmpty()), true);

    }

}
;