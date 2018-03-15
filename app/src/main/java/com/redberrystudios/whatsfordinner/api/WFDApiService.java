package com.redberrystudios.whatsfordinner.api;

import retrofit2.Retrofit;

public class WFDApiService {

    Retrofit retrofit;

    public WFDApiService(Retrofit retrofit) {
        this.retrofit = retrofit;
    }
}
