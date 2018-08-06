package data.model;

import java.util.ArrayList;

/**
 * Created by stpl on 9-1-2018.
 */

public class CityList {
    private String message;
    private String messagess;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<CityData> getObject() {
        return object;
    }

    public void setObject(ArrayList<CityData> object) {
        this.object = object;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    private ArrayList<CityData> object;

    private String success;
}
