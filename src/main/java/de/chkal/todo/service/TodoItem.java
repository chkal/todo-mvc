package de.chkal.todo.service;

/**
 * The model object representing a single item.
 * Could be an JPA entity in real world applications.
 */
public class TodoItem {

  private long id;

  private String title;

  public TodoItem() {
    // default constructor
  }

  public TodoItem(long id, String title) {
    this.id = id;
    this.title = title;
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
}
