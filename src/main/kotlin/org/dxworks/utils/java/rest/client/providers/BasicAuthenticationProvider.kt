package org.dxworks.utils.java.rest.client.providers

import com.google.api.client.http.HttpRequest

open class BasicAuthenticationProvider(
    protected val username: String,
    protected val password: String
) : AuthenticationProvider {

    override fun initialize(httpRequest: HttpRequest) {
        httpRequest.headers.setBasicAuthentication(username, password)
    }
}
