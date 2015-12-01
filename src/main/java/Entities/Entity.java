package Entities;

import org.json.JSONObject;

public abstract class Entity {

	protected JSONObject entity;
	
	public Entity(String resourceType, String id, String json) {
		if(json != "" && json != null) {
			entity = new JSONObject(json);
		} else {
			entity = new JSONObject();
			entity.put("resourceType", resourceType);
			setId(id);
		}
	}
	
	public String getResourceType() {
		return entity.getString("resourceType");
	}
	
	public String getId() {
		return entity.getString("id");
	}
	
	public void setId(String id) {
		if(id != "" && id != null) {
			entity.put("id", id);
		}
	}
	
	public JSONObject getJSONObject() {
		return entity;
	}
}
