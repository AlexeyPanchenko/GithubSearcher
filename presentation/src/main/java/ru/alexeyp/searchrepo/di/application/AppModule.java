package ru.alexeyp.searchrepo.di.application;

import android.content.Context;

import ru.alexeyp.data.db.DBHelper;
import ru.alexeyp.data.db.UserDatabase;
import toothpick.config.Module;

public class AppModule extends Module {

    public AppModule(Context context) {
        bind(Context.class).toInstance(context);
        bind(UserDatabase.class).toInstance(DBHelper.createUserDatabase(context));
    }
}
