package org.dxworks.utils.java.rest.client

import com.google.api.client.http.HttpRequestInitializer

abstract class RestClient private constructor(pathResolver: PathResolver, protected val httpClient: HttpClient) : IPathResolver by pathResolver {
    constructor(apiBaseUrl: String)
            : this(PathResolver(apiBaseUrl), HttpClient())

    constructor(apiBaseUrl: String, httpRequestInitializer: HttpRequestInitializer?)
            : this(PathResolver(apiBaseUrl), HttpClient(httpRequestInitializer))

    constructor(apiBaseUrl: String, httpClient: HttpClient)
            : this(PathResolver(apiBaseUrl), httpClient)
}
