package i.palmer.commons.jdbc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SQL语句解析方法，使用正则表达式，将SQL语句的各部分梳理出来，并进行重新组合
 * @author Drama
 *
 */
public class SQLParser {

	/**
	 * 要解析的SQL语句
	 */
	private String _sqlStr;
	/**
	 * SQL语句中选择的列
	 */
	private String columns;
	/**
	 * SQL语句中查找的表
	 */
	private String tables;
	/**
	 * 查询条件
	 */
	private String conditions;
	/**
	 * group by 条件
	 */
	private String groupColumns;
	/**
	 * order by 条件
	 */
	private String orderColumns;
	
	/**
	 * 构造函数，注入SQL语句
	 * @param sqlStr
	 */
	public SQLParser(String sqlStr){
		this._sqlStr = sqlStr;
		//解析SQL语句
		parseTables();
		parseColumns();
		parseConditions();
		parseGroupColumns();
		parseOrderColumns();
	}

	/**
	 * 解析选择的表
	 */
	private void parseTables(){
		String regex = "";
		//判断SQL语句中是否包括where条件
		if(isContains(_sqlStr, "\\s+where\\s+")){
			regex="(from)(.+)(where)";
		}else{
			regex="(from)(.+)($)";
		}
		tables = getMatchedString(regex, _sqlStr);//将解析得到的表存入属性中
	}
	
	/**
	 * 解析查询的字段有哪些
	 */
	private void parseColumns(){
		String regex = "(select)(.+)(from)";
		columns = getMatchedString(regex, _sqlStr);
	}
	
	/**
	 * 解析where条件
	 */
	private void parseConditions(){
		  String regex="";   
		    if(isContains(_sqlStr,"\\s+where\\s+")){
		      // 包括Where，有条件
		      if(isContains(_sqlStr,"group\\s+by")){
		        // 条件在where和group by之间
		        regex="(where)(.+)(group\\s+by)";  
		      }else if(isContains(_sqlStr,"order\\s+by")){
		        // 条件在where和order by之间
		        regex="(where)(.+)(order\\s+by)";  
		      }else{
		        // 条件在where到字符串末尾
		        regex="(where)(.+)($)";  
		      }        
		    }
		    else{
		      // 不包括where则条件无从谈起，返回即可
		      return;
		    }
		    conditions=getMatchedString(regex,_sqlStr);
	}
	/**
	 * 解析group by 字段
	 */
	private void parseGroupColumns(){
	    String regex="";   
	    if(isContains(_sqlStr,"group\\s+by")){
	      // 包括GroupBy，有分组字段
	      if(isContains(_sqlStr,"order\\s+by")){
	        // group by 后有order by
	        regex="(group\\s+by)(.+)(order\\s+by)";  
	      }else{
	        // group by 后无order by
	        regex="(group\\s+by)(.+)($)";  
	      }      
	    }
	    else{
	      // 不包括GroupBy则分组字段无从谈起，返回即可
	      return;
	    }
	    groupColumns=getMatchedString(regex,_sqlStr);
	}
	/**
	 * 解析order by 条件
	 */
	private void parseOrderColumns(){
	    String regex="";   
	    if(isContains(_sqlStr,"order\\s+by")){
	      // 包括order by，有分组字段
	      regex="(order\\s+by)(.+)($)";                  
	    }
	    else{
	      // 不包括GroupBy则分组字段无从谈起，返回即可
	      return;
	    }
	    orderColumns=getMatchedString(regex,_sqlStr);
	}
	
	/**
	 * 从文本text中找到regex首次匹配的字符串，不区分大小写
	 * @param regex： 正则表达式
	 * @param text：欲查找的字符串
	 * @return regex首次匹配的字符串，如未匹配返回空
	 */
	public String getMatchedString(String regex,String text){
		Pattern p = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(text);
		while(m.find()){
			return m.group(2);
		}
		return null;
	}
	/**
	 * 查看word是否在lineText中存在，支持正则表达式
	 * @param lineText
	 * @param word
	 * @return
	 */
	private boolean isContains(String lineText,String word){
		Pattern p = Pattern.compile(word,Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(lineText);
		return m.find();
	}
	
	/**
	 * 返回新的重新组织的SQL语句
	 * @return
	 */
	public String toNewSqlStatement(){
		StringBuffer sb = new StringBuffer();
		sb.append("select ").append(columns)
			.append(" from ").append(tables)
			.append(" where ").append(conditions)
			.append(" group by ").append(this.groupColumns)
			.append(" order by ").append(this.orderColumns);
		return sb.toString();
	}
	
	public String getSqlStr() {
		return _sqlStr;
	}
	/*
	public void setSqlStr(String sqlStr) {
		this._sqlStr = sqlStr;
	}*/
	public String getColumns() {
		return columns;
	}
	public void setColumns(String columns) {
		this.columns = columns;
	}
	public String getTables() {
		return tables;
	}
	public void setTalbes(String tables) {
		this.tables = tables;
	}
	public String getConditions() {
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
	public String getGroupColumns() {
		return groupColumns;
	}
	public void setGroupColumns(String groupColumns) {
		this.groupColumns = groupColumns;
	}
	public String getOrderColumns() {
		return orderColumns;
	}
	public void setOrderColumns(String orderColumns) {
		this.orderColumns = orderColumns;
	}
	
	
	
	public static void main(String[] args){
		/*
		SQLParser sp = new SQLParser("select a,b from t where a=1 and b=2 group by a order by b");
		System.out.println("tables:\t"+sp.tables);
		System.out.println("columns:\t"+sp.columns);
		System.out.println("where:\t"+sp.conditions);
		System.out.println("group by :\t"+sp.groupColumns);
		System.out.println("order by :\t"+sp.orderColumns);
		*/
		String r = "(to_char)(\\(.+\\))(.+as)";
		String st2 = "select to_char(yysj, 'yyyy-MM-dd')  as yysj,  p_id  as p_id,    emp  as emp,  lxtel  as lxtel,  to_char(ycsj, 'yyyy-MM-dd  HH:mm')  as ycsj,   stopp  as stopp,  trim(to_char(ycrs, 'fm999999.00'))  as ycrs from tabl where a=1 and b=2 group by a order by b";
		SQLParser sp2 = new SQLParser("");
		String s = sp2.getMatchedString(r, "to_char(yysj, 'yyyy-MM-dd')  as yysj,  p_id  as p_id,    emp  as emp,  lxtel  as lxtel");
		System.out.println(s);
		System.out.println(sp2.getMatchedString(r, st2));
	}
	
}
