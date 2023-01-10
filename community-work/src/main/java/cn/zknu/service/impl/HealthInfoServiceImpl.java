package cn.zknu.service.impl;

import cn.zknu.mapper.HealthInfoMapper;
import cn.zknu.pojo.HealthInfo;
import cn.zknu.service.HealthInfoService;
import cn.zknu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class HealthInfoServiceImpl implements HealthInfoService {
    //1.创建工厂
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    //查询所有

    public List<HealthInfo> selectAll() {
        //2.创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper
        HealthInfoMapper mapper = sqlSession.getMapper(HealthInfoMapper.class);
        //4.调用方法
        List<HealthInfo> healthInfos = mapper.selectAll();
        //5.释放资源
        sqlSession.close();
        return healthInfos;
    }

    public HealthInfo selectByUsername(String username) {
        //2.创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper
        HealthInfoMapper mapper = sqlSession.getMapper(HealthInfoMapper.class);
        //4.调用方法
        HealthInfo healthInfo = mapper.selectByUsername(username);
        //5.释放资源
        sqlSession.close();

        return healthInfo;
    }


    public void addHealthInfo(HealthInfo healthInfo) {
        //2.创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper
        HealthInfoMapper mapper = sqlSession.getMapper(HealthInfoMapper.class);
        //4.调用方法
        mapper.addHealthInfo(healthInfo);
        //提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
    }

    //修改一条信息：
    public void updateByUsername(HealthInfo healthInfo) {
        //2.获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper
        HealthInfoMapper mapper = sqlSession.getMapper(HealthInfoMapper.class);
       //4.调用方法
        mapper.updateByUsername(healthInfo);
        //提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
    }

    //	删除一条信息

    public void deleteByUsername(String username) {
        //2.获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper
        HealthInfoMapper mapper = sqlSession.getMapper(HealthInfoMapper.class);
        //4.调用方法
        mapper.deleteByUsername(username);
        //提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
    }

}
