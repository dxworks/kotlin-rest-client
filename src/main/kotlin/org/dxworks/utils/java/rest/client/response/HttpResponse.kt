package org.dxworks.utils.java.rest.client.response

import com.google.api.client.http.*
import com.google.api.client.http.HttpResponse
import com.google.api.client.util.IOUtils
import org.dxworks.utils.java.rest.client.utils.JsonMapper
import java.io.InputStream
import java.io.OutputStream
import java.lang.reflect.Type
import java.nio.charset.Charset

/**
 * A wrapper for the com.google.api.client.http.HttpResponse class
 *
 * This is built with the intent to let REST API Client Developers to extend the response with fields and methods.
 */
open class HttpResponse(val response: HttpResponse) {

    private val contentString: String = readContentString()

    protected open fun readContentString(): String = response.parseAsString()

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
        get() = contentString.byteInputStream()

    fun download(outputStream: OutputStream?) {
        IOUtils.copy(contentString.byteInputStream(), outputStream)
    }

    fun ignore() {
    }

    fun disconnect() {
        response.disconnect()
    }

    fun <T> parseAs(dataClass: Class<T>): T {
        return JsonMapper().readJSON(contentString, dataClass)
    }

    fun parseAs(dataType: Type): Any {
        return JsonMapper().readJSON(contentString, dataType)
    }

    fun parseAsString(): String {
        return contentString
    }

    val contentCharset: Charset
        get() = response.contentCharset

}
