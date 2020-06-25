package com.gmail.wondergab12.retrotraining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.wondergab12.retrotraining.viewmodel.PostViewModel;

@SuppressWarnings("Convert2Lambda")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.textView);
        final PostViewModel viewModel = new ViewModelProvider(this).get(PostViewModel.class);
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
        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.loadPost();
            }
        });
    }
}
