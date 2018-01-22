package ru.alexeyp.searchrepo.utils;

import io.reactivex.disposables.Disposable;

public class RxUtils {

    public static void unsubscribe(Disposable subscription) {
        if (subscription != null && !subscription.isDisposed()) {
            subscription.dispose();
        }
    }
}
