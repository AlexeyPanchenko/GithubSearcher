package ru.alexeyp.data.repositories.main;

import java.util.List;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import ru.alexeyp.data.network.RetrofitBuilder;
import ru.alexeyp.data.network.model.DTORepositories;
import ru.alexeyp.data.network.model.DTORepository;
import ru.alexeyp.data.settings.AppPreferences;
import ru.alexeyp.domain.interactors.main.MainRepository;
import ru.alexeyp.domain.model.Repository;
import ru.alexeyp.domain.model.SearchQuery;

public class MainRepositoryImpl implements MainRepository {

    private static final int SEARCH_REPO_COUNT = 20;
    private final AppPreferences _preferences;

    public MainRepositoryImpl(AppPreferences preferences) {
        _preferences = preferences;
    }

    @Override
    public Single<List<Repository>> searchRepositories(SearchQuery searchQuery) {
        //TODO: RetrofitBuilder
        String token = _preferences.getUser() != null ? _preferences.getUser().getToken() : null;
        return RetrofitBuilder.create(token)
                .seachRepositories(searchQuery.getQuery(), searchQuery.getPage(), SEARCH_REPO_COUNT)
                .map(this::dtoRepositoriesToDomain)
                .subscribeOn(Schedulers.io());
    }


    private List<Repository> dtoRepositoriesToDomain(DTORepositories repositories) {
        return Observable.fromIterable(repositories.items)
                .map(this::dtoRepositoryToDomain)
                .subscribeOn(Schedulers.newThread())
                .toList()
                .blockingGet();
    }

    private Repository dtoRepositoryToDomain(DTORepository dtoRepository) {
        return new Repository(
                dtoRepository.name,
                dtoRepository.fullName,
                dtoRepository.description,
                dtoRepository.owner.avatar);
    }
}
