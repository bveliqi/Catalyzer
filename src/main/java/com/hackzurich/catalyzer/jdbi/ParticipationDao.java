package com.hackzurich.catalyzer.jdbi;

import com.hackzurich.catalyzer.api.Participation;
import com.hackzurich.catalyzer.api.Project;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.helpers.MapResultAsBean;

import java.util.List;

/**
 * Created by behar on 11.10.14.
 */
public interface ParticipationDao {

    @MapResultAsBean
    @SqlQuery("select * from USERS_PROJECTS LIMIT :from, :number")
    List<Participation> getAll(@Bind("from") int from, @Bind("number") int number);


    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO USERS_PROJECTS " +
            "(projectId, userId, role, state, reason)" +
            " values " +
            "(:projectId, :userId, :role, :state, :reason)")
    long insert(@BindBean Participation participation);


    @MapResultAsBean
    @SqlQuery("select * from PROJECTS as p JOIN USERS_PROJECTS as up WHERE p.id = up.projectId and up.userId = :id")
    List<Project> getProjectsByUserId(@Bind("id") long id);





}
