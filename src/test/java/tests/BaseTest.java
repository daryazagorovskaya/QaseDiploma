package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import pages.ProjectsPage;
import pages.SuitePage;
import pages.TestCasePage;
import utils.PropertyReader;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {
    public static final String URL = "https://api.qase.io";
    public static String USER;
    public static String PASSWORD;
    public static String TOKEN;
    LoginPage loginPage;
    ProjectsPage projectsPage;
    TestCasePage testCasePage;
    SuitePage suitePage;

    Faker faker;

    @BeforeMethod
    public void setup() {
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.timeout = 10000;
        Configuration.baseUrl = "https://app.qase.io";
        open();
        getWebDriver().manage().window().maximize();
        faker = new Faker();
        loginPage = new LoginPage();
        projectsPage = new ProjectsPage();
        testCasePage = new TestCasePage();
        suitePage = new SuitePage();

        USER = System.getProperty("user", PropertyReader.getProperty("qs.user"));
        PASSWORD = System.getProperty("password", PropertyReader.getProperty("qs.password"));
        TOKEN = System.getProperty("token", PropertyReader.getProperty("qs.token"));


    }

    @AfterMethod(alwaysRun = true)
    public void close() {
        closeWebDriver();
    }
}
