package com.example.passwords;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PasswordListActivity extends AppCompatActivity {

    SQLiteDatabase database;
    PasswordDatabaseHelper databaseHelper;
    PasswordAdapter adapter;
    RecyclerView recyclerView;
    Cursor cursor;
    private ArrayList<Password> passwords = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_list);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        databaseHelper = new PasswordDatabaseHelper(this);
        database = databaseHelper.getReadableDatabase();

        passwords.clear();
        cursor = database.query(PasswordDatabaseHelper.TABLE, null, null, null, null, null, null);

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(PasswordDatabaseHelper._ID));
            String website_string = cursor.getString(cursor.getColumnIndexOrThrow(PasswordDatabaseHelper.COLUMN_WEBSITE));
            String username_string = cursor.getString(cursor.getColumnIndexOrThrow(PasswordDatabaseHelper.COLUMN_USERNAME));
            String password_string = cursor.getString(cursor.getColumnIndexOrThrow(PasswordDatabaseHelper.COLUMN_PASSWORD));

            Password password = new Password(id, website_string, username_string, password_string);
            passwords.add(password);
        }

        Button clearButton = findViewById(R.id.clear_button);
        EditText search = findViewById(R.id.search_text);

        if (passwords.isEmpty()) {
            TextView noPasswords = findViewById(R.id.no_passwords);
            recyclerView.setVisibility(View.GONE);
            clearButton.setVisibility(View.GONE);
            search.setVisibility(View.GONE);
            noPasswords.setVisibility(View.VISIBLE);
            return;
        }

        clearButton.setVisibility(View.VISIBLE);

        adapter = new PasswordAdapter(passwords);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        database.close();
        cursor.close();
    }

    public void back(View view) {
        finish();
    }

    public void clear(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete all the passwords?");
        builder.setTitle("Passwords deletion");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseHelper.deleteAllEntries(database);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        builder.setCancelable(false);
        builder.create();
        builder.show();
    }
}