package cn.zknu.service;

import cn.zknu.pojo.Admin;

public interface AdminService {

    // 查询一条信息：
    Admin selectByAdminName(String adminName);

    //	验 证 登  录：
    Admin checkLogin(String adminName,String password);

    //	添加一条信息：
    void addAdmin(Admin admin);

    //修改管理员密码
    void updateByAdminName(String doctorName,String password);
}
