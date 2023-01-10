package cn.zknu.service.impl;

import cn.zknu.mapper.DoctorInfoMapper;
import cn.zknu.pojo.DoctorInfo;
import cn.zknu.service.DoctorInfoService;
import cn.zknu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class DoctorInfoServiceImpl implements DoctorInfoService {

    //1.创建sqlSessionFactory
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<DoctorInfo> selectAll() {
       //2.获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        DoctorInfoMapper mapper = sqlSession.getMapper(DoctorInfoMapper.class);
        //4.调用方法
        List<DoctorInfo> doctorInfos = mapper.selectAll();
        //5.释放资源
        sqlSession.close();
        return doctorInfos;
    }

    public DoctorInfo selectByDoctorName(String doctorName) {
        //2.获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        DoctorInfoMapper mapper = sqlSession.getMapper(DoctorInfoMapper.class);
        //4.调用方法
        DoctorInfo doctorInfo = mapper.selectByDoctorName(doctorName);
        //5.释放资源
        sqlSession.close();

        return doctorInfo;
    }

    public void addDoctorInfo(DoctorInfo doctorInfo) {
        //2.获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        DoctorInfoMapper mapper = sqlSession.getMapper(DoctorInfoMapper.class);
        //4.调用方法
       mapper.addDoctorInfo(doctorInfo);
       //提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
    }

    public void updateByDoctorName(DoctorInfo doctorInfo) {
        //2.获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        DoctorInfoMapper mapper = sqlSession.getMapper(DoctorInfoMapper.class);
        //4.调用方法
       mapper.updateByDoctorName(doctorInfo);
        //提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
    }

    public void deleteByDoctorName(String doctorName) {
        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        DoctorInfoMapper mapper = sqlSession.getMapper(DoctorInfoMapper.class);
        //4.调用方法
        mapper.deleteByDoctorName(doctorName);
        //提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
    }
}
