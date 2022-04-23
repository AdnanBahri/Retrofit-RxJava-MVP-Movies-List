package com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.adapters.PopularAdapter;
import com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.databinding.FragmentMainBinding;
import com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.models.MoviesResponse;
import com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.presenters.MainPresenter;
import com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.utils.Constants;

import org.reactivestreams.Subscription;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.functions.Consumer;


public class MainFragment extends Fragment {

    private FragmentMainBinding binding;
    private MainPresenter presenter;
    private PopularAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        presenter = new MainPresenter();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupUI();
        presenter.getMovies(Constants.API_KEY)
                .subscribe(moviesResponse -> {
                    try {
                        adapter.setMovies(moviesResponse.getMovies());
                    }catch (Exception e){
                        Log.e("Error MSG",e.getMessage());
                    }
                });
                /*.subscribe(new FlowableSubscriber<MoviesResponse>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Subscription s) {

                    }

                    @Override
                    public void onNext(MoviesResponse moviesResponse) {
                        if (moviesResponse.getMovies()!=null)
                            adapter.setMovies(moviesResponse.getMovies());
                        else
                            Toast.makeText(getContext(), "Movies List is Null", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e("On Error",t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(getContext(), "Loading Movies is Completed", Toast.LENGTH_SHORT).show();
                    }
                });*/
    }

    private void setupUI() {
        adapter = new PopularAdapter();
        binding.recycler.setHasFixedSize(true);
        binding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recycler.setAdapter(adapter);
    }
}