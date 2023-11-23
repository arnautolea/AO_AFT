package org.myatf.utils;

import org.myatf.enums.Keys;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ScenarioContext {
    private static ScenarioContext instance;
    //declaration of a generic collection
    private final Map<Keys, Object> scenarioContext;
    //ConcurrentHashMap avoid potential concurrency issues.
    public ScenarioContext() {
        scenarioContext = new ConcurrentHashMap<>();
    }
    public static ScenarioContext getInstance() {
        if (instance == null) {
            instance = new ScenarioContext();
        }
        return instance;
    }

    public void saveValueToContext(Keys key, Object value) {
        scenarioContext.put(key, value);
    }

    public Object getValueFromContext(Keys key) {
        return scenarioContext.get(key);
    }

    public void clearContext() {
        scenarioContext.clear();
    }


}