package data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by RDX on 12/26/2017.
 */
public class BussinessDetailListingData implements Parcelable {
    private String id;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    private String rating;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    private String logo;

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    private String banner;

    public String getId() { return this.id; }

    public void setId(String id) { this.id = id; }

    private String companyName;

    public String getCompanyName() { return this.companyName; }

    public void setCompanyName(String companyName) { this.companyName = companyName; }

    private String website;

    public String getWebsite() { return this.website; }

    public void setWebsite(String website) { this.website = website; }

    private String category;

    public String getCategory() { return this.category; }

    public void setCategory(String category) { this.category = category; }

    private String subcategory;

    public String getSubcategory() { return this.subcategory; }

    public void setSubcategory(String subcategory) { this.subcategory = subcategory; }

    private String state;

    public String getState() { return this.state; }

    public void setState(String state) { this.state = state; }

    private String district;

    public String getDistrict() { return this.district; }

    public void setDistrict(String district) { this.district = district; }

    private String location;

    public String getLocation() { return this.location; }

    public void setLocation(String location) { this.location = location; }

    private String address;

    public String getAddress() { return this.address; }

    public void setAddress(String address) { this.address = address; }

    private String dealwith;

    public String getDealwith() { return this.dealwith; }

    public void setDealwith(String dealwith) { this.dealwith = dealwith; }

    private String email;

    public String getEmail() { return this.email; }

    public void setEmail(String email) { this.email = email; }

    private String mobile;

    public String getMobile() { return this.mobile; }

    public void setMobile(String mobile) { this.mobile = mobile; }

    private String phone;

    public String getPhone() { return this.phone; }

    public void setPhone(String phone) { this.phone = phone; }

    private String fax;

    public String getFax() { return this.fax; }

    public void setFax(String fax) { this.fax = fax; }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    private String mobile2;
    private String email2;

    public String getContactperson() {
        return contactperson;
    }

    public void setContactperson(String contactperson) {
        this.contactperson = contactperson;
    }

    private String contactperson;

    public BussinessDetailListingData() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.rating);
        dest.writeString(this.logo);
        dest.writeString(this.banner);
        dest.writeString(this.companyName);
        dest.writeString(this.website);
        dest.writeString(this.category);
        dest.writeString(this.subcategory);
        dest.writeString(this.state);
        dest.writeString(this.district);
        dest.writeString(this.location);
        dest.writeString(this.address);
        dest.writeString(this.dealwith);
        dest.writeString(this.email);
        dest.writeString(this.mobile);
        dest.writeString(this.phone);
        dest.writeString(this.fax);
        dest.writeString(this.mobile2);
        dest.writeString(this.email2);
        dest.writeString(this.contactperson);
    }

    protected BussinessDetailListingData(Parcel in) {
        this.id = in.readString();
        this.rating = in.readString();
        this.logo = in.readString();
        this.banner = in.readString();
        this.companyName = in.readString();
        this.website = in.readString();
        this.category = in.readString();
        this.subcategory = in.readString();
        this.state = in.readString();
        this.district = in.readString();
        this.location = in.readString();
        this.address = in.readString();
        this.dealwith = in.readString();
        this.email = in.readString();
        this.mobile = in.readString();
        this.phone = in.readString();
        this.fax = in.readString();
        this.mobile2 = in.readString();
        this.email2 = in.readString();
        this.contactperson = in.readString();
    }

    public static final Creator<BussinessDetailListingData> CREATOR = new Creator<BussinessDetailListingData>() {
        @Override
        public BussinessDetailListingData createFromParcel(Parcel source) {
            return new BussinessDetailListingData(source);
        }

        @Override
        public BussinessDetailListingData[] newArray(int size) {
            return new BussinessDetailListingData[size];
        }
    };
}
