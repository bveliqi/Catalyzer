package com.hackzurich.catalyzer.resources;

import com.codahale.metrics.annotation.Timed;
import com.hackzurich.catalyzer.api.User;
import com.hackzurich.catalyzer.auth.PasswordHashing;
import com.hackzurich.catalyzer.jdbi.UserDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

/**
 * Created by behar on 11.10.14.
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserDao userDao;

    public UserResource(UserDao userDao) {
        this.userDao = userDao;
    }


    @GET
    @Path("/{id}")
    @Timed
    public User getById(@PathParam("id") long id) {
        final User user = userDao.getById(id);
        if (user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return user;
    }


    @GET
    @Timed
    public List<User> getAll(@QueryParam("from") int from, @QueryParam("to") int to) {
        return userDao.getAll(from, to > 0 ? to : 10);
    }

    @POST
    @Timed
    public Response insert(User user) {
        String finalPassword = PasswordHashing.hash(user.getPassword());
        user.setPassword(finalPassword);
        long id = userDao.insert(user);
        return Response.created(URI.create(String.valueOf(id))).build();
    }

    @POST
    @Path("{id}/name")
    @Timed
    public void updateName(@PathParam("id") long id, @FormParam("name") String name) {
        if(userDao.getById(id) == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        userDao.updateName(id, name);
    }

    @POST
    @Path("{id}/motivation")
    @Timed
    public void updateMotivation(@PathParam("id") long id, @FormParam("motivation") String motivation) {
        if(userDao.getById(id) == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        userDao.updateMotivation(id, motivation);
    }

    @POST
    @Path("{id}/subtitle")
    @Timed
    public void updateSubtitle(@PathParam("id") long id, @FormParam("subtitle") String subtitle) {
        if(userDao.getById(id) == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        userDao.updateSubtitle(id, subtitle);
    }

    @POST
    @Timed
    @Path("{id}/points/add/{points}")
    public void addPoints(@PathParam("id") long id, @PathParam("points") int points) {
        if (userDao.getById(id) == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        userDao.addPoints(id, points);
    }

    @POST
    @Timed
    @Path("{id}/points/remove/{points}")
    public void removePoints(@PathParam("id") long id, @PathParam("points") int points) {
        if (userDao.getById(id) == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        userDao.removePoints(id, points);
    }


}
