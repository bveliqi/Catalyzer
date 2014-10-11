package com.hackzurich.catalyzer.resources;

import com.codahale.metrics.annotation.Timed;
import com.hackzurich.catalyzer.api.Project;
import com.hackzurich.catalyzer.api.User;
import com.hackzurich.catalyzer.jdbi.ProjectDao;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by behar on 11.10.14.
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class ProjectResource {


    private final ProjectDao projectDao;

    public ProjectResource(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @GET
    @Timed
    public String helloWord() {
        return "Hello World!";
    }

    @POST
    @Path("/new")
    public long insert() {
        Project project = new Project();
        final User author = new User();
        project.setAuthor(author);
        author.setName("Helping ppl in neighbourhood");
        project.setCategory("Whatever");
        project.setMotivation("Helping ppl");
        project.setPhotoUrl("/a/b/c.jpg");
        project.setPointsThreshold(30);
        project.setStatus("OK");
        long id = projectDao.insert(project);
        return id;
    }


}
