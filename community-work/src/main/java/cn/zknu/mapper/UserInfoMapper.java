package cn.zknu.mapper;

import cn.zknu.pojo.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserInfoMapper {
    //查询全部
    @Select("select * from user_info;")
    List<UserInfo> selectAll();

    //查询一条用户数据
    @Select("SELECT * FROM user_info WHERE username = #{username};")
    UserInfo selectByUsername(String username);

    //添加一条用户信息
    @Insert("INSERT into user_info values (null,#{username},#{name},#{sex},#{birthday},#{num},#{address},#{description})")
    void addUserInfo(UserInfo userInfo);

    //修改一条用户信息
    void updateByUsername(UserInfo userInfo);

}
