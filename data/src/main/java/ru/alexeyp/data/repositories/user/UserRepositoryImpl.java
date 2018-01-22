package ru.alexeyp.data.repositories.user;

import android.text.TextUtils;
import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Credentials;
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
        String token = defineAuthToken(credential.getLogin(), credential.getPassword());
        // TODO: RetrofitBuilder
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

    private String defineAuthToken(String login, String password) {
        String authToken = null;
        if (!TextUtils.isEmpty(login) && !TextUtils.isEmpty(password)) {
            authToken = Credentials.basic(login, password);
        }
        return authToken;
    }
}
