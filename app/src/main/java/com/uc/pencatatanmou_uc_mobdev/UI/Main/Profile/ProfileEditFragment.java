package com.uc.pencatatanmou_uc_mobdev.UI.Main.Profile;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.uc.pencatatanmou_uc_mobdev.Model.Mou;
import com.uc.pencatatanmou_uc_mobdev.Model.User;
import com.uc.pencatatanmou_uc_mobdev.R;
import com.uc.pencatatanmou_uc_mobdev.UI.Main.Auth.LoginViewModel;
import com.uc.pencatatanmou_uc_mobdev.UI.Main.Mou.MouEditFragmentArgs;
import com.uc.pencatatanmou_uc_mobdev.UI.Main.Mou.MouEditFragmentDirections;
import com.uc.pencatatanmou_uc_mobdev.UI.Main.Mou.MouViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileEditFragment extends Fragment {

    private String id, name, email;

    @BindView(R.id.txt_profile_edit_name)
    TextInputLayout txt_name;

    @BindView(R.id.txt_profile_edit_email)
    TextInputLayout txt_email;

    @BindView(R.id.btn_profile_edit_save)
    Button btn_save;

    private User user;
    private LoginViewModel viewModel;
    private List<User> fetchList;
    SharedPreferences userPref;
    SharedPreferences.Editor userEditor;

    public ProfileEditFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        userPref = getActivity().getSharedPreferences("user", getActivity().MODE_PRIVATE);
        userEditor = userPref.edit();
        return inflater.inflate(R.layout.fragment_profile_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        viewModel = ViewModelProviders.of(getActivity()).get(LoginViewModel.class);

        id = ProfileEditFragmentArgs.fromBundle(getArguments()).getUser();

        viewModel.getUserCollection().observe(requireActivity(), observeViewModel);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = txt_name.getEditText().getText().toString();
                email = txt_email.getEditText().getText().toString();

                viewModel.updateUserCollection(id, name, email).observe(requireActivity(), observeViewModel1);
                userEditor.putString("id", id);
                userEditor.commit();
                NavDirections action = ProfileEditFragmentDirections.actionProfileEditFragmentToNavProfile();
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

    private Observer<List<User>> observeViewModel = new Observer<List<User>>() {
        @Override
        public void onChanged(List<User> users) {
            fetchList = users;
            if (fetchList != null) {
                for (int i = 0; i < fetchList.size(); i++) {
                    User user = fetchList.get(i);
                    if (id.equals(user.getId())) {
                        txt_name.getEditText().setText(user.getName());
                        txt_email.getEditText().setText(user.getEmail());
                    }
                }
            }
        }
    };

    private Observer<Integer> observeViewModel1 = new Observer<Integer>() {
        @Override
        public void onChanged(Integer integer) {
            if (integer == 0) {
                Toast.makeText(getActivity(), "Profile Updated", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Profile Cannot Be Updated", Toast.LENGTH_SHORT).show();
            }
        }
    };
}