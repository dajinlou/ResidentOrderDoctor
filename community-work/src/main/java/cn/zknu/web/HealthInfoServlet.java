package cn.zknu.web;

import cn.zknu.pojo.HealthInfo;
import cn.zknu.service.HealthInfoService;
import cn.zknu.service.impl.HealthInfoServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/healthInfo/*")
public class HealthInfoServlet extends BaseServlet{

    private HealthInfoService healthInfoService = new HealthInfoServiceImpl();

    //查询所有用户
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service查询
        List<HealthInfo> healthInfos = healthInfoService.selectAll();
        //2.转为JSON
        String jsonString = JSON.toJSONString(healthInfos);

        //3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //查询一条信息
    public void selectByUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前端url?username=xiaohei
        request.setCharacterEncoding("UTF-8");
        String str = request.getParameter("username");
        //编码，再解码
        byte[] bytes = str.getBytes(StandardCharsets.ISO_8859_1);
        String username = new String(bytes, "utf-8");
        //1.调用service查询
        HealthInfo healthInfo = healthInfoService.selectByUsername(username);
        //2.转为JSON
        String jsonString = JSON.toJSONString(healthInfo);

        //3.写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //添加一条信息
    public void addHealthInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前端
        String username="xiaoer";
        String name="小二";
        Float height= Float.valueOf(173);
        Float weight= Float.valueOf(50);
        Integer bloodPressure = 101;
        Integer heartRate = 87;
        String allergyHistory = "青霉素";
        //封装
        HealthInfo healthInfo = new HealthInfo();
        healthInfo.setUsername(username);
        healthInfo.setName(name);
        healthInfo.setHeartRate(heartRate);
        healthInfo.setHeight(height);
        healthInfo.setWeight(weight);
        healthInfo.setBloodPressure(bloodPressure);
        healthInfo.setAllergyHistory(allergyHistory);

        //1.调用service添加
        healthInfoService.addHealthInfo(healthInfo);
        //3.响应成功的标识
        response.getWriter().write("success");
    }

    //修改一条信息
    public void updateByUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前端
      request.setCharacterEncoding("UTF-8");
        BufferedReader br = request.getReader();
        String params = br.readLine();

        //转为healthInfo对象
        HealthInfo healthInfo = JSON.parseObject(params, HealthInfo.class);

        //1.调用service修改
        healthInfoService.updateByUsername(healthInfo);
        //3.响应成功的标识
        response.getWriter().write("success");
    }

    //	删除一条信息
    public void deleteByUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //前端
        String username="xiaoer";

        //1.调用service修改
        healthInfoService.deleteByUsername(username);
        //3.响应成功的标识
        response.getWriter().write("success");
    }

}
