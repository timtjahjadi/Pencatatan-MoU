package com.uc.pencatatanmou_uc_mobdev.UI.Main.Real;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.uc.pencatatanmou_uc_mobdev.Model.Mou;
import com.uc.pencatatanmou_uc_mobdev.Model.Real;
import com.uc.pencatatanmou_uc_mobdev.Network.APIendPoint;
import com.uc.pencatatanmou_uc_mobdev.R;
import com.uc.pencatatanmou_uc_mobdev.Repository.MouRepo;
import com.uc.pencatatanmou_uc_mobdev.UI.Main.Mou.MouEditFragmentArgs;
import com.uc.pencatatanmou_uc_mobdev.UI.Main.Mou.MouEditFragmentDirections;
import com.uc.pencatatanmou_uc_mobdev.UI.Main.Mou.MouViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RealEditFragment extends Fragment {

    private String id, name, desc;
    private Integer compId=1;

    @BindView(R.id.txt_real_edit_name)
    TextInputLayout txt_name;

    @BindView(R.id.txt_real_edit_desc)
    TextInputLayout txt_desc;

    @BindView(R.id.btn_real_edit_save)
    Button btn_save;

    @BindView(R.id.spin_real_edit_company)
    Spinner spin_company;

    @BindView(R.id.txt_real_edit_day)
    TextView txt_date;

    private Real real;
    private RealViewModel viewModel;
    private MouViewModel mouViewModel;

    private List<String> listCompany = new ArrayList<>();
    private List<String> listCompanyName = new ArrayList<>();

    private ArrayAdapter<String> companyAdapter;

    public RealEditFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_real_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        viewModel = ViewModelProviders.of(getActivity()).get(RealViewModel.class);
        mouViewModel = ViewModelProviders.of(getActivity()).get(MouViewModel.class);
        mouViewModel.getMouCollection().observe(requireActivity(), observeViewModel1);
        real = RealEditFragmentArgs.fromBundle(getArguments()).getReal();

        id = real.getId();
        txt_name.getEditText().setText(real.getName());
        txt_desc.getEditText().setText(real.getDesc());
        txt_date.setText("Realisation Date : " + real.getDate());

        companyAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, listCompanyName);
        companyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_company.setAdapter(companyAdapter);

        spin_company.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                compId = Integer.parseInt(listCompany.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = txt_name.getEditText().getText().toString();
                desc = txt_desc.getEditText().getText().toString();

                viewModel.updateRealCollection(real.getId(), name, desc, compId.toString()).observe(requireActivity(), observeViewModel);
                NavDirections action = RealEditFragmentDirections.actionRealEditFragmentToNavReal();
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

    private Observer<Integer> observeViewModel = new Observer<Integer>() {
        @Override
        public void onChanged(Integer integer) {
            if (integer == 0) {
                Toast.makeText(getActivity(), "Data Updated", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Data Cannot Be Updated", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private Observer<List<Mou>> observeViewModel1 = new Observer<List<Mou>>() {
        @Override
        public void onChanged(List<Mou> mouList) {
            if (listCompanyName.isEmpty()) {
                for (int i = 0; i < mouList.size(); i++) {
                    listCompanyName.add(mouList.get(i).getTitle());
                    listCompany.add(mouList.get(i).getId());
                }
                companyAdapter.notifyDataSetChanged();
            }
        }
    };
}