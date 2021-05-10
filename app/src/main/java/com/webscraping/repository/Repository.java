package com.webscraping.repository;

import android.util.Log;

import com.webscraping.api.ApiClient;
import com.webscraping.api.ApiInterface;

import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Repository {
    private static final String BASE_URL = "";
    private ApiInterface apiInterface;
    private MutableLiveData <String> htmlSourceLiveData;

    public Repository() {
        htmlSourceLiveData = new MutableLiveData<>();
    }

    public void getHtmlSource(String url) {
        apiInterface = ApiClient.getClient(url).create(ApiInterface.class);
        Call<String> call = apiInterface.url();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Repository", response.body());
                htmlSourceLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("Repository", "on failure");
                htmlSourceLiveData.postValue(null);
            }
        });
    }

    public MutableLiveData<String> getHtmlSourceLiveData() {
        return htmlSourceLiveData;
    }
}
