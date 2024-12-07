package com.example.final_moon_cycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {

    TextView detailDesc, detailTitle;
    ImageView detailImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment's layout
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        // Initialize the views
        detailDesc = rootView.findViewById(R.id.detailDesc);
        detailTitle = rootView.findViewById(R.id.detailTitle);
        detailImage = rootView.findViewById(R.id.detailImage);

        // Get the arguments from the bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            // Set the data in the views
            detailDesc.setText(getString(bundle.getInt("Desc"))); // Get the string resource ID
            detailImage.setImageResource(bundle.getInt("Image"));
            detailTitle.setText(bundle.getString("Title"));
        }

        return rootView;
    }
}
