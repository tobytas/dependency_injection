package com.gmail.wondergab12.retrotraining;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        //noinspection Convert2Lambda
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkService.getInstance()
                        .getPlaceHolderService()
                        .getPostWithId(2)
                        .enqueue(new Callback<PostContainer>() {
                            @Override
                            public void onResponse(@NonNull Call<PostContainer> call, @NonNull Response<PostContainer> response) {
                                PostContainer postContainer = response.body();
                                if (postContainer != null) {
                                    textView.append(postContainer.getUserId() + "\n");
                                    textView.append(postContainer.getId() + "\n");
                                    textView.append(postContainer.getTitle() + "\n");
                                    textView.append(postContainer.getBody() + "\n");
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<PostContainer> call, @NonNull Throwable t) {
                                textView.append("Request error");
                                t.printStackTrace();
                            }
                        });
            }
        });
    }
}
