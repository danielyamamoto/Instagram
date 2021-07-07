package com.example.instagram.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.instagram.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Binding library
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        // Layout of activity is stored in a special property called root
        View view = binding.getRoot();
        setContentView(view);
    }
}