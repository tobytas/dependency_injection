package com.gmail.wondergab12.retrotraining.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gmail.wondergab12.retrotraining.PlaceHolderService;
import com.gmail.wondergab12.retrotraining.PostContainer;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

@SuppressWarnings({"WeakerAccess", "Convert2Lambda", "Anonymous2MethodRef"})
public class PostViewModel extends ViewModel {

    private final PlaceHolderService placeHolderService;
    private final MutableLiveData<PostContainer> post;
    private final MutableLiveData<Throwable> err;
    private Disposable disposable;

    public PostViewModel(MutableLiveData<PostContainer> post, MutableLiveData<Throwable> err,
                         PlaceHolderService placeHolderService) {
        this.placeHolderService = placeHolderService;
        this.post = post;
        this.err = err;
    }

    public LiveData<PostContainer> getPost() {
        return post;
    }

    public LiveData<Throwable> getErr() {
        return err;
    }

    public void loadPost() {
        disposable = placeHolderService.getPostWithId(2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PostContainer>() {
                    @Override
                    public void accept(PostContainer postContainer) {
                        post.setValue(postContainer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        err.setValue(throwable);
                    }
                });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
