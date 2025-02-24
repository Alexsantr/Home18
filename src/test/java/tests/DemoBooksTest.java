package tests;

import api.AuthorizationAPI;
import api.BooksApi;
import helpers.WithLogin;
import models.AddBookBodyModel;
import models.BookListModelResponse;
import models.LoginResponseModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.AccountPage;

public class DemoBooksTest extends TestBase {
    BooksApi booksApi = new BooksApi();
    AccountPage accountPage = new AccountPage();
    LoginResponseModel loginResponse = new AuthorizationAPI().log();
    BookListModelResponse bookListModelResponse = booksApi.getBookData1(loginResponse);
    AddBookBodyModel addBookBodyModel = booksApi.getBookData(loginResponse);

    @Test
    @Tag("demode")
    @WithLogin
    void successDeleteBookFromProfileTest() {


        booksApi.addBook(addBookBodyModel, loginResponse.getToken());
        accountPage.openPage()
                .checkHaveBooks(bookListModelResponse.getTitle());
        booksApi.deleteAllBooks(loginResponse);
        accountPage.openPage()
                .checkNotBooks(bookListModelResponse.getTitle());


    }

}
