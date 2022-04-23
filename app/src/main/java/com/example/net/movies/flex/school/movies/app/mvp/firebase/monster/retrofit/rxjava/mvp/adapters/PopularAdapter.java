package com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.databinding.MovieItemLayoutBinding;
import com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.models.Movie;
import com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    private List<Movie> movies;

    public PopularAdapter() {
        movies = new ArrayList<>();
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieItemLayoutBinding binding = MovieItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        MovieItemLayoutBinding binding;

        public ViewHolder(@NonNull MovieItemLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bindView(Movie movie) {
            binding.movieName.setText(movie.getTitle());
            binding.ratingBar.setRating((float) movie.getVoteAverage()/2);
            Glide
                    .with(binding.getRoot().getContext())
                    .load(Constants.IMAGE_BASE_URL + movie.getPosterPath())
                    .into(binding.moviePoster);
            binding.rating.setText(String.valueOf(movie.getVoteAverage()));
            binding.releaseDate.setText(movie.getReleaseDate());
            //binding.movieCard.setOnClickListener(v-> Toast.makeText(binding.getRoot().getContext(), ""+movie.getReleaseDate(), Toast.LENGTH_SHORT).show());
        }
    }
}
