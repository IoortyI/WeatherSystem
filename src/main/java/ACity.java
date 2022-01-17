public class ACity {

    private String name;
    private int id;
    private String lat;
    private String lon;

    public void setId(int id) {
        this.id = id;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    @Override
    public String toString(){
        return "City{"+
                "id="+id+
                ", name="+name+
                ", lat="+lat+
                ", lon="+lon+
                "}";
    }
}
