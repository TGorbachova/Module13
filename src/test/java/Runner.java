import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Tetiana_Horbachova on 12/16/2015.
 */
public class Runner {

    @Test
    public void test(){
        WebDriver driver = new FirefoxDriver();
        driver.get("https://mail.rambler.ru/");
        driver.findElement(By.xpath("//input[@class='form-input form-input_login form-input_login-focus']")).sendKeys("tetyana.gorbachova");
        driver.findElement(By.id("form_password")).sendKeys("Test2015");

        driver.findElement(By.xpath("//button[@class='form-button form-button_submit']")).click();

        //Checking if log in is successfull
        System.out.println("Checking if login is successfull");
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='b-rambler-topline-user__name']")).isDisplayed(), "Log in is not successfull");


        driver.findElement(By.xpath("//button[@title='Написать письмо']")).click();

        driver.findElement(By.className("uiAutocompleteTextInput")).sendKeys("tetyana.gorbachova@rambler.ru");
        driver.findElement(By.xpath("//input[@id='subject']")).sendKeys("test");

        //driver.findElement(By.xpath("//div[@class='uiTextarea composeTextarea']")).sendKeys("test");
        driver.findElement(By.xpath("//button[@title='Сохранить письмо как черновик']")).click();
        driver.findElement(By.xpath("//button[@title='К письмам']")).click();
        driver.findElement(By.xpath("//span[contains(.,'Черновики')]")).click();

        //Checking if message is present in Draft folder
        System.out.println("Checking message in draft folder");
        Assert.assertTrue(driver.findElement(By.xpath("//a[@class='tableCell tableSenderRow']")).isDisplayed(), "Message is not present");

        driver.findElement(By.xpath("//a[@class='tableCell tableSenderRow']")).click();


        driver.findElement(By.xpath("//button[contains(.,'Редактировать')]")).click();

        // Checking if message has correct attributes
        System.out.println("Checking To email attribute");
        Assert.assertEquals(driver.findElement(By.xpath("//b[@class='uiAddress']")).getText(), "tetyana.gorbachova@rambler.ru", "Incorrect email");

        System.out.println("Checking Subject email attribute");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@autocomplete='off']")).getText(), "", "Incorrect subject");

        //Send message
        driver.findElement(By.xpath("//button[@class='uiButton uiButtonNormal appComposeSend']")).click();
        //Go to Send folder
        driver.findElement(By.xpath("//span[contains(.,'Отправленные')]")).click();

        //Checking if message is present in send folder
        System.out.println("Checking message in send folder");
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='b-rambler-topline-user__name']")).isDisplayed(), "Message is not present");

        //Log out
        driver.findElement(By.xpath("//span[@class='b-rambler-topline-user__name']")).click();
        driver.findElement(By.xpath("//a[@data-cerber-topline='logout']")).click();

        driver.close();

    }
}
