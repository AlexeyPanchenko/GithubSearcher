package ru.alexeyp.domain.login;

import io.reactivex.Completable;
import ru.alexeyp.domain.model.Credential;

public interface LoginRepository {
    Completable signIn(Credential credential);
    Completable checkUser();
}
