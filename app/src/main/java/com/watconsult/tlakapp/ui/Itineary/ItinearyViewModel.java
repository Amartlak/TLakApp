package com.watconsult.tlakapp.ui.Itineary;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ItinearyViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ItinearyViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}