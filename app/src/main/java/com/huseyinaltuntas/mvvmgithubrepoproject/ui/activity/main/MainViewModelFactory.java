package com.huseyinaltuntas.mvvmgithubrepoproject.ui.activity.main;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.huseyinaltuntas.mvvmgithubrepoproject.data.network.services.RepoService;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    private final RepoService repoService;

    public MainViewModelFactory(RepoService repoService) {
        this.repoService = repoService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(repoService);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
