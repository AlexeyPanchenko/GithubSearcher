package ru.alexeyp.searchrepo.router;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public abstract class Router {

    protected <T extends Activity> void startActivity(Context context, Class<T> activity) {
        Intent intent = new Intent(context, activity);
        context.startActivity(intent);
    }

    protected <T extends Activity> void startActivity(Context context, Class<T> activity, Bundle args) {
        Intent intent = new Intent(context, activity);
        intent.putExtras(args);
        context.startActivity(intent);
    }
}
