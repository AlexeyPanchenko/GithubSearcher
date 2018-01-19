package ru.alexeyp.domain.start;


import io.reactivex.Completable;

public class StartInteractorImpl implements StartInteractor {

    private StartRepository _repository;

    public StartInteractorImpl(StartRepository repository) {
        this._repository = repository;
    }

    @Override
    public Completable checkUser() {
        return _repository.checkUser();
    }
}
