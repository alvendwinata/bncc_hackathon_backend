package com.hackathon.bncc;

import com.hackathon.bncc.db.SportAccessorImpl;
import com.hackathon.bncc.db.UserAccessor;
import com.hackathon.bncc.db.UserAccessorImpl;
import com.hackathon.bncc.impl.SportApiImpl;
import com.hackathon.bncc.impl.UserApiImpl;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import org.eclipse.jetty.servlets.CrossOriginFilter;

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
        try {
            final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

            // Configure CORS parameters
            cors.setInitParameter("allowedOrigins", "*");
            cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
            cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

            // Add URL mapping
            cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
            environment.jersey().register(new UserApiImpl(new UserAccessorImpl()));
            environment.jersey().register(new SportApiImpl(new SportAccessorImpl()));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
