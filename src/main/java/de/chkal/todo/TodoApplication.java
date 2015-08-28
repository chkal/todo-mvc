package de.chkal.todo;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Application class required by JAX-RS. If you don't want to have any
 * prefix in the URL, you can set the application path to "/".
 */
@ApplicationPath("/")
public class TodoApplication extends Application {
}
