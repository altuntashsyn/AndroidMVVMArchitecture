package com.huseyinaltuntas.mvvmgithubrepoproject.ui.activity.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huseyinaltuntas.mvvmgithubrepoproject.R;
import com.huseyinaltuntas.mvvmgithubrepoproject.data.network.model.Repo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder> {

    public interface OnRepoAdapter{
        void onRepoClicked(Repo Repo);
    }

    private List<Repo> mItems;
    private OnRepoAdapter mListener;

    public RepoAdapter(OnRepoAdapter listener) {
        mListener = listener;
        mItems = new ArrayList<>();
    }

    public void setItems(List<Repo> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Repo Repo = getItem(position);

        holder.setOnClickListener(Repo);
        holder.setTitle(Repo.getName());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    private Repo getItem(int position) {
        return mItems.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.title) TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setTitle(String title) {
            this.title.setText(title);
        }


        private void setOnClickListener(Repo Repo) {
            itemView.setTag(Repo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onRepoClicked((Repo) view.getTag());
        }
    }
}
