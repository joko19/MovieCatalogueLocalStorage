package com.example.moviecataloguelocalstorage.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviecataloguelocalstorage.R;
import com.example.moviecataloguelocalstorage.adapter.MovieAdapter;
import com.example.moviecataloguelocalstorage.db.MovieDB;
import com.example.moviecataloguelocalstorage.model.Movie;

import java.util.ArrayList;

public class MovieFavourit extends Fragment {
    View v;
    private RecyclerView myrecyclerview;
    private ArrayList<Movie> movies;
    private MovieDB movieDB;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_movie, container, false);
        loadMovie();
        myrecyclerview = v.findViewById(R.id.rv_movies);
        MovieAdapter movieadapter = new MovieAdapter(getContext(), movies);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(movieadapter);

        progressBar = v.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        return v;
    }

    public void loadMovie(){
            movieDB = new MovieDB(getContext());
            SQLiteDatabase readData = movieDB.getReadableDatabase();
            Cursor cursor = readData.rawQuery("SELECT * FROM " + MovieDB.MyColums.tableName, null);
            cursor.moveToFirst();
            movies = new ArrayList<>();
            for (int i=0; i<cursor.getCount(); i++){
                cursor.moveToPosition(i);
                Movie movie = new Movie();
                movie.setTitle(cursor.getString(1));
                movie.setRating(cursor.getString(2));
                movie.setPopularity(cursor.getString(3));
                movie.setRelease(cursor.getString(4));
                movie.setOverview(cursor.getString(5));
                movie.setPoster(cursor.getString(6));
                movie.setBackdrop(cursor.getString(7));
                movies.add(movie);
            }
        }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

