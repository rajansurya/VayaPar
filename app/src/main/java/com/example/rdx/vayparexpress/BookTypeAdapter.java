package com.example.rdx.vayparexpress;

/**
 * Created by RDX on 06-07-2018.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import data.model.Citylist_Data;

public class BookTypeAdapter extends TypeAdapter {

    @Override
    public void write(JsonWriter out, Object value) throws IOException {

    }

    @Override
    public List<Citylist_Data> read(final JsonReader reader) throws IOException {
        List<Citylist_Data> list = new ArrayList<Citylist_Data>();
//        final Citylist_Data book = new Citylist_Data();
     /*   if (reader.peek() == JsonToken.BEGIN_ARRAY) {

            reader.beginArray();
            while (reader.hasNext()) {
                Citylist_Data inning = gson.fromJson(reader, Citylist_Data.class);
                list.add(inning);
            }
            reader.endArray();
        }*/

        return list;
    }

    /*@Override
    public void write(final JsonWriter out,  List<Citylist_Data> value) throws IOException {
//        out.beginObject();
//        out.name("isbn").value(book.getIsbn());
//        out.name("title").value(book.getTitle());
//        out.name("authors").value(StringUtils.join(book.getAuthors(), ";"));
//        out.endObject();
    }*/
}
