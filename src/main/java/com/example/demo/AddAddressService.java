package com.example.demo;

import org.simpleframework.xml.Path;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;


@Path("/addContact")
    public class AddAddressService {
        @POST
        public Response addContact(String contactString) {
            Address address = GetAddressFromJSON.getAddressFromJSON(contactString);
            String response = UpdateDataBase.addContact(address);
            return Response.status(201).entity(response).build();
        }
    }
