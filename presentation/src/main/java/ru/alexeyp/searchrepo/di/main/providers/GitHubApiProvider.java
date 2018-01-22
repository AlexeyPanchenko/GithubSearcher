package ru.alexeyp.searchrepo.di.main.providers;

import javax.inject.Inject;
import javax.inject.Provider;
import ru.alexeyp.data.network.GitHubApi;
import ru.alexeyp.data.network.RetrofitBuilder;
import ru.alexeyp.data.settings.AppPreferences;

public class GitHubApiProvider implements Provider<GitHubApi> {

    private final AppPreferences _preferences;

    @Inject
    public GitHubApiProvider(AppPreferences preferences) {
        _preferences = preferences;
    }

    @Override
    public GitHubApi get() {
        String token = _preferences.getUser() != null ? _preferences.getUser().getToken() : null;
        return RetrofitBuilder.create(token);
    }
}
