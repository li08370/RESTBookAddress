package com.example.demo;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

@Path("/listContacts")
public class ListContacts {

    JSONObject jsonMain = new JSONObject();
    JSONArray jsonContactArray = new JSONArray();
    JSONObject jsonContact = new JSONObject();

    //Returns a JSON that contains the JSONArray of Contacts in JSON format. Say that 5 times over
    @GET
    @Produces("application/json")
    public Response returnListOfBooksJSON() {
        ArrayList<Address> contactList = UpdateDataBase.listContacts();
        String jsonString = "" + jsonInfoOfContacts(contactList);
        return Response.status(200).entity(jsonString).build();

    }

    //Turns the ArrayList of contacts into the JSON format mentioned above.
    private JSONObject jsonInfoOfContacts(ArrayList<Address> contactList) {
        for (Address address: contactList) {
            jsonContact = new JSONObject();

            jsonContact.put("firstname", address.getFirst_name());
            jsonContact.put("lastname", address.getLast_name());
            jsonContact.put("phonenumber", address.getPhone_number());
            //jsonContact.put("address", address.getAddreess);
            jsonContactArray.put(jsonContact);
            jsonContact = null;
        }
        jsonMain.put("Contacts", jsonContactArray);
        return jsonMain;
    }


}