package wrappers;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CheckBox {
        public void check() {
        $(By.xpath("//input[@type='checkbox']")).click();
    }
}
