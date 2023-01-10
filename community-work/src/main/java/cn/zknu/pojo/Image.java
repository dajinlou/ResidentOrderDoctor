package cn.zknu.pojo;

public class Image {
    private Integer id;
    private String url;  //照片名字
    private String img;  //照片base64编码
    private String username;  //用户账号
    private String name;   //姓名
    private String position;  //职务 3 居民 6 医生 9管理员
    private String path;      //照片的保存路径


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", img='" + img + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
