import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import page.MainPage;
import page.OptionalPage;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OptionalTest {

    static MainPage mainPage;
    static OptionalPage optionalPage;

    @BeforeAll
    static void beforeAll() throws Exception{
        mainPage=MainPage.start();
        //Driver.getCurrentDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //MainPage.getPermission();
        optionalPage=mainPage.gotoOptional();
    }


    @ParameterizedTest()
    @CsvSource({
            "阿里巴巴",
            "搜狗"
    })
    void removeOptional(String keyword) throws InterruptedException {
        ArrayList<String> stockNameBefore = optionalPage.getStockList();
        System.out.println("删除"+keyword+"前股票名称列表：");
        for (String name:stockNameBefore){
            System.out.println(name);
        }
        optionalPage.removeOptional(keyword);
        optionalPage.back();
        ArrayList<String> stockNameAfter = optionalPage.getStockList();
        System.out.println("删除"+keyword+"后股票名称列表：");
        for (String name:stockNameAfter){
            System.out.println(name);
        }
        assertTrue(!stockNameAfter.contains(keyword));
    }

    @ParameterizedTest
    @CsvSource({
            "搜狗",
            "阿里巴巴"
    })
    void addOptional(String keyword){
        ArrayList<String> stockNameBefore = optionalPage.getStockList();
        System.out.println("添加"+keyword+"前股票名称列表：");
        for (String name:stockNameBefore){
            System.out.println(name);
        }
        optionalPage.addOptional(keyword);
        optionalPage.back();
        ArrayList<String> stockNameAfter = optionalPage.getStockList();
        System.out.println("添加"+keyword+"后股票名称列表：");
        for (String name:stockNameAfter){
            System.out.println(name);
        }
        assertTrue(stockNameAfter.contains(keyword));
    }
}
