package com.example.moviesapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.Models.PopularMoviesTrailerModel;
import com.example.moviesapp.Models.PopularTvTrailerModel;
import com.example.moviesapp.R;

import java.util.List;

public class PopularTvTrailerAdapter extends RecyclerView.Adapter<PopularTvTrailerAdapter.TrailerViewHolder> {

    private List<PopularTvTrailerModel.ResultsBean> trailerList;
    private Context context;

    public PopularTvTrailerAdapter(List<PopularTvTrailerModel.ResultsBean> trailerList, Context context) {
        this.trailerList = trailerList;
        this.context = context;
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_item_rec,parent,false);
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {

        holder.trailerTitle.setText(trailerList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return trailerList.size();
    }

    public class TrailerViewHolder extends RecyclerView.ViewHolder{

        TextView trailerTitle;
        ImageView thumbnail;

        public TrailerViewHolder(@NonNull View itemView) {
            super(itemView);

            trailerTitle = itemView.findViewById(R.id.video_title);
            thumbnail = itemView.findViewById(R.id.video_image_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        PopularTvTrailerModel.ResultsBean model = trailerList.get(position);

                        String videoId = model.getKey();
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoId));
                        intent.putExtra("VIDEO_ID",videoId);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
