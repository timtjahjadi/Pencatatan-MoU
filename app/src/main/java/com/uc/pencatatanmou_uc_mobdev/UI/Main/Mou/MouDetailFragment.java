package com.uc.pencatatanmou_uc_mobdev.UI.Main.Mou;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.uc.pencatatanmou_uc_mobdev.Adapter.mouAdapter;
import com.uc.pencatatanmou_uc_mobdev.Model.Mou;
import com.uc.pencatatanmou_uc_mobdev.R;
import com.uc.pencatatanmou_uc_mobdev.UI.Main.Profile.ProfileFragmentDirections;
import com.uc.pencatatanmou_uc_mobdev.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MouDetailFragment extends Fragment {

    @BindView(R.id.txt_mou_detail_name)
    TextView name;

    @BindView(R.id.txt_mou_detail_form)
    TextView form;

    @BindView(R.id.txt_mou_detail_date)
    TextView date;

    @BindView(R.id.img_mou_detail_photo)
    ImageView photo;

    @BindView(R.id.btn_mou_detail_edit)
    Button edit;

    @BindView(R.id.btn_mou_detail_delete)
    Button delete;

    private MouViewModel viewModel;
    private Mou mou;

    public MouDetailFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mou_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        viewModel = ViewModelProviders.of(getActivity()).get(MouViewModel.class);

        mou = MouDetailFragmentArgs.fromBundle(getArguments()).getMou();

        name.setText(mou.getTitle());
        form.setText(mou.getForm());
        date.setText(mou.getDate());

        Glide.with(getActivity()).load(Constants.BASE_URL + "api/api-file/1").into(photo);

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
                                        viewModel.removeMouCollection(mou.getId()).observe(requireActivity(), observeViewModel);
                                        NavDirections action = MouDetailFragmentDirections.actionMouDetailFragmentToNavMou();
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
                NavDirections action = MouDetailFragmentDirections.actionMouDetailFragmentToMouEditFragment(mou);
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

    private Observer<Integer> observeViewModel = new Observer<Integer>() {
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