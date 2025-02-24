package api;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import models.*;


import java.util.Collections;

import static data.UrlPath.BOOKS_PATH;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static specs.ApiDemoSpec.*;

@Slf4j
public class BooksApi {

    @Step("Удаление всех книг из профиля через API")
    public void deleteAllBooks(LoginResponseModel loginResponse) {
        given(requestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .when()
                .delete(BOOKS_PATH)
                .then()
                .spec(responseSpecWithStatusCode204);
    }


    @Step("Получение данных о книгах и сохранение isbn и title")
    public AddBookBodyModel getBookData(LoginResponseModel loginResponse) {
        BookCollectionResponse response = given(requestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .when()
                .get(BOOKS_PATH)
                .then()
                .spec(responseSpecWithStatusCode200)
                .extract().as(BookCollectionResponse.class);

        AddBookBodyModel addBookBodyModel = new AddBookBodyModel();
        addBookBodyModel.setUserId(loginResponse.getUserId());
        IsbnBookModel isbnBookModel = new IsbnBookModel();

        isbnBookModel.setIsbn(response.getBooks()[0].getIsbn());
        addBookBodyModel.setCollectionOfIsbns(Collections.singletonList(isbnBookModel));

        BookListModelResponse bookListModelResponse = new BookListModelResponse();
        bookListModelResponse.setTitle(response.getBooks()[0].getTitle());

        return addBookBodyModel;
    }


    @Step("Добавление новой книги через API с использованием сохраненных данных")
    public void addBook(AddBookBodyModel addBookBodyModel, String token) {
        given(requestSpec)
                .contentType(JSON)
                .header("Authorization", "Bearer " + token)
                .body(addBookBodyModel)
                .when()
                .post(BOOKS_PATH)
                .then()
                .spec(responseSpecWithStatusCode201);


    }


    @Step("Получение данных о книгах")
    public BookListModelResponse getBookData1(LoginResponseModel loginResponse) {
        BookCollectionResponse response = given(requestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .when()
                .get(BOOKS_PATH)
                .then()
                .spec(responseSpecWithStatusCode200)
                .extract().as(BookCollectionResponse.class);

        BookListModelResponse bookListModelResponse = new BookListModelResponse();
        bookListModelResponse.setTitle(response.getBooks()[0].getTitle());

        return bookListModelResponse;

    }
}

