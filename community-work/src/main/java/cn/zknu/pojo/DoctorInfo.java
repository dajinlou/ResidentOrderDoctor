package cn.zknu.pojo;
/*医生信息表*/
public class DoctorInfo {
    private Integer id;
    private String doctorName;
    private String name;
    private String sex;
    private String tel;
    private String room;
    private Integer experience;
    private String major;
    private Integer orderCount;  //预约数量

    public DoctorInfo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    @Override
    public String toString() {
        return "DoctorInfo{" +
                "id=" + id +
                ", doctorName='" + doctorName + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                ", room='" + room + '\'' +
                ", experience=" + experience +
                ", major='" + major + '\'' +
                ", orderCount='" + orderCount + '\'' +
                '}';
    }
}
