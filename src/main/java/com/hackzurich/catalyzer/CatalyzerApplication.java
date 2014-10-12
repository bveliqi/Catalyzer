package com.hackzurich.catalyzer;

import com.hackzurich.catalyzer.api.User;
import com.hackzurich.catalyzer.auth.SimpleAuthenticator;
import com.hackzurich.catalyzer.jdbi.EventDao;
import com.hackzurich.catalyzer.jdbi.ParticipationDao;
import com.hackzurich.catalyzer.jdbi.ProjectDao;
import com.hackzurich.catalyzer.jdbi.UserDao;
import com.hackzurich.catalyzer.resources.EventResource;
import com.hackzurich.catalyzer.resources.ParticipationResource;
import com.hackzurich.catalyzer.resources.ProjectResource;
import com.hackzurich.catalyzer.resources.UserResource;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.basic.BasicAuthProvider;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.skife.jdbi.v2.DBI;

import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;


/**
 * Created by behar on 11.10.14.
 */
public class CatalyzerApplication extends Application<CatalyzerConfiguration> {

    public static void main(String[] args) throws Exception {
        new CatalyzerApplication().run(args);
    }

    @Override
    public String getName() {
        return "Catalyzer";
    }

    @Override
    public void initialize(Bootstrap<CatalyzerConfiguration> bootstrap) {
    	bootstrap.addBundle(new AssetsBundle());
    }

    @Override
    public void run(CatalyzerConfiguration configuration,
                    Environment environment) throws ClassNotFoundException, UnknownHostException, NoSuchAlgorithmException {

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
        final ProjectDao projectDao = jdbi.onDemand(ProjectDao.class);
        final UserDao userDao = jdbi.onDemand(UserDao.class);
        final ParticipationDao participationDao = jdbi.onDemand(ParticipationDao.class);
        final EventDao eventDao = jdbi.onDemand(EventDao.class);

        environment.jersey().register(new ProjectResource(projectDao, eventDao));
        environment.jersey().register(new UserResource(userDao, eventDao));
        environment.jersey().register(new ParticipationResource(participationDao, eventDao));
        environment.jersey().register(new EventResource(eventDao));

        environment.jersey().register(new BasicAuthProvider<User>(new SimpleAuthenticator(userDao), "realm"));

    }


}
