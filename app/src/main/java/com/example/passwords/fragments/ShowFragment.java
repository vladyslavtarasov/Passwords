package com.example.passwords.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.passwords.R;

public class ShowFragment extends Fragment {

    TextView passwordView;
    public ShowFragment() {
        super(R.layout.fragment_show);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        passwordView = getView().findViewById(R.id.password_view);
        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                String password = bundle.getString("password");
                passwordView.setText(getString(R.string.your_password, password));
            }
        });
    }
}
