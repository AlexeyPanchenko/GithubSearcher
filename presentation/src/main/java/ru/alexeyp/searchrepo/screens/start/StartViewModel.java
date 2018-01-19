package ru.alexeyp.searchrepo.screens.start;


import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.alexeyp.domain.start.StartInteractor;
import ru.alexeyp.searchrepo.di.Scopes;
import ru.alexeyp.searchrepo.router.start.StartRouter;
import toothpick.Toothpick;

class StartViewModel extends ViewModel {

    @Inject
    StartViewModel(StartRouter router, StartInteractor interactor) {
        interactor.checkUser()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(router::navigateToMain, throwable -> router.navigateToLogin());
    }

    @Override
    protected void onCleared() {
        Toothpick.closeScope(Scopes.START_SCOPE);
        super.onCleared();
    }
}
