package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class DropDown {

    WebDriver driver;

    public DropDown(WebDriver driver) {
        this.driver = driver;
    }

    public void pick() {
        $(By.xpath("//div[text()='Classic']")).click();
    }
}
