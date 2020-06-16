package org.dxworks.utils.java.rest.client;

import com.google.api.client.http.HttpRequestInitializer;
import lombok.experimental.Delegate;

public abstract class RestClient {

    protected HttpClient httpClient;

    @Delegate
    protected PathResolver pathResolver;

    public RestClient(String apiBaseUrl) {
        this.httpClient = new HttpClient();
        this.pathResolver = new PathResolver(apiBaseUrl);
    }

    public RestClient(String apiBaseUrl, HttpRequestInitializer httpRequestInitializer) {
        this.httpClient = new HttpClient(httpRequestInitializer);
        this.pathResolver = new PathResolver(apiBaseUrl);
    }

    public RestClient(String apiBaseUrl, HttpClient httpClient) {
        this.httpClient = httpClient;
        this.pathResolver = new PathResolver(apiBaseUrl);
    }
}
