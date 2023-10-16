package org.myatf.definitions;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private final Map<String, Object> scenarioContext;

    public ScenarioContext() {
        scenarioContext = new HashMap<>();
    }

    public void setContext(String key, Object value) {
        scenarioContext.put(key, value);
    }

    public Object getContext(String key) {
        return scenarioContext.get(key);
    }

    //public boolean contains(String key) {return scenarioContext.containsKey(key);}

    public void clearContext() {
        scenarioContext.clear();
    }
}
