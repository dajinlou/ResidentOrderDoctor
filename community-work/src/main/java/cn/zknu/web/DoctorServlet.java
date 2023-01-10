package cn.zknu.web;

import cn.zknu.pojo.Doctor;
import cn.zknu.pojo.User;
import cn.zknu.service.DoctorService;
import cn.zknu.service.impl.DoctorServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/doctor/*")
public class DoctorServlet extends BaseServlet {
    private DoctorService doctorService = new DoctorServiceImpl();

    //查询所有医生  √
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.调用service查询
        List<Doctor> doctors = doctorService.selectAll();
        //2.转为JSON
        String jsonString = JSON.toJSONString(doctors);

        //3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //查找一个医生 √
    public void selectByDoctorName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.url?username=xiaohei;
        request.setCharacterEncoding("UTF-8");
        String doctorName = request.getParameter("username");
        //1.调用service查询
        Doctor doctor = doctorService.selectByDoctorName(doctorName);

        //3.响应
        if (doctor == null) {
            response.getWriter().write("success");
        } else {
            response.getWriter().write("fail");
        }

    }

    //验证医生登录√
    public void checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收用户和密码,它是以json格式的数据提交过来的
        request.setCharacterEncoding("UTF-8");
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为Doctor对象
        Doctor doctor = JSON.parseObject(params, Doctor.class);
        //1.调用service查询
        Doctor _doctor = doctorService.checkLogin(doctor.getDoctorName(), doctor.getPassword());

        //2.响应
        if (_doctor != null) {
            response.getWriter().write("success");
        } else {
            response.getWriter().write("fail");
        }

//        response.setContentType("text/json;charset=utf-8");
//        response.getWriter().write(jsonString);
    }

    //添加医生√
    public void addDoctor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前端传出信息
        request.setCharacterEncoding("UTF-8");
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //转为doctor对象
        Doctor doctor = JSON.parseObject(params, Doctor.class);
        //1.调用service添加
        doctorService.addDoctor(doctor);

        //响应成功标识
        response.getWriter().write("success");
    }

    //修改医生密码 √
    public void updateByDoctorName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前端传出信息
        request.setCharacterEncoding("UTF-8");
        String doctorName = request.getParameter("username");
        String newPassword = request.getParameter("newPassword");
        //1.调用service修改
        doctorService.updateByDoctorName(doctorName, newPassword);

        //响应成功标识
        response.getWriter().write("success");
    }

    public void updateDoctor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前端传出信息
        request.setCharacterEncoding("UTF-8");
        BufferedReader br = request.getReader();
        String params = br.readLine();
        Doctor doctor = JSON.parseObject(params, Doctor.class);
        //1.调用service修改
        doctorService.updateDoctor(doctor);

        //响应成功标识
        response.getWriter().write("success");
    }


    //删除医生信息
    public void deleteByDoctorName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前端传出信息
        String doctorName = "wangDoctor";

        //1.调用service删除
        doctorService.deleteByDoctorName(doctorName);

        //响应成功标识
        response.getWriter().write("success");
    }


}
