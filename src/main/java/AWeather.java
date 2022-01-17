public class AWeather {

    private int id;
    private String fxDate;
    private String tempMax;
    private String tempMin;
    private String textDay;

    public  AWeather(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFxDate() {
        return fxDate;
    }

    public void setFxDate(String fxDate) {
        this.fxDate = fxDate;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getTextDay() {
        return textDay;
    }

    public void setTextDay(String textDay) {
        this.textDay = textDay;
    }

    @Override
    public String toString(){
        return  "id ="+id+
                ", fxDate ="+fxDate+
                ", tempMax ="+tempMax+
                ", tempMin ="+tempMin+
                ", textDay ="+textDay;
    }
}
