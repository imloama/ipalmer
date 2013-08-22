package i.palmer.commons.jdbc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)// 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Target(ElementType.TYPE)   //接口、类、枚举、注解
public @interface PrimaryKey {
	//名称
	public String name()default"";
	//是自动还是手段，手动的话，需要先设定值保存到attributes中
	public GenerationType generationType();
	//字段类型 可以输入string int 等
	public String type();
	
	
}
