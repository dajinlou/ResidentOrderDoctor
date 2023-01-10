package cn.zknu.service.impl;

import cn.zknu.mapper.DoctorMapper;
import cn.zknu.pojo.Doctor;
import cn.zknu.service.DoctorService;
import cn.zknu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class DoctorServiceImpl implements DoctorService {
    //1.获取SqlSessionFactory
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<Doctor> selectAll() {
        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        DoctorMapper mapper = sqlSession.getMapper(DoctorMapper.class);
        //4.调用方法
        List<Doctor> doctors = mapper.selectAll();
        //提交事务

        //5.释放资源
        sqlSession.close();
        return doctors;
    }

    public Doctor selectByDoctorName(String doctorName) {
        //2.获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper
        DoctorMapper mapper = sqlSession.getMapper(DoctorMapper.class);
        //4.调用方法
        Doctor doctor = mapper.selectByDoctorName(doctorName);
        //5.释放资源
        sqlSession.close();
        return doctor;
    }

    public Doctor checkLogin(String doctorName, String password) {
        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        DoctorMapper mapper = sqlSession.getMapper(DoctorMapper.class);
        //4.调用方法
        Doctor doctor = mapper.checkLogin(doctorName, password);
        //5.释放资源
        sqlSession.close();

        return doctor;
    }

    public void addDoctor(Doctor doctor) {
        //2.获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper
        DoctorMapper mapper = sqlSession.getMapper(DoctorMapper.class);
        //4.调用方法
        mapper.addDoctor(doctor);
        //提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
    }

    public void updateByDoctorName(String doctorName, String password) {
        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        DoctorMapper mapper = sqlSession.getMapper(DoctorMapper.class);
        //4.调用方法
        mapper.updateByDoctorName(doctorName, password);
        //提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
    }

    public void updateDoctor(Doctor doctor) {
        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        DoctorMapper mapper = sqlSession.getMapper(DoctorMapper.class);
        //4.调用方法
        mapper.updateDoctor(doctor);
        //提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
    }

    public void deleteByDoctorName(String doctorName) {
        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        DoctorMapper mapper = sqlSession.getMapper(DoctorMapper.class);
        //4.调用方法
        mapper.deleteByDoctorName(doctorName);
        //提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
    }
}
