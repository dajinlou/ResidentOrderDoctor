package cn.zknu.mapper;

import cn.zknu.pojo.AdminInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface AdminInfoMapper {

    // 获取一条信息：
    @Select("select * from admin_info where adminName=#{adminName}")
    AdminInfo selectByAdminName(String adminName);

    //	添加一条信息：
    @Insert("insert into admin_info values (null,#{adminName},#{name},#{tel});")
    void addAdminInfo(AdminInfo adminInfo);

    //	修改一条信息：
    void updateByAdminName(AdminInfo adminInfo);
}
