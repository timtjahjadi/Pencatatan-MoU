package com.uc.pencatatanmou_uc_mobdev.UI.Main.Real;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.uc.pencatatanmou_uc_mobdev.Model.Real;
import com.uc.pencatatanmou_uc_mobdev.Repository.RealRepo;

import java.util.List;

public class RealViewModel extends ViewModel {

    private RealRepo repository;

    public RealViewModel() {
        repository = RealRepo.getInstance();
    }

    public LiveData<List<Real>> getRealCollection() {
        return repository.getRealCollection();
    }

}
