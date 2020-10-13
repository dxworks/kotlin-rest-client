package org.dxworks.utils.java.rest.client.response

import com.google.api.client.http.*
import com.google.api.client.http.HttpResponse
import java.io.InputStream
import java.io.OutputStream
import java.lang.reflect.Type
import java.nio.charset.Charset

open class HttpResponse(private val response: HttpResponse) {
    val contentLoggingLimit: Int
        get() = response.contentLoggingLimit

    fun setContentLoggingLimit(contentLoggingLimit: Int): HttpResponse {
        return response.setContentLoggingLimit(contentLoggingLimit)
    }

    val isLoggingEnabled: Boolean
        get() = response.isLoggingEnabled

    fun setLoggingEnabled(loggingEnabled: Boolean): HttpResponse {
        return response.setLoggingEnabled(loggingEnabled)
    }

    val contentEncoding: String
        get() = response.contentEncoding

    val contentType: String
        get() = response.contentType

    val mediaType: HttpMediaType
        get() = response.mediaType

    val headers: HttpHeaders
        get() = response.headers

    val isSuccessStatusCode: Boolean
        get() = response.isSuccessStatusCode

    val statusCode: Int
        get() = response.statusCode

    val statusMessage: String
        get() = response.statusMessage

    val transport: HttpTransport
        get() = response.transport

    val request: HttpRequest
        get() = response.request

    val content: InputStream
        get() = response.content

    fun download(outputStream: OutputStream?) {
        response.download(outputStream)
    }

    fun ignore() {
        response.ignore()
    }

    fun disconnect() {
        response.disconnect()
    }

    fun <T> parseAs(dataClass: Class<T>?): T {
        return response.parseAs(dataClass)
    }

    fun parseAs(dataType: Type?): Any {
        return response.parseAs(dataType)
    }

    fun parseAsString(): String {
        return response.parseAsString()
    }

    val contentCharset: Charset
        get() = response.contentCharset

}
