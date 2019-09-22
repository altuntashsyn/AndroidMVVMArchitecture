package com.huseyinaltuntas.mvvmgithubrepoproject.data.network.services;

import com.huseyinaltuntas.mvvmgithubrepoproject.data.network.model.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;


public class RepoService {

    private static final String URL = "https://api.github.com/users/";

    private RepoApi mRepoApi;

    private static RepoService instance;


    public static RepoService getInstance() {
        if (instance == null) {
            instance = new RepoService();
        }
        return instance;
    }

    private RepoService() {
        Retrofit mRetrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(URL).build();
        mRepoApi = mRetrofit.create(RepoApi.class);
    }

    public RepoApi getRepoApi() {
        return mRepoApi;
    }

    public interface RepoApi {
        @GET("{user}/repos") Call<List<Repo>> getAllRepos(@Path("user") String user);
    }

}


