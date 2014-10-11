package com.hackzurich.catalyzer.resources;

import com.codahale.metrics.annotation.Timed;
import com.hackzurich.catalyzer.api.Project;
import com.hackzurich.catalyzer.jdbi.ProjectDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by behar on 11.10.14.
 */
@Path("/project")
@Produces(MediaType.APPLICATION_JSON)
public class ProjectResource {

    private final ProjectDao projectDao;

    public ProjectResource(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @GET
    @Timed
    @Path("hello")
    public String helloWord() {
        return "Hello World!";
    }


    @GET
    @Path("{id}")
    public Project getById(@PathParam("id") long id) {
        return projectDao.getById(id);
    }

    @GET
    public List<Project> getAll(@QueryParam("from") int from, @QueryParam("to") int to) {
        return projectDao.getAll(from, to > 0 ? to : 10);
    }

    @POST
    @Path("/new")
    public long insert() {
        Project project = new Project();
//        final User author = new User();
//        author.setName("Behar Veliqi");
//        project.setAuthor(author);
        project.setAuthor("Behar Veliqi");
        project.setName("Helping ppl in neighbourhood");
        project.setCategory("Whatever");
        project.setMotivation("Helping ppl");
        project.setPhotoUrl("/a/b/c.jpg");
        project.setPointsThreshold(30);
        project.setStatus("OK");
        long id = projectDao.insert(project);
        return id;
    }



}
