package com.hackzurich.catalyzer.resources;

import com.codahale.metrics.annotation.Timed;
import com.hackzurich.catalyzer.api.Project;
import com.hackzurich.catalyzer.api.User;
import com.hackzurich.catalyzer.jdbi.ProjectDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

/**
 * Created by behar on 11.10.14.
 */
@Path("project")
@Produces(MediaType.APPLICATION_JSON)
public class ProjectResource {

    private final ProjectDao projectDao;

    public ProjectResource(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @GET
    @Timed
    public List<Project> getAll(@QueryParam("from") int from, @QueryParam("to") int to) {
        return projectDao.getAll(from, to > 0 ? to : 10);
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
    public Response insert(Project project) {
        long id = projectDao.insert(project);
        return Response.created(URI.create("/projects/" + String.valueOf(id))).build();
    }

    @POST
    @Path("{id}/upvote/{points}")
    @Timed
    public void upvote(@PathParam("id") long id, @PathParam("points") int points) {
        if(projectDao.getById(id) == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        projectDao.upvote(id, points);
    }

}
