package ru.alexeyp.data.settings;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;

import ru.alexeyp.data.settings.model.User;

public class AppPreferences {

    private static final String SETTINGS_PREFERENCES = "settings_preferences";

    private final SharedPreferences _preferences;

    public AppPreferences(Context context) {
        _preferences = context.getSharedPreferences(SETTINGS_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void saveUser(User user) {
        savePreference(user);
    }

    public User getUser() {
        return getPreference(User.class, User.KEY);
    }

    public void removeUser() {
        _preferences.edit().remove(User.KEY).apply();
    }

    private <T extends IPreference> T getPreference(Class<T> type, String key) {
        return _preferences.contains(key) ? new Gson().fromJson(getString(key), type) : null;
    }

    private void savePreference(IPreference setting) {
        saveString(setting.getKey(), new Gson().toJson(setting));
    }

    private void saveString(String key, String value) {
        _preferences.edit().putString(key, value).apply();
    }

    private String getString(String key) {
        return _preferences.getString(key, null);
    }
}
