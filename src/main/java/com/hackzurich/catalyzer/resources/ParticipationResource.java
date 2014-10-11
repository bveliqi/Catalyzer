package com.hackzurich.catalyzer.resources;

import com.codahale.metrics.annotation.Timed;
import com.hackzurich.catalyzer.api.Participation;
import com.hackzurich.catalyzer.api.Project;
import com.hackzurich.catalyzer.jdbi.ParticipationDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

/**
 * Created by behar on 11.10.14.
 */
@Path("participation")
@Produces(MediaType.APPLICATION_JSON)
public class ParticipationResource {

    private final ParticipationDao participationDao;

    public ParticipationResource(ParticipationDao participationDao) {
        this.participationDao = participationDao;
    }


    @GET
    @Path("/all")
    @Timed
    public List<Participation> getAll(@QueryParam("from") int from, @QueryParam("to") int to) {
        return participationDao.getAll(from, to > 0 ? to : 10);
    }


    @POST
    @Timed
    public Response insert(Participation participation) {
        final long id = participationDao.insert(participation);
        return Response.created(URI.create(String.valueOf(id))).build();
    }


    @GET
    @Path("user/{userId}")
    @Timed
    public List<Project> getParticipations(@PathParam("userId") long userId) {
        return participationDao.getProjectsByUserId(userId);
    }


}
