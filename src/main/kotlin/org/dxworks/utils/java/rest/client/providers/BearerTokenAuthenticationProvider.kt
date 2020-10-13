package org.dxworks.utils.java.rest.client.providers

import com.google.api.client.http.HttpRequest

class BearerTokenAuthenticationProvider(private val token: String) : AuthenticationProvider {
    override fun initialize(request: HttpRequest) {
        request.headers.authorization = "Bearer $token"
    }
}
