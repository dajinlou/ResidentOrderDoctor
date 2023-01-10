package cn.zknu.service;

import cn.zknu.pojo.UserInfo;

import java.util.List;

public interface UserInfoService {
    //查询所有用户信息
    List<UserInfo> selectAll();

    //查询一条用户数据
    UserInfo selectByUsername(String username);

    //添加一条用户数据
    void addUserInfo(UserInfo userInfo);

    //修改一条用户信息
    void updateByUsername(UserInfo userInfo);
}
