package ru.alexeyp.searchrepo.screens.main;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import ru.alexeyp.domain.interactors.main.MainInteractor;
import ru.alexeyp.domain.interactors.user.UserInteractor;
import ru.alexeyp.searchrepo.router.main.MainRouter;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    private final MainRouter _router;
    private final MainInteractor _mainInteractor;
    private final UserInteractor _userInteractor;

    @Inject
    public MainViewModelFactory(MainRouter router, MainInteractor mainInteractor, UserInteractor userInteractor) {
        _router = router;
        _mainInteractor = mainInteractor;
        _userInteractor = userInteractor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(_router, _mainInteractor, _userInteractor);
    }
}
