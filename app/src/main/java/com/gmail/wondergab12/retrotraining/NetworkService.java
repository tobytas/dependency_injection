package com.gmail.wondergab12.retrotraining;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    private static class InstanceHolder {
        static final NetworkService INSTANCE = new NetworkService();
    }

    public static NetworkService getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private Retrofit retrofit;

    private NetworkService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public PlaceHolderService getPlaceHolderService() {
        return retrofit.create(PlaceHolderService.class);
    }
}
