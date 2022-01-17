import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class SaveData {

    //向数据库 basic表 添加城市信息
    public static void saveCity(ACity city) throws SQLException {

        //创建连接
        Connection conn = DbUtil.getConnection();

        //组织sql语句
        String sql ="insert into basic (`id`, `name`, `latitude`, `longitude`) values (?,?,?,?)";

        //创建PreparedStatement的对象 preparedStatement，与String sql关联
        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        //设置 ? 的值
        preparedStatement.setInt(1,city.getId());
        preparedStatement.setString(2, city.getName());
        preparedStatement.setString(3, city.getLat());
        preparedStatement.setString(4, city.getLon());


        //插入数据库
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("城市："+city.getName()+"已存在");
        }

        //该关的都关
        DbUtil.close(conn);
        DbUtil.close(preparedStatement);
    }

    //向数据库 weather表 添加天气信息
    public static void saveWeather(ArrayList<AWeather> list) throws SQLException {
        //创建连接
        Connection conn = DbUtil.getConnection();

        //组织sql语句
        String sql ="replace into weather (`id`, `fxDate`, `tempMax`, `tempMin`,`textDay`) values (?,?,?,?,?)";

        //创建PreparedStatement的对象 preparedStatement，与 sql关联
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        for(int i=0;i<3;i++) {
            preparedStatement.setInt(1,list.get(i).getId());
            preparedStatement.setString(2,list.get(i).getFxDate());
            preparedStatement.setString(3,list.get(i).getTempMax());
            preparedStatement.setString(4,list.get(i).getTempMin());
            preparedStatement.setString(5,list.get(i).getTextDay());
            //插入数据库
            preparedStatement.executeUpdate();
        }

        //该关的都关
        DbUtil.close(conn);
        DbUtil.close(preparedStatement);
    }
}
