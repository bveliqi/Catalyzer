package com.hackzurich.catalyzer.jdbi;

import com.hackzurich.catalyzer.api.User;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.helpers.MapResultAsBean;

import java.util.List;

/**
 * Created by behar on 11.10.14.
 */
public interface UserDao {


    @GetGeneratedKeys
    @SqlUpdate("insert into USERS " +
            "(name, email, password, emailConfirmed, points, totalPoints, avatar)" +
            " values " +
            "(:name, :email, :password, :emailConfirmed, :points, :totalPoints, :avatar)")
    long insert(@BindBean User user);


    @MapResultAsBean
    @SqlQuery("select * from USERS where id = :id")
    User getById(@Bind("id") long id);


    @MapResultAsBean
    @SqlQuery("select * from USERS LIMIT :from, :number")
    List<User> getAll(@Bind("from") int from, @Bind("number") int number);


    @SqlUpdate("UPDATE USERS SET points = points + :morePoints WHERE id = :id")
    void addPoints(@Bind("id") long id, @Bind("morePoints") int morePoints);


    @SqlUpdate("UPDATE USERS SET points = points - :lessPoints WHERE id = :id")
    void removePoints(@Bind("id") long id, @Bind("lessPoints") int lessPoints);




}
