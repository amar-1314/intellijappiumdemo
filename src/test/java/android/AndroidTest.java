package android;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import setup.Setup;

@SuppressWarnings(value = "unchecked")
public class AndroidTest {
    private AndroidDriver<AndroidElement> driver;

    public AndroidTest() {
        driver = (AndroidDriver<AndroidElement>) Setup.driver;
    }

    @Test
    public void loginTest() {
        driver.findElement(By.xpath("//*[@id='login_edittext_username']")).sendKeys("cptester424@cp.com");
        new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='login_edittext_password']")));
        driver.findElement(By.xpath("//*[@id='login_edittext_password']")).sendKeys("test1234");
        driver.findElement(By.xpath("//*[@text='Login']")).click();
    }

}
