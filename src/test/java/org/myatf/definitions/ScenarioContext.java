package org.myatf.definitions;

import org.myatf.enums.Context;

import java.util.HashMap;
import java.util.Map;

public enum ScenarioContext {
    INSTANCE;

    private final Map<String, Object> scenarioContext;

    ScenarioContext() {
        scenarioContext = new HashMap<>();
    }

    public void setContext(Context key, Object value) {
        scenarioContext.put(key.name(), value);
    }

    public Object getContext(Context key) {
        return scenarioContext.get(key.name());
    }

    public void clearContext() {
        scenarioContext.clear();
    }

}