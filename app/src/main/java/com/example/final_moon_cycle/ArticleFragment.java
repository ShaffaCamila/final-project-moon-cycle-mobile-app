package com.example.final_moon_cycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ArticleFragment extends Fragment {

    RecyclerView recyclerView;
    List<DataClass> dataList;
    MyAdapter adapter;
    DataClass androidData;
    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_article, container, false);

        // Initialize views
        recyclerView = rootView.findViewById(R.id.recyclerView);
        searchView = rootView.findViewById(R.id.search);
        searchView.clearFocus();

        // Set up the search view listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });

        // Set up RecyclerView
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Initialize the data list
        dataList = new ArrayList<>();
        androidData = new DataClass("Menstruasi", R.string.article01, "Indonesia", R.drawable.article1);
        dataList.add(androidData);
        androidData = new DataClass("Ini Perbedaan Siklus Menstruasi yang Normal dan Tidak", R.string.article02, "Indonesia", R.drawable.article2);
        dataList.add(androidData);
        androidData = new DataClass("Menstruasi (Haid) - Siklus Bulanan yang Dialami Wanita", R.string.article03, "Indonesia", R.drawable.article3);
        dataList.add(androidData);
        androidData = new DataClass("Mengulik Fakta dan Mitos tentang Haid yang Perlu Diketahui", R.string.article04, "Indonesia", R.drawable.article4);
        dataList.add(androidData);
        androidData = new DataClass("7 Penyebab Haid Tidak Teratur yang Harus Anda Ketahui", R.string.article05, "Java", R.drawable.article5);
        dataList.add(androidData);

        // Set the adapter for RecyclerView
        adapter = new MyAdapter(getActivity(), dataList);
        recyclerView.setAdapter(adapter);

        return rootView; // Return the inflated view
    }

    private void searchList(String text) {
        List<DataClass> dataSearchList = new ArrayList<>();
        for (DataClass data : dataList) {
            if (data.getDataTitle().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }
        if (dataSearchList.isEmpty()) {
            Toast.makeText(getContext(), "Not Found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setSearchList(dataSearchList);
        }
    }
}
