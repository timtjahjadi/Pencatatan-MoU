package com.uc.pencatatanmou_uc_mobdev.UI.Main.Mou;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.uc.pencatatanmou_uc_mobdev.R;
import com.uc.pencatatanmou_uc_mobdev.UI.Main.Auth.LoginFragmentDirections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MouFragment extends Fragment {


    public MouFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mou, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

    }
}