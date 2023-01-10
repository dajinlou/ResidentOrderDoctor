package cn.zknu;

import java.io.*;
import java.util.Base64;

public class test {
    public static void main(String[] args) {
        // 测试1：将图片转换成base64编码字符串
        String code = imgToCode("D:\\1.png");
        System.out.println("生成的base64编码字符串是：" + code);

        // 测试2：将base64编码字符串变成图片
        boolean flag = codeToImg(code, "D:\\2.png");
        System.out.println("是否成功生成图片：" + (flag ? "是" : "否"));
    }

    /**
     * 将图片转换为base64编码字符串
     * @param imgDic 图片的全路径
     * @return 图片的base64编码结果
     */
    private static String imgToCode(String imgDic) {
        // 参数验证
        File file = new File(imgDic);

        // 将图片转换成字节数组
        InputStream inputStream = null;
        byte[] bytes = null;
        try {
            inputStream = new FileInputStream(imgDic);
            bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
        } catch (Exception e) {
            throw new RuntimeException("转换过程中出现异常");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 生成base64编码字符串

        byte[] result = Base64.getEncoder().encode(bytes);
        return new String(result);
    }

    /**
     * 根据图片的base64编码字符串生成图片到指定位置
     * @param imgCode 图片的base64编码字符串
     * @param imgDir 生成图片的指定位置的全路径
     * @return
     */
    private static boolean codeToImg(String imgCode, String imgDir) {

        OutputStream outputStream = null;
        try {
            // 对Base64解码字符串进行解码
            byte[] bytes = Base64.getDecoder().decode(imgCode);
            // 处理异常数据
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {
                    bytes[i] += 256;
                }
            }
            // 生成图片到指定位置
            outputStream = new FileOutputStream(imgDir);
            outputStream.write(bytes);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
