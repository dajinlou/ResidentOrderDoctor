package cn.zknu.service.impl;

import cn.zknu.mapper.UploadMapper;
import cn.zknu.pojo.Image;
import cn.zknu.service.UploadService;
import cn.zknu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UploadServiceImpl implements UploadService {
    //1.获取sqlSessionFactory对象
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<Image> selectDoctorImages() {
        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        UploadMapper mapper = sqlSession.getMapper(UploadMapper.class);
        //4.调用方法
        List<Image> images = mapper.selectDoctorImages();
        //5.释放资源
        sqlSession.close();
        return images;
    }

    public Image selectByUsernameAndPosition(String username, String position) {
        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        UploadMapper mapper = sqlSession.getMapper(UploadMapper.class);
        //4.调用方法
        Image image = mapper.selectByUsernameAndPosition(username, position);
        //5.释放资源
        sqlSession.close();
        return image;
    }

    public void addImage(Image image) {
        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        UploadMapper mapper = sqlSession.getMapper(UploadMapper.class);
        //4.调用方法
        mapper.addImage(image);
        //提交事务
        sqlSession.commit();
        //5.释放资源
        sqlSession.close();
    }

    public void updateImagePath(Image image) {
        //2.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取mapper对象
        UploadMapper mapper = sqlSession.getMapper(UploadMapper.class);
        //4.调用方法
        mapper.updateImagePath(image);
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
