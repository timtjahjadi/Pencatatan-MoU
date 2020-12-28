package com.uc.pencatatanmou_uc_mobdev.UI.Main.Mou;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uc.pencatatanmou_uc_mobdev.Adapter.mouAdapter;
import com.uc.pencatatanmou_uc_mobdev.R;

import butterknife.ButterKnife;

public class MouAddFragment extends Fragment {

    public MouAddFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mou_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

//        viewModel = ViewModelProviders.of(getActivity()).get(MouViewModel.class);
//        viewModel.getMouCollection().observe(requireActivity(), observeViewModel);
//
//        rv_mou.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mouAdapterr = new mouAdapter(getActivity());
    }
}