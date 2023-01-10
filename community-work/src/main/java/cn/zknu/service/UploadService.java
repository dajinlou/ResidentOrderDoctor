package cn.zknu.service;

import cn.zknu.pojo.Image;

import java.util.List;

public interface UploadService {

    //查询医生图片
    List<Image> selectDoctorImages();

    //查询一条数据
    Image selectByUsernameAndPosition(String username, String position);

    //添加
    void addImage(Image image);

    //修改照片路径
    void updateImagePath(Image image);
}
