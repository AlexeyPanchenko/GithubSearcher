package ru.alexeyp.domain.login;

import io.reactivex.Completable;
import ru.alexeyp.domain.model.Credential;

public interface LoginInteractor {

    Completable signIn(Credential credential);
}
