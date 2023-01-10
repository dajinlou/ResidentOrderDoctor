package cn.zknu.web;

import cn.zknu.pojo.Image;
import cn.zknu.service.UploadService;
import cn.zknu.service.impl.UploadServiceImpl;
import com.alibaba.fastjson.JSON;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@WebServlet("/upload/*")
public class UploadServlet extends BaseServlet {
    private UploadService uploadService = new UploadServiceImpl();

    //上传
    public void uploadImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.前端
        request.setCharacterEncoding("UTF-8");
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        //转为img对象
        Image image = JSON.parseObject(params, Image.class);
        String imgCode = image.getImg().split(",")[1].toString();
        //获取图片上传的时间，防止 图片重名
        String dateTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String imgName = dateTime + image.getUrl();
       //图片路径
        String imgDir = "E:\\IdeaProject\\ProjectSpecific\\community\\community-front\\src\\assets\\upload\\" + imgName;
        codeToImg(imgCode, imgDir);
        image.setPath(imgName);
        //2.调用service方法
        Image flag = uploadService.selectByUsernameAndPosition(image.getUsername(), image.getPosition());
        //判断是插入还是修改事务
        if (flag == null) {
            uploadService.addImage(image);
        } else {
            uploadService.updateImagePath(image);
            //删除原先的照片
            String filename = flag.getPath();
            String path = "E:\\IdeaProject\\ProjectSpecific\\community\\community-front\\src\\assets\\upload\\"+filename;
            File file = new File(path);
            file.delete();
        }

        //3.响应
        response.getWriter().write("success!"+imgName);
    }

    //获取图片
    public void getImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.前端url?username="xiaohei"&position=3
        request.setCharacterEncoding("UTF-8");
        String str = request.getParameter("username");
        String position = request.getParameter("position");
        //重新编码后，再解码
        byte[] bytes = str.getBytes("ISO-8859-1");
        String username = new String(bytes, "utf-8");
        //2.调用方法
        Image image = uploadService.selectByUsernameAndPosition(username, position);
        //转为json
        String jsonString = JSON.toJSONString(image);
        //3.响应
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //获取所有医生的图片
    public void getDoctorImages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.前端
        //2.调用方法
        List<Image> images = uploadService.selectDoctorImages();
        //转为json
        String jsonString = JSON.toJSONString(images);
        //3.响应
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }


    //解码
    private static boolean codeToImg(String imgCode, String imgDir) {

        OutputStream outputStream = null;
        try {
            // 对Base64解码字符串进行解码
            byte[] bytes = Base64.getDecoder().decode(imgCode);
            // 处理异常数据
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {
                    bytes[i] += 256;
                }
            }
            // 生成图片到指定位置
            outputStream = new FileOutputStream(imgDir);
            outputStream.write(bytes);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
