package data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by RDX on 12/24/2017.
 */
public class Sub_CategoryList implements Parcelable {
//    private String message;

    private ArrayList<Sub_Category> object;

    private String success;

//    public String getMessage ()
//    {
//        return message;
//    }

//    public void setMessage (String message)
//    {
//        this.message = message;
//    }

    public ArrayList<Sub_Category> getObject() {
        return object;
    }

    public void setObject(ArrayList<Sub_Category> object) {
        this.object = object;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.object);
        dest.writeString(this.success);
    }

    public Sub_CategoryList() {
    }

    protected Sub_CategoryList(Parcel in) {
        this.object = in.createTypedArrayList(Sub_Category.CREATOR);
        this.success = in.readString();
    }

    public static final Creator<Sub_CategoryList> CREATOR = new Creator<Sub_CategoryList>() {
        @Override
        public Sub_CategoryList createFromParcel(Parcel source) {
            return new Sub_CategoryList(source);
        }

        @Override
        public Sub_CategoryList[] newArray(int size) {
            return new Sub_CategoryList[size];
        }
    };
}
