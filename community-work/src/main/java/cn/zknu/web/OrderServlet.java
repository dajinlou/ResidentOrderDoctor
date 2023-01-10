package cn.zknu.web;

import cn.zknu.pojo.Order;
import cn.zknu.service.OrderService;
import cn.zknu.service.impl.OrderServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/order/*")
public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();

    //查询所有预约信息 √
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service查询
        List<Order> orders = orderService.selectAll();
        //2.转为JSON
        String jsonString = JSON.toJSONString(orders);

        //3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //用户查询预约信息 √
    public void selectByUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前端url?username=xiaohei
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        //1.调用service查询
        List<Order> orders = orderService.selectByUsername(username);
        //2.转为JSON
        String jsonString = JSON.toJSONString(orders);

        //3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //医生查询预约信息 √
    public void selectByDoctorName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前端
        //前端url?doctorName=liDoctor
        request.setCharacterEncoding("UTF-8");
        String doctorName = request.getParameter("doctorName");
        //1.调用service查询
        List<Order> orders = orderService.selectByDoctorName(doctorName);
        //2.转为JSON
        String jsonString = JSON.toJSONString(orders);

        //3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //添加居民预约信息  √
    public void addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前端
        request.setCharacterEncoding("UTF-8");
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        //转为Order对象
        Order order = JSON.parseObject(params, Order.class);
        //查询预约该医生的人数
        int num = orderService.selectNumByDoctorName(order.getDoctorName());
        //预约该医生的上限
        int maxCount = orderService.selectCountByDoctorName(order.getDoctorName());
        //预约记录
        Order ordered = orderService.selectByDoctorNameAndUsername(order.getDoctorName(), order.getUsername());
        if (num < maxCount) {
            if (ordered != null) {
                response.getWriter().write("ordered");
            } else {
                //1.调用service添加
                orderService.addOrder(order);
                //2.响应数据
                //response.setContentType("text/json;charset=utf-8");
                response.getWriter().write("success");
            }
        } else {
            response.getWriter().write("fail");
        }
    }

    //修改预约信息  √
    public void updateOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前端
        request.setCharacterEncoding("UTF-8");
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        //转为Order对象
        Order order = JSON.parseObject(params, Order.class);
        //1.调用service修改
        orderService.updateByNames(order);
        //2.响应数据
        //response.setContentType("text/json;charset=utf-8");
        response.getWriter().write("success");
    }

    //删除居民信息 √
    public void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前端url?username=xiaohei&doctorName=wangDoctor
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String doctorName = request.getParameter("doctorName");
        //1.调用service修改
        orderService.deleteOrder(username, doctorName);
        //2.响应数据
        //response.setContentType("text/json;charset=utf-8");
        response.getWriter().write("success");
    }

}
