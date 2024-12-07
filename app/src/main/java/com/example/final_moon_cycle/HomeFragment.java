package com.example.final_moon_cycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.view.LayoutInflater;
import android.widget.TextView;
import androidx.cardview.widget.CardView;

public class HomeFragment extends Fragment {

    private CalendarView calendarView;
    private Button button;
    private DatabaseReference databaseReference;
    private LinearLayout logsContainer;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize Firebase
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        databaseReference = FirebaseDatabase.getInstance().getReference("Calendar");

        // Initialize the CalendarView, Button, and logs container
        calendarView = rootView.findViewById(R.id.calendarView);
        button = rootView.findViewById(R.id.button);
        logsContainer = rootView.findViewById(R.id.logsContainer);  // Initialize the logs container

        // Set an onClickListener for the button
        button.setOnClickListener(v -> {
            // Get the selected date from the calendar view (in milliseconds)
            long selectedDate = calendarView.getDate();

            // Convert the selected date to a string in dd-MM-yyyy format
            String dateString = convertDateToString(selectedDate);

            // Save the selected date to Firebase
            saveDateToFirebase(dateString);
        });

        // Fetch and display logs
        fetchLogsFromFirebase();

        return rootView;
    }

    // Method to convert the selected date to a formatted string (dd-MM-yyyy)
    private String convertDateToString(long selectedDate) {
        // Create a SimpleDateFormat with the desired date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        // Convert the selected date (milliseconds) to a Date object
        Date date = new Date(selectedDate);

        // Format the date to the desired format
        return dateFormat.format(date);
    }

    // Method to save the selected date to Firebase
    private void saveDateToFirebase(String dateString) {
        // Create a map to store the date
        Map<String, String> dateMap = new HashMap<>();
        dateMap.put("selected_date", dateString);

        // Save the date to Firebase under the "Calendar" node
        databaseReference.push().setValue(dateMap)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getActivity(), "Date saved successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Failed to save date", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Method to fetch and display logs from Firebase
    private void fetchLogsFromFirebase() {
        // Attach a listener to fetch data from Firebase
        databaseReference.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Clear the existing logs before adding new ones
                logsContainer.removeAllViews();

                // Iterate through each log in the Firebase database
                for (DataSnapshot snapshot : task.getResult().getChildren()) {
                    String date = snapshot.child("selected_date").getValue(String.class);
                    if (date != null) {
                        addLogToContainer(date);
                    }
                }
            } else {
                Toast.makeText(getActivity(), "Failed to load logs", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to dynamically add a log item to the container
    private void addLogToContainer(String date) {
        // Create a new CardView for each log
        CardView logCard = new CardView(getActivity());
        logCard.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        logCard.setCardBackgroundColor(getResources().getColor(R.color.white));
        logCard.setCardElevation(8f);
        logCard.setCardElevation(4f);

        // Create a TextView to display the log date
        TextView logTextView = new TextView(getActivity());
        logTextView.setText(date);
        logTextView.setTextSize(22f);
        logTextView.setTextColor(getResources().getColor(R.color.pink_700));
        logTextView.setPadding(16, 16, 16, 16);

        // Add the TextView to the CardView
        logCard.addView(logTextView);

        // Add the CardView to the logs container
        logsContainer.addView(logCard);
    }
}
