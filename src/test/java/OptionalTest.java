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
    static void beforeAll(){
        mainPage=MainPage.start();
        optionalPage=mainPage.gotoOptional();
    }

    @ParameterizedTest
    @CsvSource({
            "sogo",
            "alibaba"
    })
    void addOptional(String keyword){
        optionalPage.addOptional(keyword);
        optionalPage.back();
        ArrayList<String> stockName = optionalPage.getStockList();
        System.out.println("添加"+keyword+"后股票名称列表：");
        for (String name:stockName){
            System.out.println(name);
        }
        assertTrue(stockName.contains(keyword));
    }

    @ParameterizedTest()
    @CsvSource({
            "搜狗",
            "阿里巴巴"
    })
    void removeOptional(String keyword){
        optionalPage.removeOptional(keyword);
        optionalPage.back();
        ArrayList<String> stockName = optionalPage.getStockList();
        System.out.println("删除"+keyword+"后股票名称列表：");
        for (String name:stockName){
            System.out.println(name);
        }
        assertTrue(!stockName.contains(keyword));
    }
}
