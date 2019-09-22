package com.huseyinaltuntas.mvvmgithubrepoproject.ui.activity.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.huseyinaltuntas.mvvmgithubrepoproject.R;
import com.huseyinaltuntas.mvvmgithubrepoproject.data.DataManager;
import com.huseyinaltuntas.mvvmgithubrepoproject.data.network.model.Repo;
import com.huseyinaltuntas.mvvmgithubrepoproject.ui.activity.details.DetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements RepoAdapter.OnRepoAdapter {

    RepoAdapter RepoAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.empty_view)
    TextView emptyView;
    @BindView(R.id.btn_submit)
    Button btn_submit;
    @BindView(R.id.edittext_username)
    EditText edittext_username;

    MainViewModel viewModel;

    private MainViewModel createViewModel() {
        MainViewModelFactory factory = new MainViewModelFactory(DataManager.getInstance().getRepoService());
        return ViewModelProviders.of(this, factory).get(MainViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_repo);
        ButterKnife.bind(this);

        RepoAdapter = new RepoAdapter(this);
        recyclerView.setAdapter(RepoAdapter);

        viewModel = createViewModel();

        viewModel.getLoadingStatus().observe(this, new LoadingObserver());
        viewModel.getRepos().observe(this, new RepoObserver());

        listeners();
    }

    private void listeners() {
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.loadReposNetwork(edittext_username.getText().toString());
            }
        });

    }

    @Override
    public void onRepoClicked(Repo Repo) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("Repo", Repo);
        startActivity(intent);
    }

    //Observer
    private class LoadingObserver implements Observer<Boolean> {

        @Override
        public void onChanged(@Nullable Boolean isLoading) {
            if (isLoading == null) return;

            if (isLoading) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        }
    }

    private class RepoObserver implements Observer<List<Repo>> {

        @Override
        public void onChanged(@Nullable List<Repo> Repos) {
            if (Repos == null) return;
            RepoAdapter.setItems(Repos);

            if (Repos.isEmpty()) {
                emptyView.setVisibility(View.VISIBLE);
            } else {
                emptyView.setVisibility(View.GONE);
            }
        }
    }
}
