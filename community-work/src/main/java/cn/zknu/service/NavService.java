package cn.zknu.service;

import cn.zknu.pojo.Navigation;

import java.util.List;

public interface NavService {

    //查询用户导航
    List<Navigation> selectAllOfUser();

    //查询医生导航
    List<Navigation> selectAllOfDoctor();

    //查询管理员导航
    List<Navigation> selectAllOfAdmin();
}
