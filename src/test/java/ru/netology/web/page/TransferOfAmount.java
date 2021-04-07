package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TransferOfAmount {
    private SelenideElement amount = $("[data-test-id=amount] input");
    private SelenideElement fromWhere = $("[data-test-id=from] input");
    private SelenideElement button = $("[data-test-id=action-transfer]");
    private SelenideElement errorNotification = $("[data-test-id=error-notification]");

    public TransferOfAmount() {
        amount.shouldBe(visible);
    }

    public DashboardPage enterValue(String sum, String from) {
        String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;
        amount.sendKeys(deleteString);
        amount.sendKeys(sum);
        fromWhere.sendKeys(deleteString);
        fromWhere.sendKeys(from);
        button.click();
        return new DashboardPage();
    }

    public void enterInvalidValue(String sum, String from) {
        String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;
        amount.sendKeys(deleteString);
        amount.sendKeys(sum);
        fromWhere.sendKeys(deleteString);
        fromWhere.sendKeys(from);
        button.click();
        errorNotification.shouldBe(visible);
    }

    public void sendingEmptyNumberAndAmount() {
        String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;
        amount.sendKeys(deleteString);
        fromWhere.sendKeys(deleteString);
        button.click();
        errorNotification.shouldBe(visible);
    }
}
