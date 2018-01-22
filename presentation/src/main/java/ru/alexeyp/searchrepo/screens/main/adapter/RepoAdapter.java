package ru.alexeyp.searchrepo.screens.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import ru.alexeyp.searchrepo.databinding.RepositoryItemBinding;
import ru.alexeyp.searchrepo.model.Repository;

public class RepoAdapter extends RecyclerView.Adapter<RepositoryVH> {

    private List<Repository> _repositories;

    public RepoAdapter() {
        _repositories = new ArrayList<>();
    }

    public void setItems(List<Repository> repositories) {
        _repositories.clear();
        _repositories.addAll(repositories);
        notifyDataSetChanged();
    }

    public void addItems(List<Repository> repositories) {
        int positionStart = _repositories.size();
        _repositories.addAll(repositories);
        notifyItemRangeInserted(positionStart, repositories.size());
    }

    @Override
    public RepositoryVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RepositoryItemBinding binding = RepositoryItemBinding.inflate(inflater, parent, false);
        return new RepositoryVH(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(RepositoryVH holder, int position) {
        holder.getBinding().setRepository(_repositories.get(position));
    }

    @Override
    public int getItemCount() {
        return _repositories.size();
    }
}
