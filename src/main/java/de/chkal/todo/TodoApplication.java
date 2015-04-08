package de.chkal.todo;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Application class required by JAX-RS. Whether the specification allows
 * "/" or "/*" has to be discussed in the EG.
 *
 * https://java.net/jira/browse/MVC_SPEC-32
 */
@ApplicationPath("/r")
public class TodoApplication extends Application {
}
