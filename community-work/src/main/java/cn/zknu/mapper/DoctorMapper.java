package cn.zknu.mapper;

import cn.zknu.pojo.Doctor;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface DoctorMapper {
    //查询所有
    @Select("select * from tb_doctor;")
    @ResultMap("doctorResultMap")
    List<Doctor> selectAll();

    //   查询一条信息：
    @Select("select * from tb_doctor where doctorname=#{doctorName};")
    Doctor selectByDoctorName(String doctorName);

    //	添加一条信息：
   // @Insert("insert into tb_doctor values (null,#{doctorName},#{password});")
    void addDoctor(Doctor doctor);

    //医生验证登录
    @Select("select * from tb_doctor where doctorname=#{doctorName} and password=#{password}")
    Doctor checkLogin(@Param("doctorName")String doctorName,@Param("password")String password);

    //修改密码
    @Update("update tb_doctor set password=#{password} where doctorname=#{doctorName};")
    void updateByDoctorName(@Param("doctorName") String doctorName,@Param("password") String password);

    //修改医生账号
    void updateDoctor(Doctor doctor);

    //删除一条信息
    @Delete("delete from tb_doctor where doctorname=#{doctorName};")
    void deleteByDoctorName(String doctorName);
}
