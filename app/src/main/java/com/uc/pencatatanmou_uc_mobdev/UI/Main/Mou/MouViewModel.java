package com.uc.pencatatanmou_uc_mobdev.UI.Main.Mou;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.uc.pencatatanmou_uc_mobdev.Model.Mou;
import com.uc.pencatatanmou_uc_mobdev.Repository.MouRepo;

import java.util.List;

import okhttp3.MultipartBody;

public class MouViewModel extends ViewModel {

    private MouRepo repository;

    public MouViewModel() {
        repository = MouRepo.getInstance();
    }

    public LiveData<List<Mou>> getMouCollection() {
        return repository.getMouCollection();
    }

    public LiveData<Integer> postMouCollection(String userid, String  name, String form, String date, String photo, String attach) {
        return repository.postMou(userid, name, form, date, photo, attach);
    }

    public LiveData<Integer> removeMouCollection(String id) {
        return repository.deleteMou(id);
    }

    public LiveData<Integer> updateMouCollection(String id, String  name, String form) {
        return repository.updateMou(id, name, form);
    }

    public LiveData<Integer> postFileCollection(String id, MultipartBody.Part part) {
        return repository.uploadFile(id, part);
    }

}
