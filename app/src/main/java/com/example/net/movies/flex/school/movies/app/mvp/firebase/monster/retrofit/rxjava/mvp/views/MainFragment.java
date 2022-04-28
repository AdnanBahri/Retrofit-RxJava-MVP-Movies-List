package com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.views;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.adapters.PopularAdapter;
import com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.contract.MainContractor;
import com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.databinding.FragmentMainBinding;
import com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.models.Movie;
import com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.presenters.MainPresenter;
import com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.utils.Constants;


public class MainFragment extends Fragment implements MainContractor.View {

    private FragmentMainBinding binding;
    private MainPresenter presenter;
    private PopularAdapter adapter;
    private int totalAvailablePages = 0;
    private int currentPage = 1;
    private boolean canLoadMore = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (binding == null)
            binding = FragmentMainBinding.inflate(inflater, container, false);
        presenter = new MainPresenter(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupUI();
        fetchData();
        search();
    }

    private void setupUI() {
        adapter = new PopularAdapter(this);
        binding.recycler.setHasFixedSize(true);
        binding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recycler.setAdapter(adapter);
        binding.recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!binding.recycler.canScrollVertically(1) && canLoadMore) {
                    if (currentPage <= totalAvailablePages) {
                        currentPage++;
                        fetchData();
                    }
                }
            }
        });
    }

    @Override
    public void showProgress() {
        binding.mainProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        binding.mainProgress.setVisibility(View.GONE);
    }

    @Override
    public void showLoadMoreProgress() {
        binding.progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadMoreProgress() {
        binding.progress.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void fetchData() {
        if (currentPage == 1)
            showProgress();
        else {
            showLoadMoreProgress();
        }
        presenter.getMovies(Constants.API_KEY, currentPage)
                .subscribe(moviesResponse -> {
                    try {
                        if (currentPage == 1) {
                            hideProgress();
                            totalAvailablePages = moviesResponse.getTotalPages();
                        } else
                            hideLoadMoreProgress();
                        adapter.setMovies(moviesResponse.getMovies());
                    } catch (Exception e) {
                        Log.e("Error MSG", e.getMessage());
                    }
                });
    }

    @Override
    public void search() {
        binding.searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!binding.searchInput.getText().toString().trim().isEmpty())
                    adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                canLoadMore = binding.searchInput.getText().toString().trim().isEmpty();
            }
        });
    }

    @Override
    public void navigate(Movie movie) {

        Toast.makeText(getContext(), ""+movie.getTitle(), Toast.LENGTH_SHORT).show();
    }
}