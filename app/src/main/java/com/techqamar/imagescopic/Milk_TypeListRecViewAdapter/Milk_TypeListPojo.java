package com.techqamar.imagescopic.Milk_TypeListRecViewAdapter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Milk_TypeListPojo {
    @SerializedName("id")
    @Expose
    private String _id;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("first_name")
    @Expose
    private String first_name;
    @SerializedName("last_name")
    @Expose
    private String last_name;

    public Milk_TypeListPojo(String _id, String avatar, String email, String first_name, String last_name) {
        this._id = _id;
        this.avatar = avatar;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}