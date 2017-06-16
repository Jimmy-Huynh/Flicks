package com.tvnsoftware.flicks.api.restmanager;

/**
 * Created by TamHH on 6/15/2017.
 */

public class CommonInterface<T> {

    public interface ModelResponse<T> {
        void onSuccess(T result);

        void onFail();
    }
}
