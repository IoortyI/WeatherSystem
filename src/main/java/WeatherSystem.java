import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class WeatherSystem {

    //添加城市及其天气信息至数据库
    public static void addCityWeather(String city_name) throws SQLException {
        ACity city = null;
        try {
            city = GetData.getCity(city_name);
            SaveData.saveWeather(GetData.getWeather(city.getId()));
        } catch (IOException e) {
            System.out.println("---    输入城市名有误   ---");
            return;
        }
        SaveData.saveCity(city);
    }

    //由数据库查询城市基本信息
    public static void queryCityInfo(String city_name) throws SQLException {
        Connection conn = DbUtil.getConnection();
        String sql = "select * from basic where `name`=?";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, city_name);

        ResultSet set = preparedStatement.executeQuery();

        //判读结果集是否为空
        if (!set.isBeforeFirst()) {
            System.out.println("--- 尚未添加该城市信息 ---");
            return;
        }

        while (set.next()) {
            int id = set.getInt("id");
            String name = set.getString("name");
            String lat = set.getString("latitude");
            String lon = set.getString("longitude");
            System.out.println(
                    " " + name +
                            " id:" + id +
                            " 纬度:" + lat +
                            " 精度:" + lon
            );
        }

        DbUtil.close(conn);
        DbUtil.close(set);
        DbUtil.close(preparedStatement);
    }

    //查询指定城市及其天气信息
    public static void queryWeatherInfo(String city_name) throws SQLException {
        Connection conn = DbUtil.getConnection();

        LocalDate localDate = LocalDate.now();
        String sql = "select * from weather where `id`=(select `id` from basic where `name`=?) and (`fxDate`=? or`fxDate`=?or`fxDate`=?)";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, city_name);
        preparedStatement.setString(2, localDate.toString());
        preparedStatement.setString(3, localDate.plusDays(1).toString());
        preparedStatement.setString(4, localDate.plusDays(2).toString());

        ResultSet set = preparedStatement.executeQuery();

        //判断结果集是否为空
        if (!set.isBeforeFirst()) {
            System.out.println("--- 尚未添加该城市信息 ---");
            return;
        }

        System.out.println(city_name + "三日天气状况：");
        while (set.next()) {
            String fxDate = set.getString("fxDate");
            int tempMax = set.getInt("tempMax");
            int tempMin = set.getInt("tempMin");
            String textDay = set.getString("textDay");
            System.out.println(
                    "  " + fxDate +
                            "  " + tempMax + "°C" +
                            " /" + tempMin + "°C" +
                            "  " + textDay);
        }

        DbUtil.close(conn);
        DbUtil.close(set);
        DbUtil.close(preparedStatement);
    }

    //删除指定城市及其天气信息
    public static void deleteCityWeather(String city_name) throws SQLException {
        Connection conn = DbUtil.getConnection();


        String sql_a = "delete from weather where `id`=(select `id` from basic where `name`=?)";
        String sql_b = "delete from basic where `name`=?";

        PreparedStatement preparedStatement = conn.prepareStatement(sql_a);
        preparedStatement.setString(1, city_name);


        int k = preparedStatement.executeUpdate();
        if(k!=0) {
            preparedStatement = conn.prepareStatement(sql_b);
            preparedStatement.setString(1, city_name);
            preparedStatement.executeUpdate();
        }
        else{
            System.out.println("--- 尚未添加该城市信息 ---");
        }
        DbUtil.close(conn);
        DbUtil.close(preparedStatement);
    }

    public static void main(String[] args) throws IOException, SQLException {
        System.out.println("诶嘿嘿，天气查询系统来咯~");
        Scanner in = new Scanner(System.in);
        System.out.println("----------------------");
        System.out.println("--   1.查询城市天气    --");
        System.out.println("--   2.查询城市信息    --");
        System.out.println("--   3.添加新城市      --");
        System.out.println("--   4.删除城市        --");
        System.out.println("--   0.退出系统        --");
        System.out.println("----------------------");

        while (true) {
            System.out.println("输入‘正确’序号执行：");
            String xd = in.nextLine();


            switch (xd) {
                case "0": {
                    System.out.println("我的任务完成啦，哇哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
                    System.exit(0);
                }
                break;
                case "1": {
                    System.out.println("输入要查询的城市：");
                    String city_name = in.nextLine();
                    queryWeatherInfo(city_name);
                }
                break;
                case "2": {
                    System.out.println("输入要查询的城市：");
                    String city_name = in.nextLine();
                    queryCityInfo(city_name);
                }
                break;
                case "3": {
                    System.out.println("输入要添加的城市：");
                    String city_name = in.nextLine();
                    addCityWeather(city_name);
                }
                break;
                case "4": {
                    System.out.println("输入要删除的城市：");
                    String city_name = in.nextLine();
                    deleteCityWeather(city_name);
                }
                break;
                default: {
                    System.out.println("tnnd ,为什么不听话？？！");
                }
            }
        }
    }
}
