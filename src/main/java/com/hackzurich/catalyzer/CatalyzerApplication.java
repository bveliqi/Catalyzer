package com.hackzurich.catalyzer;

import com.hackzurich.catalyzer.api.ProjectResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
                    Environment environment) {

        environment.jersey().register(new ProjectResource());

    }

}
