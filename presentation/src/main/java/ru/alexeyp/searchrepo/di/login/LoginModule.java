package ru.alexeyp.searchrepo.di.login;

import ru.alexeyp.domain.login.LoginInteractor;
import ru.alexeyp.domain.login.LoginRepository;
import ru.alexeyp.searchrepo.di.login.providers.LoginInteractorProvider;
import ru.alexeyp.searchrepo.di.login.providers.LoginRepositoryProvider;
import toothpick.config.Module;

public class LoginModule extends Module {

    public LoginModule() {
        bind(LoginRepository.class).toProvider(LoginRepositoryProvider.class).providesSingletonInScope();
        bind(LoginInteractor.class).toProvider(LoginInteractorProvider.class).providesSingletonInScope();
    }
}
