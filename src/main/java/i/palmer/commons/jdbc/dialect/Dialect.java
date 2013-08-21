package i.palmer.commons.jdbc.dialect;

public abstract class Dialect {

	/**
	 * 返回加上分页标识的SQL语句
	 * @param pageNumber
	 * @param pageSize
	 * @param sql
	 * @return
	 */
	public abstract String paginate(int pageNumber, int pageSize, String sql);
}
