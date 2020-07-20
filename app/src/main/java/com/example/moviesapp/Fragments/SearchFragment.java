package com.example.moviesapp.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.moviesapp.Adapters.MoviesSearchAdapter;
import com.example.moviesapp.Adapters.PersonsSearchAdapter;
import com.example.moviesapp.Adapters.TvSeriesSearchAdapter;
import com.example.moviesapp.Classes.SearchPresenter;
import com.example.moviesapp.Classes.SearchView;
import com.example.moviesapp.Models.MoviesSearchModel;
import com.example.moviesapp.Models.PersonsSearchModel;
import com.example.moviesapp.Models.TvSeriesSearchModel;
import com.example.moviesapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.angmarch.views.NiceSpinner;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import io.paperdb.Paper;

public class SearchFragment extends Fragment implements SearchView {

    private NiceSpinner sourceSpinner;
    private AppCompatEditText queryEditText;
    private AppCompatButton querySearchButton;
    private RecyclerView resultsRecyclerView;
    private MoviesSearchAdapter moviesSearchAdapter;
    private PersonsSearchAdapter personsSearchAdapter;
    private TvSeriesSearchAdapter tvSeriesSearchAdapter;
    private SearchPresenter presenter;

    private String movie = "By Movie Title";
    private String person = "By Person Name";
    private String tv = "By Tv Series Title";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        sourceSpinner = view.findViewById(R.id.source_spinner);
        queryEditText = view.findViewById(R.id.query_edit_text);
        querySearchButton = view.findViewById(R.id.query_search_button);

        resultsRecyclerView = view.findViewById(R.id.results_recycler_view);
        resultsRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity(),LinearLayoutManager.VERTICAL,false));

        presenter = new SearchPresenter(this);

        Paper.init(this.getActivity());

        final ArrayList<String> category = new ArrayList<>();

        category.add(movie);
        category.add(tv);
        category.add(person);

        sourceSpinner.attachDataSource(category);

        if(Paper.book().read("position") != null){
            int position = Paper.book().read("position");
            sourceSpinner.setSelectedIndex(position);
        }

        int position = sourceSpinner.getSelectedIndex();
        if(position == 0){
            queryEditText.setHint("Enter any Movie title..");
        }else if(position == 1){
            queryEditText.setHint("Enter any Tv Series title..");
        }
        else if(position == 2) {
            queryEditText.setHint("Enter any Person name..");
        }

        sourceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0){
                    queryEditText.setHint("Enter any Movie title..");
                }else if(position == 1){
                    queryEditText.setHint("Enter any Tv Series title..");
                }
                else if(position == 2) {
                    queryEditText.setHint("Enter any Person name..");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        querySearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(queryEditText.getText() != null){
                    String query = queryEditText.getText().toString();

                    if(query.equals("") || query.equals(" ")){
                        Toast.makeText(getActivity(), "Please Enter any Text...", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        queryEditText.setText("");

                        String finalQuery = query.replaceAll(" ","+");

                        if(category.size() > 0){
                            String categoryName = category.get(sourceSpinner.getSelectedIndex());

                            if(categoryName.equals(movie)){
                                presenter.getMoviesSearchResult(finalQuery);
                            }
                            else if(categoryName.equals(tv)){
                                presenter.getTvSeriesSearchResult(finalQuery);
                            }
                            else if (categoryName.equals(person)){
                                presenter.getPersonsSearchResult(finalQuery);
                            }
                        }
                    }
                }
            }
        });
        return view;
    }

    @Override
    public void onGetSearchMovies(List<MoviesSearchModel.ResultsBean> sMoviesList) {

        moviesSearchAdapter = new MoviesSearchAdapter(sMoviesList,getActivity());
        resultsRecyclerView.setAdapter(moviesSearchAdapter);
        moviesSearchAdapter.notifyDataSetChanged();

        Paper.book().write("cache",new Gson().toJson(sMoviesList));
        Paper.book().write("source","movie");
    }

    @Override
    public void onGetSearchPersons(List<PersonsSearchModel.ResultsBean> sPersonsList) {

        personsSearchAdapter = new PersonsSearchAdapter(sPersonsList,getActivity());
        resultsRecyclerView.setAdapter(personsSearchAdapter);
        personsSearchAdapter.notifyDataSetChanged();

        Paper.book().write("cache",new Gson().toJson(sPersonsList));
        Paper.book().write("source","person");
    }

    @Override
    public void onGetSearchTvSeries(List<TvSeriesSearchModel.ResultsBean> sTvList) {

        tvSeriesSearchAdapter = new TvSeriesSearchAdapter(sTvList,getActivity());
        resultsRecyclerView.setAdapter(tvSeriesSearchAdapter);
        tvSeriesSearchAdapter.notifyDataSetChanged();

        Paper.book().write("cache",new Gson().toJson(sTvList));
        Paper.book().write("source","tv");
    }

    @Override
    public void onStop() {
        super.onStop();

        Paper.book().write("position",sourceSpinner.getSelectedIndex());
    }
}



/* if(Paper.book().read("cache") != null){

            String results = Paper.book().read("cache");

            if(Paper.book().read("source") != null){

                String source = Paper.book().read("source");

                if(source.equals("movie")){

                    MoviesSearchModel moviesSearchModel = new Gson().fromJson(results,MoviesSearchModel.class);
                }
                else{

                    PersonsSearchModel personsSearchModel = new Gson().fromJson(results,PersonsSearchModel.class);
                }
            }
        }*/
