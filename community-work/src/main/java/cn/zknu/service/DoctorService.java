package cn.zknu.service;

import cn.zknu.pojo.Doctor;

import java.util.List;

public interface DoctorService {

    //查询所有
    List<Doctor> selectAll();

    //    查询一条信息：
    Doctor selectByDoctorName(String doctorName);

    //验证登录
    Doctor checkLogin(String doctorName,String password);

    //	添加一条信息：
    void addDoctor(Doctor doctor);

    //修改密码
    void updateByDoctorName(String doctorName,String password);

    //管理员修改医生账号信息
    void updateDoctor(Doctor doctor);

    //删除一条信息
    void deleteByDoctorName(String doctorName);
}
