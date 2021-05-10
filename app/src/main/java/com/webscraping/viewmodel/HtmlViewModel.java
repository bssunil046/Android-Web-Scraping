package com.webscraping.viewmodel;

import android.app.Application;
import com.webscraping.repository.Repository;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class HtmlViewModel extends AndroidViewModel {
    private Repository repository;
    private MutableLiveData<String> htmlSourceLiveData;

    public HtmlViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        repository = new Repository();
        htmlSourceLiveData = repository.getHtmlSourceLiveData();
    }

    public void getHtmlSource(String url) {
        repository.getHtmlSource(url);
    }

    public MutableLiveData<String> getHtmlSourceLiveData() {
        return htmlSourceLiveData;
    }
}
