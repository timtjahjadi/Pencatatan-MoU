package com.uc.pencatatanmou_uc_mobdev.UI.Main.Real;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

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
import com.uc.pencatatanmou_uc_mobdev.Network.APIendPoint;
import com.uc.pencatatanmou_uc_mobdev.R;
import com.uc.pencatatanmou_uc_mobdev.Repository.MouRepo;
import com.uc.pencatatanmou_uc_mobdev.UI.Main.Mou.MouAddFragmentDirections;
import com.uc.pencatatanmou_uc_mobdev.UI.Main.Mou.MouFragmentDirections;
import com.uc.pencatatanmou_uc_mobdev.UI.Main.Mou.MouViewModel;
import com.uc.pencatatanmou_uc_mobdev.util.ItemClickSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RealAddFragment extends Fragment {

    private String name, desc;
    private Integer year=2000;
    private Integer month=10;
    private Integer day=10;
    private Integer compId=1;

    @BindView(R.id.txt_real_add_name)
    TextInputLayout txt_name;

    @BindView(R.id.txt_real_add_des)
    TextInputLayout txt_desc;

    @BindView(R.id.btn_real_add)
    Button btn_add;

    @BindView(R.id.spin_real_company)
    Spinner spin_company;

    @BindView(R.id.spin_real_day)
    Spinner spin_day;

    @BindView(R.id.spin_real_month)
    Spinner spin_month;

    @BindView(R.id.spin_real_year)
    Spinner spin_year;

    @BindView(R.id.textView4)
    TextView texttt;

    public RealAddFragment() {}

    private RealViewModel viewModel;
    private MouViewModel mouViewModel;
    private ArrayAdapter<String> companyAdapter;
    private List<String> listCompany = new ArrayList<>();
    private List<String> listCompanyName = new ArrayList<>();

    private ArrayAdapter<String> daysAdapter;
    private ArrayAdapter<String> monthsAdapter;
    private ArrayAdapter<String> yearsAdapter;

    private List<String> list_day = new ArrayList<>();
    private List<String> list_month = new ArrayList<>();
    private List<String> list_year = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_real_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        viewModel = ViewModelProviders.of(getActivity()).get(RealViewModel.class);
        mouViewModel = ViewModelProviders.of(getActivity()).get(MouViewModel.class);
        mouViewModel.getMouCollection().observe(requireActivity(), observeViewModel1);

        fetchSpinnerData();

        daysAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, list_day);
        daysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_day.setAdapter(daysAdapter);
        spin_day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                day = Integer.parseInt(list_day.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        monthsAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, list_month);
        monthsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_month.setAdapter(monthsAdapter);
        spin_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                month = position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        yearsAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, list_year);
        yearsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_year.setAdapter(yearsAdapter);
        spin_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                year = Integer.parseInt(list_year.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

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

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = txt_name.getEditText().getText().toString();
                desc = txt_desc.getEditText().getText().toString();

                viewModel.postRealCollection("1", name, desc,year+"-"+month+"-"+day, Integer.toString(compId)).observe(requireActivity(), observeViewModel);
                NavDirections action = RealAddFragmentDirections.actionRealAddFragmentToNavReal();
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

    public void fetchSpinnerData() {
        for (int i = 1; i < 31; i++) {
            list_day.add(Integer.toString(i));
        }

        list_month.add("Januari");
        list_month.add("Februari");
        list_month.add("Maret");
        list_month.add("April");
        list_month.add("Mei");
        list_month.add("Juni");
        list_month.add("Juli");
        list_month.add("Agustus");
        list_month.add("September");
        list_month.add("Oktober");
        list_month.add("November");
        list_month.add("Desember");

        for (int i = 2000; i < 2022; i++) {
            list_year.add(Integer.toString(i));
        }
    }

    private Observer<Integer> observeViewModel = new Observer<Integer>() {
        @Override
        public void onChanged(Integer integer) {
            if (integer == 0) {
                Toast.makeText(getActivity(), "Data Added successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Data Cannot Be Added", Toast.LENGTH_SHORT).show();
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