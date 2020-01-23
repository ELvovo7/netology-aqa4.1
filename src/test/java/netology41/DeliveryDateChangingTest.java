package netology41;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryDateChangingTest {

    private String testSite = "http://localhost:9999";

    @Test
    @DisplayName("Валидные данные. Ожидаем сообщение 'Успешно'")
    void inputValidDataExpectSuccess() {
        open(testSite);
        TestData.TestUser validUser = TestData.newValidUser();

        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id=name] input").setValue(validUser.getFullName());
        $("[data-test-id=phone] input").setValue(validUser.getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $$(".button").findBy(Condition.exactText("Запланировать")).click();
        $("[data-test-id=success-notification]").waitUntil(Condition.disappear, 3000);
    }

    @Test
    @DisplayName("Валидные данные. Перепланируем, меняя дату.")
    void replacedIfDateWasChanged() {
        open(testSite);
        TestData.TestUser validUser = TestData.newValidUser();

        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id=name] input").setValue(validUser.getFullName());
        $("[data-test-id=phone] input").setValue(validUser.getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $$(".button").findBy(Condition.exactText("Запланировать")).click();
        $("[data-test-id=success-notification]").waitUntil(Condition.disappear, 3000);
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        $("[data-test-id=date] input").sendKeys(TestData.shiftMyDate(4));
        // лучше было бы считать текущее значение и прибавить день к нему
        $$(".button").findBy(Condition.exactText("Запланировать")).click();
        $("[data-test-id=replan-notification]").waitUntil(Condition.disappear, 3000);
    }

    @Test
    @DisplayName("Невалидный телефон. Не должно быть сообщения 'Успешно'")
    void shouldBeErrorIfPhoneIsInvalid() {
        open(testSite);
        TestData.TestUser validUser = TestData.newUserWithInvalidPhone();

        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id=name] input").setValue(validUser.getFullName());
        $("[data-test-id=phone] input").setValue(validUser.getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $$(".button").findBy(Condition.exactText("Запланировать")).click();
        $("[data-test-id=phone]").shouldHave(Condition.cssClass("input_invalid"));
        $("[data-test-id=success-notification]").waitUntil(Condition.not(Condition.disappear), 3000);
    }

    @Test
    @DisplayName("Невалидные имя-фамилия. Ожидаем ошибку.")
    void shouldBeErrorIfNameIsInvalid() {
        open(testSite);
        TestData.TestUser validUser = TestData.newInvalidUser();

        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id=name] input").setValue(validUser.getFullName());
        $("[data-test-id=phone] input").setValue(validUser.getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $$(".button").findBy(Condition.exactText("Запланировать")).click();
        $$("[data-test-id=name]").findBy(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).waitUntil(Condition.visible, 3000);
    }
}