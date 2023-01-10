package cn.zknu.mapper;

import cn.zknu.pojo.DoctorInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/*医生信息*/
public interface DoctorInfoMapper {

    //获取全部信息：
    @Select("select * from doctor_info;")
    List<DoctorInfo> selectAll();

    //	获取一条信息：
    @Select("select * from doctor_info where doctorname = #{doctorName};")
    DoctorInfo selectByDoctorName(String doctorName);

    //    添加一条信息：
    @Insert("insert into doctor_info values (null,#{doctorName},#{name},#{sex},#{experience},#{major},#{orderCount});")
    void addDoctorInfo(DoctorInfo doctorInfo);

    //	修改一条信息：
    void updateByDoctorName(DoctorInfo doctorInfo);

    //	删除一条信息：
    @Delete("delete from doctor_info where doctorname = #{doctorName};")
   void deleteByDoctorName(String doctorName);

}
