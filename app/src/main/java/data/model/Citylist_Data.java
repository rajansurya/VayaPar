package data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rajan Gupta on 06-07-2018.
 */

public class Citylist_Data implements Parcelable {
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    private String city;
    private String cityid;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.city);
        dest.writeString(this.cityid);
    }

    public Citylist_Data() {
    }

    protected Citylist_Data(Parcel in) {
        this.city = in.readString();
        this.cityid = in.readString();
    }

    public static final Creator<Citylist_Data> CREATOR = new Creator<Citylist_Data>() {
        @Override
        public Citylist_Data createFromParcel(Parcel source) {
            return new Citylist_Data(source);
        }

        @Override
        public Citylist_Data[] newArray(int size) {
            return new Citylist_Data[size];
        }
    };
}
