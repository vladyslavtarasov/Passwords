package com.example.passwords;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.passwords.fragments.EnterFragment;
import com.example.passwords.fragments.ShowFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_enter_password, EnterFragment.class, null)
                    .commit();
        }*/

        /*if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_show_password, ShowFragment.class, null)
                    .commit();
        }*/
    }


    public void showPasswords(View view) {
        Intent intent = new Intent(this, PasswordListActivity.class);
        startActivity(intent);
    }
}