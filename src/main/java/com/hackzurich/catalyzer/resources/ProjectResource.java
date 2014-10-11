package com.hackzurich.catalyzer.resources;

import com.codahale.metrics.annotation.Timed;
import com.hackzurich.catalyzer.api.Project;
import com.hackzurich.catalyzer.api.User;
import com.hackzurich.catalyzer.jdbi.ProjectDao;
import com.hackzurich.catalyzer.jdbi.UserDao;
import org.joda.time.DateTime;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by behar on 11.10.14.
 */
@Path("/project")
@Produces(MediaType.APPLICATION_JSON)
public class ProjectResource {

    private final ProjectDao projectDao;
    private final UserDao userDao;

    public ProjectResource(ProjectDao projectDao, UserDao userDao) {
        this.projectDao = projectDao;
        this.userDao = userDao;
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
        final Project project = projectDao.getById(id);
        final List<User> acceptedUsers = projectDao.getAllAcceptedUsers(id);
        final List<User> appliedUsers = projectDao.getAllAppliedUsers(id);
        project.setApplyingUsers(appliedUsers);
        project.setApprovedUsers(acceptedUsers);
        return project;
    }

    @GET
    public List<Project> getAll(@QueryParam("from") int from, @QueryParam("to") int to) {
        return projectDao.getAll(from, to > 0 ? to : 10);
    }

    @POST
    @Path("/new")
    public long insert() {
        Project project = new Project();
        final User author = getAuthor();
        project.setName("Helping ppl in neighbourhood");
        project.setCategory("Whatever");
        project.setMotivation("Helping ppl");
        project.setPhotoUrl("/a/b/c.jpg");
        project.setPointsThreshold(30);
        project.setStatus("OK");
        project.setStartDate(new Timestamp(DateTime.now().getMillis()));
        long id = projectDao.insert(project, author);
        return id;
    }

    private long getAuthorId() {
        final User author = new User();
        author.setName("Behar Veliqi");
        final long id = userDao.insert(author);
        return id;
    }

    private User getAuthor() {
        final User author = userDao.getById(1);
        return author;
    }


}
