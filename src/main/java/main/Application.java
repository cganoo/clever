package main;

import client.CleverClient;
import com.google.inject.Guice;
import com.google.inject.Injector;
import modules.CleverModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by cganoo on 28/01/2016.
 */
public class Application {
    private final static Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        /** Using Guice DI create the object dependency graph for {@link CleverClient} */
        Injector injector = Guice.createInjector(new CleverModule());
        CleverClient cleverClient = injector.getInstance(CleverClient.class);

        /** Initiate startup */
        cleverClient.startAsync();

        /** Wait for the {@link CleverClient} to reach {@link Service.State#TERMINATED} state */
        cleverClient.awaitTerminated();
    }
}
