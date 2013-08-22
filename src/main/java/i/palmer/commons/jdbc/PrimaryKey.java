package i.palmer.commons.jdbc;

import i.palmer.commons.jdbc.annotation.GenerationType;

public class PrimaryKey {

	private String name;
	
	private GenerationType generationType;
	
	private String type;
	
	public GenerationType getGenerationType() {
		return generationType;
	}
	public void setGenerationType(GenerationType generationType) {
		this.generationType = generationType;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
