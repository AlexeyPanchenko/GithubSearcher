package ru.alexeyp.searchrepo.screens.main;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.alexeyp.domain.interactors.main.MainInteractor;
import ru.alexeyp.domain.interactors.user.UserInteractor;
import ru.alexeyp.domain.model.SearchQuery;
import ru.alexeyp.searchrepo.di.Scopes;
import ru.alexeyp.searchrepo.model.Repository;
import ru.alexeyp.searchrepo.router.main.MainRouter;
import ru.alexeyp.searchrepo.utils.RxUtils;
import ru.alexeyp.searchrepo.utils.base.LCEViewModel;
import toothpick.Toothpick;

public class MainViewModel extends LCEViewModel {

    private final MainRouter _router;
    private final MainInteractor _mainInteractor;
    private final UserInteractor _userInteractor;
    private Disposable _disposable;
    private List<Repository> _repositories;
    private List<Repository> _newRepositories;
    private boolean _isAuth;
    private String _query;

    @Inject
    public MainViewModel(MainRouter router, MainInteractor mainInteractor, UserInteractor userInteractor) {
        _router = router;
        _mainInteractor = mainInteractor;
        _userInteractor = userInteractor;
        _repositories = new ArrayList<>();
        _newRepositories = new ArrayList<>();
        _userInteractor.checkUser()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> _isAuth = true, t -> _isAuth = false);
    }

    void onQueryTextChanged(String query) {
        RxUtils.unsubscribe(_disposable);
        _query = query;
        if (TextUtils.isEmpty(query)) {
            _repositories.clear();
            setContentState();
        } else {
            loadRepositories(query);
        }
    }

    void onScrolledDown() {
        if (!_repositories.isEmpty()) {
            loadMore();
        }
    }

    void onOverflowClicked() {
        RxUtils.unsubscribe(_disposable);
        _disposable = _userInteractor.logOut()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(_router::navigateToLogin, this::setErrorState);
    }

    void onInfoDialogClosed() {
        setContentState();
    }

    List<Repository> getRepositories() {
        return _repositories;
    }

    List<Repository> getNewRepositories() {
        return _newRepositories;
    }

    String getQuery() {
        return _query;
    }

    boolean isAuth() {
        return _isAuth;
    }

    @Override
    protected void onCleared() {
        RxUtils.unsubscribe(_disposable);
        Toothpick.closeScope(Scopes.MAIN_SCOPE);
        super.onCleared();
    }

    private void loadRepositories(String query) {
        _disposable = _mainInteractor.searchRepositories(new SearchQuery(query, 1))
                .doOnSubscribe(d -> setProgressState())
                .flatMap(this::domainReposToPresentation)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onRepositoriesSearched,  this::onError);
    }

    private void onRepositoriesSearched(List<Repository> repositories) {
        _repositories = repositories;
        setContentState();
    }

    private void loadMore() {
        RxUtils.unsubscribe(_disposable);
        _disposable = _mainInteractor.loadMore()
                .doOnSubscribe(d -> setState(MainState.PROGRESS_MORE))
                .flatMap(this::domainReposToPresentation)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onMoreLoaded, this::onError);
    }

    private void onMoreLoaded(List<Repository> repositories) {
        if (repositories.isEmpty()) {
            _newRepositories.clear();
            setState(MainState.LOADED_ALL);
        } else  {
            _newRepositories = repositories;
            _repositories.addAll(repositories);
            setState(MainState.MORE_LOADED);
        }
    }

    private Single<List<Repository>> domainReposToPresentation(List<ru.alexeyp.domain.model.Repository> domainRepos) {
        return Observable.fromIterable(domainRepos)
                .map(this::domainRepoToPresentation)
                .subscribeOn(Schedulers.newThread())
                .toList();
    }

    private Repository domainRepoToPresentation(ru.alexeyp.domain.model.Repository repository) {
        return new Repository(repository.getFullName(), repository.getDescription(), repository.getAvatarUrl());
    }

    private void onError(Throwable throwable) {
        if (throwable.getMessage().contains("403")) {
            setState(MainState.FORBIDDEN_ERROR);
        } else setErrorState(throwable);
    }
}
