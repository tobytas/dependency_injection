package com.gmail.wondergab12.retrotraining;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlaceHolderService {

    @GET("/posts/{id}")
    Call<PostContainer> getPostWithId(@Path("id") int id);
}
