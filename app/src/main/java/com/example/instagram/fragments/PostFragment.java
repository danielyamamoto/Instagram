package com.example.instagram.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.instagram.databinding.FragmentPostBinding;

public class PostFragment extends Fragment {

    private FragmentPostBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPostBinding.inflate(getLayoutInflater(), container, false);
        // Layout of activity is stored in a special property called root
        View view = binding.getRoot();
        return  view;
    }
}