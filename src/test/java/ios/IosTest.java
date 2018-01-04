package ios;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import setup.Setup;

@SuppressWarnings(value = "unchecked")
public class IosTest {
    private IOSDriver<IOSElement> driver;
    private WebDriverWait wait;

    public IosTest() {
        driver = (IOSDriver<IOSElement>) Setup.driver;
        wait = new WebDriverWait(driver, 30);
    }

    @Test
    public void loginTest() {
        driver.findElement(By.xpath("//*[@text='TestFlight']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='OPEN']")));
        driver.findElement(By.xpath("//*[@text='OPEN']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@placeholder='Username']")));
        driver.findElement(By.xpath("//*[@placeholder='Username']")).sendKeys("cptester424@cp.com");
        driver.findElement(By.xpath("//*[@placeholder='Password']")).clear();
        driver.findElement(By.xpath("//*[@placeholder='Password']")).sendKeys("test1234");
    }

}