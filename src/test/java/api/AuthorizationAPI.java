package api;

import data.TestData;
import io.qameta.allure.Step;
import models.LoginBodyModel;
import models.LoginResponseModel;

import static data.UrlPath.LOG_PATH;
import static io.restassured.RestAssured.given;
import static specs.ApiDemoSpec.requestSpec;
import static specs.ApiDemoSpec.responseSpecWithStatusCode200;

public class AuthorizationAPI {

    @Step("Авторизация через API")

    public LoginResponseModel log() {
        LoginBodyModel loginBodyModel = new LoginBodyModel();
        loginBodyModel.setUserName(TestData.login);
        loginBodyModel.setPassword(TestData.password);

        return
                given(requestSpec)
                        .body(loginBodyModel)
                        .when()
                        .post(LOG_PATH)
                        .then()
                        .spec(responseSpecWithStatusCode200)
                        .extract().as(LoginResponseModel.class);

    }


}
