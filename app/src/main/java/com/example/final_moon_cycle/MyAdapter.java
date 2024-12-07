package com.example.final_moon_cycle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<DataClass>dataList;

    public void setSearchList(List<DataClass> dataSearchList) {
        this.dataList = dataSearchList;
        notifyDataSetChanged();
    }

    public MyAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.recImage.setImageResource(dataList.get(position).getDataImage());
        holder.recTitle.setText(dataList.get(position).getDataTitle());
        holder.recDesc.setText(dataList.get(position).getDataDesc());
        holder.recLang.setText(dataList.get(position).getDataLang());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Membuka DetailFragment melalui FragmentTransaction
                DetailFragment detailFragment = new DetailFragment();

                // Membuat Bundle untuk mengirim data ke fragment
                Bundle bundle = new Bundle();
                bundle.putInt("Image", dataList.get(holder.getAdapterPosition()).getDataImage());
                bundle.putString("Title", dataList.get(holder.getAdapterPosition()).getDataTitle());
                bundle.putInt("Desc", dataList.get(holder.getAdapterPosition()).getDataDesc());  // Pastikan ini int, bukan string

                // Menambahkan bundle ke DetailFragment
                detailFragment.setArguments(bundle);

                // Membuka fragment di dalam activity yang ada
                androidx.fragment.app.FragmentTransaction transaction =
                        ((androidx.appcompat.app.AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, detailFragment); // Pastikan ada container fragment di layout
                transaction.addToBackStack(null); // Menambahkan fragment ke back stack
                transaction.commit(); // Melakukan transaksi
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{
    ImageView recImage;
    TextView recTitle, recDesc, recLang;
    CardView recCard;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        recImage = itemView.findViewById(R.id.recImage);
        recTitle = itemView.findViewById(R.id.recTitle);
        recDesc = itemView.findViewById(R.id.recDesc);
        recLang = itemView.findViewById(R.id.recLang);
        recCard = itemView.findViewById(R.id.recCard);
    }
}
