package application;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class loadMap implements Serializable {
	Map<String,String> hashmap =new HashMap<String,String>();
	loadMap(){
		hashmap.put("first", "<DIV><DIV>");
		hashmap.put("second", "<p><header>");	
		
	}

protected void addProj(String name, String project){
	hashmap.put(name, project);
	
	
}

protected String getProj(String name){
	String project = hashmap.get(name);
	
	return project;
}

protected void deleteProj(String name){
	hashmap.remove(name);
	
	
}

protected Map<String, String> getHashMap(){
	return hashmap;
}

}
