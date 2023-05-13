package com.example.passwords;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PasswordAdapter extends RecyclerView.Adapter<PasswordAdapter.ViewHolder>{
    private ArrayList<Password> passwords;

    public PasswordAdapter(ArrayList<Password> passwords) {
        this.passwords = passwords;
    }

    @NonNull
    @Override
    public PasswordAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_password, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PasswordAdapter.ViewHolder holder, int position) {
        Password password = passwords.get(position);
        holder.password.setText(password.getTitle());
    }

    @Override
    public int getItemCount() {
        return passwords.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView password;
        ViewHolder(View view){
            super(view);
            password = itemView.findViewById(R.id.single_password);
        }
    }
}
