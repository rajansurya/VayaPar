package data.model;

import java.util.ArrayList;

/**
 * Created by stpl on 7-12-2017.
 */
public class State {
    @Override
    public String toString() {
        return "Decode [label="  + ", values="  + "]";
    }
    private String id;

    private ArrayList<Districtlist_Data> districtlist =new ArrayList<>();

    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Districtlist_Data> getSubcatlist() {
        return districtlist;
    }

    public void setSubcatlist(ArrayList<Districtlist_Data> subcatlist) {
        this.districtlist = subcatlist;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
