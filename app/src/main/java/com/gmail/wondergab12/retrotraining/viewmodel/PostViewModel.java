package com.gmail.wondergab12.retrotraining.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gmail.wondergab12.retrotraining.NetworkService;
import com.gmail.wondergab12.retrotraining.PostContainer;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;

@SuppressWarnings("Convert2Lambda")
public class PostViewModel extends ViewModel {

    private MutableLiveData<PostContainer> post;
    private MutableLiveData<Throwable> err;
    private Disposable disposable;

    public LiveData<PostContainer> getPost() {
        if (post == null) {
            post = new MutableLiveData<>();
        }
        return post;
    }

    public LiveData<Throwable> getErr() {
        if (err == null) {
            err = new MutableLiveData<>();
        }
        return err;
    }

    public void loadPost() {
        disposable = Observable.create(new ObservableOnSubscribe<Response<PostContainer>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Response<PostContainer>> emitter) throws Throwable {
                emitter.onNext(NetworkService.getInstance()
                        .getPlaceHolderService()
                        .getPostWithId(2)
                        .execute());
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<PostContainer>>() {
                    @Override
                    public void accept(Response<PostContainer> response) {
                        PostContainer postContainer = response.body();
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
        disposable.dispose();
    }
}
