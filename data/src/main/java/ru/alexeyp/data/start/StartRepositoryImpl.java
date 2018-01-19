package ru.alexeyp.data.start;


import io.reactivex.Completable;
import ru.alexeyp.domain.start.StartRepository;

public class StartRepositoryImpl implements StartRepository {

    @Override
    public Completable checkUser() {
        return Completable.error(NullPointerException::new);
    }
}
