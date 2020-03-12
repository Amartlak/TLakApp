package com.watconsult.tlakapp.ui.placard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlaCardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PlaCardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is PlaCard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
