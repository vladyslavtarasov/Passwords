package com.example.passwords;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button hideButton, showButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideButton = findViewById(R.id.hidePassword_button);
        showButton = findViewById(R.id.showPassword_button);
        hideButton.setEnabled(false);
    }

    public void showPassword(View view) {
        EditText password = findViewById(R.id.password_text);
        //if (!password.getText().toString().equals("")) {
        password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        password.setSelection(password.length());
        showButton.setEnabled(false);
        hideButton.setEnabled(true);
        //}
    }

    public void hidePassword(View view) {
        EditText password = findViewById(R.id.password_text);
        //if (!password.getText().toString().equals("")) {
        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        password.setSelection(password.length());
        showButton.setEnabled(true);
        hideButton.setEnabled(false);
        //}
    }

    public void viewPassword(View view) {
        EditText password = findViewById(R.id.password_text);
        if (password.getText().toString().equals("")) {
            Toast toast = Toast.makeText(this, "Please enter your password first", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        TextView passwordView = findViewById(R.id.password);
        passwordView.setText("Your password: " + password.getText());
        passwordView.setVisibility(View.VISIBLE);
        password.setText("");
    }
}