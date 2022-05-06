package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderTestNegative { //Версия 101.0.4951.54

    WebDriver driver;

    @BeforeAll

    static void setupClass() {
        WebDriverManager.chromedriver()
                        .setup();
    }

    @BeforeEach
    public void setUp2() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void close() {
        driver.quit();
        driver = null;
    }

    @Test
    @DisplayName("Name Empty")
    public void shouldShowErrorInvalidNameFieldEmpty() {

        driver.get("http://localhost:9999");
        List<WebElement> elements = driver.findElements(By.className("input__control"));
        List<WebElement> elementSub = driver.findElements(By.className("input__sub"));
        elements.get(0)
                .sendKeys("");
        elements.get(1)
                .sendKeys("+79119999999");
        driver.findElement(By.className("checkbox__box"))
              .click();
        driver.findElement(By.className("button__content"))
              .click();

        String text = elementSub.get(0)
                            .getText();
        assertEquals("Поле обязательно для заполнения", text);
    }
    @Test
    @DisplayName("Name Eng")
    public void shouldShowErrorInvalidNameEnglishName() {

        driver.get("http://localhost:9999");
        List<WebElement> elements = driver.findElements(By.className("input__control"));
        List<WebElement> elementSub = driver.findElements(By.className("input__sub"));
        elements.get(0)
                .sendKeys("Vasya Pupkin");
        elements.get(1)
                .sendKeys("+79119999999");
        driver.findElement(By.className("checkbox__box"))
              .click();
        driver.findElement(By.className("button__content"))
              .click();

        String text = elementSub.get(0)
                                .getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text);
    }

    @Test
    @DisplayName("Phone Empty")
    public void shouldShowErrorInvalidPhoneFieldEmpty() {

        driver.get("http://localhost:9999");
        List<WebElement> elements = driver.findElements(By.className("input__control"));
        List<WebElement> elementSub = driver.findElements(By.className("input__sub"));
        elements.get(0)
                .sendKeys("Вася Пупкин");
        elements.get(1)
                .sendKeys("");
        driver.findElement(By.className("checkbox__box"))
              .click();
        driver.findElement(By.className("button__content"))
              .click();
        String text = elementSub.get(1)
                            .getText();
        assertEquals("Поле обязательно для заполнения", text);
    }

    @Test
    @DisplayName("Phone ++ Digits")
    public void shouldShowErrorInvalidPhoneFieldMoreDigits() {

        driver.get("http://localhost:9999");
        List<WebElement> elements = driver.findElements(By.className("input__control"));
        List<WebElement> elementSub = driver.findElements(By.className("input__sub"));
        elements.get(0)
                .sendKeys("Вася Пупкин");
        elements.get(1)
                .sendKeys("+791199999999");
        driver.findElement(By.className("checkbox__box"))
              .click();
        driver.findElement(By.className("button__content"))
              .click();
        String text = elementSub.get(1)
                                .getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text);
    }
    @Test
    @DisplayName("Phone StartsFrom 8")
    public void shouldShowErrorInvalidPhoneStartsFrom8() {

        driver.get("http://localhost:9999");
        List<WebElement> elements = driver.findElements(By.className("input__control"));
        List<WebElement> elementSub = driver.findElements(By.className("input__sub"));
        elements.get(0)
                .sendKeys("Вася Пупкин");
        elements.get(1)
                .sendKeys("89119999999");
        driver.findElement(By.className("checkbox__box"))
              .click();
        driver.findElement(By.className("button__content"))
              .click();
        String text = elementSub.get(1)
                                .getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text);
    }







}
