package ru.alexeyp.searchrepo.screens.login;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.alexeyp.searchrepo.R;
import ru.alexeyp.searchrepo.di.Scopes;
import ru.alexeyp.searchrepo.di.login.LoginActivityModule;
import ru.alexeyp.searchrepo.di.login.LoginModule;
import ru.alexeyp.searchrepo.utils.State;
import toothpick.Scope;
import toothpick.Toothpick;

public class LoginActivity extends AppCompatActivity {

    @Inject
    LoginViewModelFactory factory;

    @BindView(R.id.et_username)
    EditText etUsername;

    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.tv_sign_in)
    TextView tvSignIn;

    @BindView(R.id.tv_sign_in_anonymous)
    TextView tvSignInAnon;

    private LoginViewModel _viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initScope();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
        initViewModel();
    }

    @OnClick(R.id.tv_sign_in)
    void clickSignIn() {
        _viewModel.onSignInClicked(etUsername.getText().toString(), etPassword.getText().toString());
    }

    @OnClick(R.id.tv_sign_in_anonymous)
    void clickSignInAnon() {
        _viewModel.onSignInAnonClicked();
    }

    @Override
    protected void onDestroy() {
        Toothpick.closeScope(Scopes.LOGIN_ACTIVITY_SCOPE);
        super.onDestroy();
    }

    private void initScope() {
        Toothpick.openScopes(Scopes.APP_SCOPE, Scopes.LOGIN_SCOPE).installModules(new LoginModule());
        Scope scope = Toothpick.openScopes(Scopes.LOGIN_SCOPE, Scopes.LOGIN_ACTIVITY_SCOPE);
        scope.installModules(new LoginActivityModule(this));
        Toothpick.inject(this, scope);
    }

    private void initViewModel() {
        _viewModel = ViewModelProviders.of(this, factory).get(LoginViewModel.class);
        _viewModel.getState().observe(this, state -> {
            if (state != null) {
                switch (state.get()) {
                    case State.CONTENT:
                        break;
                    case State.PROGRESS:
                        break;
                    case State.ERROR:
                        break;
                    case LoginState.ERROR_NAME:
                        etUsername.setError(getString(R.string.login_error_name));
                        break;
                    case LoginState.ERROR_PASSWORD:
                        etPassword.setError(getString(R.string.login_error_password));
                        break;
                }
            }

        });
    }
}
