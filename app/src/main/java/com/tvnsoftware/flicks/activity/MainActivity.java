package com.tvnsoftware.flicks.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.tvnsoftware.flicks.R;
import com.tvnsoftware.flicks.adapter.MoviesAdapter;
import com.tvnsoftware.flicks.api.model.Movie;
import com.tvnsoftware.flicks.api.model.NowPlaying;
import com.tvnsoftware.flicks.api.model.NowPlayingRequest;
import com.tvnsoftware.flicks.api.restmanager.CommonInterface;
import com.tvnsoftware.flicks.api.restmanager.RetrofitManager;
import com.tvnsoftware.flicks.api.restservice.NowPlayingService;
import com.tvnsoftware.flicks.utils.Contant;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private ListView mLvMovies;
    private final String TAG = "MainActivity";
    private ArrayList<Movie> mMovies;
    private SwipeRefreshLayout swipeContainer;
    private MoviesAdapter mMoviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RetrofitManager.getInstance().config(getApplicationContext());

        mLvMovies = (ListView) findViewById(R.id.movie_list);

        mMoviesAdapter = new MoviesAdapter(MainActivity.this, new MoviesAdapter.MoviesListener() {
            @Override
            public void onItemClick(Movie movie) {
                Intent i = new Intent(MainActivity.this, DetailMovieActivity.class);
                i.putExtra(Contant.MOVIE_OBJECT, movie);
                startActivity(i);
            }
        });
        mLvMovies.setAdapter(mMoviesAdapter);

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMovie();
            }
        });
        swipeContainer.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.colorAccent,
                R.color.colorPrimaryLight);
        getMovie();
    }

    @Override
    public void onClick(View v) {

    }

    private void getMovie() {
        NowPlayingRequest nowPlayingRequest = new NowPlayingRequest();
        nowPlayingRequest.setApiKey(Contant.API_KEY);
        NowPlayingService nowPlayingService = new NowPlayingService();
        nowPlayingService.setRequest(nowPlayingRequest);
        nowPlayingService.request(MainActivity.this, new CommonInterface.ModelResponse<NowPlaying>() {
            @Override
            public void onSuccess(NowPlaying result) {
                if (null != result) {
                    mMovies = new ArrayList<>(result.getMovies());
                    mMoviesAdapter.clear();
                    mMoviesAdapter.addAll(mMovies);
                    Log.d(TAG, result.getMovies().size() + "");
                } else {
                    Log.d(TAG, "response is NULL");
                }
                swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFail() {
                swipeContainer.setRefreshing(false);
                Log.d(TAG, "response is FAILURE");
            }
        });
    }
}
