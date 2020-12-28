package com.uc.pencatatanmou_uc_mobdev.UI.Main.Auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.uc.pencatatanmou_uc_mobdev.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginFragment extends Fragment {

    //declare all components
    @BindView(R.id.btn_login)
    Button button;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        button.setOnClickListener(v -> {
            NavDirections action = LoginFragmentDirections.actionLoginFragmentToMouFragment();
            Navigation.findNavController(view).navigate(action);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

//    @Override
//    public void onStop() {
//        super.onStop();
//        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
//    }
}