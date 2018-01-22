package ru.alexeyp.searchrepo.di.user;

import ru.alexeyp.domain.interactors.user.UserInteractor;
import ru.alexeyp.domain.interactors.user.UserRepository;
import ru.alexeyp.searchrepo.di.user.providers.UserInteractorProvider;
import ru.alexeyp.searchrepo.di.user.providers.UserRepositoryProvider;
import toothpick.config.Module;

public class LoginModule extends Module {

    public LoginModule() {
        bind(UserRepository.class).toProvider(UserRepositoryProvider.class).providesSingletonInScope();
        bind(UserInteractor.class).toProvider(UserInteractorProvider.class).providesSingletonInScope();
    }
}
