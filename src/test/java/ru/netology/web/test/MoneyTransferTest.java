package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {

    public DashboardPage setUp() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
//      val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor();
        val dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.settingInitialValues();
        return dashboardPage;
    }

    @Test
    void shouldTransferMoneyFromTheFirstCardToTheSecond() {
        val dashboardPage = setUp();
        val transferOfAmount = dashboardPage.transferToTheSecondCard();
        val firstСardNumber = DataHelper.getFirstCardsInfo().getNumber();
        transferOfAmount.enterValue("10", firstСardNumber);
        val balance1 = DataHelper.getFirstCardsInfo().getBalance();
        val expected1 = Integer.parseInt(balance1) - 10;
        val actual1 = dashboardPage.getFirstCardBalance();
        assertEquals(expected1, actual1);
        val balance2 = DataHelper.getSecondCardsInfo().getBalance();
        val expected2 = Integer.parseInt(balance2) + 10;
        val actual2 = dashboardPage.getSecondCardBalance();
        assertEquals(expected2, actual2);
    }

    @Test
    void shouldTransferMoneyFromTheSecondCardToTheFirst() {
        val dashboardPage = setUp();
        val transferOfAmount = dashboardPage.transferToTheFirstCard();
        val secondСardNumber = DataHelper.getSecondCardsInfo().getNumber();
        transferOfAmount.enterValue("30", secondСardNumber);
        val balance1 = DataHelper.getFirstCardsInfo().getBalance();
        val expected1 = Integer.parseInt(balance1) + 30;
        val actual1 = dashboardPage.getFirstCardBalance();
        assertEquals(expected1, actual1);
        val balance2 = DataHelper.getSecondCardsInfo().getBalance();
        val expected2 = Integer.parseInt(balance2) - 30;
        val actual2 = dashboardPage.getSecondCardBalance();
        assertEquals(expected2, actual2);
    }

    @Test
    void shouldTransferOfAmountMoreThanBalance() {
        val dashboardPage = setUp();
        val transferOfAmount = dashboardPage.transferToTheSecondCard();
        val firstСardNumber = DataHelper.getFirstCardsInfo().getNumber();
        transferOfAmount.enterInvalidValue("50000", firstСardNumber);
    }

    @Test
    void shouldTransferOfAmountWithKopecks() {
        val dashboardPage = setUp();
        val transferOfAmount = dashboardPage.transferToTheSecondCard();
        val firstСardNumber = DataHelper.getFirstCardsInfo().getNumber();
        transferOfAmount.enterValue("10,05", firstСardNumber);
        val balance1 = DataHelper.getFirstCardsInfo().getBalance();
        val expected1 = Integer.parseInt(balance1) - 10.05;
        val actual1 = dashboardPage.getFirstCardBalance();
        assertEquals(expected1, actual1);
        val balance2 = DataHelper.getSecondCardsInfo().getBalance();
        val expected2 = Integer.parseInt(balance2) + 10.05;
        val actual2 = dashboardPage.getSecondCardBalance();
        assertEquals(expected2, actual2);
    }

    @Test
    void shouldUnregisteredUserLogin() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val invalidAuthInfo = DataHelper.getInvalidAuthInfo();
        loginPage.invalidLogin(invalidAuthInfo);
    }

    @Test
    void shouldEnterInvalidCode() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val invalidVerificationCode = DataHelper.getInvalidVerificationCodeFor();
        verificationPage.invalidVerify(invalidVerificationCode);
    }

    @Test
    void shouldSubmittingEmptyForm() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        loginPage.emptyLogin();
    }

    @Test
    void shouldSendingEmptyCode() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        verificationPage.emptyVerify();
    }

    @Test
    void shouldSendingEmptyCardNumberAndAmount() {
        val dashboardPage = setUp();
        val transferOfAmount = dashboardPage.transferToTheSecondCard();
        transferOfAmount.sendingEmptyNumberAndAmount();
    }
}

