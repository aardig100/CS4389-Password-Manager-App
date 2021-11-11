package com.example.passwordmanager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class MainBody extends AppCompatActivity {
    String []data = {"test1","t3","test2"};
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        List<String> items = new LinkedList<>();
        items.add("Code it");

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ThirdActivity adapter = new ThirdActivity(items);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.add).setOnClickListener(view -> {
            items.add(data[counter%3]);
            counter++;
            adapter.notifyItemInserted(items.size()-1);
        });

    }
}
