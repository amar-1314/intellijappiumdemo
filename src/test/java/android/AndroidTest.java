package android;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import setup.Order;
import setup.OrderedRunner;
import setup.Setup;

@SuppressWarnings(value = "unchecked")
@RunWith(OrderedRunner.class)
public class AndroidTest {
    private AndroidDriver<AndroidElement> driver;
    private WebDriverWait wait;

    public AndroidTest() {
        driver = (AndroidDriver<AndroidElement>) Setup.driver;
        wait = new WebDriverWait(driver, 30);
    }

    @Test
    @Order(order = 1)
    public void loginTest() {
        driver.findElement(By.xpath("//*[@id='login_edittext_username']")).sendKeys("cptester424@cp.com");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='login_edittext_password']")));
        driver.findElement(By.xpath("//*[@id='login_edittext_password']")).sendKeys("test1234");
        driver.findElement(By.id("login_button_login")).click();
    }

    @Test
    @Order(order = 2)
    public void navigationToMyAccountTest() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@contentDescription='Open navigation drawer']")));
        driver.findElement(By.xpath("//*[@contentDescription='Open navigation drawer']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='My Account']")));
        driver.findElement(By.xpath("//*[@text='My Account']")).click();
    }

}
