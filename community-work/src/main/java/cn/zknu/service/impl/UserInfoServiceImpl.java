package cn.zknu.service.impl;

import cn.zknu.mapper.UserInfoMapper;
import cn.zknu.pojo.UserInfo;
import cn.zknu.service.UserInfoService;
import cn.zknu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserInfoServiceImpl implements UserInfoService {
    //1.创建SqlSessionFactory工厂
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<UserInfo> selectAll() {
        //2.获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);
        //4.调用方法
        List<UserInfo> userInfos = mapper.selectAll();
        //5.释放资源
        sqlSession.close();

        return userInfos;
    }

    //根据用户名获取一条数据
    public UserInfo selectByUsername(String username) {
       //2.获取sqlsession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取UserInfoMapper
        UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);
        //4.调用方法
        UserInfo userInfo = mapper.selectByUsername(username);
        //提交事务

        //5.释放资源
        sqlSession.close();
        return userInfo;
    }

    //添加一条用户数据
    public void addUserInfo(UserInfo userInfo) {
        //2.获取Sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);
        //4.调用方法
        mapper.addUserInfo(userInfo);
        //提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
    }

    //修改一条用户信息
    public void updateByUsername(UserInfo userInfo) {
        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);
        //4.调用方法
        mapper.updateByUsername(userInfo);
        //提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
    }
}
