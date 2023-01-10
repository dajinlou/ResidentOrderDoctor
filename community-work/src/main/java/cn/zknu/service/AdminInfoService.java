package cn.zknu.service;

import cn.zknu.pojo.AdminInfo;

public interface AdminInfoService {

    // 获取一条信息：
    AdminInfo selectByAdminName(String adminName);

    //	添加一条信息：
    void addAdminInfo(AdminInfo adminInfo);

    //	修改一条信息：
    void updateByAdminName(AdminInfo adminInfo);

}
