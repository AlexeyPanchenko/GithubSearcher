package ru.alexeyp.searchrepo;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;
import ru.alexeyp.searchrepo.di.Scopes;
import ru.alexeyp.searchrepo.di.application.AppModule;
import toothpick.Toothpick;
import toothpick.configuration.Configuration;
import toothpick.registries.FactoryRegistryLocator;
import toothpick.registries.MemberInjectorRegistryLocator;

public class SearchRepoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        initToothpick();
        initAppScope();
    }

    private void initToothpick() {
        if (BuildConfig.DEBUG) {
            Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes());
        } else {
            Toothpick.setConfiguration(Configuration.forProduction().disableReflection());
            FactoryRegistryLocator.setRootRegistry(new ru.alexeyp.searchrepo.FactoryRegistry());
            MemberInjectorRegistryLocator.setRootRegistry(new ru.alexeyp.searchrepo.MemberInjectorRegistry());
        }
    }

    private void initAppScope() {
        Toothpick.openScope(Scopes.APP_SCOPE)
                .installModules(new AppModule(this));
    }
}
