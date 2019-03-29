package mybatisown.school;

import mybatisown.school.bean.SchoolClass;
import mybatisown.school.bean.Student;
import mybatisown.school.dao.SchoolclassMapper;
import mybatisown.school.dao.StudentMapper;
import mybatisown.school.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * description：
 *
 * @author ajie
 * data 2018/11/10 18:41
 */
public class SchoolTest {
    @Test
    public void studentMapperTest(){
        try {
            SqlSession session = MyBatisUtils.getSession();
            StudentMapper studentMapper = session.getMapper(StudentMapper.class);
            List<Student> list = studentMapper.selectAllStudentStep();
            for (Student student : list){
                System.out.println(student);
            }
//            Student student = studentMapper.selectByPrimaryKey(1);
//            System.out.println(student);
//            session.close();
            System.out.println("==================");
//session.clearCache();
//            SqlSession session2 = MyBatisUtils.getSession();
//            StudentMapper studentMapper2 = session2.getMapper(StudentMapper.class);
//            Student student1 = studentMapper2.selectByPrimaryKey(1);
//            System.out.println(student1);
//            session2.close();
//            System.out.println("==================");
//
//            SqlSession session3 = MyBatisUtils.getSession();
//            StudentMapper studentMapper3 = session3.getMapper(StudentMapper.class);
//            Student student2 = studentMapper3.selectByPrimaryKey(1);
//            System.out.println(student2);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void classMapperTest(){
        try (SqlSession session = MyBatisUtils.getSession()) {
            SchoolclassMapper schoolclassMapper = session.getMapper(SchoolclassMapper.class);
            SchoolClass schoolclass = schoolclassMapper.selectAllStep(1);
            System.out.println(schoolclass);
            System.out.println(schoolclass.getStudentList());
        }
    }
}
