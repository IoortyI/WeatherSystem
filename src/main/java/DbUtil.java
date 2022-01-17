import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DbUtil {
    //链接地址
    private static String url;
    //驱动名称
    private static String driver;
    //用户名
    private static String user;
    //密码
    private static String password;

    static {
        try {
            //通过 Properties对象获取配置文件的信息
            Properties properties = new Properties();
            properties.load(new FileInputStream("src\\main\\resources\\mySQL.properties"));
            //获取相关的值
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //获取连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }


    public static void close(PreparedStatement pstmt) {
        if (pstmt != null) {    //避免出现空指针异常
            try {
                pstmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
