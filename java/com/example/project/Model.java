package com.example.project;

public class Model {

    String mitemName;
    String mitemDetail;
    int mitemPhoto;

    public Model(String mitemName, String mitemDetail, int mitemPhoto) {
        this.mitemName = mitemName;
        this.mitemDetail = mitemDetail;
        this.mitemPhoto = mitemPhoto;
}
    public String getmitemName() {
        return mitemName;
    }

    public String getmitemDetail() {
        return mitemDetail;
    }

    public int getmitemPhoto() {
        return mitemPhoto;
    }
}