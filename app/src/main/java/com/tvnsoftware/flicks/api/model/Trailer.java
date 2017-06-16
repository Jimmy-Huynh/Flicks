package com.tvnsoftware.flicks.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Thieusike on 6/15/2017.
 */

public class Trailer {
    @SerializedName("id")
    private int id;
    //    public List<object> quicktime;
    @SerializedName("youtube")
    private List<Youtube> youtube;

    public Trailer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Youtube> getYoutube() {
        return youtube;
    }

    public void setYoutube(List<Youtube> youtube) {
        this.youtube = youtube;
    }
}
