package tests;

import api.AuthorizationAPI;
import api.BooksApi;
import helpers.WithLogin;
import models.AddBookBodyModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class DemoBooksTest extends TestBase {
    BooksApi booksApi = new BooksApi();

    @Test
    @Tag("demo")
    @WithLogin
    void successDeleteBookFromProfileTest() {

        LoginResponseModel loginResponse = new AuthorizationAPI().log();
        AddBookBodyModel addBookBodyModel = booksApi.getBookData(loginResponse);
        booksApi.addBook(addBookBodyModel, loginResponse.getToken());
        booksApi.deleteAllBooks(loginResponse);
//        booksApi.checkBookInAccount(loginResponse.getUserId(),loginResponse.getToken());


    }

}
