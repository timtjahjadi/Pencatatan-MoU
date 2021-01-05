package com.uc.pencatatanmou_uc_mobdev.UI.Main.Profile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.uc.pencatatanmou_uc_mobdev.Model.User;
import com.uc.pencatatanmou_uc_mobdev.R;
import com.uc.pencatatanmou_uc_mobdev.UI.Main.Auth.LoginFragmentDirections;
import com.uc.pencatatanmou_uc_mobdev.UI.Main.Auth.LoginViewModel;
import com.uc.pencatatanmou_uc_mobdev.UI.Main.Mou.MouViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment {

    @BindView(R.id.txt_profile_name)
    TextView txt_name;

    @BindView(R.id.txt_profile_email)
    TextView txt_email;

    @BindView(R.id.btn_profile_edit)
    Button btn_edit;

    @BindView(R.id.btn_profile_logout)
    Button btn_logout;

    private LoginViewModel viewModel;
    private List<User> fetchList;
    private String ids = "";
    SharedPreferences userPref;
    SharedPreferences.Editor userEditor;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        userPref = getActivity().getSharedPreferences("user", getActivity().MODE_PRIVATE);
        userEditor = userPref.edit();
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        viewModel = ViewModelProviders.of(getActivity()).get(LoginViewModel.class);
        viewModel.getUserCollection().observe(requireActivity(), observeViewModel);

        final SharedPreferences preferences = this.getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        ids = preferences.getString("id", "");

        btn_edit.setOnClickListener(v -> {
            NavDirections action = ProfileFragmentDirections.actionNavProfileToProfileEditFragment(ids);
            Navigation.findNavController(view).navigate(action);
        });

        btn_logout.setOnClickListener(v -> {
            new AlertDialog.Builder(getActivity())
                    .setTitle("Logout?")
                    .setMessage("Are you sure to sign out?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(final DialogInterface dialogInterface, int i) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    NavDirections action = ProfileFragmentDirections.actionNavProfileToSplashFragment();
                                    Navigation.findNavController(view).navigate(action);
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
        });

    }

    private Observer<List<User>> observeViewModel = new Observer<List<User>>() {
        @Override
        public void onChanged(List<User> users) {
            fetchList = users;
            if (fetchList != null) {
                for (int i = 0; i < fetchList.size(); i++) {
                    User user = fetchList.get(i);
                    if (ids.equals(user.getId())) {
                        txt_name.setText(user.getName());
                        txt_email.setText(user.getEmail());
                    }
                }
            }
        }
    };

}