package ru.alexeyp.domain.start;


import io.reactivex.Completable;

public interface StartRepository {
    Completable checkUser();
}
