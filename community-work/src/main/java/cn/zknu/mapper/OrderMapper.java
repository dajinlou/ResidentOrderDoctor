package cn.zknu.mapper;

import cn.zknu.pojo.Order;
import org.apache.ibatis.annotations.*;
import java.util.List;

public interface OrderMapper {

    //查询所有
    @Select("select * from tb_order")
    List<Order> selectAll();

    //根据居民的账户查询
    @Select("select * from tb_order where username = #{username};")
    List<Order> selectByUsername(String username);

    //根据医生的账户进行查询
    @Select("select * from tb_order where doctorname = #{doctorName};")
    List<Order> selectByDoctorName(String doctorName);

    //查询预约数量
    @Select("select count(*) from tb_order where doctorname = #{doctorName};")
    int selectNumByDoctorName(String doctorName);

    //查询当前预约医生的上限
    @Select("select orderCount from doctor_info where doctorname=#{doctorName}")
    int selectCountByDoctorName(String doctorName);

    //查询当前居民是否已经预约该医生
    @Select("select * from tb_order where username=#{username} and doctorname=#{doctorName};")
    Order selectByDoctorNameAndUsername(@Param("doctorName")String doctorName,@Param("username")String username);

    //居民预约医生
    @Insert("insert into tb_order values (null,#{doctorName},#{dName},#{username},#{uName},#{orderTime},#{orderAddress},#{status});")
    void addOrder(Order order);

    //修改预约信息
    void updateByNames(Order order);

    //删除信息
    @Delete("delete from tb_order where username=#{username} and doctorname=#{doctorName};")
    void deleteOrder(@Param("username")String username,@Param("doctorName")String doctorName);

}
