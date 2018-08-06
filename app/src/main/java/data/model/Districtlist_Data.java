package data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Rajan Gupta on 06-07-2018.
 */

public class Districtlist_Data implements Parcelable {
    @Override
    public String toString() {
        return "Decode [label="  + ", values="  + "]";
    }

    public ArrayList<Citylist_Data> getCitylist() {
        return citylist;
    }

    public void setCitylist(ArrayList<Citylist_Data> citylist) {
        this.citylist = citylist;
    }

    public String getDistrictid() {
        return districtid;
    }

    public void setDistrictid(String districtid) {
        this.districtid = districtid;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
    private ArrayList<Citylist_Data>  citylist=new ArrayList<>();

    private String districtid;
    private String district;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.citylist);
        dest.writeString(this.districtid);
        dest.writeString(this.district);
    }

    public Districtlist_Data() {
    }

    protected Districtlist_Data(Parcel in) {
        this.citylist = in.createTypedArrayList(Citylist_Data.CREATOR);
        this.districtid = in.readString();
        this.district = in.readString();
    }

    public static final Creator<Districtlist_Data> CREATOR = new Creator<Districtlist_Data>() {
        @Override
        public Districtlist_Data createFromParcel(Parcel source) {
            return new Districtlist_Data(source);
        }

        @Override
        public Districtlist_Data[] newArray(int size) {
            return new Districtlist_Data[size];
        }
    };
}
