package de.chkal.todo.web;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class encapsulates messages displayed to the users. There can be a
 * single info message and multiple error messages. Controllers can use this
 * class to queue messages for rendering. The class shows how named CDI beans
 * can be used as a model for the view. Whether to to include some class like
 * this in the spec is not decided yet.
 */
@Named
@RequestScoped
public class Messages {

  private String info;

  private final List<String> errors = new ArrayList<>();

  public Messages addError(String error) {
    errors.add(error);
    return this;
  }

  public List<String> getErrors() {
    return Collections.unmodifiableList(errors);
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

}
