package com.uc.pencatatanmou_uc_mobdev.UI.Main.Real;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uc.pencatatanmou_uc_mobdev.Adapter.mouAdapter;
import com.uc.pencatatanmou_uc_mobdev.Adapter.realAdapter;
import com.uc.pencatatanmou_uc_mobdev.Model.Mou;
import com.uc.pencatatanmou_uc_mobdev.Model.Real;
import com.uc.pencatatanmou_uc_mobdev.R;
import com.uc.pencatatanmou_uc_mobdev.UI.Main.Mou.MouViewModel;
import com.uc.pencatatanmou_uc_mobdev.util.ItemClickSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RealFragment extends Fragment {

    @BindView(R.id.rv_reals)
    RecyclerView rv_real;

    private RealViewModel viewModel;
    private realAdapter realAdapterr;

    public RealFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_real, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        viewModel = ViewModelProviders.of(getActivity()).get(RealViewModel.class);
        viewModel.getRealCollection().observe(requireActivity(), observeViewModel);

        rv_real.setLayoutManager(new LinearLayoutManager(getActivity()));
        realAdapterr = new realAdapter(getActivity());
    }

    private Observer<List<Real>> observeViewModel = new Observer<List<Real>>() {
        @Override
        public void onChanged(List<Real> realList) {
            if (realList != null) {
                realAdapterr.setList_real(realList);
                realAdapterr.notifyDataSetChanged();
                rv_real.setAdapter(realAdapterr);

                ItemClickSupport.addTo(rv_real).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
//                        Real real = realList.get(position);
//                        NavDirections action = MovieFragmentDirections.actionToDetailFragment(movie, null);
//                        Navigation.findNavController(v).navigate(action);
                    }
                });
            }
        }
    };

}