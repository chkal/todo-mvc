package de.chkal.todo;

import javax.mvc.security.Csrf;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;

/**
 * Application class required by JAX-RS. If you don't want to have any
 * prefix in the URL, you can set the application path to "/".
 */
@ApplicationPath("/r")
public class TodoApplication extends Application {

  @Override
  public Map<String, Object> getProperties() {
    Map<String, Object> properties = new HashMap<>();

    /*
     * Enables CSRF protection. If enabled, you will have to add an
     * hidden input to your forms which wil contains the required token.
     * See items.jsp for an example.
     */
    properties.put(Csrf.CSRF_PROTECTION, Csrf.CsrfOptions.IMPLICIT);

    return properties;
  }

}
