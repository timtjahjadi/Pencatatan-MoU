package com.uc.pencatatanmou_uc_mobdev.UI.Main.Auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.uc.pencatatanmou_uc_mobdev.Model.User;
import com.uc.pencatatanmou_uc_mobdev.Repository.UserRepo;

import java.util.List;

public class LoginViewModel extends ViewModel {

    private UserRepo repository;

    public LoginViewModel() {
        repository = UserRepo.getInstance();
    }

    public LiveData<List<User>> getUserCollection() {
        return repository.getUserCollection();
    }

    public LiveData<Integer> updateUserCollection(String id, String  name, String email) {
        return repository.updateUser(id, name, email);
    }
}
