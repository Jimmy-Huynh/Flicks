package com.tvnsoftware.flicks.adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    boolean[] animationStates;

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
        animationStates = new boolean[getCount()];
        final Movie movie = getItem(position);
        ViewHolder viewHolder;
        if (null == convertView) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
            if (!animationStates[position]) {

                animationStates[position] = true;
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
                animation.setStartOffset(position * 500);
                convertView.startAnimation(animation);
            }
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        int orientation = getContext().getResources().getConfiguration().orientation;

        if (movie.getVoteAverage() >= 5) {
            Glide.with(getContext()).load(movie.getBackdropPath())
                    .placeholder(R.drawable.badge_none)
                    .bitmapTransform(new RoundedCornersTransformation(getContext(), 8, 0))
                    .into(viewHolder.ivMovie);
            viewHolder.ivIconOver.setVisibility(View.VISIBLE);
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                viewHolder.layoutRight.setVisibility(View.GONE);
            } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                viewHolder.layoutRight.setVisibility(View.VISIBLE);
            }
        } else {
            viewHolder.ivIconOver.setVisibility(View.GONE);
            Glide.with(getContext()).load(movie.getPosterPath())
                    .placeholder(R.drawable.badge_none)
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
