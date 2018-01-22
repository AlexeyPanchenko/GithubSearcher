package ru.alexeyp.data.repositories.main;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import ru.alexeyp.data.network.GitHubApi;
import ru.alexeyp.data.network.model.DTORepositories;
import ru.alexeyp.data.network.model.DTORepository;
import ru.alexeyp.domain.interactors.main.MainRepository;
import ru.alexeyp.domain.model.Repository;
import ru.alexeyp.domain.model.SearchQuery;

public class MainRepositoryImpl implements MainRepository {

    private static final int SEARCH_REPO_COUNT = 20;
    private final GitHubApi _api;

    public MainRepositoryImpl(GitHubApi api) {
        _api = api;
    }

    @Override
    public Single<List<Repository>> searchRepositories(SearchQuery searchQuery) {
        return _api.seachRepositories(searchQuery.getQuery(), searchQuery.getPage(), SEARCH_REPO_COUNT)
                .subscribeOn(Schedulers.io())
                .map(this::dtoRepositoriesToDomain);
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
