package cn.zknu.service;

import cn.zknu.pojo.HealthInfo;

import java.util.List;

public interface HealthInfoService {

    //查询所有
    List<HealthInfo> selectAll();

    //查询一条
    HealthInfo selectByUsername(String username);

    //添加一条信息
    void addHealthInfo(HealthInfo healthInfo);

    //修改一条信息
    void updateByUsername(HealthInfo healthInfo);

    //删除一条信息
    void deleteByUsername(String username);
}
