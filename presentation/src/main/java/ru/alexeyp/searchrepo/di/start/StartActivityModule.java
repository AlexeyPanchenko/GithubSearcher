package ru.alexeyp.searchrepo.di.start;

import ru.alexeyp.searchrepo.router.start.StartRouter;
import ru.alexeyp.searchrepo.router.start.StartRouterImpl;
import ru.alexeyp.searchrepo.screens.start.StartActivity;
import toothpick.config.Module;

public class StartActivityModule extends Module {

    public StartActivityModule(StartActivity activity) {
        bind(StartRouter.class).toInstance(new StartRouterImpl(activity));
    }
}
