package pages;


import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Slf4j
public class AccountPage {


    @Step("Открытие профиля")
    public AccountPage openPage() {
        open("/profile");
        return this;
    }

    @Step("Проверка, что в коллекции есть книга")
    public AccountPage checkHaveBooks(String title) {
        $("[id='see-book-" + title + "']").shouldBe(visible);
        return this;
    }

    @Step("Проверка, что в коллекции нет книги")
    public AccountPage checkNotBooks(String title) {
        $("[id='see-book-" + title + "']").shouldNotBe(visible);
        return this;
    }


}
