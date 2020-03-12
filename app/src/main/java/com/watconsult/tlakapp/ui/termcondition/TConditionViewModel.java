package com.watconsult.tlakapp.ui.termcondition;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TConditionViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TConditionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }
    public LiveData<String> getText() {
        return mText;
    }
}