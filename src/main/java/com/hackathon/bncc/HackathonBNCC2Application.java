package com.hackathon.bncc;

import com.hackathon.bncc.db.AreaAccessorImpl;
import com.hackathon.bncc.db.FacilityAccessorImpl;
import com.hackathon.bncc.db.FacilityVenueMappingAccessorImpl;
import com.hackathon.bncc.db.PromoteAccessorImpl;
import com.hackathon.bncc.db.SportAccessorImpl;
import com.hackathon.bncc.db.UnitAccessorImpl;
import com.hackathon.bncc.db.UserAccessor;
import com.hackathon.bncc.db.UserAccessorImpl;
import com.hackathon.bncc.db.UserDayMappingAccessorImpl;
import com.hackathon.bncc.db.UserPreferredLocationAccessorImpl;
import com.hackathon.bncc.db.UserSportMappingAccessorImpl;
import com.hackathon.bncc.db.VenueAccessorImpl;
import com.hackathon.bncc.db.VenueSportMappingAccessorImpl;
import com.hackathon.bncc.impl.AreaApiImpl;
import com.hackathon.bncc.impl.FacilityApiImpl;
import com.hackathon.bncc.impl.FacilityVenueMappingApiImpl;
import com.hackathon.bncc.impl.PromoteApiImpl;
import com.hackathon.bncc.impl.SportApiImpl;
import com.hackathon.bncc.impl.UnitApiImpl;
import com.hackathon.bncc.impl.UserApiImpl;
import com.hackathon.bncc.impl.UserDayMappingApiImpl;
import com.hackathon.bncc.impl.UserPreferredLocationApiImpl;
import com.hackathon.bncc.impl.UserSportMappingApiImpl;
import com.hackathon.bncc.impl.VenueApiImpl;
import com.hackathon.bncc.impl.VenueSportMappingApiImpl;
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
            environment.jersey().register(new UserSportMappingApiImpl(new UserSportMappingAccessorImpl()));
            environment.jersey().register(new VenueApiImpl(new VenueAccessorImpl(), new UserSportMappingAccessorImpl()
                , new VenueSportMappingAccessorImpl(), new UserPreferredLocationAccessorImpl(), new SportAccessorImpl()
                , new FacilityVenueMappingAccessorImpl(), new FacilityAccessorImpl(), new AreaAccessorImpl(), new UnitAccessorImpl()));
            environment.jersey().register(new UserDayMappingApiImpl(new UserDayMappingAccessorImpl()));
            environment.jersey().register(new FacilityApiImpl(new FacilityAccessorImpl()));
            environment.jersey().register(new UserPreferredLocationApiImpl(new UserPreferredLocationAccessorImpl()));
            environment.jersey().register(new VenueSportMappingApiImpl(new VenueSportMappingAccessorImpl()));
            environment.jersey().register(new FacilityVenueMappingApiImpl(new FacilityVenueMappingAccessorImpl()));
            environment.jersey().register(new PromoteApiImpl(new PromoteAccessorImpl(), new VenueAccessorImpl()));
            environment.jersey().register(new AreaApiImpl(new AreaAccessorImpl()));
            environment.jersey().register(new UnitApiImpl(new UnitAccessorImpl()));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
