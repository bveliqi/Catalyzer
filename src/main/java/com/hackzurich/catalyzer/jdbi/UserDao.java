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
            "(name, email, password, emailConfirmed, points, totalPoints, avatar, motivation, subtitle)" +
            " values " +
            "(:name, :email, :password, :emailConfirmed, :points, :totalPoints, :avatar, :motivation, :subtitle)")
    long insert(@BindBean User user);

    
    @MapResultAsBean
    @SqlQuery("select * from USERS where id = :id")
    User getById(@Bind("id") long id);

    @MapResultAsBean
    @SqlQuery("select * from USERS where name = :name")
    User getByName(@Bind("name") String name);


    @MapResultAsBean
    @SqlQuery("select * from USERS LIMIT :from, :number")
    List<User> getAll(@Bind("from") int from, @Bind("number") int number);


    @SqlUpdate("UPDATE USERS SET points = points + :morePoints WHERE id = :id")
    void addPoints(@Bind("id") long id, @Bind("morePoints") int morePoints);


    @SqlUpdate("UPDATE USERS SET points = points - :lessPoints WHERE id = :id")
    void removePoints(@Bind("id") long id, @Bind("lessPoints") int lessPoints);


    @SqlUpdate("UPDATE USERS SET name = :name WHERE id = :id")
    void updateName(@Bind("id") long id, @Bind("name") String name);

    @SqlUpdate("UPDATE USERS SET motivation = :motivation WHERE id = :id")
    void updateMotivation(@Bind("id") long id, @Bind("motivation") String motivation);

    @SqlUpdate("UPDATE USERS SET subtitle = :subtitle WHERE id = :id")
    void updateSubtitle(@Bind("id") long id, @Bind("subtitle") String subtitle);

}
