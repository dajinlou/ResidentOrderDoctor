package cn.zknu.service.impl;

import cn.zknu.mapper.OrderMapper;
import cn.zknu.pojo.Order;
import cn.zknu.service.OrderService;
import cn.zknu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    //1.获取sqlSessionFactory工厂
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<Order> selectAll() {

        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        //4.调用方法
        List<Order> orders = mapper.selectAll();
        //提交事务
//        sqlSession.commit();
        //5.释放资源
        sqlSession.close();

        return orders;
    }

    public List<Order> selectByUsername(String username) {
        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        //4.调用方法
        List<Order> orders = mapper.selectByUsername(username);
        //提交事务
//        sqlSession.commit();
        //5.释放资源
        sqlSession.close();

        return orders;
    }

    public List<Order> selectByDoctorName(String doctorName) {
        //2.获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        //4.调用方法
        List<Order> orders = mapper.selectByDoctorName(doctorName);
        //提交事务
        //5.释放资源
        sqlSession.close();

        return orders;
    }

    public int selectNumByDoctorName(String doctorName) {
        //2.获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        //4.调用方法
        int count = mapper.selectNumByDoctorName(doctorName);
        //5.释放资源
        sqlSession.close();
        return count;
    }

    public int selectCountByDoctorName(String doctorName) {
        //2.获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        //4.调用方法
        int count = mapper.selectCountByDoctorName(doctorName);
        //5.释放资源
        sqlSession.close();
        return count;
    }

    public Order selectByDoctorNameAndUsername(String doctorName, String username) {
        //2.获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        //4.调用方法
        Order order = mapper.selectByDoctorNameAndUsername(doctorName, username);
        //5.释放资源
        sqlSession.close();
        return order;
    }

    public void addOrder(Order order) {
        //2.获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        //4.调用方法
        mapper.addOrder(order);
        //提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
    }

    public void updateByNames(Order order) {
        //2.获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        //4.调用方法
        mapper.updateByNames(order);

        //提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
    }

    public void deleteOrder(String username, String doctorName) {
        //2.获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        //4.调用方法
        mapper.deleteOrder(username, doctorName);
        //提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
    }

//2.获取sqlSession
    //3.获取mapper对象
    //4.调用方法
    //提交事务
    //5.释放资源
}
