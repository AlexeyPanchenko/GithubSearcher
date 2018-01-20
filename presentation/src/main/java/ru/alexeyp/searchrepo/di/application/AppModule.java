package ru.alexeyp.searchrepo.di.application;

import android.content.Context;
import ru.alexeyp.data.settings.AppPreferences;
import toothpick.config.Module;

public class AppModule extends Module {

    public AppModule(Context context) {
        bind(Context.class).toInstance(context);
        bind(AppPreferences.class).toInstance(new AppPreferences(context));
    }
}
