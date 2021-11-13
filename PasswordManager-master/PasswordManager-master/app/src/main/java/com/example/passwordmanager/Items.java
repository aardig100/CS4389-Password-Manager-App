package com.example.passwordmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Items extends RecyclerView.Adapter<recycler> {

    List<String> items;

    public Items(List<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public recycler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items, parent, false);

        return new recycler(view).linkAdapter(this);
    }


    @Override
    public void onBindViewHolder(@NonNull recycler holder, int position) {
        holder.textView.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class recycler extends RecyclerView.ViewHolder{
    TextView textView;
    TextView textViewPassword;
    private Items adapter;

    public recycler(@NonNull View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.text);
        textViewPassword = itemView.findViewById(R.id.password);
        //generate
        itemView.findViewById(R.id.generate).setOnClickListener(view -> {
            RandomGen randomGen = new RandomGen();
            textViewPassword.setText(randomGen.getPasswdStr());
        });

        //health
        itemView.findViewById(R.id.health).setOnClickListener(view -> {
            adapter.items.remove(getAdapterPosition());
            adapter.notifyItemRemoved(getAdapterPosition());
        });

        //delete
        itemView.findViewById(R.id.delete).setOnClickListener(view -> {
            adapter.items.remove(getAdapterPosition());
            adapter.notifyItemRemoved(getAdapterPosition());
            textViewPassword.setText("password");
        });
    }

    public recycler linkAdapter(Items adapter) {
        this.adapter = adapter;
        return this;
    }
}