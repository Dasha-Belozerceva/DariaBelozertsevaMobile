package utils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PropertyDataInitializer {
    public static final String PROPERTIES_PATH = "src/test/resources/testData.properties";

    private static Properties propertyData;

    static {
        propertyData = new Properties();
        try {
            InputStream input = new FileInputStream(PROPERTIES_PATH);
            propertyData.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getTestProperty(String keyword) {
        return propertyData.getProperty(keyword);
    }
}
