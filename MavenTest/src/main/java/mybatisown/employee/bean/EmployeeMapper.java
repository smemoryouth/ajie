package mybatisown.employee.bean;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 高级语言中，如果嵌入了SQL语句，而这个SQL语句的主体结构已经明确，
 * 例如在Java的一段代码中有一个待执行的SQL“select * from t1 where c1>5”，
 * 在Java编译阶段，就可以将这段SQL交给数据库管理系统去分析，数据库软件可以对这段SQL进行语法解析，
 * 生成数据库方面的可执行代码，这样的SQL称为静态SQL，即在编译阶段就可以确定数据库要做什么事情。
 * 而如果嵌入的SQL没有明确给出，如在Java中定义了一个字符串类型的变量sql：String sql;，
 * 然后采用preparedStatement对象的execute方法去执行这个sql，
 * 该sql的值可能等于从文本框中读取的一个SQL或者从键盘输入的SQL，但具体是什么，在编译时无法确定，
 * 只有等到程序运行起来，在执行的过程中才能确定，这种SQL叫做动态SQL。
 * 例如每一种数据库软件都有能够执行SQL语句的界面，那个界面接收的SQL就是动态SQL，
 * 因为数据库厂商在做这个界面时，并不知道用户会输入哪些SQL，只有在该界面执行后，
 * 接收了用户的实际输入，才知道SQL是什么。
 * 另外还要注意一点，在SQL中如果某些参数没有确定，
 * 如"select * from t1 where c1>? and c2<?"，
 * 这种语句是静态SQL，不是动态SQL，虽然个别参数的值不知道，
 * 但整个SQL的结构已经确定，数据库是可以将它编译的，在执行阶段只需将个别参数的值补充进来即可
 * @author 阿劼
 */
@Mapper
public interface EmployeeMapper {

    int insert(@Param("employee") Employee employee);

    int insertSelective(@Param("employee") Employee employee);

    int insertList(@Param("employees") List<Employee> employees);

    int updateByPrimaryKeySelective(@Param("employee") Employee employee);
}
