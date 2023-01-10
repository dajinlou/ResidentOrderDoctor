package cn.zknu.service.impl;

import cn.zknu.mapper.AdminInfoMapper;
import cn.zknu.pojo.AdminInfo;
import cn.zknu.service.AdminInfoService;
import cn.zknu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


public class AdminInfoServiceImpl implements AdminInfoService {

    //1.创建SqlSessionFactory工厂
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public AdminInfo selectByAdminName(String adminName) {
        //2.获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        AdminInfoMapper mapper = sqlSession.getMapper(AdminInfoMapper.class);
        //4.调用方法
        AdminInfo adminInfo = mapper.selectByAdminName(adminName);
        //提交事务
        //5.释放资源
        sqlSession.close();

        return adminInfo;
    }

    public void addAdminInfo(AdminInfo adminInfo) {
        //2.获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        AdminInfoMapper mapper = sqlSession.getMapper(AdminInfoMapper.class);
        //4.调用方法
        mapper.addAdminInfo(adminInfo);
        //提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
    }

    public void updateByAdminName(AdminInfo adminInfo) {
        //2.获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        AdminInfoMapper mapper = sqlSession.getMapper(AdminInfoMapper.class);
        //4.调用方法
        mapper.updateByAdminName(adminInfo);
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
