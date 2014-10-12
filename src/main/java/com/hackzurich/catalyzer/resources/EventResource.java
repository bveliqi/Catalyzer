package com.hackzurich.catalyzer.resources;

import com.codahale.metrics.annotation.Timed;
import com.hackzurich.catalyzer.api.Event;
import com.hackzurich.catalyzer.jdbi.EventDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

/**
 * Created by behar on 12.10.14.
 */
@Path("event")
@Produces(MediaType.APPLICATION_JSON)
public class EventResource {


    private final EventDao eventDao;

    public EventResource(EventDao eventDao) {
        this.eventDao = eventDao;
    }


    @GET
    @Path("/{id}")
    @Timed
    public Event getById(@PathParam("id") long id) {
        final Event event = eventDao.getById(id);
        if (event == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return event;
    }


    @GET
    @Timed
    public List<Event> getAll(@QueryParam("from") int from, @QueryParam("to") int to) {
        return eventDao.getAll(from, to > 0 ? to : 10);
    }


    @POST
    @Timed
    public Response insert(Event event) {
        final long id = eventDao.insert(event);
        return Response.created(URI.create(String.valueOf(id))).build();
    }


    @GET
    @Timed
    @Path("userfeed/{id}")
    public List<Event> getUserFeed(@PathParam("id") long id) {
        return eventDao.getUserFeed(id);
    }

    @GET
    @Timed
    @Path("projectfeed/{id}")
    public List<Event> getProjectFeed(@PathParam("id") long id) {
        return eventDao.getProjectFeed(id);
    }


}
