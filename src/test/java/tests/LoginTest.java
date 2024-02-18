package tests;

import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Log4j2
public class LoginTest extends BaseTest {
    @BeforeMethod
    public void setUp() {
        loginPage.openPage();
    }

    @Test (description = "Authorization with correct data")
    public void login() {
        loginPage.login(USER, PASSWORD);
        Assert.assertEquals(projectsPage.waitTillOpenedIsDisplayed(), "Create new project", "Test failed");
    }

    @Test (description = "Authorization with wrong password")
    public void negativeLogin() {
        loginPage.login(USER, PASSWORD + ")");
        Assert.assertEquals(projectsPage.isAlertDisplayed(), "These credentials do not match our records.", "Test failed");
    }

    @Test (description = "Logout from account")
    public void logOut() {
        loginPage.login(USER, PASSWORD);
        projectsPage.waitTillOpened();
        projectsPage.logOut();
        projectsPage.waitTillOpenedAutPage();
        Assert.assertEquals(projectsPage.isAutPageDisplayed(), "Log in to your account", "Test failed");
    }

    @Test (description = "Check button 'Forget password'")
    public void checkButtonForgetPassword() {
        loginPage.login(USER, PASSWORD);
        loginPage.openPageResetPassword();
        Assert.assertEquals(loginPage.isResetPageDisplayed(), "Reset your password", "Test failed");
    }
}
