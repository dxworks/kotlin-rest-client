package org.dxworks.utils.java.rest.client

import com.google.common.annotations.VisibleForTesting
import java.util.*
import java.util.stream.Collectors
import java.util.stream.Stream

open class PathResolver(private val apiUrl: String) : IPathResolver {
    fun getApiPath(variableValues: Map<String, String>, vararg pathVariables: String): String {
        return Stream.concat(Stream.of(apiUrl),
                Arrays.stream(pathVariables).map { `var`: String -> replacePlaceholders(`var`, variableValues) })
                .collect(Collectors.joining("/"))
    }

    @VisibleForTesting
    fun replacePlaceholders(`var`: String, variablesValues: Map<String, String>): String {
        return if (`var`.startsWith(":")) variablesValues.getOrDefault(`var`.replace(":", ""), `var`) else `var`
    }

    fun getApiPath(vararg pathVariables: String): String {
        return getApiPath(emptyMap(), *pathVariables)
    }

}
