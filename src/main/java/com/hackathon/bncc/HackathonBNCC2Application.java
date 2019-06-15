package com.hackathon.bncc;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HackathonBNCC2Application extends Application<HackathonBNCC2Configuration> {

    public static void main(final String[] args) throws Exception {
        new HackathonBNCC2Application().run(args);
    }

    @Override
    public String getName() {
        return "HackathonBNCC2";
    }

    @Override
    public void initialize(final Bootstrap<HackathonBNCC2Configuration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final HackathonBNCC2Configuration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
