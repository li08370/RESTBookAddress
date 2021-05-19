package com.example.demo;

import org.json.JSONObject;

public class GetAddressFromJSON {
    public static Address getAddressFromJSON(String s){
        JSONObject obj = new JSONObject(s);
        return new Address(
                obj.getString("first_name"),
                obj.getString("last_name"),
                obj.getString("phone_number"),
                obj.getString("address"));

    }
}
