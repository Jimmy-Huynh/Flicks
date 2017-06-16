package com.tvnsoftware.flicks.api.model;

/**
 * Created by Thieusike on 6/15/2017.
 */

public class TrailerRequest {
    private int id;
    private String apiKey;

    public TrailerRequest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
