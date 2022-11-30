package at.ac.tuwien.inso.swtesten.lab.context;


import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
	private  Map<String, String> scenarioContext;

	public ScenarioContext(){
		scenarioContext = new HashMap<>();
	}

	public void setContext(Context key, String value) {
		scenarioContext.put(key.toString(), value);
	}

	public String getContext(Context key){
		return scenarioContext.get(key.toString());
	}

	public Boolean contains(Context key){
		return scenarioContext.containsKey(key.toString());
	}
}
