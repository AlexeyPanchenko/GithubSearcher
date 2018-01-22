package ru.alexeyp.searchrepo.screens.start;

import android.arch.lifecycle.ViewModel;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.alexeyp.domain.interactors.user.UserInteractor;
import ru.alexeyp.searchrepo.router.start.StartRouter;

class StartViewModel extends ViewModel {

    @Inject
    StartViewModel(StartRouter router, UserInteractor interactor) {
        interactor.checkUser()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(router::navigateToMain, throwable -> router.navigateToLogin());
    }
}
