package cn.zknu.mapper;

import cn.zknu.pojo.HealthInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HealthInfoMapper {
    //获取所有
    @Select("select * from health_info;")
    @ResultMap("healthInfoResultMap")
    List<HealthInfo> selectAll();

    //获取一条信息
    @Select("select * from health_info where username = #{username}")
    @ResultMap("healthInfoResultMap")
    HealthInfo selectByUsername(String username);

    //添加一条信息
    @Insert("insert into health_info values (null,#{username},#{name},#{height},#{weight},#{bloodPressure},#{heartRate},#{allergyHistory},#{description});")
    void addHealthInfo(HealthInfo healthInfo);

    //修改一条信息
    void updateByUsername(HealthInfo healthInfo);

    //删除一条信息
    @Delete("delete from health_info where username = #{username};")
    void deleteByUsername(String username);

}

