package ru.alexeyp.domain.start;


import io.reactivex.Completable;

public interface StartInteractor {
    Completable checkUser();
}
