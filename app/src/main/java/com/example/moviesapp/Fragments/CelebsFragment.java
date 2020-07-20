package com.example.moviesapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.moviesapp.Adapters.CelebsSlidePagerAdapter;
import com.example.moviesapp.Adapters.MoreCelebsAdapter;
import com.example.moviesapp.Adapters.PopularCelebsAdapter;
import com.example.moviesapp.Classes.PopularCelebsPresenter;
import com.example.moviesapp.Classes.PopularCelebsView;
import com.example.moviesapp.Models.PersonsSearchModel;
import com.example.moviesapp.Models.PopularCelebsModel;
import com.example.moviesapp.R;
import com.example.moviesapp.ui.MoreCelebs;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class CelebsFragment extends Fragment implements PopularCelebsView {


    private ViewPager sliderPagerCelebs;
    private TabLayout indicatorCelebs;
    private RecyclerView popularCelebsRv;
    private ProgressBar popularCelebsProgressBar;
    private PopularCelebsPresenter presenter;
    private PopularCelebsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_celebs, container, false);

        sliderPagerCelebs = view.findViewById(R.id.slider_pager_celebs);
        indicatorCelebs = view.findViewById(R.id.indicator_celebs);

        popularCelebsRv = view.findViewById(R.id.popular_celebs_rv);
        popularCelebsRv.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        adapter = new PopularCelebsAdapter(this.getActivity());

        popularCelebsProgressBar = view.findViewById(R.id.popular_celebs_progress_bar);

        presenter = new PopularCelebsPresenter(this);
        presenter.getPopularCelebs();

        TextView celebsSeeAll = view.findViewById(R.id.celebs_see_all);
        celebsSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MoreCelebs.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void OnGetPopularCelebs(List<PersonsSearchModel.ResultsBean> celebsList) {

        popularCelebsProgressBar.setVisibility(View.GONE);
        CelebsSlidePagerAdapter pagerAdapter = new CelebsSlidePagerAdapter(getActivity(),celebsList);
        sliderPagerCelebs.setAdapter(pagerAdapter);
        indicatorCelebs.setupWithViewPager(sliderPagerCelebs,true);
        adapter.addAll(celebsList);
        popularCelebsRv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
