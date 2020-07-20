package com.example.moviesapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.moviesapp.Models.NowPlayingMoviesModel;
import com.example.moviesapp.R;

import java.util.List;

public class SlidePagerAdapter extends PagerAdapter {

    private Context context;
    private List<NowPlayingMoviesModel.ResultsBean> slides;

    public SlidePagerAdapter(Context context, List<NowPlayingMoviesModel.ResultsBean> slides) {
        this.context = context;
        this.slides = slides;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = inflater.inflate(R.layout.slide_item,container,false);
        ImageView slideImage = slideLayout.findViewById(R.id.slide_img);
        TextView slideText = slideLayout.findViewById(R.id.slide_title);
        Glide.with(context).load("https://image.tmdb.org/t/p/w500" + slides.get(position).getPoster_path()).into(slideImage);
        slideText.setText(slides.get(position).getOriginal_title());
        container.addView(slideLayout);
        return slideLayout;
    }

    @Override
    public int getCount() {
        return slides.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
