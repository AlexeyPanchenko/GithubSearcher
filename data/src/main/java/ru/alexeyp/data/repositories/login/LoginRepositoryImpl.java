package ru.alexeyp.data.repositories.login;

import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Credentials;
import ru.alexeyp.data.network.RetrofitBuilder;
import ru.alexeyp.data.settings.AppPreferences;
import ru.alexeyp.data.settings.model.User;
import ru.alexeyp.domain.login.LoginRepository;
import ru.alexeyp.domain.model.Credential;

public class LoginRepositoryImpl implements LoginRepository {

    private final AppPreferences _preferences;

    public LoginRepositoryImpl(AppPreferences preferences) {
        _preferences = preferences;
    }

    @Override
    public Completable signIn(Credential credential) {
        String token = defineAuthToken(credential.getLogin(), credential.getPassword());
        // TODO: разобраться с корректным инжектом GitHubApi
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
                    Log.d("TTT", "User = " + new Gson().toJson(user));
                    emitter.onComplete();
                } else {
                    Log.d("TTT", "EMPTY");
                    emitter.onError(new NullPointerException());
                }
            } catch (Exception e) {
                Log.d("TTT", "ERROR");
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
