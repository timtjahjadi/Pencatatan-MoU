package com.uc.pencatatanmou_uc_mobdev.UI.Main.Mou;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.uc.pencatatanmou_uc_mobdev.Model.Mou;
import com.uc.pencatatanmou_uc_mobdev.Repository.MouRepo;

import java.util.List;

public class MouViewModel extends ViewModel {

    private MouRepo repository;

    public MouViewModel() {
        repository = MouRepo.getInstance();
    }

    public LiveData<List<Mou>> getMouCollection() {
        return repository.getMouCollection();
    }

}
