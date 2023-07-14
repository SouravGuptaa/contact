package com.example.contacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;


public class NameFileAdapter extends RecyclerView.Adapter<NameFileAdapter.ProfileHolder> {

    LinkedList<Integer> list;

     public NameFileAdapter(LinkedList<Integer> ksdfg) {
        list = ksdfg;
    }

    @NonNull
    @Override
    public ProfileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.file_name_adapter,parent,false);
        return new ProfileHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileHolder bolder, int position) {
        bolder.tvName.setText(list.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ProfileHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvDetail;
        public ProfileHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name);
            tvDetail = itemView.findViewById(R.id.detail);

        }
    }
}
