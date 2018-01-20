package ru.alexeyp.data.network;

import io.reactivex.Single;
import retrofit2.http.GET;
import ru.alexeyp.data.network.model.User;

public interface GitHubApi {

    @GET("user")
    Single<User> signIn();
}
