package com.example.passwords;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PasswordAdapter extends RecyclerView.Adapter<PasswordAdapter.ViewHolder> implements Filterable {
    private ArrayList<Password> passwords;
    private ArrayList<Password> passwordsFilter;

    public PasswordAdapter(ArrayList<Password> passwords) {
        this.passwords = passwords;
        this.passwordsFilter = passwords;
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
        holder.website.setText(password.getWebsite());
        holder.username.setText(password.getUsername());
        holder.password.setText(password.getValue());
    }

    @Override
    public int getItemCount() {
        return passwords.size();
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint == null) {
                    filterResults.values = passwords;
                    filterResults.count = passwords.size();
                } else {
                    ArrayList<Password> filteredPasswords = new ArrayList<>();
                    for (Password password:
                            passwordsFilter) {
                        if (password.getValue().toLowerCase().contains(constraint.toString().toLowerCase())) {
                            filteredPasswords.add(password);
                        }
                    }
                    filterResults.values = filteredPasswords;
                    filterResults.count = filteredPasswords.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                passwords = (ArrayList<Password>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView website;
        final TextView username;
        final TextView password;
        ViewHolder(View view){
            super(view);
            website = itemView.findViewById(R.id.single_website);
            username = itemView.findViewById(R.id.single_username);
            password = itemView.findViewById(R.id.single_password);
        }
    }
}
