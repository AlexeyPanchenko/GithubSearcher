package ru.alexeyp.searchrepo.screens.login;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import ru.alexeyp.searchrepo.R;
import ru.alexeyp.searchrepo.di.Scopes;
import ru.alexeyp.searchrepo.di.login.LoginActivityModule;
import ru.alexeyp.searchrepo.di.login.LoginModule;
import toothpick.Toothpick;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel _viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initScope();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Log.d("TTT", Toothpick.openScopes(Scopes.APP_SCOPE).toString());
        _viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
    }

    private void initScope() {
        Toothpick.openScopes(Scopes.APP_SCOPE, Scopes.LOGIN_SCOPE)
                .installModules(new LoginModule());
        Toothpick.openScopes(Scopes.LOGIN_SCOPE, Scopes.LOGIN_ACTIVITY_SCOPE)
                .installModules(new LoginActivityModule(this));
    }

    @Override
    protected void onDestroy() {
        Toothpick.closeScope(Scopes.LOGIN_ACTIVITY_SCOPE);
        super.onDestroy();
    }
}
