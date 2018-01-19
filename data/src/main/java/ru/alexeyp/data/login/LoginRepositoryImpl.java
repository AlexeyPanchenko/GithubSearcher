package ru.alexeyp.data.login;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;
import ru.alexeyp.data.network.RetrofitBuilder;
import ru.alexeyp.domain.login.LoginRepository;
import ru.alexeyp.domain.model.Credential;

public class LoginRepositoryImpl implements LoginRepository {

    @Override
    public Completable signIn(Credential credential) {
        return RetrofitBuilder.create(credential.getLogin(), credential.getPassword())
                .signIn()
                .toCompletable()
                .subscribeOn(Schedulers.io());
    }
}
