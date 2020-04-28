package org.dxworks.utils.java.rest.client;

import com.google.api.client.http.HttpRequestInitializer;
import lombok.experimental.Delegate;

public abstract class RestClient {

    protected HttpClient httpClient;

    @Delegate
    protected PathResolver pathResolver;

    public RestClient() {
        this.httpClient = new HttpClient();
        this.pathResolver = new PathResolver(getApiUrl());
    }

    public RestClient(HttpRequestInitializer httpRequestInitializer) {
        this.httpClient = new HttpClient(httpRequestInitializer);
        this.pathResolver = new PathResolver(getApiUrl());
    }

    /**
     * @return the base api url for the client.
     */
    protected abstract String getApiUrl();

}
