package com.gmail.wondergab12.retrotraining.dependencies;

import com.gmail.wondergab12.retrotraining.MainActivity;

import dagger.Component;

@Component(modules = {GlobalModule.class, WidgetsModule.class})
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);
}
