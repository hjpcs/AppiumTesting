package page;

import driver.Driver;
import org.openqa.selenium.By;

public class MainPage extends BasePage {
    By profile=By.id("user_profile_icon");

    public static MainPage start(){
        Driver.start();
        return new MainPage();
    }

    public static void getPermission(){
        Driver.getCurrentDriver().findElement(By.xpath("com.xueqiu.android:id/open")).click();
        Driver.getCurrentDriver().findElement(By.xpath("com.android.packageinstaller:id/permission_allow_button")).click();
        Driver.getCurrentDriver().findElement(By.xpath("com.android.packageinstaller:id/permission_allow_button")).click();
        Driver.getCurrentDriver().findElement(By.xpath("com.xueqiu.android:id/agree")).click();
        Driver.getCurrentDriver().findElement(By.xpath("com.android.packageinstaller:id/permission_allow_button")).click();
    }

    public ProfilePage gotoProfile(){
        Driver.getCurrentDriver().findElement(profile).click();
        return new ProfilePage();

    }

    public SearchPage gotoSearch(){
        find(By.id("home_search")).click();
        return new SearchPage();
    }

    // 进入自选页面
    public OptionalPage gotoOptional() throws Exception{
        Thread.sleep(5000);
        Driver.getCurrentDriver().findElement(By.xpath("//android.widget.TextView[@text='自选']")).click();
        return new OptionalPage();
    }
}
