package ru.alexeyp.searchrepo.screens.start;

import javax.inject.Inject;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import ru.alexeyp.domain.start.StartInteractor;
import ru.alexeyp.searchrepo.router.start.StartRouter;

public class StartViewModelFactory implements ViewModelProvider.Factory {

    private StartRouter _router;
    private StartInteractor _interactor;

    @Inject
    public StartViewModelFactory(StartRouter router, StartInteractor interactor) {
        _router = router;
        _interactor = interactor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new StartViewModel(_router, _interactor);
    }
}
