package com.tvnsoftware.flicks.api.restservice;

import android.content.Context;

import com.tvnsoftware.flicks.api.model.NowPlaying;
import com.tvnsoftware.flicks.api.model.NowPlayingRequest;
import com.tvnsoftware.flicks.api.restmanager.CommonInterface;
import com.tvnsoftware.flicks.api.restmanager.RetrofitManager;

/**
 * Created by TamHH on 6/15/2017.
 */

public class NowPlayingService extends BaseService<NowPlayingRequest, NowPlaying> {
    private NowPlayingRequest nowPlayingRequest;

    @Override
    public void request(Context context, CommonInterface.ModelResponse<NowPlaying> callBack) {
        RetrofitManager.getInstance().sendApiRequest(RetrofitManager.getInstance().getRestApiEndpointInterface().getNowPlayings(nowPlayingRequest.getApiKey()), callBack);
    }

    @Override
    public void setRequest(NowPlayingRequest r) {
        nowPlayingRequest = r;
    }
}
