package com.tvnsoftware.flicks.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tvnsoftware.flicks.R;
import com.tvnsoftware.flicks.api.model.Movie;

import java.util.ArrayList;

/**
 * Created by TamHH on 6/15/2017.
 */

public class MoviesAdapter extends ArrayAdapter<Movie> {

    private MoviesListener mListener;

    public interface MoviesListener {
        public void onItemClick(Movie movie);
    }

    public MoviesAdapter(Context context, MoviesListener listener) {
        super(context, 0);
        mListener = listener;
    }

    private static class ViewHolder {
        ImageView ivMovie;
        TextView tvTitle, tvContent;
        LinearLayout layoutItemView, layoutRight;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Movie movie = getItem(position);
        ViewHolder viewHolder;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
            viewHolder.ivMovie = (ImageView) convertView.findViewById(R.id.iv_movie);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
            viewHolder.layoutItemView = (LinearLayout) convertView.findViewById(R.id.layout_item_movie);
            viewHolder.layoutRight = (LinearLayout) convertView.findViewById(R.id.layout_description);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (movie.getVoteAverage() >= 5) {
            Glide.with(getContext()).load(movie.getBackdropPath()).into(viewHolder.ivMovie);
            viewHolder.layoutRight.setVisibility(View.GONE);
        } else {
            Glide.with(getContext()).load(movie.getPosterPath()).into(viewHolder.ivMovie);
            viewHolder.layoutRight.setVisibility(View.VISIBLE);
        }
        viewHolder.tvTitle.setText(movie.getTitle());
        viewHolder.tvContent.setText(movie.getOverview());
        viewHolder.layoutItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(movie);
            }
        });
        return convertView;
    }

}
