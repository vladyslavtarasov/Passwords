package com.example.passwords.fragments;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.passwords.R;

public class EnterFragment extends Fragment {

    Button hideButton, showButton, okButton;
    EditText website, username, password;
    public EnterFragment() {
        super(R.layout.fragment_enter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showButton = getView().findViewById(R.id.showPassword_button);
        hideButton = getView().findViewById(R.id.hidePassword_button);
        okButton = getView().findViewById(R.id.ok_button);
        website = getView().findViewById(R.id.website_text);
        username = getView().findViewById(R.id.username_text);
        password = getView().findViewById(R.id.password_text);

        hideButton.setEnabled(false);

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                password.setSelection(password.length());
                showButton.setEnabled(false);
                hideButton.setEnabled(true);
            }
        });

        hideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                password.setSelection(password.length());
                showButton.setEnabled(true);
                hideButton.setEnabled(false);
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (website.getText().toString().equals("")
                        || username.getText().toString().equals("")
                        || password.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(getActivity(), "Please enter all the data first", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                Bundle result = new Bundle();
                result.putString("website", website.getText().toString());
                result.putString("username", username.getText().toString());
                result.putString("password", password.getText().toString());
                getParentFragmentManager().setFragmentResult("requestKey", result);
                website.setText("");
                username.setText("");
                password.setText("");
            }
        });
    }
}
