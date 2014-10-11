package com.hackzurich.catalyzer.jdbi;

import com.hackzurich.catalyzer.api.Project;
import com.hackzurich.catalyzer.api.User;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.helpers.MapResultAsBean;

import java.util.List;

/**
 * Created by behar on 11.10.14.
 */
public interface ProjectDao {


    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO PROJECTS " +
            "(authorId, name, motivation, photoUrl, category, pointsThreshold, " +
            "status, startDate, endDate, longitude, latitude, points)" +
            " values " +
            "(:authorId, :name, :motivation, :photoUrl, :category, :pointsThreshold, " +
            ":status, :startDate, :endDate, :longitude, :latitude, :points)")
    long insert(@BindBean Project project);


    @MapResultAsBean
    @SqlQuery("select * from PROJECTS where id = :id")
    Project getById(@Bind("id") long id);


    @MapResultAsBean
    @SqlQuery("select * from PROJECTS LIMIT :from, :number")
    List<Project> getAll(@Bind("from") int from, @Bind("number") int number);

    @MapResultAsBean
    @SqlQuery("select * from PROJECTS ORDER BY points DESC LIMIT :from, :number")
    List<Project> getTopProjects(@Bind("from") int from, @Bind("number") int number);

    @MapResultAsBean
    @SqlQuery("SELECT u.id, u.name" +
            " FROM catalyzer.PROJECTS as p JOIN catalyzer.USERS as u JOIN catalyzer.USERS_PROJECTS as up" +
            " WHERE up.projectId = :id AND up.state = 'ACCEPTED' " +
            " AND p.id = up.projectId AND u.id = up.userId")
    List<User> getAllAcceptedUsers(@Bind("id") long id);


    @MapResultAsBean
    @SqlQuery("SELECT u.id, u.name" +
            " FROM catalyzer.PROJECTS as p JOIN catalyzer.USERS as u JOIN catalyzer.USERS_PROJECTS as up" +
            " WHERE up.projectId = :id AND up.state = 'APPLYING' " +
            " AND p.id = up.projectId AND u.id = up.userId")
    List<User> getAllAppliedUsers(@Bind("id") long id);


    @SqlUpdate("UPDATE PROJECTS SET points = points + :morePoints WHERE id = :id")
    void upvote(@Bind("id") long id, @Bind("morePoints") int morePoints);



    @SqlUpdate("UPDATE PROJECTS SET name = :name WHERE id = :id")
    void updateName(@Bind("id") long id, @Bind("name") String name);


    @SqlUpdate("UPDATE PROJECTS SET motivation = :motivation WHERE id = :id")
    void updateMotivation(@Bind("id") long id, @Bind("motivation") String motivation);



}
