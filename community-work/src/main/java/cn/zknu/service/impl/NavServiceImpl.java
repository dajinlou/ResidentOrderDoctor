package cn.zknu.service.impl;

import cn.zknu.mapper.NavMapper;
import cn.zknu.pojo.Navigation;
import cn.zknu.service.NavService;
import cn.zknu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class NavServiceImpl implements NavService {

    //1.获取sqlSessionFactory工厂
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<Navigation> selectAllOfUser() {

        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        NavMapper mapper = sqlSession.getMapper(NavMapper.class);
        //4.调用方法
        List<Navigation> navigations = mapper.selectAllOfUser();
        //5.释放资源
        sqlSession.close();

        return navigations;
    }

    public List<Navigation> selectAllOfDoctor() {
        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        NavMapper mapper = sqlSession.getMapper(NavMapper.class);
        //4.调用方法
        List<Navigation> navigations = mapper.selectAllOfDoctor();
        //5.释放资源
        sqlSession.close();

        return navigations;
    }

    public List<Navigation> selectAllOfAdmin() {
        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        NavMapper mapper = sqlSession.getMapper(NavMapper.class);
        //4.调用方法
        List<Navigation> navigations = mapper.selectAllOfAdmin();
        //5.释放资源
        sqlSession.close();
        return navigations;
    }
}
