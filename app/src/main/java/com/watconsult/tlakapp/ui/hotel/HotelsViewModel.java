package com.watconsult.tlakapp.ui.hotel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HotelsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HotelsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }
    public LiveData<String> getText() {
        return mText;
    }
}