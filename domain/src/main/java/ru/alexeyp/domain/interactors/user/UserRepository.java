package ru.alexeyp.domain.interactors.user;

import io.reactivex.Completable;
import ru.alexeyp.domain.model.Credential;

public interface UserRepository {
    Completable signIn(Credential credential);
    Completable checkUser();
    Completable logOut();
}
