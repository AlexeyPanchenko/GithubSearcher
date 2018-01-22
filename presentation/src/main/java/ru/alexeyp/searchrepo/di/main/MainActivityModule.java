package ru.alexeyp.searchrepo.di.main;

import ru.alexeyp.searchrepo.router.main.MainRouter;
import ru.alexeyp.searchrepo.router.main.MainRouterImpl;
import ru.alexeyp.searchrepo.screens.main.MainActivity;
import toothpick.config.Module;

public class MainActivityModule extends Module {

    public MainActivityModule(MainActivity activity) {
        bind(MainRouter.class).toInstance(new MainRouterImpl(activity));
    }
}
