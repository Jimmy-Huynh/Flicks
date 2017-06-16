package com.tvnsoftware.flicks.api.restservice;

import android.content.Context;

import com.tvnsoftware.flicks.api.restmanager.CommonInterface;

/**
 * Created by TamHH on 6/15/2017.
 */

public abstract class BaseService<Req, Res> {
    public abstract void request(Context context, CommonInterface.ModelResponse<Res> callBack);

    public abstract void setRequest(Req r);
}
