package ru.alexeyp.data.network;

import android.text.TextUtils;
import com.google.gson.Gson;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.alexeyp.data.BuildConfig;

public class RetrofitBuilder {

    public static final String API_BASE_URL = "https://api.github.com";

    public static GitHubApi create() {
        return create(null, null);
    }

    public static GitHubApi create(String login, String password) {
        String authToken = defineAuthToken(login, password);
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(getClient(authToken))
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(GitHubApi.class);
    }

    private static OkHttpClient getClient(String authToken) {
        final OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(loggingInterceptor);
        }
        if (!TextUtils.isEmpty(authToken)) {
            AuthInterceptor authInterceptor = new AuthInterceptor(authToken);
            if (!clientBuilder.interceptors().contains(authInterceptor)) {
                clientBuilder.addInterceptor(authInterceptor);
            }
        }
        return clientBuilder.build();
    }

    private static String defineAuthToken(String login, String password) {
        String authToken = null;
        if (!TextUtils.isEmpty(login) && !TextUtils.isEmpty(password)) {
            authToken = Credentials.basic(login, password);
        }
        return authToken;
    }
}
