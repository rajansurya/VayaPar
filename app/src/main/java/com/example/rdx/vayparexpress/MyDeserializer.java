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
import data.model.StateList;

/**
 * Created by RDX on 06-07-2018.
 */

class MyDeserializer implements JsonDeserializer<State> {
    @Override
    public State deserialize(JsonElement arg0, Type type, JsonDeserializationContext jdc)
            throws JsonParseException {
        JsonObject decodeObj = arg0.getAsJsonObject();
        Gson gson = new Gson();
        State decode = new State();//gson.fromJson(arg0, State.class);
        ArrayList<Districtlist_Data> values = new ArrayList<>();
        if (decodeObj.get("districtlist").isJsonArray()) {
            for (JsonElement jsonElement : decodeObj.get("districtlist").getAsJsonArray()) {
                Districtlist_Data districtlist_data = new Districtlist_Data();
                if (jsonElement.getAsJsonObject().get("citylist").isJsonArray()) {
                    ArrayList<Citylist_Data> citylist_dataarray = new ArrayList<>();
                    for (JsonElement cityone : jsonElement.getAsJsonObject().get("citylist").getAsJsonArray()) {
                        Citylist_Data onecity = new Citylist_Data();
                        onecity.setCity(cityone.getAsJsonObject().get("city").getAsString());
                        onecity.setCityid(cityone.getAsJsonObject().get("cityid").getAsString());
                        citylist_dataarray.add(onecity);
                    }
//                    districtlist_data = gson.fromJson(jsonElement.get("citylist").getAsJsonArray(), new TypeToken<Districtlist_Data>() {
//                    }.getType());
                    districtlist_data.setCitylist(citylist_dataarray);
                    districtlist_data.setDistrictid(jsonElement.getAsJsonObject().get("districtid").getAsString());
                    districtlist_data.setDistrictid(jsonElement.getAsJsonObject().get("district").getAsString());
                }
                values.add(districtlist_data);
            }

//            values = gson.fromJson(decodeObj.get("districtlist"), new TypeToken<List<Districtlist_Data>>() {
//            }.getType());

        } else {
//            String single = gson.fromJson(decodeObj.get("value"), String.class);
            values = new ArrayList<Districtlist_Data>();
//            values.add(single);
        }
        decode.setSubcatlist(values);
        decode.setId(decodeObj.get("id").getAsString());
        decode.setState(decodeObj.get("state").getAsString());
        // Deserialize it. You use a new instance of Gson to avoid infinite recursion
        // to this deserializer
        return decode;

    }
}
