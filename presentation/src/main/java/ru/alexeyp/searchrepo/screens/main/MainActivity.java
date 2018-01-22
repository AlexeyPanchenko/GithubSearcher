package ru.alexeyp.searchrepo.screens.main;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.alexeyp.searchrepo.R;
import ru.alexeyp.searchrepo.databinding.MainActivityBinding;
import ru.alexeyp.searchrepo.di.Scopes;
import ru.alexeyp.searchrepo.di.main.MainActivityModule;
import ru.alexeyp.searchrepo.di.main.MainModule;
import ru.alexeyp.searchrepo.screens.main.adapter.RepoAdapter;
import ru.alexeyp.searchrepo.utils.State;
import ru.alexeyp.searchrepo.utils.ui.InfoDialogFragment;
import ru.alexeyp.searchrepo.utils.ui.PaginationScrollListener;
import toothpick.Scope;
import toothpick.Toothpick;

public class MainActivity extends AppCompatActivity implements PaginationScrollListener.LoadListener,
        InfoDialogFragment.CloseListener {

    @Inject
    MainViewModelFactory factory;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_repositories)
    RecyclerView rvRepositories;

    private MainViewModel _viewModel;
    private RepoAdapter _adapter;
    private PaginationScrollListener _scrollListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initScope();
        super.onCreate(savedInstanceState);
        initViewModel();
        initBinding();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search_action);
        initSearchView(searchItem);
        MenuItem overflowItem = menu.findItem(R.id.overflow_action);
        overflowItem.setTitle(_viewModel.isAuth() ? R.string.exit : R.string.login_signIn);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.overflow_action:
                _viewModel.onOverflowClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void loadNewData() {
        _viewModel.loadMore();
    }

    @Override
    public void onDialogClosed() {
        _viewModel.onInfoDialogClosed();
    }

    @Override
    protected void onDestroy() {
        Toothpick.closeScope(Scopes.MAIN_ACTIVITY_SCOPE);
        super.onDestroy();
    }

    private void initScope() {
        Toothpick.openScopes(Scopes.USER_SCOPE, Scopes.MAIN_SCOPE).installModules(new MainModule());
        Scope scope = Toothpick.openScopes(Scopes.MAIN_SCOPE, Scopes.MAIN_ACTIVITY_SCOPE);
        scope.installModules(new MainActivityModule(this));
        Toothpick.inject(this, scope);
    }

    private void initViewModel() {
        _viewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);
        _viewModel.getState().observe(this, state -> {
            if (state != null) {
                switch (state.get()) {
                    case State.CONTENT:
                        _adapter.setItems(_viewModel.getRepositories());
                        _scrollListener.setEnabled(false);
                        break;
                    case MainState.MORE_LOADED:
                        _adapter.addItems(_viewModel.getNewRepositories());
                        _scrollListener.setEnabled(false);
                        break;
                    case MainState.LOADED_ALL:
                        _scrollListener.setEnabled(true);
                        break;
                    case State.ERROR:
                        InfoDialogFragment
                                .showDialog(getSupportFragmentManager(), getString(R.string.something_wrong));
                        break;
                }
            }
        });
    }

    private void initBinding() {
        MainActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        binding.setViewModel(_viewModel);
        initView();
    }

    private void initView() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initRecyclerView();
    }

    private void initRecyclerView() {
        _adapter = new RepoAdapter();
        rvRepositories.setAdapter(_adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        _scrollListener = new PaginationScrollListener(layoutManager, this);
        rvRepositories.setLayoutManager(layoutManager);
        rvRepositories.addOnScrollListener(_scrollListener);
        _adapter.setItems(_viewModel.getRepositories());
    }

    private void initSearchView(MenuItem searchItem) {
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);
        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.onActionViewExpanded();
        searchView.setQuery(_viewModel.getQuery(), true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                _viewModel.onQueryTextChanged(newText);
                return true;
            }
        });
        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return true;
            }
        });
    }
}
