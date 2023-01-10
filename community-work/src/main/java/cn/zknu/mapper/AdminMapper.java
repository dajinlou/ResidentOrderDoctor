package cn.zknu.mapper;

import cn.zknu.pojo.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AdminMapper {

    // 查询一条信息：
    @Select("select * from tb_admin where adminName=#{adminName};")
    Admin selectByAdminName(String adminName);

    //	验 证 登  录：
    @Select("select * from tb_admin where adminName=#{adminName} and password=#{password}")
    Admin checkLogin(@Param("adminName") String adminName,@Param("password") String password);

    //	添加一条信息：
    //@Insert("insert into tb_admin values (null,#{adminName},#{password});")
    void addAdmin(Admin admin);

    //修改管理员密码
    @Update("update tb_admin set password=#{password} where adminName=#{adminName};")
    void updateByAdminName(@Param("adminName")String adminName,@Param("password")String password);

}
