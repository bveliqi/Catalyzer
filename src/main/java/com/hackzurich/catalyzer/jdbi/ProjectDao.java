package com.hackzurich.catalyzer.jdbi;

import com.hackzurich.catalyzer.api.Project;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.helpers.MapResultAsBean;

import java.util.List;

/**
 * Created by behar on 11.10.14.
 */
public interface ProjectDao {


    @GetGeneratedKeys
    @SqlUpdate("insert into PROJECTS " +
            "(author, name, motivation, photoUrl, category, pointsThreshold, status)" +
            " values " +
            "(:author, :name, :motivation, :photoUrl, :category, :pointsThreshold, :status)")
    long insert(@BindBean Project project);


    @MapResultAsBean
    @SqlQuery("select * from PROJECTS where id = :id")
    Project getById(@Bind("id") long id);


    @MapResultAsBean
    @SqlQuery("select * from PROJECTS LIMIT :from, :number")
    List<Project> getAll(@Bind("from") int from, @Bind("number") int number);


}
