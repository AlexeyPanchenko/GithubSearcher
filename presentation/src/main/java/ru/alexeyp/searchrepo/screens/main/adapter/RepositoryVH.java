package ru.alexeyp.searchrepo.screens.main.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import ru.alexeyp.searchrepo.databinding.RepositoryItemBinding;

public class RepositoryVH extends RecyclerView.ViewHolder {

    private final RepositoryItemBinding _binding;

    public RepositoryVH(View itemView) {
        super(itemView);
        _binding = DataBindingUtil.bind(itemView);
    }

    RepositoryItemBinding getBinding() {
        return _binding;
    }
}
