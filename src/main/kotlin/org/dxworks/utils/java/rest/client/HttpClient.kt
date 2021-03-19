package org.dxworks.utils.java.rest.client

import com.google.api.client.http.*
import com.google.api.client.http.apache.v2.ApacheHttpTransport
import com.google.api.client.http.json.JsonHttpContent
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.JsonObjectParser
import com.google.api.client.json.jackson2.JacksonFactory
import org.dxworks.utils.java.rest.client.providers.CompositeHttpRequestInitializer
import org.dxworks.utils.java.rest.client.response.HttpResponse

open class HttpClient(private val httpRequestInitializer: HttpRequestInitializer? = null) {

    open fun get(url: GenericUrl, customRequestInitializer: HttpRequestInitializer? = null): HttpResponse {
        val requestFactory = getHttpRequestFactory(CompositeHttpRequestInitializer(httpRequestInitializer, customRequestInitializer))
        val request = requestFactory.buildGetRequest(url)
        return HttpResponse(request.execute())
    }

    open fun <T> getAndProcess(url: GenericUrl, customRequestInitializer: HttpRequestInitializer? = null, processor: (HttpResponse) -> T): T {
        return processor(get(url, customRequestInitializer))
    }

    open fun patch(url: GenericUrl, body: Any? = null, customRequestInitializer: HttpRequestInitializer? = null): HttpResponse {
        val requestFactory = getHttpRequestFactory(CompositeHttpRequestInitializer(httpRequestInitializer, customRequestInitializer))
        val content = getJsonHttpContent(body)
        val request = requestFactory.buildPatchRequest(url, content)
        return HttpResponse(request.execute())
    }

    open fun post(url: GenericUrl, body: Any? = null, customRequestInitializer: HttpRequestInitializer? = null): HttpResponse {
        val requestFactory = getHttpRequestFactory(CompositeHttpRequestInitializer(httpRequestInitializer, customRequestInitializer))
        val content = getJsonHttpContent(body)
        val request = requestFactory.buildPostRequest(url, content)
        return HttpResponse(request.execute())
    }

    open fun put(url: GenericUrl?, body: Any? = null, customRequestInitializer: HttpRequestInitializer? = null): HttpResponse {
        val requestFactory = getHttpRequestFactory(CompositeHttpRequestInitializer(httpRequestInitializer, customRequestInitializer))
        val content = getJsonHttpContent(body)
        val request = requestFactory.buildPutRequest(url, content)
        request.isLoggingEnabled = true
        return HttpResponse(request.execute())
    }

    private fun getJsonHttpContent(body: Any?): JsonHttpContent? {
        var content: JsonHttpContent? = null
        if (body != null) content = JsonHttpContent(JacksonFactory(), body)
        return content
    }

    private fun getHttpRequestFactory(httpRequestInitializer: HttpRequestInitializer?): HttpRequestFactory {
        return HTTP_TRANSPORT.createRequestFactory { request: HttpRequest ->
            request.parser = JsonObjectParser(JSON_FACTORY)
            httpRequestInitializer?.initialize(request)
        }
    }

    companion object {
        private val HTTP_TRANSPORT: HttpTransport = ApacheHttpTransport()
        private val JSON_FACTORY: JsonFactory = JacksonFactory()
    }
}
