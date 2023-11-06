package org.myatf.utils;

import org.myatf.enums.Keys;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private static ScenarioContext instance;
    private final Map<Keys, Object> scenarioContext;

    public ScenarioContext() {
        scenarioContext = new HashMap<>();
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