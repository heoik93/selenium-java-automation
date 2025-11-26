package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private Properties properties;

    public ConfigReader() {
        properties = new Properties();
        try {
            InputStream is = getClass().getClassLoader()
                    .getResourceAsStream("config/config.properties");
            if (is != null) {
                properties.load(is);
            } else {
                throw new RuntimeException("config.properties 파일을 찾을 수 없습니다.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("config.properties 로딩 중 오류 발생");
        }
    }

    // key 값으로 properties 읽기
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getUsername() {
        return getProperty("username");
    }

    public String getPassword() {
        return getProperty("password");
    }

    public String getUrl() {
        return getProperty("url");
    }
}
