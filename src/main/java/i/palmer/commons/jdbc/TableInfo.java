package i.palmer.commons.jdbc;

import java.util.ArrayList;
import java.util.List;

public class TableInfo {

	//表名
	private String name;
	
	private List<PrimaryKey> keys;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<PrimaryKey> getKeys() {
		return keys;
	}
	
	public void setKeys(List<PrimaryKey> keys) {
		this.keys = keys;
	}
	
	public void addKey(PrimaryKey key){
		if(keys==null)
			keys = new ArrayList<PrimaryKey>();
		keys.add(key);
	}
	
	
}
