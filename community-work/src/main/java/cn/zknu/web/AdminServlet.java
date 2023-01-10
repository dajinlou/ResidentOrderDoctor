package cn.zknu.web;

import cn.zknu.pojo.Admin;
import cn.zknu.service.AdminService;
import cn.zknu.service.impl.AdminServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/admin/*")
public class AdminServlet extends BaseServlet {
    private AdminService adminService = new AdminServiceImpl();

    //通过用户名和密码查询用户查找一个用户 √
    public void selectByAdminName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.url?username=xiaohei;
        request.setCharacterEncoding("UTF-8");
        String adminName = request.getParameter("username");
        //1.调用service查询
        Admin _admin = adminService.selectByAdminName(adminName);
        //响应数据
        if (_admin == null) {
            response.getWriter().write("success");
        } else {
            response.getWriter().write("fail");
        }


    }

    //通过用户名和密码查询用户查找一个用户 √
    public void checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.接收用户和密码,它是以json格式的数据提交过来的
        request.setCharacterEncoding("UTF-8");
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转换为User对象
        //转换为Admin对象
        Admin admin = JSON.parseObject(params, Admin.class);
        //1.调用service查询
        Admin _admin = adminService.checkLogin(admin.getAdminName(), admin.getPassword());
        //响应数据
        if (_admin != null) {
            response.getWriter().write("success");
        } else {
            response.getWriter().write("fail");
        }


    }

    //添加用户 √
    public void addAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前端传出信息
        request.setCharacterEncoding("UTF-8");
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为admin对象
        Admin admin = JSON.parseObject(params, Admin.class);
        //1.调用service添加
        adminService.addAdmin(admin);

        //响应成功标识
        response.getWriter().write("success");
    }

    //修改管理员密码 √
    public void updateByAdminName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前端传出信息
        request.setCharacterEncoding("UTF-8");
        String adminName = request.getParameter("username");
        String newPassword = request.getParameter("newPassword");
        //1.调用service修改
       adminService.updateByAdminName(adminName,newPassword);

        //响应成功标识
        response.getWriter().write("success");
    }


}
