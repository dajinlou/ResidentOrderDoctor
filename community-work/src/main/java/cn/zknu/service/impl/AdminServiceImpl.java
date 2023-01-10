package cn.zknu.service.impl;

import cn.zknu.mapper.AdminMapper;
import cn.zknu.pojo.Admin;
import cn.zknu.service.AdminService;
import cn.zknu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class AdminServiceImpl implements AdminService {
    //1.获取sqlSessionFactory对象
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();


    public Admin selectByAdminName(String adminName) {

        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        //4.调用方法
        Admin admin = mapper.selectByAdminName(adminName);
        //5.释放资源
        sqlSession.close();

        return admin;
    }

    public Admin checkLogin(String adminName, String password) {
        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        //4.调用方法
        Admin admin = mapper.checkLogin(adminName, password);
        //5.释放资源
        sqlSession.close();
        return admin;
    }

    public void addAdmin(Admin admin) {
        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        //4.调用方法
        mapper.addAdmin(admin);
        //提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
    }

    public void updateByAdminName(String doctorName, String password) {
        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        //4.调用方法
        mapper.updateByAdminName(doctorName, password);
        //提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
    }

    //2.获取sqlSession对象
    //3.获取mapper对象
    //4.调用方法
    //提交事务
    //5.释放资源
}
