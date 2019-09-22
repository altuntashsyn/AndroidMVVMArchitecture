package com.huseyinaltuntas.mvvmgithubrepoproject.ui.activity.main;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.huseyinaltuntas.mvvmgithubrepoproject.data.network.model.Repo;
import com.huseyinaltuntas.mvvmgithubrepoproject.data.network.services.RepoService;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainViewModel extends ViewModel {

    private MutableLiveData<List<Repo>> repoList;
    private MutableLiveData<Boolean> isLoading;

    private RepoService repoService;

    MainViewModel(RepoService repoService) {
        this.repoService = repoService;
        repoList = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
    }

    MutableLiveData<List<Repo>> getRepos() {
        return repoList;
    }
    MutableLiveData<Boolean> getLoadingStatus() {
        return isLoading;
    }

    void loadReposNetwork(String user) {
        setIsLoading(true);

        Call<List<Repo>> repoCall = repoService.getRepoApi().getAllRepos(user);
        repoCall.enqueue(new RepoCallback());
    }
    void showEmptyList() { setRepos(Collections.<Repo>emptyList()); }

    private void setIsLoading(boolean loading) {
        isLoading.postValue(loading);
    }
    private void setRepos(List<Repo> Repos) {
        setIsLoading(false);
        repoList.postValue(Repos);
    }

    /**
     * Callback
     **/
    private class RepoCallback implements Callback<List<Repo>> {

        @Override
        public void onResponse(@NonNull Call<List<Repo>> call, @NonNull Response<List<Repo>> response) {
            List<Repo> RepoResponse = response.body();

            if (RepoResponse != null) {
                setRepos(RepoResponse);
            } else {
                setRepos(Collections.<Repo>emptyList());
            }
        }

        @Override
        public void onFailure(Call<List<Repo>> call, Throwable t) {
            setRepos(Collections.<Repo>emptyList());

        }
    }
}
