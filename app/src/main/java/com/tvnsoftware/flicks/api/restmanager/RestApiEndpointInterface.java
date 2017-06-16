package com.tvnsoftware.flicks.api.restmanager;

import com.tvnsoftware.flicks.api.model.NowPlaying;
import com.tvnsoftware.flicks.api.model.Trailer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by TamHH on 6/15/2017.
 */

public interface RestApiEndpointInterface {
    @GET("now_playing")
    Call<NowPlaying> getNowPlayings(@Query("api_key") String apiKey);

    @GET("{id}/trailers")
    Call<Trailer> getTrailers(@Path("id") int id, @Query("api_key") String apiKey);
}
