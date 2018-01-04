package setup;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Setup extends EventFiringWebDriver {

    public static MobileDriver driver = null;
    private static final Thread closeTread = new Thread(() -> driver.quit());
    private static String reportDirectory = null;
    private static DeviceEnum deviceName = DeviceEnum.valueOf(System.getProperty("deviceName").toUpperCase());
    private static DesiredCapabilities dc = new DesiredCapabilities();

    static {
        String reportFormat = "xml";
        try {
            reportDirectory = new File(".").getCanonicalPath() + "\\reports";
            FileUtils.deleteDirectory(new File(reportDirectory));
        } catch (IOException e) {
            e.printStackTrace();
        }
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", "TestName");
        dc.setCapability(MobileCapabilityType.NO_RESET, true);
        try {
            switch (deviceName) {
                case IPHONE6:
                    dc.setCapability(MobileCapabilityType.UDID, "0afc0579b3564edb76c72fda3997d2997d632969");
                    driver = new IOSDriver<IOSElement>(new URL("http://localhost:4723/wd/hub"), dc);
                    break;
                case IPHONE5:
                    dc.setCapability(MobileCapabilityType.UDID, "0b000860612e57f9f502cd729fdcbc06c1055cf4");
                    driver = new IOSDriver<IOSElement>(new URL("http://localhost:4723/wd/hub"), dc);
                    break;
                case ANDROID:
                    dc.setCapability(MobileCapabilityType.UDID, "TA46309TSD");
                    dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.alarm.alarmmobile.android.frontpoint");
                    dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.alarm.alarmmobile.android.feature.auth.login.LoginActivity");
                    driver = new AndroidDriver<AndroidElement>(new URL("http://localhost:4723/wd/hub"), dc);
                    break;
                default:
                    System.out.println("Device name not supported");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Runtime.getRuntime().addShutdownHook(closeTread);
    }

    public Setup() {
        super(driver);
    }

    @Override
    public void close() {
        if (Thread.currentThread() != closeTread) {
            throw new UnsupportedOperationException("Driver will shutdown");
        }
        super.close();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
