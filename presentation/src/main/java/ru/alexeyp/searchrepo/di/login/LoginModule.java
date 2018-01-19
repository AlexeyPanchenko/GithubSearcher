package ru.alexeyp.searchrepo.di.login;

import ru.alexeyp.data.login.LoginRepositoryImpl;
import ru.alexeyp.domain.login.LoginInteractor;
import ru.alexeyp.domain.login.LoginRepository;
import ru.alexeyp.searchrepo.di.login.providers.LoginInteractorProvider;
import toothpick.config.Module;

public class LoginModule extends Module {

    public LoginModule() {
        bind(LoginRepository.class).toInstance(new LoginRepositoryImpl());
        bind(LoginInteractor.class).toProvider(LoginInteractorProvider.class).providesSingletonInScope();
    }
}
