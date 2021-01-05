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

    public LiveData<Integer> postRealCollection(String userid, String name, String desc, String date, String mouid) {
        return repository.postReal(userid, name, desc, date, mouid);
    }

    public LiveData<Integer> removeRealCollection(String id) {
        return repository.deleteReal(id);
    }

    public LiveData<Integer> updateRealCollection(String id, String name, String desc, String mouid) {
        return repository.updateReal(id, name, desc, mouid);
    }
}
