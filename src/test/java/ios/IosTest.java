package ios;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
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
public class IosTest {
    private IOSDriver<IOSElement> driver;
    private WebDriverWait wait;

    public IosTest() {
        driver = (IOSDriver<IOSElement>) Setup.driver;
        wait = new WebDriverWait(driver, 30);
    }

    @Test
    @Order(order = 1)
    public void loginTest() {
        driver.findElement(By.xpath("//*[@text='TestFlight']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='OPEN']")));
        driver.findElement(By.xpath("//*[@text='OPEN']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@placeholder='Username']")));
        driver.findElement(By.xpath("//*[@placeholder='Username']")).sendKeys("cptester424@cp.com");
        driver.findElement(By.xpath("//*[@placeholder='Password']")).clear();
        driver.findElement(By.xpath("//*[@placeholder='Password']")).sendKeys("test1234");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='LOGIN']")));
        driver.findElement(By.xpath("//*[@text='LOGIN']")).click();
    }

    @Test
    @Order(order = 2)
    public void navigationToMyAccountTest() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@text='DashboardView']/*[@class='UIAButton'])[1]")));
        driver.findElement(By.xpath("(//*[@text='DashboardView']/*[@class='UIAButton'])[1]")).click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='My Account']")));
        driver.findElement(By.xpath("//*[@text='My Account']")).click();
    }

}