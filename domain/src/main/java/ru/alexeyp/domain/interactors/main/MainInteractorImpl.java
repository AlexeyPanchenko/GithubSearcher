package ru.alexeyp.domain.interactors.main;

import java.util.List;

import io.reactivex.Single;
import ru.alexeyp.domain.model.Repository;
import ru.alexeyp.domain.model.SearchQuery;

public class MainInteractorImpl implements MainInteractor {

    private final MainRepository _mainRepository;
    private SearchQuery _searchQuery;

    public MainInteractorImpl(MainRepository mainRepository) {
        _mainRepository = mainRepository;
    }

    @Override
    public Single<List<Repository>> searchRepositories(SearchQuery searchQuery) {
        _searchQuery = searchQuery;
        return _mainRepository.searchRepositories(searchQuery);
    }

    @Override
    public Single<List<Repository>> loadMore() {
        int newPage = _searchQuery.getPage() + 1;
        _searchQuery.setPage(newPage);
        return _mainRepository.searchRepositories(_searchQuery);
    }

}
