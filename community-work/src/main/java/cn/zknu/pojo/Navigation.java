package cn.zknu.pojo;

public class Navigation {
    private String menuName;
    private String title;
    private String title2;
    private String icon;
    private String path;
    private String itemName;
    private String component;

    public Navigation() {
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    @Override
    public String toString() {
        return "Navigation{" +
                "menuName='" + menuName + '\'' +
                ", title='" + title + '\'' +
                ", title2='" + title2 + '\'' +
                ", icon='" + icon + '\'' +
                ", path='" + path + '\'' +
                ", itemName='" + itemName + '\'' +
                ", component='" + component + '\'' +
                '}';
    }
}
