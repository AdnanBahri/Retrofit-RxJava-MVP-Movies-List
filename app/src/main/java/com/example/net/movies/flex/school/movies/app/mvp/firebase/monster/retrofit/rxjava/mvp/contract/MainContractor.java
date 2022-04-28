package com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.contract;

import com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.models.Movie;
import com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.models.MoviesResponse;

import io.reactivex.rxjava3.core.Flowable;

public interface MainContractor {

    interface View {
        void showProgress();

        void hideProgress();

        void showLoadMoreProgress();

        void hideLoadMoreProgress();

        void fetchData();

        void search();

        void navigate(Movie movie);
    }

    interface Presenter {
        Flowable<MoviesResponse> getMovies(String api_key, int page);
    }
}
