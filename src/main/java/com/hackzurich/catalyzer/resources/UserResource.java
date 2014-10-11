package com.hackzurich.catalyzer.resources;

import com.hackzurich.catalyzer.api.User;
import com.hackzurich.catalyzer.jdbi.UserDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    public User getById(@PathParam("id") long id) {
        return userDao.getById(id);
    }


    @GET
    public List<User> getAll(@QueryParam("from") int from, @QueryParam("to") int to) {
        return userDao.getAll(from, to > 0 ? to : 10);
    }

    @POST
    @Path("/new")
    public long insert() {
        User user = new User();
        user.setName("Behar Veliqi");
        user.setEmail("behar@veliqi.de");
        user.setEmailConfirmed(false);
        user.setAvatar(0);
        user.setPassword("klfrhekfhkshfkjd");
        long id = userDao.insert(user);
        return id;
    }



}
