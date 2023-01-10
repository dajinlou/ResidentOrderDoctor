package cn.zknu.service;

import cn.zknu.pojo.DoctorInfo;

import java.util.List;

public interface DoctorInfoService {

    //获取全部信息：
    List<DoctorInfo> selectAll();

    //	获取一条信息：
    DoctorInfo selectByDoctorName(String doctorName);

    //    添加一条信息：
    void addDoctorInfo(DoctorInfo doctorInfo);

    //	修改一条信息：
    void updateByDoctorName(DoctorInfo doctorInfo);

    //	删除一条信息：
    void deleteByDoctorName(String doctorName);
}
