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

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

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

    static class ViewHolder {
        @BindView(R.id.iv_movie)
        ImageView ivMovie;
        @BindView(R.id.iv_icon_over)
        ImageView ivIconOver;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.layout_item_movie)
        LinearLayout layoutItemView;
        @BindView(R.id.layout_description)
        LinearLayout layoutRight;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Movie movie = getItem(position);
        ViewHolder viewHolder;
        if (null == convertView) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (movie.getVoteAverage() >= 5) {
            Glide.with(getContext()).load(movie.getBackdropPath())
                    .bitmapTransform(new RoundedCornersTransformation(getContext(), 8, 0))
                    .into(viewHolder.ivMovie);
            viewHolder.ivIconOver.setVisibility(View.VISIBLE);
            viewHolder.layoutRight.setVisibility(View.GONE);
        } else {
            viewHolder.ivIconOver.setVisibility(View.GONE);
            Glide.with(getContext()).load(movie.getPosterPath())
                    .bitmapTransform(new RoundedCornersTransformation(getContext(), 8, 0))
                    .into(viewHolder.ivMovie);
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
        viewHolder.ivIconOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(movie);
            }
        });
        return convertView;
    }

}
