package i.palmer.commons.jdbc;

import i.palmer.commons.jdbc.annotation.GenerationType;
import i.palmer.commons.jdbc.annotation.PrimaryKey;
import i.palmer.commons.jdbc.annotation.Table;

@Table(name="xt_user",keys={@PrimaryKey(name="pid",generationType=GenerationType.specified,type="String")})
public class UserTest extends Model<UserTest>{

	private static final long serialVersionUID = -8009468965481856974L;

}
