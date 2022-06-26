package dev.rooftop.core.httpclient.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.rooftop.core.config.AppConfig;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.Setter;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

public class AuthInterceptor implements Interceptor {

    private final Logger logger = Logger.getLogger(getClass().getName());

    private String token;

    public AuthInterceptor() {
    }

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if (token == null) {
            token = requestToken();
        }

        HttpUrl httpUrl = request.url().newBuilder()
            .addQueryParameter("token", token)
            .build();
        Request authenticatedRequest = request
            .newBuilder()
            .url(httpUrl)
            .build();
        logger.info(
            String.format("Authenticated Http Request: %s", authenticatedRequest.url().url()));
        return chain.proceed(authenticatedRequest);
    }

    public String requestToken() throws IOException {
        logger.info("Requesting token...");
        String url = AppConfig.getInstance().getUrl();
        String email = AppConfig.getInstance().getUserEmail();
        String tokenUrl = String.format("%s/token?email=%s", url, email);
        logger.info(String.format("tokenUrl: %s", tokenUrl));
        Request request = new Request.Builder()
            .url(tokenUrl)
            .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Response response = okHttpClient.newCall(request).execute();
        ObjectMapper mapper = new ObjectMapper();
        TokenResponse tokenResponse = mapper.readValue(
            Objects.requireNonNull(response.body()).string(), TokenResponse.class);
        return tokenResponse.getToken();
    }

    public String getToken() {
        return token;
    }

    @Getter
    @Setter
    protected static class TokenResponse {

        private String token;
    }
}
