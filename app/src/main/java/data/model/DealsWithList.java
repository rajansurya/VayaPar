package data.model;

import java.util.ArrayList;

/**
 * Created by RDX on 1/13/2018.
 */

public class DealsWithList {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<DealsWith> getObject() {
        return object;
    }

    public void setObject(ArrayList<DealsWith> object) {
        this.object = object;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    private ArrayList<DealsWith> object;

    private String success;
}
