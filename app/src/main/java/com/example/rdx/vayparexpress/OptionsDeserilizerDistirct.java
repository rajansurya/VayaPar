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
import data.model.State;

/**
 * Created by RDX on 07-07-2018.
 */

public class OptionsDeserilizerDistirct implements JsonDeserializer<State> {
    @Override
    public State deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        if (jsonObject.has("districtlist")) {
            if (jsonObject.get("districtlist").isJsonArray()) {
                Districtlist_Data gsonObj;
//for (JsonElement districtlist:jsonObject.get("districtlist").getAsJsonArray()) {
//    if (districtlist.getAsJsonObject().has("citylist")) {

//                if (jsonObject.get("districtlist").getAsJsonObject().get("citylist").isJsonArray()) {
////            Gson gson = new Gson();
////            gsonObj = gson.fromJson(jsonObject.toString(), Districtlist_Data.class);
//                    Gson gson = new Gson();
//                    State statedata = gson.fromJson(jsonObject.toString(), State.class);
//                    return statedata;
////            System.out.println("CITY " + gsonObj.getDistrict());
////            System.out.println("CITY " + gsonObj.getCitylist().get(0).getCity());
//                } else {
                State notcity = new State();

                ArrayList<Districtlist_Data> districtlist_datacity = new ArrayList<>();
                for (JsonElement districtlist : jsonObject.get("districtlist").getAsJsonArray()) {
                    if (districtlist.getAsJsonObject().has("citylist")) {
                        if (!districtlist.getAsJsonObject().get("citylist").isJsonArray()) {
                            Districtlist_Data districtlist_data = new Districtlist_Data();
                            districtlist_data.setCitylist(new ArrayList<Citylist_Data>());
                            districtlist_data.setDistrictid(districtlist.getAsJsonObject().get("districtid").getAsString());
                            districtlist_data.setDistrict(districtlist.getAsJsonObject().get("district").getAsString());
                            districtlist_datacity.add(districtlist_data);
                        } else {
                            Gson gson = new Gson();
                            Districtlist_Data gsonObjloccc = gson.fromJson(districtlist.getAsJsonObject().toString(), Districtlist_Data.class);
                            districtlist_datacity.add(gsonObjloccc);
                        }
                    }
                }
                notcity.setSubcatlist(districtlist_datacity);
                notcity.setState(jsonObject.get("state").getAsString());
                notcity.setId(jsonObject.get("id").getAsString());
                return notcity;
            } else {
                State state = new State();
                state.setState(jsonObject.get("state").getAsString());
                state.setId(jsonObject.get("id").getAsString());
                state.setSubcatlist(new ArrayList<Districtlist_Data>());
                return state;
            }
        }
        return null;
    }
}
