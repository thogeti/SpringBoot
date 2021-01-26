package ca.sdm.cdr.common.singleton.bean;

public class ReferenceTableBean {

	String tableName;
	String keyColumnName;
	String codeColumnName;
	boolean isUpdatable;

	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getKeyColumnName() {
		return keyColumnName;
	}
	public void setKeyColumnName(String keyColumnName) {
		this.keyColumnName = keyColumnName;
	}
	public String getCodeColumnName() {
		return codeColumnName;
	}
	public void setCodeColumnName(String codeColumnName) {
		this.codeColumnName = codeColumnName;
	}
	public boolean isUpdatable() {
		return isUpdatable;
	}
	public void setUpdatable(boolean isUpdatable) {
		this.isUpdatable = isUpdatable;
	}
	
	
}
