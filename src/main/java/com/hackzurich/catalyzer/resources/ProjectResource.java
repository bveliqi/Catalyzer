package com.hackzurich.catalyzer.resources;

import com.codahale.metrics.annotation.Timed;
import com.hackzurich.catalyzer.api.Project;
import com.hackzurich.catalyzer.api.User;
import com.hackzurich.catalyzer.jdbi.EventDao;
import com.hackzurich.catalyzer.jdbi.ProjectDao;
import io.dropwizard.auth.Auth;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

/**
 * Created by behar on 11.10.14
 */
@Path("project")
@Produces(MediaType.APPLICATION_JSON)
public class ProjectResource {

    private final ProjectDao projectDao;
    private final EventDao eventDao;

    public ProjectResource(ProjectDao projectDao, EventDao eventDao) {
        this.projectDao = projectDao;
        this.eventDao = eventDao;
    }

    @GET
    @Timed
    public List<Project> getAll(@QueryParam("from") int from, @QueryParam("to") int to) {
        return projectDao.getAll(from, to > 0 ? to : 10);
    }

    @GET
    @Timed
    @Path("top")
    public List<Project> getTopProjects(@QueryParam("from") int from, @QueryParam("to") int to) {
        return projectDao.getTopProjects(from, to > 0 ? to : 10);
    }

    @GET
    @Path("{id}")
    @Timed
    public Project getById(@PathParam("id") long id) {
        final Project project = projectDao.getById(id);
        if(project == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        final List<User> acceptedUsers = projectDao.getAllAcceptedUsers(id);
        final List<User> appliedUsers = projectDao.getAllAppliedUsers(id);
        project.setApplyingUsers(appliedUsers);
        project.setApprovedUsers(acceptedUsers);
        return project;
    }


    @POST
    @Timed
    public Response insert(@Auth(required = false) User user, Project project) {
        long id = projectDao.insert(project);
        return Response.created(URI.create(String.valueOf(id))).build();
    }

    @POST
    @Path("{id}/upvote/{points}")
    @Timed
    public void upvote(@Auth(required = false) User user, @PathParam("id") long id, @PathParam("points") int points) {
        if(projectDao.getById(id) == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        projectDao.upvote(id, points);
    }


    @POST
    @Path("{id}/name")
    @Timed
    public void updateName(@Auth(required = false) User user, @PathParam("id") long id, @FormParam("name") String name) {
        if(projectDao.getById(id) == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        projectDao.updateName(id, name);
    }


    @POST
    @Path("{id}/motivation")
    @Timed
    public void updateMotivation(@Auth(required = false) User user,
                                 @PathParam("id") long id, @FormParam("motivation") String motivation) {
        if(projectDao.getById(id) == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        projectDao.updateMotivation(id, motivation);
    }

}
