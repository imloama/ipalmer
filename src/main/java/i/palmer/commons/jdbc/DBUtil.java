package i.palmer.commons.jdbc;

import i.palmer.commons.jdbc.dialect.Dialect;
import i.palmer.commons.jdbc.dialect.PostgreSQLDialect;

import javax.annotation.Resource;
import javax.sql.DataSource;

public final class DBUtil {
	
	@Resource
	private static DataSource dataSource;
	
	static Dialect dialce = new PostgreSQLDialect();
	
	static{
		//String driverClass = dataSource.getConnection().
	}
}
