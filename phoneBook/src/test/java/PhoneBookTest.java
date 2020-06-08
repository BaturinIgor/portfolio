import io.qameta.allure.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PhoneBookTest {
    private ChromeDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        /*НЕОБХОДИМО ЗАМЕНИТЬ ПУТЬ ДО CHROMEDRIVER.EXE*/
        System.setProperty("webdriver.chrome.driver", "C:/Program Files/chrome driver/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        driver = new ChromeDriver(options);

        /*Открытие браузера и страницы*/
        driver.get("http://localhost:8080/phonebook-api.html");
        wait = new WebDriverWait(driver, 50);
        /*Ожидание, пока тег, с которым будет работа, прогрузится*/
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"opblock-tag-section is-open\"]")));
    }

    @Epic("TESTING FOR http://localhost:8080/phonebook-api.html TASKS")
    @Feature(value = "Tests for get request /users/{userId}/contacts/{contactId}")
    @Severity(SeverityLevel.NORMAL)
    @Description("In this test we will enter correct userId and contactId. When we entered we see server response 200 and response body")
    @Story(value = "Test for get request with correct values")
    @Test
    public void searchByUserIDAndContactID() {
        /*Нажатие на область для получения GET-запроса по userID и contactID*/
        driver.findElement(By.id("operations-default-getContactById")).click();

        /*Ожидание, пока открывшая область прогузится для того, чтобы не было ошибок при поиске еще неотобразившихся элементов*/
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"opblock opblock-get is-open\"]")));

        /*Нажатие на кнопку Try it out для возможности ввести userID и contactID"*/
        driver.findElement(By.xpath("//*[@class=\"btn try-out__btn\"]")).click();

        /*Ввод допустимых значение в userID и contactID*/
        driver.findElement(By.xpath("//*[@data-param-name=\"userId\"]"))
                .findElement(By.tagName("input")).sendKeys("2");
        driver.findElement(By.xpath("//*[@data-param-name=\"contactId\"]"))
                .findElement(By.tagName("input")).sendKeys("1");

        /*Нажатие на кнопку Execute для получения GET-запроса*/
        driver.findElement(By.xpath("//*[@class=\"btn execute opblock-control__btn\"]")).click();

        /*Ожидание, пока данные придут с сервера*/
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"responses-table live-responses-table\"]")));

        /*Проверка на то, что код запроса 200 и данные пришли с сервера*/
        assert(driver.findElement(By.xpath("//*[@class=\"response\"]"))
                .findElement(By.xpath("//*[@class=\"response-col_status\"]")).getText()
                .equals("200"));

        /*Нажатие на кнопку Clear для очищения результата запроса*/
        driver.findElement(By.xpath("//*[@class=\"btn btn-clear opblock-control__btn\"]")).click();

        /*Нажатие на кнопку Cancel, чтобы поля userID и contactID вернулись в состояния "disabled"*/
        driver.findElement(By.xpath("//*[@class=\"btn try-out__btn cancel\"]")).click();

        /*Закрытие области с данными*/
        driver.findElement(By.id("operations-default-getContactById")).click();
    }

    @Epic("TESTING FOR http://localhost:8080/phonebook-api.html TASKS")
    @Feature(value = "Tests for get request /users/{userId}/contacts/{contactId}")
    @Severity(SeverityLevel.NORMAL)
    @Description("In this test we will not enter values userId and contactId. When we entered, the system will highlight the required fields in red")
    @Story(value = "Test for get request with empty values")
    @Test
    public void searchByUserIDAndContactIDEmptyFieldsFailure() {
        driver.findElement(By.id("operations-default-getContactById")).click();

        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"opblock opblock-get is-open\"]")));

        driver.findElement(By.xpath("//*[@class=\"btn try-out__btn\"]")).click();
        driver.findElement(By.xpath("//*[@class=\"btn execute opblock-control__btn\"]")).click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[class=\"invalid\"")));

        assert(driver.findElement(By.cssSelector("[placeholder=\"userId\"]")).getAttribute("class")
                .equals("invalid"));
        assert(driver.findElement(By.cssSelector("[placeholder=\"userId\"]")).getAttribute("title")
                .equals("Required field is not provided"));
        assert(driver.findElement(By.cssSelector("[placeholder=\"contactId\"]")).getAttribute("class")
                .equals("invalid"));
        assert(driver.findElement(By.cssSelector("[placeholder=\"contactId\"]")).getAttribute("title")
                .equals("Required field is not provided"));

        driver.findElement(By.xpath("//*[@class=\"btn try-out__btn cancel\"]")).click();
        driver.findElement(By.id("operations-default-getContactById")).click();
    }

    @Epic("TESTING FOR http://localhost:8080/phonebook-api.html TASKS")
    @Feature(value = "Tests for get request /users/{userId}/contacts/{contactId}")
    @Severity(SeverityLevel.NORMAL)
    @Description("In this test we will enter incorrect userId and contactId. When we entered, the system will highlight in red the wrong fields to fill")
    @Story(value = "Test for get request with incorrect values")
    @Test
    public void searchByUserIDAndContactIDWrongFieldsFailure() {
        driver.findElement(By.id("operations-default-getContactById")).click();

        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"opblock opblock-get is-open\"]")));

        driver.findElement(By.xpath("//*[@class=\"btn try-out__btn\"]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"btn execute opblock-control__btn\"]")));

        driver.findElement(By.xpath("//*[@data-param-name=\"userId\"]"))
                .findElement(By.tagName("input")).sendKeys("Igor");
        driver.findElement(By.xpath("//*[@data-param-name=\"contactId\"]"))
                .findElement(By.tagName("input")).sendKeys("Igor228");

        driver.findElement(By.xpath("//*[@class=\"btn execute opblock-control__btn\"]")).click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[class=\"invalid\"")));

        assert(driver.findElement(By.cssSelector("[placeholder=\"userId\"]")).getAttribute("class")
                .equals("invalid"));
        assert(driver.findElement(By.cssSelector("[placeholder=\"userId\"]")).getAttribute("title")
                .equals("Value must be an integer"));
        assert(driver.findElement(By.cssSelector("[placeholder=\"contactId\"]")).getAttribute("class")
                .equals("invalid"));
        assert(driver.findElement(By.cssSelector("[placeholder=\"contactId\"]")).getAttribute("title")
                .equals("Value must be an integer"));

        driver.findElement(By.xpath("//*[@class=\"btn try-out__btn cancel\"]")).click();
        driver.findElement(By.id("operations-default-getContactById")).click();
    }

    @Epic("TESTING FOR http://localhost:8080/phonebook-api.html TASKS")
    @Feature(value = "Tests for get request /users/search")
    @Severity(SeverityLevel.NORMAL)
    @Description("In this test we will enter correct userId and contactId. When we entered we see server response 200 and response body")
    @Story(value = "Test for get request with correct values")
    @Test
    public void searchUsersByName() {
        driver.findElement(By.id("operations-default-getUsersByName")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"opblock opblock-get is-open\"]")));

        driver.findElement(By.xpath("//*[@class=\"btn try-out__btn\"]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"btn execute opblock-control__btn\"]")));

        driver.findElement(By.xpath("//*[@data-param-name=\"name\"]"))
                .findElement(By.tagName("input")).sendKeys("John");

        driver.findElement(By.xpath("//*[@class=\"btn execute opblock-control__btn\"]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"responses-table live-responses-table\"]")));

        assert(driver.findElement(By.xpath("//*[@class=\"response\"]"))
                .findElement(By.xpath("//*[@class=\"response-col_status\"]")).getText()
                .equals("200"));

        driver.findElement(By.xpath("//*[@class=\"btn btn-clear opblock-control__btn\"]")).click();
        driver.findElement(By.xpath("//*[@class=\"btn try-out__btn cancel\"]")).click();
        driver.findElement(By.id("operations-default-getContactById")).click();
    }

    @After
    public void close() {
        /*Закрытие браузера*/
        driver.quit();
    }
}
