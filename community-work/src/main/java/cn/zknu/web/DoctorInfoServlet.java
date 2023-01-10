package cn.zknu.web;


import cn.zknu.pojo.DoctorInfo;
import cn.zknu.service.DoctorInfoService;
import cn.zknu.service.impl.DoctorInfoServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/doctorInfo/*")
public class DoctorInfoServlet extends BaseServlet {
    private DoctorInfoService doctorInfoService = new DoctorInfoServiceImpl();

    //获取全部信息：
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service查询
        List<DoctorInfo> doctorInfos = doctorInfoService.selectAll();
        //2.转为JSON
        String jsonString = JSON.toJSONString(doctorInfos);

        //3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //	获取一条信息： √
    public void selectByDoctorName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收用户和密码,它是以json格式的数据提交过来的
        request.setCharacterEncoding("UTF-8");
        String doctorName = request.getParameter("username");
        //1.调用service查询
        DoctorInfo doctorInfo = doctorInfoService.selectByDoctorName(doctorName);
        //2.转为JSON
        String jsonString = JSON.toJSONString(doctorInfo);

        //3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    // 添加一条信息：
    public void addDoctorInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前端数据
        String doctorName = "jinDoctor";
        String name = "靳军辉";
        String sex = "男";
        Integer experience = 3;
        String major = "牙科";
        //封装
        DoctorInfo doctorInfo = new DoctorInfo();
        doctorInfo.setDoctorName(doctorName);
        doctorInfo.setExperience(experience);
        doctorInfo.setName(name);
        doctorInfo.setSex(sex);
        doctorInfo.setMajor(major);

        //1.调用service添加
        doctorInfoService.addDoctorInfo(doctorInfo);

        //2.响应
        response.getWriter().write("success");
    }

    //	修改一条信息：√
    public void updateByDoctorName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前端数据
        request.setCharacterEncoding("UTF-8");
        BufferedReader br = request.getReader();
        String params = br.readLine();

        //转为doctorInfo对象
        DoctorInfo doctorInfo = JSON.parseObject(params, DoctorInfo.class);
        //1.调用service添加
        doctorInfoService.updateByDoctorName(doctorInfo);

        //2.响应
        response.getWriter().write("success");
    }

    //	删除一条信息：
    public void deleteByDoctorName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前端数据
        String doctorName = "jinDoctor";

        //1.调用service添加
        doctorInfoService.deleteByDoctorName(doctorName);

        //2.响应
        response.getWriter().write("success");
    }

}
