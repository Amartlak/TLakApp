package com.watconsult.tlakapp.ui.upcomingtour;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TourViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TourViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }
    public LiveData<String> getText() {
        return mText;
    }
}