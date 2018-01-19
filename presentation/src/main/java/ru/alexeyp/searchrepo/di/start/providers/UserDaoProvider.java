package ru.alexeyp.searchrepo.di.start.providers;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Provider;

import ru.alexeyp.data.db.UserDao;
import ru.alexeyp.data.db.UserDatabase;

public class UserDaoProvider implements Provider<UserDao> {

    private Context _context;
    private UserDatabase _database;

    @Inject
    public UserDaoProvider(Context context, UserDatabase database) {
        _context = context;
        _database = database;
    }

    @Override
    public UserDao get() {
        return _database.userDao();
    }
}
