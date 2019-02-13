package driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static AndroidDriver<AndroidElement> driver;
    public static void start(){

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        GlobalConfig config = GlobalConfig.load("/data/globalConfig.yaml");
        config.appium.capabilities.keySet().forEach(Key->{
            Object value = config.appium.capabilities.get(Key);
            desiredCapabilities.setCapability(Key, value);
        });

        /*desiredCapabilities.setCapability("platformName", "android");
        //xiaomi8
        //desiredCapabilities.setCapability("deviceName", "14375200");
        //Pixel
        desiredCapabilities.setCapability("deviceName", "emulator-5554");
        desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");
        //获取权限
        desiredCapabilities.setCapability("autoGrantPermissions", true);
        //设置输入法
        desiredCapabilities.setCapability("unicodeKeyboard", true);
        //是否重置键盘
        desiredCapabilities.setCapability("resetKeyboard", true);*/

        URL remoteUrl = null;
        try {
            remoteUrl = new URL(config.appium.url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(config.appium.wait, TimeUnit.SECONDS);
    }

    public static AndroidDriver<AndroidElement> getCurrentDriver(){
        return driver;
    }

}
