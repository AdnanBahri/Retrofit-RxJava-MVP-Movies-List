package com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.presenters;

import com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.contract.MainContractor;
import com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.models.MoviesResponse;
import com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.network.RetrofitClient;
import com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.network.TMDBApi;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainPresenter implements MainContractor.Presenter{

    private MainContractor.View listener;

    private TMDBApi api;
    public MainPresenter(MainContractor.View listener){
        api=RetrofitClient.getInstance().create(TMDBApi.class);
        this.listener=listener;
    }

    @Override
    public Flowable<MoviesResponse> getMovies(String api_key,int page){
        return api.getMovies(api_key,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
