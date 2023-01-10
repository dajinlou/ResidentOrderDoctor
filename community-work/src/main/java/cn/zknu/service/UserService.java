package cn.zknu.service;

import cn.zknu.pojo.User;

import java.util.List;

public interface UserService {
    //查询所有
    List<User> selectAll();

    //    查询一条信息：
    User selectByUsername(String username);

    //验证登录
    User checkLogin(String username,String password);

    //	添加一条信息：
    void addUser(User user);

    //修改密码
    void updateByUsername(String username,String password);

    //管理员修改账号和密码
    void updateUser(User user);

    //删除一条信息
    void deleteByUsername(String username);
}
