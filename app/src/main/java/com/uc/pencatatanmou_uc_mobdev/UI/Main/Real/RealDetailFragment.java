package com.uc.pencatatanmou_uc_mobdev.UI.Main.Real;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uc.pencatatanmou_uc_mobdev.R;

public class RealDetailFragment extends Fragment {

    public RealDetailFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_real_detail, container, false);
    }
}