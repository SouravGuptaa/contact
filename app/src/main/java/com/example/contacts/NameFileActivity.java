package com.example.contacts;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class NameFileActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NameFileAdapter nameFileAdapter;
    LinkedList<Integer> profileList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_name);
        init();
    }

    private void init() {
        initElements();
    }

    private void initElements() {
        recyclerView = findViewById(R.id.namerecyclerview);
        profileList = new LinkedList<>();

        for (int i=0;i<=50;i++){
            profileList.add(i);
        }
        nameFileAdapter = new NameFileAdapter(profileList);
        recyclerView.setLayoutManager(new LinearLayoutManager(NameFileActivity.this));
        recyclerView.setAdapter(nameFileAdapter);



    }
}
