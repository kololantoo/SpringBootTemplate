package com.kolosensei.springboottooltemplate.template;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Date;

/**
 * @author zhengyang
 * @version 1.0
 * @date 2021/1/22 14:44
 * @description: java 反射相关的使用示例
 */
public class JavaReflectTemplate {

    public static void main(String[] args) {
        Test test = new Test();
        Class c1 = test.getClass();
        Constructor[] declaredConstructors = c1.getDeclaredConstructors();
        System.out.println(declaredConstructors);

        //打印每一个构造方法的参数类型
        for (int i = 0; i < declaredConstructors.length; i++) {
            System.out.print(Modifier.toString(declaredConstructors[i].getModifiers()) + "参数：");
            Class[] parametertypes = declaredConstructors[i].getParameterTypes();
            for (int j = 0; j < parametertypes.length; j++) {
                System.out.print(parametertypes[j].getName() + " ");
            }
        }
    }
}
class Test {
    private String name;
    private Date date;
    public int num;

    private Test(String name) {
        this.name = name;
    }

    public Test() {};

    public Test(String name, Date date, int num) {
        this.name = name;
        this.date = date;
        this.num = num;
    }

    private Test(Date date) {
        this.date = date;
    }

    private void show() {
        System.out.println("this is a private method");
    }
}
