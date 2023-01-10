package cn.zknu.web;

import cn.zknu.pojo.UserInfo;
import cn.zknu.service.UserInfoService;
import cn.zknu.service.impl.UserInfoServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/userInfo/*")
public class UserInfoServlet extends BaseServlet {

    private UserInfoService userInfoService = new UserInfoServiceImpl();

    //查询所有用户 √
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service查询
        List<UserInfo> userInfos = userInfoService.selectAll();
        //2.转为JSON
        String jsonString = JSON.toJSONString(userInfos);

        //3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //1.查找一个用户 √
    public void selectByUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.url?username=xiaohei
        request.setCharacterEncoding("UTF-8");
        String str = request.getParameter("username");
        //重新编码，再解码
        byte[] bytes = str.getBytes("ISO-8859-1");
        String username = new String(bytes, "utf-8");
        //1.调用service查询
        UserInfo userInfo = userInfoService.selectByUsername(username);
        //2.转为JSON
        String jsonString = JSON.toJSONString(userInfo);

        //3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //2.添加一个用户
    public void addUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前端传出信息
        String username = "xiaozhang";
        String name = "小张";
        String sex = "女";
        String birthday = "2010-12-06";
        String num = "18439561318";
        String address = "王庄";
        String description = "喜欢计算机";
        //封装对象
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setName(name);
        userInfo.setSex(sex);
        userInfo.setBirthday(birthday);
//        userInfo.setNum(num);
        userInfo.setAddress(address);
        userInfo.setDescription(description);

        //1.调用service添加
        userInfoService.addUserInfo(userInfo);

        //3.响应成功的标识
        response.getWriter().write("success");
    }

    //3.修改用户信息 √
    public void updateByUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收用户和密码,它是以json格式的数据提交过来的
        request.setCharacterEncoding("UTF-8");
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为userInfo对象
        UserInfo userInfo = JSON.parseObject(params, UserInfo.class);
        //1.调用service添加
        userInfoService.updateByUsername(userInfo);

        //3.响应成功的标识
        response.getWriter().write("success");
    }

}
