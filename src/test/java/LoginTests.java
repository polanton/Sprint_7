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
import static org.hamcrest.Matchers.hasKey;

public class LoginTests {

    private Boolean creationWasSuccessful;
    private String[] createdUserCreditals = new String[2];



    //проверка была ли создана учётка, если быыла, то сохраняем логин/пароль для последующего удаления
    public void checkIfIsSucsessForRollbackTestData(Response response, CreateCourierJsonRequest createRequest){
        if ( response.getBody().peek().jsonPath().get("ok").equals(true)){
            creationWasSuccessful = true;
            createdUserCreditals[0] = createRequest.getLogin();
            createdUserCreditals[1] = createRequest.getPassword();
        }

    }

    @Before
    public void setUp(){
        RestAssured.baseURI = Configs.YASCOOTER_API_BASEURL;
        creationWasSuccessful = false;
    }

    @Step("Создание курьера для теста")
    public void createSuccessStep(CreateCourierJsonRequest createRequest){
        Response response = Actions.sendCreate(createRequest);

        checkIfIsSucsessForRollbackTestData(response,createRequest);

        response.then()
                .assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(201);

    }

    @Step("Вход под созданным курьером")
    public void loginSuccessStep(LoginJsonRequest loginRequest){
        Actions.sendLogin(loginRequest)
                .then()
                .assertThat().body("$", hasKey("id"))
                .and()
                .statusCode(200);
    }

    @DisplayName("Успешный вход после создания")
    @Test
    public void successfulLogin(){
        CreateCourierJsonRequest createRequest =
                new CreateCourierJsonRequest(TestData.generateLogin(),TestData.generatePassword(),TestData.generateFirstName());

        createSuccessStep(createRequest);

        loginSuccessStep(new LoginJsonRequest(createRequest.getLogin(),createRequest.getPassword()));

    }

    @DisplayName("Попытка входа без поля логин в звапросе")
    @Test
    public void withoutLoginFieldReturnError(){
        CreateCourierJsonRequest createRequest =
                new CreateCourierJsonRequest(TestData.generateLogin(),TestData.generatePassword(),TestData.generateFirstName());

        createSuccessStep(createRequest);

        Actions.sendLogin(LoginJsonRequest.withoutLogin(createRequest.getPassword()))
                .then()
                .assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(400);
    }
/*
    @DisplayName("Попытка входа без пароля в запросе")
    @Test
    public void withoutPasswordFieldReturnError(){
        CreateCourierJsonRequest createRequest =
                new CreateCourierJsonRequest(TestData.generateLogin(),TestData.generatePassword(),TestData.generateFirstName());

        createSuccessStep(createRequest);

        Actions.sendLogin(LoginJsonRequest.withoutPassword(createRequest.getLogin()))
                .then()
                .assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(400);
    }
*/
    @DisplayName("Попытка входа с некорректным паролем")
    @Test
    public void withWrongPasswordReturnError(){
        CreateCourierJsonRequest createRequest =
                new CreateCourierJsonRequest(TestData.generateLogin(),TestData.generatePassword(),TestData.generateFirstName());

        createSuccessStep(createRequest);

        Actions.sendLogin(new LoginJsonRequest(createRequest.getLogin(),createRequest.getPassword()+"XXX"))
                .then()
                .assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404);
    }

    @DisplayName("попытка входа с несуществующим логином")
    @Test
    public void withWrongLoginReturnError(){
        CreateCourierJsonRequest createRequest =
                new CreateCourierJsonRequest(TestData.generateLogin(),TestData.generatePassword(),TestData.generateFirstName());

        createSuccessStep(createRequest);

        String wrongLogin = createRequest.getLogin() + createRequest.getLogin();

        Actions.sendLogin(new LoginJsonRequest(wrongLogin,createRequest.getPassword()))
                .then()
                .assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404);
    }

    @DisplayName("попытка входа с несуществующим логином и паролем")
    @Test
    public void withWrongLoginAndPasswordReturnsError(){
        CreateCourierJsonRequest createRequest =
                new CreateCourierJsonRequest(TestData.generateLogin(),TestData.generatePassword(),TestData.generateFirstName());

        createSuccessStep(createRequest);

        String wrongLogin = createRequest.getLogin() + createRequest.getLogin();

        Actions.sendLogin(new LoginJsonRequest(wrongLogin,createRequest.getPassword()+"XXX"))
                .then()
                .assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404);
    }

    @After
    public void tearDown(){
        //если было успешное создание, то входим, получаем id , отправляем запрос на удаление созданного курьера
        if (creationWasSuccessful){
            int courierId = Actions.sendLogin(new LoginJsonRequest(createdUserCreditals[0],createdUserCreditals[1]))
                    .as(LoginJsonResponse.class).getId();
            Actions.sendDelete(new DeleteCourierJsonRequest(courierId));

        }
    }
}
