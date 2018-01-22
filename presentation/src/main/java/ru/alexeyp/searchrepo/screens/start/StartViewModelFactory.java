package ru.alexeyp.searchrepo.screens.start;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import javax.inject.Inject;
import ru.alexeyp.domain.interactors.user.UserInteractor;
import ru.alexeyp.searchrepo.router.start.StartRouter;

public class StartViewModelFactory implements ViewModelProvider.Factory {

    private StartRouter _router;
    private UserInteractor _interactor;

    @Inject
    public StartViewModelFactory(StartRouter router, UserInteractor interactor) {
        _router = router;
        _interactor = interactor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new StartViewModel(_router, _interactor);
    }
}
