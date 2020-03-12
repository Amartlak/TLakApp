package com.watconsult.tlakapp.ui.mygroup;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyGroupViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MyGroupViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is share fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}