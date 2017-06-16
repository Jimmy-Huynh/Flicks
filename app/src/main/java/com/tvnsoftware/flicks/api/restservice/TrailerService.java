package com.tvnsoftware.flicks.api.restservice;

import android.content.Context;

import com.tvnsoftware.flicks.api.model.Trailer;
import com.tvnsoftware.flicks.api.model.TrailerRequest;
import com.tvnsoftware.flicks.api.restmanager.CommonInterface;
import com.tvnsoftware.flicks.api.restmanager.RetrofitManager;

/**
 * Created by Thieusike on 6/15/2017.
 */

public class TrailerService extends BaseService<TrailerRequest, Trailer> {
    private TrailerRequest trailerRequest;

    @Override
    public void request(Context context, CommonInterface.ModelResponse<Trailer> callBack) {
        RetrofitManager.getInstance().sendApiRequest(RetrofitManager.getInstance()
                .getRestApiEndpointInterface().getTrailers(trailerRequest.getId(), trailerRequest.getApiKey()), callBack);
    }

    @Override
    public void setRequest(TrailerRequest r) {
        trailerRequest = r;
    }
}
