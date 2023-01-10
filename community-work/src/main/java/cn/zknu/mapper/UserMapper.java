package cn.zknu.mapper;

import cn.zknu.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    //查询所有信息
    @Select("select * from tb_user;")
    List<User> selectAll();

    //   查询一条信息：
    @Select("select * from tb_user where username=#{username};")
    User selectByUsername(String username);

    //验证登录信息
    @Select("select * from tb_user where username=#{username} and password=#{password}")
    User checkLogin(@Param("username")String username,@Param("password") String password);

   //	添加一条信息：
   // @Insert("insert into tb_user values (null,#{username},#{password});")
    void addUser(User user);

    //修改密码
    @Update("update tb_user set password=#{password} where username=#{username};")
    void updateByUsername(@Param("username") String username,@Param("password") String password);

    //删除一条信息
    @Delete("delete from tb_user where username = #{username};")
    void deleteByUsername(String username);

    //删除信息
    void updateUser(User user);
}
