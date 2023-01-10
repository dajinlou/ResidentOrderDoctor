package cn.zknu.mapper;

import cn.zknu.pojo.Navigation;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NavMapper {
    //查询用户导航
    @Select("select * from nav_user")
    @ResultMap("navigationResultMap")
    List<Navigation> selectAllOfUser();

    //查询医生导航
    @Select("select * from nav_doctor")
    @ResultMap("navigationResultMap")
    List<Navigation> selectAllOfDoctor();

    //查询管理员导航
    @Select("select * from nav_admin;")
    @ResultMap("navigationResultMap")
    List<Navigation> selectAllOfAdmin();

}
