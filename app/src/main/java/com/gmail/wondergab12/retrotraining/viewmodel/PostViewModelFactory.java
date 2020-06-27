package com.gmail.wondergab12.retrotraining.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.gmail.wondergab12.retrotraining.PlaceHolderService;
import com.gmail.wondergab12.retrotraining.PostContainer;

import javax.inject.Inject;

public class PostViewModelFactory implements ViewModelProvider.Factory {

    private PlaceHolderService placeHolderService;
    private MutableLiveData<PostContainer> post;
    private MutableLiveData<Throwable> err;

    @Inject
    public PostViewModelFactory(MutableLiveData<PostContainer> post, MutableLiveData<Throwable> err,
                                PlaceHolderService placeHolderService) {
        this.post = post;
        this.err = err;
        this.placeHolderService = placeHolderService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new PostViewModel(post, err, placeHolderService);
    }
}
