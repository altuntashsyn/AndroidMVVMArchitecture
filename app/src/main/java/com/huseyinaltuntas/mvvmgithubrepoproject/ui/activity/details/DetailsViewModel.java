package com.huseyinaltuntas.mvvmgithubrepoproject.ui.activity.details;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Intent;

import com.huseyinaltuntas.mvvmgithubrepoproject.data.network.model.Repo;


public class DetailsViewModel extends ViewModel {

    MutableLiveData<Repo> repo;

    public DetailsViewModel() {
        this.repo = new MutableLiveData<>();
    }

    public void loadMovieData(Intent intent) {
        assert intent.getExtras() != null;
        Repo repoExtra = intent.getExtras().getParcelable("Repo");

        repo.postValue(repoExtra);
    }

    public MutableLiveData<Repo> getRepos() {
        return repo;
    }
}
