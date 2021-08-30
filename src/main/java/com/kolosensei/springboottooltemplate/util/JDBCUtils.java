package com.kolosensei.springboottooltemplate.util;

/**
 * @author kolosensei
 * @version 1.0
 * @date 10/12/2019 14:01
 */

import java.sql.*;

public class JDBCUtils {
    public static String driver = "com.mysql.jdbc.Driver";
    public static String url_start = "jdbc:mysql://";
    public static String url_suffix = "?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
    private static Connection conn;

    public JDBCUtils() {
    }

    public static Connection getConnection(String url, String user, String password) {
        try {
            conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (Exception var4) {
            throw new RuntimeException("链接数据库的url或用户名密码错误！");
        }
    }

    public static Connection getConnection(String ip, String port, String database, String user, String password) {
        String url = url_start + ip + ":" + port + "/" + database + url_suffix;

        try {
            conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (Exception var7) {
            throw new RuntimeException("链接数据库的url或用户名密码错误！");
        }
    }

    public static void closeStament(ResultSet rs, Statement st, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException var6) {
                var6.printStackTrace();
            }
        }

        if (st != null) {
            try {
                st.close();
            } catch (SQLException var5) {
                var5.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException var4) {
                var4.printStackTrace();
            }
        }

    }

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException var1) {
            var1.printStackTrace();
        }

        conn = null;
    }
}

