package driver;

import java.util.HashMap;

public class SearchConfig {

    public String keyword = "";
    public String name= "";
    public HashMap<String, Object> data = new HashMap<String, Object>();

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
