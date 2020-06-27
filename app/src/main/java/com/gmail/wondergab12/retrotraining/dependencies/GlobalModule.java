package com.gmail.wondergab12.retrotraining.dependencies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.gmail.wondergab12.retrotraining.PlaceHolderService;
import com.gmail.wondergab12.retrotraining.PostContainer;
import com.gmail.wondergab12.retrotraining.viewmodel.PostViewModel;
import com.gmail.wondergab12.retrotraining.viewmodel.PostViewModelFactory;

import dagger.Module;
import dagger.Provides;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class GlobalModule {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    private AppCompatActivity activity;

    public GlobalModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    MutableLiveData<PostContainer> provideMutablePostContainerLiveData() {
        return new MutableLiveData<>();
    }

    @Provides
    MutableLiveData<Throwable> provideMutableThrowableLiveData() {
        return new MutableLiveData<>();
    }

    @Provides
    PlaceHolderService providePlaceHolderService() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(PlaceHolderService.class);
    }

    @Provides
    PostViewModel providePostViewModel(PostViewModelFactory postViewModelFactory) {
        return new ViewModelProvider(activity, postViewModelFactory).get(PostViewModel.class);
    }
}
