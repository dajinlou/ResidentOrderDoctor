package cn.zknu.web;

import cn.zknu.pojo.AdminInfo;
import cn.zknu.pojo.UserInfo;
import cn.zknu.service.AdminInfoService;
import cn.zknu.service.impl.AdminInfoServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/adminInfo/*")
public class AdminInfoServlet extends BaseServlet {
    private AdminInfoService adminInfoService = new AdminInfoServiceImpl();

    //1.查找一个用户 √
    public void selectByAdminName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.url?username=xiaohei
        request.setCharacterEncoding("UTF-8");
        String adminName = request.getParameter("username");
        //1.调用service查询
        AdminInfo adminInfo = adminInfoService.selectByAdminName(adminName);
        //2.转为JSON
        String jsonString = JSON.toJSONString(adminInfo);

        //3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //2.添加一个用户
    public void addAdminInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前端传出信息
        request.setCharacterEncoding("UTF-8");
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为adminInfo对象
        AdminInfo adminInfo = JSON.parseObject(params, AdminInfo.class);
        //1.调用service添加
        adminInfoService.addAdminInfo(adminInfo);

        //3.响应成功的标识
        response.getWriter().write("success");
    }

    //3.修改用户信息
    public void updateByAdminName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前端传出信息
        request.setCharacterEncoding("UTF-8");
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为adminInfo对象
        AdminInfo adminInfo = JSON.parseObject(params, AdminInfo.class);
        //1.调用service修改
        adminInfoService.updateByAdminName(adminInfo);
        //2.响应成功的标识
        response.getWriter().write("success");
    }

}
