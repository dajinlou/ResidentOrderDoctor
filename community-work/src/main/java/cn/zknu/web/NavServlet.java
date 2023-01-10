package cn.zknu.web;

import cn.zknu.pojo.Navigation;
import cn.zknu.service.NavService;
import cn.zknu.service.impl.NavServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/nav/*")
public class NavServlet extends BaseServlet {
    private NavService navService = new NavServiceImpl();

    //查询用户导航
    public void selectAllOfUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service查询
        List<Navigation> navigations = navService.selectAllOfUser();
        //2.转为JSON
        String jsonString = JSON.toJSONString(navigations);

        //3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //查询医生导航
    public void selectAllOfDoctor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service查询
        List<Navigation> navigations = navService.selectAllOfDoctor();
        //2.转为JSON
        String jsonString = JSON.toJSONString(navigations);

        //3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //查询管理员导航
    public void selectAllOfAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service查询
        List<Navigation> navigations = navService.selectAllOfAdmin();
        //2.转为JSON
        String jsonString = JSON.toJSONString(navigations);

        //3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

}
