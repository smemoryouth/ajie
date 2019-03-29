package jdbc.classtest.dao;

/**
 * description：user DAO层,功能定义
 *
 * @author ajie
 * data 2018/10/23 10:53
 */
public interface UserDao {

    /**
     * 创建表
     */
    int createTable();

    /**
     * 插入user信息
     * @param user
     */
    void addUser(User user);

    /**
     * 根据id查找user信息
     * @param id
     * @return
     */
    User getUser(int id);
}
