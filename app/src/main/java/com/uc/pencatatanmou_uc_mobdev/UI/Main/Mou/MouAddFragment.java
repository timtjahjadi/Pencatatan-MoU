package com.uc.pencatatanmou_uc_mobdev.UI.Main.Mou;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.uc.pencatatanmou_uc_mobdev.Model.Mou;
import com.uc.pencatatanmou_uc_mobdev.Network.APIendPoint;
import com.uc.pencatatanmou_uc_mobdev.R;
import com.uc.pencatatanmou_uc_mobdev.Repository.MouRepo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;

public class MouAddFragment extends Fragment {

    private String name, form;
    String filePath = "";
    private Integer year=2000;
    private Integer month=10;
    private Integer day=10;
    private MouRepo repo;
    private APIendPoint api;

    private Bitmap imagee;
    private Integer returning = 99;

    @BindView(R.id.txt_mou_add_name)
    TextInputLayout txt_name;

    @BindView(R.id.txt_mou_add_form)
    TextInputLayout txt_form;

    @BindView(R.id.btn_mou_add_create)
    Button btn_add;

    @BindView(R.id.spin_mou_tgl)
    Spinner spin_day;

    @BindView(R.id.spin_mou_bln)
    Spinner spin_month;

    @BindView(R.id.spin_mou_thn)
    Spinner spin_year;

    @BindView(R.id.btn_mou_up_photo)
    Button btn_up_photo;

    @BindView(R.id.imageView)
    ImageView image;

    private Mou mou;
    File file;
    private MouViewModel viewModel;

    private ArrayAdapter<String> daysAdapter;
    private ArrayAdapter<String> monthsAdapter;
    private ArrayAdapter<String> yearsAdapter;

    private List<String> list_day = new ArrayList<>();
    private List<String> list_month = new ArrayList<>();
    private List<String> list_year = new ArrayList<>();

    public MouAddFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mou_add, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        viewModel = ViewModelProviders.of(getActivity()).get(MouViewModel.class);

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

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = txt_name.getEditText().getText().toString();
                form = txt_form.getEditText().getText().toString();

                viewModel.postMouCollection("1", name, form,year+"-"+month+"-"+day,"-","-").observe(requireActivity(), observeViewModel);

                NavDirections action = MouAddFragmentDirections.actionMouAddFragmentToNavMou();
                Navigation.findNavController(v).navigate(action);
            }
        });

        btn_up_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "open gallery"), 1);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        String path = data.getData().getPath();
        filePath = path;

        if (requestCode == 1 && resultCode == RESULT_OK) {

            List<Bitmap> bitmaps = new ArrayList<>();

                Uri imageUri = data.getData();

            try {
                    InputStream inputStream = getActivity().getContentResolver().openInputStream(imageUri);

                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                    bitmaps.add(bitmap);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            image.setImageBitmap(bitmaps.get(0));
            imagee = bitmaps.get(0);

            file = new File(path);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part parts = MultipartBody.Part.createFormData("User Image", file.getName(), requestBody);
            viewModel.postFileCollection("17", parts).observe(requireActivity(), observeViewModel1);

        }
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

    private Observer<Integer> observeViewModel1 = new Observer<Integer>() {
        @Override
        public void onChanged(Integer integer) {
            if (integer == 0) {
                Toast.makeText(getActivity(), "Data Added successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Data Cannot Be Added", Toast.LENGTH_SHORT).show();
            }
        }
    };
}