package com.kolosensei.springboottooltemplate.util;

import java.io.*;
import java.util.Properties;

/**
 * @ClassName PropertyUtils
 * @Description 根据文件路径获取属性
 * @Author zhengyang
 * @Date 2020/09/02 16:54
 * @Version 1.0
 */
public class PropertyUtils {

    public static Properties getProperties(String fileName) {
        Properties props = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(fileName));
            props.load(inputStream);
        } catch (FileNotFoundException e) {
            System.out.println("conf.properties文件未找到");
        } catch (IOException e) {
            System.out.println("出现IOException");
        } finally {
            try {
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.out.println("conf.properties文件流关闭出现异常");
            }
        }
        System.out.println("加载properties文件内容完成...........");
        return props;
    }

    public static void main(String[] args) {
        Properties properties = PropertyUtils.getProperties("src/main/resources/yinhua.properties");
        String name = properties.getProperty("tb.anls_field_info");
        System.out.println(name);
    }
}
