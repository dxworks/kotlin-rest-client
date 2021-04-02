package org.dxworks.utils.java.rest.client.providers

import com.google.api.client.http.HttpRequest
import com.google.api.client.http.HttpRequestInitializer

open class CompositeHttpRequestInitializer(
    vararg initializers: HttpRequestInitializer?
) : HttpRequestInitializer {

    private val initializersList: List<HttpRequestInitializer> = initializers.filterNotNull()

    override fun initialize(request: HttpRequest) {
        initializersList.forEach { it.initialize(request) }
    }

}
