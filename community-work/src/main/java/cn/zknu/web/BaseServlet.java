package cn.zknu.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*根据HttpServlet,根据请求的最后一行路径进行方法分发*/

public class BaseServlet extends HttpServlet {
    //根据请求的最后一段进行方法分发
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //1.获取请求路径
        String uri = req.getRequestURI();
        //2.在获取最后一段路径，方法名
        int index = uri.lastIndexOf('/');
        String methodName = uri.substring(index+1);
//        System.out.println(methodName);

        //2.1 获取BrandServlet /UserServlet 字节码对象 class
        Class<? extends BaseServlet> cls = this.getClass();

        //2.2获取执行方法 Method对象
        try {
            Method method = cls.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            //2.3.执行方法
            method.invoke(this,req,resp);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }
}
