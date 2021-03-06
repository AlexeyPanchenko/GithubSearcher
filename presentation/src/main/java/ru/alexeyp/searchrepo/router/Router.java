package ru.alexeyp.searchrepo.router;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public abstract class Router {

    protected <T extends Activity> void startActivity(Context context, Class<T> activity) {
        Intent intent = new Intent(context, activity);
        context.startActivity(intent);
    }

    protected <T extends Activity> void startActivityWithClearBackstack(Context context, Class<T> activity){
        Intent intent = new Intent(context, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
}
