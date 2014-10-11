package com.hackzurich.catalyzer.resources;

import com.codahale.metrics.annotation.Timed;
import com.hackzurich.catalyzer.api.User;
import com.hackzurich.catalyzer.jdbi.UserDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by behar on 11.10.14.
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private static final String SALT = "EA333B0BFC3DFFE85";

    private final UserDao userDao;
    final MessageDigest digestInstance;

    public UserResource(UserDao userDao) throws NoSuchAlgorithmException {
        this.userDao = userDao;
        digestInstance = MessageDigest.getInstance("SHA-256");
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
        String finalPassword = hashPassword(user);
        user.setPassword(finalPassword);
        long id = userDao.insert(user);
        return Response.created(URI.create(String.valueOf(id))).build();
    }

    private String hashPassword(User user) {
        String password = user.getPassword() == null ? "" : user.getPassword();
        final byte[] bytes = password.getBytes(StandardCharsets.UTF_8);
        digestInstance.update(bytes);
        final byte[] digest = digestInstance.digest();
        final String hashedPassword = new HexBinaryAdapter().marshal(digest);
        return SALT + hashedPassword;
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
