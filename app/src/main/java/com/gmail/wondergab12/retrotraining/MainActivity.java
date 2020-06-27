package com.gmail.wondergab12.retrotraining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.wondergab12.retrotraining.viewmodel.PostViewModel;

import javax.inject.Inject;

@SuppressWarnings("Convert2Lambda")
public class MainActivity extends AppCompatActivity {

    @Inject TextView textView;
    @Inject Button button;
    @Inject PostViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((MyApplication) getApplication()).getAppComponent(this).inject(this);

        viewModel.getPost().observe(this, new Observer<PostContainer>() {
            @Override
            public void onChanged(PostContainer post) {
                if (post != null) {
                    textView.append(post.getUserId() + "\n");
                    textView.append(post.getId() + "\n");
                    textView.append(post.getTitle() + "\n");
                    textView.append(post.getBody() + "\n");
                }
            }
        });
        viewModel.getErr().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {
                Toast.makeText(MainActivity.this, "Request Error", Toast.LENGTH_SHORT).show();
                throwable.printStackTrace();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.loadPost();
            }
        });
    }
}
