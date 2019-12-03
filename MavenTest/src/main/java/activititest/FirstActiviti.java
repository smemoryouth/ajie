package activititest;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

/**
 * description：
 *
 * @author 阿劼
 * data 2019/11/4 9:37
 */
public class FirstActiviti {
    @Test
    public void createDatabaseTest() {
        //    创建一个流程引擎配置对象
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();

        //    设置数据源信息
        configuration.setJdbcDriver("com.mysql.jdbc.Driver");
        configuration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti?useSSL=false");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("wl968640");

        //    设置自动建表
        configuration.setDatabaseSchemaUpdate("true");

        //  创建一个流程引擎对象，在创建流程引擎对象中会自动建表
        ProcessEngine processEngine = configuration.buildProcessEngine();
    }

}
