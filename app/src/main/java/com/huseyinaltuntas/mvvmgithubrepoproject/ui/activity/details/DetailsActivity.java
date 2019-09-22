package com.huseyinaltuntas.mvvmgithubrepoproject.ui.activity.details;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huseyinaltuntas.mvvmgithubrepoproject.R;
import com.huseyinaltuntas.mvvmgithubrepoproject.data.network.model.Repo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.image)
    AppCompatImageView image;
    @BindView(R.id.stars)
    TextView stars;
    @BindView(R.id.issues)
    TextView issues;
    @BindView(R.id.owner)
    TextView owner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        DetailsViewModel viewModel = createViewModel();

        viewModel.getRepos().observe(this, new RepoObserver());

        viewModel.loadMovieData(getIntent());
    }

    private DetailsViewModel createViewModel() {
        return ViewModelProviders.of(this).get(DetailsViewModel.class);
    }

    private class RepoObserver implements Observer<Repo> {
        @Override
        public void onChanged(@Nullable Repo repo) {
            if (repo == null) return;

            stars.setText("Stars: " + repo.getStars());
            issues.setText("Open Issues: " + repo.getOpen_issues());
            owner.setText(repo.getOwner().getLogin());
            Glide.with(getApplicationContext()).load(repo.getOwner().getAvatar_url()).into(image);
        }
    }

}

