package com.example.passwords;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
            String password_string = cursor.getString(cursor.getColumnIndexOrThrow(PasswordDatabaseHelper.COLUMN_PASSWORD));

            Password password = new Password(id, password_string);
            passwords.add(password);
        }

        Button clearButton = findViewById(R.id.clear_button);

        if (passwords.isEmpty()) {
            TextView noPasswords = findViewById(R.id.no_passwords);
            recyclerView.setVisibility(View.GONE);
            clearButton.setVisibility(View.GONE);
            noPasswords.setVisibility(View.VISIBLE);
            return;
        }

        /*passwords.add(new Password(1, "qwerty1"));
        passwords.add(new Password(2, "qwerty2"));
        passwords.add(new Password(3, "qwerty3"));
        passwords.add(new Password(4, "qwerty4"));*/

        clearButton.setVisibility(View.VISIBLE);

        adapter = new PasswordAdapter(passwords);
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
        databaseHelper.deleteAllEntries(database);
        finish();
    }
}