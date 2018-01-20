package ru.alexeyp.data.network;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private final String _authToken;

    AuthInterceptor(String authToken) {
        _authToken = authToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request authRequest = request.newBuilder()
                .header("Authorization", _authToken)
                .build();
        return chain.proceed(authRequest);
    }
}
