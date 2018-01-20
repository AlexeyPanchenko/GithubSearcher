package ru.alexeyp.searchrepo.di.login.providers;

import javax.inject.Inject;
import javax.inject.Provider;
import ru.alexeyp.data.repositories.login.LoginRepositoryImpl;
import ru.alexeyp.data.settings.AppPreferences;

public class LoginRepositoryProvider implements Provider<LoginRepositoryImpl> {

    private AppPreferences _preferences;

    @Inject
    public LoginRepositoryProvider(AppPreferences preferences) {
        _preferences = preferences;
    }

    @Override
    public LoginRepositoryImpl get() {
        return new LoginRepositoryImpl(_preferences);
    }
}
