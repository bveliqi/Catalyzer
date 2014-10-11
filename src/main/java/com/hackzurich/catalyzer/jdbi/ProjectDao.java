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
            "status, startDate, endDate)" +
            " values " +
            "(:authorId, :name, :motivation, :photoUrl, :category, :pointsThreshold, " +
            ":status, :startDate, :endDate)")
    long insert(@BindBean Project project);


    @MapResultAsBean
    @SqlQuery("select * from PROJECTS where id = :id")
    Project getById(@Bind("id") long id);


    @MapResultAsBean
    @SqlQuery("select * from PROJECTS LIMIT :from, :number")
    List<Project> getAll(@Bind("from") int from, @Bind("number") int number);

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

}
