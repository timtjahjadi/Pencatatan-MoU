package com.uc.pencatatanmou_uc_mobdev.UI.Main.Mou;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uc.pencatatanmou_uc_mobdev.Adapter.mouAdapter;
import com.uc.pencatatanmou_uc_mobdev.Model.Mou;
import com.uc.pencatatanmou_uc_mobdev.R;
import com.uc.pencatatanmou_uc_mobdev.UI.Main.Auth.LoginFragmentDirections;
import com.uc.pencatatanmou_uc_mobdev.util.ItemClickSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MouFragment extends Fragment {

    @BindView(R.id.rv_mous)
    RecyclerView rv_mou;

    @BindView(R.id.fab_add_mou)
    Button btn_add;

    private MouViewModel viewModel;
    private mouAdapter mouAdapterr;

    public MouFragment() {}

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

        viewModel = ViewModelProviders.of(getActivity()).get(MouViewModel.class);
        viewModel.getMouCollection().observe(requireActivity(), observeViewModel);

        rv_mou.setLayoutManager(new LinearLayoutManager(getActivity()));
        mouAdapterr = new mouAdapter(getActivity());

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = MouFragmentDirections.actionNavMouToMouAddFragment();
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

    private Observer<List<Mou>> observeViewModel = new Observer<List<Mou>>() {
        @Override
        public void onChanged(List<Mou> mouList) {
            if (mouList != null) {
                mouAdapterr.setList_mou(mouList);
                mouAdapterr.notifyDataSetChanged();
                rv_mou.setAdapter(mouAdapterr);

                ItemClickSupport.addTo(rv_mou).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Mou mou = mouList.get(position);
                        NavDirections action = MouFragmentDirections.actionNavMouToMouDetailFragment(mou);
                        Navigation.findNavController(v).navigate(action);
                    }
                });
            }
        }
    };
}