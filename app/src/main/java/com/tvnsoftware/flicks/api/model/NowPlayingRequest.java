package com.tvnsoftware.flicks.api.model;

/**
 * Created by TamHH on 6/15/2017.
 */

public class NowPlayingRequest {
    private String apiKey;

    public NowPlayingRequest() {
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
