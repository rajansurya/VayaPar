package data.model;

/**
 * Created by stpl on 7-12-2017.
 */
public class Category {
    private String id;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    private String icon;
    private String title;

    public String getSmallicon() {
        return smallicon;
    }

    public void setSmallicon(String smallicon) {
        this.smallicon = smallicon;
    }

    private String smallicon;

    private String subcatlist;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubcatlist() {
        return subcatlist;
    }

    public void setSubcatlist(String subcatlist) {
        this.subcatlist = subcatlist;
    }
}
