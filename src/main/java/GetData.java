import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

public class GetData {

    //听说ObjectMapper开销大，就让它们共用一个
    public static  ObjectMapper mapper = new ObjectMapper();

    //通过api获取城市信息，返回城市对象
    public static ACity getCity(String city_name) throws IOException {
        String urlStr = "https://geoapi.qweather.com/v2/city/lookup?key=7b34dc79f6d04bbeb8b50c035c7c5291&location=" + URLEncoder.encode(city_name, "utf-8");
        URL url = new URL(urlStr);
        URLConnection urlConnection = url.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

        //获取 httpURLConnection 的输入流
        InputStream is = httpURLConnection.getInputStream();
        GZIPInputStream gzipInputStream = new GZIPInputStream(is);
        StringBuilder res = new StringBuilder();

        String line;
        BufferedReader br = new BufferedReader(new
                InputStreamReader(gzipInputStream, StandardCharsets.UTF_8));
        while ((line = br.readLine()) != null) {
            res.append(line);
        }
        //对字符串进行处理（对县级以上只返回第一个json对象）
        res.delete(0, 1);
        String s = res.toString();
        String json = s.substring(s.indexOf('{'), s.indexOf('}') + 1);

        //反序列化时忽略json中存在但Java对象中不存在的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //返回转化得到的对象
        return mapper.readValue(json, ACity.class);
    }

    //通过api获取天气信息，返回天气对象数组
    public static ArrayList<AWeather> getWeather(int city_id) throws IOException {
        String urlStr = "https://devapi.qweather.com/v7/weather/3d?key=7b34dc79f6d04bbeb8b50c035c7c5291&location=" + city_id;
        URL url = new URL(urlStr);
        URLConnection urlConnection = url.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

        //获取 httpURLConnection 的输入流
        InputStream is = httpURLConnection.getInputStream();
        GZIPInputStream gzipInputStream = new GZIPInputStream(is);
        StringBuilder res = new StringBuilder();

        String line;
        BufferedReader br = new BufferedReader(new
                InputStreamReader(gzipInputStream, StandardCharsets.UTF_8));
        while ((line = br.readLine()) != null) {
            res.append(line);
        }

        String json = res.toString();

        ArrayList<AWeather> list = new ArrayList<>();
        JsonNode rootNode = mapper.readTree(json);
        JsonNode dailyNode = rootNode.get("daily");
        //遍历daily数组
        if (null != dailyNode && dailyNode.size() > 0) {
            for (JsonNode node : dailyNode) {
                AWeather temp = new AWeather();
                temp.setId(city_id);
                temp.setFxDate(node.get("fxDate").asText());
                temp.setTempMax(node.get("tempMax").asText());
                temp.setTempMin(node.get("tempMin").asText());
                temp.setTextDay(node.get("textDay").asText());
                list.add(temp);
            }
        }
        return list;
    }
}