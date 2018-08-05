package data.model;

import java.util.ArrayList;

/**
 * Created by stpl on 7-12-2017.
 */
public class Categories {
    private String message;

    private ArrayList<Category> object;

    private String success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Category> getObject() {
        return object;
    }

    public void setObject(ArrayList<Category> object) {
        this.object = object;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
