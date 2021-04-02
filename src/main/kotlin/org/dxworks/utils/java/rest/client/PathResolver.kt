package org.dxworks.utils.java.rest.client

import com.google.common.annotations.VisibleForTesting
import java.util.*
import java.util.stream.Collectors
import java.util.stream.Stream

open class PathResolver(private val apiUrl: String) {
    fun getApiPath(variableValues: Map<String, String>, vararg pathVariables: String): String {
        return Stream.concat(Stream.of(apiUrl),
            Arrays.stream(pathVariables).map { v: String -> replacePlaceholders(v, variableValues) })
            .collect(Collectors.joining("/"))
    }

    @VisibleForTesting
    fun replacePlaceholders(v: String, variablesValues: Map<String, String>): String {
        return if (v.startsWith(":")) variablesValues.getOrDefault(v.replace(":", ""), v) else v
    }

    fun getApiPath(vararg pathVariables: String): String {
        return getApiPath(emptyMap(), *pathVariables)
    }

}
