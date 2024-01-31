package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class CheckBox {
    WebDriver driver;

    public CheckBox(WebDriver driver) {
        this.driver = driver;

    }

    public void check() {
        $(By.xpath("//input[@type='checkbox']")).click();
    }
}
