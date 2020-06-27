package com.gmail.wondergab12.retrotraining.dependencies;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

import com.gmail.wondergab12.retrotraining.R;

import dagger.Module;
import dagger.Provides;

@Module
public class WidgetsModule {

    private Activity activity;

    public WidgetsModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    TextView providesTextView() {
        return activity.findViewById(R.id.textView);
    }

    @Provides
    Button provideButton() {
        return activity.findViewById(R.id.button);
    }
}
