package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test (description = "Authorization with correct data")
    public void login() {
        loginPage.openPage();
        loginPage.login("zagorik.dasha17@gmail.com", "Switch206med)");
        Assert.assertEquals(projectsPage.waitTillOpenedIsDisplayed(), "Create new project", "Test failed");
    }

    @Test (description = "Authorization with wrong password")
    public void negativeLogin() {
        loginPage.openPage();
        loginPage.login("zagorik.dasha17@gmail.com", "Switch206med))))");
        Assert.assertEquals(projectsPage.isAlertDisplayed(), "These credentials do not match our records.", "Test failed");
    }

    @Test (description = "Logout from account")
    public void logOut() {
        loginPage.openPage();
        loginPage.login("zagorik.dasha17@gmail.com", "Switch206med)");
        projectsPage.waitTillOpened();
        String projectName = faker.name().firstName() + faker.name().lastName();
        projectsPage.logOut();
        projectsPage.waitTillOpenedAutPage();
        Assert.assertEquals(projectsPage.isAutPageDisplayed(), "Log in to your account", "Test failed");
    }

    @Test (description = "Check button 'Forget password'")
    public void checkButtonForgetPassword() {
        loginPage.openPage();
        loginPage.login("zagorik.dasha17@gmail.com", "Switch206med)");
        loginPage.openPageResetPassword();
        Assert.assertEquals(loginPage.isResetPageDisplayed(), "Reset your password", "Test failed");
    }
}
