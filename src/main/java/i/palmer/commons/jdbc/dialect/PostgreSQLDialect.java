package i.palmer.commons.jdbc.dialect;


public class PostgreSQLDialect extends Dialect {

	/**
	 * 生成分页SQL语句
	 */
	@Override
	public String paginate(int pageNumber, int pageSize, String sql) {
		int index = sql.indexOf(";");
		if(index>0){
			sql = sql.substring(0,index);
		}
		StringBuilder sb = new StringBuilder();
		sb.append(sql).append(" limit ").append(pageSize)
			.append(" offset ").append((pageNumber-1)*pageSize);
		return sb.toString();
	}

}
