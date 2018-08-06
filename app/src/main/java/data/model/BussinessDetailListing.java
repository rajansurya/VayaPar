package data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by RDX on 12/26/2017.
 */
public class BussinessDetailListing implements Parcelable {
    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String success;
    private String message;
    private ArrayList<BussinessDetailListingData> object;
    public ArrayList<BussinessDetailListingData> getObject() {
        return object;
    }

    public void setObject(ArrayList<BussinessDetailListingData> object) {
        this.object = object;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.success);
        dest.writeString(this.message);
        dest.writeList(this.object);
    }

    public BussinessDetailListing() {
    }

    protected BussinessDetailListing(Parcel in) {
        this.success = in.readString();
        this.message = in.readString();
        this.object = new ArrayList<BussinessDetailListingData>();
        in.readList(this.object, BussinessDetailListingData.class.getClassLoader());
    }

    public static final Creator<BussinessDetailListing> CREATOR = new Creator<BussinessDetailListing>() {
        @Override
        public BussinessDetailListing createFromParcel(Parcel source) {
            return new BussinessDetailListing(source);
        }

        @Override
        public BussinessDetailListing[] newArray(int size) {
            return new BussinessDetailListing[size];
        }
    };
}
