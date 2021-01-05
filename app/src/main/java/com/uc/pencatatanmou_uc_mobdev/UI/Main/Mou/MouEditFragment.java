package com.uc.pencatatanmou_uc_mobdev.UI.Main.Mou;

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
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.uc.pencatatanmou_uc_mobdev.Model.Mou;
import com.uc.pencatatanmou_uc_mobdev.Network.APIendPoint;
import com.uc.pencatatanmou_uc_mobdev.R;
import com.uc.pencatatanmou_uc_mobdev.Repository.MouRepo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MouEditFragment extends Fragment {

    private String id, name, form;
    private Integer year=2000;
    private Integer month=10;
    private Integer day=10;

    @BindView(R.id.txt_mou_edit_name)
    TextInputLayout txt_name;

    @BindView(R.id.txt_mou_edit_form)
    TextInputLayout txt_form;

    @BindView(R.id.btn_mou_edit_save)
    Button btn_save;

    @BindView(R.id.txt_mou_edit_day)
    TextView txt_day ;

    private Mou mou;
    private MouViewModel viewModel;

    public MouEditFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mou_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        viewModel = ViewModelProviders.of(getActivity()).get(MouViewModel.class);

        mou = MouEditFragmentArgs.fromBundle(getArguments()).getMou();

        id = mou.getId();
        txt_name.getEditText().setText(mou.getTitle());
        txt_form.getEditText().setText(mou.getForm());
        txt_day.setText("MOU Date : " + mou.getDate());

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = txt_name.getEditText().getText().toString();
                form = txt_form.getEditText().getText().toString();

                viewModel.updateMouCollection(mou.getId(), name, form).observe(requireActivity(), observeViewModel);
                NavDirections action = MouEditFragmentDirections.actionMouEditFragmentToNavMou();
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
}