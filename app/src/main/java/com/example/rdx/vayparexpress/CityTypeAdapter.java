package com.example.rdx.vayparexpress;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import data.model.Citylist_Data;
import data.model.Districtlist_Data;
import data.model.State;

/**
 * Created by RDX on 07-07-2018.
 */

public class CityTypeAdapter implements JsonDeserializer<Districtlist_Data> {
    @Override
    public Districtlist_Data deserialize(JsonElement arg0, Type type, JsonDeserializationContext jdc)
            throws JsonParseException {
        JsonObject decodeObj = arg0.getAsJsonObject();
        Gson gson = new Gson();
        Districtlist_Data decode = new Districtlist_Data();//gson.fromJson(arg0, Districtlist_Data.class);
        ArrayList<Citylist_Data> values = null;
        if (decodeObj.get("citylist").isJsonArray()) {
            values = gson.fromJson(decodeObj.get("citylist"), new TypeToken<List<Citylist_Data>>() {
            }.getType());


        } else {
            //String single = gson.fromJson(decodeObj.get("citylist"), String.class);
            values = new ArrayList<Citylist_Data>();
            //values.add(single);
        }
        decode.setCitylist(values);
        // Deserialize it. You use a new instance of Gson to avoid infinite recursion
        // to this deserializer
        return decode;

    }
}
