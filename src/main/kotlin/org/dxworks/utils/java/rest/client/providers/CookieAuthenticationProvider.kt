package org.dxworks.utils.java.rest.client.providers

import com.google.api.client.http.HttpRequest

class CookieAuthenticationProvider(private val cookie: String) : AuthenticationProvider {
    override fun initialize(httpRequest: HttpRequest) {
        httpRequest.headers.cookie = cookie
    }
}
