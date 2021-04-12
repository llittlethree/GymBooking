package com.fzh.com.utils;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileToBase64 {
    /**
     * <p>将文件转成base64 字符串</p>
     * @param path 文件路径
     * @return
     * @throws Exception
     */
    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int)file.length()];
        inputFile.read(buffer);
        inputFile.close();
        //return Base64.encodeBase64String(buffer);
        return Base64.encodeBase64String(buffer);
    }
    /**
     * <p>将base64字符解码保存文件</p>
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */
    public static void decoderBase64File(String base64Code,String targetPath) throws Exception {
        byte[] buffer = new Base64().decodeBase64(base64Code);
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
    }
    /**
     * <p>将base64字符保存文本文件</p>
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */
    public static void toFile(String base64Code,String targetPath) throws Exception {
        byte[] buffer = base64Code.getBytes();
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
    }
    /**
     * 判断图片base64字符串的文件格式
     *
     * @param base64ImgData
     * @return
     */
    public static String checkImageBase64Format(String base64ImgData) {
        byte[] b = Base64.decodeBase64(base64ImgData);
        String type = ".jpg";
        if (0x424D == ((b[0] & 0xff) << 8 | (b[1] & 0xff))) {
            type = ".bmp";
        } else if (0x8950 == ((b[0] & 0xff) << 8 | (b[1] & 0xff))) {
            type = ".png";
        } else if (0xFFD8 == ((b[0] & 0xff) << 8 | (b[1] & 0xff))) {
            type = ".jpg";
        }
        return type;
    }

    /**
     * 将base64文本转成图片
     * @param base64TextFilePath
     * @param targetPath
     * @throws Exception
     */
    public static void base64TextFileToFile(String base64TextFilePath, String targetPath) throws Exception {
        FileOutputStream out = null;
        FileInputStream in = null;
        try {
            File file = new File(base64TextFilePath);
            in = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            in.read(buffer);

            byte[] buffer2 = new BASE64Decoder().decodeBuffer(new String(buffer));

            out = new FileOutputStream(targetPath);
            out.write(buffer2);

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                in.close();
            } catch (Exception e1) {
            }
            try {
                out.close();
            } catch (Exception e1) {
            }
        }
    }

    public static void main(String[] args) {
        try {
//            base64TextFileToFile("D:\\attached\\d.txt","D:\\attached\\be.png");

//            encodeBase64File("D:\\attached\\c.png");
            //System.out.println(encodeBase64File("D:\\attached\\011523178639492.jpg"));
            /*String img = getImgStrToBase64("https://www.baidu.com/img/flexible/logo/pc/result.png");
            System.out.println(img);
            System.out.println(checkImageBase64Format(img));
            getImgBase64ToImgFile(
                    img,
                    "E:"+File.separator+"zhang"+File.separator+"baidu.png");*/
            /*UUID uuid = UUID.randomUUID();
            System.out.println(uuid);
            System.out.println(UUID.randomUUID().toString());*/
            System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将网络链接图片或者本地图片文件转换成Base64编码字符串
     *
     * @param imgStr 网络图片Url/本地图片目录路径
     * @return
     */
    public static String getImgStrToBase64(String imgStr) {
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        byte[] buffer = null;
        try {
            //判断网络链接图片文件/本地目录图片文件
            if (imgStr.startsWith("http://") || imgStr.startsWith("https://")) {
                // 创建URL
                URL url = new URL(imgStr);
                // 创建链接
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                inputStream = conn.getInputStream();
                outputStream = new ByteArrayOutputStream();
                // 将内容读取内存中
                buffer = new byte[1024];
                int len = -1;
                while ((len = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }
                buffer = outputStream.toByteArray();
            } else {
                inputStream = new FileInputStream(imgStr);
                int count = 0;
                while (count == 0) {
                    count = inputStream.available();
                }
                buffer = new byte[count];
                inputStream.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    // 关闭inputStream流
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    // 关闭outputStream流
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 对字节数组Base64编码
        return new BASE64Encoder().encode(buffer);
    }


    /**
     * 将图片Base64编码转换成img图片文件
     *
     * @param imgBase64 图片Base64编码
     * @param imgPath   图片生成路径
     * @return
     */
    public static boolean getImgBase64ToImgFile(String imgBase64, String imgPath) {
        boolean flag = true;
        OutputStream outputStream = null;
        try {
            // 解密处理数据
            byte[] bytes = new BASE64Decoder().decodeBuffer(imgBase64);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {
                    bytes[i] += 256;
                }
            }
            outputStream = new FileOutputStream(imgPath);
            outputStream.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            if (outputStream != null) {
                try {
                    // 关闭outputStream流
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }
}
