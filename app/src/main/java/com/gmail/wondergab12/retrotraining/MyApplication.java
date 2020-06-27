package com.gmail.wondergab12.retrotraining;

import android.app.Application;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.wondergab12.retrotraining.dependencies.ApplicationComponent;
import com.gmail.wondergab12.retrotraining.dependencies.DaggerApplicationComponent;
import com.gmail.wondergab12.retrotraining.dependencies.GlobalModule;
import com.gmail.wondergab12.retrotraining.dependencies.WidgetsModule;

public class MyApplication extends Application {

    public ApplicationComponent getAppComponent(AppCompatActivity activity) {
        GlobalModule globalModule = new GlobalModule(activity);
        WidgetsModule widgetsModule = new WidgetsModule(activity);
        return DaggerApplicationComponent.builder().globalModule(globalModule).widgetsModule(widgetsModule).build();
    }
}
