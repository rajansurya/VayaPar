package com.example.rdx.vayparexpress;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import data.model.Citylist_Data;
import data.model.Districtlist_Data;

/**
 * Created by RDX on 07-07-2018.
 */

public class OptionsDeserilizer implements JsonDeserializer<Districtlist_Data>{
    @Override
    public Districtlist_Data deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        Districtlist_Data gsonObj=new Districtlist_Data();
        if (jsonObject.has("citylist")) {

            if (jsonObject.get("citylist").isJsonArray()) {
                Gson gson = new Gson();
                 gsonObj = gson.fromJson(jsonObject.toString(), Districtlist_Data.class);
                System.out.println("CITY "+gsonObj.getDistrict());
                System.out.println("CITY "+gsonObj.getCitylist().get(0).getCity());
            }
        }
        return gsonObj;
    }
}
