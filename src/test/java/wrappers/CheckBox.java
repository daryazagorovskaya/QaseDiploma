package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class CheckBox {
        public void check() {
        $(By.xpath("//input[@type='checkbox']")).click();
    }
}
