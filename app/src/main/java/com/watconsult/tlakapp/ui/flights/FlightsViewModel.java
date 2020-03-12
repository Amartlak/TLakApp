package com.watconsult.tlakapp.ui.flights;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FlightsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FlightsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }
    public LiveData<String> getText() {
        return mText;
    }
}