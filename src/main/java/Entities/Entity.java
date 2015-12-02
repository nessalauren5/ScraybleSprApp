package Entities;

import org.json.JSONObject;

public class Entity {

	private static final String ID = "id";
	private static final String RESOURCE_TYPE = "resourceType";
	protected JSONObject entity;
	
	public Entity(String resourceType, String id, String json) {
		if(json != "" && json != null) {
			entity = new JSONObject(json);
		} else {
			entity = new JSONObject();
			entity.put(RESOURCE_TYPE, resourceType);
			setId(id);
		}
	}
	
	public String getResourceType() {
		return entity.getString(RESOURCE_TYPE);
	}
	
	public String getId() {
		return entity.getString(ID);
	}
	
	public void setId(String id) {
		if(id != "" && id != null) {
			entity.put(ID, id);
		}
	}
	
	public JSONObject getJSONObject() {
		return entity;
	}
}