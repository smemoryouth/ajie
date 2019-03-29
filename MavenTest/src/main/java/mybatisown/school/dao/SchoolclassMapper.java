package mybatisown.school.dao;

import mybatisown.school.bean.SchoolClass;

public interface SchoolclassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SchoolClass record);

    int insertSelective(SchoolClass record);

    SchoolClass selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SchoolClass record);

    int updateByPrimaryKey(SchoolClass record);

    SchoolClass selectAllByPrimaryKey(Integer id);

    SchoolClass selectAllStep(Integer id);
}