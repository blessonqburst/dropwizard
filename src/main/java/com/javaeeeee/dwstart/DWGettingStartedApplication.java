package com.javaeeeee.dwstart;

import com.javaeeeee.dwstart.auth.GreetingAuthenticator;
import com.javaeeeee.dwstart.core.Employee;
import com.javaeeeee.dwstart.core.User;
import com.javaeeeee.dwstart.db.EmployeeDAO;
import com.javaeeeee.dwstart.resources.EmployeesResource;
import com.javaeeeee.dwstart.resources.HelloResource;
import com.javaeeeee.dwstart.resources.SecuredHelloResource;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthFactory;
import io.dropwizard.auth.basic.BasicAuthFactory;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * The application class.
 *
 * @author Dmitry Noranovich
 */
public class DWGettingStartedApplication
        extends Application<DWGettingStartedConfiguration> {

    /**
     * Hibernate bundle.
     */
    private final HibernateBundle<DWGettingStartedConfiguration> hibernateBundle
            = new HibernateBundle<DWGettingStartedConfiguration>(
                    Employee.class
            ) {

                @Override
                public DataSourceFactory getDataSourceFactory(
                        DWGettingStartedConfiguration configuration
                ) {
                    return configuration.getDataSourceFactory();
                }

            };

    /**
     * The main method of the application.
     *
     * @param args command-line arguments
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {
        new DWGettingStartedApplication().run(args);
    }

    @Override
    public String getName() {
        return "DWGettingStarted";
    }

    @Override
    public void initialize(
            final Bootstrap<DWGettingStartedConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(final DWGettingStartedConfiguration configuration,
            final Environment environment) {
        final EmployeeDAO employeeDAO
                = new EmployeeDAO(hibernateBundle.getSessionFactory());
        environment.jersey().register(AuthFactory.binder(
                new BasicAuthFactory<>(
                        new GreetingAuthenticator(configuration.getLogin(),
                                configuration.getPassword()),
                        "SECURITY REALM",
                        User.class)));
        environment.jersey().register(new HelloResource());
        environment.jersey().register(new SecuredHelloResource());
        environment.jersey().register(new EmployeesResource(employeeDAO));
    }

}
