package com.hackzurich.catalyzer.jdbi;

import com.hackzurich.catalyzer.api.Event;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.helpers.MapResultAsBean;

import java.util.List;

/**
 * Created by behar on 12.10.14.
 */
public interface EventDao {

    @MapResultAsBean
    @SqlQuery("select * from EVENTS LIMIT :from, :number")
    List<Event> getAll(@Bind("from") int from, @Bind("number") int number);

    @MapResultAsBean
    @SqlQuery("select * from EVENTS WHERE id = :id")
    Event getById(@Bind("id") long id);

    @MapResultAsBean
    @SqlQuery(" SELECT * FROM catalyzer.EVENTS as e " +
            " WHERE e.userId IN ( " +
            " SELECT followingId FROM catalyzer.FOLLOWERS as f " +
            " WHERE f.followerId = :userId)" +
            " OR e.projectId IN ( " +
            " SELECT followingId FROM catalyzer.FOLLOWERS as f " +
            " WHERE f.followerId = :userId" +
            ")")
    List<Event> getUserFeed(@Bind("userId") long userId);


    @MapResultAsBean
    @SqlQuery("SELECT * FROM catalyzer.EVENTS WHERE projectId = :projectId")
    List<Event> getProjectFeed(@Bind("projectId") long projectId);


    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO EVENTS " +
            "(userId, projectId, message, timestamp)" +
            " values " +
            "(:userId, :projectId, :message, :timestamp)")
    long insert(@BindBean Event event);

}
