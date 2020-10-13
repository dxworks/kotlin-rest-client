package org.dxworks.utils.java.rest.client.utils

import com.google.api.client.json.JsonObjectParser
import com.google.api.client.json.jackson2.JacksonFactory
import java.io.*
import java.lang.reflect.Type
import java.nio.charset.Charset

open class JsonMapper {
    private val jsonFactory = JacksonFactory()

    fun <T> readJSON(jsonString: String, type: Class<T>): T {
        return JsonObjectParser(jsonFactory).parseAndClose(StringReader(jsonString), type)
    }

    fun <T> readJSON(reader: Reader, type: Class<T>): T {
        return JsonObjectParser(jsonFactory).parseAndClose(reader, type)
    }

    fun <T> readJSONfromFile(file: File, type: Class<T>, encoding: Charset = Charset.defaultCharset()): T {
        return JsonObjectParser(jsonFactory).parseAndClose(getReader(file, encoding), type)
    }

    fun readJSON(jsonString: String, type: Type): Any {
        return JsonObjectParser(jsonFactory).parseAndClose(StringReader(jsonString), type)
    }

    fun readJSON(reader: Reader, type: Type): Any {
        return JsonObjectParser(jsonFactory).parseAndClose(reader, type)
    }

    fun readJSONfromFile(file: File, type: Type, encoding: Charset = Charset.defaultCharset()): Any {
        return JsonObjectParser(jsonFactory).parseAndClose(getReader(file, encoding), type)
    }

    private fun getReader(file: File, encoding: Charset): InputStreamReader {
        return InputStreamReader(FileInputStream(file), encoding)
    }

    fun getSerializedObject(o: Any): String {
        return StringWriter().also {
            writeJSON(it, o)
        }.toString()
    }

    fun getPrettySerializedObject(o: Any): String {
        return StringWriter().also {
            writePrettyJSON(it, o)
        }.toString()
    }

    fun writePrettyJSON(writer: Writer, o: Any) {
        val generator = jsonFactory.createJsonGenerator(writer).apply {
            enablePrettyPrint()
            serialize(o)
            flush()
        }
    }

    fun writeJSON(writer: Writer, o: Any) {
        jsonFactory.createJsonGenerator(writer).apply {
            serialize(o)
            flush()
        }
    }

    fun writePrettyJSONtoFile(file: File, o: Any, encoding: Charset = Charset.defaultCharset()) {
        jsonFactory.createJsonGenerator(getWriter(file, encoding)).apply {
            enablePrettyPrint()
            serialize(o)
            flush()
        }
    }

    fun writeJSONtoFile(file: File, o: Any, encoding: Charset = Charset.defaultCharset()) {
        jsonFactory.createJsonGenerator(getWriter(file, encoding)).apply {
            serialize(o)
            flush()
        }
    }

    private fun getWriter(file: File, encoding: Charset): Writer {
        return OutputStreamWriter(FileOutputStream(file), encoding)
    }
}
