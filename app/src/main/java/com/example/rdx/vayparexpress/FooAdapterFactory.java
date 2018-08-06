package com.example.rdx.vayparexpress;//package com.example.rdx.vayparexpress;
//
//import com.google.gson.Gson;
//import com.google.gson.TypeAdapter;
//import com.google.gson.TypeAdapterFactory;
//import com.google.gson.reflect.TypeToken;
//import com.google.gson.stream.JsonReader;
//import com.google.gson.stream.JsonWriter;
//
//import java.io.IOException;
//
//import data.model.Citylist_Data;
//
///**
// * Created by RDX on 06-07-2018.
// */
//
//public enum FooAdapterFactory implements TypeAdapterFactory {
//    INSTANCE; // Josh Bloch's Enum singleton pattern
//
//    @SuppressWarnings("unchecked")
//    @Override
//    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
//        if (!Citylist_Data.class.isAssignableFrom(type.getRawType())) return null;
//
//        // Note: You have access to the `gson` object here; you can access other deserializers using gson.getAdapter and pass them into your constructor
//        return (TypeAdapter<T>) new FooAdapter();
//    }
//
//    private static class FooAdapter extends TypeAdapter<Citylist_Data> {
//        @Override
//        public void write(JsonWriter out, Citylist_Data value) {
//            // your code
//        }
//
//        @Override
//        public Citylist_Data read(JsonReader in) throws IOException {
//            // your code
////            return
//        }
//
//
//    }
//}
