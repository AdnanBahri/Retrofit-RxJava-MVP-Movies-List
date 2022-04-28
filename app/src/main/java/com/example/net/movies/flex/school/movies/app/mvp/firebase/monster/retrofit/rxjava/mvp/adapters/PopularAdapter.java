package com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.adapters;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.R;
import com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.contract.MainContractor;
import com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.databinding.MovieItemLayoutBinding;
import com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.models.Movie;
import com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> implements Filterable {

    private List<Movie> movies;
    private List<Movie> filteredMovies;
    private int oldSize;
    private MainContractor.View listener;


    public PopularAdapter(MainContractor.View listener) {
        this.listener = listener;
        movies = new ArrayList<>();
        filteredMovies = new ArrayList<>();
    }

    public void setMovies(List<Movie> movies) {
        oldSize = this.movies.size();
        this.movies.addAll(movies);
        notifyItemRangeInserted(oldSize, this.movies.size());
        filteredMovies = this.movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieItemLayoutBinding binding = MovieItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(filteredMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return filteredMovies.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String key = charSequence.toString();
                if (key.isEmpty() || key.trim().isEmpty())
                    filteredMovies = movies;
                else {
                    List<Movie> temp = new ArrayList<>();
                    for (Movie movie : movies) {
                        if (movie.getOriginalTitle().toLowerCase().contains(key))
                            temp.add(movie);
                    }
                    filteredMovies = temp;
                }
                FilterResults results = new FilterResults();
                results.values = filteredMovies;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredMovies = (List<Movie>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        MovieItemLayoutBinding binding;

        public ViewHolder(@NonNull MovieItemLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bindView(Movie movie) {
            binding.movieCard.setAnimation(AnimationUtils.loadAnimation(binding.getRoot().getContext(), R.anim.fade_in_anim));
            binding.movieCard.setOnClickListener(v -> listener.navigate(movie));
            binding.movieName.setText(movie.getTitle());
            binding.ratingBar.setRating((float) movie.getVoteAverage() / 2);
            Glide
                    .with(binding.getRoot().getContext())
                    .load(Constants.IMAGE_BASE_URL + movie.getPosterPath())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            binding.producer.setVisibility(View.GONE);
                            binding.moviePoster.setImageResource(R.drawable.ic_launcher_background);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            binding.progress.setVisibility(View.GONE);
                            binding.moviePoster.setImageResource(R.drawable.ic_launcher_background);
                            return false;
                        }
                    })
                    .into(binding.moviePoster);
            binding.rating.setText(String.valueOf(movie.getVoteAverage()));
            binding.releaseDate.setText(movie.getReleaseDate());
            //binding.movieCard.setOnClickListener(v-> Toast.makeText(binding.getRoot().getContext(), ""+movie.getReleaseDate(), Toast.LENGTH_SHORT).show());
        }
    }
}
