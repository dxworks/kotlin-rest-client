package org.dxworks.utils.java.rest.client.providers

import com.google.api.client.http.HttpRequest

class BasicAuthenticationProvider(private val username: String,
                                  private val password: String) : AuthenticationProvider {

    override fun initialize(httpRequest: HttpRequest) {
        httpRequest.headers.setBasicAuthentication(username, password)
    }
}
