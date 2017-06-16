package com.tvnsoftware.flicks.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.tvnsoftware.flicks.R;
import com.tvnsoftware.flicks.api.model.Movie;
import com.tvnsoftware.flicks.api.model.Trailer;
import com.tvnsoftware.flicks.api.model.TrailerRequest;
import com.tvnsoftware.flicks.api.restmanager.CommonInterface;
import com.tvnsoftware.flicks.api.restservice.TrailerService;
import com.tvnsoftware.flicks.utils.Contant;

public class DetailMovieActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private Movie mMovie;
    private TextView mTvTitle, mTvReleaseDate, mTvContent;
    private ImageView mIvStar;
    private YouTubePlayerView youTubePlayerView;
    private Trailer mTrailer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        mMovie = (Movie) getIntent().getSerializableExtra(Contant.MOVIE_OBJECT);

        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvReleaseDate = (TextView) findViewById(R.id.tv_release_date);
        mTvContent = (TextView) findViewById(R.id.tv_content_summary);
        mIvStar = (ImageView) findViewById(R.id.iv_star);
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.player);

        if (null != mMovie) {
            mTvTitle.setText(mMovie.getTitle());
            mTvReleaseDate.setText(mMovie.getReleaseDate());
            mTvContent.setText(mMovie.getOverview());
            if (mMovie.getVoteAverage() >= 5) {
                Glide.with(DetailMovieActivity.this).load(R.drawable.five_star).into(mIvStar);
            } else if (mMovie.getVoteAverage() >= 4) {
                Glide.with(DetailMovieActivity.this).load(R.drawable.four_star).into(mIvStar);
            } else if (mMovie.getVoteAverage() >= 3) {
                Glide.with(DetailMovieActivity.this).load(R.drawable.three_star).into(mIvStar);
            } else if (mMovie.getVoteAverage() >= 2) {
                Glide.with(DetailMovieActivity.this).load(R.drawable.two_star).into(mIvStar);
            } else {
                Glide.with(DetailMovieActivity.this).load(R.drawable.one_star).into(mIvStar);
            }
            getTrailer();

        }

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (mMovie.getVoteAverage() >= 5) {
            if (null != mTrailer) {
                if (null != mTrailer.getYoutube()) {
                    youTubePlayer.loadVideo(mTrailer.getYoutube().get(0).getSource());//auto play
                }
            }

        } else {
            if (null != mTrailer) {
                if (null != mTrailer.getYoutube()) {
                    youTubePlayer.cueVideo(mTrailer.getYoutube().get(0).getSource());//not auto play
                }
            }
        }


    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    private void getTrailer() {
        TrailerRequest trailerRequest = new TrailerRequest();
        trailerRequest.setId(mMovie.getId());
        trailerRequest.setApiKey(Contant.API_KEY);
        TrailerService trailerService = new TrailerService();
        trailerService.setRequest(trailerRequest);
        trailerService.request(DetailMovieActivity.this, new CommonInterface.ModelResponse<Trailer>() {
            @Override
            public void onSuccess(Trailer result) {
                if (null != result) {
                    mTrailer = result;
                    youTubePlayerView.initialize(Contant.APP_YOUTUBE_KEY, DetailMovieActivity.this);
                } else {
                    //TODO: do some thing here
                }
            }

            @Override
            public void onFail() {
                //TODO: do some thing here
            }
        });

    }
}
