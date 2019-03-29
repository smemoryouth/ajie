package jdbc.dao;

/**
 * description：employee表的dao操作
 *
 * @author ajie
 * data 2018/8/13 11:51
 */
public interface EmployeeDao {
    /**
     * 增加员工
     * @param employee 员工
     */
    void addEmployee(Employee employee);

    /**
     * 通过工号获取员工
     * @param deptno 工号
     * @return 员工
     */
    Employee getEmployee(int deptno);

    /**
     * 通过登录信息获取员工
     * @param loginName 注册名
     * @param password 密码
     * @return
     */
    Employee findEmployee(String loginName, String password);

    /**
     * 更新员工信息
     * @param employee 员工
     */
    void update(Employee employee);

    /**
     * 删除员工信息
     * @param employee 员工
     */
    void delete(Employee employee);
}
