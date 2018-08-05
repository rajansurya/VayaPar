package com.example.rdx.vayparexpress;

/**
 * Created by RDX on 06-07-2018.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;


public class ArrayAdapter<T> extends TypeAdapter<List<T>> {

    private Class<T> adapterclass;

    public ArrayAdapter(Class<T> adapterclass) {
        this.adapterclass = adapterclass;
    }

    @Override
    public List<T> read(JsonReader reader) throws IOException {

        List<T> list = new ArrayList<T>();
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new ArrayAdapterFactory()).create();

        final JsonToken token = reader.peek();
        // Handling of Scenario 2( Check JavaDoc for the class) :
        if (token == JsonToken.STRING || token == JsonToken.NUMBER ||
                token == JsonToken.BOOLEAN) {
            T inning = (T) gson.fromJson(reader, adapterclass);
            list.add(inning);
        } else if (token == JsonToken.BEGIN_OBJECT) {
            // Handling of Scenario 1(Check JavaDoc for the class) :
            T inning = (T) gson.fromJson(reader, adapterclass);
            list.add(inning);
        } else if (token == JsonToken.BEGIN_ARRAY) {
            reader.beginArray();
            while (reader.hasNext()) {
                @SuppressWarnings("unchecked")
                T inning = (T) gson.fromJson(reader, adapterclass);
                list.add(inning);
            }
            reader.endArray();
        }

        return list;
    }

    @Override
    public void write(JsonWriter writer, List<T> value) throws IOException {

    }


}