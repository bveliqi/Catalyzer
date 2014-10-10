package com.hackzurich.catalyzer.api;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by behar on 11.10.14.
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class ProjectResource {


    @GET
    @Timed
    public String helloWord() {
        return "Hello World!";
    }



}
