package i.palmer.commons.jdbc;

import i.palmer.commons.jdbc.annotation.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 通用实体类 新建的实体类，要实现该抽象类，并且提供两个注解， 一个是@Table 用来表明在数据库中的表名 一个是@keys{@key }
 * 包括Key的生成策略 支持自动/手动
 * 
 * @author itwarcraft@gmail.com
 */
@SuppressWarnings("rawtypes")
public abstract class Model<M extends Model> implements Serializable {

	private static final long serialVersionUID = 7112785924701363736L;

	protected final static Logger logger = LoggerFactory.getLogger(Model.class);

	protected Map<String, Object> attributes = new HashMap<String, Object>();
	protected Set<String> modifyAttributes = null;

	// protected MetaModel metaModel = new MetaModel();

	protected TableInfo table = null;

	@Resource
	protected JdbcTemplate jdbcTemplate;

	public Model(){
		initTableInfo();
	}
	
	
	protected Map<String, Object> getAttributes() {
		return attributes;
	}

	protected Model setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
		return this;
	}

	@SuppressWarnings("unchecked")
	protected M set(String attribute, Object value) {
		attributes.put(attribute, value);
		getModifyAttributes().add(attribute);
		return (M) this;
	}

	@SuppressWarnings("unchecked")
	public Page<M> paginate(int pageNumber, int pageSize, String sql,
			Object... args) throws InstantiationException,
			IllegalAccessException {

		if (pageNumber < 1 || pageSize < 1)
			throw new ModelException("页码和记录数总数不能小于0");

		long totalRow = 0;
		int totalPage = 0;

		// 解析SQL语句,添加分页语句
		// SQLParser parser = new SQLParser(sql);

		String sql_count = "select count(*) as num from (" + sql
				+ ") table_count";
		Map<String, Object> r = jdbcTemplate.queryForMap(sql_count, args);
		int size = (Integer) r.get("num");

		if (size == 0) {
			return new Page<M>(new ArrayList<M>(0), pageNumber, pageSize, 0, 0);
		}
		// 总行数
		totalRow = size;

		totalPage = (int) (totalRow / pageSize);
		if (totalRow % pageSize != 0) {
			totalPage++;
		}

		String sql2 = DBUtil.dialce.paginate(pageNumber, pageSize, sql);
		List<Map<String, Object>> rs = jdbcTemplate.queryForList(sql2, args);
		// int size = result.size();
		// if (size == 1)
		Class<? extends Model> modelClass = getClass();
		List<M> result = new ArrayList<M>();

		for (Map<String, Object> map : rs) {
			Model<?> m = modelClass.newInstance();
			m.setAttributes(map);
			result.add((M) m);
		}

		return new Page<M>(result, pageNumber, pageSize, (int) totalRow,
				totalPage);
	}

	// save it self
	public boolean save() {
		
		

		return false;
	}

	public boolean update() {
		
		
		return false;
	}

	//实例化类的时候用到
	public void initTableInfo() {
		if (table == null) {
			table = new TableInfo();

			// 表名 字段类型
			Class<? extends Model> modelClass = getClass();
			Table t = modelClass.getAnnotation(Table.class);
			table.setName(t.name());

			for (int i = 0, n = t.keys().length; i < n; i++) {
				PrimaryKey key = new PrimaryKey();
				key.setName(t.keys()[i].name());
				key.setGenerationType(t.keys()[i].generationType());
				key.setType(t.keys()[i].type());
				table.addKey(key);
			}
		}

	}

	/**
	 * Get attribute of any mysql type
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(String attr) {
		return (T) (attributes.get(attr));
	}

	/**
	 * Get attribute of any mysql type. Returns defaultValue if null.
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(String attr, Object defaultValue) {
		Object result = attributes.get(attr);
		return (T) (result != null ? result : defaultValue);
	}

	/**
	 * Get attribute of mysql type: varchar, char, enum, set, text, tinytext,
	 * mediumtext, longtext
	 */
	public String getStr(String attr) {
		return (String) attributes.get(attr);
	}

	/**
	 * Get attribute of mysql type: int, integer, tinyint(n) n > 1, smallint,
	 * mediumint
	 */
	public Integer getInt(String attr) {
		return (Integer) attributes.get(attr);
	}

	/**
	 * Get attribute of mysql type: bigint
	 */
	public Long getLong(String attr) {
		return (Long) attributes.get(attr);
	}

	// java.util.Data never returned
	// public java.util.Date getDate(String attr) {
	// return attrs.get(attr);
	// }

	/**
	 * Get attribute of mysql type: date, year
	 */
	public java.sql.Date getDate(String attr) {
		return (java.sql.Date) attributes.get(attr);
	}

	/**
	 * Get attribute of mysql type: time
	 */
	public java.sql.Time getTime(String attr) {
		return (java.sql.Time) attributes.get(attr);
	}

	/**
	 * Get attribute of mysql type: timestamp, datetime
	 */
	public java.sql.Timestamp getTimestamp(String attr) {
		return (java.sql.Timestamp) attributes.get(attr);
	}

	/**
	 * Get attribute of mysql type: real, double
	 */
	public Double getDouble(String attr) {
		return (Double) attributes.get(attr);
	}

	/**
	 * Get attribute of mysql type: float
	 */
	public Float getFloat(String attr) {
		return (Float) attributes.get(attr);
	}

	/**
	 * Get attribute of mysql type: bit, tinyint(1)
	 */
	public Boolean getBoolean(String attr) {
		return (Boolean) attributes.get(attr);
	}

	/**
	 * Get attribute of mysql type: decimal, numeric
	 */
	public java.math.BigDecimal getBigDecimal(String attr) {
		return (java.math.BigDecimal) attributes.get(attr);
	}

	/**
	 * Get attribute of mysql type: binary, varbinary, tinyblob, blob,
	 * mediumblob, longblob
	 */
	public byte[] getBytes(String attr) {
		return (byte[]) attributes.get(attr);
	}

	/**
	 * Get attribute of any type that extends from Number
	 */
	public Number getNumber(String attr) {
		return (Number) attributes.get(attr);
	}

	/**
	 * Put key value pair to the model when the key is not attribute of the
	 * model.
	 */
	@SuppressWarnings("unchecked")
	public M put(String key, Object value) {
		attributes.put(key, value);
		return (M) this;
	}

	public Set<String> getModifyAttributes() {
		if (modifyAttributes == null)
			modifyAttributes = new HashSet<String>();
		return modifyAttributes;
	}

}
