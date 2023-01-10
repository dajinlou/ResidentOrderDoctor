package cn.zknu.service.impl;

import cn.zknu.mapper.UserMapper;
import cn.zknu.pojo.User;
import cn.zknu.service.UserService;
import cn.zknu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserServiceImpl implements UserService {
    //1.获取SqlSessionFactory
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<User> selectAll() {
        //2.获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4.调用方法
        List<User> users = mapper.selectAll();
        //5.释放资源
        sqlSession.close();
        return users;
    }

    public User selectByUsername(String username) {
        //2.获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4.调用方法
        User user = mapper.selectByUsername(username);
        //5.释放资源
        sqlSession.close();
        return user;
    }

    public User checkLogin(String username, String password) {
        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4.调用方法
        User user = mapper.checkLogin(username, password);
        //5.释放资源
        sqlSession.close();

        return user;
    }

    public void addUser(User user) {
        //2.获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4.调用方法
        mapper.addUser(user);
        //提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
    }

    public void updateByUsername(String username, String password) {
        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4.调用方法
        mapper.updateByUsername(username, password);
        //提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
    }

    public void updateUser(User user) {
        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //调用方法
        mapper.updateUser(user);
        //提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
    }

    public void deleteByUsername(String username) {
        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4.调用方法
        mapper.deleteByUsername(username);
        //提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
    }


    //2.获取sqlSession对象
    //3.获取mapper对象
    //4.调用方法
    //5.释放资源
}
