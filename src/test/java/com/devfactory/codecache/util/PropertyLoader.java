package com.devfactory.codecache.util;

import java.io.InputStream;
import java.util.Properties;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
class PropertyLoader {

    @SneakyThrows
    static Properties getProperties() {
        InputStream propFile = ClassLoader.getSystemResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(propFile);
        return properties;
    }
}
