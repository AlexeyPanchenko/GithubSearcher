package ru.alexeyp.searchrepo.di.main.providers;

import javax.inject.Inject;
import javax.inject.Provider;
import ru.alexeyp.data.repositories.main.MainRepositoryImpl;
import ru.alexeyp.data.settings.AppPreferences;

public class MainRepositoryProvider implements Provider<MainRepositoryImpl> {

    private final AppPreferences _preferences;

    @Inject
    public MainRepositoryProvider(AppPreferences preferences) {
        _preferences = preferences;
    }

    @Override
    public MainRepositoryImpl get() {
        return new MainRepositoryImpl(_preferences);
    }
}
