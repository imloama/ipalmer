package i.palmer.commons.jdbc;

import org.junit.Test;

public class SQLParserTest {

	
	@Test
	public void test1(){
		String sql = "select id as id,username as username to_date(atime,'yyyy-MM-dd') as atime from table1 where id=2 order by id desc";
		SQLParser parser = new SQLParser(sql);
		System.out.println(parser.getColumns());
	}
	
}
