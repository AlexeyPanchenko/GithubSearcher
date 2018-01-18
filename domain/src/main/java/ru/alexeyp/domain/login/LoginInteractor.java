package ru.alexeyp.domain.login;

import io.reactivex.Completable;

public interface LoginInteractor {

    Completable signIn();
}
