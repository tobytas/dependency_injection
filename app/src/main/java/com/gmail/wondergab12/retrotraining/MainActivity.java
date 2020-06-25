package com.gmail.wondergab12.retrotraining;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

import retrofit2.Response;

@SuppressWarnings("Convert2Lambda")
public class MainActivity extends AppCompatActivity {

    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                                if (postContainer != null) {
                                    textView.append(postContainer.getUserId() + "\n");
                                    textView.append(postContainer.getId() + "\n");
                                    textView.append(postContainer.getTitle() + "\n");
                                    textView.append(postContainer.getBody() + "\n");
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                textView.append("Response error");
                                throwable.printStackTrace();
                            }
                        });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
