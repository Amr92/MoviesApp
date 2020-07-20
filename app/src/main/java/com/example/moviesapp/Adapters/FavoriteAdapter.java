package com.example.moviesapp.Adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteVH> {

    
    @NonNull
    @Override
    public FavoriteVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class FavoriteVH extends RecyclerView.ViewHolder{

        public FavoriteVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
