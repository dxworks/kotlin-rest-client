package org.dxworks.utils.java.rest.client.utils;

import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import lombok.SneakyThrows;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class JsonMapper {

    private final JacksonFactory jsonFactory = new JacksonFactory();

    public <T> T readJSON(String jsonString, Class<T> type) throws IOException {
        return new JsonObjectParser(jsonFactory).parseAndClose(new StringReader(jsonString), type);
    }

    public <T> T readJSONfromFile(File file, Class<T> type) throws IOException {
        return readJSONfromFile(file, type, Charset.defaultCharset());
    }

    public <T> T readJSONfromFile(File file, Class<T> type, Charset encoding) throws IOException {
        return new JsonObjectParser(jsonFactory).parseAndClose(getReader(file, encoding), type);
    }

    public <T> T readJSON(Reader reader, Class<T> type) throws IOException {
        return new JsonObjectParser(jsonFactory).parseAndClose(reader, type);
    }

    public Object readJSON(String jsonString, Type type) throws IOException {
        return new JsonObjectParser(jsonFactory).parseAndClose(new StringReader(jsonString), type);
    }

    public Object readJSONfromFile(File file, Type type) throws IOException {
        return readJSONfromFile(file, type, Charset.defaultCharset());
    }

    public Object readJSONfromFile(File file, Type type, Charset encoding) throws IOException {
        return new JsonObjectParser(jsonFactory).parseAndClose(getReader(file, encoding), type);
    }

    private InputStreamReader getReader(File file, Charset encoding) throws FileNotFoundException {
        return new InputStreamReader(new FileInputStream(file), encoding);
    }

    public Object readJSON(Reader reader, Type type) throws IOException {
        return new JsonObjectParser(jsonFactory).parseAndClose(reader, type);
    }

    public String getSerializedObject(Object o) throws IOException {
        Writer out = new StringWriter();
        writeJSON(out, o);
        return out.toString();
    }

    public String getPrettySerializedObject(Object o) throws IOException {
        Writer out = new StringWriter();
        writePrettyJSON(out, o);
        return out.toString();
    }

    public void writePrettyJSON(Writer writer, Object o) throws IOException {
        JsonGenerator generator = jsonFactory.createJsonGenerator(writer);
        generator.enablePrettyPrint();
        generator.serialize(o);
        generator.flush();
    }

    public void writeJSON(Writer writer, Object o) throws IOException {
        JsonGenerator generator = jsonFactory.createJsonGenerator(writer);
        generator.serialize(o);
        generator.flush();
    }

    public void writePrettyJSONtoFile(File file, Object o) throws IOException {
        writePrettyJSONtoFile(file, o, Charset.defaultCharset());
    }

    public void writePrettyJSONtoFile(File file, Object o, Charset encoding) throws IOException {
        JsonGenerator generator = jsonFactory.createJsonGenerator(getWriter(file));
        generator.enablePrettyPrint();
        generator.serialize(o);
        generator.flush();
    }

    public void writeJSONtoFile(File file, Object o) throws IOException {
        writeJSONtoFile(file, o, Charset.defaultCharset());
    }

    public void writeJSONtoFile(File file, Object o, Charset encoding) throws IOException {
        JsonGenerator generator = jsonFactory.createJsonGenerator(getWriter(file));
        generator.serialize(o);
        generator.flush();
    }

    @SneakyThrows
    private Writer getWriter(File file) {
        return new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
    }
}
