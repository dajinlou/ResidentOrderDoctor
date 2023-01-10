package cn.zknu.web;

import cn.zknu.pojo.User;
import cn.zknu.pojo.UserInfo;
import cn.zknu.service.UserService;
import cn.zknu.service.impl.UserServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    //查询所有用户 √
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service查询
        List<User> users = userService.selectAll();
        //2.转为JSON
        String jsonString = JSON.toJSONString(users);

        //3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //通过用户名和密码查询用户查找一个用户 √
    public void selectByUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.url?username=xiaohei;
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        //1.调用service查询
        User _user = userService.selectByUsername(username);
        //响应数据
        if (_user==null) {
            response.getWriter().write("success");
        }else{
            response.getWriter().write("fail");
        }


    }

    //通过用户名和密码查询用户查找一个用户 √
    public void checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //1.接收用户和密码,它是以json格式的数据提交过来的
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转换为User对象
        User user = JSON.parseObject(params, User.class);
        //1.调用service查询
        User _user = userService.checkLogin(user.getUsername(), user.getPassword());
        //响应数据
        if (_user!=null) {
            response.getWriter().write("success");
        }else{
            response.getWriter().write("fail");
        }


    }

    //添加用户 √
    public void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前端传出信息
        request.setCharacterEncoding("UTF-8");
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为User对象
        User user = JSON.parseObject(params, User.class);
        //1.调用service添加
        userService.addUser(user);

        //响应成功标识
        response.getWriter().write("success");
    }

    //修改用户密码 √
    public void updateByUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前端传出信息
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String newPassword = request.getParameter("newPassword");

        //1.调用service修改
        userService.updateByUsername(username,newPassword);
        //响应成功标识
        response.getWriter().write("success");
    }

    //修改用户 √
    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前端传出信息
        request.setCharacterEncoding("UTF-8");
        BufferedReader br = request.getReader();
        String params = br.readLine();

        //转为User对象
        User user = JSON.parseObject(params, User.class);
        System.out.println(user);
        //1.调用service添加
        userService.updateUser(user);
        //响应成功标识
        response.getWriter().write("success");
    }

    //删除用户
    public void deleteByUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前端传出信息
        String username = "zhangsan";

        //1.调用service删除
        userService.deleteByUsername(username);

        //响应成功标识
        response.getWriter().write("success");
    }

}
