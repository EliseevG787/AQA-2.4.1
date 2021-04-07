package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement firstButton = $$("[data-test-id=action-deposit]").first();
    private SelenideElement secondButton = $$("[data-test-id=action-deposit]").last();
    private ElementsCollection cards = $$(".list__item");


    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }

    public int getSecondCardBalance() {
        val text = cards.last().text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public DashboardPage() {
        firstButton.shouldBe(visible);
    }

    public TransferOfAmount transferToTheFirstCard() {
        firstButton.click();
        return new TransferOfAmount();
    }

    public TransferOfAmount transferToTheSecondCard() {
        secondButton.click();
        return new TransferOfAmount();
    }

    public void settingInitialValues() {
        int difference = 0;
        if (getFirstCardBalance() < 10000) {
            difference = 10000 - getFirstCardBalance();
            transferToTheFirstCard().enterValue(Integer.toString(difference), "5559 0000 0000 0002");
        }
        if (getFirstCardBalance() > 10000) {
            difference = getFirstCardBalance() - 10000;
            difference = 10000 - getFirstCardBalance();
            transferToTheSecondCard().enterValue(Integer.toString(difference), "5559 0000 0000 0001");
        }
    }
}
