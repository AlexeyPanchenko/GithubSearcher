package ru.alexeyp.data.repositories.user;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;
import ru.alexeyp.data.network.RetrofitBuilder;
import ru.alexeyp.data.settings.AppPreferences;
import ru.alexeyp.data.settings.model.User;
import ru.alexeyp.domain.interactors.user.UserRepository;
import ru.alexeyp.domain.model.Credential;

public class UserRepositoryImpl implements UserRepository {

    private final AppPreferences _preferences;

    public UserRepositoryImpl(AppPreferences preferences) {
        _preferences = preferences;
    }

    @Override
    public Completable signIn(Credential credential) {
        String token = RetrofitBuilder.defineAuthToken(credential.getLogin(), credential.getPassword());
        return RetrofitBuilder.create(token)
                .signIn()
                .doOnSuccess(user -> _preferences.saveUser(new User(user.login, token)))
                .toCompletable()
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Completable checkUser() {
        return Completable.create(emitter -> {
            try {
                User user = _preferences.getUser();
                if (user != null) {
                    emitter.onComplete();
                } else {
                    emitter.onError(new NullPointerException());
                }
            } catch (Exception e) {
                emitter.onError(e);
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Completable logOut() {
        return Completable.create(emitter -> {
            try {
                _preferences.removeUser();
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        }).subscribeOn(Schedulers.io());
    }
}
