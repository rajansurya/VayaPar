package data.model;

import java.util.ArrayList;

/**
 * Created by stpl on 7-12-2017.
 */
public class StateList {
    private String message;

    private ArrayList<State> object;

    private String success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<State> getObject() {
        return object;
    }

    public void setObject(ArrayList<State> object) {
        this.object = object;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
