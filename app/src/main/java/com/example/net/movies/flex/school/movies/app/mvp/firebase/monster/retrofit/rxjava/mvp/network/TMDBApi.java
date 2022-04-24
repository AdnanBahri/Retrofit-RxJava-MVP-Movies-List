package com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.network;

import com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.models.MoviesResponse;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TMDBApi {

    @GET("movie/popular")
    Flowable<MoviesResponse> getMovies(
            @Query("api_key") String api_key,
            @Query("page") int page
    );
}
