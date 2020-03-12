package com.watconsult.tlakapp.ui.sos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SOSViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SOSViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }
    public LiveData<String> getText() {
        return mText;
    }
}