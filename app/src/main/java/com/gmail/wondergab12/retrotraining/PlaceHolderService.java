package com.gmail.wondergab12.retrotraining;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlaceHolderService {

    @GET("/posts/{id}")
    Observable<PostContainer> getPostWithId(@Path("id") int id);
}
