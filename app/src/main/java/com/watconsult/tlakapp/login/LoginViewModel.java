package com.watconsult.tlakapp.login;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    public MutableLiveData<String> EmailAddress = new MutableLiveData<>();
    public MutableLiveData<String> Password = new MutableLiveData<>();

    private MutableLiveData<LoginModel> loginModelLiveData;

    public MutableLiveData<LoginModel> getUser()
    {
        if(loginModelLiveData == null)
        {
            loginModelLiveData = new MutableLiveData<>();

        }
        return loginModelLiveData;
    }
    public void OnClick(View view)
    {
        LoginModel loginModel = new LoginModel(EmailAddress.getValue(),Password.getValue());
        loginModelLiveData.setValue(loginModel);
    }
    }

