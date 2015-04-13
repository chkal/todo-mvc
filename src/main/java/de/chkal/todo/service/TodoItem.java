package de.chkal.todo.service;

import java.time.LocalDate;

/**
 * The model object representing a single item.
 * Could be an JPA entity in real world applications.
 */
public class TodoItem {

  public enum Priority {
    LOW,
    MEDIUM,
    HIGH
  }

  private long id;

  private String title;

  private Priority priority;

  private LocalDate dueDate;

  public TodoItem() {
    // default constructor
  }

  public TodoItem(long id, String title, Priority priority, LocalDate dueDate) {
    this.id = id;
    this.title = title;
    this.priority = priority;
    this.dueDate = dueDate;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Priority getPriority() {
    return priority;
  }

  public void setPriority(Priority priority) {
    this.priority = priority;
  }

  public LocalDate getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }
}
