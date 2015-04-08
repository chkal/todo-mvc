package de.chkal.todo.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

/**
 * This class represents the form which is submitted when the user creates a new item.
 * The classes uses {@link FormParam} to map the form data to fields. The fields are
 * annotated with Bean Validation annotations to describe constraints.
 */
public class CreateItemForm {

  @NotNull
  @Size(min = 3, message = "The title must be at least 3 characters")
  @FormParam("title")
  private String title;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

}
