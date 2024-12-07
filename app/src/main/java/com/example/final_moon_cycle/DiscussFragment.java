package com.example.final_moon_cycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DiscussFragment extends Fragment {

    FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment layout
        View rootView = inflater.inflate(R.layout.fragment_discuss, container, false);

        // Apply edge-to-edge window insets for better UI appearance
        ViewCompat.setOnApplyWindowInsetsListener(rootView.findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the FloatingActionButton
        fab = rootView.findViewById(R.id.fab);

        // Set onClickListener for the FloatingActionButton
        fab.setOnClickListener(view -> {
            // Launch the UploadActivity when the FAB is clicked
            Intent intent = new Intent(getActivity(), UploadActivity.class);
            startActivity(intent);
        });

        return rootView;  // Return the root view of the fragment
    }
}
