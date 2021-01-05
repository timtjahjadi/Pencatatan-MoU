package com.uc.pencatatanmou_uc_mobdev.UI.Main.Auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.uc.pencatatanmou_uc_mobdev.Model.Mou;
import com.uc.pencatatanmou_uc_mobdev.Model.Real;
import com.uc.pencatatanmou_uc_mobdev.Model.User;
import com.uc.pencatatanmou_uc_mobdev.R;
import com.uc.pencatatanmou_uc_mobdev.UI.Main.Mou.MouViewModel;
import com.uc.pencatatanmou_uc_mobdev.UI.Main.Real.RealViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginFragment extends Fragment {

    private String email, password;

    //declare all components
    @BindView(R.id.btn_login)
    Button button;

    @BindView(R.id.text_email)
    TextInputLayout txt_email;

    @BindView(R.id.text_password)
    TextInputLayout txt_pass;

    @BindView(R.id.logintext)
    TextView tulisan;

    private LoginViewModel loginViewModel;
    private Boolean pass = false;
    private List<User> fetchList;
    SharedPreferences userPref;
    SharedPreferences.Editor userEditor;
    private Context context;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        userPref = getActivity().getSharedPreferences("user", getActivity().MODE_PRIVATE);
        userEditor = userPref.edit();
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        loginViewModel = ViewModelProviders.of(getActivity()).get(LoginViewModel.class);

        button.setOnClickListener(v -> {
            loginViewModel.getUserCollection().observe(requireActivity(), observeViewModel);
            if (pass) {
                NavDirections action = LoginFragmentDirections.actionLoginFragmentToMouFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    private Observer<List<User>> observeViewModel = new Observer<List<User>>() {
        @Override
        public void onChanged(List<User> users) {
            fetchList = users;
            email = txt_email.getEditText().getText().toString();
            password = txt_pass.getEditText().getText().toString();

            if (fetchList != null) {
                for (int i = 0; i < fetchList.size(); i++) {
                    User user = fetchList.get(i);
                    if (email.equals(user.getEmail())) {
                        if (password.equals(user.getPassword())) {
                            pass = true;
                            userEditor.putString("id", user.getId());
                            userEditor.commit();
                            Toast.makeText(getActivity(), "Welcome, " + user.getName(), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getActivity(), "Wrong Email or Password. Please Try Again", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getActivity(), "Wrong Email or Password. Please Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    };

//    private Observer<List<User>> observeViewModel = new Observer<List<User>>() {
//        @Override
//        public void onChanged(List<User> users) {
//            email = txt_email.toString();
//            password = txt_pass.toString();
//            tulisan.setText("asdadadasd");
//            pass = true;
//
////            if (users != null) {
////                for (int i = 0; i < users.size(); i++) {
////                    User Obj = users.get(i);
////
////                    if (Obj.getEmail().equals(email)) {
////                        if (Obj.getPassword().equals(password)) {
////                            pass = true;
////                        }
////                    } else {
////                        pass = false;
////                    }
////                }
////            }
//        }
//    };

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

//    @Override
//    public void onStop() {
//        super.onStop();
//        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
//    }
}