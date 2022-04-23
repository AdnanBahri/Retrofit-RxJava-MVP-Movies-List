package com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}