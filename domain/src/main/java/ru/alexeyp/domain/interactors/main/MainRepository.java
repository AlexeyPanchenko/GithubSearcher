package ru.alexeyp.domain.interactors.main;

import java.util.List;

import io.reactivex.Single;
import ru.alexeyp.domain.model.Repository;
import ru.alexeyp.domain.model.SearchQuery;

public interface MainRepository {
    Single<List<Repository>> searchRepositories(SearchQuery searchQuery);
}
