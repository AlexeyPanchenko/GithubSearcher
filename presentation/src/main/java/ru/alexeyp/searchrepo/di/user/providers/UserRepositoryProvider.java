package ru.alexeyp.searchrepo.di.user.providers;

import javax.inject.Inject;
import javax.inject.Provider;
import ru.alexeyp.data.repositories.user.UserRepositoryImpl;
import ru.alexeyp.data.settings.AppPreferences;

public class UserRepositoryProvider implements Provider<UserRepositoryImpl> {

    private AppPreferences _preferences;

    @Inject
    public UserRepositoryProvider(AppPreferences preferences) {
        _preferences = preferences;
    }

    @Override
    public UserRepositoryImpl get() {
        return new UserRepositoryImpl(_preferences);
    }
}
