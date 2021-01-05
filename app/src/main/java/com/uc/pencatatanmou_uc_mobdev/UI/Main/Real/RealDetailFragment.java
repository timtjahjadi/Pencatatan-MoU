package com.uc.pencatatanmou_uc_mobdev.UI.Main.Real;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.uc.pencatatanmou_uc_mobdev.Model.Mou;
import com.uc.pencatatanmou_uc_mobdev.Model.Real;
import com.uc.pencatatanmou_uc_mobdev.R;
import com.uc.pencatatanmou_uc_mobdev.UI.Main.Mou.MouDetailFragmentDirections;
import com.uc.pencatatanmou_uc_mobdev.UI.Main.Mou.MouViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RealDetailFragment extends Fragment {

    @BindView(R.id.txt_real_detail_name)
    TextView name;

    @BindView(R.id.txt_real_detail_desc)
    TextView desc;

    @BindView(R.id.txt_real_detail_company)
    TextView company;

    @BindView(R.id.txt_real_detail_date)
    TextView date;

    @BindView(R.id.btn_real_detail_edit)
    Button edit;

    @BindView(R.id.btn_real_detail_delete)
    Button delete;

    private Real real;

    private List<Mou> mouFetchList;
    private MouViewModel mouViewModel;
    private RealViewModel viewModel;

    public RealDetailFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_real_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mouViewModel = ViewModelProviders.of(getActivity()).get(MouViewModel.class);
        viewModel = ViewModelProviders.of(getActivity()).get(RealViewModel.class);
        mouViewModel.getMouCollection().observe(requireActivity(), observeViewModel);

        real = RealDetailFragmentArgs.fromBundle(getArguments()).getReal();
        name.setText(real.getName());
        desc.setText(real.getDesc());
        date.setText(real.getDate());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Delete data?")
                        .setMessage("You can't undo this action")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(final DialogInterface dialogInterface, int i) {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        viewModel.removeRealCollection(real.getId()).observe(requireActivity(), observeViewModel1);
                                        NavDirections action = RealDetailFragmentDirections.actionRealDetailFragmentToNavReal();
                                        Navigation.findNavController(v).navigate(action);
                                    }
                                }, 1000);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .create()
                        .show();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = RealDetailFragmentDirections.actionRealDetailFragmentToRealEditFragment(real);
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

    private Observer<List<Mou>> observeViewModel = new Observer<List<Mou>>() {
        @Override
        public void onChanged(List<Mou> companyList) {
            mouFetchList = companyList;
            if (mouFetchList != null) {
                for (int i = 0; i < mouFetchList.size(); i++) {
                    Mou mouObj = mouFetchList.get(i);
                    if (mouObj.getId().equals(real.getMouid())) {
                        company.setText(mouFetchList.get(i).getTitle());
                    }
                }
            }
        }
    };

    private Observer<Integer> observeViewModel1 = new Observer<Integer>() {
        @Override
        public void onChanged(Integer integer) {
            if (integer == 0) {
                Toast.makeText(getActivity(), "Data Deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Data Cannot Be Deleted", Toast.LENGTH_SHORT).show();
            }
        }
    };
}