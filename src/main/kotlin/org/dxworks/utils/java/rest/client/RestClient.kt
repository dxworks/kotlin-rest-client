package org.dxworks.utils.java.rest.client

import com.google.api.client.http.HttpRequestInitializer
import com.google.common.annotations.VisibleForTesting
import java.util.*
import java.util.stream.Collectors
import java.util.stream.Stream

abstract class RestClient private constructor(private val pathResolver: PathResolver, protected val httpClient: HttpClient) {
    constructor(apiBaseUrl: String)
            : this(PathResolver(apiBaseUrl), HttpClient())

    constructor(apiBaseUrl: String, httpRequestInitializer: HttpRequestInitializer?)
            : this(PathResolver(apiBaseUrl), HttpClient(httpRequestInitializer))

    constructor(apiBaseUrl: String, httpClient: HttpClient)
            : this(PathResolver(apiBaseUrl), httpClient)

    fun getApiPath(variableValues: Map<String, String>, vararg pathVariables: String): String {
        return pathResolver.getApiPath(variableValues, *pathVariables)
    }

    fun replacePlaceholders(v: String, variablesValues: Map<String, String>): String {
        return pathResolver.replacePlaceholders(v, variablesValues)
    }

    fun getApiPath(vararg pathVariables: String): String {
        return pathResolver.getApiPath(*pathVariables)
    }
}
