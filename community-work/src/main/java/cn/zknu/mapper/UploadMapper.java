package cn.zknu.mapper;

import cn.zknu.pojo.Image;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UploadMapper {

    //查询所有的医生图片
    @Select("select * from tb_image where position='6'")
    List<Image> selectDoctorImages();
    //查询一条数据
    @Select("select * from tb_image where username=#{username} and position=#{position}")
    Image selectByUsernameAndPosition(@Param("username")String username,@Param("position")String position);

    //插入数据
    @Insert("insert into tb_image values (null,#{username},#{name},#{position},#{path})")
    void addImage(Image image);

    //修改数据
    @Update("update tb_image set path=#{path} where username=#{username} and position = #{position}")
    void updateImagePath(Image image);
}
