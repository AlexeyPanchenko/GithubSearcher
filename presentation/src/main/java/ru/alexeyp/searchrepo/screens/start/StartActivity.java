package ru.alexeyp.searchrepo.screens.start;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import javax.inject.Inject;
import ru.alexeyp.searchrepo.di.Scopes;
import ru.alexeyp.searchrepo.di.login.LoginModule;
import ru.alexeyp.searchrepo.di.start.StartActivityModule;
import toothpick.Scope;
import toothpick.Toothpick;

public class StartActivity extends AppCompatActivity {

    @Inject
    StartViewModelFactory factory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initScope();
        super.onCreate(savedInstanceState);
        ViewModelProviders.of(this, factory).get(StartViewModel.class);
    }

    private void initScope() {
        Toothpick.openScopes(Scopes.APP_SCOPE, Scopes.LOGIN_SCOPE).installModules(new LoginModule());
        Scope scope = Toothpick.openScopes(Scopes.LOGIN_SCOPE, Scopes.START_ACTIVITY_SCOPE);
        scope.installModules(new StartActivityModule(this));
        Toothpick.inject(this, scope);
    }

    @Override
    protected void onDestroy() {
        Toothpick.closeScope(Scopes.START_ACTIVITY_SCOPE);
        super.onDestroy();
    }
}
