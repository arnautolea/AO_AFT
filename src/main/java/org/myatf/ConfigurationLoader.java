package org.myatf;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class ConfigurationLoader {
    private static final String configFile = "config.yaml";

    public static Map<String, Object> loadConfig() {
        Yaml yaml = new Yaml();
        InputStream inputStream = ConfigurationLoader.class.getClassLoader().getResourceAsStream(configFile);
        return yaml.load(inputStream);
    }

    public static void main(String[] args) {
        Map<String, Object> config = loadConfig();

        String driverPath = (String) config.get("driverPath");
        String baseUrl = (String) config.get("baseUrl");

        // Use the configuration values in test code
        System.out.println("Driver Path Chrome: " + driverPath);
        System.out.println("Base URL: " + baseUrl);
    }
}