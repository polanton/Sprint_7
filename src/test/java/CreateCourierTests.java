import config.Configs;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import requests.CreateCourierJsonRequest;
import requests.DeleteCourierJsonRequest;
import requests.LoginJsonRequest;
import requests.LoginJsonResponse;
import testdata.TestData;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateCourierTests {

    private Boolean isSucsess;
    private String[] createdUserCreditals = new String[2];



    //проверка была ли создана учётка, если быыла, то сохраняем логин/пароль для последующего удаления
    public void checkIfIsSucsessForRollbackTestData(Response response, CreateCourierJsonRequest createRequest){
        if ( response.getBody().peek().jsonPath().get("ok").equals(true)){
            isSucsess = true;
            createdUserCreditals[0] = createRequest.getLogin();
            createdUserCreditals[1] = createRequest.getPassword();
        }

    }

    @Before
    public void setUp(){
        RestAssured.baseURI = Configs.YASCOOTER_API_BASEURL;
        isSucsess = false;
    }

    @Step("Успешное создание курьера")
    public void createSuccessStep(CreateCourierJsonRequest createRequest){
           Response response = Actions.sendCreate(createRequest);

        checkIfIsSucsessForRollbackTestData(response,createRequest);

        response.then()
                .assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(201);

    }
    @DisplayName("Успешное создание курьера")
    @Test
    public void createSuccess(){
         CreateCourierJsonRequest createRequest =
              new CreateCourierJsonRequest(TestData.generateLogin(),TestData.generatePassword(),TestData.generateFirstName());

        createSuccessStep(createRequest);
    }
    @DisplayName("Повторнрое создание курьера")
    @Test
    public void DuplicateCreationReturnsError(){
        CreateCourierJsonRequest createRequest =
                new CreateCourierJsonRequest(TestData.generateLogin(),TestData.generatePassword(),TestData.generateFirstName());

        createSuccessStep(createRequest);

        Actions.sendCreate(createRequest).then().
                assertThat()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(409);
    }
    @DisplayName("Попытка создания без логина в звапросе")
    @Test
    public void CreateWithoutLoginReturnsError(){
        CreateCourierJsonRequest createRequest = CreateCourierJsonRequest.withoutLogin(TestData.generatePassword(),TestData.generateFirstName());
        Response response = Actions.sendCreate(createRequest);

        response.then()
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(400);

    }
    @DisplayName("Попытка создания без пароля в запросе")
    @Test
    public void CreateWithoutPasswordReturnsError(){
        CreateCourierJsonRequest createRequest = CreateCourierJsonRequest.withoutPassword(TestData.generateLogin(),TestData.generateFirstName());
        Response response = Actions.sendCreate(createRequest);

        response.then()
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(400);

    }
    @DisplayName("Создание без необязательного поля имя")
    @Test
    public void CreateWithoutFirstNameReturnsSucsess(){
        CreateCourierJsonRequest createRequest = CreateCourierJsonRequest.withoutFirstName(TestData.generateLogin(),TestData.generatePassword());
        Response response = Actions.sendCreate(createRequest);

        response.then()
                .assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(201);

        checkIfIsSucsessForRollbackTestData(response,createRequest);

    }

    @After
    public void tearDown(){
       //если было успешное создание, то входим, получаем id , отправляем запрос на удаление созданного курьера
        if (isSucsess){
           int courierId = Actions.sendLogin(new LoginJsonRequest(createdUserCreditals[0],createdUserCreditals[1]))
                   .as(LoginJsonResponse.class).getId();
           Actions.sendDelete(new DeleteCourierJsonRequest(courierId));

       }
    }
}
