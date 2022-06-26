package dev.rooftop.core.httpclient.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.rooftop.core.config.AppConfig;
import dev.rooftop.core.httpclient.domain.HttpRequest;
import dev.rooftop.core.httpclient.exceptions.CustomHttpException;
import dev.rooftop.core.httpclient.interceptors.AuthInterceptor;
import dev.rooftop.core.httpclient.services.AbstractHttpClientService;
import java.io.IOException;
import java.util.Objects;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpClientServiceImpl extends AbstractHttpClientService {

    private static final MediaType APPLICATION_JSON = MediaType.parse("application/json");
    private static HttpClientServiceImpl INSTANCE;
    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;
    private final String url;

    private HttpClientServiceImpl(String url) {
        this.url = url;
        okHttpClient = buildOkHttpClient();
        objectMapper = new ObjectMapper();
    }

    public static HttpClientServiceImpl getInstance(String url) {
        if (INSTANCE == null) {
            INSTANCE = new HttpClientServiceImpl(url);
        }
        return INSTANCE;
    }

    public static HttpClientServiceImpl getInstance() {
        String url = AppConfig.getInstance().getUrl();
        return getInstance(url);
    }

    @Override
    public <T> T get(HttpRequest httpRequest, Class<T> clazz)
            throws CustomHttpException, IOException {

        Headers headers = buildHeaders(httpRequest);
        HttpUrl httpUrl = buildUrl(httpRequest);

        Request request = new Request.Builder()
                .url(httpUrl)
                .headers(headers)
                .get()
                .build();

        Response response = okHttpClient.newCall(request).execute();
        if (response.code() != 200) {
            throw new CustomHttpException(response.message(), response.code());
        }
        String responseString = Objects.requireNonNull(response.body()).string();
        return objectMapper.readValue(responseString, clazz);
    }

    @Override
    public <T> T post(HttpRequest httpRequest, Class<T> clazz)
            throws CustomHttpException, IOException {
        Headers headers = buildHeaders(httpRequest);
        HttpUrl httpUrl = buildUrl(httpRequest);
        RequestBody requestBody = RequestBody.create(
                objectMapper.writeValueAsString(httpRequest.getBody()), APPLICATION_JSON);

        Request request = new Request.Builder()
                .url(httpUrl)
                .headers(headers)
                .post(requestBody)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        if (response.code() != 200) {
            throw new CustomHttpException(response.message(), response.code());
        }
        return objectMapper.readValue(Objects.requireNonNull(response.body()).string(), clazz);
    }

    private HttpUrl buildUrl(HttpRequest httpRequest) {
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(
                HttpUrl.parse(url + "/" + httpRequest.getPath())).newBuilder();
        if (httpRequest.getHttpQueryParam() != null) {
            httpRequest.getHttpQueryParam()
                    .forEach(
                            httpQueryParam -> urlBuilder.setQueryParameter(httpQueryParam.getName(),
                                    httpQueryParam.getValue()));
        }
        return urlBuilder.build();
    }

    private Headers buildHeaders(HttpRequest httpRequest) {
        Headers.Builder headersBuilder = new Headers.Builder();
        if (httpRequest.getHeaders() != null) {
            httpRequest.getHeaders().forEach(
                    httpHeader -> headersBuilder.add(httpHeader.getName(), httpHeader.getValue()));
        }
        return headersBuilder.build();
    }


    private OkHttpClient buildOkHttpClient() {
        return new OkHttpClient.Builder().addInterceptor(new AuthInterceptor()).build();
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public String getUrl() {
        return url;
    }
}
