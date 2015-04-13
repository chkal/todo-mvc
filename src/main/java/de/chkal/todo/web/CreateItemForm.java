package de.chkal.todo.web;

import de.chkal.todo.service.TodoItem;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

  @NotNull(message = "Please select the priority of the task")
  @FormParam("priority")
  private TodoItem.Priority priority;

  @FormParam("duedate")
  private ParsableDate dueDate;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public TodoItem.Priority getPriority() {
    return priority;
  }

  public void setPriority(TodoItem.Priority priority) {
    this.priority = priority;
  }

  public LocalDate getDueDate() {
    return dueDate != null ? dueDate.getDate() : null;
  }

  /**
   * Just a wrapper for a LocalDate with a public constructor having a single string argument.
   * JAX-RS will use this constructor for converting submitted form value to an object.
   */
  public static class ParsableDate {

    private final LocalDate date;

    public ParsableDate(String str) {
      this.date = LocalDate.parse(str, DateTimeFormatter.ISO_DATE);
    }

    public LocalDate getDate() {
      return date;
    }

  }

}
