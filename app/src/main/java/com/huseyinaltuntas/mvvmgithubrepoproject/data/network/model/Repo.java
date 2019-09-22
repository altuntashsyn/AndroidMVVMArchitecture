package com.huseyinaltuntas.mvvmgithubrepoproject.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Repo implements Parcelable {


    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("avatar_url")
    private String avatar_url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getOpen_issues() {
        return open_issues;
    }

    public void setOpen_issues(int open_issues) {
        this.open_issues = open_issues;
    }

    public com.huseyinaltuntas.mvvmgithubrepoproject.data.network.model.Owner getOwner() {
        return owner;
    }

    public void setOwner(com.huseyinaltuntas.mvvmgithubrepoproject.data.network.model.Owner owner) {
        this.owner = owner;
    }

    public static Creator<Repo> getCREATOR() {
        return CREATOR;
    }

    @Expose
    @SerializedName("full_name")
    private String full_name;

    @Expose
    @SerializedName("stargazers_count")
    private int stars;

    @Expose
    @SerializedName("open_issues_count")
    private int open_issues;

    @Expose
    @SerializedName("owner")
    private com.huseyinaltuntas.mvvmgithubrepoproject.data.network.model.Owner owner;

    protected Repo(Parcel in) {
        id = in.readInt();
        name = in.readString();
        avatar_url = in.readString();
        full_name = in.readString();
        stars = in.readInt();
        open_issues = in.readInt();
        owner = in.readParcelable(com.huseyinaltuntas.mvvmgithubrepoproject.data.network.model.Owner.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(avatar_url);
        dest.writeString(full_name);
        dest.writeInt(stars);
        dest.writeInt(open_issues);
        dest.writeParcelable(owner, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Repo> CREATOR = new Creator<Repo>() {
        @Override
        public Repo createFromParcel(Parcel in) {
            return new Repo(in);
        }

        @Override
        public Repo[] newArray(int size) {
            return new Repo[size];
        }
    };
}
