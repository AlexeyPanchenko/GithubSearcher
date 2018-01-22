package ru.alexeyp.data.network;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.alexeyp.data.network.model.DTORepositories;
import ru.alexeyp.data.network.model.DTOUser;

public interface GitHubApi {

    @GET("user")
    Single<DTOUser> signIn();

    @GET("search/repositories")
    Single<DTORepositories> seachRepositories(
            @Query("q") String query,
            @Query("page") int page,
            @Query("per_page") int count);
}
