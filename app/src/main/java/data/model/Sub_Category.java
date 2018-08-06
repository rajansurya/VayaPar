package data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by RDX on 12/24/2017.
 */
public class Sub_Category implements Parcelable {
    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String Category;
    private String id;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Category);
        dest.writeString(this.id);
    }

    public Sub_Category() {
    }

    protected Sub_Category(Parcel in) {
        this.Category = in.readString();
        this.id = in.readString();
    }

    public static final Creator<Sub_Category> CREATOR = new Creator<Sub_Category>() {
        @Override
        public Sub_Category createFromParcel(Parcel source) {
            return new Sub_Category(source);
        }

        @Override
        public Sub_Category[] newArray(int size) {
            return new Sub_Category[size];
        }
    };
}
