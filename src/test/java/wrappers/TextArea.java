package wrappers;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class TextArea {

    String label;

    public TextArea(String label) {
        this.label = label;
    }
    public void write(String text) {
    $(By.xpath("//p[@data-placeholder='For example: We can authorize on page http://example.com/login']")).sendKeys(text);
    }
}
