package org.myatf.definitions;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private static ScenarioContext instance;

    private Map<String, Boolean> scenarioResults;

    private ScenarioContext() {
        scenarioResults = new HashMap<>();
    }
    public static ScenarioContext getInstance() {
        if (instance == null) {
            instance = new ScenarioContext();
        }
        return instance;
    }
    public void setScenarioResults(String scenarioName, boolean isSuccess) {
        scenarioResults.put(scenarioName, isSuccess);
    }

    public Map<String, Boolean> getScenarioResults() {
        return scenarioResults;
    }

}
