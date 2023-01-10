package cn.zknu.pojo;


/*健康信息表*/
public class HealthInfo {

    private Integer id;   //
    private String username;  //账号
    private String name;      //居民姓名
    private Float height;    //身高
    private Float weight;    //体重
    private Integer bloodPressure;  //血压
    private Integer heartRate;    //心率
    private String allergyHistory;  //药物过敏史
    private String description;  //个人身体状况说明

    public HealthInfo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Integer getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(Integer bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public String getAllergyHistory() {
        return allergyHistory;
    }

    public void setAllergyHistory(String allergyHistory) {
        this.allergyHistory = allergyHistory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "HealthInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", bloodPressure=" + bloodPressure +
                ", heartRate=" + heartRate +
                ", allergyHistory='" + allergyHistory + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
