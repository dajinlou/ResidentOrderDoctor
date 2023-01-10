package cn.zknu.pojo;

public class Order {
    private Integer id;
    private String doctorName;
    private String dName;
    private String username;
    private String uName;
    private String orderTime;
    private String orderAddress;
    private Integer status;
    private String statusStr;

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

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusStr() {
        if (this.status == -1) {
            return "已拒绝";
        } else if (this.status == 0) {
            return "预约中...";
        } else {
            return "预约成功";
        }
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", doctorName='" + doctorName + '\'' +
                ", dName='" + dName + '\'' +
                ", username='" + username + '\'' +
                ", uName='" + uName + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", orderAddress='" + orderAddress + '\'' +
                ", status=" + status +
                ", statusStr='" + statusStr + '\'' +
                '}';
    }
}
