package cn.zknu.service;

import cn.zknu.pojo.Order;

import java.util.List;

public interface OrderService {

    //查询所有
    List<Order> selectAll();

    //居民端查询用户信息
    List<Order> selectByUsername(String username);

    //医生端查询预约信息
    List<Order> selectByDoctorName(String doctorName);

    //查询当前预约该医生的条数
    int selectNumByDoctorName(String doctorName);

    //查询预约医生上限
    int selectCountByDoctorName(String doctorName);

    //查询是否当前医生已经预约过
    Order selectByDoctorNameAndUsername(String doctorName,String username);

    //居民预约医生
    void addOrder(Order order);

    //修改预约信息
    void updateByNames(Order order);

    //删除预约消息
    void deleteOrder(String username,String doctorName);
}
