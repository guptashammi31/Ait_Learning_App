package com.live.ravi.chatdummy;

/**
 * Created by Shammi on 30-12-2018.
 */

public class ImageUploadInfo {

    public String imageName;
public String dt;
    public String imageURL;
public String sub;
    public ImageUploadInfo() {

    }

    public ImageUploadInfo(String sub,String name,String dt, String url) {
           this.sub=sub;
        this.imageName = name;
        this.dt=dt;
        this.imageURL= url;
    }
    public String getSub() {
        return sub;
    }
    public String getDt() {
        return dt;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageURL() {
        return imageURL;
    }

}