package ru.alexeyp.searchrepo.utils.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class PaginationScrollListener extends RecyclerView.OnScrollListener {

    public interface LoadListener {
        void onScrolledDown();
    }

    private LinearLayoutManager _layoutManager;
    private LoadListener _loadListener;
    private boolean _isEnabled;
    private int _previousTotal;

    public PaginationScrollListener(LinearLayoutManager layoutManager, LoadListener loadListener) {
        _layoutManager = layoutManager;
        _loadListener = loadListener;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleThreshold = 4;
        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = _layoutManager.getItemCount();
        int firstVisibleItem = _layoutManager.findFirstVisibleItemPosition();

        if (_isEnabled && (totalItemCount > _previousTotal)) {
            _isEnabled = false;
            _previousTotal = totalItemCount;
        }

        if (!_isEnabled && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            _isEnabled = true;
            _loadListener.onScrolledDown();
        }
    }

    public void setEnabled(boolean enabled) {
        _isEnabled = enabled;
    }

}
