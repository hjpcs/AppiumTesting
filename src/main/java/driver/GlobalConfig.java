package driver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;

public class GlobalConfig {

    public AppiumConfig appium;
    public XueqiuConfig xueqiu;
    public SearchConfig search;

    public static GlobalConfig load(String path){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            GlobalConfig globalConfig = mapper.readValue(GlobalConfig.class.getResource(path), GlobalConfig.class);
            return globalConfig;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
