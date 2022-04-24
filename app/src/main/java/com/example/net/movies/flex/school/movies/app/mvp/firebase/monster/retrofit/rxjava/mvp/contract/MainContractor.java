package com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.contract;

import com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.models.MoviesResponse;

import io.reactivex.rxjava3.core.Flowable;

public interface MainContractor {

    interface View {
        void showProgress();

        void hideProgress();

        void showLoadMoreProgress();

        void hideLoadMoreProgress();

        void fetchData();
    }

    interface Presenter {
        Flowable<MoviesResponse> getMovies(String api_key, int page);
    }
}
