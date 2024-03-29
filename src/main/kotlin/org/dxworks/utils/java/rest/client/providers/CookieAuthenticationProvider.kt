package org.dxworks.utils.java.rest.client.providers

import com.google.api.client.http.HttpRequest

open class CookieAuthenticationProvider(protected val cookie: String) : AuthenticationProvider {
    override fun initialize(httpRequest: HttpRequest) {
        httpRequest.headers.cookie = cookie
    }
}
