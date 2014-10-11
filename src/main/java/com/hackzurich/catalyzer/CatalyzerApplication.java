package com.hackzurich.catalyzer;

import com.hackzurich.catalyzer.jdbi.ProjectDao;
import com.hackzurich.catalyzer.resources.ProjectResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

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
        // nothing to do yet
    }

    @Override
    public void run(CatalyzerConfiguration configuration,
                    Environment environment) throws ClassNotFoundException {

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "h2");
        final ProjectDao projectDao = jdbi.onDemand(ProjectDao.class);

        environment.jersey().register(new ProjectResource(projectDao));

    }

}
