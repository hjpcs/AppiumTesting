import driver.GlobalConfig;
import driver.SearchConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import page.MainPage;
import page.SearchPage;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class SearchTest {

    static MainPage mainPage;
    static SearchPage searchPage;

    @BeforeAll
    static void beforeAll(){
        mainPage=MainPage.start();
        searchPage=mainPage.gotoSearch();
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/data/search.csv")
    void 搜索测试(String keyword, String name){
        String content=searchPage.search(keyword).getAll().get(0);
        assertThat(content, equalTo(name));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data/select.csv")
    void 选择(String keyword){
        ArrayList<String> array=searchPage.search(keyword).addSelected();
        assertThat(array, hasItems("com.xueqiu.android:id/followed_btn", "com.xueqiu.android:id/follow_btn"));
    }

    private static Stream<Arguments> readYaml() {
        SearchConfig searchConfig = new SearchConfig();
        GlobalConfig config = GlobalConfig.load("/data/globalConfig.yaml");
        config.search.data.keySet().forEach(Key->{
            String value = config.search.data.get(Key).toString();
            searchConfig.setKeyword(Key);
            searchConfig.setName(value);
        });
        return Stream.of(arguments(searchConfig.getKeyword(),searchConfig.getName()));
    }

    @ParameterizedTest
    @MethodSource("readYaml")
    void searchTest(String keyword, String name){
        String content=searchPage.search(keyword).getAll().get(0);
        assertThat(content, equalTo(name));
    }
}
